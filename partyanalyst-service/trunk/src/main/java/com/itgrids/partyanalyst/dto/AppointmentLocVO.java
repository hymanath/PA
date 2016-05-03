package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AppointmentLocVO implements Serializable{
	
	private Long   id;
	private String name;
	
	private Long   count=0l;
	private Long   uniqueCount=0l;
	
	private List<AppointmentLocVO> typeList;
	
	private List<AppointmentLocVO> subList;
	private Map<Long,AppointmentLocVO> subMap;
	
	private Long totalCount=0l;
	private Long totalUniqueCount=0l;
	
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
	public Long getUniqueCount() {
		return uniqueCount;
	}
	public void setUniqueCount(Long uniqueCount) {
		this.uniqueCount = uniqueCount;
	}
	public List<AppointmentLocVO> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<AppointmentLocVO> typeList) {
		this.typeList = typeList;
	}
	public List<AppointmentLocVO> getSubList() {
		return subList;
	}
	public void setSubList(List<AppointmentLocVO> subList) {
		this.subList = subList;
	}
	public Map<Long, AppointmentLocVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, AppointmentLocVO> subMap) {
		this.subMap = subMap;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalUniqueCount() {
		return totalUniqueCount;
	}
	public void setTotalUniqueCount(Long totalUniqueCount) {
		this.totalUniqueCount = totalUniqueCount;
	}
	
	
}
