package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "cadre_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreAddress {
	private Long cadreAddressId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private State state;
	private Long stateId;
	private District district;
	private Long districtId;
	private Tehsil tehsil;
	private Long tehsilId;
	private LocalElectionBody localElectionBody;
	private Long localElectionBodyId;
	private String village;
	private String houseNo;
	private String pincode;
	private String landmark;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_address_id", unique = true, nullable = false)
	public Long getCadreAddressId() {
		return cadreAddressId;
	}
	
	public void setCadreAddressId(Long cadreAddressId) {
		this.cadreAddressId = cadreAddressId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "state_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}
	
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "district_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}
	
	public void setDistrict(District district) {
		this.district = district;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tehsil_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}
	
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	
	@Column(name="tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "local_election_body_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	
	@Column(name="local_election_body_id")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@Column(name="village")
	public String getVillage() {
		return village;
	}
	
	public void setVillage(String village) {
		this.village = village;
	}
	
	@Column(name="house_no")
	public String getHouseNo() {
		return houseNo;
	}
	
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@Column(name="pincode")
	public String getPincode() {
		return pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	@Column(name="landmark")
	public String getLandmark() {
		return landmark;
	}
	
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	
}
