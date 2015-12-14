package com.itgrids.partyanalyst.model;

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
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Address extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private String address1;
	private String address2;
	private String mandal;
	private String district;
	private String city;
	private String state;
	//private String country;
	private Long pincode;
	
	private Set<CandidateAddress> candidateAddress = new HashSet<CandidateAddress>(0);
	private Set<AddressContact> addressContact=new HashSet<AddressContact>();

	
	
	public Address() {
	}
	
	public Address(Long addressId,String address1,String address2,String mandal,String district,String city,String state,Long pincode) {
		this.addressId = addressId;
		this.address2=address2;
		this.address1=address1;
		this.city=city;
		//this.country=country;
		this.district=district;
		this.mandal=mandal;
		this.pincode=pincode;
		this.state=state;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id", unique = true, nullable = false)
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name = "address1", length = 30)
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@Column(name = "address2", length = 30)
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Column(name = "mandal", length = 30)
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	
	@Column(name = "district", length = 45)
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Column(name = "city", length = 45)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "state", length = 45)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
/*	
	@Column(name = "country", length = 45)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}*/
	
	@Column(name = "pin_code", length = 45)
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateAddress> getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(Set<CandidateAddress> candidateAddress) {
		this.candidateAddress = candidateAddress;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<AddressContact> getAddressContact() {
		return addressContact;
	}

	public void setAddressContact(Set<AddressContact> addressContact) {
		this.addressContact = addressContact;
	}

	

}
