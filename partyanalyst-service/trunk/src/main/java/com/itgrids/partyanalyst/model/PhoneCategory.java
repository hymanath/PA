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

@Entity
@Table(name = "phone_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhoneCategory  extends BaseModel implements Serializable {

	private Long phoneCategoryId;
	private String phoneCategoryName;
	
	private Set<PhoneNumber> phoneNumber = new HashSet<PhoneNumber>(0);
	
	public PhoneCategory(){
		
	}
	
	public PhoneCategory(Long phoneCategoryId,String phoneCategoryName){
		this.phoneCategoryId=phoneCategoryId;
		this.phoneCategoryName=phoneCategoryName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "phone_category_id", unique = true, nullable = false)
	public Long getPhoneCategoryId() {
		return phoneCategoryId;
	}
	public void setPhoneCategoryId(Long phoneCategoryId) {
		this.phoneCategoryId = phoneCategoryId;
	}
	
	@Column(name = "phone_category_name", length = 45)
	public String getPhoneCategoryName() {
		return phoneCategoryName;
	}
	public void setPhoneCategoryName(String phoneCategoryName) {
		this.phoneCategoryName = phoneCategoryName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneCategory")
	public Set<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
