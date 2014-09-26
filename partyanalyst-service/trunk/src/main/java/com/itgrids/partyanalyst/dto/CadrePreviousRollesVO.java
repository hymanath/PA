package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class CadrePreviousRollesVO implements Serializable {

	
	private static final long serialVersionUID = 6426809253013344836L;
	private Long designationLevelId;
	private Long designationLevelValue;
	private Date fromDate;
	private Date toDate;
	
	private Long electionTypeId;
	private String year;
	private Long constituencyId;
	public Long getDesignationLevelId() {
		return designationLevelId;
	}
	public void setDesignationLevelId(Long designationLevelId) {
		this.designationLevelId = designationLevelId;
	}
	public Long getDesignationLevelValue() {
		return designationLevelValue;
	}
	public void setDesignationLevelValue(Long designationLevelValue) {
		this.designationLevelValue = designationLevelValue;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
	
}
