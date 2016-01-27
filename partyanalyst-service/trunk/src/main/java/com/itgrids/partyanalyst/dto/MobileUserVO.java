package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class MobileUserVO implements Serializable{
    
	private Long   wardId;
	private String divisionNo;
	private String divisionName;
	private String dateString;
	private Long   usersCount;
	private Long   voterscount;
	private Long   mobilescount;
	private Long   ratingId;
	private Long   ratingCount;
	
	private List<MobileUserVO>  dateList;
	private List<MobileUserVO>  ratingList;
	
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getDivisionNo() {
		return divisionNo;
	}
	public void setDivisionNo(String divisionNo) {
		this.divisionNo = divisionNo;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Long getUsersCount() {
		return usersCount;
	}
	public void setUsersCount(Long usersCount) {
		this.usersCount = usersCount;
	}
	public Long getVoterscount() {
		return voterscount;
	}
	public void setVoterscount(Long voterscount) {
		this.voterscount = voterscount;
	}
	public Long getMobilescount() {
		return mobilescount;
	}
	public void setMobilescount(Long mobilescount) {
		this.mobilescount = mobilescount;
	}
	public Long getRatingId() {
		return ratingId;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}
	public List<MobileUserVO> getDateList() {
		return dateList;
	}
	public void setDateList(List<MobileUserVO> dateList) {
		this.dateList = dateList;
	}
	public List<MobileUserVO> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<MobileUserVO> ratingList) {
		this.ratingList = ratingList;
	}
	public Long getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Long ratingCount) {
		this.ratingCount = ratingCount;
	}
	
	
}
