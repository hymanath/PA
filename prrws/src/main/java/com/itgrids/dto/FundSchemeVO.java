package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FundSchemeVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long count;
	private Long totalCount=0L;
	private String perc;
	private Long yearId;
	private String year;
	private String amount;
	private AddressVO addressVO;
	private List<FundSchemeVO> subList = new ArrayList<FundSchemeVO>(0);
	private String locationIdStr;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<FundSchemeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<FundSchemeVO> subList) {
		this.subList = subList;
	}
	public Long getYearId() {
		return yearId;
	}
	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
}
