package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * gallary_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name = "file_gallary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileGallary implements Serializable{

	private static final long serialVersionUID = -4139954488062501310L;
	private Long fileGallaryId;
	private Gallary gallary;
	private File file;
	private Date createdDate;
	private Date updateddate;
	private String isPrivate;
	private String isDelete;
	
	private FileGallary(){
	}
	
	private FileGallary(Gallary gallary,File file,Date createdDate,
			Date updateddate,String isPrivate,String isDelete){
		this.gallary = gallary;
		this.file = file;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.isPrivate = isPrivate;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_gallary_id", unique=true, nullable=false)
	public Long getFileGallaryId() {
		return fileGallaryId;
	}

	public void setFileGallaryId(Long fileGallaryId) {
		this.fileGallaryId = fileGallaryId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="gallry_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Gallary getGallary() {
		return gallary;
	}

	public void setGallary(Gallary gallary) {
		this.gallary = gallary;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date", length = 10)
	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	@Column(name = "is_private", length = 10)
	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}

	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
			
}
