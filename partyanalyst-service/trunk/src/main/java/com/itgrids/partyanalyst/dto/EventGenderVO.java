package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Map;

public class EventGenderVO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String name;
	private String dateStr;
	
	private Long maleAttendees = 0l;
	private Long femaleAttendees = 0l;

	private Long maleInvitees = 0l;
	private Long femaleInvitees = 0l;
	
	private Long maleNonInvitees=0l;
	private Long femaleNonInvitees=0l;
	
	private Map<String,EventGenderVO> subMap;
	
	private String maleAttendeePercantage;
	private String maleInviteePercantage;
	private String maleNonInviteePercantage;
	
	private String femaleAttendeePercantage;
	private String femaleInviteePercantage;
	private String femaleNonInviteePercantage;
	
	private Long maleInviteesCalled=0l;
	private Long femaleInviteesCalled=0l;
	
	
	public Long getMaleAttendees() {
		return maleAttendees;
	}
	public void setMaleAttendees(Long maleAttendees) {
		this.maleAttendees = maleAttendees;
	}
	public Long getFemaleAttendees() {
		return femaleAttendees;
	}
	public void setFemaleAttendees(Long femaleAttendees) {
		this.femaleAttendees = femaleAttendees;
	}
	
	public Long getMaleInvitees() {
		return maleInvitees;
	}
	public void setMaleInvitees(Long maleInvitees) {
		this.maleInvitees = maleInvitees;
	}
	public Long getFemaleInvitees() {
		return femaleInvitees;
	}
	public void setFemaleInvitees(Long femaleInvitees) {
		this.femaleInvitees = femaleInvitees;
	}
	
	public Long getMaleNonInvitees() {
		return maleNonInvitees;
	}
	public void setMaleNonInvitees(Long maleNonInvitees) {
		this.maleNonInvitees = maleNonInvitees;
	}
	public Long getFemaleNonInvitees() {
		return femaleNonInvitees;
	}
	public void setFemaleNonInvitees(Long femaleNonInvitees) {
		this.femaleNonInvitees = femaleNonInvitees;
	}
	
	public Map<String, EventGenderVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<String, EventGenderVO> subMap) {
		this.subMap = subMap;
	}
	public String getMaleAttendeePercantage() {
		return maleAttendeePercantage;
	}
	public void setMaleAttendeePercantage(String maleAttendeePercantage) {
		this.maleAttendeePercantage = maleAttendeePercantage;
	}
	public String getMaleInviteePercantage() {
		return maleInviteePercantage;
	}
	public void setMaleInviteePercantage(String maleInviteePercantage) {
		this.maleInviteePercantage = maleInviteePercantage;
	}
	public String getMaleNonInviteePercantage() {
		return maleNonInviteePercantage;
	}
	public void setMaleNonInviteePercantage(String maleNonInviteePercantage) {
		this.maleNonInviteePercantage = maleNonInviteePercantage;
	}
	public String getFemaleAttendeePercantage() {
		return femaleAttendeePercantage;
	}
	public void setFemaleAttendeePercantage(String femaleAttendeePercantage) {
		this.femaleAttendeePercantage = femaleAttendeePercantage;
	}
	public String getFemaleInviteePercantage() {
		return femaleInviteePercantage;
	}
	public void setFemaleInviteePercantage(String femaleInviteePercantage) {
		this.femaleInviteePercantage = femaleInviteePercantage;
	}
	public String getFemaleNonInviteePercantage() {
		return femaleNonInviteePercantage;
	}
	public void setFemaleNonInviteePercantage(String femaleNonInviteePercantage) {
		this.femaleNonInviteePercantage = femaleNonInviteePercantage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Long getMaleInviteesCalled() {
		return maleInviteesCalled;
	}
	public void setMaleInviteesCalled(Long maleInviteesCalled) {
		this.maleInviteesCalled = maleInviteesCalled;
	}
	public Long getFemaleInviteesCalled() {
		return femaleInviteesCalled;
	}
	public void setFemaleInviteesCalled(Long femaleInviteesCalled) {
		this.femaleInviteesCalled = femaleInviteesCalled;
	}
	
   	
}
