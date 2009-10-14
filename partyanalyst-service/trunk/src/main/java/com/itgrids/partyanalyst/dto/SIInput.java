package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashMap;

import com.itgrids.partyanalyst.BaseObject;

@Deprecated
public class SIInput extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Object> inputMap;

	public HashMap<String, Object> getInputMap() {
		return inputMap;
	}

	public void setInputMap(HashMap<String, Object> inputMap) {
		this.inputMap = inputMap;
	}
}
