package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationWiseBoothDetailsVO implements Serializable,Cloneable{

	private static final long serialVersionUID = -8852552121214870720L;
	
	private Long locationId;
	private String locationName;
	private Set<SelectOptionVO> booths;
	private Set<SelectOptionVO> subLocations;
	private List<SelectOptionVO> hamletsOfTownship;
	private List<LocationWiseBoothDetailsVO> result = new ArrayList<LocationWiseBoothDetailsVO>(0);
	private List<LocationWiseBoothDetailsVO2> result2 = new ArrayList<LocationWiseBoothDetailsVO2>(0);
	private Long population;
	private Long votesPolled;
	private Long total = 0l;;
	private String electionYear;
	private Long count;
	
	private Long id;
	private String name;
	private String mobileNo;
	private String planedDate;
	private String conductedDate;
	private String isAlreadyImageUpload;
	
	private String day;
	private String dateStr;
	
	private String roleType;
	private Long   totalCount = 0l;
	private Long   proposedCount = 0l;
	private Long   finalizedCount = 0l;
	private Long   vaccancyCount = 0l;
	
	private Long districtId;
	private String districtName;
	
	private Long constituencyId;
	private String constituencyName;
	private Long mandalId;
	private String mandalName;
	
	private Long villageId;
	private String villageName;
	
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getIsAlreadyImageUpload() {
		return isAlreadyImageUpload;
	}
	public void setIsAlreadyImageUpload(String isAlreadyImageUpload) {
		this.isAlreadyImageUpload = isAlreadyImageUpload;
	}
	
	
	public String getPlanedDate() {
		return planedDate;
	}

	public void setPlanedDate(String planedDate) {
		this.planedDate = planedDate;
	}

	public String getConductedDate() {
		return conductedDate;
	}

	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}

	public LocationWiseBoothDetailsVO(){}
	
	public LocationWiseBoothDetailsVO(Long locationId,String locationName)
	{
		this.locationId=locationId;
		this.locationName = locationName;
	}
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
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
	
	public Long getPopulation() {
		return population;
	}
	
	public void setPopulation(Long population) {
		this.population = population;
	}

	public Set<SelectOptionVO> getBooths() {
		return booths;
	}

	public void setBooths(Set<SelectOptionVO> booths) {
		this.booths = booths;
	}

	public Set<SelectOptionVO> getSubLocations() {
		return subLocations;
	}

	public void setSubLocations(Set<SelectOptionVO> subLocations) {
		this.subLocations = subLocations;
	}

	public List<SelectOptionVO> getHamletsOfTownship() {
		return hamletsOfTownship;
	}

	public void setHamletsOfTownship(List<SelectOptionVO> hamletsOfTownship) {
		this.hamletsOfTownship = hamletsOfTownship;
	}

	public Long getVotesPolled() {
		return votesPolled;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof LocationWiseBoothDetailsVO){
			LocationWiseBoothDetailsVO vo = (LocationWiseBoothDetailsVO) obj;
			return new EqualsBuilder().append(locationId, vo.getLocationId()).isEquals();
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 37).append(locationId).toHashCode();
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<LocationWiseBoothDetailsVO> getResult() {
		return result;
	}

	public void setResult(List<LocationWiseBoothDetailsVO> result) {
		this.result = result;
	}
	public Long getCount() {
		return count;
	}

	public List<LocationWiseBoothDetailsVO2> getResult2() {
		return result2;
	}
	public void setResult2(List<LocationWiseBoothDetailsVO2> result2) {
		this.result2 = result2;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getProposedCount() {
		return proposedCount;
	}
	public void setProposedCount(Long proposedCount) {
		this.proposedCount = proposedCount;
	}
	public Long getFinalizedCount() {
		return finalizedCount;
	}
	public void setFinalizedCount(Long finalizedCount) {
		this.finalizedCount = finalizedCount;
	}
	public Long getVaccancyCount() {
		return vaccancyCount;
	}
	public void setVaccancyCount(Long vaccancyCount) {
		this.vaccancyCount = vaccancyCount;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	
	
}
