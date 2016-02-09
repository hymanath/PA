package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class InviteesVO implements java.io.Serializable {

	private Long id;
	private String name;
	private Long committeeId;
	private Long levelId;
	private Long levelValue;
	private String levelStr;
	private Long publicRepresentatorsTypeId;
	private Long groupId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long panchayatId;
	private Long stateId;
	private List<Long> rolesIds = new ArrayList<Long>();
	
	private List<InviteesVO> centralLevelVOList = new ArrayList<InviteesVO>();
	private List<InviteesVO> stateLevelVOList = new ArrayList<InviteesVO>();
	private List<InviteesVO> districtLevelVOList = new ArrayList<InviteesVO>();
	private List<InviteesVO> mandalLevelVOList = new ArrayList<InviteesVO>();
	private List<InviteesVO> villageLevelVOList = new ArrayList<InviteesVO>();
	private List<InviteesVO> publicRepresentatorsList = new ArrayList<InviteesVO>();
	private List<InviteesVO> groupVOList = new ArrayList<InviteesVO>();
	
	private String reportType;
	private String actionType;
	private String stateStr;
	private String groupName;
	private String eventId;
	private String startIndex;
	private String maxIndex;

	
	public Long getStateId() {
		return stateId;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(String maxIndex) {
		this.maxIndex = maxIndex;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
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
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public String getLevelStr() {
		return levelStr;
	}
	public void setLevelStr(String levelStr) {
		this.levelStr = levelStr;
	}
	public Long getPublicRepresentatorsTypeId() {
		return publicRepresentatorsTypeId;
	}
	public void setPublicRepresentatorsTypeId(Long publicRepresentatorsTypeId) {
		this.publicRepresentatorsTypeId = publicRepresentatorsTypeId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public List<Long> getRolesIds() {
		return rolesIds;
	}
	public void setRolesIds(List<Long> rolesIds) {
		this.rolesIds = rolesIds;
	}
	public List<InviteesVO> getStateLevelVOList() {
		return stateLevelVOList;
	}
	public void setStateLevelVOList(List<InviteesVO> stateLevelVOList) {
		this.stateLevelVOList = stateLevelVOList;
	}
	public List<InviteesVO> getDistrictLevelVOList() {
		return districtLevelVOList;
	}
	public void setDistrictLevelVOList(List<InviteesVO> districtLevelVOList) {
		this.districtLevelVOList = districtLevelVOList;
	}
	public List<InviteesVO> getMandalLevelVOList() {
		return mandalLevelVOList;
	}
	public void setMandalLevelVOList(List<InviteesVO> mandalLevelVOList) {
		this.mandalLevelVOList = mandalLevelVOList;
	}
	public List<InviteesVO> getVillageLevelVOList() {
		return villageLevelVOList;
	}
	public void setVillageLevelVOList(List<InviteesVO> villageLevelVOList) {
		this.villageLevelVOList = villageLevelVOList;
	}
	public List<InviteesVO> getPublicRepresentatorsList() {
		return publicRepresentatorsList;
	}
	public void setPublicRepresentatorsList(
			List<InviteesVO> publicRepresentatorsList) {
		this.publicRepresentatorsList = publicRepresentatorsList;
	}
	public List<InviteesVO> getGroupVOList() {
		return groupVOList;
	}
	public void setGroupVOList(List<InviteesVO> groupVOList) {
		this.groupVOList = groupVOList;
	}
	public List<InviteesVO> getCentralLevelVOList() {
		return centralLevelVOList;
	}
	public void setCentralLevelVOList(List<InviteesVO> centralLevelVOList) {
		this.centralLevelVOList = centralLevelVOList;
	}
		
}
