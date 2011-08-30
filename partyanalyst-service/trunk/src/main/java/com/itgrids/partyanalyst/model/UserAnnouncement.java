package com.itgrids.partyanalyst.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="user_announcement")
public class UserAnnouncement extends BaseModel implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long userAnnouncementId;
	private Announcement announcement;
	private Registration user;
	private Set<UserConstituencyScope> userConstituencyScope;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_announcement_id", unique = true, nullable = false)
	public Long getUserAnnouncementId() {
		return userAnnouncementId;
	}
	public void setUserAnnouncementId(Long userAnnouncementId) {
		this.userAnnouncementId = userAnnouncementId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_id")
	public Announcement getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAnnouncement")
	public Set<UserConstituencyScope> getUserConstituencyScope() {
		return userConstituencyScope;
	}
	public void setUserConstituencyScope(
			Set<UserConstituencyScope> userConstituencyScope) {
		this.userConstituencyScope = userConstituencyScope;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public Registration getUser() {
		return user;
	}
	public void setUser(Registration user) {
		this.user = user;
	}
	
	
}
