package com.itgrids.partyanalyst.dto;

import java.util.List;


public class HHQuestionSummaryReportVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long questionId;
	private String question;
	private Long optionId;
	private String option;
	private List<HHQuestionSummaryReportVO> optionsList;
	private Long optsCount;
	
	private String percentage;
	
	private Long totalCount;
	private Long panchayatId;
	private String panchayat;
	private Long constituencyId;
	private String constituency;
	private List<HHQuestionSummaryReportVO> panchayatList;
	
	
	public List<HHQuestionSummaryReportVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<HHQuestionSummaryReportVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getOptsCount() {
		return optsCount;
	}
	public void setOptsCount(Long optsCount) {
		this.optsCount = optsCount;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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
	public List<HHQuestionSummaryReportVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<HHQuestionSummaryReportVO> optionsList) {
		this.optionsList = optionsList;
	}
	
	
	
}
