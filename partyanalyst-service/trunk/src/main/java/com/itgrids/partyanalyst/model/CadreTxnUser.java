package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_txn_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CadreTxnUser extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long cadreTxnUserId;	
	private User user;
	private String mobileNo;
	
	public CadreTxnUser(){
		super();
	}
	
	public CadreTxnUser(Long cadreTxnUserId,User user, String mobileNo){
		super();
		this.cadreTxnUserId = cadreTxnUserId;
		this.user = user;
		this.mobileNo = mobileNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_txn_user_id", nullable = false, unique = true)
	public Long getCadreTxnUserId() {
		return cadreTxnUserId;
	}

	public void setCadreTxnUserId(Long cadreTxnUserId) {
		this.cadreTxnUserId = cadreTxnUserId;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "mobile_no", length = 15)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
