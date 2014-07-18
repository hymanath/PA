package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

	@Entity
	@Table(name = "committee_level")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public class CommitteeLevel {

	
	private Long commiiteeLevelId;
	private String commiiteeLevel;
	private Long orderNo;
	
	public CommitteeLevel()
	{
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commiitee_level_id", unique=true, nullable=false)
	public Long getCommiiteeLevelId() {
		return commiiteeLevelId;
	}
	public void setCommiiteeLevelId(Long commiiteeLevelId) {
		this.commiiteeLevelId = commiiteeLevelId;
	}
	@Column(name="commiitee_level")
	public String getCommiiteeLevel() {
		return commiiteeLevel;
	}
	public void setCommiiteeLevel(String commiiteeLevel) {
		this.commiiteeLevel = commiiteeLevel;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
