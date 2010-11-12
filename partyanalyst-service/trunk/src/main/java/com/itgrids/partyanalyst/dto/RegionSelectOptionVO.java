/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class RegionSelectOptionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String label;
	private List<SelectOptionVO> optionsList;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<SelectOptionVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<SelectOptionVO> optionsList) {
		this.optionsList = optionsList;
	}
	
	

}
