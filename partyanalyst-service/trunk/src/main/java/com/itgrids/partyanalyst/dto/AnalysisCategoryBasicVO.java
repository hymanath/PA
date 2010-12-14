/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AnalysisCategoryBasicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2651595556479899329L;
	
	private Long categoryId;
	private String categoryType;
	private Long categoryResultCount;
	private Float categoryScore;
	
	
	public Float getCategoryScore() {
		return categoryScore;
	}
	public void setCategoryScore(Float categoryScore) {
		this.categoryScore = categoryScore;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public Long getCategoryResultCount() {
		return categoryResultCount;
	}
	public void setCategoryResultCount(Long categoryResultCount) {
		this.categoryResultCount = categoryResultCount;
	}

}
