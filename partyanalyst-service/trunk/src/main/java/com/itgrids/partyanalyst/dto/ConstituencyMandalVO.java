package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyMandalVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	
	private List<PartyInfoVO> partiesReslts;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	 
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public Long getTehsilId() {
		return tehsilId;
	}
	
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	public String getTehsilName() {
		return tehsilName;
	}
	
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public List<PartyInfoVO> getPartiesReslts() {
		return partiesReslts;
	}

	public void setPartiesReslts(List<PartyInfoVO> partiesReslts) {
		this.partiesReslts = partiesReslts;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ConstituencyMandalVO))
			return false;
		ConstituencyMandalVO voObj = (ConstituencyMandalVO) obj;
		return this.constituencyId.equals(voObj.getConstituencyId()) && this.tehsilId.equals(voObj.getTehsilId());
	}
	
	@Override
	public int hashCode(){
		return this.constituencyId.hashCode() + this.tehsilId.hashCode();
	}
	
	
}
