package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
/**
 * used in the DTO classes to know the result status of a task
 * @author Narender
 *
 */
public class ResultStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4612224221019720019L;
	
	private int resultCode;
    private Throwable exceptionEncountered;
    private boolean isResultPartial;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Throwable getExceptionEncountered() {
		return exceptionEncountered;
	}
	public void setExceptionEncountered(Throwable exceptionEncountered) {
		this.exceptionEncountered = exceptionEncountered;
	}
	public boolean isResultPartial() {
		return isResultPartial;
	}
	public void setResultPartial(boolean isResultPartial) {
		this.isResultPartial = isResultPartial;
	}
    
    
}
