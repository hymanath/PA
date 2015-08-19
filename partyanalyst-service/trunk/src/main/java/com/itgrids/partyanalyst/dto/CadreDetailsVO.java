package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class CadreDetailsVO implements java.io.Serializable{
    
	private Long id;
	private String name;
	private String mobileno;
	private String image;
	private Long constituencyId;
	private String constituency;
	private String districtName;
	private String programName;
	private String designation;
	
	private boolean achievements;
	private boolean goals;
	private boolean leaderShipLevels;
	private boolean communicationSkills;
	private boolean leaderShipSkills;
	private boolean health;
	
	private Long leaderShipLevelId;
	private Long communicationSkillsStatusId;
	private Long leaderShipSkillsStatusId;
	private Long healthStatusId;
	private String remarks;
	
	private Map<Long,CadreDetailsVO> subMap;
	private List<CadreDetailsVO> subList;
	
	private List<SimpleVO> achievementsList;
	private List<SimpleVO> goalsList;
	
	private List<IdNameVO> leadershiplevelslist;
	private List<IdNameVO> communicationsSkillslist;
	private List<IdNameVO> leadershipSkillslist;
	private List<IdNameVO> healthStatuslist;
	
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
	public boolean isAchievements() {
		return achievements;
	}
	public void setAchievements(boolean achievements) {
		this.achievements = achievements;
	}
	public boolean isGoals() {
		return goals;
	}
	public void setGoals(boolean goals) {
		this.goals = goals;
	}
	public boolean isLeaderShipLevels() {
		return leaderShipLevels;
	}
	public void setLeaderShipLevels(boolean leaderShipLevels) {
		this.leaderShipLevels = leaderShipLevels;
	}
	public boolean isCommunicationSkills() {
		return communicationSkills;
	}
	public void setCommunicationSkills(boolean communicationSkills) {
		this.communicationSkills = communicationSkills;
	}
	public boolean isLeaderShipSkills() {
		return leaderShipSkills;
	}
	public void setLeaderShipSkills(boolean leaderShipSkills) {
		this.leaderShipSkills = leaderShipSkills;
	}
	public boolean isHealth() {
		return health;
	}
	public void setHealth(boolean health) {
		this.health = health;
	}
	
	public Map<Long, CadreDetailsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CadreDetailsVO> subMap) {
		this.subMap = subMap;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public List<CadreDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreDetailsVO> subList) {
		this.subList = subList;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getLeaderShipLevelId() {
		return leaderShipLevelId;
	}
	public void setLeaderShipLevelId(Long leaderShipLevelId) {
		this.leaderShipLevelId = leaderShipLevelId;
	}
	public Long getCommunicationSkillsStatusId() {
		return communicationSkillsStatusId;
	}
	public void setCommunicationSkillsStatusId(Long communicationSkillsStatusId) {
		this.communicationSkillsStatusId = communicationSkillsStatusId;
	}
	public Long getLeaderShipSkillsStatusId() {
		return leaderShipSkillsStatusId;
	}
	public void setLeaderShipSkillsStatusId(Long leaderShipSkillsStatusId) {
		this.leaderShipSkillsStatusId = leaderShipSkillsStatusId;
	}
	public Long getHealthStatusId() {
		return healthStatusId;
	}
	public void setHealthStatusId(Long healthStatusId) {
		this.healthStatusId = healthStatusId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<SimpleVO> getAchievementsList() {
		return achievementsList;
	}
	public void setAchievementsList(List<SimpleVO> achievementsList) {
		this.achievementsList = achievementsList;
	}
	public List<SimpleVO> getGoalsList() {
		return goalsList;
	}
	public void setGoalsList(List<SimpleVO> goalsList) {
		this.goalsList = goalsList;
	}
	public List<IdNameVO> getLeadershiplevelslist() {
		return leadershiplevelslist;
	}
	public void setLeadershiplevelslist(List<IdNameVO> leadershiplevelslist) {
		this.leadershiplevelslist = leadershiplevelslist;
	}
	public List<IdNameVO> getCommunicationsSkillslist() {
		return communicationsSkillslist;
	}
	public void setCommunicationsSkillslist(List<IdNameVO> communicationsSkillslist) {
		this.communicationsSkillslist = communicationsSkillslist;
	}
	public List<IdNameVO> getLeadershipSkillslist() {
		return leadershipSkillslist;
	}
	public void setLeadershipSkillslist(List<IdNameVO> leadershipSkillslist) {
		this.leadershipSkillslist = leadershipSkillslist;
	}
	public List<IdNameVO> getHealthStatuslist() {
		return healthStatuslist;
	}
	public void setHealthStatuslist(List<IdNameVO> healthStatuslist) {
		this.healthStatuslist = healthStatuslist;
	}
         	
}
