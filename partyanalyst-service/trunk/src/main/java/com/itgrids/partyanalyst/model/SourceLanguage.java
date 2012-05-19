/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 27, 2011
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
 * file entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "source_language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SourceLanguage extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = -38175259016513403L;
	private Long languageId;
	private String language;
	private Set<File> files = new HashSet<File>(0);
	private Set<FileSourceLanguage> fileSourceLanguage = new HashSet<FileSourceLanguage>(0);
	
	/** default constructor */
	public SourceLanguage() {

	}

	/** full constructor */
	public SourceLanguage(Long languageId, String language) {
		this.languageId = languageId;
		this.language = language;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "language_id", unique = true, nullable = false)
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	@Column(name = "language", length = 100)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "language")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sourceLanguage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<FileSourceLanguage> getFileSourceLanguage() {
		return fileSourceLanguage;
	}

	public void setFileSourceLanguage(Set<FileSourceLanguage> fileSourceLanguage) {
		this.fileSourceLanguage = fileSourceLanguage;
	}

}
