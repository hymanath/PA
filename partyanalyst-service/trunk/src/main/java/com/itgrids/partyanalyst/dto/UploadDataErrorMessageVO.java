package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UploadDataErrorMessageVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> corrections;
	private List<Exception> exceptions;
	
	public UploadDataErrorMessageVO(){
		
	}
	
	public UploadDataErrorMessageVO(List<String> corrections,
			List<Exception> exceptions) {
		this.corrections = corrections;
		this.exceptions = exceptions;
	}

	public List<String> getCorrections() {
		return corrections;
	}
	public void setCorrections(List<String> corrections) {
		this.corrections = corrections;
	}
	public List<Exception> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}
	
	
}
