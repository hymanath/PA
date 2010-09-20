package com.itgrids.partyanalyst.dto;

public class OptionVO {
	private Long optionId;
	private String option;
	private Double percentage;
	
	public OptionVO() {
	}

	/**
	 * @param optionId
	 * @param option
	 */
	public OptionVO(Long optionId, String option,Double percentage) {
		super();
		this.optionId = optionId;
		this.option = option;
		this.percentage = percentage;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
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
