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
@Table(name = "user_sms_receiver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSmsReceiver extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 2770484105252236590L;

	private Long userSmsReceiverId;
	private UserSmsSent userSmsSent;
	private ReceiverType receiverType;
	private Long receiverId;
	private String firstname;
	private String lastname;
	private String mobile;
	private String smsSentType;
	private String isDeleted;
	private Long noOfSmsSent;
	
	public UserSmsReceiver(){
		
	}
	public UserSmsReceiver(Long userSmsReceiverId,UserSmsSent userSmsSent,ReceiverType receiverType,Long receiverId,String firstname,String lastname,String mobile,String smsSentType) {
		this.userSmsReceiverId = userSmsReceiverId;
		this.userSmsSent = userSmsSent;
		this.receiverType = receiverType;
		this.receiverId = receiverId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.smsSentType = smsSentType;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_sms_receiver_id", unique = true, nullable = false)
	public Long getUserSmsReceiverId() {
		return userSmsReceiverId;
	}
	public void setUserSmsReceiverId(Long userSmsReceiverId) {
		this.userSmsReceiverId = userSmsReceiverId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_sms_sent_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserSmsSent getUserSmsSent() {
		return userSmsSent;
	}
	
	public void setUserSmsSent(UserSmsSent userSmsSent) {
		this.userSmsSent = userSmsSent;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ReceiverType getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(ReceiverType receiverType) {
		this.receiverType = receiverType;
	}
	
	@Column(name="receiver_id" , length = 15)
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	
	@Column(name="firstname" , length = 45)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name="lastname" , length = 45)
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name="mobile" , length = 45)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="sms_sent_type" , length = 45)
	public String getSmsSentType() {
		return smsSentType;
	}
	public void setSmsSentType(String smsSentType) {
		this.smsSentType = smsSentType;
	}

	@Column(name="is_deleted" , length = 8)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="no_of_sms_sent")
	public Long getNoOfSmsSent() {
	return noOfSmsSent;
	}
	public void setNoOfSmsSent(Long noOfSmsSent) {
	this.noOfSmsSent = noOfSmsSent;
	}
}
