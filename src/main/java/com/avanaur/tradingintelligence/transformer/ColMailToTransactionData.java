package com.avanaur.tradingintelligence.transformer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.avanaur.tradingintelligence.model.TransactionData;
import com.avanaur.tradingintelligence.model.TransactionType;

public class ColMailToTransactionData extends AbstractMessageTransformer {

	private static final Log LOGGER = LogFactory
			.getLog(ColMailToTransactionData.class);

	/**
	 * Transform list of projects to JSON
	 */
	@Override
	public List<TransactionData> transformMessage(MuleMessage message,
			String outputEncoding) throws TransformerException {

		List<TransactionData> transactions = new ArrayList<TransactionData>();
		try {
			Document payloadDoc = (Document) message.getPayload();

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			NodeList securities = (NodeList) xpath.compile(
					"//table[.//font=\'Action:\']").evaluate(payloadDoc,
					XPathConstants.NODESET);
			NodeList tradeList = (NodeList) xpath.compile(
					"//table[.//font=\'Quantity\']").evaluate(payloadDoc,
					XPathConstants.NODESET);

			DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");

			for (int i = 0; i < securities.getLength(); i++) {

				Node security = securities.item(i);
				Node trades = tradeList.item(i);

				NodeList securityData = (NodeList) xpath.compile(".//font")
						.evaluate(security, XPathConstants.NODESET);
				NodeList tradeData = (NodeList) xpath.compile(".//font")
						
						.evaluate(trades, XPathConstants.NODESET);
				for (int j = 0; j < (tradeData.getLength() - 17)/6; j++) {
					
					TransactionData data = new TransactionData();
					data.setTransactionOrder((j+1));
					data.setAction(TransactionType.getTransactionType(securityData.item(1).getTextContent().trim()));
					data.setCharges(Double.parseDouble(tradeData.item(j*6+7+4).getTextContent().trim().replace(",", "")) + Double.parseDouble(tradeData.item(j*6+7+3).getTextContent().trim().replace(",", "")));
					data.setGrossAmount(Double.parseDouble(tradeData.item(j*6+7+2).getTextContent().trim().replace(",", "")));
					data.setSaleTax(Double.parseDouble(tradeData.item(j*6+7+5).getTextContent().trim().replace(",", "")));;
					data.setPrice(Double.parseDouble(tradeData.item(j*6+7+1).getTextContent().trim().replace(",", "")));
					data.setQuantity(Long.parseLong(tradeData.item(j*6+7).getTextContent().trim().replace(",", "")));
					data.setStockSymbol(securityData.item(5).getTextContent().trim());
					data.setTransactionDate(df.parse(securityData.item(3).getTextContent().trim()));
					data.setNetAmount(data.getGrossAmount()-data.getCharges()-data.getSaleTax());
					transactions.add(data);

				}

			}

		} catch (Exception e) {
			LOGGER.error("Error transforming into TransactionData", e);
		}
		return transactions;
	}
}
