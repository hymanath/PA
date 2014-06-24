package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="hh_leader")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHLeader extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String mobileNo;
	//private String uniqueCode;
	private String is_active;
	private String voterId; 
	/*private Long constituencyId;
	private Long boothId;
	private Constituency constituency;
	private Booth booth;*/
	
	//private HHBoothLeader hhBoothLeader;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	/*@Column(name="unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}*/
	
	@Column(name="is_active")
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	
	@Column(name="voter_id")
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
	/*@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
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
	@JoinColumn(name = "constituency_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}*/
	
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hhBoothLeader")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHBoothLeader getHhBoothLeader() {
		return hhBoothLeader;
	}
	public void setHhBoothLeader(HHBoothLeader hhBoothLeader) {
		this.hhBoothLeader = hhBoothLeader;
	}*/
	
	
	
	

}
