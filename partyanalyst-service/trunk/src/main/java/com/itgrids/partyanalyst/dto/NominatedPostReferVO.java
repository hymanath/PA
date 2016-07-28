/**
 * @author SRAVANTH
 * July 27, 2016
 * NominatedPostReferVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date July 27, 2016
 */
public class NominatedPostReferVO {

	private Long id;
	private String name;
	
	private Long nominatedPostFinalId;
	private Long tdpCadreId;
	private Long voterId;
	private String voterName;
	private String voterMoblie;
	private String voterGender;
	private String cadreName;
	private String cadreMobile;
	private Long age;
	private String cadreGender;
	private String caste;
	private String subCaste;
	private String casteName;
	private Long applStatusId;
	private String status;
	private Long nominatedPostCandidateId;
	private List<NominatedPostReferVO> subList = new ArrayList<NominatedPostReferVO>();
	private Long appliedDeptCount = 0l;
	private Long shortListedDeptCount = 0l;
	private String partyPosition;
	private List<IdNameVO> idNamevoList = new ArrayList<IdNameVO>();
	private Long referenceCount = 0l;
	private Long commentCount = 0l;
	
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
	public List<NominatedPostReferVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NominatedPostReferVO> subList) {
		this.subList = subList;
	}
	public Long getNominatedPostFinalId() {
		return nominatedPostFinalId;
	}
	public void setNominatedPostFinalId(Long nominatedPostFinalId) {
		this.nominatedPostFinalId = nominatedPostFinalId;
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
	public String getVoterGender() {
		return voterGender;
	}
	public void setVoterGender(String voterGender) {
		this.voterGender = voterGender;
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
	public String getCadreGender() {
		return cadreGender;
	}
	public void setCadreGender(String cadreGender) {
		this.cadreGender = cadreGender;
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
	public Long getNominatedPostCandidateId() {
		return nominatedPostCandidateId;
	}
	public void setNominatedPostCandidateId(Long nominatedPostCandidateId) {
		this.nominatedPostCandidateId = nominatedPostCandidateId;
	}
	public Long getAppliedDeptCount() {
		return appliedDeptCount;
	}
	public void setAppliedDeptCount(Long appliedDeptCount) {
		this.appliedDeptCount = appliedDeptCount;
	}
	public Long getShortListedDeptCount() {
		return shortListedDeptCount;
	}
	public void setShortListedDeptCount(Long shortListedDeptCount) {
		this.shortListedDeptCount = shortListedDeptCount;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public List<IdNameVO> getIdNamevoList() {
		return idNamevoList;
	}
	public void setIdNamevoList(List<IdNameVO> idNamevoList) {
		this.idNamevoList = idNamevoList;
	}
	public Long getReferenceCount() {
		return referenceCount;
	}
	public void setReferenceCount(Long referenceCount) {
		this.referenceCount = referenceCount;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
}
