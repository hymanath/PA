package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class KeyValueVO {
	private Long key;
	private String value;
	private String electionType;
	private List<Long> deptIdsList = new ArrayList<Long>();
	private String designation;
	private Long designationId;
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public KeyValueVO(){}
	public KeyValueVO(Long key,String value){
		this.key = key;
		this.value = value;
	}
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	
}
