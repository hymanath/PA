package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VoterStratagicReportVo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String voterAgeRange;
	private Long femaleVotersCount;
	private Long maleVotersCount;
	private Long totalVoters;
	
	private Double femaleTotalPercentage;	
	private Double maleTotalPercentage;	
	private Double totalPercentage;

	private String message;
	private List<VoterStratagicReportVo> voterStategicReportVOList = null;

	private String heading;
	
	
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVoterAgeRange() {
		return voterAgeRange;
	}

	public void setVoterAgeRange(String voterAgeRange) {
		this.voterAgeRange = voterAgeRange;
	}

	public Long getFemaleVotersCount() {
		return femaleVotersCount;
	}

	public void setFemaleVotersCount(Long femaleVotersCount) {
		this.femaleVotersCount = femaleVotersCount;
	}

	public Long getMaleVotersCount() {
		return maleVotersCount;
	}

	public void setMaleVotersCount(Long maleVotersCount) {
		this.maleVotersCount = maleVotersCount;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Double getFemaleTotalPercentage() {
		return femaleTotalPercentage;
	}

	public void setFemaleTotalPercentage(Double femaleTotalPercentage) {
		this.femaleTotalPercentage = femaleTotalPercentage;
	}

	public Double getMaleTotalPercentage() {
		return maleTotalPercentage;
	}

	public void setMaleTotalPercentage(Double maleTotalPercentage) {
		this.maleTotalPercentage = maleTotalPercentage;
	}

	public Double getTotalPercentage() {
		return totalPercentage;
	}

	public void setTotalPercentage(Double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}

	public List<VoterStratagicReportVo> getVoterStategicReportVOList() {
		return voterStategicReportVOList;
	}

	public void setVoterStategicReportVOList(
			List<VoterStratagicReportVo> voterStategicReportVOList) {
		this.voterStategicReportVOList = voterStategicReportVOList;
	}
	
	
}
