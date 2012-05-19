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
@Table(name = "file_path")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilePath extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5962197813848465146L;

	private Long filePathId;
	private FileSourceLanguage fileSourceLanguage;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_path_id", unique = true, nullable = false)
	public Long getFilePathId() {
		return filePathId;
	}
	public void setFilePathId(Long filePathId) {
		this.filePathId = filePathId;
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
	
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
