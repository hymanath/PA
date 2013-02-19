package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SelectOptionVO implements Serializable, Comparable<SelectOptionVO> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Long id;
	private String url;
	private String type;
	private String value;
	private String location;
	private String villageCovered;
	private List<SelectOptionVO> selectOptionsList;
	private List<SelectOptionVO> selectOptionsList1;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SelectOptionVO(){}
	
	public SelectOptionVO(Long id, String name)
	{
		this.id = id;
		this.name = name;	
	}
	public SelectOptionVO(Long id, String name,String url){
		this.id = id;
		this.name = name;
		this.url = url;
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

	public List<SelectOptionVO> getSelectOptionsList1() {
		return selectOptionsList1;
	}

	public void setSelectOptionsList1(List<SelectOptionVO> selectOptionsList1) {
		this.selectOptionsList1 = selectOptionsList1;
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

	public int compareTo(SelectOptionVO obj) {
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}

	public List<SelectOptionVO> getSelectOptionsList() {
		return selectOptionsList;
	}

	public void setSelectOptionsList(List<SelectOptionVO> selectOptionsList) {
		this.selectOptionsList = selectOptionsList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVillageCovered() {
		return villageCovered;
	}

	public void setVillageCovered(String villageCovered) {
		this.villageCovered = villageCovered;
	}

	
}
