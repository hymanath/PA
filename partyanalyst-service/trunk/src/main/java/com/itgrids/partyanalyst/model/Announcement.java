package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="announcement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Announcement extends BaseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long announcementId;
	private String title;
	private String discription;
	private Date fromDate ;
	private Date toDate;
	private Set<UserAnnouncement> userAnnouncement = new HashSet<UserAnnouncement>(0);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "announcement_id", unique = true, nullable = false)
	public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	
	@Column(name = "title" , length = 150)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "discription" , length = 600)
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<UserAnnouncement> getUserAnnouncement() {
		return userAnnouncement;
	}
	public void setUserAnnouncement(Set<UserAnnouncement> userAnnouncement) {
		this.userAnnouncement = userAnnouncement;
	}
	
}
