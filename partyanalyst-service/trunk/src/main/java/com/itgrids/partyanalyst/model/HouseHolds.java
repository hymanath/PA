package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="house_holds")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HouseHolds extends BaseModel implements Serializable {
	private Long houseHoldId;
	private String houseNo;
	private Panchayat panchayat;
	private LocalElectionBody localElectionBody;
	private Long panchaytId;
	private Long localElectionBodyId;
	private Set<HouseHoldVoter> houseHoldVoters = new HashSet<HouseHoldVoter>(0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "house_hold_id", unique = true, nullable = false)
	public Long getHouseHoldId() {
		return houseHoldId;
	}
	public void setHouseHoldId(Long houseHoldId) {
		this.houseHoldId = houseHoldId;
	}
	
	@Column(name = "house_no", length = 45)
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="local_election_body_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	
	@Column(name = "panchayat_id")
	public Long getPanchaytId() {
		return panchaytId;
	}
	public void setPanchaytId(Long panchaytId) {
		this.panchaytId = panchaytId;
	}
	
	@Column(name = "local_election_body_id")
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "houseHolds")
	public Set<HouseHoldVoter> getHouseHoldVoters() {
		return houseHoldVoters;
	}
	public void setHouseHoldVoters(Set<HouseHoldVoter> houseHoldVoters) {
		this.houseHoldVoters = houseHoldVoters;
	}

}
