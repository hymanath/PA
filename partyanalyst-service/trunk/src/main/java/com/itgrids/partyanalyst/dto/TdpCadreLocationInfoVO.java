package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TdpCadreLocationInfoVO implements Serializable{
	
	
	private static final long serialVersionUID = 1652069565905488620L;
	private Long id;
	private String name;
	
	private Long locationScopeId;
	private Long locationValue;
	
	private String surveyDateStr;
	private Date   surveyDate;
	private Long cadre2016Records = 0l;
	private Long cadre2016RenewalRecords = 0l;
	private Long cadre2016NewRecords = 0l ;
	
	private Double cadre2016RecordsPerc = 0.0;
	private Double cadre2016RenewalRecordsPerc = 0.0;
	private Double cadre2016NewRecordsPerc = 0.0;
	
	private String dateType;
	
	private Long cadreCount = 0l;
	private String cadrePercent ;
	
	private List<TdpCadreLocationInfoVO> assemblyList;
	private List<TdpCadreLocationInfoVO> parliamentList;
	private List<TdpCadreLocationInfoVO> districtList;
	private List<TdpCadreLocationInfoVO> stateList;
	private List<TdpCadreLocationInfoVO> tehsilList;
	
	private Map<Long,TdpCadreLocationInfoVO> subMap;
	private Map<String,TdpCadreLocationInfoVO> subMap1;
	private List<TdpCadreLocationInfoVO> subList;
	
	private Long constituencyId;
	
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
	public Long getCadre2016Records() {
		return cadre2016Records;
	}
	public void setCadre2016Records(Long cadre2016Records) {
		this.cadre2016Records = cadre2016Records;
	}
	public Long getCadre2016RenewalRecords() {
		return cadre2016RenewalRecords;
	}
	public void setCadre2016RenewalRecords(Long cadre2016RenewalRecords) {
		this.cadre2016RenewalRecords = cadre2016RenewalRecords;
	}
	public Long getCadre2016NewRecords() {
		return cadre2016NewRecords;
	}
	public void setCadre2016NewRecords(Long cadre2016NewRecords) {
		this.cadre2016NewRecords = cadre2016NewRecords;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public List<TdpCadreLocationInfoVO> getAssemblyList() {
		return assemblyList;
	}
	public void setAssemblyList(List<TdpCadreLocationInfoVO> assemblyList) {
		this.assemblyList = assemblyList;
	}
	public List<TdpCadreLocationInfoVO> getParliamentList() {
		return parliamentList;
	}
	public void setParliamentList(List<TdpCadreLocationInfoVO> parliamentList) {
		this.parliamentList = parliamentList;
	}
	public List<TdpCadreLocationInfoVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<TdpCadreLocationInfoVO> districtList) {
		this.districtList = districtList;
	}
	public List<TdpCadreLocationInfoVO> getStateList() {
		return stateList;
	}
	public void setStateList(List<TdpCadreLocationInfoVO> stateList) {
		this.stateList = stateList;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public Double getCadre2016RecordsPerc() {
		return cadre2016RecordsPerc;
	}
	public void setCadre2016RecordsPerc(Double cadre2016RecordsPerc) {
		this.cadre2016RecordsPerc = cadre2016RecordsPerc;
	}
	public Double getCadre2016RenewalRecordsPerc() {
		return cadre2016RenewalRecordsPerc;
	}
	public void setCadre2016RenewalRecordsPerc(Double cadre2016RenewalRecordsPerc) {
		this.cadre2016RenewalRecordsPerc = cadre2016RenewalRecordsPerc;
	}
	public Double getCadre2016NewRecordsPerc() {
		return cadre2016NewRecordsPerc;
	}
	public void setCadre2016NewRecordsPerc(Double cadre2016NewRecordsPerc) {
		this.cadre2016NewRecordsPerc = cadre2016NewRecordsPerc;
	}
	public Long getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Long cadreCount) {
		this.cadreCount = cadreCount;
	}
	public String getCadrePercent() {
		return cadrePercent;
	}
	public void setCadrePercent(String cadrePercent) {
		this.cadrePercent = cadrePercent;
	}
	
	public Map<Long, TdpCadreLocationInfoVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, TdpCadreLocationInfoVO> subMap) {
		this.subMap = subMap;
	}
	public String getSurveyDateStr() {
		return surveyDateStr;
	}
	public void setSurveyDateStr(String surveyDateStr) {
		this.surveyDateStr = surveyDateStr;
	}
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	public List<TdpCadreLocationInfoVO> getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(List<TdpCadreLocationInfoVO> tehsilList) {
		this.tehsilList = tehsilList;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public List<TdpCadreLocationInfoVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TdpCadreLocationInfoVO> subList) {
		this.subList = subList;
	}
	public Map<String, TdpCadreLocationInfoVO> getSubMap1() {
		return subMap1;
	}
	public void setSubMap1(Map<String, TdpCadreLocationInfoVO> subMap1) {
		this.subMap1 = subMap1;
	}
	
}
