package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFeedback extends BaseModel implements Serializable{

	private Long userFeedbackId;
	private Long userFeedbackModuleId;
	private String name;
	private String mobile;
	private String email;
	private String feedbackDescription;
	
	private UserFeedbackModule userFeedbackModule;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_feedback_id", unique = true, nullable = false)
	public Long getUserFeedbackId() {
		return userFeedbackId;
	}
	public void setUserFeedbackId(Long userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}
	
	@Column(name="user_feedback_module_id")
	public Long getUserFeedbackModuleId() {
		return userFeedbackModuleId;
	}
	public void setUserFeedbackModuleId(Long userFeedbackModuleId) {
		this.userFeedbackModuleId = userFeedbackModuleId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="feedback_description")
	public String getFeedbackDescription() {
		return feedbackDescription;
	}
	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_feedback_module_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserFeedbackModule getUserFeedbackModule() {
		return userFeedbackModule;
	}
	public void setUserFeedbackModule(UserFeedbackModule userFeedbackModule) {
		this.userFeedbackModule = userFeedbackModule;
	}
}
