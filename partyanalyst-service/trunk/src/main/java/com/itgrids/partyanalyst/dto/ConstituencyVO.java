package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;



public class ConstituencyVO extends ResultStatus{
	
	private Long id;
	private Long constituencyId;
	private Long districtId;
	private String name;
	private String stateName;
	private String districtName;
	private String electionType;
	private Date delemitationInfo;
	private String delemitationInfoStr;
	private List<VotersWithDelimitationInfoVO> assembliesOfParliamentInfo;
	private List<VotersWithDelimitationInfoVO> assembliesOfParliamentBasicInfo;
	private String[] pieChartNames;
	private String[] extraInfo;
	private List<PartyResultVO> predictedPartiesResults;
	private List<PartyResultVO> allPartiesElecResults;
	private Long totalVoters2010;
	private Long totalVoters2009;
	private Long totalPolledVotes;
	private String votesPercent;
	private List<MandalVO> localElectionsInfo;
	
	private Boolean isDataExists = true;
	private List<PartyResultsVO> presentYearResults;
	private List<PartyResultsVO> previousYearResults;
	private List<PartyElectionVotersVO> partiesCombinedResults;
	
	private List<SelectOptionVO> listOfWards;
	private List<SelectOptionVO> listOfParties;
	
	public ConstituencyVO(){
		
	}
	public ConstituencyVO(Long id, String name, String stateName, String districtName,
			String electionType,Date delemitationInfo) {
		this.id = id;
		this.name = name;
		this.stateName = stateName;
		this.districtName = districtName;
		this.electionType = electionType;
		this.delemitationInfo = delemitationInfo;
	}
	
	public List<VotersWithDelimitationInfoVO> getAssembliesOfParliamentBasicInfo() {
		return assembliesOfParliamentBasicInfo;
	}
	public void setAssembliesOfParliamentBasicInfo(
			List<VotersWithDelimitationInfoVO> assembliesOfParliamentBasicInfo) {
		this.assembliesOfParliamentBasicInfo = assembliesOfParliamentBasicInfo;
	}
	public List<SelectOptionVO> getListOfWards() {
		return listOfWards;
	}
	public void setListOfWards(List<SelectOptionVO> listOfWards) {
		this.listOfWards = listOfWards;
	}
	public List<SelectOptionVO> getListOfParties() {
		return listOfParties;
	}
	public void setListOfParties(List<SelectOptionVO> listOfParties) {
		this.listOfParties = listOfParties;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public Date getDelemitationInfo() {
		return delemitationInfo;
	}
	public void setDelemitationInfo(Date delemitationInfo) {
		this.delemitationInfo = delemitationInfo;
	}
	public List<VotersWithDelimitationInfoVO> getAssembliesOfParliamentInfo() {
		return assembliesOfParliamentInfo;
	}
	public void setAssembliesOfParliamentInfo(
			List<VotersWithDelimitationInfoVO> assembliesOfParliamentInfo) {
		this.assembliesOfParliamentInfo = assembliesOfParliamentInfo;
	}
	public String[] getPieChartNames() {
		return pieChartNames;
	}
	public void setPieChartNames(String[] pieChartNames) {
		this.pieChartNames = pieChartNames;
	}
	public String[] getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String[] extraInfo) {
		this.extraInfo = extraInfo;
	}
	public List<PartyResultVO> getPredictedPartiesResults() {
		return predictedPartiesResults;
	}
	public void setPredictedPartiesResults(
			List<PartyResultVO> predictedPartiesResults) {
		this.predictedPartiesResults = predictedPartiesResults;
	}
	public List<PartyResultVO> getAllPartiesElecResults() {
		return allPartiesElecResults;
	}
	public void setAllPartiesElecResults(List<PartyResultVO> allPartiesElecResults) {
		this.allPartiesElecResults = allPartiesElecResults;
	}
	public Long getTotalVoters2010() {
		return totalVoters2010;
	}
	public void setTotalVoters2010(Long totalVoters2010) {
		this.totalVoters2010 = totalVoters2010;
	}
	public Long getTotalVoters2009() {
		return totalVoters2009;
	}
	public void setTotalVoters2009(Long totalVoters2009) {
		this.totalVoters2009 = totalVoters2009;
	}
	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}
	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}
	public String getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public List<PartyResultsVO> getPresentYearResults() {
		return presentYearResults;
	}
	public void setPresentYearResults(List<PartyResultsVO> presentYearResults) {
		this.presentYearResults = presentYearResults;
	}
	public List<PartyResultsVO> getPreviousYearResults() {
		return previousYearResults;
	}
	public void setPreviousYearResults(List<PartyResultsVO> previousYearResults) {
		this.previousYearResults = previousYearResults;
	}
	public List<PartyElectionVotersVO> getPartiesCombinedResults() {
		return partiesCombinedResults;
	}
	public void setPartiesCombinedResults(
			List<PartyElectionVotersVO> partiesCombinedResults) {
		this.partiesCombinedResults = partiesCombinedResults;
	}
	public Boolean getIsDataExists() {
		return isDataExists;
	}
	public void setIsDataExists(Boolean isDataExists) {
		this.isDataExists = isDataExists;
	}
	public List<MandalVO> getLocalElectionsInfo() {
		return localElectionsInfo;
	}
	public void setLocalElectionsInfo(List<MandalVO> localElectionsInfo) {
		this.localElectionsInfo = localElectionsInfo;
	}
	public String getDelemitationInfoStr() {
		return delemitationInfoStr;
	}
	public void setDelemitationInfoStr(String delemitationInfoStr) {
		this.delemitationInfoStr = delemitationInfoStr;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
		
}
