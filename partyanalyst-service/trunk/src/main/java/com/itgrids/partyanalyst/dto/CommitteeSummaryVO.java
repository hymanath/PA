package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
public class CommitteeSummaryVO implements Serializable{

	private Long mainComittees = 0l;
	private Long mainComitteesConformed = 0l;
	private Long mainComitteesNotConformed = 0l;
	
	private String affilatedCommitteeName;
	private Long affComitteesConformed = 0l;
	private Long affComitteesNotConformed = 0l;
	private Long totalAffilatedCommittees = 0l;
	
	private Long startedCount = 0l;
	private Long affilatedStartedCount = 0l;
	private Long mainCommitteId;
	private Long affilatedCommitteId;
	
	private Long districtId;
	private String districtName;
	private Long totalCommittees;
	private Long membersCount;
	private Long afflMembersCount;
	private List<CommitteeSummaryVO> districtWiseList;
	private Long mainStarted;
	private Long mainCompleted;
	private Long afflStarted;
	private Long afflCompleted;
	
	private CommitteeSummaryVO townMandalDivisionVO;
	private CommitteeSummaryVO villageWardVO;
	private CommitteeSummaryVO districtCommVO;
	private CommitteeSummaryVO stateCommVO;
	private String startPerc;
	private Long constiId;
	private String name;
	
	private List<CommitteeSummaryVO> resultList;
	private Long basicCommitteeTypeId;
	private String basicCommitteeName;
	private Long electrolsCount;
	private Long mandalId;
	private Long panchayatId;
	private Long constiNo;
	private Long locationId;
	private String locationName;
	private List<CommitteeSummaryVO> locationsList,localBodiesList, mandalsList, divisionList;
	private String accessState;	
	
	private CadreIVRVO cadreIVRVO;
	private Long youvathaStarted;
	private Long mahilaStarted;
	private Long rythuStarted;
	private Long tradeStarted;
	private Long bcCellStarted;
	private Long scCellStarted;
	private Long stCellStarted;
	private Long minorityStarted;
	private Long CristianStarted;
	private Long tnsfStarted;
	private Long tntucStarted;
	private Long tsnvStarted;
	private Long legalCellStarted;
	private Long doctorStarted;
	private Long kalluGeethaStarted;
	private Long chenethaStarted;
	private Long rakshaVedikaStarted;
	private Long tnusStarted;
	private Long commercialStarted;
	private Long culturalStarted;	
	private Long othersStarted;
	
	private Long youvathaCmpltd;
	private Long mahilaCmpltd;
	private Long rythuCmpltd;
	private Long tradeCmpltd;
	private Long bcCellCmpltd;
	private Long scCellCmpltd;
	private Long stCellCmpltd;
	private Long minorityCmpltd;
	private Long CristianCmpltd;
	private Long tnsfCmpltd;
	private Long tntucCmpltd;
	private Long tsnvCmpltd;
	private Long legalCmpltd;
	private Long doctorCmpltd;
	private Long kalluGeethaCmpltd;
	private Long chenethaCmpltd;
	private Long rakshaVedikaCmpltd;
	private Long tnusCmpltd;
	private Long commercialCmpltd;
	private Long culturalCmpltd;	
	private Long othersCmpltd;
	
	private Boolean affliatedCommitteesExist;
	private Boolean mainCommitteesExist;
	
	public Long getAfflMembersCount() {
		return afflMembersCount;
	}
	public void setAfflMembersCount(Long afflMembersCount) {
		this.afflMembersCount = afflMembersCount;
	}
	public Long getTradeStarted() {
		return tradeStarted;
	}
	public Long getBcCellStarted() {
		return bcCellStarted;
	}
	public Long getScCellStarted() {
		return scCellStarted;
	}
	public Long getStCellStarted() {
		return stCellStarted;
	}
	public Long getMinorityStarted() {
		return minorityStarted;
	}
	public Long getCristianStarted() {
		return CristianStarted;
	}
	public Long getTnsfStarted() {
		return tnsfStarted;
	}
	public Long getTntucStarted() {
		return tntucStarted;
	}
	public Long getTsnvStarted() {
		return tsnvStarted;
	}
	public Long getLegalCellStarted() {
		return legalCellStarted;
	}
	public Long getDoctorStarted() {
		return doctorStarted;
	}
	public Long getKalluGeethaStarted() {
		return kalluGeethaStarted;
	}
	public Long getChenethaStarted() {
		return chenethaStarted;
	}
	public Long getRakshaVedikaStarted() {
		return rakshaVedikaStarted;
	}
	public Long getTnusStarted() {
		return tnusStarted;
	}
	public Long getCommercialStarted() {
		return commercialStarted;
	}
	public Long getCulturalStarted() {
		return culturalStarted;
	}
	public Long getTradeCmpltd() {
		return tradeCmpltd;
	}
	public Long getBcCellCmpltd() {
		return bcCellCmpltd;
	}
	public Long getScCellCmpltd() {
		return scCellCmpltd;
	}
	public Long getStCellCmpltd() {
		return stCellCmpltd;
	}
	public Long getMinorityCmpltd() {
		return minorityCmpltd;
	}
	public Long getCristianCmpltd() {
		return CristianCmpltd;
	}
	public Long getTnsfCmpltd() {
		return tnsfCmpltd;
	}
	public Long getTntucCmpltd() {
		return tntucCmpltd;
	}
	public Long getTsnvCmpltd() {
		return tsnvCmpltd;
	}
	public Long getLegalCmpltd() {
		return legalCmpltd;
	}
	public Long getDoctorCmpltd() {
		return doctorCmpltd;
	}
	public Long getKalluGeethaCmpltd() {
		return kalluGeethaCmpltd;
	}
	public Long getChenethaCmpltd() {
		return chenethaCmpltd;
	}
	public Long getRakshaVedikaCmpltd() {
		return rakshaVedikaCmpltd;
	}
	public Long getTnusCmpltd() {
		return tnusCmpltd;
	}
	public Long getCommercialCmpltd() {
		return commercialCmpltd;
	}
	public Long getCulturalCmpltd() {
		return culturalCmpltd;
	}
	public void setTradeStarted(Long tradeStarted) {
		this.tradeStarted = tradeStarted;
	}
	public void setBcCellStarted(Long bcCellStarted) {
		this.bcCellStarted = bcCellStarted;
	}
	public void setScCellStarted(Long scCellStarted) {
		this.scCellStarted = scCellStarted;
	}
	public void setStCellStarted(Long stCellStarted) {
		this.stCellStarted = stCellStarted;
	}
	public void setMinorityStarted(Long minorityStarted) {
		this.minorityStarted = minorityStarted;
	}
	public void setCristianStarted(Long cristianStarted) {
		CristianStarted = cristianStarted;
	}
	public void setTnsfStarted(Long tnsfStarted) {
		this.tnsfStarted = tnsfStarted;
	}
	public void setTntucStarted(Long tntucStarted) {
		this.tntucStarted = tntucStarted;
	}
	public void setTsnvStarted(Long tsnvStarted) {
		this.tsnvStarted = tsnvStarted;
	}
	public void setLegalCellStarted(Long legalCellStarted) {
		this.legalCellStarted = legalCellStarted;
	}
	public void setDoctorStarted(Long doctorStarted) {
		this.doctorStarted = doctorStarted;
	}
	public void setKalluGeethaStarted(Long kalluGeethaStarted) {
		this.kalluGeethaStarted = kalluGeethaStarted;
	}
	public void setChenethaStarted(Long chenethaStarted) {
		this.chenethaStarted = chenethaStarted;
	}
	public void setRakshaVedikaStarted(Long rakshaVedikaStarted) {
		this.rakshaVedikaStarted = rakshaVedikaStarted;
	}
	public void setTnusStarted(Long tnusStarted) {
		this.tnusStarted = tnusStarted;
	}
	public void setCommercialStarted(Long commercialStarted) {
		this.commercialStarted = commercialStarted;
	}
	public void setCulturalStarted(Long culturalStarted) {
		this.culturalStarted = culturalStarted;
	}
	public void setTradeCmpltd(Long tradeCmpltd) {
		this.tradeCmpltd = tradeCmpltd;
	}
	public void setBcCellCmpltd(Long bcCellCmpltd) {
		this.bcCellCmpltd = bcCellCmpltd;
	}
	public void setScCellCmpltd(Long scCellCmpltd) {
		this.scCellCmpltd = scCellCmpltd;
	}
	public void setStCellCmpltd(Long stCellCmpltd) {
		this.stCellCmpltd = stCellCmpltd;
	}
	public void setMinorityCmpltd(Long minorityCmpltd) {
		this.minorityCmpltd = minorityCmpltd;
	}
	public void setCristianCmpltd(Long cristianCmpltd) {
		CristianCmpltd = cristianCmpltd;
	}
	public void setTnsfCmpltd(Long tnsfCmpltd) {
		this.tnsfCmpltd = tnsfCmpltd;
	}
	public void setTntucCmpltd(Long tntucCmpltd) {
		this.tntucCmpltd = tntucCmpltd;
	}
	public void setTsnvCmpltd(Long tsnvCmpltd) {
		this.tsnvCmpltd = tsnvCmpltd;
	}
	public void setLegalCmpltd(Long legalCmpltd) {
		this.legalCmpltd = legalCmpltd;
	}
	public void setDoctorCmpltd(Long doctorCmpltd) {
		this.doctorCmpltd = doctorCmpltd;
	}
	public void setKalluGeethaCmpltd(Long kalluGeethaCmpltd) {
		this.kalluGeethaCmpltd = kalluGeethaCmpltd;
	}
	public void setChenethaCmpltd(Long chenethaCmpltd) {
		this.chenethaCmpltd = chenethaCmpltd;
	}
	public void setRakshaVedikaCmpltd(Long rakshaVedikaCmpltd) {
		this.rakshaVedikaCmpltd = rakshaVedikaCmpltd;
	}
	public void setTnusCmpltd(Long tnusCmpltd) {
		this.tnusCmpltd = tnusCmpltd;
	}
	public void setCommercialCmpltd(Long commercialCmpltd) {
		this.commercialCmpltd = commercialCmpltd;
	}
	public void setCulturalCmpltd(Long culturalCmpltd) {
		this.culturalCmpltd = culturalCmpltd;
	}
	public Long getOthersStarted() {
		return othersStarted;
	}
	public Long getOthersCmpltd() {
		return othersCmpltd;
	}
	public void setOthersStarted(Long othersStarted) {
		this.othersStarted = othersStarted;
	}
	public void setOthersCmpltd(Long othersCmpltd) {
		this.othersCmpltd = othersCmpltd;
	}
	public Long getYouvathaStarted() {
		return youvathaStarted;
	}
	public Long getMahilaStarted() {
		return mahilaStarted;
	}
	public Long getRythuStarted() {
		return rythuStarted;
	}
	public Long getYouvathaCmpltd() {
		return youvathaCmpltd;
	}
	public Long getMahilaCmpltd() {
		return mahilaCmpltd;
	}
	public Long getRythuCmpltd() {
		return rythuCmpltd;
	}
	public void setYouvathaStarted(Long youvathaStarted) {
		this.youvathaStarted = youvathaStarted;
	}
	public void setMahilaStarted(Long mahilaStarted) {
		this.mahilaStarted = mahilaStarted;
	}
	public void setRythuStarted(Long rythuStarted) {
		this.rythuStarted = rythuStarted;
	}
	public void setYouvathaCmpltd(Long youvathaCmpltd) {
		this.youvathaCmpltd = youvathaCmpltd;
	}
	public void setMahilaCmpltd(Long mahilaCmpltd) {
		this.mahilaCmpltd = mahilaCmpltd;
	}
	public void setRythuCmpltd(Long rythuCmpltd) {
		this.rythuCmpltd = rythuCmpltd;
	}
	public String getAccessState() {
		return accessState;
	}
	public void setAccessState(String accessState) {
		this.accessState = accessState;
	}
	public List<CommitteeSummaryVO> getLocalBodiesList() {
		return localBodiesList;
	}
	public void setLocalBodiesList(List<CommitteeSummaryVO> localBodiesList) {
		this.localBodiesList = localBodiesList;
	}
	public List<CommitteeSummaryVO> getMandalsList() {
		return mandalsList;
	}
	public void setMandalsList(List<CommitteeSummaryVO> mandalsList) {
		this.mandalsList = mandalsList;
	}
	public List<CommitteeSummaryVO> getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(List<CommitteeSummaryVO> divisionList) {
		this.divisionList = divisionList;
	}
	public List<CommitteeSummaryVO> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<CommitteeSummaryVO> locationsList) {
		this.locationsList = locationsList;
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
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public List<CommitteeSummaryVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<CommitteeSummaryVO> resultList) {
		this.resultList = resultList;
	}
	public Long getBasicCommitteeTypeId() {
		return basicCommitteeTypeId;
	}
	public void setBasicCommitteeTypeId(Long basicCommitteeTypeId) {
		this.basicCommitteeTypeId = basicCommitteeTypeId;
	}
	
	public String getBasicCommitteeName() {
		return basicCommitteeName;
	}
	public void setBasicCommitteeName(String basicCommitteeName) {
		this.basicCommitteeName = basicCommitteeName;
	}
	public Long getElectrolsCount() {
		return electrolsCount;
	}
	public void setElectrolsCount(Long electrolsCount) {
		this.electrolsCount = electrolsCount;
	}
	public String getStartPerc() {
		return startPerc;
	}
	public void setStartPerc(String startPerc) {
		this.startPerc = startPerc;
	}
	public Long getMainStarted() {
		return mainStarted;
	}
	public void setMainStarted(Long mainStarted) {
		this.mainStarted = mainStarted;
	}
	public Long getMainCompleted() {
		return mainCompleted;
	}
	public void setMainCompleted(Long mainCompleted) {
		this.mainCompleted = mainCompleted;
	}
	public Long getAfflStarted() {
		return afflStarted;
	}
	public void setAfflStarted(Long afflStarted) {
		this.afflStarted = afflStarted;
	}
	public Long getAfflCompleted() {
		return afflCompleted;
	}
	public void setAfflCompleted(Long afflCompleted) {
		this.afflCompleted = afflCompleted;
	}
	public CommitteeSummaryVO getTownMandalDivisionVO() {
		return townMandalDivisionVO;
	}
	public void setTownMandalDivisionVO(CommitteeSummaryVO townMandalDivisionVO) {
		this.townMandalDivisionVO = townMandalDivisionVO;
	}
	public CommitteeSummaryVO getVillageWardVO() {
		return villageWardVO;
	}
	public void setVillageWardVO(CommitteeSummaryVO villageWardVO) {
		this.villageWardVO = villageWardVO;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getTotalCommittees() {
		return totalCommittees;
	}
	public void setTotalCommittees(Long totalCommittees) {
		this.totalCommittees = totalCommittees;
	}
	public Long getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Long membersCount) {
		this.membersCount = membersCount;
	}
	public List<CommitteeSummaryVO> getDistrictWiseList() {
		return districtWiseList;
	}
	public void setDistrictWiseList(List<CommitteeSummaryVO> districtWiseList) {
		this.districtWiseList = districtWiseList;
	}
	public Long getMainCommitteId() {
		return mainCommitteId;
	}
	public void setMainCommitteId(Long mainCommitteId) {
		this.mainCommitteId = mainCommitteId;
	}
	public Long getAffilatedCommitteId() {
		return affilatedCommitteId;
	}
	public void setAffilatedCommitteId(Long affilatedCommitteId) {
		this.affilatedCommitteId = affilatedCommitteId;
	}
	public Long getMainComittees() {
		return mainComittees;
	}
	public void setMainComittees(Long mainComittees) {
		this.mainComittees = mainComittees;
	}
	public Long getMainComitteesConformed() {
		return mainComitteesConformed;
	}
	public void setMainComitteesConformed(Long mainComitteesConformed) {
		this.mainComitteesConformed = mainComitteesConformed;
	}
	public Long getMainComitteesNotConformed() {
		return mainComitteesNotConformed;
	}
	public void setMainComitteesNotConformed(Long mainComitteesNotConformed) {
		this.mainComitteesNotConformed = mainComitteesNotConformed;
	}
	public String getAffilatedCommitteeName() {
		return affilatedCommitteeName;
	}
	public void setAffilatedCommitteeName(String affilatedCommitteeName) {
		this.affilatedCommitteeName = affilatedCommitteeName;
	}
	public Long getAffComitteesConformed() {
		return affComitteesConformed;
	}
	public void setAffComitteesConformed(Long affComitteesConformed) {
		this.affComitteesConformed = affComitteesConformed;
	}
	public Long getAffComitteesNotConformed() {
		return affComitteesNotConformed;
	}
	public void setAffComitteesNotConformed(Long affComitteesNotConformed) {
		this.affComitteesNotConformed = affComitteesNotConformed;
	}
	public Long getTotalAffilatedCommittees() {
		return totalAffilatedCommittees;
	}
	public void setTotalAffilatedCommittees(Long totalAffilatedCommittees) {
		this.totalAffilatedCommittees = totalAffilatedCommittees;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getAffilatedStartedCount() {
		return affilatedStartedCount;
	}
	public void setAffilatedStartedCount(Long affilatedStartedCount) {
		this.affilatedStartedCount = affilatedStartedCount;
	}
	public Long getConstiId() {
		return constiId;
	}
	public void setConstiId(Long constiId) {
		this.constiId = constiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getConstiNo() {
		return constiNo;
	}
	public void setConstiNo(Long constiNo) {
		this.constiNo = constiNo;
	}
	public CadreIVRVO getCadreIVRVO() {
		return cadreIVRVO;
	}
	public void setCadreIVRVO(CadreIVRVO cadreIVRVO) {
		this.cadreIVRVO = cadreIVRVO;
	}
	public CommitteeSummaryVO getDistrictCommVO() {
		return districtCommVO;
	}
	public void setDistrictCommVO(CommitteeSummaryVO districtCommVO) {
		this.districtCommVO = districtCommVO;
	}
	public CommitteeSummaryVO getStateCommVO() {
		return stateCommVO;
	}
	public void setStateCommVO(CommitteeSummaryVO stateCommVO) {
		this.stateCommVO = stateCommVO;
	}
	public Boolean getAffliatedCommitteesExist() {
		return affliatedCommitteesExist;
	}
	public void setAffliatedCommitteesExist(Boolean affliatedCommitteesExist) {
		this.affliatedCommitteesExist = affliatedCommitteesExist;
	}
	public Boolean getMainCommitteesExist() {
		return mainCommitteesExist;
	}
	public void setMainCommitteesExist(Boolean mainCommitteesExist) {
		this.mainCommitteesExist = mainCommitteesExist;
	}
	

}
