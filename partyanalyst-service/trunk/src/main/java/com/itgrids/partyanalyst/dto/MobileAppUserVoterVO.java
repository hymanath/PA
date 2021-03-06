package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MobileAppUserVoterVO {
	private Long mobileAppUserId;
	private Long wardId;
	private Long boothId;
	private Long voterId;
	private String mobileNum;
	private Long rating;
	private String uniqueKey;
	private String latitude;
	private String longitude;
	private String surveyTime;
	private String imeiNo;
	private String versionNo;
	private String isVoted;
	private String votedTime;
	private Long tdpCadreId;
	private List<Long> voterIds;
	private List<String> mobileNums;
	private Integer firstResult;
	private Integer maxResults;
	private Long divisonId;
	private String reqType;
	
	public Integer getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public Long getDivisonId() {
		return divisonId;
	}
	public void setDivisonId(Long divisonId) {
		this.divisonId = divisonId;
	}
	public List<String> getMobileNums() {
		return mobileNums;
	}
	public void setMobileNums(List<String> mobileNums) {
		this.mobileNums = mobileNums;
	}
	public List<Long> getVoterIds() {
		return voterIds;
	}
	public void setVoterIds(List<Long> voterIds) {
		this.voterIds = voterIds;
	}
	public String getIsVoted() {
		return isVoted;
	}
	public void setIsVoted(String isVoted) {
		this.isVoted = isVoted;
	}
	public String getVotedTime() {
		return votedTime;
	}
	public void setVotedTime(String votedTime) {
		this.votedTime = votedTime;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}
	
	

}
