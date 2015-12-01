package com.itgrids.partyanalyst.model;

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
@Table(name = "communication_media_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaResponse extends BaseModel implements java.io.Serializable{
  
	
	private Long communicationMediaResponseId;
	private Long communicationMediaRoundId;
	private Long communicationMediaQuestionId;
	private Long mediaOptionsId;
	private Long tdpCadreId;
	private String mobileNo;
	private Long keyId;
	private Date insertedTime;
	private String comments;
	private String isValid;
	private String isDeleted; 
	private Date ivrDate;
	private CommunicationMediaRound communicationMediaRound;
	private CommunicationMediaQuestion communicationMediaQuestion;
	private MediaOptions mediaOptions;
	private TdpCadre tdpCadre; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "communication_media_response_id", unique = true, nullable = false)
	public Long getCommunicationMediaResponseId() {
		return communicationMediaResponseId;
	}
	public void setCommunicationMediaResponseId(Long communicationMediaResponseId) {
		this.communicationMediaResponseId = communicationMediaResponseId;
	}
	
	@Column(name = "communication_media_round_id")
	public Long getCommunicationMediaRoundId() {
		return communicationMediaRoundId;
	}
	public void setCommunicationMediaRoundId(Long communicationMediaRoundId) {
		this.communicationMediaRoundId = communicationMediaRoundId;
	}
	
	@Column(name = "communication_media_question_id")
	public Long getCommunicationMediaQuestionId() {
		return communicationMediaQuestionId;
	}
	public void setCommunicationMediaQuestionId(Long communicationMediaQuestionId) {
		this.communicationMediaQuestionId = communicationMediaQuestionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="communication_media_round_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommunicationMediaRound getCommunicationMediaRound() {
		return communicationMediaRound;
	}
	public void setCommunicationMediaRound(
			CommunicationMediaRound communicationMediaRound) {
		this.communicationMediaRound = communicationMediaRound;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="communication_media_question_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommunicationMediaQuestion getCommunicationMediaQuestion() {
		return communicationMediaQuestion;
	}
	public void setCommunicationMediaQuestion(
			CommunicationMediaQuestion communicationMediaQuestion) {
		this.communicationMediaQuestion = communicationMediaQuestion;
	}
	
	@Column(name = "media_options_id")
	public Long getMediaOptionsId() {
		return mediaOptionsId;
	}
	public void setMediaOptionsId(Long mediaOptionsId) {
		this.mediaOptionsId = mediaOptionsId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "key_id")
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "is_valid")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="media_options_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MediaOptions getMediaOptions() {
		return mediaOptions;
	}
	public void setMediaOptions(MediaOptions mediaOptions) {
		this.mediaOptions = mediaOptions;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name = "ivr_date")
	public Date getIvrDate() {
		return ivrDate;
	}
	public void setIvrDate(Date ivrDate) {
		this.ivrDate = ivrDate;
	}
}
