package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "phone_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneType  extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Long phoneTypeId;
	private String type;
	private Integer orderNo;
	
	private Set<PhoneNumber> phoneNumber = new HashSet<PhoneNumber>(0);
	
	public PhoneType(){
		
	}
	
	public PhoneType(Long phoneTypeId,String type,Integer orderNo){
		this.phoneTypeId=phoneTypeId;
		this.type=type;
		this.orderNo=orderNo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_type_id", unique = true, nullable = false)
	public Long getPhoneTypeId() {
		return phoneTypeId;
	}
	public void setPhoneTypeId(Long phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}
	
	
	@Column(name = "type_name", length = 30)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "order_no", length = 15)

	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneType")
	public Set<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	

	

}
