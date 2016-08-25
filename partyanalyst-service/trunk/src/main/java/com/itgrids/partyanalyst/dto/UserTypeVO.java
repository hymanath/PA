package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserTypeVO implements Serializable{
	
	private Long   id;
	private String name;
	private String image;
	private String locationName;
	
	private Long   userId;
	private Long   tdpCadreId;
	private Long   activityMemberId;
	
	private Long   userTypeId;
	private String userType;
	private String shortName;
	
	private Long   locationLevelId;
	private String locationLevelName;
	private List<Long> locationValues;
	private Set<Long>  locationValuesSet;
	
	private Map<Long,UserTypeVO> subMap;
	private List<UserTypeVO> subList;
	
	private Long totalCount = 0l;
	private Long completedCount = 0l;
	private Long startedCount = 0l;
	private Long notStartedCount = 0l;
	private Double completedPerc = 0.0;
	private Double startedPerc = 0.0;
	private Double notStartedPerc = 0.0;
	
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
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public String getLocationLevelName() {
		return locationLevelName;
	}
	public void setLocationLevelName(String locationLevelName) {
		this.locationLevelName = locationLevelName;
	}
	public List<Long> getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(List<Long> locationValues) {
		this.locationValues = locationValues;
	}
	
	public List<UserTypeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<UserTypeVO> subList) {
		this.subList = subList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public Map<Long, UserTypeVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, UserTypeVO> subMap) {
		this.subMap = subMap;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Long> getLocationValuesSet() {
		return locationValuesSet;
	}
	public void setLocationValuesSet(Set<Long> locationValuesSet) {
		this.locationValuesSet = locationValuesSet;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Double getCompletedPerc() {
		return completedPerc;
	}
	public void setCompletedPerc(Double completedPerc) {
		this.completedPerc = completedPerc;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Double getStartedPerc() {
		return startedPerc;
	}
	public void setStartedPerc(Double startedPerc) {
		this.startedPerc = startedPerc;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public Double getNotStartedPerc() {
		return notStartedPerc;
	}
	public void setNotStartedPerc(Double notStartedPerc) {
		this.notStartedPerc = notStartedPerc;
	}
	
	
}
