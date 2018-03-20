package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PetitionsInputVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String endorsmentNo;
	private String fromDate;
	private String endDate;
	private Long costOrNonCostWorkTypeId;
	private List<Long> deptIdsList = new ArrayList<Long>(0);
	private List<Long> statusIdsList = new ArrayList<Long>(0);
	private List<Long> subjectIdsList = new ArrayList<Long>(0);
	private List<Long> subSubjectIdsList = new ArrayList<Long>(0);
	private List<Long> constituencyIdsList = new ArrayList<Long>(0);
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
	public String getEndorsmentNo() {
		return endorsmentNo;
	}
	public void setEndorsmentNo(String endorsmentNo) {
		this.endorsmentNo = endorsmentNo;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
		if(fromDate == null || fromDate.isEmpty())
			fromDate = null;
		this.fromDate = fromDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
		if(endDate == null || endDate.isEmpty())
			endDate = null;
		this.endDate = endDate;
	}
	public Long getCostOrNonCostWorkTypeId() {
		return costOrNonCostWorkTypeId;
	}
	public void setCostOrNonCostWorkTypeId(Long costOrNonCostWorkTypeId) {
		this.costOrNonCostWorkTypeId = costOrNonCostWorkTypeId;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	public List<Long> getStatusIdsList() {
		return statusIdsList;
	}
	public void setStatusIdsList(List<Long> statusIdsList) {
		this.statusIdsList = statusIdsList;
	}
	public List<Long> getSubjectIdsList() {
		return subjectIdsList;
	}
	public void setSubjectIdsList(List<Long> subjectIdsList) {
		this.subjectIdsList = subjectIdsList;
	}
	public List<Long> getSubSubjectIdsList() {
		return subSubjectIdsList;
	}
	public void setSubSubjectIdsList(List<Long> subSubjectIdsList) {
		this.subSubjectIdsList = subSubjectIdsList;
	}
	public List<Long> getConstituencyIdsList() {
		return constituencyIdsList;
	}
	public void setConstituencyIdsList(List<Long> constituencyIdsList) {
		this.constituencyIdsList = constituencyIdsList;
	}
}
