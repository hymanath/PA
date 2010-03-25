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
import java.util.Set;

public class UserGroupBasicDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6231050197456486604L;
	
	private GroupsBasicInfoVO groupBasicDetails;
	private List<GroupsBasicInfoVO> subGroupDetails;
	private ResultStatus resultStatus;
	private List<String> membersMobileNos;
	private Set<SelectOptionVO> systemGroupsBCList;
	private Set<SelectOptionVO> myGroupsBCList;

	
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

	public List<String> getMembersMobileNos() {
		return membersMobileNos;
	}

	public void setMembersMobileNos(List<String> membersMobileNos) {
		this.membersMobileNos = membersMobileNos;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Set<SelectOptionVO> getSystemGroupsBCList() {
		return systemGroupsBCList;
	}

	public void setSystemGroupsBCList(Set<SelectOptionVO> systemGroupsBCList) {
		this.systemGroupsBCList = systemGroupsBCList;
	}

	public Set<SelectOptionVO> getMyGroupsBCList() {
		return myGroupsBCList;
	}

	public void setMyGroupsBCList(Set<SelectOptionVO> myGroupsBCList) {
		this.myGroupsBCList = myGroupsBCList;
	}
	

}
