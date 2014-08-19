package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class DuplicateMobileNumbersVO implements Comparable<DuplicateMobileNumbersVO>{
	
	private String mobileNumber;
	private String voterName;
	private String tehsilName;
	private String muncipalityName;
	private String ind;
	private Long count = 0L;
	private String constituencyName;
	private Long boothId;
	private String partNo;
	private String hoseNo;
	private String userName;
	private String date;
	private Long total = 0L;
	private List<DuplicateMobileNumbersVO> subList = new ArrayList<DuplicateMobileNumbersVO>();
	private List<DuplicateMobileNumbersVO> countList = new ArrayList<DuplicateMobileNumbersVO>();

	
	public List<DuplicateMobileNumbersVO> getCountList() {
		return countList;
	}
	public void setCountList(List<DuplicateMobileNumbersVO> countList) {
		this.countList = countList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public List<DuplicateMobileNumbersVO> getSubList() {
		return subList;
	}
	public void setSubList(List<DuplicateMobileNumbersVO> subList) {
		this.subList = subList;
	}
	public String getHoseNo() {
		return hoseNo;
	}
	public void setHoseNo(String hoseNo) {
		this.hoseNo = hoseNo;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	public String getInd() {
		return ind;
	}
	public void setInd(String ind) {
		this.ind = ind;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	//@Override
	public int compareTo(DuplicateMobileNumbersVO o) {
		return (int) (o.count  - this.count);
	}
}
