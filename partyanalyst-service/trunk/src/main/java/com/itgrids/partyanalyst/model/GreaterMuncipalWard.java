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
@Table(name = "greater_muncipal_ward")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GreaterMuncipalWard extends BaseModel implements java.io.Serializable{

	private Long greaterMuncipalWardId;
	private String divisionName;
	private String divisionCode;
	private Long wardId;
	private Long localElectionBodyId;
	private String reservation;
	private String totalVoters;
	
	private Constituency ward;
	private LocalElectionBody localElectionBody;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "greater_muncipal_ward_id", unique = true, nullable = false)
	public Long getGreaterMuncipalWardId() {
		return greaterMuncipalWardId;
	}
	public void setGreaterMuncipalWardId(Long greaterMuncipalWardId) {
		this.greaterMuncipalWardId = greaterMuncipalWardId;
	}
	
	@Column(name = "division_name")
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	@Column(name = "division_code")
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	
	@Column(name = "ward_id")
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	
	@Column(name = "local_election_body_id")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@Column(name = "Reservation")
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id", insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getWard() {
		return ward;
	}
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id", insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	
	@Column(name = "total_voters")
	public String getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(String totalVoters) {
		this.totalVoters = totalVoters;
	}
}
