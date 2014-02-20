package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AnalysisBasicInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190790853326019708L;

	private NewsAnalysisVO totalCount;
	private NewsAnalysisVO ownNews;
	private NewsAnalysisVO onOtherParty;
	private NewsAnalysisVO onMe;
	private NewsAnalysisVO inMedia;
	private String name;
	private Long id;
	
	
	public NewsAnalysisVO getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(NewsAnalysisVO totalCount) {
		this.totalCount = totalCount;
	}
	
	public NewsAnalysisVO getOwnNews() {
		return ownNews;
	}
	
	public void setOwnNews(NewsAnalysisVO ownNews) {
		this.ownNews = ownNews;
	}
	
	public NewsAnalysisVO getOnOtherParty() {
		return onOtherParty;
	}
	
	public void setOnOtherParty(NewsAnalysisVO onOtherParty) {
		this.onOtherParty = onOtherParty;
	}
	
	public NewsAnalysisVO getOnMe() {
		return onMe;
	}
	
	public void setOnMe(NewsAnalysisVO onMe) {
		this.onMe = onMe;
	}
	
	public NewsAnalysisVO getInMedia() {
		return inMedia;
	}
	
	public void setInMedia(NewsAnalysisVO inMedia) {
		this.inMedia = inMedia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
