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
@Table(name="user_announcements")
public class UserAnnouncement extends BaseModel implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long userAnnouncementsId;
	private Announcement announcement;
	private Registration user;
	private Set<UserConstituencyScope> userConstituencyScope;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_announcements_id", unique = true, nullable = false)
	public Long getUserAnnouncementsId() {
		return userAnnouncementsId;
	}
	public void setUserAnnouncementsId(Long userAnnouncementsId) {
		this.userAnnouncementsId = userAnnouncementsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcements_id")
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
