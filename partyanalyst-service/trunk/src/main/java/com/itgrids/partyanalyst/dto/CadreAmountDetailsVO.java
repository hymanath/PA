package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CadreAmountDetailsVO implements Serializable{
	private Long userId;
	private String userName;
	private String name;
	private String mobileNo;
	private String constituency;
	private Long constituencyId;
	private Long totalCount = 0l;
	private Long totalAmount = 0l;
	private Long paidAmount = 0l;
	private Long difference = 0l;
	private String date;
	private List<CadreAmountDetailsVO> infoList;
	
	private Long totalRecords;
	private Long ttAmount;
	private Long totalPaidAmount;
	private Long totalDifference;
	
	
	
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getTtAmount() {
		return ttAmount;
	}
	public void setTtAmount(Long ttAmount) {
		this.ttAmount = ttAmount;
	}
	public Long getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(Long totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public Long getTotalDifference() {
		return totalDifference;
	}
	public void setTotalDifference(Long totalDifference) {
		this.totalDifference = totalDifference;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Long getDifference() {
		return difference;
	}
	public void setDifference(Long difference) {
		this.difference = difference;
	}
	public List<CadreAmountDetailsVO> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<CadreAmountDetailsVO> infoList) {
		this.infoList = infoList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
}
