package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashMap;

import com.itgrids.partyanalyst.BaseObject;

@Deprecated
public class SIResult extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Throwable exception;
	
	private boolean fail;
	
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public boolean isFail() {
		return fail;
	}

	public void setFail(boolean fail) {
		this.fail = fail;
	}

}
