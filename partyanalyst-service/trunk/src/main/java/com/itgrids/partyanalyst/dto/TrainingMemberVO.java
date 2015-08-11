package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainingMemberVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String mobileNumber;
	private String status;
	private String image;
	private String role;
	private String location;
	private String age;
	private Long inviteeId;
	private Long inviteeCallerId;
	private Long availableCount;
	private Long totalCount;
	private List<TrainingMemberVO> subList = new ArrayList<TrainingMemberVO>();
	private String remarks;
	
	public Long getAvailableCount() {
		return availableCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getInviteeId() {
		return inviteeId;
	}
	public void setInviteeId(Long inviteeId) {
		this.inviteeId = inviteeId;
	}
	public Long getInviteeCallerId() {
		return inviteeCallerId;
	}
	public void setInviteeCallerId(Long inviteeCallerId) {
		this.inviteeCallerId = inviteeCallerId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<TrainingMemberVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TrainingMemberVO> subList) {
		this.subList = subList;
	}
	
}
