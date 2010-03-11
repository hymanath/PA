/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 08,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserGroupBasicDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6231050197456486604L;
	
	private GroupsBasicInfoVO groupBasicDetails;
	private List<GroupsBasicInfoVO> subGroupDetails;
	private ResultStatus resultStatus;
	
    //getters and setters
	public GroupsBasicInfoVO getGroupBasicDetails() {
		return groupBasicDetails;
	}

	public void setGroupBasicDetails(GroupsBasicInfoVO groupBasicDetails) {
		this.groupBasicDetails = groupBasicDetails;
	}

	public List<GroupsBasicInfoVO> getSubGroupDetails() {
		return subGroupDetails;
	}

	public void setSubGroupDetails(List<GroupsBasicInfoVO> subGroupDetails) {
		this.subGroupDetails = subGroupDetails;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

}
