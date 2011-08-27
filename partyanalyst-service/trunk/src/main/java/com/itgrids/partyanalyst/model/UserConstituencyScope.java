package com.itgrids.partyanalyst.model;

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
@Table(name="user_constituency_scope")
public class UserConstituencyScope extends BaseModel implements java.io.Serializable{
  
	private Long userConstituencyScopeId;
	private UserAnnouncement userAnnouncement;
	private Constituency constituency;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_constituency_scope_id", unique = true, nullable = false)
	public Long getUserConstituencyScopeId() {
		return userConstituencyScopeId;
	}
	public void setUserConstituencyScopeId(Long userConstituencyScopeId) {
		this.userConstituencyScopeId = userConstituencyScopeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_announcements_id")
	public UserAnnouncement getUserAnnouncement() {
		return userAnnouncement;
	}
	public void setUserAnnouncement(UserAnnouncement userAnnouncement) {
		this.userAnnouncement = userAnnouncement;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
		
}
