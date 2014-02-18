package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PoliticalFeedBackVO implements Serializable{

	
	
	private static final long serialVersionUID = -1564231346422799075L;
	private Long politicalFeedBackVOId;
	private Long parlimentId;
	private String parlimentName;
	private Long constituencyId;
	private String assemblyName;
	private String summary;
	private String impNews;
	private String cmPoliticalFeedBack;
	private String otherPoliticalFeedBack;
	private List<String> actionItems;
	private String createdDate;
	private Date insertedDate;
	private Date updatedDate;
	private String isDeleted;
	private Long userId;
	
	private List<PoliticalFeedBackVO> politicalFeedBackVOList;

	public Long getPoliticalFeedBackVOId() {
		return politicalFeedBackVOId;
	}

	public void setPoliticalFeedBackVOId(Long politicalFeedBackVOId) {
		this.politicalFeedBackVOId = politicalFeedBackVOId;
	}

	public Long getParlimentId() {
		return parlimentId;
	}

	public void setParlimentId(Long parlimentId) {
		this.parlimentId = parlimentId;
	}

	public String getParlimentName() {
		return parlimentName;
	}

	public void setParlimentName(String parlimentName) {
		this.parlimentName = parlimentName;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getAssemblyName() {
		return assemblyName;
	}

	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImpNews() {
		return impNews;
	}

	public void setImpNews(String impNews) {
		this.impNews = impNews;
	}

	public String getCmPoliticalFeedBack() {
		return cmPoliticalFeedBack;
	}

	public void setCmPoliticalFeedBack(String cmPoliticalFeedBack) {
		this.cmPoliticalFeedBack = cmPoliticalFeedBack;
	}

	public String getOtherPoliticalFeedBack() {
		return otherPoliticalFeedBack;
	}

	public void setOtherPoliticalFeedBack(String otherPoliticalFeedBack) {
		this.otherPoliticalFeedBack = otherPoliticalFeedBack;
	}

	public List<String> getActionItems() {
		return actionItems;
	}

	public void setActionItems(List<String> actionItems) {
		this.actionItems = actionItems;
	}

	public List<PoliticalFeedBackVO> getPoliticalFeedBackVOList() {
		return politicalFeedBackVOList;
	}

	public void setPoliticalFeedBackVOList(
			List<PoliticalFeedBackVO> politicalFeedBackVOList) {
		this.politicalFeedBackVOList = politicalFeedBackVOList;
	}

	

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
