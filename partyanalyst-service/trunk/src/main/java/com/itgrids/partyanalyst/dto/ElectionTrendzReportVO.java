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

public class ElectionTrendzReportVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -255839104302126865L;
	
	private String state;
	private String electionType;
	private String electionYear;
	private Long constituencyId;
	private String constituencyName;
	private List<SelectOptionVO> otherElectionYears;
	private List<SelectOptionVO> mandalsInfo;
	
	private ElectionTrendzInfoVO completeTrendz;
	private ElectionTrendzInfoVO maleTrendz;
	private ElectionTrendzInfoVO femaleTrendz;
	private ElectionTrendzInfoVO maleAndFemaleTrendz;
	
	private ResultStatus resultStatus;
	
    //getters and setters
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

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

	public List<SelectOptionVO> getOtherElectionYears() {
		return otherElectionYears;
	}

	public void setOtherElectionYears(List<SelectOptionVO> otherElectionYears) {
		this.otherElectionYears = otherElectionYears;
	}

	public List<SelectOptionVO> getMandalsInfo() {
		return mandalsInfo;
	}

	public void setMandalsInfo(List<SelectOptionVO> mandalsInfo) {
		this.mandalsInfo = mandalsInfo;
	}

	public ElectionTrendzInfoVO getCompleteTrendz() {
		return completeTrendz;
	}

	public void setCompleteTrendz(ElectionTrendzInfoVO completeTrendz) {
		this.completeTrendz = completeTrendz;
	}

	public ElectionTrendzInfoVO getMaleTrendz() {
		return maleTrendz;
	}

	public void setMaleTrendz(ElectionTrendzInfoVO maleTrendz) {
		this.maleTrendz = maleTrendz;
	}

	public ElectionTrendzInfoVO getFemaleTrendz() {
		return femaleTrendz;
	}

	public void setFemaleTrendz(ElectionTrendzInfoVO femaleTrendz) {
		this.femaleTrendz = femaleTrendz;
	}

	public ElectionTrendzInfoVO getMaleAndFemaleTrendz() {
		return maleAndFemaleTrendz;
	}

	public void setMaleAndFemaleTrendz(ElectionTrendzInfoVO maleAndFemaleTrendz) {
		this.maleAndFemaleTrendz = maleAndFemaleTrendz;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	

}
