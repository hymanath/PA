package com.itgrids.model;

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
@Table(name = "nrega_fa_vacant_panchayat")
public class NregaFAVacantPanchayat {

	private Long nregaFaVacantPanchayatId;
	private String panchayatName;
	private String uniqueCode;
	private Long prPanchayatId;
	private Long panchayatId;
	private Long listTypeId;
	private Long nregaFaTypeId;
	private Long locationAddressId;
	private String isFilled;
	private Long noOfVacant;
	
	private PrPanchayat prPanchayat;
	private Panchayat panchayat;
	private NregaFAType nregaFAType;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="nrega_fa_vacant_panchayat_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaFaVacantPanchayatId() {
		return nregaFaVacantPanchayatId;
	}
	public void setNregaFaVacantPanchayatId(Long nregaFaVacantPanchayatId) {
		this.nregaFaVacantPanchayatId = nregaFaVacantPanchayatId;
	}
	
	@Column(name="panchayat_name")
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	@Column(name="unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	@Column(name="pr_panchayat_id")
	public Long getPrPanchayatId() {
		return prPanchayatId;
	}
	public void setPrPanchayatId(Long prPanchayatId) {
		this.prPanchayatId = prPanchayatId;
	}
	
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@Column(name="list_type_id")
	public Long getListTypeId() {
		return listTypeId;
	}
	public void setListTypeId(Long listTypeId) {
		this.listTypeId = listTypeId;
	}
	
	@Column(name="nrega_fa_type_id")
	public Long getNregaFaTypeId() {
		return nregaFaTypeId;
	}
	public void setNregaFaTypeId(Long nregaFaTypeId) {
		this.nregaFaTypeId = nregaFaTypeId;
	}
	
	@Column(name="location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	
	@Column(name="is_filled")
	public String getIsFilled() {
		return isFilled;
	}
	public void setIsFilled(String isFilled) {
		this.isFilled = isFilled;
	}
	
	@Column(name="no_of_vacant")
	public Long getNoOfVacant() {
		return noOfVacant;
	}
	public void setNoOfVacant(Long noOfVacant) {
		this.noOfVacant = noOfVacant;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pr_panchayat_id", insertable = false, updatable = false)
	public PrPanchayat getPrPanchayat() {
		return prPanchayat;
	}
	public void setPrPanchayat(PrPanchayat prPanchayat) {
		this.prPanchayat = prPanchayat;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_fa_type_id", insertable = false, updatable = false)
	public NregaFAType getNregaFAType() {
		return nregaFAType;
	}
	public void setNregaFAType(NregaFAType nregaFAType) {
		this.nregaFAType = nregaFAType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
}
