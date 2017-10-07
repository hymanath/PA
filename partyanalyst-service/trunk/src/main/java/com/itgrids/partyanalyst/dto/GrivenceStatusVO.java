package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GrivenceStatusVO {
	
	
	private String grivenceType;
	private String name;
	private Long count = 0l;
	private List<GrivenceStatusVO> subList=new ArrayList<GrivenceStatusVO>(0);
	private Long id;
	private Long partyCount = 0l;
	private Long govtCount = 0l;
	private Long welfareCount = 0l;
	private Long deathCount = 0l;
	private Long hosptalCount = 0l;
	private Long feeConsCount = 0l;
	private Long seatCount = 0l;
	private String perc;
	
	public String getGrivenceType() {
		return grivenceType;
	}
	public void setGrivenceType(String grivenceType) {
		this.grivenceType = grivenceType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<GrivenceStatusVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GrivenceStatusVO> subList) {
		this.subList = subList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getWelfareCount() {
		return welfareCount;
	}
	public void setWelfareCount(Long welfareCount) {
		this.welfareCount = welfareCount;
	}
	public Long getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(Long deathCount) {
		this.deathCount = deathCount;
	}
	public Long getHosptalCount() {
		return hosptalCount;
	}
	public void setHosptalCount(Long hosptalCount) {
		this.hosptalCount = hosptalCount;
	}
	public Long getFeeConsCount() {
		return feeConsCount;
	}
	public void setFeeConsCount(Long feeConsCount) {
		this.feeConsCount = feeConsCount;
	}
	public Long getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Long seatCount) {
		this.seatCount = seatCount;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	
	
	
}
