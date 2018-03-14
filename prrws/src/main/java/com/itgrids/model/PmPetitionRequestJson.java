package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pm_petition_request_json")
public class PmPetitionRequestJson  {

		
	private Long pmPetitionRequestJsonId;
	private Long petitionId;
	private String insertionType;
	private String jsonFormat;
	private Long userId;
	private Date insertedTime;
	
	private Petition petition;
	private User user;
	
	@Id
	@Column(name="pm_petition_request_json_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmPetitionRequestJsonId() {
		return pmPetitionRequestJsonId;
	}
	public void setPmPetitionRequestJsonId(Long pmPetitionRequestJsonId) {
		this.pmPetitionRequestJsonId = pmPetitionRequestJsonId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="insertionType")
	public String getInsertionType() {
		return insertionType;
	}
	public void setInsertionType(String insertionType) {
		this.insertionType = insertionType;
	}
	@Column(name="json_format")
	public String getJsonFormat() {
		return jsonFormat;
	}
	public void setJsonFormat(String jsonFormat) {
		this.jsonFormat = jsonFormat;
	}
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_id", insertable = false, updatable = false)
	public Petition getPetition() {
		return petition;
	}
	public void setPetition(Petition petition) {
		this.petition = petition;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
