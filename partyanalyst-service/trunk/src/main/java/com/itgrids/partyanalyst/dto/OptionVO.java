package com.itgrids.partyanalyst.dto;

public class OptionVO {
	private Long optionId;
	private String option;
	
	public OptionVO() {
		}

	/**
	 * @param optionId
	 * @param option
	 */
	public OptionVO(Long optionId, String option) {
		super();
		this.optionId = optionId;
		this.option = option;
	}

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
