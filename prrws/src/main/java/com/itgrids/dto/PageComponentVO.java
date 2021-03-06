package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PageComponentVO {

	private Long id;
	private String name;
	private Long order;
	private String shortName;
	private String url;
	private List<PageComponentVO> subList = new ArrayList<PageComponentVO>(0);
	
	
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
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public List<PageComponentVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PageComponentVO> subList) {
		this.subList = subList;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
