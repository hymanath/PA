/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class ConstituencyManagementOverviewVO extends UserDetailsInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConstituencyManagementDataVO influencingPeopleDetails;
	private ConstituencyManagementDataVO localUserGroupDetails;
	private ConstituencyManagementDataVO localPoliticalChangesDetails;
	
	public ConstituencyManagementDataVO getInfluencingPeopleDetails() {
		return influencingPeopleDetails;
	}
	public void setInfluencingPeopleDetails(
			ConstituencyManagementDataVO influencingPeopleDetails) {
		this.influencingPeopleDetails = influencingPeopleDetails;
	}
	public ConstituencyManagementDataVO getLocalUserGroupDetails() {
		return localUserGroupDetails;
	}
	public void setLocalUserGroupDetails(
			ConstituencyManagementDataVO localUserGroupDetails) {
		this.localUserGroupDetails = localUserGroupDetails;
	}
	public ConstituencyManagementDataVO getLocalPoliticalChangesDetails() {
		return localPoliticalChangesDetails;
	}
	public void setLocalPoliticalChangesDetails(
			ConstituencyManagementDataVO localPoliticalChangesDetails) {
		this.localPoliticalChangesDetails = localPoliticalChangesDetails;
	}

}
