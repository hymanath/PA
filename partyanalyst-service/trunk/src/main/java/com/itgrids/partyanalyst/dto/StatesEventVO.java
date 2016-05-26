package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StatesEventVO implements Serializable{
	
	private Long    attendees;
	private Long    invitees;
	private Long    nonInvitees;
	
	private String  dateStr;
	private boolean isDataExist;
	private String  name;
	
	private StatesEventVO apStateVO;
	private StatesEventVO tsStateVO;
	private StatesEventVO otherStatesVO;
	private StatesEventVO allStatesVO;
	
	private List<StatesEventVO> subList;
	private Map<String,StatesEventVO> subMap;
	
	private String calcPercantage;
	private String highOrlow;
	
	private String dateString1;
	private String dateString2;
	
	private boolean isCurrentDay;
	
	public Long getAttendees() {
		return attendees;
	}
	public void setAttendees(Long attendees) {
		this.attendees = attendees;
	}
	public Long getInvitees() {
		return invitees;
	}
	public void setInvitees(Long invitees) {
		this.invitees = invitees;
	}
	
	public Long getNonInvitees() {
		return nonInvitees;
	}
	public void setNonInvitees(Long nonInvitees) {
		this.nonInvitees = nonInvitees;
	}
	public StatesEventVO getApStateVO() {
		return apStateVO;
	}
	public void setApStateVO(StatesEventVO apStateVO) {
		this.apStateVO = apStateVO;
	}
	public StatesEventVO getTsStateVO() {
		return tsStateVO;
	}
	public void setTsStateVO(StatesEventVO tsStateVO) {
		this.tsStateVO = tsStateVO;
	}
	public StatesEventVO getOtherStatesVO() {
		return otherStatesVO;
	}
	public void setOtherStatesVO(StatesEventVO otherStatesVO) {
		this.otherStatesVO = otherStatesVO;
	}
	public StatesEventVO getAllStatesVO() {
		return allStatesVO;
	}
	public void setAllStatesVO(StatesEventVO allStatesVO) {
		this.allStatesVO = allStatesVO;
	}
	public List<StatesEventVO> getSubList() {
		return subList;
	}
	public void setSubList(List<StatesEventVO> subList) {
		this.subList = subList;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public boolean isDataExist() {
		return isDataExist;
	}
	public void setDataExist(boolean isDataExist) {
		this.isDataExist = isDataExist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, StatesEventVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<String, StatesEventVO> subMap) {
		this.subMap = subMap;
	}
	public String getCalcPercantage() {
		return calcPercantage;
	}
	public void setCalcPercantage(String calcPercantage) {
		this.calcPercantage = calcPercantage;
	}
	public String getHighOrlow() {
		return highOrlow;
	}
	public void setHighOrlow(String highOrlow) {
		this.highOrlow = highOrlow;
	}
	public String getDateString1() {
		return dateString1;
	}
	public void setDateString1(String dateString1) {
		this.dateString1 = dateString1;
	}
	public String getDateString2() {
		return dateString2;
	}
	public void setDateString2(String dateString2) {
		this.dateString2 = dateString2;
	}
	public boolean isCurrentDay() {
		return isCurrentDay;
	}
	public void setCurrentDay(boolean isCurrentDay) {
		this.isCurrentDay = isCurrentDay;
	}
	
	
}
