package com.itgrids.partyanalyst.dto;

import java.util.List;

public class NavigationVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SelectOptionVO stateInfo;
	private List<SelectOptionVO> districtInfo;
	private List<SelectOptionVO> acsInfo;
	private List<SelectOptionVO> pcsInfo;
	private SelectOptionVO mandalInfo;
	private List<SelectOptionVO> problemsCount;
	private List<ProblemBeanVO> approvalProblems;
	private List<SelectOptionVO> messageTypes;
	private List<EntitlementVO> entitlementVO;
	private ResultStatus resultStatus;
	private Long count;
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<EntitlementVO> getEntitlementVO() {
		return entitlementVO;
	}

	public void setEntitlementVO(List<EntitlementVO> entitlementVO) {
		this.entitlementVO = entitlementVO;
	}

	public List<SelectOptionVO> getMessageTypes() {
		return messageTypes;
	}

	public void setMessageTypes(List<SelectOptionVO> messageTypes) {
		this.messageTypes = messageTypes;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getProblemsCount() {
		return problemsCount;
	}

	public void setProblemsCount(List<SelectOptionVO> problemsCount) {
		this.problemsCount = problemsCount;
	}

	public List<ProblemBeanVO> getApprovalProblems() {
		return approvalProblems;
	}

	public void setApprovalProblems(List<ProblemBeanVO> approvalProblems) {
		this.approvalProblems = approvalProblems;
	}

	public SelectOptionVO getStateInfo() {
		return stateInfo;
	}
	
	public void setStateInfo(SelectOptionVO stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	public List<SelectOptionVO> getDistrictInfo() {
		return districtInfo;
	}
	
	public void setDistrictInfo(List<SelectOptionVO> districtInfo) {
		this.districtInfo = districtInfo;
	}
		
	public SelectOptionVO getMandalInfo() {
		return mandalInfo;
	}
	
	public void setMandalInfo(SelectOptionVO mandalInfo) {
		this.mandalInfo = mandalInfo;
	}

	public List<SelectOptionVO> getAcsInfo() {
		return acsInfo;
	}

	public void setAcsInfo(List<SelectOptionVO> acsInfo) {
		this.acsInfo = acsInfo;
	}

	public List<SelectOptionVO> getPcsInfo() {
		return pcsInfo;
	}

	public void setPcsInfo(List<SelectOptionVO> pcsInfo) {
		this.pcsInfo = pcsInfo;
	}
	
}
