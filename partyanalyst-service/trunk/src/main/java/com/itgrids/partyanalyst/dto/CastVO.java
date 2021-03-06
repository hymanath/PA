package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CastVO implements Serializable, Comparable<CastVO>{

	private String castName;
	private String castPercentage;
	private Long castCount; 
	
	private String canstnone;
	private Long castnoneCount;
	
	private String gender;
	
	private Long femalevoters;
	
	private Long malevoters;
	
	private Long castStateId;
	
	private String casteCategoryName;

	private Long partyCount = 0l;
	
	private String partyName;
	
	private Long partyId;
	
	private List<CastVO> partiesList,casteList,casteStateList;
	
	private Map<Long,CastVO> partiesMap;
	
	private Long partyNotAssigCount = 0l;
	
	private String expctdPercentage;
	
	private int expctdVotesCount;
	
	private Long panchayatId;
	private Long locationId;
	private String locationName;
	private Long totalVoters;
	private String panchayat;
	private String booth;
	private String constituency;
	private String tehsil;
	private String hamlet;
	private String range;
	private List<CastVO> range1;
	private Long casteId;
	private Long stateId;
	private Long casteCategoryGroupId;

   public CastVO(){
		
	}
	
	public CastVO(String castName, String castPercentage) {
		this.castName = castName;
		this.castPercentage = castPercentage;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCasteCategoryGroupId() {
		return casteCategoryGroupId;
	}

	public void setCasteCategoryGroupId(Long casteCategoryGroupId) {
		this.casteCategoryGroupId = casteCategoryGroupId;
	}

	public List<CastVO> getCasteStateList() {
		return casteStateList;
	}

	public void setCasteStateList(List<CastVO> casteStateList) {
		this.casteStateList = casteStateList;
	}

	public Long getCasteId() {
		return casteId;
	}

	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public List<CastVO> getRange1() {
		return range1;
	}

	public void setRange1(List<CastVO> range1) {
		this.range1 = range1;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}

	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getCastName() {
		return castName;
	}
	
	public void setCastName(String castName) {
		this.castName = castName;
	}
	
	public String getCastPercentage() {
		return castPercentage;
	}
	
	public void setCastPercentage(String castPercentage) {
		this.castPercentage = castPercentage;
	}

	public Long getCastCount() {
		return castCount;
	}

	public void setCastCount(Long castCount) {
		this.castCount = castCount;
	}
	
	public String getCanstnone() {
		return canstnone;
	}

	public void setCanstnone(String canstnone) {
		this.canstnone = canstnone;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCastnoneCount() {
		return castnoneCount;
	}

	public void setCastnoneCount(Long castnoneCount) {
		this.castnoneCount = castnoneCount;
	}
	
	public Long getFemalevoters() {
		return femalevoters;
	}

	public void setFemalevoters(Long femalevoters) {
		this.femalevoters = femalevoters;
	}

	public Long getMalevoters() {
		return malevoters;
	}

	public void setMalevoters(Long malevoters) {
		this.malevoters = malevoters;
	}
	
	public int compareTo(CastVO arg0) {
		CastVO obj = null;
		if(obj instanceof CastVO){
			CastVO vo = (CastVO) obj;
			return castName.compareToIgnoreCase(vo.getCastName());
		}
		else
			return 0;
	}

	public Long getCastStateId() {
		return castStateId;
	}

	public void setCastStateId(Long castStateId) {
		this.castStateId = castStateId;
	}
	public String getCasteCategoryName() {
		return casteCategoryName;
	}

	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}

	public Long getPartyCount() {
		return partyCount;
	}

	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}

	public List<CastVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<CastVO> partiesList) {
		this.partiesList = partiesList;
	}

	public Map<Long, CastVO> getPartiesMap() {
		return partiesMap;
	}

	public void setPartiesMap(Map<Long, CastVO> partiesMap) {
		this.partiesMap = partiesMap;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getPartyNotAssigCount() {
		return partyNotAssigCount;
	}

	public void setPartyNotAssigCount(Long partyNotAssigCount) {
		this.partyNotAssigCount = partyNotAssigCount;
	}

	public String getExpctdPercentage() {
		return expctdPercentage;
	}

	public void setExpctdPercentage(String expctdPercentage) {
		this.expctdPercentage = expctdPercentage;
	}

	public int getExpctdVotesCount() {
		return expctdVotesCount;
	}

	public void setExpctdVotesCount(int expctdVotesCount) {
		this.expctdVotesCount = expctdVotesCount;
	}

	public List<CastVO> getCasteList() {
		return casteList;
	}

	public void setCasteList(List<CastVO> casteList) {
		this.casteList = casteList;
	}

	

}
