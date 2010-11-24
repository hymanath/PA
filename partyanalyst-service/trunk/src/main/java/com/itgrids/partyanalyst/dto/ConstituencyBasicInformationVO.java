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
public class ConstituencyBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Constituency ID*/
	private Long constituencyId;
	/** Name of the Constituency*/
	private String constituencyName;
	/** Type of Constituency*/
	private String constituencyType;
	/** Reservation Zone */
	private String reservationZone;
	/** District Id */
	private Long districtId;
	/** District of the Constituency*/
	private String districtName;
	/** Tehsil Id */
	private Long tehsilId;
	/** Name of Tehsil  */
	private String tehsilName;
	/** state Id*/
	private Long stateId;
	/** State Name */
	private String stateName;
	/** Scope of constituency */
	private String constituencyScope;
    /** Constituency Start Date */
	private Date startDate;
	/** Start Date as String */
	private String startDateAsString;
	/** Constituency Deform Date*/
	private Date deformDate;
	/** Deform Date as String */
	private String deformDateAsString;
	
	/** Getters And Setters */
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getConstituencyScope() {
		return constituencyScope;
	}
	public void setConstituencyScope(String constituencyScope) {
		this.constituencyScope = constituencyScope;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartDateAsString() {
		return startDateAsString;
	}
	public void setStartDateAsString(String startDateAsString) {
		this.startDateAsString = startDateAsString;
	}
	public Date getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}
	public String getDeformDateAsString() {
		return deformDateAsString;
	}
	public void setDeformDateAsString(String deformDateAsString) {
		this.deformDateAsString = deformDateAsString;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getReservationZone() {
		return reservationZone;
	}
	public void setReservationZone(String reservationZone) {
		this.reservationZone = reservationZone;
	}
}
