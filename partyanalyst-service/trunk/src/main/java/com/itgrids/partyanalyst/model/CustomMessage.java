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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name= "custom_message")
public class CustomMessage extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long customMessageId;
	private String subject;
	private String message;
	private Date sentDate;
	private AnanymousUser senderId;
	private AnanymousUser recepientId;
    private MessageType messageType;
	
	//Default Constructor
	public CustomMessage(){		
	}
	
	//Primary key Constructor
	public CustomMessage(Long customMessageId){
		this.customMessageId = customMessageId;
	}
	
	//Full Constructor
	public CustomMessage(String subject,String message,Date sentDate,AnanymousUser senderId,AnanymousUser recepientId){
		this.subject = subject;
		this.message = message;
		this.sentDate = sentDate;
		this.senderId = senderId;
		this.recepientId = recepientId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_message_id", unique=true, nullable=false)
	public Long getCustomMessageId() {
		return customMessageId;
	}

	public void setCustomMessageId(Long customMessageId) {
		this.customMessageId = customMessageId;
	}

	@Column(name="subject",length=70)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name="message",length=70)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name="sent_date",length=70)
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sender_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public AnanymousUser getSenderId() {
		return senderId;
	}

	public void setSenderId(AnanymousUser senderId) {
		this.senderId = senderId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="recepient_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public AnanymousUser getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(AnanymousUser recepientId) {
		this.recepientId = recepientId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="message_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	
	
}
