package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class KeyValueVO {
	private String name;
	private Long key;
	private String value;
	private Long count=0L;
	private Long totalCount=0L;
	private String electionType;
	private List<Long> deptIdsList = new ArrayList<Long>(0);
	private String designation;
	private Long designationId;
	private List<KeyValueVO> subList = new ArrayList<KeyValueVO>(0);
	private String deptName="";
	private List<Long> depDesigIds  = null; 
	  
	
	  public List<Long> getDepDesigIds() {
		return depDesigIds;
	}
	public void setDepDesigIds(List<Long> depDesigIds) {
		this.depDesigIds = depDesigIds;
	}
	public String getDeptName() {
	    return deptName;
	  }
	  public void setDeptName(String deptName) {
	    this.deptName = deptName;
	  }		
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
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
	public KeyValueVO(Long key,String value,Long count){
		this.key = key;
		this.value = value;
		this.count = count;
	}
	
	public List<KeyValueVO> getSubList() {
		return subList;
	}
	public void setSubList(List<KeyValueVO> subList) {
		this.subList = subList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
