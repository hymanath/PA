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
	private Long referCandCount;
	
	
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
}
