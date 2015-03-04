package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreCommitteeMemberVO {

	
	private Long id;
	private String name;
	private Long total;
	private String status;
	private Long level;
	private String imagePath;
	private String role;
	private String locationName;
	private String committe;
	private String membershipNo;
	private String age;
	private String gender;
	private String casteName;
	private String totalCount;
	private String maleCount;
	private String femaleCount;
	List<CadreCommitteeMemberVO> knownList;
	List<CadreCommitteeMemberVO> unKnownList;
	
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(String maleCount) {
		this.maleCount = maleCount;
	}
	public String getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(String femaleCount) {
		this.femaleCount = femaleCount;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getCommitte() {
		return committe;
	}
	public void setCommitte(String committe) {
		this.committe = committe;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CadreCommitteeMemberVO> getKnownList() {
		return knownList;
	}
	public void setKnownList(List<CadreCommitteeMemberVO> knownList) {
		this.knownList = knownList;
	}
	public List<CadreCommitteeMemberVO> getUnKnownList() {
		return unKnownList;
	}
	public void setUnKnownList(List<CadreCommitteeMemberVO> unKnownList) {
		this.unKnownList = unKnownList;
	}
	
	
}
