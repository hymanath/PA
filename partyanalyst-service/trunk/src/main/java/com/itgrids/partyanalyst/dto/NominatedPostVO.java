package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NominatedPostVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long stateId;
	private Long districtId;
	private Long ConstituencyId;
	private Long panchayatId;
	private String hno;
	private String address1;
	private String address2;
	private String pincode;
	private String 	mobileNo;
	private Long boardLevelId;
	private Long deptId;
	private Long deptBoardId;
	private Long deptBoardPostnId;
	private Long mandalId;
	private String voterCardNo;
	private List<NominatedPostVO> nominatdList = new ArrayList<NominatedPostVO>();
	
	
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public List<NominatedPostVO> getNominatdList() {
		return nominatdList;
	}
	public void setNominatdList(List<NominatedPostVO> nominatdList) {
		this.nominatdList = nominatdList;
	}
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getDeptBoardId() {
		return deptBoardId;
	}
	public void setDeptBoardId(Long deptBoardId) {
		this.deptBoardId = deptBoardId;
	}
	public Long getDeptBoardPostnId() {
		return deptBoardPostnId;
	}
	public void setDeptBoardPostnId(Long deptBoardPostnId) {
		this.deptBoardPostnId = deptBoardPostnId;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return ConstituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		ConstituencyId = constituencyId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
