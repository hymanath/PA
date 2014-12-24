package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreIVRResponseVO {
   
	private List<CadreIVRResponseVO> apList;
	private List<CadreIVRResponseVO> tgList;
	
	private String name;
	private String locationName;
	private String areaName;
	private Long totalCalls;
	private Long totalCallsPerc;
	private Long received;
	private Long receivedPerc;
	private Long notReceived;
	private Long notReceivedPerc;
	private Long notMember;
	private Long notMemberPerc;
	
	
	public List<CadreIVRResponseVO> getApList() {
		return apList;
	}
	
	public void setApList(List<CadreIVRResponseVO> apList) {
		this.apList = apList;
	}
	
	public List<CadreIVRResponseVO> getTgList() {
		return tgList;
	}
	
	public void setTgList(List<CadreIVRResponseVO> tgList) {
		this.tgList = tgList;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public Long getTotalCalls() {
		return totalCalls;
	}
	
	public void setTotalCalls(Long totalCalls) {
		this.totalCalls = totalCalls;
	}
	
	public Long getTotalCallsPerc() {
		return totalCallsPerc;
	}
	
	public void setTotalCallsPerc(Long totalCallsPerc) {
		this.totalCallsPerc = totalCallsPerc;
	}
	
	public Long getReceived() {
		return received;
	}
	
	public void setReceived(Long received) {
		this.received = received;
	}
	
	public Long getReceivedPerc() {
		return receivedPerc;
	}
	
	public void setReceivedPerc(Long receivedPerc) {
		this.receivedPerc = receivedPerc;
	}
	
	public Long getNotReceived() {
		return notReceived;
	}
	
	public void setNotReceived(Long notReceived) {
		this.notReceived = notReceived;
	}
	
	public Long getNotReceivedPerc() {
		return notReceivedPerc;
	}
	
	public void setNotReceivedPerc(Long notReceivedPerc) {
		this.notReceivedPerc = notReceivedPerc;
	}
	
	public Long getNotMember() {
		return notMember;
	}
	
	public void setNotMember(Long notMember) {
		this.notMember = notMember;
	}
	
	public Long getNotMemberPerc() {
		return notMemberPerc;
	}
	
	public void setNotMemberPerc(Long notMemberPerc) {
		this.notMemberPerc = notMemberPerc;
	}
		
}
