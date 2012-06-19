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
@Table(name="user_connectedto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserConnectedto extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2371208767651956601L;

	private Long userConnectedtoId;
	private AnanymousUser senderId;
	private AnanymousUser recepientId;
	private User userSource;
	private User userTarget;
	
	
	//default constructor
	public UserConnectedto(){
		
	}
	
	//Primary key constructor
	public UserConnectedto(Long userConnectedtoId){
		this.userConnectedtoId = userConnectedtoId;
	}
	
	//Full key constructor
	public UserConnectedto(AnanymousUser senderId,AnanymousUser recepientId){
		this.senderId = senderId;
		this.recepientId = recepientId;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_connectedto_id", unique=true, nullable=false)
	public Long getUserConnectedtoId() {
		return userConnectedtoId;
	}

	public void setUserConnectedtoId(Long userConnectedtoId) {
		this.userConnectedtoId = userConnectedtoId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="free_user_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public AnanymousUser getSenderId() {
		return senderId;
	}

	public void setSenderId(AnanymousUser senderId) {
		this.senderId = senderId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="free_user_target_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public AnanymousUser getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(AnanymousUser recepientId) {
		this.recepientId = recepientId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUserSource() {
		return userSource;
	}

	public void setUserSource(User userSource) {
		this.userSource = userSource;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_target_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(User userTarget) {
		this.userTarget = userTarget;
	}
			
}
