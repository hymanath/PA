package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class CadreFeedbackVO implements java.io.Serializable{
    
	
	private Long count=0l;
	private Long id;
	private String name;
	private Long achievementCount=0l;
	private Long gaoalCount=0l;
	
	private Map<String,CadreFeedbackVO> map;
	private Map<Long,CadreFeedbackVO> subMap;
	
	private List<CadreFeedbackVO> list;
	private List<CadreFeedbackVO> subList;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Map<String, CadreFeedbackVO> getMap() {
		return map;
	}
	public void setMap(Map<String, CadreFeedbackVO> map) {
		this.map = map;
	}
	public Map<Long, CadreFeedbackVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CadreFeedbackVO> subMap) {
		this.subMap = subMap;
	}
	public List<CadreFeedbackVO> getList() {
		return list;
	}
	public void setList(List<CadreFeedbackVO> list) {
		this.list = list;
	}
	public List<CadreFeedbackVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreFeedbackVO> subList) {
		this.subList = subList;
	}
	public Long getAchievementCount() {
		return achievementCount;
	}
	public void setAchievementCount(Long achievementCount) {
		this.achievementCount = achievementCount;
	}
	public Long getGaoalCount() {
		return gaoalCount;
	}
	public void setGaoalCount(Long gaoalCount) {
		this.gaoalCount = gaoalCount;
	}
    	
}
