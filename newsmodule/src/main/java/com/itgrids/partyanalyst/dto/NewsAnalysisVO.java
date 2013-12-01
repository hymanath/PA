package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class NewsAnalysisVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2329516280426212003L;

	 private Long positiveCount = 0l;
	 private Long negativeCount = 0l;
	 private Long neutralCount = 0l;
	 private Long total = 0l;
	 private Long sourceCandId;
	 private Long destiCandId;
	 private Long sourcePartyId;
	 private Long destiPartyId;
	 private Long locationLvl;
	 private Long locationId;
	 private Long sourceId;
	 private Long categoryId;
	 private Long keywordId;
	 private String benifitsFor;
	 private List<String> levels;
	 private List<NewsAnalysisVO> subList;
	 private boolean subListPresent;
	 private String name;
	 private String buildMethod;
	 private Integer rowSpan = 0;
	 private Long sourceBenifitId;
	 private Long destiBenifitId;
	 
	public Long getPositiveCount() {
		return positiveCount;
	}
	
	public void setPositiveCount(Long positiveCount) {
		this.positiveCount = positiveCount;
	}
	
	public Long getNegativeCount() {
		return negativeCount;
	}
	
	public void setNegativeCount(Long negativeCount) {
		this.negativeCount = negativeCount;
	}
	
	public Long getNeutralCount() {
		return neutralCount;
	}
	
	public void setNeutralCount(Long neutralCount) {
		this.neutralCount = neutralCount;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public Long getLocationId() {
		return locationId;
	}
	
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public Long getSourceId() {
		return sourceId;
	}
	
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getKeywordId() {
		return keywordId;
	}
	
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}

	public Long getSourceCandId() {
		return sourceCandId;
	}

	public void setSourceCandId(Long sourceCandId) {
		this.sourceCandId = sourceCandId;
	}

	public Long getDestiCandId() {
		return destiCandId;
	}

	public void setDestiCandId(Long destiCandId) {
		this.destiCandId = destiCandId;
	}

	public Long getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Long sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}

	public Long getDestiPartyId() {
		return destiPartyId;
	}

	public void setDestiPartyId(Long destiPartyId) {
		this.destiPartyId = destiPartyId;
	}

	public String getBenifitsFor() {
		return benifitsFor;
	}

	public void setBenifitsFor(String benifitsFor) {
		this.benifitsFor = benifitsFor;
	}

	public List<String> getLevels() {
		return levels;
	}

	public void setLevels(List<String> levels) {
		this.levels = levels;
	}

	public List<NewsAnalysisVO> getSubList() {
		return subList;
	}

	public void setSubList(List<NewsAnalysisVO> subList) {
		this.subList = subList;
	}

	public boolean isSubListPresent() {
		return subListPresent;
	}

	public void setSubListPresent(boolean subListPresent) {
		this.subListPresent = subListPresent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuildMethod() {
		return buildMethod;
	}

	public void setBuildMethod(String buildMethod) {
		this.buildMethod = buildMethod;
	}

	public Long getLocationLvl() {
		return locationLvl;
	}

	public void setLocationLvl(Long locationLvl) {
		this.locationLvl = locationLvl;
	}

	public Integer getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(Integer rowSpan) {
		this.rowSpan = rowSpan;
	}

	public Long getSourceBenifitId() {
		return sourceBenifitId;
	}

	public void setSourceBenifitId(Long sourceBenifitId) {
		this.sourceBenifitId = sourceBenifitId;
	}

	public Long getDestiBenifitId() {
		return destiBenifitId;
	}

	public void setDestiBenifitId(Long destiBenifitId) {
		this.destiBenifitId = destiBenifitId;
	}

	
}
