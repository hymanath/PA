package com.itgrids.partyanalyst.model;

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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "cadre_reg_amount_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegAmountFile extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4347528196669387669L;
	private Long CadreRegAmountFileId;
	private String fileName;
	private String path;
	private User uploadedby;
	private Date uploadedTime;
	private Date date;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_amount_file_id", unique = true, nullable = false)
	public Long getCadreRegAmountFileId() {
		return CadreRegAmountFileId;
	}
	public void setCadreRegAmountFileId(Long cadreRegAmountFileId) {
		CadreRegAmountFileId = cadreRegAmountFileId;
	}
	
	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	 @Column(name = "path" )
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "uploaded_time")
	public Date getUploadedTime() {
		return uploadedTime;
	}
	
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="uploaded_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUploadedby() {
		return uploadedby;
	}
	public void setUploadedby(User uploadedby) {
		this.uploadedby = uploadedby;
	}


}
