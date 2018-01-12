package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuVO implements java.io.Serializable {
	
	private Long id;
	private Long entitlementUrlId;
	private String name;
	private String url;
	private String type;
	private Long parentEntitlementGroupId;
	
	private List<MenuVO> subList = new ArrayList<MenuVO>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MenuVO> getSubList() {
		return subList;
	}

	public void setSubList(List<MenuVO> subList) {
		this.subList = subList;
	}

	public Long getParentEntitlementGroupId() {
		return parentEntitlementGroupId;
	}

	public void setParentEntitlementGroupId(Long parentEntitlementGroupId) {
		this.parentEntitlementGroupId = parentEntitlementGroupId;
	}

	public Long getEntitlementUrlId() {
		return entitlementUrlId;
	}

	public void setEntitlementUrlId(Long entitlementUrlId) {
		this.entitlementUrlId = entitlementUrlId;
	}
	

}
