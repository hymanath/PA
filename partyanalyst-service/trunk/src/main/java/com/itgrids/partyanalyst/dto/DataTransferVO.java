package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DataTransferVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private Object presentYearChart;
	private Object previousYearChart;
	private Object latestYearChart;
	private List<CandidateVO> candidateVO;
	private ResultStatus resultStatus;
	private String loginStatus;
	
	
	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	public List<CandidateVO> getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(List<CandidateVO> candidateVO) {
		this.candidateVO = candidateVO;
	}

	public Object getPresentYearChart() {
		return presentYearChart;
	}
	
	public void setPresentYearChart(Object presentYearChart) {
		this.presentYearChart = presentYearChart;
	}
	
	public Object getPreviousYearChart() {
		return previousYearChart;
	}
	
	public void setPreviousYearChart(Object previousYearChart) {
		this.previousYearChart = previousYearChart;
	}

	public Object getLatestYearChart() {
		return latestYearChart;
	}

	public void setLatestYearChart(Object latestYearChart) {
		this.latestYearChart = latestYearChart;
	}

}
