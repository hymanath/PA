package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class OptionVO implements Serializable,Comparable<OptionVO> {
	private Long optionId;
	private String option;
	private Double percentage;
	private Long votesObtained;
	
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

	public Long getVotesObtained() {
		return votesObtained;
	}

	public void setVotesObtained(Long votesObtained) {
		this.votesObtained = votesObtained;
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

	@Override
	public int compareTo(OptionVO obj) {
		if(obj instanceof OptionVO)
		{
			OptionVO vo = (OptionVO)obj;
			return option.compareToIgnoreCase(vo.getOption());
		}
		return 0;
	}
	
		
	
	

}
