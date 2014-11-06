package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreRegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883187909007470353L;

	private Long totalCount;
	private Long apCount;
	private Long tgCount;
	private String name;
	private String area;
	private String location;
	private Long percentage;
	private String date;
	private Long id;
	private List<CadreRegisterInfo> infoList;
	private List<CadreRegisterInfo> allDetailsList;
	private List<CadreRegisterInfo> cadreRegisterInfoList;
	private String percentStr;
	private String number;
	private String memberShipNo;
	private Long apWebCount;
	private Long tgWebCount;
	private Long apTabCount;
	private Long tgTabCount;
	private String uname;
	private Long amount;
	
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Long getApWebCount() {
		return apWebCount;
	}

	public void setApWebCount(Long apWebCount) {
		this.apWebCount = apWebCount;
	}

	public Long getTgWebCount() {
		return tgWebCount;
	}

	public void setTgWebCount(Long tgWebCount) {
		this.tgWebCount = tgWebCount;
	}

	public Long getApTabCount() {
		return apTabCount;
	}

	public void setApTabCount(Long apTabCount) {
		this.apTabCount = apTabCount;
	}

	public Long getTgTabCount() {
		return tgTabCount;
	}

	public void setTgTabCount(Long tgTabCount) {
		this.tgTabCount = tgTabCount;
	}

	public String getMemberShipNo() {
		return memberShipNo;
	}

	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public List<CadreRegisterInfo> getCadreRegisterInfoList() {
		return cadreRegisterInfoList;
	}

	public void setCadreRegisterInfoList(
			List<CadreRegisterInfo> cadreRegisterInfoList) {
		this.cadreRegisterInfoList = cadreRegisterInfoList;
	}

	public List<CadreRegisterInfo> getAllDetailsList() {
		return allDetailsList;
	}

	public void setAllDetailsList(List<CadreRegisterInfo> allDetailsList) {
		this.allDetailsList = allDetailsList;
	}

	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getApCount() {
		return apCount;
	}
	
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	
	public Long getTgCount() {
		return tgCount;
	}
	
	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getPercentage() {
		return percentage;
	}

	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<CadreRegisterInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CadreRegisterInfo> infoList) {
		this.infoList = infoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPercentStr() {
		return percentStr;
	}

	public void setPercentStr(String percentStr) {
		this.percentStr = percentStr;
	}	

}
