package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="message_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageType extends BaseModel implements Serializable {

	private static final long serialVersionUID = 4911420226567088187L;

	private Long messageTypeId;
	private String messageType;
	private String description;
	
	//Default Constructor
	public MessageType(){
		
	}
	
	//PrimaryKey Constructor
	public MessageType(Long messageTypeId){
		this.messageTypeId = messageTypeId;
	}
	
	//Full Constructor
	public MessageType(String messageType,String description){
		this.messageType = messageType;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="message_type_id", unique=true, nullable=false)
	public Long getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Long messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	@Column(name="message_type",length=30)
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Column(name="description",length=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
