package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class IVRResponseVO implements Serializable{
     
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsAnswered() {
		return isAnswered;
	}
	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	private Long totalCount;
	private Long answeredcount;
	private Long unAnsweredCount;
	
	private Boolean isAnswered;
	private String name;
	private String question;
	private String option;
	private Long optionId;
	private Long optionTypeId;
	private Long optionCount;
	private List<IVRResponseVO> optionsList;
	
	private List<String> descriptionList;
	private String tdpCadreName;
	private String dateString;
	private String description;
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getAnsweredcount() {
		return answeredcount;
	}
	public void setAnsweredcount(Long answeredcount) {
		this.answeredcount = answeredcount;
	}
	public Long getUnAnsweredCount() {
		return unAnsweredCount;
	}
	public void setUnAnsweredCount(Long unAnsweredCount) {
		this.unAnsweredCount = unAnsweredCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public List<IVRResponseVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<IVRResponseVO> optionsList) {
		this.optionsList = optionsList;
	}
	public Long getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(Long optionCount) {
		this.optionCount = optionCount;
	}
	public List<String> getDescriptionList() {
		return descriptionList;
	}
	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	public String getTdpCadreName() {
		return tdpCadreName;
	}
	public void setTdpCadreName(String tdpCadreName) {
		this.tdpCadreName = tdpCadreName;
	}
    	
}
