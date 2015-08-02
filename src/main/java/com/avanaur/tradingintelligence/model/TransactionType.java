package com.avanaur.tradingintelligence.model;

public enum TransactionType {

	BUY("BOUGHT"), SELL("SOLD");

	private TransactionType(String pastTense) {
		this.pastTense = pastTense;
	}

	private String pastTense;

	public String getPastTense() {
		return pastTense;
	}

	public static TransactionType getTransactionType(String pastTense) {
		for (TransactionType t : TransactionType.values()) {
			if (t.getPastTense().equals(pastTense))
				return t;
		}
		return null;

	}

}
