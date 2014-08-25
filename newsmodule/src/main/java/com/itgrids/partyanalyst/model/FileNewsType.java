package com.itgrids.partyanalyst.model;

import java.io.Serializable;

/**
 * @author <a href="sasi.itgrids.hyd@gmail.com">SASIn</a>
 * @since 19th AUG 2014
 */

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
@Table(name = "file_news_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				
public class FileNewsType extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fileNewsTypeId;
	private Long fileId;
	private Long newsTypeId;
	private File file;
	private NewsType newsType;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_news_type_id", unique=true, nullable=false)
	public Long getFileNewsTypeId() {
		return fileNewsTypeId;
	}
	public void setFileNewsTypeId(Long fileNewsTypeId) {
		this.fileNewsTypeId = fileNewsTypeId;
	}
	
	@Column(name = "file_id")
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	@Column(name = "news_type_id")
	public Long getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(Long newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "news_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	
		 
}
