package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;


public class CadrePerformanceVO implements java.io.Serializable{

	private Long id;
	private String name;
	private CadreBasicPerformaceVO cadreBasicPerformaceVO;
	private CadreEventsVO cadreEventsVO;
	private CadreStatsVO cadreStatsVO;
	private List<CadrePerformanceVO> subList = new ArrayList<CadrePerformanceVO>(0);
	
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
	public CadreBasicPerformaceVO getCadreBasicPerformaceVO() {
		return cadreBasicPerformaceVO;
	}
	public void setCadreBasicPerformaceVO(
			CadreBasicPerformaceVO cadreBasicPerformaceVO) {
		this.cadreBasicPerformaceVO = cadreBasicPerformaceVO;
	}
	public CadreEventsVO getCadreEventsVO() {
		return cadreEventsVO;
	}
	public void setCadreEventsVO(CadreEventsVO cadreEventsVO) {
		this.cadreEventsVO = cadreEventsVO;
	}
	public CadreStatsVO getCadreStatsVO() {
		return cadreStatsVO;
	}
	public void setCadreStatsVO(CadreStatsVO cadreStatsVO) {
		this.cadreStatsVO = cadreStatsVO;
	}
	public List<CadrePerformanceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadrePerformanceVO> subList) {
		this.subList = subList;
	}
	
}
