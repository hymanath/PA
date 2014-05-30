package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GenericVO {
	
	private Long id;
    private String name;
    private Long count;
    private List<GenericVO> genericVOList = new ArrayList<GenericVO>();
    
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	
	
}
