package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CadreDateVO implements Serializable{
	
	private static final long serialVersionUID = 7685623809305673094L;
	
	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	private String dateStr;
	private Long hour;
	private Long regCount=0l;
	
	
	private Long usersCount=0l;
	private Long stateId;
	
	private Integer dayHour;
	
	private List<CadreDateVO> apList;
	private List<CadreDateVO> tsList;
	
	private Map<Integer,CadreDateVO> subMap;
	private Map<Integer,CadreDateVO> subMap1;
	
	public Long getRegCount() {
		return regCount;
	}

	public void setRegCount(Long regCount) {
		this.regCount = regCount;
	}

	public Long getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(Long usersCount) {
		this.usersCount = usersCount;
	}

	public Long getHour() {
		return hour;
	}

	public void setHour(Long hour) {
		this.hour = hour;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public List<CadreDateVO> getApList() {
		return apList;
	}

	public void setApList(List<CadreDateVO> apList) {
		this.apList = apList;
	}

	public List<CadreDateVO> getTsList() {
		return tsList;
	}

	public void setTsList(List<CadreDateVO> tsList) {
		this.tsList = tsList;
	}

	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}

	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}

	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}

	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}

	public Integer getDayHour() {
		return dayHour;
	}

	public void setDayHour(Integer dayHour) {
		this.dayHour = dayHour;
	}

	public Map<Integer, CadreDateVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<Integer, CadreDateVO> subMap) {
		this.subMap = subMap;
	}

	public Map<Integer, CadreDateVO> getSubMap1() {
		return subMap1;
	}

	public void setSubMap1(Map<Integer, CadreDateVO> subMap1) {
		this.subMap1 = subMap1;
	}
	
	
	
}
