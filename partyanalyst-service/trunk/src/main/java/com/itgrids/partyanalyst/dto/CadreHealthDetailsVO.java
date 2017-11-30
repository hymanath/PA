package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CadreHealthDetailsVO implements java.io.Serializable {
	
	private Long id;
	private Long tdpcadreId;
	private Long age;
	private String gender;
	private Long height;
	private Long weight;
	private Long spot;
	private Long systolicBp;
	private Long diastolicBp;
	private Long heartPulse;
	private Long spiro;
	private String startTime;
	
	private String membershipNo;
	private String imagePathStr;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTdpcadreId() {
		return tdpcadreId;
	}

	public void setTdpcadreId(Long tdpcadreId) {
		this.tdpcadreId = tdpcadreId;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Long getSpot() {
		return spot;
	}

	public void setSpot(Long spot) {
		this.spot = spot;
	}

	public Long getSystolicBp() {
		return systolicBp;
	}

	public void setSystolicBp(Long systolicBp) {
		this.systolicBp = systolicBp;
	}

	public Long getDiastolicBp() {
		return diastolicBp;
	}

	public void setDiastolicBp(Long diastolicBp) {
		this.diastolicBp = diastolicBp;
	}

	public Long getHeartPulse() {
		return heartPulse;
	}

	public void setHeartPulse(Long heartPulse) {
		this.heartPulse = heartPulse;
	}

	public Long getSpiro() {
		return spiro;
	}

	public void setSpiro(Long spiro) {
		this.spiro = spiro;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getImagePathStr() {
		return imagePathStr;
	}

	public void setImagePathStr(String imagePathStr) {
		this.imagePathStr = imagePathStr;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	

}
