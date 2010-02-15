package com.itgrids.partyanalyst.dto;

public class ResultWithExceptionVO {

	private Object finalResult;
	private ResultStatus resultStatus;
	
	public ResultWithExceptionVO(Object finalResult, ResultStatus resultStatus) {
		this.finalResult = finalResult;
		this.resultStatus = resultStatus;
	}
	
	public ResultWithExceptionVO(){
		
	}

	public Object getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Object finalResult) {
		this.finalResult = finalResult;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	
}
