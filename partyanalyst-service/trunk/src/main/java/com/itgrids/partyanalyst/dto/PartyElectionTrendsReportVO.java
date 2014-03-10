package com.itgrids.partyanalyst.dto;

public class PartyElectionTrendsReportVO implements Comparable<PartyElectionTrendsReportVO> {
	//private String party;
	private Integer electionYear;
	private Long  totalVoters;
	private Long totalVotesPolled;
	private String tdpPartyName;
	
	private PartyElectionTrendsReportHelperVO tdpVo;
	private PartyElectionTrendsReportHelperVO incVo=new PartyElectionTrendsReportHelperVO();
	private PartyElectionTrendsReportHelperVO prpVo = new PartyElectionTrendsReportHelperVO();
	private PartyElectionTrendsReportHelperVO trsVo = new PartyElectionTrendsReportHelperVO();
	private PartyElectionTrendsReportHelperVO bjpVo = new PartyElectionTrendsReportHelperVO();
	private PartyElectionTrendsReportHelperVO othersVo;
	
	
	public PartyElectionTrendsReportHelperVO getTrsVo() {
		return trsVo;
	}
	public void setTrsVo(PartyElectionTrendsReportHelperVO trsVo) {
		this.trsVo = trsVo;
	}
	public PartyElectionTrendsReportHelperVO getBjpVo() {
		return bjpVo;
	}
	public void setBjpVo(PartyElectionTrendsReportHelperVO bjpVo) {
		this.bjpVo = bjpVo;
	}
	public Integer getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(Integer electionYear) {
		this.electionYear = electionYear;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getTotalVotesPolled() {
		return totalVotesPolled;
	}
	public void setTotalVotesPolled(Long totalVotesPolled) {
		this.totalVotesPolled = totalVotesPolled;
	}
	public String getTdpPartyName() {
		return tdpPartyName;
	}
	public void setTdpPartyName(String tdpPartyName) {
		this.tdpPartyName = tdpPartyName;
	}
	public PartyElectionTrendsReportHelperVO getTdpVo() {
		return tdpVo;
	}
	public void setTdpVo(PartyElectionTrendsReportHelperVO tdpVo) {
		this.tdpVo = tdpVo;
	}
	public PartyElectionTrendsReportHelperVO getIncVo() {
		return incVo;
	}
	public void setIncVo(PartyElectionTrendsReportHelperVO incVo) {
		this.incVo = incVo;
	}
	public PartyElectionTrendsReportHelperVO getPrpVo() {
		return prpVo;
	}
	public void setPrpVo(PartyElectionTrendsReportHelperVO prpVo) {
		this.prpVo = prpVo;
	}
	public PartyElectionTrendsReportHelperVO getOthersVo() {
		return othersVo;
	}
	public void setOthersVo(PartyElectionTrendsReportHelperVO othersVo) {
		this.othersVo = othersVo;
	}
	/*@Override
	public String toString() {
		return "PartyElectionTrendsReportVO [electionYear=" + electionYear
				+ ", totalVoters=" + totalVoters + ", totalVotesPolled="
				+ totalVotesPolled + ", tdpPartyName=" + tdpPartyName
				+ ", tdpVo=" + tdpVo + ", incVo=" + incVo + ", prpVo=" + prpVo
				+ "]";
	}*/
	@Override
	public int compareTo(PartyElectionTrendsReportVO o) {
		
		return o.electionYear.compareTo(this.electionYear);
	}
  
	
	
	
	//private String electionType;
	//private Long voterSize;
//	private Long presVotesEarned;
//	private Long prevVotesEarned;
//	private String presPercentage;
//	private String prevPercentage;
	
	
}
