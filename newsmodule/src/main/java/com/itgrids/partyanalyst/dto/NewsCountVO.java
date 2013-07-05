package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsCountVO extends ResultStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long constituencyScopeId;
	private Long constituencyValue;
	private Long tehsilScopeId;
	private List<Long> tehsilIds;
	private Long hamletScopeId;
	private List<Long> hamletIds;
	private List<Long> candidateIds;
	private Long categoryId;
	private Long newsImportanceId;
	private int startIndex;
	private int maxResults;
	private Long tehsilValue;
	private List<Long> constituencyValuesList;
	private Long muncipalityScopeId;
	private List<Long> muncipalityValuesList;
	private List<Long> wardIdsList;
	private Long wardScopeId;
	private Long totalNewsCount= 0L;
	private Long responseNewsCount = 0L;
	private Long notResponseNewsCount = 0L;
	private String name;
	private List<NewsCountVO> newsCountVOList = new ArrayList<NewsCountVO>(0);
	private Long id;
	private SelectOptionVO selectOptionVO = new SelectOptionVO();
	
	
	public List<Long> getWardIdsList() {
		return wardIdsList;
	}
	public void setWardIdsList(List<Long> wardIdsList) {
		this.wardIdsList = wardIdsList;
	}
	public Long getWardScopeId() {
		return wardScopeId;
	}
	public void setWardScopeId(Long wardScopeId) {
		this.wardScopeId = wardScopeId;
	}
	public Long getMuncipalityScopeId() {
		return muncipalityScopeId;
	}
	public void setMuncipalityScopeId(Long muncipalityScopeId) {
		this.muncipalityScopeId = muncipalityScopeId;
	}
	public List<Long> getMuncipalityValuesList() {
		return muncipalityValuesList;
	}
	public void setMuncipalityValuesList(List<Long> muncipalityValuesList) {
		this.muncipalityValuesList = muncipalityValuesList;
	}
	public List<Long> getConstituencyValuesList() {
		return constituencyValuesList;
	}
	public void setConstituencyValuesList(List<Long> constituencyValuesList) {
		this.constituencyValuesList = constituencyValuesList;
	}
	public Long getTehsilValue() {
		return tehsilValue;
	}
	public void setTehsilValue(Long tehsilValue) {
		this.tehsilValue = tehsilValue;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	public Long getNewsImportanceId() {
		return newsImportanceId;
	}
	public void setNewsImportanceId(Long newsImportanceId) {
		this.newsImportanceId = newsImportanceId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<Long> getCandidateIds() {
		return candidateIds;
	}
	public void setCandidateIds(List<Long> candidateIds) {
		this.candidateIds = candidateIds;
	}
	public Long getConstituencyScopeId() {
		return constituencyScopeId;
	}
	public void setConstituencyScopeId(Long constituencyScopeId) {
		this.constituencyScopeId = constituencyScopeId;
	}
	public Long getConstituencyValue() {
		return constituencyValue;
	}
	public void setConstituencyValue(Long constituencyValue) {
		this.constituencyValue = constituencyValue;
	}
	public Long getTehsilScopeId() {
		return tehsilScopeId;
	}
	public void setTehsilScopeId(Long tehsilScopeId) {
		this.tehsilScopeId = tehsilScopeId;
	}
	public List<Long> getTehsilIds() {
		return tehsilIds;
	}
	public void setTehsilIds(List<Long> tehsilIds) {
		this.tehsilIds = tehsilIds;
	}
	public Long getHamletScopeId() {
		return hamletScopeId;
	}
	public void setHamletScopeId(Long hamletScopeId) {
		this.hamletScopeId = hamletScopeId;
	}
	public List<Long> getHamletIds() {
		return hamletIds;
	}
	public void setHamletIds(List<Long> hamletIds) {
		this.hamletIds = hamletIds;
	}
	public Long getTotalNewsCount() {
		return totalNewsCount;
	}
	public void setTotalNewsCount(Long totalNewsCount) {
		this.totalNewsCount = totalNewsCount;
	}
	
	public Long getResponseNewsCount() {
		return responseNewsCount;
	}
	public void setResponseNewsCount(Long responseNewsCount) {
		this.responseNewsCount = responseNewsCount;
	}
	public Long getNotResponseNewsCount() {
		return notResponseNewsCount;
	}
	public void setNotResponseNewsCount(Long notResponseNewsCount) {
		this.notResponseNewsCount = notResponseNewsCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NewsCountVO> getNewsCountVOList() {
		return newsCountVOList;
	}
	public void setNewsCountVOList(List<NewsCountVO> newsCountVOList) {
		this.newsCountVOList = newsCountVOList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}
	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

}
