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
@Table(name="tv_news_channel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TvNewsChannel implements java.io.Serializable {
	
	private Long tvNewsChannelId;
	private String channelName;
	private String channelLanguage;
	private String description;
	private String isDeleted;
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "tv_news_channel_id",nullable = false, unique = true)
    public Long getTvNewsChannelId() {
		return tvNewsChannelId;
	}
	public void setTvNewsChannelId(Long tvNewsChannelId) {
		this.tvNewsChannelId = tvNewsChannelId;
	}
	
	@Column(name = "channel_name")
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@Column(name = "channel_language")
	public String getChannelLanguage() {
		return channelLanguage;
	}
	public void setChannelLanguage(String channelLanguage) {
		this.channelLanguage = channelLanguage;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
