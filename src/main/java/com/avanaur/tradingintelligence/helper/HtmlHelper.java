package com.avanaur.tradingintelligence.helper;

import javax.xml.parsers.ParserConfigurationException;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

public class HtmlHelper {

	public static Document toDom(String html) {
		TagNode tagNode = new HtmlCleaner().clean(html);
		Document dom = null;
		try {
			dom = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
		} catch (ParserConfigurationException e) {
		}
		return dom; 
	}
}
