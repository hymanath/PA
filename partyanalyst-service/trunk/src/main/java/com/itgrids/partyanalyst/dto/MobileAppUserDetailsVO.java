/**
 * @author Sasi
 * Jan 27, 2016
 * MobileAppUserDetailsVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sasi
 * @date Jan 27, 2016
 */
public class MobileAppUserDetailsVO implements Serializable{
	private Long			mobileAppUserId;
	private String			startTime;
	private String			endtime;
	private String			date;
	private String			name;
	private String 			mobileNo;
	private String			uniqueCode;
	private int				voterIdsCollected;
	private int				noOfMobiles;
	private int				noOfSmsSent;
	private int				rating;
	private int				ratingCount;
	private int				uniqueVoters;
	private List<MobileAppUserDetailsVO>	ratings;
	private List<MobileAppUserDetailsVO>	userRslt;
	private int				usersCount;
	private int				errorCode;
	private String			statusMsg;
	private int				divisionVoters;
	
	
	public int getDivisionVoters() {
		return divisionVoters;
	}
	public void setDivisionVoters(int divisionVoters) {
		this.divisionVoters = divisionVoters;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public int getUsersCount() {
		return usersCount;
	}
	public void setUsersCount(int usersCount) {
		this.usersCount = usersCount;
	}
	public List<MobileAppUserDetailsVO> getUserRslt() {
		return userRslt;
	}
	public void setUserRslt(List<MobileAppUserDetailsVO> userRslt) {
		this.userRslt = userRslt;
	}
	public int getUniqueVoters() {
		return uniqueVoters;
	}
	public void setUniqueVoters(int uniqueVoters) {
		this.uniqueVoters = uniqueVoters;
	}
	public List<MobileAppUserDetailsVO> getRatings() {
		return ratings;
	}
	public void setRatings(List<MobileAppUserDetailsVO> ratings) {
		this.ratings = ratings;
	}
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getVoterIdsCollected() {
		return voterIdsCollected;
	}
	public void setVoterIdsCollected(int voterIdsCollected) {
		this.voterIdsCollected = voterIdsCollected;
	}
	
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getNoOfSmsSent() {
		return noOfSmsSent;
	}
	public void setNoOfSmsSent(int noOfSmsSent) {
		this.noOfSmsSent = noOfSmsSent;
	}
	public int getNoOfMobiles() {
		return noOfMobiles;
	}
	public void setNoOfMobiles(int noOfMobiles) {
		this.noOfMobiles = noOfMobiles;
	}
	
	
}
