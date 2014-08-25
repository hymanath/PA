package com.itgrids.partyanalyst.model;

import java.io.Serializable;

/**
 * @author <a href="sasi.itgrids.hyd@gmail.com">SASIn</a>
 * @since 19th AUG 2014
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "news_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				
public class NewsType extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long newsTypeId;
	private String newsTypeName;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="news_type_id", unique=true, nullable=false)
	public Long getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(Long newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	
	
	@Column(name = "news_type_name")
	
	public String getNewsTypeName() {
		return newsTypeName;
	}
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	
	
	
	
		
	 
}
