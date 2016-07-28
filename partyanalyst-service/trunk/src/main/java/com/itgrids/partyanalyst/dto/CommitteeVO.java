package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CommitteeVO implements Serializable {
	
	private Long id;
	private String name;
	
	private Long completedCount = 0l;
	private Long startedCount = 0l;
	private Long yetToStartCount = 0l;
	
	private Map<Long,CommitteeVO> subMap;
	private List<CommitteeVO> subList;
	
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
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getYetToStartCount() {
		return yetToStartCount;
	}
	public void setYetToStartCount(Long yetToStartCount) {
		this.yetToStartCount = yetToStartCount;
	}
	public Map<Long, CommitteeVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CommitteeVO> subMap) {
		this.subMap = subMap;
	}
	public List<CommitteeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CommitteeVO> subList) {
		this.subList = subList;
	}
	
	
	
}
