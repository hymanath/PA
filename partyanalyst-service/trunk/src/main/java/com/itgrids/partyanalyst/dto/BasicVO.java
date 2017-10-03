package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class BasicVO implements Serializable{

	private Long id;
	private Long count=0l;
	private String name;
	private String description;
	private String mandalName;
	private String casteName;
	private Double perc;
	private List<BasicVO> selectedCasteDetails = new ArrayList<BasicVO>();
	private List<BasicVO> hamletVoterInfo = new ArrayList<BasicVO>();
	private List<BasicVO> hamletCasteInfo = new ArrayList<BasicVO>();
	private List<BasicVO> panchayatVoterInfo = new ArrayList<BasicVO>();
	private List<VoterCastInfoVO> casteList = new ArrayList<VoterCastInfoVO>();
	private Long hamletId;
	private String hamletName;
	private Long voterCount=0L;
	private List casteNames;
	private String persent;
	private Long expCount;
	private Long levelValue;
	private Long levelId;
	private BasicVO afterDelimationDetails;
	private BasicVO beforeDelimationDetails;
	private Long totalVoters=0L;
	private String aliancedWith;
	private List<String> ageRanges = new ArrayList<String>();
	private Long electionTypeId;
	private String electionType;
	private Long districtId;
	private String district;
	private Long parlimentId;
	private String parliament;
	private List<BasicVO> daysList;
	private List<BasicVO> locationsList=new ArrayList<BasicVO>(0);
	private List<BasicVO> constituencyList = new ArrayList<BasicVO>(0);
	private List<BasicVO> districtList = new ArrayList<BasicVO>(0);
	private Long		  locationInfoId;
	private ActivityAttendanceInfoVO activityAttendanceInfoVO = new ActivityAttendanceInfoVO();
	private Map<String,Long> dayWiseMap = new LinkedHashMap<String, Long>();
	private Long coveredCount;
	private Long day;
	private Long totalResult=0L;
	private String date;
	private List<EventDocumentVO> documentsVOList = new ArrayList<EventDocumentVO>(0);
	
	public BasicVO(){}
	
	public BasicVO(Long id,String name)
	{
		id= this.id;
		name = this.name;
		
	}
	
	
	public Map<String, Long> getDayWiseMap() {
		return dayWiseMap;
	}

	public void setDayWiseMap(Map<String, Long> dayWiseMap) {
		this.dayWiseMap = dayWiseMap;
	}

	public Long getLocationInfoId() {
		return locationInfoId;
	}

	public void setLocationInfoId(Long locationInfoId) {
		this.locationInfoId = locationInfoId;
	}

	public List<BasicVO> getDaysList() {
		return daysList;
	}

	public void setDaysList(List<BasicVO> daysList) {
		this.daysList = daysList;
	}

	public List<BasicVO> getLocationsList() {
		return locationsList;
	}

	public void setLocationsList(List<BasicVO> locationsList) {
		this.locationsList = locationsList;
	}

	public List<String> getAgeRanges() {
		return ageRanges;
	}

	public void setAgeRanges(List<String> ageRanges) {
		this.ageRanges = ageRanges;
	}

	public String getAliancedWith() {
		return aliancedWith;
	}
	public void setAliancedWith(String aliancedWith) {
		this.aliancedWith = aliancedWith;
	}
	public List<BasicVO> getSelectedCasteDetails() {
		return selectedCasteDetails;
	}
	public void setSelectedCasteDetails(List<BasicVO> selectedCasteDetails) {
		this.selectedCasteDetails = selectedCasteDetails;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getPersent() {
		return persent;
	}
	public void setPersent(String persent) {
		this.persent = persent;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public List<BasicVO> getPanchayatVoterInfo() {
		return panchayatVoterInfo;
	}
	public void setPanchayatVoterInfo(List<BasicVO> panchayatVoterInfo) {
		this.panchayatVoterInfo = panchayatVoterInfo;
	}
	public List getCasteNames() {
		return casteNames;
	}
	public void setCasteNames(List casteNames) {
		this.casteNames = casteNames;
	}
	public List<VoterCastInfoVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<VoterCastInfoVO> casteList) {
		this.casteList = casteList;
	}
	public List<BasicVO> getHamletVoterInfo() {
		return hamletVoterInfo;
	}
	public void setHamletVoterInfo(List<BasicVO> hamletVoterInfo) {
		this.hamletVoterInfo = hamletVoterInfo;
	}
	public List<BasicVO> getHamletCasteInfo() {
		return hamletCasteInfo;
	}
	public void setHamletCasteInfo(List<BasicVO> hamletCasteInfo) {
		this.hamletCasteInfo = hamletCasteInfo;
	}
	public Long getVoterCount() {
		return voterCount;
	}
	public void setVoterCount(Long voterCount) {
		this.voterCount = voterCount;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public Long getExpCount() {
		return expCount;
	}
	public void setExpCount(Long expCount) {
		this.expCount = expCount;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public BasicVO getAfterDelimationDetails() {
		return afterDelimationDetails;
	}
	public void setAfterDelimationDetails(BasicVO afterDelimationDetails) {
		this.afterDelimationDetails = afterDelimationDetails;
	}
	public BasicVO getBeforeDelimationDetails() {
		return beforeDelimationDetails;
	}
	public void setBeforeDelimationDetails(BasicVO beforeDelimationDetails) {
		this.beforeDelimationDetails = beforeDelimationDetails;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getParlimentId() {
		return parlimentId;
	}

	public void setParlimentId(Long parlimentId) {
		this.parlimentId = parlimentId;
	}

	public String getParliament() {
		return parliament;
	}

	public void setParliament(String parliament) {
		this.parliament = parliament;
	}

	public List<BasicVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<BasicVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public List<BasicVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<BasicVO> districtList) {
		this.districtList = districtList;
	}

	public ActivityAttendanceInfoVO getActivityAttendanceInfoVO() {
		return activityAttendanceInfoVO;
	}

	public void setActivityAttendanceInfoVO(
			ActivityAttendanceInfoVO activityAttendanceInfoVO) {
		this.activityAttendanceInfoVO = activityAttendanceInfoVO;
	}

	public Long getCoveredCount() {
		return coveredCount;
	}

	public void setCoveredCount(Long coveredCount) {
		this.coveredCount = coveredCount;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Long totalResult) {
		this.totalResult = totalResult;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<EventDocumentVO> getDocumentsVOList() {
		return documentsVOList;
	}

	public void setDocumentsVOList(List<EventDocumentVO> documentsVOList) {
		this.documentsVOList = documentsVOList;
	}
	
}
