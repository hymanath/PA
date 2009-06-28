package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.itgrids.partyanalyst.BaseObject;

@Entity
@Table(name="party")
public class Party extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="party_id",nullable=false,insertable=false,updatable=false)
	private Long partyId;

	@Column(name="long_name",nullable=false,insertable=false,updatable=false)
	private String longName;
	
	@Column(name="short_name",nullable=false,insertable=false,updatable=false)
	private String shortName;
	
	@Column(name="symbol",nullable=false,insertable=false,updatable=false)
	private String symbol;
	
	@Column(name="address",nullable=false,insertable=false,updatable=false)
	private String address;
	
	@Column(name="comments",nullable=false,insertable=false,updatable=false)
	private String comments;
	
	@Column(name="party_recognization",nullable=false,insertable=false,updatable=false)
	private String recognition;

	public Party() {}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	
}
