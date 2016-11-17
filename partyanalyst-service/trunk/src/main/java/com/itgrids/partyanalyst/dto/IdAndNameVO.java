package com.itgrids.partyanalyst.dto;

import java.util.List;

public class IdAndNameVO {
	private Long id;
	private String name;
	private Long inviteeCount = 0l;
	private Long attenteeCount = 0l;
	private Long inviteeAttendeeCnt = 0l;
	private String imagePathStr;
	private String mobileNumber;
	private List<IdAndNameVO> issueTypes ;
	private String actualMobNumber;
	private List<IdAndNameVO> distList ;
	private Long apTotal = 0l;
	private Long tsTotal = 0l;
	private Long apNow = 0l;
	private Long tsNow = 0l;
	private String startTime;
	private String endTime;
	private Double per2016 = 0.00;
	
	
	public IdAndNameVO(){}
	public IdAndNameVO(Long id,String name){
		this.id=id;
		this.name =name;
	}
	public List<IdAndNameVO> getDistList() {
		return distList;
	}
	public void setDistList(List<IdAndNameVO> distList) {
		this.distList = distList;
	}
	public List<IdAndNameVO> getIssueTypes() {
		return issueTypes;
	}
	public void setIssueTypes(List<IdAndNameVO> issueTypes) {
		this.issueTypes = issueTypes;
	}
	
	
	public String getImagePathStr() {
		return imagePathStr;
	}
	public void setImagePathStr(String imagePathStr) {
		this.imagePathStr = imagePathStr;
	}
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getAttenteeCount() {
		return attenteeCount;
	}
	public void setAttenteeCount(Long attenteeCount) {
		this.attenteeCount = attenteeCount;
	}
	public Long getInviteeAttendeeCnt() {
		return inviteeAttendeeCnt;
	}
	public void setInviteeAttendeeCnt(Long inviteeAttendeeCnt) {
		this.inviteeAttendeeCnt = inviteeAttendeeCnt;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getActualMobNumber() {
		return actualMobNumber;
	}
	public void setActualMobNumber(String actualMobNumber) {
		this.actualMobNumber = actualMobNumber;
	}
	public Long getApTotal() {
		return apTotal;
	}
	public void setApTotal(Long apTotal) {
		this.apTotal = apTotal;
	}
	public Long getTsTotal() {
		return tsTotal;
	}
	public void setTsTotal(Long tsTotal) {
		this.tsTotal = tsTotal;
	}
	public Long getApNow() {
		return apNow;
	}
	public void setApNow(Long apNow) {
		this.apNow = apNow;
	}
	public Long getTsNow() {
		return tsNow;
	}
	public void setTsNow(Long tsNow) {
		this.tsNow = tsNow;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Double getPer2016() {
		return per2016;
	}
	public void setPer2016(Double per2016) {
		this.per2016 = per2016;
	}
}
