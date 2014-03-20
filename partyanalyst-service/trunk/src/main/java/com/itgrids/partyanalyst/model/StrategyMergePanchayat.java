package com.itgrids.partyanalyst.model;

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
@Table(name = "strategy_merge_panchayat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StrategyMergePanchayat extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2682601701244742580L;

	private Long strategyMergePanchayatId;
	private String type;
	private Panchayat panchayat;
	private Long typeValue;
	private Constituency constituency;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "strategy_merge_panchayat_id",unique = true, nullable = false)
	public Long getStrategyMergePanchayatId() {
		return strategyMergePanchayatId;
	}
	
	public void setStrategyMergePanchayatId(Long strategyMergePanchayatId) {
		this.strategyMergePanchayatId = strategyMergePanchayatId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}	
	
	@Column(name = "type_value")
	public Long getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Long typeValue) {
		this.typeValue = typeValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
}
