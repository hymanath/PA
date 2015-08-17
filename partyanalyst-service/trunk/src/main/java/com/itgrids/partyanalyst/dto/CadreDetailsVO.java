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
	
	private Long batchId;
	private String batchCode;
	
	private boolean achievements;
	private boolean goals;
	private boolean leaderShipLevels;
	private boolean communicationSkills;
	private boolean leaderShipSkills;
	private boolean health;
	
	private Map<Long,CadreDetailsVO> subMap;
	private List<CadreDetailsVO> subList;
	
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
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
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
	
}
