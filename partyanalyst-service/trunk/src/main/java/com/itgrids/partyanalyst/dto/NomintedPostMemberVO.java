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
	private List<NomintedPostMemberVO> subList = new ArrayList<NomintedPostMemberVO>();
	
	
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
}
