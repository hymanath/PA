/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreCategoryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cadreCategoryId;
	private String cadreCategoryType;
	
	//Getters and Setters
	public Long getCadreCategoryId() {
		return cadreCategoryId;
	}
	public void setCadreCategoryId(Long cadreCategoryId) {
		this.cadreCategoryId = cadreCategoryId;
	}
	public String getCadreCategoryType() {
		return cadreCategoryType;
	}
	public void setCadreCategoryType(String cadreCategoryType) {
		this.cadreCategoryType = cadreCategoryType;
	}

}
