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
@Table(name = "card_print_validation_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintValidationUser extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3991487640208871699L;
	
	private Long cardPrintValidationUserId;
	private String name;
	private String username;
	private String password;
	private String mobileno;
	private String isActive;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_print_validation_user_id", unique = true, nullable = false)
	public Long getCardPrintValidationUserId() {
		return cardPrintValidationUserId;
	}
	public void setCardPrintValidationUserId(Long cardPrintValidationUserId) {
		this.cardPrintValidationUserId = cardPrintValidationUserId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="mobile_no")
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
