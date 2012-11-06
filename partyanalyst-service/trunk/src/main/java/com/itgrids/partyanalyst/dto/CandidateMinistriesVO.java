package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CandidateMinistriesVO implements Serializable {

	private static final long serialVersionUID = -2426782207984592621L;
	
	private Long candidateId;
	private String candidateName;
	private Long partyId;
	private String partyName;
	private List<ElectionGoverningBodyVO> ministries;
	private List<String> ministryTypes;
	private Date fromDate;
	private Date toDate;
	private int noOfMinistries;
	private Boolean isChiefMinister;
	private Boolean isDeputyChiefMinister;
	private Boolean isPrimeMinister;
	private Boolean hasCabinetMinisters;
	private Boolean hasMSIC;
	private Boolean hasMS;
	
	private Boolean expireOneMinistry;
	private Boolean expisreAllMinistry;
	private int noOfMinistriesExpired;
	private int noOfMinistriesNotExpired;
	
	private String candidateConstiuencyName;
	private String candidateStateName;
	private String candidateDistrictName;
	private Map<String,Long> statesMap;
	
	private Long candidateConstiuencyId;
	private Long candidateStateId;
	private Long candidateDistrictId;
	
	private List<StatePageVO> statesList;
	private List<ConstituencyInfoVO> districtList;
	private Map<String,DistrictWisePartyResultVO> districtConstituencyCountMap;
	private Map<String , StateLevelPartyReportVO> stateConstituencyCountMap;
	
	
	
	public Boolean getIsDeputyChiefMinister() {
		return isDeputyChiefMinister;
	}
	public void setIsDeputyChiefMinister(Boolean isDeputyChiefMinister) {
		this.isDeputyChiefMinister = isDeputyChiefMinister;
	}
	public Boolean getIsPrimeMinister() {
		return isPrimeMinister;
	}
	public void setIsPrimeMinister(Boolean isPrimeMinister) {
		this.isPrimeMinister = isPrimeMinister;
	}
	public Boolean getHasCabinetMinisters() {
		return hasCabinetMinisters;
	}
	public void setHasCabinetMinisters(Boolean hasCabinetMinisters) {
		this.hasCabinetMinisters = hasCabinetMinisters;
	}
	public Boolean getHasMSIC() {
		return hasMSIC;
	}
	public void setHasMSIC(Boolean hasMSIC) {
		this.hasMSIC = hasMSIC;
	}
	public Boolean getHasMS() {
		return hasMS;
	}
	public void setHasMS(Boolean hasMS) {
		this.hasMS = hasMS;
	}
	public Boolean getIsChiefMinister() {
		return isChiefMinister;
	}
	public void setIsChiefMinister(Boolean isChiefMinister) {
		this.isChiefMinister = isChiefMinister;
	}
	public List<String> getMinistryTypes() {
		return ministryTypes;
	}
	public void setMinistryTypes(List<String> ministryTypes) {
		this.ministryTypes = ministryTypes;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getNoOfMinistries() {
		return noOfMinistries;
	}
	public void setNoOfMinistries(int noOfMinistries) {
		this.noOfMinistries = noOfMinistries;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public Long getCandidateId() {
		return candidateId;
		
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<ElectionGoverningBodyVO> getMinistries() {
		return ministries;
	}
	public void setMinistries(List<ElectionGoverningBodyVO> ministries) {
		this.ministries = ministries;
	}
	public Boolean getExpisreAllMinistry() {
		return expisreAllMinistry;
	}
	public void setExpisreAllMinistry(Boolean expisreAllMinistry) {
		this.expisreAllMinistry = expisreAllMinistry;
	}
	public Boolean getExpireOneMinistry() {
		return expireOneMinistry;
	}
	public void setExpireOneMinistry(Boolean expireOneMinistry) {
		this.expireOneMinistry = expireOneMinistry;
	}
	public int getNoOfMinistriesExpired() {
		return noOfMinistriesExpired;
	}
	public void setNoOfMinistriesExpired(int noOfMinistriesExpired) {
		this.noOfMinistriesExpired = noOfMinistriesExpired;
	}
	public int getNoOfMinistriesNotExpired() {
		return noOfMinistriesNotExpired;
	}
	public void setNoOfMinistriesNotExpired(int noOfMinistriesNotExpired) {
		this.noOfMinistriesNotExpired = noOfMinistriesNotExpired;
	}
	public String getCandidateConstiuencyName() {
		return candidateConstiuencyName;
	}
	public void setCandidateConstiuencyName(String candidateConstiuencyName) {
		this.candidateConstiuencyName = candidateConstiuencyName;
	}
	public String getCandidateStateName() {
		return candidateStateName;
	}
	public void setCandidateStateName(String candidateStateName) {
		this.candidateStateName = candidateStateName;
	}
	public String getCandidateDistrictName() {
		return candidateDistrictName;
	}
	public void setCandidateDistrictName(String candidateDistrictName) {
		this.candidateDistrictName = candidateDistrictName;
	}
	public Map<String,Long> getStatesMap() {
		return statesMap;
	}
	public void setStatesMap(Map<String,Long> statesMap) {
		this.statesMap = statesMap;
	}
	
	public Long getCandidateConstiuencyId() {
		return candidateConstiuencyId;
	}
	public void setCandidateConstiuencyId(Long candidateConstiuencyId) {
		this.candidateConstiuencyId = candidateConstiuencyId;
	}
	public Long getCandidateStateId() {
		return candidateStateId;
	}
	public void setCandidateStateId(Long candidateStateId) {
		this.candidateStateId = candidateStateId;
	}
	public Long getCandidateDistrictId() {
		return candidateDistrictId;
	}
	public void setCandidateDistrictId(Long candidateDistrictId) {
		this.candidateDistrictId = candidateDistrictId;
	}
	public List<StatePageVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<StatePageVO> statesList) {
		this.statesList = statesList;
	}
	public List<ConstituencyInfoVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<ConstituencyInfoVO> districtList) {
		this.districtList = districtList;
	}
	

	public Map<String, DistrictWisePartyResultVO> getDistrictConstituencyCountMap() {
		return districtConstituencyCountMap;
	}
	public void setDistrictConstituencyCountMap(
			Map<String, DistrictWisePartyResultVO> districtConstituencyCountMap) {
		this.districtConstituencyCountMap = districtConstituencyCountMap;
	}
	public Map<String, StateLevelPartyReportVO> getStateConstituencyCountMap() {
		return stateConstituencyCountMap;
	}
	public void setStateConstituencyCountMap(
			Map<String, StateLevelPartyReportVO> stateConstituencyCountMap) {
		this.stateConstituencyCountMap = stateConstituencyCountMap;
	}
	
	
}
