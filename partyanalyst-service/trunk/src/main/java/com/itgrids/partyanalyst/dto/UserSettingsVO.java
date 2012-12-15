package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserSettingsVO {
	
	private Long userId;
	private Long contentId;
	private Long settingsOptionId;
	private String contentName;
	private List<String> optionsList;
	private Long selectedOptionId;
	private String selectedOptionName;
	
	

	public String getSelectedOptionName() {
		return selectedOptionName;
	}
	public void setSelectedOptionName(String selectedOptionName) {
		this.selectedOptionName = selectedOptionName;
	}
	public Long getSelectedOptionId() {
		return selectedOptionId;
	}
	public void setSelectedOptionId(Long selectedOptionId) {
		this.selectedOptionId = selectedOptionId;
	}
	public List<String> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<String> optionsList) {
		this.optionsList = optionsList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public Long getSettingsOptionId() {
		return settingsOptionId;
	}
	public void setSettingsOptionId(Long settingsOptionId) {
		this.settingsOptionId = settingsOptionId;
	}
	
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
}
