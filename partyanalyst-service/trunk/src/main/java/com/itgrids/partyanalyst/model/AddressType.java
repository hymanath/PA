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
@Table(name="address_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AddressType extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long addressTypeId;
	private String type;
	private Integer orderNo;
	
	private Set<CandidateAddress> candidateAddress = new HashSet<CandidateAddress>(0);
	
	
	public AddressType(){
		
	}
	public AddressType(Long addressTypeId,String type,Integer orderNo ){
		this.addressTypeId=addressTypeId;
		this.type=type;
		this.orderNo=orderNo;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_type_id", unique = true, nullable = false)
	public Long getAddressTypeId() {
		return addressTypeId;
	}
	public void setAddressTypeId(Long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}
	
	@Column(name = "type_name", length = 30)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "order_no", length = 11)
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "addressType")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateAddress> getCandidateAddress() {
		return candidateAddress;
	}
	public void setCandidateAddress(Set<CandidateAddress> candidateAddress) {
		this.candidateAddress = candidateAddress;
	}
	
	

}
