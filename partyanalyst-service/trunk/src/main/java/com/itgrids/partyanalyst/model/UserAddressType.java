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
@Table(name = "user_address_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAddressType extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long userAddressTypeId;
	private String type;
	
	public UserAddressType(){
		
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "user_address_type_id", nullable = false, unique = true)
	public Long getUserAddressTypeId() {
		return userAddressTypeId;
	}
	public void setUserAddressTypeId(Long userAddressTypeId) {
		this.userAddressTypeId = userAddressTypeId;
	}
	
	@Column(name = "type", length = 25)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
