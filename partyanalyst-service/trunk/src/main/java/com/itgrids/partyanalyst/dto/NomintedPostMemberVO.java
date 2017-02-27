/**
 * @author SRAVANTH
 * July 15, 2016
 * NomintedPostMemberVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date July 15, 2016
 */
public class NomintedPostMemberVO {

	private Long id;
	private String name;
	private Long nominatedPostCandidateId;
	private Long tdpCadreId;
	private Long voterId;
	private String voterName;
	private String voterMoblie;
	private String cadreName;
	private String cadreMobile;
	private Long age;
	private String caste;
	private String subCaste;
	private String casteName;
	private Long applStatusId;
	private String status;
	private String partyPosition;
	private Long nominatedPostId;
	private Long nominatePostApplicationId;
	private Long otherDepartmentsCount;
	private String otherDeptShortListed;
	private List<NomintedPostMemberVO> subList = new ArrayList<NomintedPostMemberVO>();
	private List<NomintedPostMemberVO> subList1 = new ArrayList<NomintedPostMemberVO>();
	private String level;
	private Long deptId;
	private Long deptBoardId;
	private Long deptBoardPostnId;
	private List<IdNameVO> idNamevoList = new ArrayList<IdNameVO>();  
	private List<IdNameVO> nomDocsList = new ArrayList<IdNameVO>();
	private Long referCandCount;
	private Long boardLevelId;
	private Long levelValue;
	private Long levelId;
	private String levelName;
	private String imageURL;
	private String cadreAge;
	private String candCaste;
	private String candCasteName;
	private String gender;
	private String cadreGen;
	private String publicReprStr;
	private Long nominatedPostStatusId;
	private String nominatedPostStatusName;
	private String membershipNO;
	private String boardName;
	private String positionName;
	private Long castId;
	private Long castCategoryId;
	private String castCategoryName;
	private Long govtOrderId;
	private String govtOrderName;
	private String fromDate;
	private String toDate;
	private String deptName;
	private String expireDate;
	
	private Long appliedCount=0l;
	private Long shortListedCount=0l;
	
	private String uiFromDateStr;
	private String uiToDateStr;
	private String path;
	
	
	public String getUiFromDateStr() {
		return uiFromDateStr;
	}
	public void setUiFromDateStr(String uiFromDateStr) {
		this.uiFromDateStr = uiFromDateStr;
	}
	public String getUiToDateStr() {
		return uiToDateStr;
	}
	public void setUiToDateStr(String uiToDateStr) {
		this.uiToDateStr = uiToDateStr;
	}
	public String getPublicReprStr() {
		return publicReprStr;
	}
	public void setPublicReprStr(String publicReprStr) {
		this.publicReprStr = publicReprStr;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCadreGen() {
		return cadreGen;
	}
	public void setCadreGen(String cadreGen) {
		this.cadreGen = cadreGen;
	}
	public String getCadreAge() {
		return cadreAge;
	}
	public void setCadreAge(String cadreAge) {
		this.cadreAge = cadreAge;
	}
	public String getCandCaste() {
		return candCaste;
	}
	public void setCandCaste(String candCaste) {
		this.candCaste = candCaste;
	}
	public String getCandCasteName() {
		return candCasteName;
	}
	public void setCandCasteName(String candCasteName) {
		this.candCasteName = candCasteName;
	}
	public List<IdNameVO> getNomDocsList() {
		return nomDocsList;
	}
	public void setNomDocsList(List<IdNameVO> nomDocsList) {
		this.nomDocsList = nomDocsList;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
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
	public Long getNominatedPostCandidateId() {
		return nominatedPostCandidateId;
	}
	public void setNominatedPostCandidateId(Long nominatedPostCandidateId) {
		this.nominatedPostCandidateId = nominatedPostCandidateId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getVoterMoblie() {
		return voterMoblie;
	}
	public void setVoterMoblie(String voterMoblie) {
		this.voterMoblie = voterMoblie;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getCadreMobile() {
		return cadreMobile;
	}
	public void setCadreMobile(String cadreMobile) {
		this.cadreMobile = cadreMobile;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getSubCaste() {
		return subCaste;
	}
	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getApplStatusId() {
		return applStatusId;
	}
	public void setApplStatusId(Long applStatusId) {
		this.applStatusId = applStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<NomintedPostMemberVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NomintedPostMemberVO> subList) {
		this.subList = subList;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public Long getNominatedPostId() {
		return nominatedPostId;
	}
	public void setNominatedPostId(Long nominatedPostId) {
		this.nominatedPostId = nominatedPostId;
	}
	public Long getNominatePostApplicationId() {
		return nominatePostApplicationId;
	}
	public void setNominatePostApplicationId(Long nominatePostApplicationId) {
		this.nominatePostApplicationId = nominatePostApplicationId;
	}
	public Long getOtherDepartmentsCount() {
		return otherDepartmentsCount;
	}
	public void setOtherDepartmentsCount(Long otherDepartmentsCount) {
		this.otherDepartmentsCount = otherDepartmentsCount;
	}
	public String getOtherDeptShortListed() {
		return otherDeptShortListed;
	}
	public void setOtherDeptShortListed(String otherDeptShortListed) {
		this.otherDeptShortListed = otherDeptShortListed;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public List<NomintedPostMemberVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<NomintedPostMemberVO> subList1) {
		this.subList1 = subList1;
	}
	public List<IdNameVO> getIdNamevoList() {
		return idNamevoList;
	}
	public void setIdNamevoList(List<IdNameVO> idNamevoList) {
		this.idNamevoList = idNamevoList;
	}
	public Long getReferCandCount() {
		return referCandCount;
	}
	public void setReferCandCount(Long referCandCount) {
		this.referCandCount = referCandCount;
	}
	public Long getAppliedCount() {
		return appliedCount;
	}
	public void setAppliedCount(Long appliedCount) {
		this.appliedCount = appliedCount;
	}
	public Long getShortListedCount() {
		return shortListedCount;
	}
	public void setShortListedCount(Long shortListedCount) {
		this.shortListedCount = shortListedCount;
	}
	public Long getNominatedPostStatusId() {
		return nominatedPostStatusId;
	}
	public void setNominatedPostStatusId(Long nominatedPostStatusId) {
		this.nominatedPostStatusId = nominatedPostStatusId;
	}
	public String getNominatedPostStatusName() {
		return nominatedPostStatusName;
	}
	public void setNominatedPostStatusName(String nominatedPostStatusName) {
		this.nominatedPostStatusName = nominatedPostStatusName;
	}
	public String getMembershipNO() {
		return membershipNO;
	}
	public void setMembershipNO(String membershipNO) {
		this.membershipNO = membershipNO;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getCastId() {
		return castId;
	}
	public void setCastId(Long castId) {
		this.castId = castId;
	}
	public Long getCastCategoryId() {
		return castCategoryId;
	}
	public void setCastCategoryId(Long castCategoryId) {
		this.castCategoryId = castCategoryId;
	}
	public String getCastCategoryName() {
		return castCategoryName;
	}
	public void setCastCategoryName(String castCategoryName) {
		this.castCategoryName = castCategoryName;
	}
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	public String getGovtOrderName() {
		return govtOrderName;
	}
	public void setGovtOrderName(String govtOrderName) {
		this.govtOrderName = govtOrderName;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
