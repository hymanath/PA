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
@Table(name="message_to_party")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageToParty extends BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 3030367834367449209L;

	private Long messageToPartyId;
	private String name;
	private Constituency constituency;
	private String message;
	private Party party;
	private Date sentTime;
	private String isDelete;
	private String isPrivate;
	private String isApproved;
	
	public MessageToParty()
	{}
	
	public MessageToParty(String name,Constituency constituency,String message,Party party,Date sentTime,String isDelect, String isPrivate,String isApproved)
	{
		this.isDelete = isDelect;
		this.name = name;
		this.constituency = constituency;
		this.message=message;
		this.party = party;
		this.sentTime=sentTime;
		this.isApproved=isApproved;
		this.isDelete=isDelete;
		this.isPrivate=isPrivate;
	}

	@Id
	@Column(name="message_to_party_id ",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getMessageToPartyId() {
		return messageToPartyId;
	}

	public void setMessageToPartyId(Long messageToPartyId) {
		this.messageToPartyId = messageToPartyId;
	}
	
	@Column(name="name",length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name="message",length=500)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="sent_time")

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	@Column(name="is_delete",length=10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="is_private",length=10)
	public String getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	@Column(name="is_approved",length=10)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
	
}

