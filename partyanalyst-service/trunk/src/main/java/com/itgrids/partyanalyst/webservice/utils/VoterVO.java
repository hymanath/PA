package com.itgrids.partyanalyst.webservice.utils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.VoterDataVO;


public class VoterVO implements Serializable , Comparable<VoterVO>{
	
	private static final long serialVersionUID = 1L;	
	private String houseNo;
	private String relationshipType;
	private String voterIDCardNo;
	private String gender;
	private Long age;
	private String voterId;
	/*private Long totalVoters;*/
	private Long boothNo;
	private String status;
	private String boothName;
	private Long voterIds;
	private Long boothId;
	/*private Long panchayatId;
	private String panchayatName;*/
	private String publicationName;
	private Long serialNo;
	/*private Long candidateId;*/
	private Long assemblyConstituencyId;
	private Long parliamentConstituencyId;
/*	private String electionType;*/
	private Long partNo;
	private Long sNo;
	private String relativeName;	
	private String mobileNo;	
	private Long publicationDateId;
	private Date publicationDate;
	/*private Long electionId;
	private Date electionDate;*/
	private String type;

	private String name;

	//@Override
	public int compareTo(VoterVO o) {
		// TODO Auto-generated method stub
		return this.getAge().compareTo(o.getAge());
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}

	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	/*public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}*/

	public Long getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(Long boothNo) {
		this.boothNo = boothNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public Long getVoterIds() {
		return voterIds;
	}

	public void setVoterIds(Long voterIds) {
		this.voterIds = voterIds;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	/*public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}*/

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	

	public Long getAssemblyConstituencyId() {
		return assemblyConstituencyId;
	}

	public void setAssemblyConstituencyId(Long assemblyConstituencyId) {
		this.assemblyConstituencyId = assemblyConstituencyId;
	}

	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}

	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}

	/*public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
*/
	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	/*public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}*/

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}