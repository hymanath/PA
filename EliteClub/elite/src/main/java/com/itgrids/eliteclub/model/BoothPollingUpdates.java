package com.itgrids.eliteclub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "booth_polling_updates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothPollingUpdates {
	
	private int BoothPollingUpdatesId;
	private String message;
	private String mobileNumber;
    private Date  recievedDate;
    private Date  insertedDate;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getBoothPollingUpdatesId() {
		return BoothPollingUpdatesId;
	}
	public void setBoothPollingUpdatesId(int boothPollingUpdatesId) {
		BoothPollingUpdatesId = boothPollingUpdatesId;
	}
	@Column(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="recieved_date")
	public Date getRecievedDate() {
		return recievedDate;
	}
	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inserted_date",insertable=false)
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
    
    
    


}
