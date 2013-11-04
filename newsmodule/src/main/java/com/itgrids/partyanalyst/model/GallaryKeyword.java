package com.itgrids.partyanalyst.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;




@Entity
@Table(name = "gallary_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GallaryKeyword extends BaseModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gallaryKeywordId;
	private Gallary gallary;
	private Keyword keyword;
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	
	
	public GallaryKeyword() {

	}

	/** full constructor */
	public GallaryKeyword(Long gallaryKeywordId, Gallary gallary,  Keyword keyword,Date createdDate, Date updatedDate,String createdBy) {
		this.gallaryKeywordId = gallaryKeywordId;
		this.gallary = gallary;
		this.keyword = keyword;	
		this.createdDate = createdDate;
		this.updatedDate=updatedDate;
		this.createdBy=createdBy;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gallary_keyword_id", unique = true, nullable = false)
	public Long getGallaryKeywordId() {
		return gallaryKeywordId;
	}

	public void setGallaryKeywordId(Long gallaryKeywordId) {
		this.gallaryKeywordId = gallaryKeywordId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gallary_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Gallary getGallary() {
		return gallary;
	}

	public void setGallary(Gallary gallary) {
		this.gallary = gallary;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "keyword_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}
    
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
   
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	@Column(name = "created_by", length = 10)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
	
	

}

