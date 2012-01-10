package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CustomPageVO implements Serializable{

	private static final long serialVersionUID = 8426212521866094475L;
	
	private String name;
	private String type;
	
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
