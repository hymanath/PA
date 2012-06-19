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
@Table(name= "custom_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomMessage extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long customMessageId;
	private String subject;
	private String status;
	private Date sentDate;
	private AnanymousUser senderId;
	private AnanymousUser recepientId;
    private MessageType messageType;
    private User sender;
    private User recepient;
	
	//Default Constructor
	public CustomMessage(){		
	}
	
	//Primary key Constructor
	public CustomMessage(Long customMessageId){
		this.customMessageId = customMessageId;
	}
	
	//Full Constructor
	public CustomMessage(String subject,String status,Date sentDate,AnanymousUser senderId,AnanymousUser recepientId){
		this.subject = subject;
		this.status = status;
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

	@Column(name="subject",length=300)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name="sent_date",length=70)
	public Date getSentDate() {
		return sentDate;
	}
	
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="free_user_sender_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public AnanymousUser getSenderId() {
		return senderId;
	}

	public void setSenderId(AnanymousUser senderId) {
		this.senderId = senderId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="free_user_recepient_id")
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="sender_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="recepient_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getRecepient() {
		return recepient;
	}

	public void setRecepient(User recepient) {
		this.recepient = recepient;
	}
	
}
