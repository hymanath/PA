package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleVO implements java.io.Serializable{
	
   private Long id;
   private String name;
   
   private String dateString;
   private Date date;
   
   private Long count;
   private Long total;
   private Long totalCount;
   private String isAttended = "-";
   
   private List<SimpleVO> simpleVOList1;
   private List<SimpleVO> simpleVOList2;
   
   private Map<Long,SimpleVO> map;
   
   private String progName="";
   private String campName="";
   private String batchName="";
   private Long batchId=0l;
   private Long locValue;
   private String remarks;
   
   private boolean nonInvitee=false;
   
   private Long totalInviteeCount=0l;
   private Long totalAttendedCount = 0l;
   private Long totalAbsentCount =0l;
   private List<Long> cadreIds;
   private SimpleVO simpleVO1;
   
  
   private Long day1Count;
   private Long day2Count;
   private Long day3Count;
   
   private String centerName;
	
   
   
	public String getCenterName() {
	return centerName;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}
	public Long getDay1Count() {
	return day1Count;
}
public void setDay1Count(Long day1Count) {
	this.day1Count = day1Count;
}
public Long getDay2Count() {
	return day2Count;
}
public void setDay2Count(Long day2Count) {
	this.day2Count = day2Count;
}
public Long getDay3Count() {
	return day3Count;
}
public void setDay3Count(Long day3Count) {
	this.day3Count = day3Count;
}
	public SimpleVO getSimpleVO1() {
	return simpleVO1;
}
public void setSimpleVO1(SimpleVO simpleVO1) {
	this.simpleVO1 = simpleVO1;
}
	public List<Long> getCadreIds() {
	return cadreIds;
}
public void setCadreIds(List<Long> cadreIds) {
	this.cadreIds = cadreIds;
}
	public Long getBatchId() {
	return batchId;
}
public void setBatchId(Long batchId) {
	this.batchId = batchId;
}
	public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
	public Long getTotalInviteeCount() {
	return totalInviteeCount;
}
public void setTotalInviteeCount(Long totalInviteeCount) {
	this.totalInviteeCount = totalInviteeCount;
}
public Long getTotalAttendedCount() {
	return totalAttendedCount;
}
public void setTotalAttendedCount(Long totalAttendedCount) {
	this.totalAttendedCount = totalAttendedCount;
}
public Long getTotalAbsentCount() {
	return totalAbsentCount;
}
public void setTotalAbsentCount(Long totalAbsentCount) {
	this.totalAbsentCount = totalAbsentCount;
}
	public boolean isNonInvitee() {
	return nonInvitee;
}
public void setNonInvitee(boolean nonInvitee) {
	this.nonInvitee = nonInvitee;
}
	public String getIsAttended() {
	return isAttended;
	}
	public void setIsAttended(String isAttended) {
	this.isAttended = isAttended;
}

	public String getProgName() {
	return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
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
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<SimpleVO> getSimpleVOList1() {
		return simpleVOList1;
	}
	public void setSimpleVOList1(List<SimpleVO> simpleVOList1) {
		this.simpleVOList1 = simpleVOList1;
	}
	public List<SimpleVO> getSimpleVOList2() {
		return simpleVOList2;
	}
	public void setSimpleVOList2(List<SimpleVO> simpleVOList2) {
		this.simpleVOList2 = simpleVOList2;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Map<Long, SimpleVO> getMap() {
		return map;
	}
	public void setMap(Map<Long, SimpleVO> map) {
		this.map = map;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getLocValue() {
		return locValue;
	}
	public void setLocValue(Long locValue) {
		this.locValue = locValue;
	}
    
   }
