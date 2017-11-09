package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "jb_committee_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeLevel extends BaseModel implements java.io.Serializable {
	
	
	private Long jbCommitteeLevelId;
	private String name;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_level_id", unique = true, nullable = false)
	public Long getJbCommitteeLevelId() {
		return jbCommitteeLevelId;
	}
	public void setJbCommitteeLevelId(Long jbCommitteeLevelId) {
		this.jbCommitteeLevelId = jbCommitteeLevelId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
