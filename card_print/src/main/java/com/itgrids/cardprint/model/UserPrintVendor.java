package com.itgrids.cardprint.model;

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
@Table(name = "user_print_vendor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPrintVendor extends BaseModel implements Serializable {


	private Long userPrintVendorId;
	private Long userId;
	private Long printVendorId;
	
	private User user;
	private CardPrintVendor cardPrintVendor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_print_vendor_id", unique = true, nullable = false)
	public Long getUserPrintVendorId() {
		return userPrintVendorId;
	}
	public void setUserPrintVendorId(Long userPrintVendorId) {
		this.userPrintVendorId = userPrintVendorId;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "print_vendor_id")
	public Long getPrintVendorId() {
		return printVendorId;
	}
	public void setPrintVendorId(Long printVendorId) {
		this.printVendorId = printVendorId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "print_vendor_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintVendor getCardPrintVendor() {
		return cardPrintVendor;
	}
	public void setCardPrintVendor(CardPrintVendor cardPrintVendor) {
		this.cardPrintVendor = cardPrintVendor;
	}
	
}
