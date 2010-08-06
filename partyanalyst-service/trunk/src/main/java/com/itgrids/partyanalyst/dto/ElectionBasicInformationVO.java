/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 04, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

/*
 * @author Sai Krishna Basetti
 */
public class ElectionBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2732227598119291604L;
	
	/** Election ID for a happened Election */
	private Long electionId;
	/** Type of Election */
	private String electionType;
	/** Scope of Election*/
	private String electionScope;
	/** Year of Election Happened*/
	private String electionYear;
	/** Notification date of Election*/
	private Date notificationDate;
	/** Notification Date in String form*/
	private String notificationDateAsString;
	/** Start date of Election*/
	private Date startDate;
	/** Start Date in String form*/
	private String startStringDateAsString;
	
	
	/** Getters and Setters */
	
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionScope() {
		return electionScope;
	}
	public void setElectionScope(String electionScope) {
		this.electionScope = electionScope;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getNotificationDateAsString() {
		return notificationDateAsString;
	}
	public void setNotificationDateAsString(String notificationDateAsString) {
		this.notificationDateAsString = notificationDateAsString;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartStringDateAsString() {
		return startStringDateAsString;
	}
	public void setStartStringDateAsString(String startStringDateAsString) {
		this.startStringDateAsString = startStringDateAsString;
	}

}
