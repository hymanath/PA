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
	private String dateOfBirth;
	private String qualification;
	private String occupation;
	private String voterName;
	private String panchayatName;
	private String tehsilName;
	private String partyPosition;
	private String representativeType;
	
	List<CadreCommitteeMemberVO> knownList;
	List<CadreCommitteeMemberVO> unKnownList;
	
	private List<CasteDetailsVO> casteGroupVO;
	private List<CasteDetailsVO> ageDetailsIfoVO;
	private List<CasteDetailsVO> casteNameVO;
	private List<CasteDetailsVO> mandalLevelDetails;
	private List<CasteDetailsVO> notParticipatedMandals = new ArrayList<CasteDetailsVO>();
	private List<CasteDetailsVO> notParticipatedLocalBodys= new ArrayList<CasteDetailsVO>();
	private List<CasteDetailsVO> notParticioatedDivisions= new ArrayList<CasteDetailsVO>();
	private List<CasteDetailsVO> notParticioatedOthers= new ArrayList<CasteDetailsVO>();
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
	
	private List<CasteDetailsVO> constiVOList;
	private List<RolesVO> rolesList;
	
	private boolean lowPerformance;
	private String mandalBoothCmpr;
	
	private Long totalMembs;
	private Long mainCmmtteeMembs;
	private Long afflCmmtteeMembs;
	private Long lowPerfMainCmmtteeMembs;
	private Long lowPerfAfflCmmtteeMembs;
	
	private Long actualMandals;
	private Long actualLocalBodys;
	private Long actualDivistions;
	private Long actualOthers;
	private String type;
	
	private String voterIdCardNo;
	
	private CadreCommitteeMemberVO ccmVO;
	
	
	public Long getActualOthers() {
		return actualOthers;
	}
	public void setActualOthers(Long actualOthers) {
		this.actualOthers = actualOthers;
	}
	public Long getActualMandals() {
		return actualMandals;
	}
	public void setActualMandals(Long actualMandals) {
		this.actualMandals = actualMandals;
	}
	public Long getActualLocalBodys() {
		return actualLocalBodys;
	}
	public void setActualLocalBodys(Long actualLocalBodys) {
		this.actualLocalBodys = actualLocalBodys;
	}
	public Long getActualDivistions() {
		return actualDivistions;
	}
	public void setActualDivistions(Long actualDivistions) {
		this.actualDivistions = actualDivistions;
	}
	public List<CasteDetailsVO> getNotParticipatedMandals() {
		return notParticipatedMandals;
	}
	public void setNotParticipatedMandals(
			List<CasteDetailsVO> notParticipatedMandals) {
		this.notParticipatedMandals = notParticipatedMandals;
	}
	public List<CasteDetailsVO> getNotParticipatedLocalBodys() {
		return notParticipatedLocalBodys;
	}
	public void setNotParticipatedLocalBodys(
			List<CasteDetailsVO> notParticipatedLocalBodys) {
		this.notParticipatedLocalBodys = notParticipatedLocalBodys;
	}
	public List<CasteDetailsVO> getNotParticioatedDivisions() {
		return notParticioatedDivisions;
	}
	public void setNotParticioatedDivisions(
			List<CasteDetailsVO> notParticioatedDivisions) {
		this.notParticioatedDivisions = notParticioatedDivisions;
	}
	public List<CasteDetailsVO> getNotParticioatedOthers() {
		return notParticioatedOthers;
	}
	public void setNotParticioatedOthers(List<CasteDetailsVO> notParticioatedOthers) {
		this.notParticioatedOthers = notParticioatedOthers;
	}
	public Long getTotalMembs() {
		return totalMembs;
	}
	public void setTotalMembs(Long totalMembs) {
		this.totalMembs = totalMembs;
	}
	public Long getMainCmmtteeMembs() {
		return mainCmmtteeMembs;
	}
	public void setMainCmmtteeMembs(Long mainCmmtteeMembs) {
		this.mainCmmtteeMembs = mainCmmtteeMembs;
	}
	public Long getAfflCmmtteeMembs() {
		return afflCmmtteeMembs;
	}
	public void setAfflCmmtteeMembs(Long afflCmmtteeMembs) {
		this.afflCmmtteeMembs = afflCmmtteeMembs;
	}
	public Long getLowPerfMainCmmtteeMembs() {
		return lowPerfMainCmmtteeMembs;
	}
	public void setLowPerfMainCmmtteeMembs(Long lowPerfMainCmmtteeMembs) {
		this.lowPerfMainCmmtteeMembs = lowPerfMainCmmtteeMembs;
	}
	public Long getLowPerfAfflCmmtteeMembs() {
		return lowPerfAfflCmmtteeMembs;
	}
	public void setLowPerfAfflCmmtteeMembs(Long lowPerfAfflCmmtteeMembs) {
		this.lowPerfAfflCmmtteeMembs = lowPerfAfflCmmtteeMembs;
	}
	public boolean isLowPerformance() {
		return lowPerformance;
	}
	public void setLowPerformance(boolean lowPerformance) {
		this.lowPerformance = lowPerformance;
	}
	public String getMandalBoothCmpr() {
		return mandalBoothCmpr;
	}
	public void setMandalBoothCmpr(String mandalBoothCmpr) {
		this.mandalBoothCmpr = mandalBoothCmpr;
	}
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
	public List<CasteDetailsVO> getConstiVOList() {
		return constiVOList;
	}
	public void setConstiVOList(List<CasteDetailsVO> constiVOList) {
		this.constiVOList = constiVOList;
	}
	public List<CasteDetailsVO> getMandalLevelDetails() {
		return mandalLevelDetails;
	}
	public void setMandalLevelDetails(List<CasteDetailsVO> mandalLevelDetails) {
		this.mandalLevelDetails = mandalLevelDetails;
	}
	public List<RolesVO> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<RolesVO> rolesList) {
		this.rolesList = rolesList;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	
	public String getRepresentativeType() {
		return representativeType;
	}
	public void setRepresentativeType(String representativeType) {
		this.representativeType = representativeType;
	}
	public CadreCommitteeMemberVO getCcmVO() {
		return ccmVO;
	}
	public void setCcmVO(CadreCommitteeMemberVO ccmVO) {
		this.ccmVO = ccmVO;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}
	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}
	
	
	
}
