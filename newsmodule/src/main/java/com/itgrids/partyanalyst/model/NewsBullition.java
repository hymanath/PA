package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "news_bullition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsBullition extends BaseModel implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long dewsBullitionId;
	private Long channelId;
	private Date newsBullitionTime;
	private Long minutes;
	private Long seconds;
	private String newsBullitionDescription;
	private String isHeadLine;
	private Long newsBullitionTypeId;
	private Date insertedTime;
	private Date updateTime;
	private String isDeleted;
	
	private Channel channel;
	private NewsBullitionType newsBullitionType;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "news_bullition_id", unique = true, nullable = false)
	public Long getDewsBullitionId() {
		return dewsBullitionId;
	}

	public void setDewsBullitionId(Long dewsBullitionId) {
		this.dewsBullitionId = dewsBullitionId;
	}

	@Column(name="channel_id")
	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	@Column(name="news_bullition_time")
	public Date getNewsBullitionTime() {
		return newsBullitionTime;
	}

	public void setNewsBullitionTime(Date newsBullitionTime) {
		this.newsBullitionTime = newsBullitionTime;
	}

	@Column(name="minutes")
	public Long getMinutes() {
		return minutes;
	}

	public void setMinutes(Long minutes) {
		this.minutes = minutes;
	}

	@Column(name="seconds")
	public Long getSeconds() {
		return seconds;
	}

	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}

	@Column(name="news_bullition_description")
	public String getNewsBullitionDescription() {
		return newsBullitionDescription;
	}

	public void setNewsBullitionDescription(String newsBullitionDescription) {
		this.newsBullitionDescription = newsBullitionDescription;
	}

	@Column(name="is_head_line")
	public String getIsHeadLine() {
		return isHeadLine;
	}

	public void setIsHeadLine(String isHeadLine) {
		this.isHeadLine = isHeadLine;
	}

	@Column(name="news_bullition_type_id")
	public Long getNewsBullitionTypeId() {
		return newsBullitionTypeId;
	}

	public void setNewsBullitionTypeId(Long newsBullitionTypeId) {
		this.newsBullitionTypeId = newsBullitionTypeId;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="channel_id" , insertable = false , updatable = false)
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Column(name="news_bullition_type_id" , insertable = false , updatable = false)
	public NewsBullitionType getNewsBullitionType() {
		return newsBullitionType;
	}

	public void setNewsBullitionType(NewsBullitionType newsBullitionType) {
		this.newsBullitionType = newsBullitionType;
	}
	
	
	
	
}
