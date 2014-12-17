package com.itgrids.partyanalyst.model;

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
@Table(name = "verified_data_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerifiedDataStatus {

	private Long verifiedDataStatusId;
	private Long voterId;
	private Long userId;
	private Long familyVoterId;
	private String uniqueKey;
	private String status;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="verified_data_status_id", unique = true, nullable = false)
	public Long getVerifiedDataStatusId() {
		return verifiedDataStatusId;
	}
	public void setVerifiedDataStatusId(Long verifiedDataStatusId) {
		this.verifiedDataStatusId = verifiedDataStatusId;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="family_voter_id")
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	
	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
