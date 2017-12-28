package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class ItecEOfficeVO {

	private Long id;
	private String name;
	
	private Long departmentId;
	private String departmentName;
	private String employeeName;
	private String postName;
	private String designation;
	private String isPostDetailActive;
	private Long created = 0L;
	private Long zeroToSeven = 0L;
	private Long eightToFifteen = 0L;
	private Long sixteenToThirty = 0L;
	private Long thirtyoneToSixty = 0L;
	private Long aboveSixty = 0L;
	private Long totalCount = 0L;
	private String percentage;
	private List<ItecEOfficeVO> subList = new ArrayList<ItecEOfficeVO>(0);
	
	private List<ItecEOfficeVO> jsList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> directorList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> jdList = new ArrayList<ItecEOfficeVO>(0);
	
	private List<ItecEOfficeVO> specialOfficerList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> pmList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> soList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> aaoList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> asoList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> jaoList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> otherList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> ceoList = new ArrayList<ItecEOfficeVO>(0);
	private List<ItecEOfficeVO> ministerList = new ArrayList<ItecEOfficeVO>(0);
	private Long orderNumber = 0L;
	private String ownerName;
	private Long openBal = 0L;
	private Long recievedFiles = 0L;
	private Long forwaredFiles = 0L;
	private Long closedFiles = 0L;
	private Long parkedFiles = 0L;
	private Long receiptCreated = 0L;
	private Long actionFiles = 0L;
	private String ZeroToSevenPerc;
	private String eightToFifteenPerc;
	private String sixteenToThirtyPerc;
	private String thirtyoneToSixtyPerc;
	private String aboveSixtyPerc;
	private String lastUpdatedTime;
	private String orgName;
	
	
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIsPostDetailActive() {
		return isPostDetailActive;
	}
	public void setIsPostDetailActive(String isPostDetailActive) {
		this.isPostDetailActive = isPostDetailActive;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public Long getZeroToSeven() {
		return zeroToSeven;
	}
	public void setZeroToSeven(Long zeroToSeven) {
		this.zeroToSeven = zeroToSeven;
	}
	public Long getEightToFifteen() {
		return eightToFifteen;
	}
	public void setEightToFifteen(Long eightToFifteen) {
		this.eightToFifteen = eightToFifteen;
	}
	public Long getSixteenToThirty() {
		return sixteenToThirty;
	}
	public void setSixteenToThirty(Long sixteenToThirty) {
		this.sixteenToThirty = sixteenToThirty;
	}
	public Long getThirtyoneToSixty() {
		return thirtyoneToSixty;
	}
	public void setThirtyoneToSixty(Long thirtyoneToSixty) {
		this.thirtyoneToSixty = thirtyoneToSixty;
	}
	public Long getAboveSixty() {
		return aboveSixty;
	}
	public void setAboveSixty(Long aboveSixty) {
		this.aboveSixty = aboveSixty;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<ItecEOfficeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ItecEOfficeVO> subList) {
		this.subList = subList;
	}
	public List<ItecEOfficeVO> getJsList() {
		return jsList;
	}
	public void setJsList(List<ItecEOfficeVO> jsList) {
		this.jsList = jsList;
	}
	public List<ItecEOfficeVO> getDirectorList() {
		return directorList;
	}
	public void setDirectorList(List<ItecEOfficeVO> directorList) {
		this.directorList = directorList;
	}
	public List<ItecEOfficeVO> getJdList() {
		return jdList;
	}
	public void setJdList(List<ItecEOfficeVO> jdList) {
		this.jdList = jdList;
	}
	public List<ItecEOfficeVO> getSpecialOfficerList() {
		return specialOfficerList;
	}
	public void setSpecialOfficerList(List<ItecEOfficeVO> specialOfficerList) {
		this.specialOfficerList = specialOfficerList;
	}
	public List<ItecEOfficeVO> getPmList() {
		return pmList;
	}
	public void setPmList(List<ItecEOfficeVO> pmList) {
		this.pmList = pmList;
	}
	public List<ItecEOfficeVO> getSoList() {
		return soList;
	}
	public void setSoList(List<ItecEOfficeVO> soList) {
		this.soList = soList;
	}
	public List<ItecEOfficeVO> getAaoList() {
		return aaoList;
	}
	public void setAaoList(List<ItecEOfficeVO> aaoList) {
		this.aaoList = aaoList;
	}
	public List<ItecEOfficeVO> getAsoList() {
		return asoList;
	}
	public void setAsoList(List<ItecEOfficeVO> asoList) {
		this.asoList = asoList;
	}
	public List<ItecEOfficeVO> getJaoList() {
		return jaoList;
	}
	public void setJaoList(List<ItecEOfficeVO> jaoList) {
		this.jaoList = jaoList;
	}
	public List<ItecEOfficeVO> getOtherList() {
		return otherList;
	}
	public void setOtherList(List<ItecEOfficeVO> otherList) {
		this.otherList = otherList;
	}
	public List<ItecEOfficeVO> getCeoList() {
		return ceoList;
	}
	public void setCeoList(List<ItecEOfficeVO> ceoList) {
		this.ceoList = ceoList;
	}
	public List<ItecEOfficeVO> getMinisterList() {
		return ministerList;
	}
	public void setMinisterList(List<ItecEOfficeVO> ministerList) {
		this.ministerList = ministerList;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Long getOpenBal() {
		return openBal;
	}
	public void setOpenBal(Long openBal) {
		this.openBal = openBal;
	}
	public Long getRecievedFiles() {
		return recievedFiles;
	}
	public void setRecievedFiles(Long recievedFiles) {
		this.recievedFiles = recievedFiles;
	}
	public Long getForwaredFiles() {
		return forwaredFiles;
	}
	public void setForwaredFiles(Long forwaredFiles) {
		this.forwaredFiles = forwaredFiles;
	}
	public Long getClosedFiles() {
		return closedFiles;
	}
	public void setClosedFiles(Long closedFiles) {
		this.closedFiles = closedFiles;
	}
	public Long getParkedFiles() {
		return parkedFiles;
	}
	public void setParkedFiles(Long parkedFiles) {
		this.parkedFiles = parkedFiles;
	}
	public Long getReceiptCreated() {
		return receiptCreated;
	}
	public void setReceiptCreated(Long receiptCreated) {
		this.receiptCreated = receiptCreated;
	}
	public Long getActionFiles() {
		return actionFiles;
	}
	public void setActionFiles(Long actionFiles) {
		this.actionFiles = actionFiles;
	}
	public String getZeroToSevenPerc() {
		return ZeroToSevenPerc;
	}
	public void setZeroToSevenPerc(String zeroToSevenPerc) {
		ZeroToSevenPerc = zeroToSevenPerc;
	}
	public String getEightToFifteenPerc() {
		return eightToFifteenPerc;
	}
	public void setEightToFifteenPerc(String eightToFifteenPerc) {
		this.eightToFifteenPerc = eightToFifteenPerc;
	}
	public String getSixteenToThirtyPerc() {
		return sixteenToThirtyPerc;
	}
	public void setSixteenToThirtyPerc(String sixteenToThirtyPerc) {
		this.sixteenToThirtyPerc = sixteenToThirtyPerc;
	}
	public String getThirtyoneToSixtyPerc() {
		return thirtyoneToSixtyPerc;
	}
	public void setThirtyoneToSixtyPerc(String thirtyoneToSixtyPerc) {
		this.thirtyoneToSixtyPerc = thirtyoneToSixtyPerc;
	}
	public String getAboveSixtyPerc() {
		return aboveSixtyPerc;
	}
	public void setAboveSixtyPerc(String aboveSixtyPerc) {
		this.aboveSixtyPerc = aboveSixtyPerc;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
