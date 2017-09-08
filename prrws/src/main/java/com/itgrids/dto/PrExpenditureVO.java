package com.itgrids.dto;

import java.io.Serializable;

public class PrExpenditureVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long locationId;
	private String locationName;
	private Long grossAmount;
	private Long deductions;
	private Long netAmount;
	
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
	
	public Long getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Long grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Long getDeductions() {
		return deductions;
	}

	public void setDeductions(Long deductions) {
		this.deductions = deductions;
	}

	public Long getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Long netAmount) {
		this.netAmount = netAmount;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	
	
}
