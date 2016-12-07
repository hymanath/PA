package com.itgrids.cardprint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country", catalog = "party_analyst")
public class Country implements java.io.Serializable {
	
	private static final long serialVersionUID = -1001384285493215728L;
	
	private Long countryId;
	private String countryName;
	private String capital;
	private String isoCode;

	// Constructors
	public Country() {
	}
	public Country(Long countryId, String countryName) {
		this.countryId = countryId;
		this.countryName = countryName;
	}
	
	// Property Accessors
	
	@Id
	@Column(name = "country_id", unique = true, nullable = false)
	public Long getCountryId() {
		return this.countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	
	@Column(name = "country_name", nullable = false, length = 100)
	public String getCountryName() {
		return this.countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	@Column(name = "capital", length = 100)
	public String getCapital() {
		return this.capital;
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	@Column(name = "iso_code", length = 25)
	public String getIsoCode() {
		return this.isoCode;
	}
	
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
}
