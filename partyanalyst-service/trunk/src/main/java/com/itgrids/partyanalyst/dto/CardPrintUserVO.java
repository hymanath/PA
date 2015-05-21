package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CardPrintUserVO {

	private String uname;
	private String pwd;
	private String fromDate;
	private String endDate;
	private Long count = 0l;
	private Long total = 0l;
	private String membershipNumber;
	private String status;
	private Long id;
	private List<CardPrintUserVO> subList= new ArrayList<CardPrintUserVO>();
	private String date;
	private Long printCnt =0l;
	private Long reprintCnt =0l;
	
			
	
	
	
	public Long getPrintCnt() {
		return printCnt;
	}
	public void setPrintCnt(Long printCnt) {
		this.printCnt = printCnt;
	}
	public Long getReprintCnt() {
		return reprintCnt;
	}
	public void setReprintCnt(Long reprintCnt) {
		this.reprintCnt = reprintCnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CardPrintUserVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CardPrintUserVO> subList) {
		this.subList = subList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMembershipNumber() {
		return membershipNumber;
	}
	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	

}
