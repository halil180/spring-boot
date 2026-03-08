package org.bookstore.customer.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class Address {

	@Column(name = "ADDRESS_STREET", nullable = false)
	private String street;

	@Column(name = "ADDRESS_CITY", nullable = false)
	private String city;

	@Column(name = "ADDRESS_STATE_PROVINCE")
	private String stateProvince;

	@Column(name = "ADDRESS_POSTAL_CODE", nullable = false)
	private String postalCode;

	@Column(name = "ADDRESS_COUNTRY", nullable = false)
	private String country;

	public Address() {
	}

	public Address(String street, String city, String stateProvince, String postalCode, String country) {
		this.street = street;
		this.stateProvince = stateProvince;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
