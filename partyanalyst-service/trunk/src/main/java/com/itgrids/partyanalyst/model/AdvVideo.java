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
@Table(name="adv_video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdvVideo extends BaseModel implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	private Long   advVideoId;
	private VideoCategory videoCategory;
	private String show;
	private String description;
	private String tags;
	private Date   updateTime;
	private String code ;
	private String thumbnailUrl;
	private int duration;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "adv_video_id", unique = true, nullable = false)
		
	public Long getAdvVideoId() {
		return advVideoId;
	}

	public void setAdvVideoId(Long advVideoId) {
		this.advVideoId = advVideoId;
	}

	@Column(name="show" , length=45 )
	public String getShow() {
		return show;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VideoCategory getVideoCategory() {
		return videoCategory;
	}
	public void setVideoCategory(VideoCategory videoCategory) {
		this.videoCategory = videoCategory;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
	@Column(name="description" , length=1000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="tags" , length=1000)
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	
	@Column(name ="code" , length = 2000)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name="thumbnail_url" , length = 200)
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	@Column(name="duration")
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}
