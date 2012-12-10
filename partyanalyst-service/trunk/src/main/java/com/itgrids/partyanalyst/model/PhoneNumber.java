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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "phone_number")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneNumber  extends BaseModel implements Serializable{

	private Long phoneNumberId;
	private PhoneType phoneType;
	private PhoneCategory phoneCategory;
	private String number;
	
	private Set<CandidatePhone> candidatePhone = new HashSet<CandidatePhone>(0);
	private Set<AddressContact> addressContact=new HashSet<AddressContact>(0);
	
	public PhoneNumber(){
		
	}
	
	public PhoneNumber(Long phoneNumberId,PhoneType phoneType,PhoneCategory phoneCategory,String number){
		this.phoneNumberId=phoneNumberId;
		this.phoneType=phoneType;
		this.phoneCategory=phoneCategory;
		this.number=number;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_number_id", unique = true, nullable = false)
	public Long getPhoneNumberId() {
		return phoneNumberId;
	}
	public void setPhoneNumberId(Long phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PhoneCategory getPhoneCategory() {
		return phoneCategory;
	}
	

	public void setPhoneCategory(PhoneCategory phoneCategory) {
		this.phoneCategory= phoneCategory;
	}
	
	@Column(name = "number", length = 45)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneNumber")
	public Set<CandidatePhone> getCandidatePhone() {
		return candidatePhone;
	}

	public void setCandidatePhone(Set<CandidatePhone> candidatePhone) {
		this.candidatePhone = candidatePhone;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneNumber")
	public Set<AddressContact> getAddressContact() {
		return addressContact;
	}

	public void setAddressContact(Set<AddressContact> addressContact) {
		this.addressContact = addressContact;
	}

}

