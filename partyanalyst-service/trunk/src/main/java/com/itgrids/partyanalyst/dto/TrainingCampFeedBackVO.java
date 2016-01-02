package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TrainingCampFeedBackVO {
	
	private String name;
	private Long id;
	private String constituency;
	private String district;
	private String question;
	private String answer;
	private Long constituencyId;
	private String membershipId;
	private String imagePath;
	private String title;
	private String location_field;
	
	private List<TrainingCampFeedBackVO> subList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<TrainingCampFeedBackVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TrainingCampFeedBackVO> subList) {
		this.subList = subList;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation_field() {
		return location_field;
	}
	public void setLocation_field(String location_field) {
		this.location_field = location_field;
	}
	
	
	
}
