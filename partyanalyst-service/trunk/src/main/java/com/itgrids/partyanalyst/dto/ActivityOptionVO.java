package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActivityOptionVO implements Serializable{
	private Long		optionId;
	private String		option;
	
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	
}
