/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 24, 2011
 */
package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * file_type entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "file_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileType extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long fileTypeId;
	private String type;
	private Long orderNo;
	private Set<File> file = new HashSet<File>();

	/** default constructor */
	public FileType() {

	}

	/** full constructor */
	public FileType(Long fileTypeId, String type, Long orderNo,
			Set<File> file) {
		this.fileTypeId = fileTypeId;
		this.type = type;
		this.orderNo = orderNo;
		this.file = file;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_type_id", unique = true, nullable = false)
	public Long getFileTypeId() {
		return fileTypeId;
	}

	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fileType")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<File> getFile() {
		return file;
	}

	

	

	public void setFile(Set<File> file) {
		this.file = file;
	}

}
