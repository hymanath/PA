package com.itgrids.partyanalyst.model;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="party_trends")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyTrends implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 private Long partyTrendsId;	
	 private Long priority;	
	
	  
	  private Constituency constituency;
	 private String name;
	 private Long constituencyId;
	  
	private Float pervTrenzWt;
	private Float prpWt;
	private Float  youngVotersWt;
	private Float  totalWt;
	private Long id;
	private String type;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="party_trends_id", unique=true, nullable=false)
	 public Long getPartyTrendsId() {
			return partyTrendsId;
		}
		public void setPartyTrendsId(Long partyTrendsId) {
			this.partyTrendsId = partyTrendsId;
		}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	 @Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @Column(name="perv_trenz_wt")
	public Float getPervTrenzWt() {
		return pervTrenzWt;
	}
	  
	public void setPervTrenzWt(Float pervTrenzWt) {
		this.pervTrenzWt = pervTrenzWt;
	}
	 @Column(name="prp_wt")
	public Float getPrpWt() {
		return prpWt;
	}
	public void setPrpWt(Float prpWt) {
		this.prpWt = prpWt;
	}
	@Column(name="young_voters_wt")
	public Float getYoungVotersWt() {
		return youngVotersWt;
	}
	public void setYoungVotersWt(Float youngVotersWt) {
		this.youngVotersWt = youngVotersWt;
	}
	@Column(name="total_wt")
	public Float getTotalWt() {
		return totalWt;
	}
	public void setTotalWt(Float totalWt) {
		this.totalWt = totalWt;
	}
	@Column(name="constituency_id" ,insertable=false ,updatable=false )
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="priority")
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	
	
	
	
	
	 
	
	
	 
	
	
	
	 
	 
	 

}
