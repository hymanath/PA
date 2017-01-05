package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SmallVO implements Serializable{
    
	/*
	 * SREEDHAR : DONT ADD ANY EXTRA FIELDS HERE.
	 */
	
	private Long id;
	private String name;
	
	public SmallVO(){}
	
	public SmallVO(Long id , String name)
	{
		this.id = id;
		this.name = name;
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
	
	
}
