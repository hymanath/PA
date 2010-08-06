/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 04, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/*
 * @author Sai Krishna Basetti
 */
public class PartyBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Party Id */
	private Long partyId;
	/** Party Short Name*/
	private String partyShortName;
	/** Party Long Name*/
	private String partyLongName;
	/** Party Symbol*/
	private String partySymbol;
	/** Party Logo */
	private String partyLogo;
	/** Party Flag*/
	private String partyFlag;
	/** Party Recognition */
	private String partyRecognition;
	
	/** Getters And Setters */
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public String getPartyLongName() {
		return partyLongName;
	}
	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	public String getPartySymbol() {
		return partySymbol;
	}
	public void setPartySymbol(String partySymbol) {
		this.partySymbol = partySymbol;
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
	public String getPartyRecognition() {
		return partyRecognition;
	}
	public void setPartyRecognition(String partyRecognition) {
		this.partyRecognition = partyRecognition;
	}
	

}
