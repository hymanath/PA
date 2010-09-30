package com.itgrids.partyanalyst.dto;

public class ResultWithExceptionVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object finalResult;
	
	public ResultWithExceptionVO(Object finalResult) {
		this.finalResult = finalResult;
		
	}
	
	public ResultWithExceptionVO(){
		
	}

	public Object getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(Object finalResult) {
		this.finalResult = finalResult;
	}
	
}
