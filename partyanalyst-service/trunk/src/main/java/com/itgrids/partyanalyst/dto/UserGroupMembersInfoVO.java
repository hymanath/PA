/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class UserGroupMembersInfoVO extends UserGroupMembersVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long staticUserDesignationId;
	private String designationType;
	private String description;
	private Long groupCategoryId;
	private String groupCategoryType;
	private String groupScope;
	private Long groupScopeRegionId;
	private String groupScopeRegion;
	
	public Long getStaticUserDesignationId() {
		return staticUserDesignationId;
	}
	public void setStaticUserDesignationId(Long staticUserDesignationId) {
		this.staticUserDesignationId = staticUserDesignationId;
	}
	public String getDesignationType() {
		return designationType;
	}
	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getGroupCategoryId() {
		return groupCategoryId;
	}
	public void setGroupCategoryId(Long groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}
	public String getGroupCategoryType() {
		return groupCategoryType;
	}
	public void setGroupCategoryType(String groupCategoryType) {
		this.groupCategoryType = groupCategoryType;
	}
	public String getGroupScope() {
		return groupScope;
	}
	public void setGroupScope(String groupScope) {
		this.groupScope = groupScope;
	}
	public String getGroupScopeRegion() {
		return groupScopeRegion;
	}
	public void setGroupScopeRegion(String groupScopeRegion) {
		this.groupScopeRegion = groupScopeRegion;
	}
	public Long getGroupScopeRegionId() {
		return groupScopeRegionId;
	}
	public void setGroupScopeRegionId(Long groupScopeRegionId) {
		this.groupScopeRegionId = groupScopeRegionId;
	}
	

}
