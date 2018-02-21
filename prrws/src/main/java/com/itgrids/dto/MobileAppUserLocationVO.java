package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class MobileAppUserLocationVO {
	private Long locationScopeId;
	private String locationScope;
	private List<SmallVO> smallVOList = new ArrayList<SmallVO>(0);
	
	
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	public List<SmallVO> getSmallVOList() {
		return smallVOList;
	}
	public void setSmallVOList(List<SmallVO> smallVOList) {
		this.smallVOList = smallVOList;
	}
	
	
}
