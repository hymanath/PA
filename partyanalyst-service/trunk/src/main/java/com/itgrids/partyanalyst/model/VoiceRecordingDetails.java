package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name="voice_recording_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoiceRecordingDetails extends BaseModel implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Long voiceRecordingDetailsId;
	private String recordingName;
	private String recordingDescription;
	
	private User user;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voice_recording_details_id", unique = true, nullable = false)
	public Long getVoiceRecordingDetailsId() {
		return voiceRecordingDetailsId;
	}
	public void setVoiceRecordingDetailsId(Long voiceRecordingDetailsId) {
		this.voiceRecordingDetailsId = voiceRecordingDetailsId;
	}
	
	@Column(name="recording_name" , length = 20)
	public String getRecordingName() {
		return recordingName;
	}
	public void setRecordingName(String recordingName) {
		this.recordingName = recordingName;
	}
	
	@Column(name="recording_description" , length = 500)
	public String getRecordingDescription() {
		return recordingDescription;
	}
	public void setRecordingDescription(String recordingDescription) {
		this.recordingDescription = recordingDescription;
	}

	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
