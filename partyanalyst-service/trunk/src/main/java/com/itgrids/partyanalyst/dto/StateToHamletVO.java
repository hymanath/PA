package com.itgrids.partyanalyst.dto;

public class StateToHamletVO extends ResultStatus{
	private SelectOptionVO state;
	private SelectOptionVO district;
	private SelectOptionVO constituency;
	private SelectOptionVO mandal;
	private SelectOptionVO revenueVillage;
	private SelectOptionVO hamlet;
	public SelectOptionVO getState() {
		return state;
	}
	public void setState(SelectOptionVO state) {
		this.state = state;
	}
	public SelectOptionVO getDistrict() {
		return district;
	}
	public void setDistrict(SelectOptionVO district) {
		this.district = district;
	}
	public SelectOptionVO getConstituency() {
		return constituency;
	}
	public void setConstituency(SelectOptionVO constituency) {
		this.constituency = constituency;
	}
	public SelectOptionVO getMandal() {
		return mandal;
	}
	public void setMandal(SelectOptionVO mandal) {
		this.mandal = mandal;
	}
	public SelectOptionVO getRevenueVillage() {
		return revenueVillage;
	}
	public void setRevenueVillage(SelectOptionVO revenueVillage) {
		this.revenueVillage = revenueVillage;
	}
	public SelectOptionVO getHamlet() {
		return hamlet;
	}
	public void setHamlet(SelectOptionVO hamlet) {
		this.hamlet = hamlet;
	}
}
