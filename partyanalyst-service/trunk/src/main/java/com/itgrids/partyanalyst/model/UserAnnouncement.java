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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="user_announcement")
public class UserAnnouncement extends BaseModel implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long userAnnouncementId;
	private Announcement announcement;
	private User user;
	private Long userId;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	@Column(name = "user_id", length = 10)
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAnnouncement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserConstituencyScope> getUserConstituencyScope() {
		return userConstituencyScope;
	}
	public void setUserConstituencyScope(
			Set<UserConstituencyScope> userConstituencyScope) {
		this.userConstituencyScope = userConstituencyScope;
	}
	
	
	
	
}
