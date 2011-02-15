/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class ProblemCompleteDetailsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProblemBeanVO problemBasicDetails;
	private List<ProblemStatusDataVO> problemLifeCycleData;
	private ProblemStatusChangeFactorsVO statusActionInputs;
	
	private ResultStatus resultStatus;

	public ProblemBeanVO getProblemBasicDetails() {
		return problemBasicDetails;
	}

	public void setProblemBasicDetails(ProblemBeanVO problemBasicDetails) {
		this.problemBasicDetails = problemBasicDetails;
	}

	
	public List<ProblemStatusDataVO> getProblemLifeCycleData() {
		return problemLifeCycleData;
	}

	public void setProblemLifeCycleData(
			List<ProblemStatusDataVO> problemLifeCycleData) {
		this.problemLifeCycleData = problemLifeCycleData;
	}

	public ProblemStatusChangeFactorsVO getStatusActionInputs() {
		return statusActionInputs;
	}

	public void setStatusActionInputs(
			ProblemStatusChangeFactorsVO statusActionInputs) {
		this.statusActionInputs = statusActionInputs;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

}
