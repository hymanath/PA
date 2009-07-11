package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.itgrids.partyanalyst.BaseObject;

@Entity
@Table(name="country")
public class Country extends BaseObject {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="country_id",nullable=false,insertable=false,updatable=false)
	private Long countryId;

	@Column(name="country_name",nullable=false,insertable=false,updatable=false)
	private String countryName;
	
	@Column(name="capital",nullable=false,insertable=false,updatable=false)
	private String capital;
	
	@Column(name="iso_code",nullable=false,insertable=false,updatable=false)
	private String isoCode;

	public Country() {}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
	
}
