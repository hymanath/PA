package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name="hh_booth_leader")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHBoothLeader extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3497926842401872275L;
	
	private Long hhBoothLeaderId;
	private Long boothId;
	private Long hhLeaderId;
	private Long constituencyId;
	private Booth booth;
	private HHLeader hhLeader;
	private Constituency constituency;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hh_booth_leader_id", unique = true, nullable = false)
	public Long getHhBoothLeaderId() {
		return hhBoothLeaderId;
	}
	public void setHhBoothLeaderId(Long hhBoothLeaderId) {
		this.hhBoothLeaderId = hhBoothLeaderId;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name="hh_leader_id")
	public Long getHhLeaderId() {
		return hhLeaderId;
	}
	public void setHhLeaderId(Long hhLeaderId) {
		this.hhLeaderId = hhLeaderId;
	}
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hh_leader_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHLeader getHhLeader() {
		return hhLeader;
	}
	public void setHhLeader(HHLeader hhLeader) {
		this.hhLeader = hhLeader;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	

}
