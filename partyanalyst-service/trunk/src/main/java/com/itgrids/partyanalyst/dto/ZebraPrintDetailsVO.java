package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ZebraPrintDetailsVO implements java.io.Serializable{

	private Long Id;
	private String district;
	private String parliament;
	private String name;
	
	private String dataPushDate;
	private Long totalPushCount;
	
	private String updatedDate;
	private String printStatus;
	private Long printStatusCount;
	
	private String errorStatus;
	private Long errorStatusCount;
	private Long remainingCount = 0l;
	
	private Long rowCount;
	private Double erroPerc;
	private Double printPerc;
	private Double pendingPerc;
	private Double sentPerc;
	private Double registeredPerc;
	private String relativeName;
	private String mobileNo;
	private String membershipNo;
	
	List<ZebraPrintDetailsVO> zebraPrintDetailsVOList = new ArrayList<ZebraPrintDetailsVO>(0);
	List<ZebraPrintDetailsVO> dataPushDetailsList = new ArrayList<ZebraPrintDetailsVO>(0);
	List<String> datesList = new ArrayList<String>(); 
	List<String> dataPushDatesList = new ArrayList<String>(); 
	
	public Double getSentPerc() {
		return sentPerc;
	}
	public void setSentPerc(Double sentPerc) {
		this.sentPerc = sentPerc;
	}
	public Double getRegisteredPerc() {
		return registeredPerc;
	}
	public void setRegisteredPerc(Double registeredPerc) {
		this.registeredPerc = registeredPerc;
	}
	public Long getRemainingCount() {
		return remainingCount;
	}
	public void setRemainingCount(Long remainingCount) {
		this.remainingCount = remainingCount;
	}
	public Double getErroPerc() {
		return erroPerc;
	}
	public void setErroPerc(Double erroPerc) {
		this.erroPerc = erroPerc;
	}
	public Double getPrintPerc() {
		return printPerc;
	}
	public void setPrintPerc(Double printPerc) {
		this.printPerc = printPerc;
	}
	public Double getPendingPerc() {
		return pendingPerc;
	}
	public void setPendingPerc(Double pendingPerc) {
		this.pendingPerc = pendingPerc;
	}
	public List<String> getDataPushDatesList() {
		return dataPushDatesList;
	}
	public void setDataPushDatesList(List<String> dataPushDatesList) {
		this.dataPushDatesList = dataPushDatesList;
	}
	public List<ZebraPrintDetailsVO> getDataPushDetailsList() {
		return dataPushDetailsList;
	}
	public void setDataPushDetailsList(List<ZebraPrintDetailsVO> dataPushDetailsList) {
		this.dataPushDetailsList = dataPushDetailsList;
	}
	public List<String> getDatesList() {
		return datesList;
	}
	public void setDatesList(List<String> datesList) {
		this.datesList = datesList;
	}
	public Long getRowCount() {
		return rowCount;
	}
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}
	public List<ZebraPrintDetailsVO> getZebraPrintDetailsVOList() {
		return zebraPrintDetailsVOList;
	}
	public void setZebraPrintDetailsVOList(
			List<ZebraPrintDetailsVO> zebraPrintDetailsVOList) {
		this.zebraPrintDetailsVOList = zebraPrintDetailsVOList;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataPushDate() {
		return dataPushDate;
	}
	public void setDataPushDate(String dataPushDate) {
		this.dataPushDate = dataPushDate;
	}
	public Long getTotalPushCount() {
		return totalPushCount;
	}
	public void setTotalPushCount(Long totalPushCount) {
		this.totalPushCount = totalPushCount;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public Long getPrintStatusCount() {
		return printStatusCount;
	}
	public void setPrintStatusCount(Long printStatusCount) {
		this.printStatusCount = printStatusCount;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public Long getErrorStatusCount() {
		return errorStatusCount;
	}
	public void setErrorStatusCount(Long errorStatusCount) {
		this.errorStatusCount = errorStatusCount;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	
}
