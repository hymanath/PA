package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;


public class MahanaduEventVO implements Serializable, Comparable<MahanaduEventVO> {

	private Long id;
	private String name;
	private Long invitees=0l;
	private Long nonInvitees=0l;
	private Long attendees=0l;
	private Long total=0l;
	private Long voterCount = 0l;
	private Long cadreCount = 0l;
	
	private String startTime;
	private String endTime;
	private String desc;
	private String inviteeExists;
	
	private List<MahanaduEventVO> subList = new ArrayList<MahanaduEventVO>();
	private List<MahanaduEventVO> hoursList = new ArrayList<MahanaduEventVO>();
	
	private List<MahanaduEventVO> datesList = new ArrayList<MahanaduEventVO>();
	
	private String mobileNo;
	
	private List<String> dates;
	private List<String> dateCombinations;
	private Long locationId;
	private String locationName;
	private String locationType;
	private int count;
	
	private List<Long> attendedCadres;
	private int oneDayCount;
	private Long revisitCount;
	private int newCount;
	
	private Long validCount=0l;
	private Long inValidCount=0l;
	
	private boolean isMahanaduEvent;
	
	private String eventStartDate;
	private String eventEndDate;
	
	private String formateEventStartDate;
	private String formateEventEndDate;
	
	private String lastUpdatedDate;
	
	public Long getRevisitCount() {
		return revisitCount;
	}
	public void setRevisitCount(Long revisitCount) {
		this.revisitCount = revisitCount;
	}
	public int getOneDayCount() {
		return oneDayCount;
	}
	public void setOneDayCount(int oneDayCount) {
		this.oneDayCount = oneDayCount;
	}
	public List<Long> getAttendedCadres() {
		return attendedCadres;
	}
	public void setAttendedCadres(List<Long> attendedCadres) {
		this.attendedCadres = attendedCadres;
	}
	public List<MahanaduEventVO> getDatesList() {
		return datesList;
	}
	public void setDatesList(List<MahanaduEventVO> datesList) {
		this.datesList = datesList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public List<String> getDateCombinations() {
		return dateCombinations;
	}
	public void setDateCombinations(List<String> dateCombinations) {
		this.dateCombinations = dateCombinations;
	}
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	
	
	public List<MahanaduEventVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MahanaduEventVO> subList) {
		this.subList = subList;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInviteeExists() {
		return inviteeExists;
	}
	public void setInviteeExists(String inviteeExists) {
		this.inviteeExists = inviteeExists;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
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
	public Long getAttendees() {
		return attendees;
	}
	public void setAttendees(Long attendees) {
		this.attendees = attendees;
	}
	public Long getVoterCount() {
		return voterCount;
	}
	public void setVoterCount(Long voterCount) {
		this.voterCount = voterCount;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	public int compareTo(MahanaduEventVO obj) {
		if(obj instanceof MahanaduEventVO){
			MahanaduEventVO vo = (MahanaduEventVO) obj;
			if(vo.getName() != null)
			return name.compareToIgnoreCase(vo.getName());
			else
				return 0;
		}
		else
			return 0;
	}
	public List<MahanaduEventVO> getHoursList() {
		return hoursList;
	}
	public void setHoursList(List<MahanaduEventVO> hoursList) {
		this.hoursList = hoursList;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getNewCount() {
		return newCount;
	}
	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}
	public Long getValidCount() {
		return validCount;
	}
	public void setValidCount(Long validCount) {
		this.validCount = validCount;
	}
	public Long getInValidCount() {
		return inValidCount;
	}
	public void setInValidCount(Long inValidCount) {
		this.inValidCount = inValidCount;
	}
	public boolean isMahanaduEvent() {
		return isMahanaduEvent;
	}
	public void setMahanaduEvent(boolean isMahanaduEvent) {
		this.isMahanaduEvent = isMahanaduEvent;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getFormateEventStartDate() {
		return formateEventStartDate;
	}
	public void setFormateEventStartDate(String formateEventStartDate) {
		this.formateEventStartDate = formateEventStartDate;
	}
	public String getFormateEventEndDate() {
		return formateEventEndDate;
	}
	public void setFormateEventEndDate(String formateEventEndDate) {
		this.formateEventEndDate = formateEventEndDate;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
