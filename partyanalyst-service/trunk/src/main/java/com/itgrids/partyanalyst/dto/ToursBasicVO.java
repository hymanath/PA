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
	
	private String designation;
	private Long noOfLeaderCnt=0l;
	private Long submitedLeaderCnt=0l;
	private Long notSubmitedLeaserCnt=0l;
	private Long ownToursCnt=0l;
	private Long inchargerToursCnt=0l;
	private Long totalSubmittedToursCnt=0l;
	private Double averageTours=0.0d;
	
	
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getNoOfLeaderCnt() {
		return noOfLeaderCnt;
	}
	public void setNoOfLeaderCnt(Long noOfLeaderCnt) {
		this.noOfLeaderCnt = noOfLeaderCnt;
	}
	public Long getSubmitedLeaderCnt() {
		return submitedLeaderCnt;
	}
	public void setSubmitedLeaderCnt(Long submitedLeaderCnt) {
		this.submitedLeaderCnt = submitedLeaderCnt;
	}
	public Long getNotSubmitedLeaserCnt() {
		return notSubmitedLeaserCnt;
	}
	public void setNotSubmitedLeaserCnt(Long notSubmitedLeaserCnt) {
		this.notSubmitedLeaserCnt = notSubmitedLeaserCnt;
	}
	public Long getOwnToursCnt() {
		return ownToursCnt;
	}
	public void setOwnToursCnt(Long ownToursCnt) {
		this.ownToursCnt = ownToursCnt;
	}
	public Long getInchargerToursCnt() {
		return inchargerToursCnt;
	}
	public void setInchargerToursCnt(Long inchargerToursCnt) {
		this.inchargerToursCnt = inchargerToursCnt;
	}
	public Long getTotalSubmittedToursCnt() {
		return totalSubmittedToursCnt;
	}
	public void setTotalSubmittedToursCnt(Long totalSubmittedToursCnt) {
		this.totalSubmittedToursCnt = totalSubmittedToursCnt;
	}
	public Double getAverageTours() {
		return averageTours;
	}
	public void setAverageTours(Double averageTours) {
		this.averageTours = averageTours;
	}
	
}
