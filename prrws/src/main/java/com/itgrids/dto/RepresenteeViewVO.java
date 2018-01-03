package com.itgrids.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepresenteeViewVO {

	private Long id;
	private String name;
	private Long endorsementNO;
	private Long petitionId;
	private String endorsmentDate;
	private String referrerName;
	private String desigName;
	private String workName;
	private Long noOfWorks = 0l;
	private String estimationCost;
	private String statusType="";
	private List<String> desigList = new ArrayList<String>();
	private String raisedDate;
	private Long totalRepresents=0l;
	private List<RepresenteeViewVO> referrerList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> subList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> deptList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> statusList = new ArrayList<RepresenteeViewVO>();
	private List<RepresenteeViewVO> list = new ArrayList<RepresenteeViewVO>();
	private Long statusId;
	private Set<Long> petitionIds = new HashSet<Long>();
	private Set<Long> subWorkIds = new HashSet<Long>();
	
	
	public Set<Long> getPetitionIds() {
		return petitionIds;
	}
	public void setPetitionIds(Set<Long> petitionIds) {
		this.petitionIds = petitionIds;
	}
	public Set<Long> getSubWorkIds() {
		return subWorkIds;
	}
	public void setSubWorkIds(Set<Long> subWorkIds) {
		this.subWorkIds = subWorkIds;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public List<RepresenteeViewVO> getList() {
		return list;
	}
	public void setList(List<RepresenteeViewVO> list) {
		this.list = list;
	}
	public List<RepresenteeViewVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<RepresenteeViewVO> statusList) {
		this.statusList = statusList;
	}
	public List<RepresenteeViewVO> getReferrerList() {
		return referrerList;
	}
	public void setReferrerList(List<RepresenteeViewVO> referrerList) {
		this.referrerList = referrerList;
	}
	public List<RepresenteeViewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<RepresenteeViewVO> subList) {
		this.subList = subList;
	}
	public List<RepresenteeViewVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<RepresenteeViewVO> deptList) {
		this.deptList = deptList;
	}
	public Long getTotalRepresents() {
		return totalRepresents;
	}
	public void setTotalRepresents(Long totalRepresents) {
		this.totalRepresents = totalRepresents;
	}
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public List<String> getDesigList() {
		return desigList;
	}
	public void setDesigList(List<String> desigList) {
		this.desigList = desigList;
	}
	
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
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
	public Long getEndorsementNO() {
		return endorsementNO;
	}
	public void setEndorsementNO(Long endorsementNO) {
		this.endorsementNO = endorsementNO;
	}
	public String getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	public String getReferrerName() {
		return referrerName;
	}
	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}
	public String getDesigName() {
		return desigName;
	}
	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	public String getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(String estimationCost) {
		this.estimationCost = estimationCost;
	}
	
	
}
