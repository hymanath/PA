package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BirthDayDetailsVO implements Serializable {

	private List<IdNameVO> idNameVOList = new ArrayList<IdNameVO>(0);
	//private List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>(0);
	private Long id;
	private String name;
	private String designation;
	private boolean isWished;
	private String imageStr;
	private String mobileNo;
	private Long totalCount;
	private Long wishCount;
	private List<BirthDayDetailsVO> subList = new ArrayList<BirthDayDetailsVO>(0);
	private String bDayDate;
	private String pubRepDesignation;
	private String partyDesignation;
	private Long occasionId; 
	private Long totalPosCount;
	private List<BirthDayDetailsVO> designationWiseList = new ArrayList<BirthDayDetailsVO>(0);
	private AddressVO addressVO;
	
	
	
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getTotalPosCount() {
		return totalPosCount;
	}
	public void setTotalPosCount(Long totalPosCount) {
		this.totalPosCount = totalPosCount;
	}
	public Long getOccasionId() {
		return occasionId;
	}
	public void setOccasionId(Long occasionId) {
		this.occasionId = occasionId;
	}
	public String getPartyDesignation() {
		return partyDesignation;
	}
	public void setPartyDesignation(String partyDesignation) {
		this.partyDesignation = partyDesignation;
	}
	public String getPubRepDesignation() {
		return pubRepDesignation;
	}
	public void setPubRepDesignation(String pubRepDesignation) {
		this.pubRepDesignation = pubRepDesignation;
	}
	public String getbDayDate() {
		return bDayDate;
	}
	public void setbDayDate(String bDayDate) {
		this.bDayDate = bDayDate;
	}
	public List<BirthDayDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<BirthDayDetailsVO> subList) {
		this.subList = subList;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public boolean isWished() {
		return isWished;
	}
	public void setWished(boolean isWished) {
		this.isWished = isWished;
	}
	public String getImageStr() {
		return imageStr;
	}
	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getWishCount() {
		return wishCount;
	}
	public void setWishCount(Long wishCount) {
		this.wishCount = wishCount;
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
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	public List<BirthDayDetailsVO> getDesignationWiseList() {
		return designationWiseList;
	}
	public void setDesignationWiseList(List<BirthDayDetailsVO> designationWiseList) {
		this.designationWiseList = designationWiseList;
	}
	
}
