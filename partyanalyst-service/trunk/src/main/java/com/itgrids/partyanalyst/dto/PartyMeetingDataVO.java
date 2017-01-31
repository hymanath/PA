package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



public class PartyMeetingDataVO implements Serializable{
	
	private Long id;
	private String name;
	private Long totalMeetings = 0l;
	private Long conductedMeetings = 0l;
	private Long notConductedMeetings = 0l;
	
	private Long partyMeetingId;
	private String partyMeetingName;
	private String remarks;
	
	private List<PartyMeetingDataVO> meetingsList;
	private List<PartyMeetingDataVO> levelList;
	private Map<Long,PartyMeetingDataVO> subMap;
	
	public PartyMeetingDataVO(){}
	
	public PartyMeetingDataVO(Long id , String name){
		this.id = id;
		this.name = name;
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
	public Long getTotalMeetings() {
		return totalMeetings;
	}
	public void setTotalMeetings(Long totalMeetings) {
		this.totalMeetings = totalMeetings;
	}
	public Long getConductedMeetings() {
		return conductedMeetings;
	}
	public void setConductedMeetings(Long conductedMeetings) {
		this.conductedMeetings = conductedMeetings;
	}
	public Long getNotConductedMeetings() {
		return notConductedMeetings;
	}
	public void setNotConductedMeetings(Long notConductedMeetings) {
		this.notConductedMeetings = notConductedMeetings;
	}
	

	public List<PartyMeetingDataVO> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<PartyMeetingDataVO> levelList) {
		this.levelList = levelList;
	}

	public Map<Long, PartyMeetingDataVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<Long, PartyMeetingDataVO> subMap) {
		this.subMap = subMap;
	}

	public List<PartyMeetingDataVO> getMeetingsList() {
		return meetingsList;
	}

	public void setMeetingsList(List<PartyMeetingDataVO> meetingsList) {
		this.meetingsList = meetingsList;
	}

	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	public String getPartyMeetingName() {
		return partyMeetingName;
	}

	public void setPartyMeetingName(String partyMeetingName) {
		this.partyMeetingName = partyMeetingName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
