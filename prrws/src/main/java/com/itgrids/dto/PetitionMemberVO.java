package com.itgrids.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PetitionMemberVO {
	
	private Long id;
	private String name;
	
	private String memberType;
	private String endorsmentDate;
	private String representationDate;
	private String mobileNo;
	private String emailId;
	private String voterCardNo;
	private List<MultipartFile> filesList ;
	
	public List<MultipartFile> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<MultipartFile> filesList) {
		this.filesList = filesList;
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
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(String endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	public String getRepresentationDate() {
		return representationDate;
	}
	public void setRepresentationDate(String representationDate) {
		this.representationDate = representationDate;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	
}
