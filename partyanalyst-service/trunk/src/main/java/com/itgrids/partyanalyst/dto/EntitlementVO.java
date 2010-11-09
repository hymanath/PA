package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class EntitlementVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1239965172089914152L;
	
	private ResultStatus resultStatus;
	private List<SelectOptionVO> setOfGroups;
	private List<SelectOptionVO> listOfUsers;
	private String message;
	private String name;
	private List<SelectOptionVO> listOfEntitlements;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SelectOptionVO> getListOfEntitlements() {
		return listOfEntitlements;
	}

	public void setListOfEntitlements(List<SelectOptionVO> listOfEntitlements) {
		this.listOfEntitlements = listOfEntitlements;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getSetOfGroups() {
		return setOfGroups;
	}

	public void setSetOfGroups(List<SelectOptionVO> setOfGroups) {
		this.setOfGroups = setOfGroups;
	}

	public List<SelectOptionVO> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(List<SelectOptionVO> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}

