package com.itgrids.electoralconnect.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name="announcement_files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnnouncementFiles extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7511029806162962495L;
	private Long announcementFilesId;
	private File file;
	private Announcements announcements;
	
	
	public AnnouncementFiles(File file,Announcements announcement){
		this.file=file;
		this.announcements=announcement;
	}
	public AnnouncementFiles(){}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="announcement_files_id",unique=true,nullable=false)
	public Long getAnnouncementFilesId() {
		return announcementFilesId;
	}
	public void setAnnouncementFilesId(Long announcementFilesId) {
		this.announcementFilesId = announcementFilesId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Announcements getAnnouncement() {
		return announcements;
	}
	public void setAnnouncement(Announcements announcement) {
		this.announcements = announcement;
	}
	
}
