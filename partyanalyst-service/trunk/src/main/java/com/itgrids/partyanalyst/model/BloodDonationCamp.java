package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "blood_donation_camp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BloodDonationCamp extends BaseModel implements Serializable{
	
	private Long bloodDonationCampId;
	private String campName;
	private Date fromDate;
	private Date toDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_donation_camp_id", unique = true, nullable = false)
	public Long getBloodDonationCampId() {
		return bloodDonationCampId;
	}
	public void setBloodDonationCampId(Long bloodDonationCampId) {
		this.bloodDonationCampId = bloodDonationCampId;
	}
	
	@Column(name="camp_name")
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
