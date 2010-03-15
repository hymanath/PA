/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyWisePartyResultsForMandal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261308407228468467L;
	
	private Long constituencyId;
	private String constiName;
	private Long maleVoters;
	private Long femaleVoters;
	private Long polledVotes;
	private Long noOfVoters;
	private Long noOfBooths;
	private Long maleAndFemaleVoters;
	private List<PartyElectionResultVO> partyElecResults;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstiName() {
		return constiName;
	}
	public void setConstiName(String constiName) {
		this.constiName = constiName;
	}
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public Long getPolledVotes() {
		return polledVotes;
	}
	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}
	public Long getMaleAndFemaleVoters() {
		return maleAndFemaleVoters;
	}
	public void setMaleAndFemaleVoters(Long maleAndFemaleVoters) {
		this.maleAndFemaleVoters = maleAndFemaleVoters;
	}
	public Long getNoOfVoters() {
		return noOfVoters;
	}
	public void setNoOfVoters(Long noOfVoters) {
		this.noOfVoters = noOfVoters;
	}
	public Long getNoOfBooths() {
		return noOfBooths;
	}
	public void setNoOfBooths(Long noOfBooths) {
		this.noOfBooths = noOfBooths;
	}
	public List<PartyElectionResultVO> getPartyElecResults() {
		return partyElecResults;
	}
	public void setPartyElecResults(List<PartyElectionResultVO> partyElecResults) {
		this.partyElecResults = partyElecResults;
	}

}
