package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TEJA
 * @date Jan 12, 2017
 */
public class AmsAppVO {
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
	private List<AmsAppVO> idnameList = new ArrayList<AmsAppVO>();
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
	private List<AmsTrackingVO> commentList = new ArrayList<AmsTrackingVO>(0);
	private List<Long> locationScopeIds = new ArrayList<Long>();
	private Long categoryId=0L;
	private String color;
	private Long childLevelId=0l;
	private String callerName;
	private String userType;
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
	public List<AmsAppVO> getIdnameList() {
		return idnameList;
	}
	public void setIdnameList(List<AmsAppVO> idnameList) {
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Long getConstitunecyId() {
		return constitunecyId;
	}
	public void setConstitunecyId(Long constitunecyId) {
		this.constitunecyId = constitunecyId;
	}
	public Long getConstituencyNo() {
		return constituencyNo;
	}
	public void setConstituencyNo(Long constituencyNo) {
		this.constituencyNo = constituencyNo;
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
	public void setDistId(Long distId) {
		this.distId = distId;
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
	public List<String> getSessionLevel() {
		return sessionLevel;
	}
	public void setSessionLevel(List<String> sessionLevel) {
		this.sessionLevel = sessionLevel;
	}
	public String getIsInvitee() {
		return isInvitee;
	}
	public void setIsInvitee(String isInvitee) {
		this.isInvitee = isInvitee;
	}
	public String getIsAccess() {
		return isAccess;
	}
	public void setIsAccess(String isAccess) {
		this.isAccess = isAccess;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<SessionVO> getSessionsList() {
		return sessionsList;
	}
	public void setSessionsList(List<SessionVO> sessionsList) {
		this.sessionsList = sessionsList;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getMainTitle() {
		return mainTitle;
	}
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
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
	public String getDueDateStr() {
		return dueDateStr;
	}
	public void setDueDateStr(String dueDateStr) {
		this.dueDateStr = dueDateStr;
	}
	public List<AmsTrackingVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<AmsTrackingVO> commentList) {
		this.commentList = commentList;
	}
	public List<Long> getLocationScopeIds() {
		return locationScopeIds;
	}
	public void setLocationScopeIds(List<Long> locationScopeIds) {
		this.locationScopeIds = locationScopeIds;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getChildLevelId() {
		return childLevelId;
	}
	public void setChildLevelId(Long childLevelId) {
		this.childLevelId = childLevelId;
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
	
}