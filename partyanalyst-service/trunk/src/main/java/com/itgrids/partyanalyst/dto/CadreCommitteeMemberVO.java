package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
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
	
	private List<CasteDetailsVO> casteGroupVO;
	private List<CasteDetailsVO> ageDetailsIfoVO;
	private List<CasteDetailsVO> casteNameVO;
	private List<Long> commiteeRoleIds = new ArrayList<Long>();
	private String casteGroupName;
	private String mobileNo;
	private String voterCardNo;
	private String commiteeName;
	private String voterId;
	private String constituencyName;
	private Long constituencyId;
	private String partNo;
	private Long boothId;
	private String fromDate;
	private String toDate;
	private Long vtrId;
	
	private String ownBoothPerc;
	private String ownPanchPerc;
	private String ownMandalPerc;
	private String ownConstiPerc;
	private String ownMunciPerc;
	private String ownWardPerc;
	
	
	
	
	public String getOwnBoothPerc() {
		return ownBoothPerc;
	}
	public void setOwnBoothPerc(String ownBoothPerc) {
		this.ownBoothPerc = ownBoothPerc;
	}
	public String getOwnPanchPerc() {
		return ownPanchPerc;
	}
	public void setOwnPanchPerc(String ownPanchPerc) {
		this.ownPanchPerc = ownPanchPerc;
	}
	public String getOwnMandalPerc() {
		return ownMandalPerc;
	}
	public void setOwnMandalPerc(String ownMandalPerc) {
		this.ownMandalPerc = ownMandalPerc;
	}
	public String getOwnConstiPerc() {
		return ownConstiPerc;
	}
	public void setOwnConstiPerc(String ownConstiPerc) {
		this.ownConstiPerc = ownConstiPerc;
	}
	public String getOwnMunciPerc() {
		return ownMunciPerc;
	}
	public void setOwnMunciPerc(String ownMunciPerc) {
		this.ownMunciPerc = ownMunciPerc;
	}
	public String getOwnWardPerc() {
		return ownWardPerc;
	}
	public void setOwnWardPerc(String ownWardPerc) {
		this.ownWardPerc = ownWardPerc;
	}
	public Long getVtrId() {
		return vtrId;
	}
	public void setVtrId(Long vtrId) {
		this.vtrId = vtrId;
	}
	public List<CasteDetailsVO> getCasteNameVO() {
		return casteNameVO;
	}
	public void setCasteNameVO(List<CasteDetailsVO> casteNameVO) {
		this.casteNameVO = casteNameVO;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getCommiteeName() {
		return commiteeName;
	}
	public void setCommiteeName(String commiteeName) {
		this.commiteeName = commiteeName;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public List<Long> getCommiteeRoleIds() {
		return commiteeRoleIds;
	}
	public void setCommiteeRoleIds(List<Long> commiteeRoleIds) {
		this.commiteeRoleIds = commiteeRoleIds;
	}
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
	public String getCasteGroupName() {
		return casteGroupName;
	}
	public void setCasteGroupName(String casteGroupName) {
		this.casteGroupName = casteGroupName;
	}
	public List<CasteDetailsVO> getCasteGroupVO() {
		return casteGroupVO;
	}
	public void setCasteGroupVO(List<CasteDetailsVO> casteGroupVO) {
		this.casteGroupVO = casteGroupVO;
	}
	public List<CasteDetailsVO> getAgeDetailsIfoVO() {
		return ageDetailsIfoVO;
	}
	public void setAgeDetailsIfoVO(List<CasteDetailsVO> ageDetailsIfoVO) {
		this.ageDetailsIfoVO = ageDetailsIfoVO;
	}
	
	
	
}
