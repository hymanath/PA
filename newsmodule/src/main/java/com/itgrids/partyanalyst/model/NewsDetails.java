package com.itgrids.partyanalyst.model;

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
@Table(name = "news_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsDetails extends BaseModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5889893026987509348L;

	private Long newsDetailsId;
	private FileSourceLanguage fileSourceLanguage;
	private Integer pageNo;
	private Integer edition;
	private String newsLength;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_details_id", unique = true, nullable = false)
	public Long getNewsDetailsId() {
		return newsDetailsId;
	}
	
	public void setNewsDetailsId(Long newsDetailsId) {
		this.newsDetailsId = newsDetailsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_source_language_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FileSourceLanguage getFileSourceLanguage() {
		return fileSourceLanguage;
	}

	public void setFileSourceLanguage(FileSourceLanguage fileSourceLanguage) {
		this.fileSourceLanguage = fileSourceLanguage;
	}

	@Column(name = "page_no")
	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	@Column(name = "edition")
	public Integer getEdition() {
		return edition;
	}
	
	public void setEdition(Integer edition) {
		this.edition = edition;
	}
	
	@Column(name = "news_length")
	public String getNewsLength() {
		return newsLength;
	}
	
	public void setNewsLength(String newsLength) {
		this.newsLength = newsLength;
	}
	
	
}
