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
@Table(name="file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4037562885318335562L;
	private Long fileId;
	private String title;
	private String description;
	private FileType fileType;
	private String filePath;
	private Date updatedTime;
	private User updatedBy;
	private Set<AnnouncementFiles> announcementFiles=new HashSet<AnnouncementFiles>();
	
	public File(String title,String description,FileType fileType,String filePath,Date updatedTime,User updatedBy){
		this.title=title;
		this.description=description;
		this.fileType=fileType;
		this.filePath=filePath;
		this.updatedBy=updatedBy;
		this.updatedTime=updatedTime;
	}
	public File(){};
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_id",unique=true,nullable=false)
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	@Column(name="title",length=45)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="description",length=45)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	
	
	@Column(name="file_path",length=100)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "announcementFiles")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<AnnouncementFiles> getAnnouncementFiles() {
		return announcementFiles;
	}
	public void setAnnouncementFiles(Set<AnnouncementFiles> announcementFiles) {
		this.announcementFiles = announcementFiles;
	}
	
	
}
