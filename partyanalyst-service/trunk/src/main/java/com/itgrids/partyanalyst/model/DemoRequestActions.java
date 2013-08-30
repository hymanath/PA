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

@Entity
@Table(name = "demo_request_actions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemoRequestActions extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = -9008523407890931525L;
	private String demoRequestActionsId;
	private DemoRequest demoRequest;
	private User user;
	private String type;
	private String content;
	private String response;
	private Date actionTime;
	
	public DemoRequestActions(){}
	public DemoRequestActions(DemoRequest demoRequest,User user,String type,String content,
			String response,Date actionTime){
		this.demoRequest = demoRequest;
		this.user = user;
		this.type = type;
		this.content = content;
		this.response = response;
		this.actionTime = actionTime;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "demo_request_actions_id", unique = true, nullable = false)
	public String getDemoRequestActionsId() {
		return demoRequestActionsId;
	}
	public void setDemoRequestActionsId(String demoRequestActionsId) {
		this.demoRequestActionsId = demoRequestActionsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "demo_request_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DemoRequest getDemoRequest() {
		return demoRequest;
	}
	public void setDemoRequest(DemoRequest demoRequest) {
		this.demoRequest = demoRequest;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "response")
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Column(name = "action_time")
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	
	

}
