package com.itgrids.partyanalyst.model;

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
@Table(name = "communication_media_question_options")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaQuestionOptions extends BaseModel implements java.io.Serializable{

	 private Long communicationMediaQuestionOptionsId;
	 private Long communicationMediaQuestionId;
	 private Long mediaOptionsId;
	 private Long orderNo;
	 private String isDeleted;
	 
	 private CommunicationMediaQuestion communicationMediaQuestion;
	 private MediaOptions mediaOptions;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "communication_media_question_options_id", unique = true, nullable = false)
	public Long getCommunicationMediaQuestionOptionsId() {
		return communicationMediaQuestionOptionsId;
	}
	public void setCommunicationMediaQuestionOptionsId(
			Long communicationMediaQuestionOptionsId) {
		this.communicationMediaQuestionOptionsId = communicationMediaQuestionOptionsId;
	}
	
	@Column(name = "communication_media_question_id")
	public Long getCommunicationMediaQuestionId() {
		return communicationMediaQuestionId;
	}
	public void setCommunicationMediaQuestionId(Long communicationMediaQuestionId) {
		this.communicationMediaQuestionId = communicationMediaQuestionId;
	}
	
	@Column(name = "media_options_id")
	public Long getMediaOptionsId() {
		return mediaOptionsId;
	}
	public void setMediaOptionsId(Long mediaOptionsId) {
		this.mediaOptionsId = mediaOptionsId;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	 
}
