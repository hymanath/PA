package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.dto.enums.DelimitationConstituencyType;

public class DelimitationConstituencyMandalResultVO implements Serializable {

	private DelimitationConstituencyType constituencyType;
	private List<MandalInfoVO> presentMandals;
	private List<MandalInfoVO> mandalsBeforeDelimitationConstituency;
	private Long delimitationYear;
	private Long beforeDelimitationYear;
	
	public DelimitationConstituencyType getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(DelimitationConstituencyType constituencyType) {
		this.constituencyType = constituencyType;
	}
	public List<MandalInfoVO> getPresentMandals() {
		return presentMandals;
	}
	public void setPresentMandals(List<MandalInfoVO> mandals) {
		this.presentMandals = mandals;
	}
	public List<MandalInfoVO> getMandalsBeforeDelimitationConstituency() {
		return mandalsBeforeDelimitationConstituency;
	}
	public void setMandalsBeforeDelimitationConstituency(
			List<MandalInfoVO> mandalsBeforeDelimitationConstituency) {
		this.mandalsBeforeDelimitationConstituency = mandalsBeforeDelimitationConstituency;
	}
	public Long getDelimitationYear() {
		return delimitationYear;
	}
	public void setDelimitationYear(Long delimitationYear) {
		this.delimitationYear = delimitationYear;
	}
	public Long getBeforeDelimitationYear() {
		return beforeDelimitationYear;
	}
	public void setBeforeDelimitationYear(Long beforeDelimitationYear) {
		this.beforeDelimitationYear = beforeDelimitationYear;
	}	
}
