package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pred_expenditure_location")
public class PredExpenditureLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long predExpenditureLocationId;
	private Long predExpenditureId;
	private Long locationAddressId;
	private Long isDeleted;
	
	private LocationAddress locationAddress;
	private PredExpenditure predExpenditure;
	
	@Id
	@Column(name = "pred_expenditure_location_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPredExpenditureLocationId() {
		return predExpenditureLocationId;
	}
	public void setPredExpenditureLocationId(Long predExpenditureLocationId) {
		this.predExpenditureLocationId = predExpenditureLocationId;
	}
	@Column(name = "pred_expenditure_id")
	public Long getPredExpenditureId() {
		return predExpenditureId;
	}
	public void setPredExpenditureId(Long predExpenditureId) {
		this.predExpenditureId = predExpenditureId;
	}
	@Column(name = "location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	@Column(name = "is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pred_expenditure_id", insertable = false, updatable = false)
	public PredExpenditure getPredExpenditure() {
		return predExpenditure;
	}
	public void setPredExpenditure(PredExpenditure predExpenditure) {
		this.predExpenditure = predExpenditure;
	}
	
	
	
	
	
}
