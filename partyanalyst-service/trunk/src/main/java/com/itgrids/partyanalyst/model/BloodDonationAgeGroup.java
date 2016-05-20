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
@Table(name = "blood_donation_age_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodDonationAgeGroup extends BaseModel implements Serializable{

	private Long bloodDonationAgeGroupId;
	private String ageGroup;
	private Long mainAge;
	private Long maxAge;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_donation_age_group_id", unique = true, nullable = false)
	public Long getBloodDonationAgeGroupId() {
		return bloodDonationAgeGroupId;
	}
	public void setBloodDonationAgeGroupId(Long bloodDonationAgeGroupId) {
		this.bloodDonationAgeGroupId = bloodDonationAgeGroupId;
	}
	
	@Column(name="age_group")
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	@Column(name="main_age")
	public Long getMainAge() {
		return mainAge;
	}
	public void setMainAge(Long mainAge) {
		this.mainAge = mainAge;
	}
	
	@Column(name="max_age")
	public Long getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}
}
