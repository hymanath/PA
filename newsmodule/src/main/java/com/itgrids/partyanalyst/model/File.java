/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 24, 2011
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
import com.itgrids.partyanalyst.model.BaseModel;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * file entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long fileId;
	private String fileName;
	private String filePath;
	private String fileTitle;
	private String fileDescription;
	private FileType fileType;
	private RegionScopes regionScopes;
	private String keywords;
	private Long locationValue;
	private String source;
	private Date fileDate;
	private Source sourceObj;
	private SourceLanguage language;
	private NewsImportance newsImportance;
	private Category category;
	//private Set<ProblemFile> ProblemFile = new HashSet<ProblemFile>();
	private Set<FileGallary> fileGallary = new HashSet<FileGallary>(0);
	private Set<FileSourceLanguage> fileSourceLanguage = new HashSet<FileSourceLanguage>(0);
	//private Set<ProblemFiles> problemFiles = new HashSet<ProblemFiles>(0);
	//private Set<NewsProblem> newsProblems = new HashSet<NewsProblem>();
	
	private String comment;
	private User user;

	

	/** default constructor */
	public File() {

	}

	/** full constructor *//*
	public File(Long fileId, String fileName, String filePath,
			String fileTitle, String fileDescription, FileType fileType,
			Set<ProblemFile> ProblemFile) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileTitle = fileTitle;
		this.fileDescription = fileDescription;
		this.fileType = fileType;
		this.ProblemFile = ProblemFile;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_id", unique = true, nullable = false)
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_name", length = 100)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_path", length = 200)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "file_title", length = 200)
	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	@Column(name = "file_description", length = 500)
	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
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
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "file")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemFile> getProblemFile() {
		return ProblemFile;
	}

	public void setProblemFile(Set<ProblemFile> problemFile) {
		ProblemFile = problemFile;
	}
*/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "file")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<FileGallary> getFileGallary() {
		return fileGallary;
	}

	public void setFileGallary(Set<FileGallary> fileGallary) {
		this.fileGallary = fileGallary;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@Column(name = "keywords",length = 200)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public String getSource() {
		return source;
	}

	@Column(name = "source")
	public void setSource(String source) {
		this.source = source;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "file_date")
	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Source getSourceObj() {
		return sourceObj;
	}

	public void setSourceObj(Source sourceObj) {
		this.sourceObj = sourceObj;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SourceLanguage getLanguage() {
		return language;
	}

	public void setLanguage(SourceLanguage language) {
		this.language = language;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "news_importance_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public NewsImportance getNewsImportance() {
		return newsImportance;
	}
   
	
	public void setNewsImportance(NewsImportance newsImportance) {
		this.newsImportance = newsImportance;
	}	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "file")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<FileSourceLanguage> getFileSourceLanguage() {
		return fileSourceLanguage;
	}

	public void setFileSourceLanguage(Set<FileSourceLanguage> fileSourceLanguage) {
		this.fileSourceLanguage = fileSourceLanguage;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "file")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemFiles> getProblemFiles() {
		return problemFiles;
	}

	public void setProblemFiles(Set<ProblemFiles> problemFiles) {
		this.problemFiles = problemFiles;
	}*/
	
	
	
	@Column(name="comments" , length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "file")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<NewsProblem> getNewsProblems() {
		return newsProblems;
	}

	public void setNewsProblems(Set<NewsProblem> newsProblems) {
		this.newsProblems = newsProblems;
	}

  */
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
