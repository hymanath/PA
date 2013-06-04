package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "file_source_language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileSourceLanguage extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7086451337986050402L;
   
	private Long fileSourceLanguageId;
	private File file;
	private Source source;
	private SourceLanguage language;
	private Set<FilePaths> filePaths = new HashSet<FilePaths>(0);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_source_language_id", unique = true, nullable = false)
	public Long getFileSourceLanguageId() {
		return fileSourceLanguageId;
	}
	public void setFileSourceLanguageId(Long fileSourceLanguageId) {
		this.fileSourceLanguageId = fileSourceLanguageId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
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

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fileSourceLanguage")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<FilePaths> getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(Set<FilePaths> filePaths) {
		this.filePaths = filePaths;
	}
	
	
}
