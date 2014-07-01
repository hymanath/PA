package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Prasad Thiragabathina
 *
 *This Model Describes About Surey User Detais(firstname,lastname,username,password etc....)
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "survey_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUser extends BaseModel implements Serializable
{

	private Long surveyUserId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String mobileNo;
	private String address;
	private SurveyUserType surveyUserType;
	private String activeStatus;
	private Date insertedTime;
	private Date updatedTime;
	private String remarks;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_id", unique = true, nullable = false)
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "mobile_number")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_type")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUserType getSurveyUserType() {
		return surveyUserType;
	}
	public void setSurveyUserType(SurveyUserType surveyUserType) {
		this.surveyUserType = surveyUserType;
	}
	
	@Column(name = "active_status")
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
