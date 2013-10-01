package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="video_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VideoCategory extends BaseModel implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long videoCategoryId;
	private String category;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "video_category_id", unique = true, nullable = false)
	public Long getVideoCategoryId() {
		return videoCategoryId;
	}
	public void setVideoCategoryId(Long videoCategoryId) {
		this.videoCategoryId = videoCategoryId;
	}
	
	@Column(name = "category" , length = 45)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
