package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationFundDetailsVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private Long id;
	private String name;
	private String type;
	private Double totalAmt;
	private Double perc;
	private Long fundedLoc;
	private Long notFundedLoc;
	private Double fundedPerc;
	private Double nonFundedPerc;
	private Long totSchemes;
	
	private List<IdNameVO> subList = new ArrayList<IdNameVO>();
	
	
	
	
	public Long getTotSchemes() {
		return totSchemes;
	}
	public void setTotSchemes(Long totSchemes) {
		this.totSchemes = totSchemes;
	}
	public Long getFundedLoc() {
		return fundedLoc;
	}
	public void setFundedLoc(Long fundedLoc) {
		this.fundedLoc = fundedLoc;
	}
	public Long getNotFundedLoc() {
		return notFundedLoc;
	}
	public void setNotFundedLoc(Long notFundedLoc) {
		this.notFundedLoc = notFundedLoc;
	}
	public Double getFundedPerc() {
		return fundedPerc;
	}
	public void setFundedPerc(Double fundedPerc) {
		this.fundedPerc = fundedPerc;
	}
	public Double getNonFundedPerc() {
		return nonFundedPerc;
	}
	public void setNonFundedPerc(Double nonFundedPerc) {
		this.nonFundedPerc = nonFundedPerc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public List<IdNameVO> getSubList() {
		return subList;
	}
	public void setSubList(List<IdNameVO> subList) {
		this.subList = subList;
	}
	
	
	
}
