package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VotersInfoForMandalVO {
	
	private String mandalId;
	private String mandalName;
	private String totalMaleVoters;
	private String totalFemaleVoters;
	private String totalVoters;
	private String isPartial;
	private String percent;
	private BigDecimal totVoters;
	private Boolean isMandal = false;
	private String unKnowVoters;
	private List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
	private boolean subLevelExists;
	private Long id;
	private String name;
	private String type;
	private String electionYear;
	private boolean datapresent = true;
	private List<VotersInfoForMandalVO> previousElectInfoList;
	private Long maleVotersDiff = 0l;
	private Long femaleVotersDiff = 0l;
	private Long totalVotersDiff = 0l;
	private String status;
	private String  totalVotersPercentage;
	private String totalMalePercentage;
	private String totalFemalePercentage;
	private Long totalFamilies;
	private String totalFamilyPercentage;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long publicationDateId;
	private Long totalBooths = 0l;
	private Long constituencyId;
	
	public Long getReportLevelId() {
		return reportLevelId;
	}
	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getTotalFamilies() {
		return totalFamilies;
	}
	public void setTotalFamilies(Long totalFamilies) {
		this.totalFamilies = totalFamilies;
	}
	public String getTotalFamilyPercentage() {
		return totalFamilyPercentage;
	}
	public void setTotalFamilyPercentage(String totalFamilyPercentage) {
		this.totalFamilyPercentage = totalFamilyPercentage;
	}
	private boolean panchayatInfoPresent; 
	List<String> newlyAdded;
	List<String> completelyRemoved;
	List<String> otherComment;
	Integer presentBoothsCount;
	Integer prevBoothsCount;
	String presentBooths;
	String prevBooths;
	private String isPublication;
	private String publicationDate;
	private String electionDate;
	private int electinYear ;
	
	public int getElectinYear() {
		return electinYear;
	}
	public void setElectinYear(int electinYear) {
		this.electinYear = electinYear;
	}
	public String getElectionDate() {
		return electionDate;
	}
	public void setElectionDate(String electionDate) {
		this.electionDate = electionDate;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getIsPublication() {
		return isPublication;
	}
	public void setIsPublication(String isPublication) {
		this.isPublication = isPublication;
	}
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getTotalMaleVoters() {
		return totalMaleVoters;
	}
	public void setTotalMaleVoters(String totalMaleVoters) {
		this.totalMaleVoters = totalMaleVoters;
	}
	public String getTotalFemaleVoters() {
		return totalFemaleVoters;
	}
	public void setTotalFemaleVoters(String totalFemaleVoters) {
		this.totalFemaleVoters = totalFemaleVoters;
	}
	public String getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(String totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public BigDecimal getTotVoters() {
		return totVoters;
	}
	public void setTotVoters(BigDecimal totVoters) {
		this.totVoters = totVoters;
	}
	public Boolean getIsMandal() {
		return isMandal;
	}
	public void setIsMandal(Boolean isMandal) {
		this.isMandal = isMandal;
	}
	public String getUnKnowVoters() {
		return unKnowVoters;
	}
	public void setUnKnowVoters(String unKnowVoters) {
		this.unKnowVoters = unKnowVoters;
	}
	public List<VotersInfoForMandalVO> getVotersInfoForMandalVOList() {
		return votersInfoForMandalVOList;
	}
	public void setVotersInfoForMandalVOList(
			List<VotersInfoForMandalVO> votersInfoForMandalVOList) {
		this.votersInfoForMandalVOList = votersInfoForMandalVOList;
	}
	public boolean isSubLevelExists() {
		return subLevelExists;
	}
	public void setSubLevelExists(boolean subLevelExists) {
		this.subLevelExists = subLevelExists;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public boolean isDatapresent() {
		return datapresent;
	}
	public void setDatapresent(boolean datapresent) {
		this.datapresent = datapresent;
	}
	public List<VotersInfoForMandalVO> getPreviousElectInfoList() {
		return previousElectInfoList;
	}
	public void setPreviousElectInfoList(
			List<VotersInfoForMandalVO> previousElectInfoList) {
		this.previousElectInfoList = previousElectInfoList;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Long getMaleVotersDiff() {
		return maleVotersDiff;
	}
	public void setMaleVotersDiff(Long maleVotersDiff) {
		this.maleVotersDiff = maleVotersDiff;
	}
	public Long getFemaleVotersDiff() {
		return femaleVotersDiff;
	}
	public void setFemaleVotersDiff(Long femaleVotersDiff) {
		this.femaleVotersDiff = femaleVotersDiff;
	}
	public Long getTotalVotersDiff() {
		return totalVotersDiff;
	}
	public void setTotalVotersDiff(Long totalVotersDiff) {
		this.totalVotersDiff = totalVotersDiff;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getTotalVotersPercentage() {
		return totalVotersPercentage;
	}
	public void setTotalVotersPercentage(String totalVotersPercentage) {
		this.totalVotersPercentage = totalVotersPercentage;
	}
	public String getTotalMalePercentage() {
		return totalMalePercentage;
	}
	public void setTotalMalePercentage(String totalMalePercentage) {
		this.totalMalePercentage = totalMalePercentage;
	}
	public String getTotalFemalePercentage() {
		return totalFemalePercentage;
	}
	public void setTotalFemalePercentage(String totalFemalePercentage) {
		this.totalFemalePercentage = totalFemalePercentage;
	}
	public boolean isPanchayatInfoPresent() {
		return panchayatInfoPresent;
	}
	public void setPanchayatInfoPresent(boolean panchayatInfoPresent) {
		this.panchayatInfoPresent = panchayatInfoPresent;
	}
	public List<String> getNewlyAdded() {
		return newlyAdded;
	}
	public void setNewlyAdded(List<String> newlyAdded) {
		this.newlyAdded = newlyAdded;
	}
	public List<String> getCompletelyRemoved() {
		return completelyRemoved;
	}
	public void setCompletelyRemoved(List<String> completelyRemoved) {
		this.completelyRemoved = completelyRemoved;
	}
	public List<String> getOtherComment() {
		return otherComment;
	}
	public void setOtherComment(List<String> otherComment) {
		this.otherComment = otherComment;
	}
	public Integer getPresentBoothsCount() {
		return presentBoothsCount;
	}
	public void setPresentBoothsCount(Integer presentBoothsCount) {
		this.presentBoothsCount = presentBoothsCount;
	}
	public Integer getPrevBoothsCount() {
		return prevBoothsCount;
	}
	public void setPrevBoothsCount(Integer prevBoothsCount) {
		this.prevBoothsCount = prevBoothsCount;
	}
	public String getPresentBooths() {
		return presentBooths;
	}
	public void setPresentBooths(String presentBooths) {
		this.presentBooths = presentBooths;
	}
	public String getPrevBooths() {
		return prevBooths;
	}
	public void setPrevBooths(String prevBooths) {
		this.prevBooths = prevBooths;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
}
