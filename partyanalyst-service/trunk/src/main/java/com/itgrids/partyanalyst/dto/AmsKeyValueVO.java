package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AmsKeyValueVO implements Serializable{

	private Long id;
	private String name;
	private List<AmsKeyValueVO> idnameList = new ArrayList<AmsKeyValueVO>();
	
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
	public List<AmsKeyValueVO> getIdnameList() {
		return idnameList;
	}
	public void setIdnameList(List<AmsKeyValueVO> idnameList) {
		this.idnameList = idnameList;
	}
	
	
}
