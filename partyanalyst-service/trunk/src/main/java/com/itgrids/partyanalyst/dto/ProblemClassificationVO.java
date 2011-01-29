/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 25, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class ProblemClassificationVO extends ResultStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long classificationId;
	private String classificationType;
	
	private List<ProblemBeanVO> problemsList;

	public Long getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Long classificationId) {
		this.classificationId = classificationId;
	}

	public String getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}

	public List<ProblemBeanVO> getProblemsList() {
		return problemsList;
	}

	public void setProblemsList(List<ProblemBeanVO> problemsList) {
		this.problemsList = problemsList;
	}

}
