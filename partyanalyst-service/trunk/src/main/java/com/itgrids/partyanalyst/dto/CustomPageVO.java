package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CustomPageVO implements Serializable{

	private static final long serialVersionUID = 8426212521866094475L;
	
	private String name;
	private String type;
	private Long typeId;
	private int error;
	
	
	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public CustomPageVO()
	{}
	
	public CustomPageVO(String name,String type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
