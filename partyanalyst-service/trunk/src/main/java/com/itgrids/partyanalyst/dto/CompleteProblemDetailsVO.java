package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CompleteProblemDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2074112340945606345L;
    private String userStatus;  //loggedin , freeuser,partyanyuser,both
    private String noAccess;    //check if user has access to view this problem
    private String profileImg;    //posted user profile image
    private String problemTitle;   
    private String problemDesc;
    private String rating;
    private String ratingByyou;
    private String isAlreadyRated;
    private Long postedUserId;
    private String firstName;
    private String lastName;
    private String problemCompleteLoc;
    private String existingFrom;
    private String identifiedOn;
    private String changedToPrivate;
    private String isPublic;
    private String isConnectPeopleReq;
    private Long problemId;
    private String isTaken;
    private String isOwner;
    private String status;      //problem status NEW,PROGRESS,PENDING,FIXED
    private String modifyAccess; //whether user has access to modified the problem states
    private List<ProblemStatusDataVO> problemRecentActivity;  //All activities performed by all users
    private List<FileVO> problemFiles; //contains all uploaded problem related files
    private ProblemStatusDataVO problemStatus; //contains department,cadre,problemtype details for customer problems only
    private List<CompleteProblemDetailsVO> relatedProblems;
    private String avgRating;
    private String totalpeople;
    private Integer ratingGiven;
    private String isProblemDel;
    private Long problemTypeId;
    private String problemType;
    private Long problemsCount;
    private String cadrecount;
    private String deptcount;
    private List<ProblemBeanVO> deptwiseCount;
    private List<SelectOptionVO> cadreWiseCount;
    private Long cadrepostedId;
    private String cadrepostedName;
    private Long cadretakenId;
    private String cadretakenName;
    private String deptName;
    private String cadreseting;
    private String deptsetng;
    
	public Long getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(Long problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getNoAccess() {
		return noAccess;
	}
	public void setNoAccess(String noAccess) {
		this.noAccess = noAccess;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getProblemTitle() {
		return problemTitle;
	}
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProblemCompleteLoc() {
		return problemCompleteLoc;
	}
	public void setProblemCompleteLoc(String problemCompleteLoc) {
		this.problemCompleteLoc = problemCompleteLoc;
	}
	public String getExistingFrom() {
		return existingFrom;
	}
	public void setExistingFrom(String existingFrom) {
		this.existingFrom = existingFrom;
	}
	public String getIdentifiedOn() {
		return identifiedOn;
	}
	public void setIdentifiedOn(String identifiedOn) {
		this.identifiedOn = identifiedOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ProblemStatusDataVO> getProblemRecentActivity() {
		return problemRecentActivity;
	}
	public void setProblemRecentActivity(
			List<ProblemStatusDataVO> problemRecentActivity) {
		this.problemRecentActivity = problemRecentActivity;
	}
	public String getModifyAccess() {
		return modifyAccess;
	}
	public void setModifyAccess(String modifyAccess) {
		this.modifyAccess = modifyAccess;
	}
	public List<FileVO> getProblemFiles() {
		return problemFiles;
	}
	public void setProblemFiles(List<FileVO> problemFiles) {
		this.problemFiles = problemFiles;
	}
	public ProblemStatusDataVO getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(ProblemStatusDataVO problemStatus) {
		this.problemStatus = problemStatus;
	}
	public String getIsTaken() {
		return isTaken;
	}
	public void setIsTaken(String isTaken) {
		this.isTaken = isTaken;
	}
	public String getChangedToPrivate() {
		return changedToPrivate;
	}
	public void setChangedToPrivate(String changedToPrivate) {
		this.changedToPrivate = changedToPrivate;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getIsConnectPeopleReq() {
		return isConnectPeopleReq;
	}
	public void setIsConnectPeopleReq(String isConnectPeopleReq) {
		this.isConnectPeopleReq = isConnectPeopleReq;
	}
	public Long getPostedUserId() {
		return postedUserId;
	}
	public void setPostedUserId(Long postedUserId) {
		this.postedUserId = postedUserId;
	}
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public String getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(String isOwner) {
		this.isOwner = isOwner;
	}
	public List<CompleteProblemDetailsVO> getRelatedProblems() {
		return relatedProblems;
	}
	public void setRelatedProblems(List<CompleteProblemDetailsVO> relatedProblems) {
		this.relatedProblems = relatedProblems;
	}
	public String getRatingByyou() {
		return ratingByyou;
	}
	public void setRatingByyou(String ratingByyou) {
		this.ratingByyou = ratingByyou;
	}
	public String getIsAlreadyRated() {
		return isAlreadyRated;
	}
	public void setIsAlreadyRated(String isAlreadyRated) {
		this.isAlreadyRated = isAlreadyRated;
	}
	public String getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}
	public String getTotalpeople() {
		return totalpeople;
	}
	public void setTotalpeople(String totalpeople) {
		this.totalpeople = totalpeople;
	}
	public Integer getRatingGiven() {
		return ratingGiven;
	}
	public void setRatingGiven(Integer ratingGiven) {
		this.ratingGiven = ratingGiven;
	}
	public String getIsProblemDel() {
		return isProblemDel;
	}
	public void setIsProblemDel(String isProblemDel) {
		this.isProblemDel = isProblemDel;
	}
	public Long getProblemsCount() {
		return problemsCount;
	}
	public void setProblemsCount(Long problemsCount) {
		this.problemsCount = problemsCount;
	}
	public String getCadrecount() {
		return cadrecount;
	}
	public void setCadrecount(String cadrecount) {
		this.cadrecount = cadrecount;
	}
	public String getDeptcount() {
		return deptcount;
	}
	public void setDeptcount(String deptcount) {
		this.deptcount = deptcount;
	}
	public List<ProblemBeanVO> getDeptwiseCount() {
		return deptwiseCount;
	}
	public void setDeptwiseCount(List<ProblemBeanVO> deptwiseCount) {
		this.deptwiseCount = deptwiseCount;
	}
	public List<SelectOptionVO> getCadreWiseCount() {
		return cadreWiseCount;
	}
	public void setCadreWiseCount(List<SelectOptionVO> cadreWiseCount) {
		this.cadreWiseCount = cadreWiseCount;
	}
	public Long getCadrepostedId() {
		return cadrepostedId;
	}
	public void setCadrepostedId(Long cadrepostedId) {
		this.cadrepostedId = cadrepostedId;
	}
	public String getCadrepostedName() {
		return cadrepostedName;
	}
	public void setCadrepostedName(String cadrepostedName) {
		this.cadrepostedName = cadrepostedName;
	}
	public Long getCadretakenId() {
		return cadretakenId;
	}
	public void setCadretakenId(Long cadretakenId) {
		this.cadretakenId = cadretakenId;
	}
	public String getCadretakenName() {
		return cadretakenName;
	}
	public void setCadretakenName(String cadretakenName) {
		this.cadretakenName = cadretakenName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCadreseting() {
		return cadreseting;
	}
	public void setCadreseting(String cadreseting) {
		this.cadreseting = cadreseting;
	}
	public String getDeptsetng() {
		return deptsetng;
	}
	public void setDeptsetng(String deptsetng) {
		this.deptsetng = deptsetng;
	}
    
}
