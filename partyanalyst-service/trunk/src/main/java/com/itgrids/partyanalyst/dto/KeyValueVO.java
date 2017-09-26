package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KeyValueVO implements Serializable {

	private Long id;
	private String name;
	private Long count = 0l;
	private List<KeyValueVO> list = new ArrayList<KeyValueVO>(0);
	private Long totalCount = 0l;
	private List<KeyValueVO> subList = new ArrayList<KeyValueVO>(0);
	private Long scopeValue = 0l;
	private String partyMetingName;
	private String date;
	private List<String> imageList = new ArrayList<String>(0);
	private String path;
	private Long percent;
	private String locationName;
	private String districtName;
	
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
	public List<KeyValueVO> getList() {
		return list;
	}
	public void setList(List<KeyValueVO> list) {
		this.list = list;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<KeyValueVO> getSubList() {
		return subList;
	}
	public void setSubList(List<KeyValueVO> subList) {
		this.subList = subList;
	}
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	public String getPartyMetingName() {
		return partyMetingName;
	}
	public void setPartyMetingName(String partyMetingName) {
		this.partyMetingName = partyMetingName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getPercent() {
		return percent;
	}
	public void setPercent(Long percent) {
		this.percent = percent;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
}
