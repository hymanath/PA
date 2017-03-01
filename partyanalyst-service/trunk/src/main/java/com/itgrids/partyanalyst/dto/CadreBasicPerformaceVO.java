package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srishailam Pittala
 * @Date 1st March, 2017
 * */
public class CadreBasicPerformaceVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String imagePath;
	private String membershipNo;
	private String mobileNO;
	private String casteGroup;
	private String casteName;
	private String regDate;
	private List<CadreBasicPerformaceVO> subList = new ArrayList<CadreBasicPerformaceVO>(0);
	
	
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getCasteGroup() {
		return casteGroup;
	}
	public void setCasteGroup(String casteGroup) {
		this.casteGroup = casteGroup;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public List<CadreBasicPerformaceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreBasicPerformaceVO> subList) {
		this.subList = subList;
	}
	
}
