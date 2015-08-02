package com.avanaur.tradingintelligence.model;

import java.util.Date;

public class TransactionData {

	private String stockSymbol;
	private int transactionOrder;
	private TransactionType action;
	private Date transactionDate;
	private Long quantity;
	private double price;
	private double grossAmount;
	private double charges;
	private double saleTax;
	private double netAmount;

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public TransactionType getAction() {
		return action;
	}

	public void setAction(TransactionType action) {
		this.action = action;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public int getTransactionOrder() {
		return transactionOrder;
	}

	public void setTransactionOrder(int transactionOrder) {
		this.transactionOrder = transactionOrder;
	}

	public double getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(double saleTax) {
		this.saleTax = saleTax;
	}

}
