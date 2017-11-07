package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleVO implements java.io.Serializable{
	
   private Long id;
   private String name;
   private String membershipNo;
   
   private String dateString;
   private Date date;
   private String imageStr;
   private String mobileNo;
   
   private String startDateStr;
   private String endDateStr;
   
   private Long count;
   private Long total =0l;
   private Long totalCount =0l;
   private String isAttended = "-";
   
   private List<SimpleVO> simpleVOList1 = new ArrayList<SimpleVO>(0);
   private List<SimpleVO> simpleVOList2 = new ArrayList<SimpleVO>(0);
   private Long totalBatchesCount=0L;
   private Long batchTypeId =0L;
   
   private Map<Long,SimpleVO> map;
   
   private String progName="";
   private String campName="";
   private String batchName="";
   private String scheduleName="";
   private Long batchId=0l;
   private Long locValue;
   private String remarks;
   
   private boolean nonInvitee=false;
   
   private Long totalInviteeCount=0l;
   private Long totalAttendedCount = 0l;
   private Long totalAbsentCount =0l;
   private List<Long> cadreIds;
   private SimpleVO simpleVO1;
   
  
   private Long day1Count=0l;
   private Long day2Count=0l;
   private Long day3Count=0l;
   
   private Long centerId;
   private String centerName;
   private String percentage;
   private List<Long> inviteeList = new ArrayList<Long>(0);
   private List<Long> inviteeAttendedList = new ArrayList<Long>(0);
   private List<Long> nonInviteeAttendedlist=new ArrayList<Long>(0);
   
   private Long inviteeCount;
   private Long inviteeAttendedCount = 0l;
   private int nonInviteeAttendedCount = 0;
   private String partyBenefitStr;
   private Long cadreId;
   private Long campId;
   private Long programId;
   private String status;
   
   private Long categoryId;
   private String category;
   private int oneDayNIACount=0;
   private int twoDaysNIACount=0;
   private int threeDaysNIACount=0;
   
   private Long constituencyId;
   private String constituencyName;
   private String designationLevel;
   private String designation;
   private Long designationLevelId;
   private Long designationLevelValue;
   private String designationLevelName;
   private List<Long> mandalIdsList = new ArrayList<Long>(0);
   private Map<Long,String> mandalMap = new HashMap<Long, String>(0);
   private List<Long> villageIdsList = new ArrayList<Long>(0);
   private Map<Long,String> villageMap = new HashMap<Long, String>(0);
   private List<Long> townIdsList = new ArrayList<Long>(0);
   private Map<Long,String> townMap = new HashMap<Long,String>(0);
   private List<Long> wardIdsList = new ArrayList<Long>(0);
   private Map<Long,String> wardMap = new HashMap<Long, String>(0);
   private List<Long> divisionIdsList = new ArrayList<Long>(0);
   private Map<Long,String> divisionMap = new HashMap<Long, String>(0);
   private List<Long> stateIdsList = new ArrayList<Long>(0);
   private Map<Long,String> stateMap = new HashMap<Long, String>(0);
   private List<Long> districtIdsList = new ArrayList<Long>(0);
   private Map<Long,String> districtMap = new HashMap<Long, String>(0);
   private List<Long> centralIdsList = new ArrayList<Long>(0);
   private Map<Long,String> centralMap = new HashMap<Long, String>(0);
   
   private Long oneDayInvitedAttendedCount=0l;
   private Long twoDaysInvitedAttendedCount=0l;
   private Long threeDaysInvitedAttendedCount=0l;
   
   private Long oneDayNonInvitedAttendedCount=0l;
   private Long twoDaysNonInvitedAttendedCount=0l;
   private Long threeDaysNonInvitedAttendedCount=0l;
   
   private Long notAttendedCount=0l;
   private Long age=0l;
   private String caste;
   
   private Long confirmedCount=0L;
   private Long totl=0l;
   private Long nonInviteeAtendedCount =0l;
   
   private Long day1IACount=0l;
   private Long day2IACount=0l;
   private Long day3IACount=0l;
   
   private Long day1NIACount=0l;
   private Long day2NIACount=0l;
   private Long day3NIACount=0l;
   
   private List<SimpleVO> CampDetails=new ArrayList<SimpleVO>(0);
   private List<SimpleVO> batchDetails=new ArrayList<SimpleVO>(0);
   
   
   
public List<SimpleVO> getCampDetails() {
	return CampDetails;
}
public void setCampDetails(List<SimpleVO> campDetails) {
	CampDetails = campDetails;
}
public List<SimpleVO> getBatchDetails() {
	return batchDetails;
}
public void setBatchDetails(List<SimpleVO> batchDetails) {
	this.batchDetails = batchDetails;
}
public Long getDay1IACount() {
	return day1IACount;
}
public void setDay1IACount(Long day1iaCount) {
	day1IACount = day1iaCount;
}
public Long getDay2IACount() {
	return day2IACount;
}
public void setDay2IACount(Long day2iaCount) {
	day2IACount = day2iaCount;
}
public Long getDay3IACount() {
	return day3IACount;
}
public void setDay3IACount(Long day3iaCount) {
	day3IACount = day3iaCount;
}
public Long getDay1NIACount() {
	return day1NIACount;
}
public void setDay1NIACount(Long day1niaCount) {
	day1NIACount = day1niaCount;
}
public Long getDay2NIACount() {
	return day2NIACount;
}
public void setDay2NIACount(Long day2niaCount) {
	day2NIACount = day2niaCount;
}
public Long getDay3NIACount() {
	return day3NIACount;
}
public void setDay3NIACount(Long day3niaCount) {
	day3NIACount = day3niaCount;
}
public Long getConfirmedCount() {
	return confirmedCount;
}
public void setConfirmedCount(Long confirmedCount) {
	this.confirmedCount = confirmedCount;
}

	public String getCaste() {
	return caste;
}
public void setCaste(String caste) {
	this.caste = caste;
}
	public Long getAge() {
	return age;
}
public void setAge(Long age) {
	this.age = age;
}
	public Long getNotAttendedCount() {
	return notAttendedCount;
}
public void setNotAttendedCount(Long notAttendedCount) {
	this.notAttendedCount = notAttendedCount;
}
	public Map<Long, String> getMandalMap() {
	return mandalMap;
}
public void setMandalMap(Map<Long, String> mandalMap) {
	this.mandalMap = mandalMap;
}
public Map<Long, String> getVillageMap() {
	return villageMap;
}
public void setVillageMap(Map<Long, String> villageMap) {
	this.villageMap = villageMap;
}
public Map<Long, String> getTownMap() {
	return townMap;
}
public void setTownMap(Map<Long, String> townMap) {
	this.townMap = townMap;
}
public Map<Long, String> getWardMap() {
	return wardMap;
}
public void setWardMap(Map<Long, String> wardMap) {
	this.wardMap = wardMap;
}
public Map<Long, String> getDivisionMap() {
	return divisionMap;
}
public void setDivisionMap(Map<Long, String> divisionMap) {
	this.divisionMap = divisionMap;
}
public Map<Long, String> getStateMap() {
	return stateMap;
}
public void setStateMap(Map<Long, String> stateMap) {
	this.stateMap = stateMap;
}
public Map<Long, String> getDistrictMap() {
	return districtMap;
}
public void setDistrictMap(Map<Long, String> districtMap) {
	this.districtMap = districtMap;
}
public Map<Long, String> getCentralMap() {
	return centralMap;
}
public void setCentralMap(Map<Long, String> centralMap) {
	this.centralMap = centralMap;
}
	public List<Long> getMandalIdsList() {
		return mandalIdsList;
	}
	public void setMandalIdsList(List<Long> mandalIdsList) {
		this.mandalIdsList = mandalIdsList;
	}
	public List<Long> getVillageIdsList() {
		return villageIdsList;
	}
	public void setVillageIdsList(List<Long> villageIdsList) {
		this.villageIdsList = villageIdsList;
	}
	public List<Long> getTownIdsList() {
		return townIdsList;
	}
	public void setTownIdsList(List<Long> townIdsList) {
		this.townIdsList = townIdsList;
	}
	public List<Long> getWardIdsList() {
		return wardIdsList;
	}
	public void setWardIdsList(List<Long> wardIdsList) {
		this.wardIdsList = wardIdsList;
	}
	public List<Long> getDivisionIdsList() {
		return divisionIdsList;
	}
	public void setDivisionIdsList(List<Long> divisionIdsList) {
		this.divisionIdsList = divisionIdsList;
	}
	public List<Long> getStateIdsList() {
		return stateIdsList;
	}
	public void setStateIdsList(List<Long> stateIdsList) {
		this.stateIdsList = stateIdsList;
	}
	public List<Long> getDistrictIdsList() {
		return districtIdsList;
	}
	public void setDistrictIdsList(List<Long> districtIdsList) {
		this.districtIdsList = districtIdsList;
	}
	public List<Long> getCentralIdsList() {
		return centralIdsList;
	}
	public void setCentralIdsList(List<Long> centralIdsList) {
		this.centralIdsList = centralIdsList;
	}
	public Long getDesignationLevelId() {
   		return designationLevelId;
	}
	public void setDesignationLevelId(Long designationLevelId) {
		this.designationLevelId = designationLevelId;
	}
	public Long getDesignationLevelValue() {
		return designationLevelValue;
	}
	public void setDesignationLevelValue(Long designationLevelValue) {
		this.designationLevelValue = designationLevelValue;
	}
	public String getDesignationLevelName() {
		return designationLevelName;
	}
	public void setDesignationLevelName(String designationLevelName) {
		this.designationLevelName = designationLevelName;
	}
	public String getDesignationLevel() {
		return designationLevel;
	}
	public void setDesignationLevel(String designationLevel) {
		this.designationLevel = designationLevel;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
public int getOneDayNIACount() {
		return oneDayNIACount;
	}
	public void setOneDayNIACount(int oneDayNIACount) {
		this.oneDayNIACount = oneDayNIACount;
	}
	public int getTwoDaysNIACount() {
		return twoDaysNIACount;
	}
	public void setTwoDaysNIACount(int twoDaysNIACount) {
		this.twoDaysNIACount = twoDaysNIACount;
	}
	public int getThreeDaysNIACount() {
		return threeDaysNIACount;
	}
	public void setThreeDaysNIACount(int threeDaysNIACount) {
		this.threeDaysNIACount = threeDaysNIACount;
	}
	public Long getConstituencyId() {
	return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getCategoryId() {
	return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPartyBenefitStr() {
		return partyBenefitStr;
	}
	public void setPartyBenefitStr(String partyBenefitStr) {
		this.partyBenefitStr = partyBenefitStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public int getNonInviteeAttendedCount() {
		return nonInviteeAttendedCount;
	}
	public void setNonInviteeAttendedCount(int nonInviteeAttendedCount) {
		this.nonInviteeAttendedCount = nonInviteeAttendedCount;
	}
	public Long getCenterId() {
		return centerId;
	}
	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public List<Long> getInviteeList() {
		return inviteeList;
	}
	public void setInviteeList(List<Long> inviteeList) {
		this.inviteeList = inviteeList;
	}
	public List<Long> getInviteeAttendedList() {
		return inviteeAttendedList;
	}
	public void setInviteeAttendedList(List<Long> inviteeAttendedList) {
		this.inviteeAttendedList = inviteeAttendedList;
	}

	public String getPercentage() {
	return percentage;
}
public void setPercentage(String percentage) {
	this.percentage = percentage;
}
	public String getCenterName() {
	return centerName;
}
public void setCenterName(String centerName) {
	this.centerName = centerName;
}
	public Long getDay1Count() {
	return day1Count;
}
public void setDay1Count(Long day1Count) {
	this.day1Count = day1Count;
}
public Long getDay2Count() {
	return day2Count;
}
public void setDay2Count(Long day2Count) {
	this.day2Count = day2Count;
}
public Long getDay3Count() {
	return day3Count;
}
public void setDay3Count(Long day3Count) {
	this.day3Count = day3Count;
}
	public SimpleVO getSimpleVO1() {
	return simpleVO1;
}
public void setSimpleVO1(SimpleVO simpleVO1) {
	this.simpleVO1 = simpleVO1;
}
	public List<Long> getCadreIds() {
	return cadreIds;
}
public void setCadreIds(List<Long> cadreIds) {
	this.cadreIds = cadreIds;
}
	public Long getBatchId() {
	return batchId;
}
public void setBatchId(Long batchId) {
	this.batchId = batchId;
}
	public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
	public Long getTotalInviteeCount() {
	return totalInviteeCount;
}
public void setTotalInviteeCount(Long totalInviteeCount) {
	this.totalInviteeCount = totalInviteeCount;
}
public Long getTotalAttendedCount() {
	return totalAttendedCount;
}
public void setTotalAttendedCount(Long totalAttendedCount) {
	this.totalAttendedCount = totalAttendedCount;
}
public Long getTotalAbsentCount() {
	return totalAbsentCount;
}
public void setTotalAbsentCount(Long totalAbsentCount) {
	this.totalAbsentCount = totalAbsentCount;
}
	public boolean isNonInvitee() {
	return nonInvitee;
}
public void setNonInvitee(boolean nonInvitee) {
	this.nonInvitee = nonInvitee;
}
	public String getIsAttended() {
	return isAttended;
	}
	public void setIsAttended(String isAttended) {
	this.isAttended = isAttended;
}

	public String getProgName() {
	return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
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
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<SimpleVO> getSimpleVOList1() {
		return simpleVOList1;
	}
	public void setSimpleVOList1(List<SimpleVO> simpleVOList1) {
		this.simpleVOList1 = simpleVOList1;
	}
	public List<SimpleVO> getSimpleVOList2() {
		return simpleVOList2;
	}
	public void setSimpleVOList2(List<SimpleVO> simpleVOList2) {
		this.simpleVOList2 = simpleVOList2;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Map<Long, SimpleVO> getMap() {
		return map;
	}
	public void setMap(Map<Long, SimpleVO> map) {
		this.map = map;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getLocValue() {
		return locValue;
	}
	public void setLocValue(Long locValue) {
		this.locValue = locValue;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getImageStr() {
		return imageStr;
	}
	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<Long> getNonInviteeAttendedlist() {
		return nonInviteeAttendedlist;
	}
	public void setNonInviteeAttendedlist(List<Long> nonInviteeAttendedlist) {
		this.nonInviteeAttendedlist = nonInviteeAttendedlist;
	}
	public Long getOneDayInvitedAttendedCount() {
		return oneDayInvitedAttendedCount;
	}
	public void setOneDayInvitedAttendedCount(Long oneDayInvitedAttendedCount) {
		this.oneDayInvitedAttendedCount = oneDayInvitedAttendedCount;
	}
	public Long getTwoDaysInvitedAttendedCount() {
		return twoDaysInvitedAttendedCount;
	}
	public void setTwoDaysInvitedAttendedCount(Long twoDaysInvitedAttendedCount) {
		this.twoDaysInvitedAttendedCount = twoDaysInvitedAttendedCount;
	}
	public Long getThreeDaysInvitedAttendedCount() {
		return threeDaysInvitedAttendedCount;
	}
	public void setThreeDaysInvitedAttendedCount(Long threeDaysInvitedAttendedCount) {
		this.threeDaysInvitedAttendedCount = threeDaysInvitedAttendedCount;
	}
	public Long getOneDayNonInvitedAttendedCount() {
		return oneDayNonInvitedAttendedCount;
	}
	public void setOneDayNonInvitedAttendedCount(Long oneDayNonInvitedAttendedCount) {
		this.oneDayNonInvitedAttendedCount = oneDayNonInvitedAttendedCount;
	}
	public Long getTwoDaysNonInvitedAttendedCount() {
		return twoDaysNonInvitedAttendedCount;
	}
	public void setTwoDaysNonInvitedAttendedCount(
			Long twoDaysNonInvitedAttendedCount) {
		this.twoDaysNonInvitedAttendedCount = twoDaysNonInvitedAttendedCount;
	}
	public Long getThreeDaysNonInvitedAttendedCount() {
		return threeDaysNonInvitedAttendedCount;
	}
	public void setThreeDaysNonInvitedAttendedCount(
			Long threeDaysNonInvitedAttendedCount) {
		this.threeDaysNonInvitedAttendedCount = threeDaysNonInvitedAttendedCount;
	}
	public Long getTotl() {
		return totl;
	}
	public void setTotl(Long totl) {
		this.totl = totl;
	}
	public Long getNonInviteeAtendedCount() {
		return nonInviteeAtendedCount;
	}
	public void setNonInviteeAtendedCount(Long nonInviteeAtendedCount) {
		this.nonInviteeAtendedCount = nonInviteeAtendedCount;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleVO other = (SimpleVO) obj;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (campId == null) {
			if (other.campId != null)
				return false;
		} else if (!campId.equals(other.campId))
			return false;
		return true;
	}*/
	@Override
	public String toString() {
		return "SimpleVO [id=" + id + ", name=" + name + ", membershipNo="
				+ membershipNo + ", dateString=" + dateString + ", date="
				+ date + ", imageStr=" + imageStr + ", mobileNo=" + mobileNo
				+ ", startDateStr=" + startDateStr + ", endDateStr="
				+ endDateStr + ", count=" + count + ", total=" + total
				+ ", totalCount=" + totalCount + ", isAttended=" + isAttended
				+ ", simpleVOList1=" + simpleVOList1 + ", simpleVOList2="
				+ simpleVOList2 + ", map=" + map + ", progName=" + progName
				+ ", campName=" + campName + ", batchName=" + batchName
				+ ", scheduleName=" + scheduleName + ", batchId=" + batchId
				+ ", locValue=" + locValue + ", remarks=" + remarks
				+ ", nonInvitee=" + nonInvitee + ", totalInviteeCount="
				+ totalInviteeCount + ", totalAttendedCount="
				+ totalAttendedCount + ", totalAbsentCount=" + totalAbsentCount
				+ ", cadreIds=" + cadreIds + ", simpleVO1=" + simpleVO1
				+ ", day1Count=" + day1Count + ", day2Count=" + day2Count
				+ ", day3Count=" + day3Count + ", centerId=" + centerId
				+ ", centerName=" + centerName + ", percentage=" + percentage
				+ ", inviteeList=" + inviteeList + ", inviteeAttendedList="
				+ inviteeAttendedList + ", nonInviteeAttendedlist="
				+ nonInviteeAttendedlist + ", inviteeCount=" + inviteeCount
				+ ", inviteeAttendedCount=" + inviteeAttendedCount
				+ ", nonInviteeAttendedCount=" + nonInviteeAttendedCount
				+ ", partyBenefitStr=" + partyBenefitStr + ", cadreId="
				+ cadreId + ", campId=" + campId + ", programId=" + programId
				+ ", status=" + status + ", categoryId=" + categoryId
				+ ", category=" + category + ", oneDayNIACount="
				+ oneDayNIACount + ", twoDaysNIACount=" + twoDaysNIACount
				+ ", threeDaysNIACount=" + threeDaysNIACount
				+ ", constituencyId=" + constituencyId + ", constituencyName="
				+ constituencyName + ", designationLevel=" + designationLevel
				+ ", designation=" + designation + ", designationLevelId="
				+ designationLevelId + ", designationLevelValue="
				+ designationLevelValue + ", designationLevelName="
				+ designationLevelName + ", mandalIdsList=" + mandalIdsList
				+ ", mandalMap=" + mandalMap + ", villageIdsList="
				+ villageIdsList + ", villageMap=" + villageMap
				+ ", townIdsList=" + townIdsList + ", townMap=" + townMap
				+ ", wardIdsList=" + wardIdsList + ", wardMap=" + wardMap
				+ ", divisionIdsList=" + divisionIdsList + ", divisionMap="
				+ divisionMap + ", stateIdsList=" + stateIdsList
				+ ", stateMap=" + stateMap + ", districtIdsList="
				+ districtIdsList + ", districtMap=" + districtMap
				+ ", centralIdsList=" + centralIdsList + ", centralMap="
				+ centralMap + ", oneDayInvitedAttendedCount="
				+ oneDayInvitedAttendedCount + ", twoDaysInvitedAttendedCount="
				+ twoDaysInvitedAttendedCount
				+ ", threeDaysInvitedAttendedCount="
				+ threeDaysInvitedAttendedCount
				+ ", oneDayNonInvitedAttendedCount="
				+ oneDayNonInvitedAttendedCount
				+ ", twoDaysNonInvitedAttendedCount="
				+ twoDaysNonInvitedAttendedCount
				+ ", threeDaysNonInvitedAttendedCount="
				+ threeDaysNonInvitedAttendedCount + ", notAttendedCount="
				+ notAttendedCount + ", age=" + age + ", caste=" + caste
				+ ", confirmedCount=" + confirmedCount + ", totl=" + totl
				+ ", nonInviteeAtendedCount=" + nonInviteeAtendedCount
				+ ", day1IACount=" + day1IACount + ", day2IACount="
				+ day2IACount + ", day3IACount=" + day3IACount
				+ ", day1NIACount=" + day1NIACount + ", day2NIACount="
				+ day2NIACount + ", day3NIACount=" + day3NIACount
				+ ", CampDetails=" + CampDetails + ", batchDetails="
				+ batchDetails + "]";
	}
	public Long getTotalBatchesCount() {
		return totalBatchesCount;
	}
	public void setTotalBatchesCount(Long totalBatchesCount) {
		this.totalBatchesCount = totalBatchesCount;
	}
	public Long getBatchTypeId() {
		return batchTypeId;
	}
	public void setBatchTypeId(Long batchTypeId) {
		this.batchTypeId = batchTypeId;
	}
	
   }
