package com.itgrids.electoralconnect.model;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="announcements")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Announcements extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3115385870214683625L;
	private Long announcementId;
	private String title;
	private String description;
	private Date date;
	private Date updatedTime;
	private User updatedBy;
	private AnnouncementType announcementType;
	private String isDeleted;
	private Set<Comment> comment=new HashSet<Comment>();
	
	
	public Announcements(String title,String description,Date date,Date updatedTime,User updatedBy,AnnouncementType announcementType,Set<Comment> comment,String isDeleted){
		this.title=title;
		this.description=description;
		this.date=date;
		this.updatedBy=updatedBy;
		this.updatedTime=updatedTime;
		this.announcementType=announcementType;
		this.isDeleted = isDeleted;
	}
	public Announcements(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="announcement_id",unique=true,nullable=false)
	public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	
	@Column(name="title",length=45)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="description",length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AnnouncementType getAnnouncementType() {
		return announcementType;
	}
	public void setAnnouncementType(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "announcements")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<Comment> getComment() {
		return comment;
	}
	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	
	@Column(name="is_deleted",length=45)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	
}
