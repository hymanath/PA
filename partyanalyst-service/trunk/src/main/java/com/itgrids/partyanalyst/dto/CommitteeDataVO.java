package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CommitteeDataVO implements Serializable{
	
	private Long completedCount = 0l;
	private Long startedCount = 0l;
	private Long notStartedCount =0l;
	private Long totalCount = 0l;
	
	private Long id;
	private String name;
	
	private CommitteeDataVO mainVO;
	private CommitteeDataVO affliatedVO; 
	
	private List<CommitteeDataVO> subList;
	private Map<Long,CommitteeDataVO> subMap;
	
	
	private Double startedPerc = 0.0;
	private Double completedPerc = 0.0;
	private Double notStartedPerc = 0.0;
	
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
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
	public CommitteeDataVO getMainVO() {
		return mainVO;
	}
	public void setMainVO(CommitteeDataVO mainVO) {
		this.mainVO = mainVO;
	}
	public CommitteeDataVO getAffliatedVO() {
		return affliatedVO;
	}
	public void setAffliatedVO(CommitteeDataVO affliatedVO) {
		this.affliatedVO = affliatedVO;
	}
	public List<CommitteeDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CommitteeDataVO> subList) {
		this.subList = subList;
	}
	public Double getStartedPerc() {
		return startedPerc;
	}
	public void setStartedPerc(Double startedPerc) {
		this.startedPerc = startedPerc;
	}
	public Double getCompletedPerc() {
		return completedPerc;
	}
	public void setCompletedPerc(Double completedPerc) {
		this.completedPerc = completedPerc;
	}
	public Map<Long, CommitteeDataVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CommitteeDataVO> subMap) {
		this.subMap = subMap;
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
