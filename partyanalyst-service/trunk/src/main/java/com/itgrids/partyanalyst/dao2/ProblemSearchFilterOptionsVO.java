package com.itgrids.partyanalyst.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class ProblemSearchFilterOptionsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5147377613734866414L;
    
	private Long locationId;
	private Long locationValue;
	private Long statusId;
	private Long problemTypeId;
	private boolean isCadreReq;
	private boolean onlyUserProb;
	private Long departmentId;
	private Long selectedUserid;
	private Date fromDate;
	private Date toDate;
	private Long userId;
	private String locationString;
	private List<Long> cadreProblemIds;
	private List<Long> departmntProblemIds;
	private int firstResult;
	private int maxResult;
	private boolean isMyPrivateProb;
	private boolean isMyPublicProb;
	private boolean isTakenUpProb;
	private boolean isCommentByMeProb;
	private boolean isAllPublicProb;
	private boolean isPostedByMeProb;
	private List<Long> initialConditionsIds;
	private Long impactLevel;
	private Map<Long,String> departmntnames;
	private Map<Long,String> cadrepostednames;
	private Map<Long,SelectOptionVO> cadretakennames;
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(Long problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getSelectedUserid() {
		return selectedUserid;
	}
	public void setSelectedUserid(Long selectedUserid) {
		this.selectedUserid = selectedUserid;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLocationString() {
		return locationString;
	}
	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}
	public boolean isCadreReq() {
		return isCadreReq;
	}
	public void setCadreReq(boolean isCadreReq) {
		this.isCadreReq = isCadreReq;
	}
	public List<Long> getCadreProblemIds() {
		return cadreProblemIds;
	}
	public void setCadreProblemIds(List<Long> cadreProblemIds) {
		this.cadreProblemIds = cadreProblemIds;
	}
	public List<Long> getDepartmntProblemIds() {
		return departmntProblemIds;
	}
	public void setDepartmntProblemIds(List<Long> departmntProblemIds) {
		this.departmntProblemIds = departmntProblemIds;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public boolean isOnlyUserProb() {
		return onlyUserProb;
	}
	public void setOnlyUserProb(boolean onlyUserProb) {
		this.onlyUserProb = onlyUserProb;
	}
	public boolean isMyPrivateProb() {
		return isMyPrivateProb;
	}
	public void setMyPrivateProb(boolean isMyPrivateProb) {
		this.isMyPrivateProb = isMyPrivateProb;
	}
	public boolean isMyPublicProb() {
		return isMyPublicProb;
	}
	public void setMyPublicProb(boolean isMyPublicProb) {
		this.isMyPublicProb = isMyPublicProb;
	}
	public boolean isTakenUpProb() {
		return isTakenUpProb;
	}
	public void setTakenUpProb(boolean isTakenUpProb) {
		this.isTakenUpProb = isTakenUpProb;
	}
	public boolean isCommentByMeProb() {
		return isCommentByMeProb;
	}
	public void setCommentByMeProb(boolean isCommentByMeProb) {
		this.isCommentByMeProb = isCommentByMeProb;
	}
	public boolean isAllPublicProb() {
		return isAllPublicProb;
	}
	public void setAllPublicProb(boolean isAllPublicProb) {
		this.isAllPublicProb = isAllPublicProb;
	}
	public boolean isPostedByMeProb() {
		return isPostedByMeProb;
	}
	public void setPostedByMeProb(boolean isPostedByMeProb) {
		this.isPostedByMeProb = isPostedByMeProb;
	}
	public List<Long> getInitialConditionsIds() {
		return initialConditionsIds;
	}
	public void setInitialConditionsIds(List<Long> initialConditionsIds) {
		this.initialConditionsIds = initialConditionsIds;
	}
	public Long getImpactLevel() {
		return impactLevel;
	}
	public void setImpactLevel(Long impactLevel) {
		this.impactLevel = impactLevel;
	}
	public Map<Long, String> getDepartmntnames() {
		return departmntnames;
	}
	public void setDepartmntnames(Map<Long, String> departmntnames) {
		this.departmntnames = departmntnames;
	}
	public Map<Long, String> getCadrepostednames() {
		return cadrepostednames;
	}
	public void setCadrepostednames(Map<Long, String> cadrepostednames) {
		this.cadrepostednames = cadrepostednames;
	}
	public Map<Long, SelectOptionVO> getCadretakennames() {
		return cadretakennames;
	}
	public void setCadretakennames(Map<Long, SelectOptionVO> cadretakennames) {
		this.cadretakennames = cadretakennames;
	}
	
    
}
