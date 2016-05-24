package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
	
	private List<MahanaduEventVO> subList = new ArrayList<MahanaduEventVO>(0);
	private List<MahanaduEventVO> hoursList = new ArrayList<MahanaduEventVO>(0);
	
	private List<MahanaduEventVO> datesList = new ArrayList<MahanaduEventVO>(0);
	
	private Map<String,MahanaduEventVO> subMap;
	private String mobileNo;
	
	private List<String> dates = new ArrayList<String>(0);
	private List<String> dateCombinations = new ArrayList<String>(0);;
	private Long locationId;
	private String locationName;
	private String locationType;
	private int count;
	
	private List<Long> attendedCadres = new ArrayList<Long>(0);
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
	private Long totalVisitorsCount;
	
	private Long uniqueInviteeVisitorsAttended = 0l;
	private Long uniqueNonInviteeVisitorsAttended = 0l;
	
	private Long inviteesCalled;
	private String dateStr;
	private boolean isDataExist;
	private String districtName;
	private Long   locationNo;
	
	private Long eightam=0l;
	private Long nineam=0l;
	private Long tenam=0l;
	private Long elevenam=0l;
	private Long twelvpm=0l;
	private Long onepm=0l;
	private Long twopm=0l;
	private Long threepm=0l;
	private Long fourpm=0l;
	private Long fivepm=0l;
	private Long sixpm=0l;
	private Long sevenpm=0l;
	private Long eightpm=0l;
	
	private String attendeePercantage;
	private String inviteePercantage;
	private String nonInviteePercantage;
	  
	public Long getTotalVisitorsCount() {
		return totalVisitorsCount;
	}
	public void setTotalVisitorsCount(Long totalVisitorsCount) {
		this.totalVisitorsCount = totalVisitorsCount;
	}
	
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
	public Long getUniqueInviteeVisitorsAttended() {
		return uniqueInviteeVisitorsAttended;
	}
	public void setUniqueInviteeVisitorsAttended(Long uniqueInviteeVisitorsAttended) {
		this.uniqueInviteeVisitorsAttended = uniqueInviteeVisitorsAttended;
	}
	public Long getUniqueNonInviteeVisitorsAttended() {
		return uniqueNonInviteeVisitorsAttended;
	}
	public void setUniqueNonInviteeVisitorsAttended(
			Long uniqueNonInviteeVisitorsAttended) {
		this.uniqueNonInviteeVisitorsAttended = uniqueNonInviteeVisitorsAttended;
	}
	public Long getInviteesCalled() {
		return inviteesCalled;
	}
	public void setInviteesCalled(Long inviteesCalled) {
		this.inviteesCalled = inviteesCalled;
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
	public Map<String, MahanaduEventVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<String, MahanaduEventVO> subMap) {
		this.subMap = subMap;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(Long locationNo) {
		this.locationNo = locationNo;
	}
	public Long getEightam() {
		return eightam;
	}
	public void setEightam(Long eightam) {
		this.eightam = eightam;
	}
	public Long getNineam() {
		return nineam;
	}
	public void setNineam(Long nineam) {
		this.nineam = nineam;
	}
	public Long getTenam() {
		return tenam;
	}
	public void setTenam(Long tenam) {
		this.tenam = tenam;
	}
	public Long getElevenam() {
		return elevenam;
	}
	public void setElevenam(Long elevenam) {
		this.elevenam = elevenam;
	}
	public Long getTwelvpm() {
		return twelvpm;
	}
	public void setTwelvpm(Long twelvpm) {
		this.twelvpm = twelvpm;
	}
	public Long getOnepm() {
		return onepm;
	}
	public void setOnepm(Long onepm) {
		this.onepm = onepm;
	}
	public Long getTwopm() {
		return twopm;
	}
	public void setTwopm(Long twopm) {
		this.twopm = twopm;
	}
	public Long getThreepm() {
		return threepm;
	}
	public void setThreepm(Long threepm) {
		this.threepm = threepm;
	}
	public Long getFourpm() {
		return fourpm;
	}
	public void setFourpm(Long fourpm) {
		this.fourpm = fourpm;
	}
	public Long getFivepm() {
		return fivepm;
	}
	public void setFivepm(Long fivepm) {
		this.fivepm = fivepm;
	}
	public Long getSixpm() {
		return sixpm;
	}
	public void setSixpm(Long sixpm) {
		this.sixpm = sixpm;
	}
	public Long getSevenpm() {
		return sevenpm;
	}
	public void setSevenpm(Long sevenpm) {
		this.sevenpm = sevenpm;
	}
	public Long getEightpm() {
		return eightpm;
	}
	public void setEightpm(Long eightpm) {
		this.eightpm = eightpm;
	}
	public String getAttendeePercantage() {
		return attendeePercantage;
	}
	public void setAttendeePercantage(String attendeePercantage) {
		this.attendeePercantage = attendeePercantage;
	}
	public String getInviteePercantage() {
		return inviteePercantage;
	}
	public void setInviteePercantage(String inviteePercantage) {
		this.inviteePercantage = inviteePercantage;
	}
	public String getNonInviteePercantage() {
		return nonInviteePercantage;
	}
	public void setNonInviteePercantage(String nonInviteePercantage) {
		this.nonInviteePercantage = nonInviteePercantage;
	}
	
}
