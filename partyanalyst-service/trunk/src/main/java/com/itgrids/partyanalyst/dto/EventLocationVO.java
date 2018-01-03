package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventLocationVO {

	private Long locationId;
	private String locationName;
	private Long totalCount=0l  ;
	private Long conductedCount=0l;
	private String questionName;
	private Double percentage;
	private Long questionId;
	private String optionName;
	private String optionType;
	private Long optionId;
	private Long count=0l;
	private List<EventLocationVO> questionList= new ArrayList<EventLocationVO>();
	private List<EventLocationVO> optionList= new ArrayList<EventLocationVO>();
	
	private Long districtId;
	private Long parlimentId;
	private String districtName;
	private String parliamentName;
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<EventLocationVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<EventLocationVO> questionList) {
		this.questionList = questionList;
	}
	public List<EventLocationVO> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<EventLocationVO> optionList) {
		this.optionList = optionList;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getParlimentId() {
		return parlimentId;
	}
	public void setParlimentId(Long parlimentId) {
		this.parlimentId = parlimentId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	
	
}
