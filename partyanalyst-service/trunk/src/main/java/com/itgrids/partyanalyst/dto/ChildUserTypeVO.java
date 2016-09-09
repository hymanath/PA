package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChildUserTypeVO {
	
	public Long activityMemberId = 0l;
	public String image="";
	public Long locationLevelId = 0l;
	public String locationLevelName="";
	public Set<Long> locationValueSet = new HashSet<Long>(0);
	public String name="";
	public String tdpCadreId="";
	public Long userTypeId=0l;
	public String usertType="";
	
	public Long organizationId=0l;
	public String organization="";
	public Long count=0l;
	
	public Long editionId=0l;
	public String edition="";
	
	public Long PositiveCountMain=0l;
	public Long negativeCountMain=0l;
	public Long neutralCountMain=0l;
	
	public Double PositiveCountMainPerc=0.0;
	public Double negativeCountMainperc=0.0;
	public Double neutralCountMainperd=0.0;
	
	public Long PositiveCountDist=0l;
	public Long negativeCountDist=0l;
	public Long neutralCountDist=0l;
	
	public Double PositiveCountDistPerc=0.0;
	public Double negativeCountDistPerc=0.0;
	public Double neutralCountDistPerc=0.0;
	
	public List<ChildUserTypeVO> ChildUserTypeVOList = new ArrayList<ChildUserTypeVO>(0);
	public List<ChildUserTypeVO> ChildUserTypeVOList1 = new ArrayList<ChildUserTypeVO>(0);
	
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public Set<Long> getLocationValueSet() {
		return locationValueSet;
	}
	public void setLocationValueSet(Set<Long> locationValueSet) {
		this.locationValueSet = locationValueSet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(String tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUsertType() {
		return usertType;
	}
	public void setUsertType(String usertType) {
		this.usertType = usertType;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Long getPositiveCountMain() {
		return PositiveCountMain;
	}
	public void setPositiveCountMain(Long positiveCountMain) {
		PositiveCountMain = positiveCountMain;
	}
	public Long getNegativeCountMain() {
		return negativeCountMain;
	}
	public void setNegativeCountMain(Long negativeCountMain) {
		this.negativeCountMain = negativeCountMain;
	}
	public Long getNeutralCountMain() {
		return neutralCountMain;
	}
	public void setNeutralCountMain(Long neutralCountMain) {
		this.neutralCountMain = neutralCountMain;
	}
	public Double getPositiveCountMainPerc() {
		return PositiveCountMainPerc;
	}
	public void setPositiveCountMainPerc(Double positiveCountMainPerc) {
		PositiveCountMainPerc = positiveCountMainPerc;
	}
	public Double getNegativeCountMainperc() {
		return negativeCountMainperc;
	}
	public void setNegativeCountMainperc(Double negativeCountMainperc) {
		this.negativeCountMainperc = negativeCountMainperc;
	}
	public Double getNeutralCountMainperd() {
		return neutralCountMainperd;
	}
	public void setNeutralCountMainperd(Double neutralCountMainperd) {
		this.neutralCountMainperd = neutralCountMainperd;
	}
	public Long getPositiveCountDist() {
		return PositiveCountDist;
	}
	public void setPositiveCountDist(Long positiveCountDist) {
		PositiveCountDist = positiveCountDist;
	}
	public Long getNegativeCountDist() {
		return negativeCountDist;
	}
	public void setNegativeCountDist(Long negativeCountDist) {
		this.negativeCountDist = negativeCountDist;
	}
	public Long getNeutralCountDist() {
		return neutralCountDist;
	}
	public void setNeutralCountDist(Long neutralCountDist) {
		this.neutralCountDist = neutralCountDist;
	}
	public Double getPositiveCountDistPerc() {
		return PositiveCountDistPerc;
	}
	public void setPositiveCountDistPerc(Double positiveCountDistPerc) {
		PositiveCountDistPerc = positiveCountDistPerc;
	}
	public Double getNegativeCountDistPerc() {
		return negativeCountDistPerc;
	}
	public void setNegativeCountDistPerc(Double negativeCountDistPerc) {
		this.negativeCountDistPerc = negativeCountDistPerc;
	}
	public Double getNeutralCountDistPerc() {
		return neutralCountDistPerc;
	}
	public void setNeutralCountDistPerc(Double neutralCountDistPerc) {
		this.neutralCountDistPerc = neutralCountDistPerc;
	}
	public List<ChildUserTypeVO> getChildUserTypeVOList() {
		return ChildUserTypeVOList;
	}
	public void setChildUserTypeVOList(List<ChildUserTypeVO> childUserTypeVOList) {
		ChildUserTypeVOList = childUserTypeVOList;
	}
	public List<ChildUserTypeVO> getChildUserTypeVOList1() {
		return ChildUserTypeVOList1;
	}
	public void setChildUserTypeVOList1(List<ChildUserTypeVO> childUserTypeVOList1) {
		ChildUserTypeVOList1 = childUserTypeVOList1;
	}
	
	
	
}
