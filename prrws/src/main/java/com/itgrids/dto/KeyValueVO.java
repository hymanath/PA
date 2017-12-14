package com.itgrids.dto;

public class KeyValueVO {
	private Long key;
	private String value;
	private String electionType;
	
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
	
	
	
}
