package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToursBasicVO {

	private Long id = 0l;
	private String idStr;
	private Long candDtlsId;
	private String name;
	private Long tdpCadreId;
	private Long activityMemberId;
	private String memberShipNo;
	private Long voaterId;
	private String mobileNo;
    private String type;
    private Long locationScopeId;
    private Long locationValue;
    private String image;
    private Long candidateCount = 0l;
    private Long selectedCandCount = 0l;
    private Long totalTour = 0l;
    private Long year = 0l;
    private String month ;
    private String comment ;
    private String filePath;
	private List<ToursBasicVO> subList = null;
	private List<ToursBasicVO> subList2 = null;
	private List<ToursBasicVO> monthList = null;
	private String designation;
	private Long designationId;
	private Long noOfLeaderCnt=0l;
	private Long submitedLeaderCnt=0l;
	private Long notSubmitedLeaserCnt=0l;
	private Long ownTours;
	private Long inchargerTours;
	private Long ownToursCnt=0l;
	private Long inchargerToursCnt=0l;
	private Long totalSubmittedToursCnt=0l;
	private Double averageTours=0.0d;
	private String locationScope;
	
	private Double submitedCandidateTourPer=0.0d;
	private Double notsubmitedCandidateTourPer=0.0d;
	private ToursBasicVO overAllDetailsVO;
	private List<Long> locationValueList = null;
	private Set<Long> locationSet = new HashSet<Long>(0);
	private List<String> remarkList = null;
	private List<String> filePathList = null;
	private Long noOfDistinctTours=0l;
	private String isTourSubmitted;
	
	private Long locationId;
	private String locationName;
	
	private Long targetDays=0l;
	private Long complainceCnt=0l;
	private Long complainceDays=0l;
	private Double complaincePer = 0.0d;
	private Double nonComplaincePer = 0.0d;
	private Long nonComplainceCnt=0l;
	private Set<Long> complaincandidateIdsSet;
	private Set<Long> noNComplaincandidateIdsSet;
	private List<ToursBasicVO> subList3 = new ArrayList<ToursBasicVO>(0);
	
	private Long inchargeDistrictTrDays = 0l;
	private Long inchargeConstituencyTrDays = 0l;
	private Long ownContituencyTrDays = 0l;
	private Long ownDistrictTrDays =0l;
	
	private Long inchargeDistrictComplainceDays = 0l;
	private Long inchargeConstituencyComplainceDays = 0l;
	private Long ownDistrictComplainceDays = 0l;
	private Long ownConstituencyComplainceDays =0l;
	
	private Double inchargeDistrictComplaincePer = 0.0d;
	private Double inchargeConstituencyComplaincePer = 0.0d;
	private Double ownContituencyComplaincePer = 0.0d;
	private Double ownDistrictComplaincePer =0.0d;
	private Double stateTourCategoryComplaincePer = 0.0d;
	private Double anganwadiVisitsComplaincePer = 0.0d;
	private Double midDayMealComplaincePer = 0.0d;
	private Double inchargeParliamentPer = 0.0d;
	private Double ownAreaPer = 0.0d;


	
	private Long govtWorkTrDays=0l;
	private Long govtWorkComplainceDays=0l;
	private Double govtWorkComplaincePer = 0.0d;
	private Long yetToTourCnt = 0l;
	
	private String tourDate;
	private Long constituencyId;
	private String constituencyName;
	private Long tourCategoryId;
	private String tourCategory;
	private Long tourTypeId;
	private String tourType;
	
	private Long addressId;
	private Long categoryTargetDays=0l;
	private Long tourTypeTargetDays=0l;
	
	private Long tourTypeAchievedDays=0l;
	private Long count=0l;
	private String isComplaince;
	private String voterCardNumber;
	private Long orderNo;
	
	private List<IdNameVO> categoryList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> tourTypeList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> distList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> constList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> manTowDivList = new ArrayList<IdNameVO>(0);
	private List<IdNameVO> panWardList = new ArrayList<IdNameVO>(0);
	private Long toursMonthId;
	private Map<Long,String> subMap=null;
	
	public ToursBasicVO() {
		super();
	}
	public ToursBasicVO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getVoaterId() {
		return voaterId;
	}
	public void setVoaterId(Long voaterId) {
		this.voaterId = voaterId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCandidateCount() {
		return candidateCount;
	}
	public void setCandidateCount(Long candidateCount) {
		this.candidateCount = candidateCount;
	}
	public Long getSelectedCandCount() {
		return selectedCandCount;
	}
	public void setSelectedCandCount(Long selectedCandCount) {
		this.selectedCandCount = selectedCandCount;
	}
	public Long getTotalTour() {
		return totalTour;    
	}
	public void setTotalTour(Long totalTour) {
		this.totalTour = totalTour;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getNoOfLeaderCnt() {
		return noOfLeaderCnt;
	}
	public void setNoOfLeaderCnt(Long noOfLeaderCnt) {
		this.noOfLeaderCnt = noOfLeaderCnt;
	}
	public Long getSubmitedLeaderCnt() {
		return submitedLeaderCnt;
	}
	public void setSubmitedLeaderCnt(Long submitedLeaderCnt) {
		this.submitedLeaderCnt = submitedLeaderCnt;
	}
	public Long getNotSubmitedLeaserCnt() {
		return notSubmitedLeaserCnt;
	}
	public void setNotSubmitedLeaserCnt(Long notSubmitedLeaserCnt) {
		this.notSubmitedLeaserCnt = notSubmitedLeaserCnt;
	}
	
	public Long getTotalSubmittedToursCnt() {
		return totalSubmittedToursCnt;
	}
	public void setTotalSubmittedToursCnt(Long totalSubmittedToursCnt) {
		this.totalSubmittedToursCnt = totalSubmittedToursCnt;
	}
	public Double getAverageTours() {
		return averageTours;
	}
	public void setAverageTours(Double averageTours) {
		this.averageTours = averageTours;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	
	public Double getSubmitedCandidateTourPer() {
		return submitedCandidateTourPer;
	}
	public void setSubmitedCandidateTourPer(Double submitedCandidateTourPer) {
		this.submitedCandidateTourPer = submitedCandidateTourPer;
	}
	public Double getNotsubmitedCandidateTourPer() {
		return notsubmitedCandidateTourPer;
	}
	public void setNotsubmitedCandidateTourPer(Double notsubmitedCandidateTourPer) {
		this.notsubmitedCandidateTourPer = notsubmitedCandidateTourPer;
	}
	public ToursBasicVO getOverAllDetailsVO() {
		return overAllDetailsVO;
	}
	public void setOverAllDetailsVO(ToursBasicVO overAllDetailsVO) {
		this.overAllDetailsVO = overAllDetailsVO;
	}
	public List<Long> getLocationValueList() {
		if(locationValueList != null){
			return locationValueList;
		}else{
			locationValueList = new ArrayList<Long>();
			return locationValueList;    
		}
	}
	public List<ToursBasicVO> getSubList() {   
		if(subList != null){
			return subList;
		}else{
			subList = new ArrayList<ToursBasicVO>();
			return subList;   
		}
	}
	public List<ToursBasicVO> getSubList2() {   
		if(subList2 != null){
			return subList2;
		}else{
			subList2 = new ArrayList<ToursBasicVO>();
			return subList2;   
		}
	}
	
	public List<ToursBasicVO> getMonthList() {
		if(monthList != null){
			return monthList;
		}else{
			monthList = new ArrayList<ToursBasicVO>();
			return monthList;
		}
	}
	
	public Set<Long> getLocationSet() {
		return locationSet;
	}
	public void setLocationSet(Set<Long> locationSet) {
		this.locationSet = locationSet;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public List<String> getRemarkList() {
		if(remarkList != null){
			return remarkList;
		}else{
			remarkList = new ArrayList<String>();
			return remarkList;
		}
	}
	public List<String> getFilePathList() {
		if(filePathList != null){
			return filePathList;
		}else{
			filePathList = new ArrayList<String>();
			return filePathList;
		}
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getNoOfDistinctTours() {
		return noOfDistinctTours;
	}
	public void setNoOfDistinctTours(Long noOfDistinctTours) {
		this.noOfDistinctTours = noOfDistinctTours;
	}
	public Long getCandDtlsId() {
		return candDtlsId;
	}
	public void setCandDtlsId(Long candDtlsId) {
		this.candDtlsId = candDtlsId;
	}
	public String getIsTourSubmitted() {
		return isTourSubmitted;
	}
	public void setIsTourSubmitted(String isTourSubmitted) {
		this.isTourSubmitted = isTourSubmitted;
	}
	public Long getOwnTours() {
		return ownTours;
	}
	public void setOwnTours(Long ownTours) {
		this.ownTours = ownTours;
	}
	public Long getInchargerTours() {
		return inchargerTours;
	}
	public void setInchargerTours(Long inchargerTours) {
		this.inchargerTours = inchargerTours;
	}
	public Long getInchargerToursCnt() {
		return inchargerToursCnt;
	}
	public void setInchargerToursCnt(Long inchargerToursCnt) {
		this.inchargerToursCnt = inchargerToursCnt;
	}
	
	public Long getOwnToursCnt() {
		return ownToursCnt;
	}
	public void setOwnToursCnt(Long ownToursCnt) {
		this.ownToursCnt = ownToursCnt;
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
	public Long getTargetDays() {
		return targetDays;
	}
	public void setTargetDays(Long targetDays) {
		this.targetDays = targetDays;
	}
	public Long getComplainceCnt() {
		return complainceCnt;
	}
	public void setComplainceCnt(Long complainceCnt) {
		this.complainceCnt = complainceCnt;
	}
	public Long getNonComplainceCnt() {
		return nonComplainceCnt;
	}
	public void setNonComplainceCnt(Long nonComplainceCnt) {
		this.nonComplainceCnt = nonComplainceCnt;
	}
	public Set<Long> getComplaincandidateIdsSet() {
		return complaincandidateIdsSet;
	}
	public void setComplaincandidateIdsSet(Set<Long> complaincandidateIdsSet) {
		this.complaincandidateIdsSet = complaincandidateIdsSet;
	}
	public Set<Long> getNoNComplaincandidateIdsSet() {
		return noNComplaincandidateIdsSet;
	}
	public void setNoNComplaincandidateIdsSet(Set<Long> noNComplaincandidateIdsSet) {
		this.noNComplaincandidateIdsSet = noNComplaincandidateIdsSet;
	}
	public List<ToursBasicVO> getSubList3() {
		return subList3;
	}
	public void setSubList3(List<ToursBasicVO> subList3) {
		this.subList3 = subList3;
	}
	public Double getComplaincePer() {
		return complaincePer;
	}
	public void setComplaincePer(Double complaincePer) {
		this.complaincePer = complaincePer;
	}
	public Long getComplainceDays() {
		return complainceDays;
	}
	public void setComplainceDays(Long complainceDays) {
		this.complainceDays = complainceDays;
	}
	public Long getInchargeDistrictTrDays() {
		return inchargeDistrictTrDays;
	}
	public void setInchargeDistrictTrDays(Long inchargeDistrictTrDays) {
		this.inchargeDistrictTrDays = inchargeDistrictTrDays;
	}
	public Long getInchargeConstituencyTrDays() {
		return inchargeConstituencyTrDays;
	}
	public void setInchargeConstituencyTrDays(Long inchargeConstituencyTrDays) {
		this.inchargeConstituencyTrDays = inchargeConstituencyTrDays;
	}

	public Long getInchargeDistrictComplainceDays() {
		return inchargeDistrictComplainceDays;
	}
	public void setInchargeDistrictComplainceDays(
			Long inchargeDistrictComplainceDays) {
		this.inchargeDistrictComplainceDays = inchargeDistrictComplainceDays;
	}
	public Long getInchargeConstituencyComplainceDays() {
		return inchargeConstituencyComplainceDays;
	}
	public void setInchargeConstituencyComplainceDays(
			Long inchargeConstituencyComplainceDays) {
		this.inchargeConstituencyComplainceDays = inchargeConstituencyComplainceDays;
	}
	
	public Double getInchargeDistrictComplaincePer() {
		return inchargeDistrictComplaincePer;
	}
	public void setInchargeDistrictComplaincePer(
			Double inchargeDistrictComplaincePer) {
		this.inchargeDistrictComplaincePer = inchargeDistrictComplaincePer;
	}
	public Double getInchargeConstituencyComplaincePer() {
		return inchargeConstituencyComplaincePer;
	}
	public void setInchargeConstituencyComplaincePer(
			Double inchargeConstituencyComplaincePer) {
		this.inchargeConstituencyComplaincePer = inchargeConstituencyComplaincePer;
	}
	
	public Long getOwnContituencyTrDays() {
		return ownContituencyTrDays;
	}
	public void setOwnContituencyTrDays(Long ownContituencyTrDays) {
		this.ownContituencyTrDays = ownContituencyTrDays;
	}
	public Long getOwnDistrictTrDays() {
		return ownDistrictTrDays;
	}
	public void setOwnDistrictTrDays(Long ownDistrictTrDays) {
		this.ownDistrictTrDays = ownDistrictTrDays;
	}
	public Long getOwnDistrictComplainceDays() {
		return ownDistrictComplainceDays;
	}
	public void setOwnDistrictComplainceDays(Long ownDistrictComplainceDays) {
		this.ownDistrictComplainceDays = ownDistrictComplainceDays;
	}
	public Long getOwnConstituencyComplainceDays() {
		return ownConstituencyComplainceDays;
	}
	public void setOwnConstituencyComplainceDays(Long ownConstituencyComplainceDays) {
		this.ownConstituencyComplainceDays = ownConstituencyComplainceDays;
	}
	public Double getOwnContituencyComplaincePer() {
		return ownContituencyComplaincePer;
	}
	public void setOwnContituencyComplaincePer(Double ownContituencyComplaincePer) {
		this.ownContituencyComplaincePer = ownContituencyComplaincePer;
	}
	public Double getOwnDistrictComplaincePer() {
		return ownDistrictComplaincePer;
	}
	public void setOwnDistrictComplaincePer(Double ownDistrictComplaincePer) {
		this.ownDistrictComplaincePer = ownDistrictComplaincePer;
	}
	public Long getGovtWorkTrDays() {
		return govtWorkTrDays;
	}
	public void setGovtWorkTrDays(Long govtWorkTrDays) {
		this.govtWorkTrDays = govtWorkTrDays;
	}
	public Long getGovtWorkComplainceDays() {
		return govtWorkComplainceDays;
	}
	public void setGovtWorkComplainceDays(Long govtWorkComplainceDays) {
		this.govtWorkComplainceDays = govtWorkComplainceDays;
	}
	public Double getGovtWorkComplaincePer() {
		return govtWorkComplaincePer;
	}
	public void setGovtWorkComplaincePer(Double govtWorkComplaincePer) {
		this.govtWorkComplaincePer = govtWorkComplaincePer;
	}
	public Long getYetToTourCnt() {
		return yetToTourCnt;
	}
	public void setYetToTourCnt(Long yetToTourCnt) {
		this.yetToTourCnt = yetToTourCnt;
	}
	public String getTourDate() {
		return tourDate;
	}
	public void setTourDate(String tourDate) {
		this.tourDate = tourDate;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTourCategoryId() {
		return tourCategoryId;
	}
	public void setTourCategoryId(Long tourCategoryId) {
		this.tourCategoryId = tourCategoryId;
	}
	public String getTourCategory() {
		return tourCategory;
	}
	public void setTourCategory(String tourCategory) {
		this.tourCategory = tourCategory;
	}
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	public String getTourType() {
		return tourType;
	}
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public List<IdNameVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<IdNameVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<IdNameVO> getTourTypeList() {
		return tourTypeList;
	}
	public void setTourTypeList(List<IdNameVO> tourTypeList) {
		this.tourTypeList = tourTypeList;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public List<IdNameVO> getDistList() {
		return distList;
	}
	public void setDistList(List<IdNameVO> distList) {
		this.distList = distList;
	}
	public List<IdNameVO> getConstList() {
		return constList;
	}
	public void setConstList(List<IdNameVO> constList) {
		this.constList = constList;
	}
	public List<IdNameVO> getManTowDivList() {
		return manTowDivList;
	}
	public void setManTowDivList(List<IdNameVO> manTowDivList) {
		this.manTowDivList = manTowDivList;
	}
	public List<IdNameVO> getPanWardList() {
		return panWardList;
	}
	public void setPanWardList(List<IdNameVO> panWardList) {
		this.panWardList = panWardList;
	}
	public Long getCategoryTargetDays() {
		return categoryTargetDays;
	}
	public void setCategoryTargetDays(Long categoryTargetDays) {
		this.categoryTargetDays = categoryTargetDays;
	}
	public Long getTourTypeTargetDays() {
		return tourTypeTargetDays;
	}
	public void setTourTypeTargetDays(Long tourTypeTargetDays) {
		this.tourTypeTargetDays = tourTypeTargetDays;
	}
	public Long getTourTypeAchievedDays() {
		return tourTypeAchievedDays;
	}
	public void setTourTypeAchievedDays(Long tourTypeAchievedDays) {
		this.tourTypeAchievedDays = tourTypeAchievedDays;
	}
	public Double getNonComplaincePer() {
		return nonComplaincePer;
	}
	public void setNonComplaincePer(Double nonComplaincePer) {
		this.nonComplaincePer = nonComplaincePer;
	}
	public Long getCount() {
		return count;
	}
	public String getVoterCardNumber() {
		return voterCardNumber;
	}
	public void setVoterCardNumber(String voterCardNumber) {
		this.voterCardNumber = voterCardNumber;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getIsComplaince() {
		return isComplaince;
	}
	public void setIsComplaince(String isComplaince) {
		this.isComplaince = isComplaince;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Double getStateTourCategoryComplaincePer() {
		return stateTourCategoryComplaincePer;
	}
	public void setStateTourCategoryComplaincePer(
			Double stateTourCategoryComplaincePer) {
		this.stateTourCategoryComplaincePer = stateTourCategoryComplaincePer;
	}
	public Long getToursMonthId() {
		return toursMonthId;
	}
	public void setToursMonthId(Long toursMonthId) {
		this.toursMonthId = toursMonthId;
	}
	public Double getAnganwadiVisitsComplaincePer() {
		return anganwadiVisitsComplaincePer;
	}
	public void setAnganwadiVisitsComplaincePer(Double anganwadiVisitsComplaincePer) {
		this.anganwadiVisitsComplaincePer = anganwadiVisitsComplaincePer;
	}
	public Double getMidDayMealComplaincePer() {
		return midDayMealComplaincePer;
	}
	public void setMidDayMealComplaincePer(Double midDayMealComplaincePer) {
		this.midDayMealComplaincePer = midDayMealComplaincePer;
	}
	public Map<Long, String> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, String> subMap) {
		this.subMap = subMap;
	}
	public Double getInchargeParliamentPer() {
		return inchargeParliamentPer;
	}
	public void setInchargeParliamentPer(Double inchargeParliamentPer) {
		this.inchargeParliamentPer = inchargeParliamentPer;
	}
	public Double getOwnAreaPer() {
		return ownAreaPer;
	}
	public void setOwnAreaPer(Double ownAreaPer) {
		this.ownAreaPer = ownAreaPer;
	}
	
}
