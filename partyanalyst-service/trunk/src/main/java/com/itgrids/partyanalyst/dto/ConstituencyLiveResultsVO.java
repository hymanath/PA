package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Sasikanth Ravipudi
 */
public class ConstituencyLiveResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long leadingPartyId;
	private Long followingPartyId;
	private String leadingParty;
	private String followingParty;
	private Long leadBy;
	private Long constituencyId;
	private String constituency;
	private Long districtId;
	private String district;
	
	private Long stateId;
	private String state;
	private Long parliamentId;
	private String parliament;
	
	private Long upaAlliancesCount;
	private Long ndaAlliancesCount;
	private Long othersCount;
	
	private Long upaLeadCount;
	private Long ndaLeadCount;
	private Long othersLeadCount;
	private Long upaWonCount;
	private Long ndaWonCount;
	private Long othersWonCount;
	private Long statesTotalCount;
	
	private List<ConstituencyLiveResultsVO> statesList;
	private List<ConstituencyLiveResultsVO> constituenciesList;
	private Map<Long,ConstituencyLiveResultsVO> constiMap = new HashMap<Long, ConstituencyLiveResultsVO>();
	
	
	private Long prevTotalNdaCount;
	private Long prevTotalUpaCount;
	private Long prevTotalOthersCount;
	
	private Long ttlNdaLeadCount;
	private Long ttlUpaLeadCount;
	private Long ttlOthersLeadCount;
	
	private Long ttlNdaWonCount;
	private Long ttlUpaWonCount;
	private Long ttlOthersWonCount;
	
	private Long overallStatesCount;
	
	private Long overAllNdaCount;
	private Long overAllUpaCount;
	private Long overAllOthersCount;
	
	
	private Long partyId;
	private String party;
	private String partyLogoPath;
	private String partyFlagPath;
	private String partyFlagLargePath;
	
	private Long partyWonCount;
	private Long partyLeadCount;
	private Long partyCount;
	
	private String allianceGroup;
	
	
	
	public String getPartyFlagLargePath() {
		return partyFlagLargePath;
	}
	public void setPartyFlagLargePath(String partyFlagLargePath) {
		this.partyFlagLargePath = partyFlagLargePath;
	}
	public Long getPartyWonCount() {
		return partyWonCount;
	}
	public void setPartyWonCount(Long partyWonCount) {
		this.partyWonCount = partyWonCount;
	}
	public Long getPartyLeadCount() {
		return partyLeadCount;
	}
	public void setPartyLeadCount(Long partyLeadCount) {
		this.partyLeadCount = partyLeadCount;
	}
	public String getAllianceGroup() {
		return allianceGroup;
	}
	public void setAllianceGroup(String allianceGroup) {
		this.allianceGroup = allianceGroup;
	}
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getPartyLogoPath() {
		return partyLogoPath;
	}
	public void setPartyLogoPath(String partyLogoPath) {
		this.partyLogoPath = partyLogoPath;
	}
	public String getPartyFlagPath() {
		return partyFlagPath;
	}
	public void setPartyFlagPath(String partyFlagPath) {
		this.partyFlagPath = partyFlagPath;
	}
	public Long getOverAllNdaCount() {
		return overAllNdaCount;
	}
	public void setOverAllNdaCount(Long overAllNdaCount) {
		this.overAllNdaCount = overAllNdaCount;
	}
	public Long getOverAllUpaCount() {
		return overAllUpaCount;
	}
	public void setOverAllUpaCount(Long overAllUpaCount) {
		this.overAllUpaCount = overAllUpaCount;
	}
	public Long getOverAllOthersCount() {
		return overAllOthersCount;
	}
	public void setOverAllOthersCount(Long overAllOthersCount) {
		this.overAllOthersCount = overAllOthersCount;
	}
	public Long getTtlNdaLeadCount() {
		return ttlNdaLeadCount;
	}
	public void setTtlNdaLeadCount(Long ttlNdaLeadCount) {
		this.ttlNdaLeadCount = ttlNdaLeadCount;
	}
	public Long getTtlUpaLeadCount() {
		return ttlUpaLeadCount;
	}
	public void setTtlUpaLeadCount(Long ttlUpaLeadCount) {
		this.ttlUpaLeadCount = ttlUpaLeadCount;
	}
	public Long getTtlOthersLeadCount() {
		return ttlOthersLeadCount;
	}
	public void setTtlOthersLeadCount(Long ttlOthersLeadCount) {
		this.ttlOthersLeadCount = ttlOthersLeadCount;
	}
	public Long getTtlNdaWonCount() {
		return ttlNdaWonCount;
	}
	public void setTtlNdaWonCount(Long ttlNdaWonCount) {
		this.ttlNdaWonCount = ttlNdaWonCount;
	}
	public Long getTtlUpaWonCount() {
		return ttlUpaWonCount;
	}
	public void setTtlUpaWonCount(Long ttlUpaWonCount) {
		this.ttlUpaWonCount = ttlUpaWonCount;
	}
	public Long getTtlOthersWonCount() {
		return ttlOthersWonCount;
	}
	public void setTtlOthersWonCount(Long ttlOthersWonCount) {
		this.ttlOthersWonCount = ttlOthersWonCount;
	}
	public Long getOverallStatesCount() {
		return overallStatesCount;
	}
	public void setOverallStatesCount(Long overallStatesCount) {
		this.overallStatesCount = overallStatesCount;
	}
	public Long getPrevTotalNdaCount() {
		return prevTotalNdaCount;
	}
	public void setPrevTotalNdaCount(Long prevTotalNdaCount) {
		this.prevTotalNdaCount = prevTotalNdaCount;
	}
	public Long getPrevTotalUpaCount() {
		return prevTotalUpaCount;
	}
	public void setPrevTotalUpaCount(Long prevTotalUpaCount) {
		this.prevTotalUpaCount = prevTotalUpaCount;
	}
	public Long getPrevTotalOthersCount() {
		return prevTotalOthersCount;
	}
	public void setPrevTotalOthersCount(Long prevTotalOthersCount) {
		this.prevTotalOthersCount = prevTotalOthersCount;
	}
	public Long getStatesTotalCount() {
		return statesTotalCount;
	}
	public void setStatesTotalCount(Long statesTotalCount) {
		this.statesTotalCount = statesTotalCount;
	}
	public Map<Long, ConstituencyLiveResultsVO> getConstiMap() {
		return constiMap;
	}
	public void setConstiMap(Map<Long, ConstituencyLiveResultsVO> constiMap) {
		this.constiMap = constiMap;
	}
	public Long getUpaWonCount() {
		return upaWonCount;
	}
	public void setUpaWonCount(Long upaWonCount) {
		this.upaWonCount = upaWonCount;
	}
	public Long getNdaWonCount() {
		return ndaWonCount;
	}
	public void setNdaWonCount(Long ndaWonCount) {
		this.ndaWonCount = ndaWonCount;
	}
	public Long getOthersWonCount() {
		return othersWonCount;
	}
	public void setOthersWonCount(Long othersWonCount) {
		this.othersWonCount = othersWonCount;
	}
	public Long getUpaLeadCount() {
		return upaLeadCount;
	}
	public void setUpaLeadCount(Long upaLeadCount) {
		this.upaLeadCount = upaLeadCount;
	}
	public Long getNdaLeadCount() {
		return ndaLeadCount;
	}
	public void setNdaLeadCount(Long ndaLeadCount) {
		this.ndaLeadCount = ndaLeadCount;
	}
	public Long getOthersLeadCount() {
		return othersLeadCount;
	}
	public void setOthersLeadCount(Long othersLeadCount) {
		this.othersLeadCount = othersLeadCount;
	}
	public Long getLeadingPartyId() {
		return leadingPartyId;
	}
	public void setLeadingPartyId(Long leadingPartyId) {
		this.leadingPartyId = leadingPartyId;
	}
	public Long getFollowingPartyId() {
		return followingPartyId;
	}
	public void setFollowingPartyId(Long followingPartyId) {
		this.followingPartyId = followingPartyId;
	}
	public String getLeadingParty() {
		return leadingParty;
	}
	public void setLeadingParty(String leadingParty) {
		this.leadingParty = leadingParty;
	}
	public String getFollowingParty() {
		return followingParty;
	}
	public void setFollowingParty(String followingParty) {
		this.followingParty = followingParty;
	}
	public Long getLeadBy() {
		return leadBy;
	}
	public void setLeadBy(Long leadBy) {
		this.leadBy = leadBy;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public Long getUpaAlliancesCount() {
		return upaAlliancesCount;
	}
	public void setUpaAlliancesCount(Long upaAlliancesCount) {
		this.upaAlliancesCount = upaAlliancesCount;
	}
	public Long getNdaAlliancesCount() {
		return ndaAlliancesCount;
	}
	public void setNdaAlliancesCount(Long ndaAlliancesCount) {
		this.ndaAlliancesCount = ndaAlliancesCount;
	}
	public Long getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(Long othersCount) {
		this.othersCount = othersCount;
	}
	public List<ConstituencyLiveResultsVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<ConstituencyLiveResultsVO> statesList) {
		this.statesList = statesList;
	}
	public List<ConstituencyLiveResultsVO> getConstituenciesList() {
		return constituenciesList;
	}
	public void setConstituenciesList(
			List<ConstituencyLiveResultsVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	
	
	
	
	
}
