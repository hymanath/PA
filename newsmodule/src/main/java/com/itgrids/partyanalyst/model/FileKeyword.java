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
@Table(name="file_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileKeyword extends BaseModel implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2639097043585266046L;
	private Long fileKeywordId;
	private File file;
	private Keyword keyword;
	
    public FileKeyword(){
		
	}
	public FileKeyword(Long fileKeywordId, File file, Keyword keyword){
		this.fileKeywordId=fileKeywordId;
		this.file=file;
		this.keyword = keyword;
				
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "file_keyword_id",length=15, unique = true, nullable = false)
	public Long getFileKeywordId() {
		return fileKeywordId;
	}
	public void setFileKeywordId(Long fileKeywordId) {
		this.fileKeywordId = fileKeywordId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
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

}
