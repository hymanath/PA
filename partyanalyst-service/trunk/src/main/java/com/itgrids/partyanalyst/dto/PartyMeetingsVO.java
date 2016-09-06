package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartyMeetingsVO implements Serializable{
	
	private Long id;
	private String name;
	
	private Long plannedCount = 0l;
	private Long conductedCount = 0l;
	private Long notConductedCount = 0l;
	
	/*For Core DashBoard  */
	private Long totalCount=0l;
	private Long mayBeCount=0l;
	private Double totalCountPer=0.0;
	private Double  conductedCountPer=0.0;
	private Double notConductedCountPer=0.0;
	private Double mayBeCountPer=0.0;
	private PartyMeetingsVO overAllVO;
	private List<PartyMeetingsVO> partyMettingsVOList;
	
	
	private List<PartyMeetingsVO> subList;
	private Map<Long,PartyMeetingsVO> subMap;
	private Map<String,PartyMeetingsVO> subMap1;
	
	private Integer year;
	private String month;
	

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
	public Long getPlannedCount() {
		return plannedCount;
	}
	public void setPlannedCount(Long plannedCount) {
		this.plannedCount = plannedCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Long getNotConductedCount() {
		return notConductedCount;
	}
	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}
	public List<PartyMeetingsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PartyMeetingsVO> subList) {
		this.subList = subList;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Map<Long, PartyMeetingsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, PartyMeetingsVO> subMap) {
		this.subMap = subMap;
	}
	public Map<String, PartyMeetingsVO> getSubMap1() {
		return subMap1;
	}
	public void setSubMap1(Map<String, PartyMeetingsVO> subMap1) {
		this.subMap1 = subMap1;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMayBeCount() {
		return mayBeCount;
	}
	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}
	public Double getTotalCountPer() {
		return totalCountPer;
	}
	public void setTotalCountPer(Double totalCountPer) {
		this.totalCountPer = totalCountPer;
	}
	public Double getConductedCountPer() {
		return conductedCountPer;
	}
	public void setConductedCountPer(Double conductedCountPer) {
		this.conductedCountPer = conductedCountPer;
	}
	public Double getNotConductedCountPer() {
		return notConductedCountPer;
	}
	public void setNotConductedCountPer(Double notConductedCountPer) {
		this.notConductedCountPer = notConductedCountPer;
	}
	public Double getMayBeCountPer() {
		return mayBeCountPer;
	}
	public void setMayBeCountPer(Double mayBeCountPer) {
		this.mayBeCountPer = mayBeCountPer;
	}
	public PartyMeetingsVO getOverAllVO() {
		return overAllVO;
	}
	public void setOverAllVO(PartyMeetingsVO overAllVO) {
		this.overAllVO = overAllVO;
	}
	public List<PartyMeetingsVO> getPartyMettingsVOList() {
		if(partyMettingsVOList == null){
			partyMettingsVOList = new ArrayList<PartyMeetingsVO>();
		}
		return partyMettingsVOList;
	}	
}
