package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyVO extends ResultStatus implements Serializable{

	
	private static final long serialVersionUID = 3566539517285923003L;
	private String partyLongName;
	private String partyShortName;
	private Long partyId;
	private String symbol;
	private String address;
	private String partyRecognization;
	private String partyLogo;
	private String partyFlag;
	public String getPartyLongName() {
		return partyLongName;
	}
	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
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
	public String getPartyRecognization() {
		return partyRecognization;
	}
	public void setPartyRecognization(String partyRecognization) {
		this.partyRecognization = partyRecognization;
	}
	public String getPartyLogo() {
		return partyLogo;
	}
	public void setPartyLogo(String partyLogo) {
		this.partyLogo = partyLogo;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	
	
	
}
