package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Swadhin
 *
 */
/**
 * @author Swadhin
 *
 */
public class IdNameVO implements Serializable,Comparator<IdNameVO>{
	private Long id;
	private String name;
	private Long districtid;
	private Long availableCount=0l;
	private Long actualCount=0l;
	private Long orderId;
	private String percentage;
	private Long count = 0l;
	private String dateStr;
	private List<String> subList = new ArrayList<String>();
	private List<IdNameVO> idnameList = new ArrayList<IdNameVO>();
	private List<IdNameVO> petitionerList = new ArrayList<IdNameVO>();
	private String mobileNo;
	private String status;
	private String publicRepr;
	private String partyPos;
	private Long applicationStatusId;
	private String applicationStatus;
	private List<IdNameVO> subList1 = new ArrayList<IdNameVO>();
	private Long wishCount=0l;
	private String wish;
	private Long applicationsCount=0L;
	private Long memberId;
	private String districtName;
	private Long lessThan9 =0l;
	private Long between9To1030 =0l;
	private Long between1030To1130=0l;
	private Long between1130To13=0l;
	private Long greaterThan13=0l;
	private Long cadreId;
	private Long totalCount = 0l;
	private Long approvedCount = 0l;
	private Long rejectedCount = 0l;
	private Long pendingCount = 0l;
	private Long tabUserId;
	private String tabUserName;
	private String gender;
	private String image;
	private String voterImage;
	private Long statusId = 0l;
	private String userStatus;

	private Long constitunecyId;
	private Long constituencyNo;
	private Long districtId;
	private Long cadreUserId;
	private Long distId;
	private String constituencyName;
	private String imageUrl;
	private String relativeName;
	private String membershipNo;
	private Long departmentId;
	private Long boardId;
	private String deptName;
	private String boardName;
	private Long postionId;
	private String positionName;
	private List<String> sessionList;
	private List<String> sessionLevel;
	
	private String isInvitee;
	private String isAccess;
	private List<String> attendedTimeList;
	private List<String> attendedDateList;
	private String remark;
	
	private List<IdNameVO> publicRepDesgList;
	private List<IdNameVO> committeeDesgList;
	 
	private String time;
	private List<SessionVO> sessionsList = new ArrayList<SessionVO>(0);
	
	private Long alertId;
	private String mainTitle;
	private String title;
	private String description;
	private String assignedOfficerStr;
	private String assignedByOfficerStr;
	private String designation;
	private String dueDateStr;
	private List<AlertTrackingVO> commentList = new ArrayList<AlertTrackingVO>(0);
	private List<Long> locationScopeIds = new ArrayList<Long>();
	private Long categoryId=0L;
	private String color;
	private Long childLevelId=0l;
	private String callerName;
	private String userType;
	//private String relativeName;
	private Long age;
	private String dateOfbirth;
	//private String gender;
	private String houseNo;
	private String adharNo;
	//private String mobileNo;
	private String meekosamOccupation;
	private String meekosamCasteCategory;
	private String meekosamArgeeCategory;
	private String meekosamAnnualIncome;
	private String voterCardNo;
	private String stateName;
	//private String districtName;
	private String tehsilName;
	private String panchayatName;
	private String municipalityName;
	private Long inviteeAttnd;
	private Long nonInviteeAttnd;
	
	
	public Long getInviteeAttnd() {
		return inviteeAttnd;
	}

	public void setInviteeAttnd(Long inviteeAttnd) {
		this.inviteeAttnd = inviteeAttnd;
	}

	public Long getNonInviteeAttnd() {
		return nonInviteeAttnd;
	}

	public void setNonInviteeAttnd(Long nonInviteeAttnd) {
		this.nonInviteeAttnd = nonInviteeAttnd;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<AlertTrackingVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<AlertTrackingVO> commentList) {
		this.commentList = commentList;
	}

	public String getDueDateStr() {
		return dueDateStr;
	}

	public void setDueDateStr(String dueDateStr) {
		this.dueDateStr = dueDateStr;
	}

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public String getAssignedOfficerStr() {
		return assignedOfficerStr;
	}

	public void setAssignedOfficerStr(String assignedOfficerStr) {
		this.assignedOfficerStr = assignedOfficerStr;
	}

	public String getAssignedByOfficerStr() {
		return assignedByOfficerStr;
	}

	public void setAssignedByOfficerStr(String assignedByOfficerStr) {
		this.assignedByOfficerStr = assignedByOfficerStr;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SessionVO> getSessionsList() {
		return sessionsList;
	}

	public void setSessionsList(List<SessionVO> sessionsList) {
		this.sessionsList = sessionsList;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getConstituencyNo() {
		return constituencyNo;
	}

	public void setConstituencyNo(Long constituencyNo) {
		this.constituencyNo = constituencyNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int compare(IdNameVO o2, IdNameVO o1) {
		// TODO Auto-generated method stub
		return o2.getOrderId().compareTo(o1.getOrderId());
		
	}

	public IdNameVO(){}
	
	
	public String getIsInvitee() {
		return isInvitee;
	}

	public void setIsInvitee(String isInvitee) {
		this.isInvitee = isInvitee;
	}

	public IdNameVO(Long id,String name){
		this.id=id;
		this.name=name;
	}
	
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getActualCount() {
		return actualCount;
	}

	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
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

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public List<String> getSubList() {
		return subList;
	}

	public void setSubList(List<String> subList) {
		this.subList = subList;
	}

	public List<IdNameVO> getIdnameList() {
		return idnameList;
	}

	public void setIdnameList(List<IdNameVO> idnameList) {
		this.idnameList = idnameList;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPublicRepr() {
		return publicRepr;
	}

	public void setPublicRepr(String publicRepr) {
		this.publicRepr = publicRepr;
	}

	public String getPartyPos() {
		return partyPos;
	}

	public void setPartyPos(String partyPos) {
		this.partyPos = partyPos;
	}

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public List<IdNameVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<IdNameVO> subList1) {
		this.subList1 = subList1;
	}

	public Long getWishCount() {
		return wishCount;
	}

	public void setWishCount(Long wishCount) {
		this.wishCount = wishCount;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public Long getApplicationsCount() {
		return applicationsCount;
	}

	public void setApplicationsCount(Long applicationsCount) {
		this.applicationsCount = applicationsCount;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getLessThan9() {
		return lessThan9;
	}

	public void setLessThan9(Long lessThan9) {
		this.lessThan9 = lessThan9;
	}

	

	public Long getBetween9To1030() {
		return between9To1030;
	}

	public void setBetween9To1030(Long between9To1030) {
		this.between9To1030 = between9To1030;
	}

	public Long getBetween1030To1130() {
		return between1030To1130;
	}

	public void setBetween1030To1130(Long between1030To1130) {
		this.between1030To1130 = between1030To1130;
	}

	public Long getBetween1130To13() {
		return between1130To13;
	}

	public void setBetween1130To13(Long between1130To13) {
		this.between1130To13 = between1130To13;
	}

	public Long getGreaterThan13() {
		return greaterThan13;
	}

	public void setGreaterThan13(Long greaterThan13) {
		this.greaterThan13 = greaterThan13;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getApprovedCount() {
		return approvedCount;
	}

	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}

	public Long getRejectedCount() {
		return rejectedCount;
	}

	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}

	public Long getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}

	public Long getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}

	public String getTabUserName() {
		return tabUserName;
	}

	public void setTabUserName(String tabUserName) {
		this.tabUserName = tabUserName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVoterImage() {
		return voterImage;
	}

	public void setVoterImage(String voterImage) {
		this.voterImage = voterImage;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getConstitunecyId() {
		return constitunecyId;
	}

	public void setConstitunecyId(Long constitunecyId) {
		this.constitunecyId = constitunecyId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	public Long getCadreUserId() {
		return cadreUserId;
	}

	public void setCadreUserId(Long cadreUserId) {
		this.cadreUserId = cadreUserId;
	}

	
	public Long getDistId() {
		return distId;
	}

	public void setDistId(Object object) {
		this.distId = (Long) object;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Long getPostionId() {
		return postionId;
	}

	public void setPostionId(Long postionId) {
		this.postionId = postionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public List<String> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<String> sessionList) {
		this.sessionList = sessionList;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public List<String> getAttendedTimeList() {
		return attendedTimeList;
	}

	public void setAttendedTimeList(List<String> attendedTimeList) {
		this.attendedTimeList = attendedTimeList;
	}

	public List<String> getAttendedDateList() {
		return attendedDateList;
	}

	public void setAttendedDateList(List<String> attendedDateList) {
		this.attendedDateList = attendedDateList;
	}

	public List<String> getSessionLevel() {
		return sessionLevel;
	}

	public void setSessionLevel(List<String> sessionLevel) {
		this.sessionLevel = sessionLevel;
	}

	public List<IdNameVO> getPublicRepDesgList() {
		return publicRepDesgList;
	}

	public void setPublicRepDesgList(List<IdNameVO> publicRepDesgList) {
		this.publicRepDesgList = publicRepDesgList;
	}

	public List<IdNameVO> getCommitteeDesgList() {
		return committeeDesgList;
	}

	public void setCommitteeDesgList(List<IdNameVO> committeeDesgList) {
		this.committeeDesgList = committeeDesgList;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getIsAccess() {
		return isAccess;
	}

	public void setIsAccess(String isAccess) {
		this.isAccess = isAccess;
	}
	public List<Long> getLocationScopeIds() {
		return locationScopeIds;
	}

	public void setLocationScopeIds(List<Long> locationScopeIds) {
		this.locationScopeIds = locationScopeIds;
	}
	public Long getChildLevelId() {
		return childLevelId;
	}

	public void setChildLevelId(Long childLevelId) {
		this.childLevelId = childLevelId;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getDateOfbirth() {
		return dateOfbirth;
	}

	public void setDateOfbirth(String dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getMeekosamOccupation() {
		return meekosamOccupation;
	}

	public void setMeekosamOccupation(String meekosamOccupation) {
		this.meekosamOccupation = meekosamOccupation;
	}

	public String getMeekosamCasteCategory() {
		return meekosamCasteCategory;
	}

	public void setMeekosamCasteCategory(String meekosamCasteCategory) {
		this.meekosamCasteCategory = meekosamCasteCategory;
	}

	public String getMeekosamArgeeCategory() {
		return meekosamArgeeCategory;
	}

	public void setMeekosamArgeeCategory(String meekosamArgeeCategory) {
		this.meekosamArgeeCategory = meekosamArgeeCategory;
	}

	public String getMeekosamAnnualIncome() {
		return meekosamAnnualIncome;
	}

	public void setMeekosamAnnualIncome(String meekosamAnnualIncome) {
		this.meekosamAnnualIncome = meekosamAnnualIncome;
	}

	public String getVoterCardNo() {
		return voterCardNo;
	}

	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}

	public List<IdNameVO> getPetitionerList() {
		return petitionerList;
	}

	public void setPetitionerList(List<IdNameVO> petitionerList) {
		this.petitionerList = petitionerList;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}
		
	
}
