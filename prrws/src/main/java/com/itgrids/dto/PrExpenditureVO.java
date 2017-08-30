package com.itgrids.dto;

import java.io.Serializable;

public class PrExpenditureVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long locationId;
	private String locationName;
	private String grossAmount;
	private String deductions;
	private String netAmount;
	
	private AddressVO addressVO;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getDeductions() {
		return deductions;
	}

	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	
	
}
