package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="post_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PostType extends BaseModel implements Serializable{
	
	private Long postTypeId;
	private String postType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_type_id", unique = true, nullable = false)
	public Long getPostTypeId() {
		return postTypeId;
	}
	public void setPostTypeId(Long postTypeId) {
		this.postTypeId = postTypeId;
	}
	@Column(name = "post_type")
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	
}
