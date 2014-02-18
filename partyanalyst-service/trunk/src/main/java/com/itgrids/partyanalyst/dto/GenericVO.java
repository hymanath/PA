package com.itgrids.partyanalyst.dto;

public class GenericVO {
	
	private Long id;
    private String name;
    
    public GenericVO()
    {
    	
    	
    }
    public GenericVO(Long id,String name)
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
