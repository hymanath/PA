package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminHouseVO implements Serializable{
	
	private Long id;
	private String date;
	private String name;
	private Long sessionId;
	private Long count = 0l;
	private Long partyId;
	private String partyName;
	private String startDate;
	private List<AdminHouseVO> partyList = new ArrayList<AdminHouseVO>(0); 
	private Long adminHouseSessionId;
	private Long adminHouseSessionDayId;
	private Long speechAsceptId;
	private String aspect;
	private Long candidateId;
	private Long adminHouseMemberId;
	private List<AdminHouseVO> candidateList = new ArrayList<AdminHouseVO>(0);
	private Double total = 0.0d;
	private Double value = 0.0d;
	private Double score = 0.0d;
	private Double avgSubCount = 0.0d;
	private Double avgPresCount = 0.0d;
	private Double avgCunterAttCount = 0.0d;
	private Double avgBdyLanCount = 0.0d;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getCount() {
		return count;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public List<AdminHouseVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<AdminHouseVO> partyList) {
		this.partyList = partyList;
	}
	public Long getAdminHouseSessionId() {
		return adminHouseSessionId;
	}
	public void setAdminHouseSessionId(Long adminHouseSessionId) {
		this.adminHouseSessionId = adminHouseSessionId;
	}
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}
	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	public Long getSpeechAsceptId() {
		return speechAsceptId;
	}
	public void setSpeechAsceptId(Long speechAsceptId) {
		this.speechAsceptId = speechAsceptId;
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getAdminHouseMemberId() {
		return adminHouseMemberId;
	}
	public void setAdminHouseMemberId(Long adminHouseMemberId) {
		this.adminHouseMemberId = adminHouseMemberId;
	}
	public List<AdminHouseVO> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(List<AdminHouseVO> candidateList) {
		this.candidateList = candidateList;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getAvgSubCount() {
		return avgSubCount;
	}
	public void setAvgSubCount(Double avgSubCount) {
		this.avgSubCount = avgSubCount;
	}
	public Double getAvgPresCount() {
		return avgPresCount;
	}
	public void setAvgPresCount(Double avgPresCount) {
		this.avgPresCount = avgPresCount;
	}
	public Double getAvgCunterAttCount() {
		return avgCunterAttCount;
	}
	public void setAvgCunterAttCount(Double avgCunterAttCount) {
		this.avgCunterAttCount = avgCunterAttCount;
	}
	public Double getAvgBdyLanCount() {
		return avgBdyLanCount;
	}
	public void setAvgBdyLanCount(Double avgBdyLanCount) {
		this.avgBdyLanCount = avgBdyLanCount;
	}
}
