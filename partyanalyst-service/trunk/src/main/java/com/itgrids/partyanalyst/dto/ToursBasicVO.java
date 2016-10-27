package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ToursBasicVO {

	private Long id;
	private String name;
	private Long tdpCadreId;
	private String memberShipNo;
	private Long voaterId;
	private String mobileNo;
    private String type;
    private Long locationScopeId;
    private Long locationValue;
    private String image;
	private List<ToursBasicVO> subList;
	
	
	public ToursBasicVO() {
		super();
	}
	public ToursBasicVO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getVoaterId() {
		return voaterId;
	}
	public void setVoaterId(Long voaterId) {
		this.voaterId = voaterId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<ToursBasicVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ToursBasicVO> subList) {
		this.subList = subList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
