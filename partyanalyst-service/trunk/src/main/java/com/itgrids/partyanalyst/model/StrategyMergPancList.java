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
@Table(name = "strategy_merg_panc_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StrategyMergPancList  extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143701370603058373L;

	private Long strategyMergPancListId;
	private StrategyMergePanchayat strategyMergePanchayat;
	private Panchayat panchayat;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "strategy_merg_panc_list_id",unique = true, nullable = false)
	public Long getStrategyMergPancListId() {
		return strategyMergPancListId;
	}
	
	public void setStrategyMergPancListId(Long strategyMergPancListId) {
		this.strategyMergPancListId = strategyMergPancListId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="strategy_merge_panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public StrategyMergePanchayat getStrategyMergePanchayat() {
		return strategyMergePanchayat;
	}
	
	public void setStrategyMergePanchayat(
			StrategyMergePanchayat strategyMergePanchayat) {
		this.strategyMergePanchayat = strategyMergePanchayat;
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
	
	
}
