package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class TownshipBoothDetailsVO implements Serializable {
	//0-townshipID, 1-townshipName, 2-totalVoters, 3-validVoters, 
	//4-boothID, 5-partNo, 6-hamletId, 7-hamletName
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long townshipID;
	private String townshipName;
	private Long validVoters;
	private String percentageOfValidVotes;
	
	private Long sNO;
	private String chartTitle;
	private String chartName;
	private Long electionId;
	private Long mandalId;
	private String mandalName;
	private Long totalValidVotersInMandal;
	ResultStatus resultStatus;
	private Long totalVoters;
	
	private List<TownshipBoothDetailsVO> townshipVotingTrends;
	private Set<SelectOptionVO> booths;
	/*private Long boothID;
	private String partNo;*/

	private Set<SelectOptionVO> hamlets;
	/*private Long hamletId;
	private String hamletName;*/
	
	// logged in user voters total and percentage
	private String userVotesInfo;

	public Long getSNO() {
		return sNO;
	}

	public void setSNO(Long sno) {
		sNO = sno;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public List<TownshipBoothDetailsVO> getTownshipVotingTrends() {
		return townshipVotingTrends;
	}

	public void setTownshipVotingTrends(
			List<TownshipBoothDetailsVO> townshipVotingTrends) {
		this.townshipVotingTrends = townshipVotingTrends;
	}

	public String getPercentageOfValidVotes() {
		return percentageOfValidVotes;
	}

	public void setPercentageOfValidVotes(String percentageOfValidVotes) {
		this.percentageOfValidVotes = percentageOfValidVotes;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public Long getTotalValidVotersInMandal() {
		return totalValidVotersInMandal;
	}

	public void setTotalValidVotersInMandal(Long totalValidVotersInMandal) {
		this.totalValidVotersInMandal = totalValidVotersInMandal;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getTownshipID() {
		return townshipID;
	}

	public void setTownshipID(Long townshipID) {
		this.townshipID = townshipID;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getValidVoters() {
		return validVoters;
	}

	public void setValidVoters(Long validVoters) {
		this.validVoters = validVoters;
	}

	public Set<SelectOptionVO> getBooths() {
		return booths;
	}

	public void setBooths(Set<SelectOptionVO> booths) {
		this.booths = booths;
	}

	public Set<SelectOptionVO> getHamlets() {
		return hamlets;
	}

	public void setHamlets(Set<SelectOptionVO> hamlets) {
		this.hamlets = hamlets;
	}

	public String getUserVotesInfo() {
		return userVotesInfo;
	}

	public void setUserVotesInfo(String userVotesInfo) {
		this.userVotesInfo = userVotesInfo;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	
}
