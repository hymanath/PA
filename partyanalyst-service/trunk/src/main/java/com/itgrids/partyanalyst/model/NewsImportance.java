package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

@Entity
@Table(name="news_importance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsImportance extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long newsImportanceId;
	private String importance;
	private Long orderNo;
	private Set<File> file = new HashSet<File>(0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "news_importance_id", unique = true, nullable = false)
	public Long getNewsImportanceId() {
		return newsImportanceId;
	}
	public void setNewsImportanceId(Long newsImportanceId) {
		this.newsImportanceId = newsImportanceId;
	}
	
	@Column(name = "importance" , length = 50)
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	@Column(name = "order_no" , length = 3)
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsImportance")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<File> getFile() {
		return file;
	}
	public void setFile(Set<File> file) {
		this.file = file;
	}
	
}
