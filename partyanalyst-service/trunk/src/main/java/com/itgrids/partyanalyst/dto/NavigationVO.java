package com.itgrids.partyanalyst.dto;

import java.util.List;

public class NavigationVO extends ResultStatus{

	private SelectOptionVO stateInfo;
	private List<SelectOptionVO> districtInfo;
	private List<SelectOptionVO> acsInfo;
	private List<SelectOptionVO> pcsInfo;
	private SelectOptionVO mandalInfo;
	
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
