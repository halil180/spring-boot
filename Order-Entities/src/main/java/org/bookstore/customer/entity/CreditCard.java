package org.bookstore.customer.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class CreditCard {

	@Column(name = "CREDIT_CARD_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private CreditCardType type;

	@Column(name = "CREDIT_CARD_NUMBER", nullable = false)
	private String number;

	@Column(name = "CREDIT_CARD_EXPIRATION_MONTH", nullable = false)
	private int expirationMonth;

	@Column(name = "CREDIT_CARD_EXPIRATION_YEAR", nullable = false)
	private int expirationYear;

	public CreditCard() {
	}

	public CreditCard(CreditCardType type, String number, int expirationMonth, int expirationYear) {
		this.type = type;
		this.number = number;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
	}

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
}
