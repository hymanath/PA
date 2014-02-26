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
@Table(name = "prp_weighteges")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PRPWeighteges extends BaseModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long prpWeightegesId;
	private Constituency constituency;
	private Double weightege;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="prp_weighteges_id", unique= true, nullable = false)
	public Long getPrpWeightegesId() {
		return prpWeightegesId;
	}
	public void setPrpWeightegesId(Long prpWeightegesId) {
		this.prpWeightegesId = prpWeightegesId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@Column(name="weightage")
	public Double getWeightege() {
		return weightege;
	}
	public void setWeightege(Double weightege) {
		this.weightege = weightege;
	}
	
	
}
