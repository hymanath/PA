package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "cadre_address_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreAddressDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long cadreAddressDetailsId;
	private UserAddress userAddressId;
	private UserAddressType userAddressTypeId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	public CadreAddressDetails(){
		
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "cadre_address_details_id", nullable = false, unique = true)
	public Long getCadreAddressDetailsId() {
		return cadreAddressDetailsId;
	}
	public void setCadreAddressDetailsId(Long cadreAddressDetailsId) {
		this.cadreAddressDetailsId = cadreAddressDetailsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_address_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(UserAddress userAddressId) {
		this.userAddressId = userAddressId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_address_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddressType getUserAddressTypeId() {
		return userAddressTypeId;
	}
	public void setUserAddressTypeId(UserAddressType userAddressTypeId) {
		this.userAddressTypeId = userAddressTypeId;
	}
	
	@Column(name = "inserted_by", length = 25)
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by", length = 25)
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "inserted_time", length = 25)
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time", length = 25)
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
