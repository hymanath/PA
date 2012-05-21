package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "file_paths")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilePaths extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5962197813848465146L;

	private Long filePathsId;
	private FileSourceLanguage fileSourceLanguage;
	private String filePath;
	private FileType fileType;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_paths_id", unique = true, nullable = false)
	public Long getFilePathsId() {
		return filePathsId;
	}
	public void setFilePathsId(Long filePathsId) {
		this.filePathsId = filePathsId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_source_language_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public FileSourceLanguage getFileSourceLanguage() {
		return fileSourceLanguage;
	}
	public void setFileSourceLanguage(FileSourceLanguage fileSourceLanguage) {
		this.fileSourceLanguage = fileSourceLanguage;
	}
	
	@Column(name="file_path" , length=200)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
