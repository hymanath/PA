package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GrivenceStatusVO implements Cloneable {
	
	
	private String grivenceType;
	private String name;
	private Long count = 0l;
	private List<GrivenceStatusVO> subList=new ArrayList<GrivenceStatusVO>(0);
	private Long id;
	private String locationIdStr;
	private Long partyCount = 0l;
	private Long govtCount = 0l;
	private Long welfareCount = 0l;
	private Long deathCount = 0l;
	private Long hosptalCount = 0l;
	private Long feeConsCount = 0l;
	private Long seatCount = 0l;
	private Long memberCount = 0l;
	private Long expectedAmount=0l;
	private Long approvedAmount=0l;
	private Long completedCount=0l;
	private Long totalAmount=0l;
	private String perc;
	private List<GrivenceStatusVO> subList1;
	private List<GrivenceStatusVO> subList2;
	private List<Long> statusList = new ArrayList<Long>(0);
	private List<Long> daysList = new ArrayList<Long>(0);
	
	
	public List<Long> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Long> statusList) {
		this.statusList = statusList;
	}
	public List<Long> getDaysList() {
		return daysList;
	}
	public void setDaysList(List<Long> daysList) {
		this.daysList = daysList;
	}
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
	public List<GrivenceStatusVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<GrivenceStatusVO> subList1) {
		this.subList1 = subList1;
	}
	public List<GrivenceStatusVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<GrivenceStatusVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Long memberCount) {
		this.memberCount = memberCount;
	}
	public Long getExpectedAmount() {
		return expectedAmount;
	}
	public void setExpectedAmount(Long expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public Long getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(Long approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
