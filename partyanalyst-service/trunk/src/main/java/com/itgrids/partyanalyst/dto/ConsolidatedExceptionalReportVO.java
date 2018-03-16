package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConsolidatedExceptionalReportVO {

	private Long locationId;
	private String locationName;
	private Long totalCount = 0l;
	private Long conductedCount = 0l;
	private Double conductedPercentage;
	private Long notConductedCount = 0l;
	private Double notConductedPercentage;
	private Double percentage=0.0;
	private Double percentage1=0.0;
	private AddressVO addressVO;
	private Long mayBeCount = 0l;
	private Double mayBePercentage;
	private Long sortNo = 0l;
	private Long committeeHavingNoSmartPhone=0l;
	private Double constMeetingPerc=0.0;
	private Double mandalMeetingPerc=0.0;
	private Double villageMeetingPerc=0.0;
	private Double mandalCommitteePerc=0.0;
	private Double villageCommPerc=0.0;
	private Double affiliatedCommPerc=0.0;
	private Double boothCommPerc=0.0;
	private Double dalithaTejPerc=0.0;
	private Double kaizalaPerc=0.0;
	private Double trainingCampPerc=0.0;
	private Double alertsPerc=0.0;
	
	private List<ConsolidatedExceptionalReportVO> subList1;
	private List<ConsolidatedExceptionalReportVO> subList2;

	
	
	public Double getConstMeetingPerc() {
		return constMeetingPerc;
	}
	public void setConstMeetingPerc(Double constMeetingPerc) {
		this.constMeetingPerc = constMeetingPerc;
	}
	public Double getMandalMeetingPerc() {
		return mandalMeetingPerc;
	}
	public void setMandalMeetingPerc(Double mandalMeetingPerc) {
		this.mandalMeetingPerc = mandalMeetingPerc;
	}
	public Double getVillageMeetingPerc() {
		return villageMeetingPerc;
	}
	public void setVillageMeetingPerc(Double villageMeetingPerc) {
		this.villageMeetingPerc = villageMeetingPerc;
	}
	public Double getMandalCommitteePerc() {
		return mandalCommitteePerc;
	}
	public void setMandalCommitteePerc(Double mandalCommitteePerc) {
		this.mandalCommitteePerc = mandalCommitteePerc;
	}
	public Double getVillageCommPerc() {
		return villageCommPerc;
	}
	public void setVillageCommPerc(Double villageCommPerc) {
		this.villageCommPerc = villageCommPerc;
	}
	public Double getAffiliatedCommPerc() {
		return affiliatedCommPerc;
	}
	public void setAffiliatedCommPerc(Double affiliatedCommPerc) {
		this.affiliatedCommPerc = affiliatedCommPerc;
	}
	public Double getBoothCommPerc() {
		return boothCommPerc;
	}
	public void setBoothCommPerc(Double boothCommPerc) {
		this.boothCommPerc = boothCommPerc;
	}
	public Double getDalithaTejPerc() {
		return dalithaTejPerc;
	}
	public void setDalithaTejPerc(Double dalithaTejPerc) {
		this.dalithaTejPerc = dalithaTejPerc;
	}
	public Double getKaizalaPerc() {
		return kaizalaPerc;
	}
	public void setKaizalaPerc(Double kaizalaPerc) {
		this.kaizalaPerc = kaizalaPerc;
	}
	public Double getTrainingCampPerc() {
		return trainingCampPerc;
	}
	public void setTrainingCampPerc(Double trainingCampPerc) {
		this.trainingCampPerc = trainingCampPerc;
	}
	public Long getCommitteeHavingNoSmartPhone() {
		return committeeHavingNoSmartPhone;
	}
	public void setCommitteeHavingNoSmartPhone(Long committeeHavingNoSmartPhone) {
		this.committeeHavingNoSmartPhone = committeeHavingNoSmartPhone;
	}
	public Long getSortNo() {
		return sortNo;
	}
	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	public Double getPercentage1() {
		return percentage1;
	}

	public void setPercentage1(Double percentage1) {
		this.percentage1 = percentage1;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getConductedCount() {
		return conductedCount;
	}

	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}

	public Long getNotConductedCount() {
		return notConductedCount;
	}

	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	public List<ConsolidatedExceptionalReportVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<ConsolidatedExceptionalReportVO> subList1) {
		this.subList1 = subList1;
	}

    public List<ConsolidatedExceptionalReportVO> getSubList2() {
		return subList2;
	}

	public void setSubList2(List<ConsolidatedExceptionalReportVO> subList2) {
		this.subList2 = subList2;
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

	public Double getConductedPercentage() {
		return conductedPercentage;
	}

	public void setConductedPercentage(Double conductedPercentage) {
		this.conductedPercentage = conductedPercentage;
	}

	public Double getNotConductedPercentage() {
		return notConductedPercentage;
	}

	public void setNotConductedPercentage(Double notConductedPercentage) {
		this.notConductedPercentage = notConductedPercentage;
	}

	public Long getMayBeCount() {
		return mayBeCount;
	}

	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}

	public Double getMayBePercentage() {
		return mayBePercentage;
	}

	public void setMayBePercentage(Double mayBePercentage) {
		this.mayBePercentage = mayBePercentage;
	}
	public Double getAlertsPerc() {
		return alertsPerc;
	}
	public void setAlertsPerc(Double alertsPerc) {
		this.alertsPerc = alertsPerc;
	}
	
	
}
