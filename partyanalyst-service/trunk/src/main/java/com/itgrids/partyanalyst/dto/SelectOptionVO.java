package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SelectOptionVO implements Serializable, Comparable<SelectOptionVO> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Long id;
	
	public SelectOptionVO(){}
	
	public SelectOptionVO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return this.id.equals(vo.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(id==null)
			id = -1L;
		return this.id.intValue();
	}

	@Override
	public int compareTo(SelectOptionVO obj) {
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}
	
}
