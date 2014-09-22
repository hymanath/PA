package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DebatePartyWiseCountVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String partyName;
	private Long totalDebates;
	private Long charId;
	private String charsName;
	private Double debateScale;
	
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getTotalDebates() {
		return totalDebates;
	}
	public void setTotalDebates(Long totalDebates) {
		this.totalDebates = totalDebates;
	}
	public Double getDebateScale() {
		return debateScale;
	}
	public void setDebateScale(Double debateScale) {
		this.debateScale = debateScale;
	}
	public Long getCharId() {
		return charId;
	}
	public void setCharId(Long charId) {
		this.charId = charId;
	}
	public String getCharsName() {
		return charsName;
	}
	public void setCharsName(String charsName) {
		this.charsName = charsName;
	}
	
	
	
	
}
