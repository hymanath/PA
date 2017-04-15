package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FilterSectionVO implements Serializable{

	private Long 	id;
	private String 	name;
	
	private List<FilterSectionVO> scopesList = new ArrayList<FilterSectionVO>(0);
	private List<FilterSectionVO> severityList = new ArrayList<FilterSectionVO>(0);
	private List<FilterSectionVO> categoryList = new ArrayList<FilterSectionVO>(0);
	private List<FilterSectionVO> editionsList = new ArrayList<FilterSectionVO>(0);
	private List<FilterSectionVO> tvNewsChannelList = new ArrayList<FilterSectionVO>(0);
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
	public List<FilterSectionVO> getScopesList() {
		return scopesList;
	}
	public void setScopesList(List<FilterSectionVO> scopesList) {
		this.scopesList = scopesList;
	}
	public List<FilterSectionVO> getSeverityList() {
		return severityList;
	}
	public void setSeverityList(List<FilterSectionVO> severityList) {
		this.severityList = severityList;
	}
	public List<FilterSectionVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<FilterSectionVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<FilterSectionVO> getEditionsList() {
		return editionsList;
	}
	public void setEditionsList(List<FilterSectionVO> editionsList) {
		this.editionsList = editionsList;
	}
	public List<FilterSectionVO> getTvNewsChannelList() {
		return tvNewsChannelList;
	}
	public void setTvNewsChannelList(List<FilterSectionVO> tvNewsChannelList) {
		this.tvNewsChannelList = tvNewsChannelList;
	}
	
	
	
	
}
