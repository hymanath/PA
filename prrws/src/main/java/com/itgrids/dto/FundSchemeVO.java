package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FundSchemeVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long count;
	private Long totalCount=0L;
	private String perc;
	private Long yearId;
	private String year;
	private String amount="0.0";
	private AddressVO addressVO;
	private List<FundSchemeVO> subList = new ArrayList<FundSchemeVO>(0);
	private String locationIdStr;
	private Map<Long,String> subMap1;
	private Map<String,Long> subMap2;
	private Set<Long> locationIdSet;
	
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
	public Map<Long, String> getSubMap1() {
		return subMap1;
	}
	public void setSubMap1(Map<Long, String> subMap1) {
		this.subMap1 = subMap1;
	}
	public Map<String, Long> getSubMap2() {
		return subMap2;
	}
	public void setSubMap2(Map<String, Long> subMap2) {
		this.subMap2 = subMap2;
	}
	public Set<Long> getLocationIdSet() {
		return locationIdSet;
	}
	public void setLocationIdSet(Set<Long> locationIdSet) {
		this.locationIdSet = locationIdSet;
	}	
	
}
