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
@Table(name = "cadre_leadership_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreLeadershipLevel extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long cadreLeadershipLevelId;
	private String leadershipLevel;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_leadership_level_id", unique = true, nullable = false)
	public Long getCadreLeadershipLevelId() {
		return cadreLeadershipLevelId;
	}
	public void setCadreLeadershipLevelId(Long cadreLeadershipLevelId) {
		this.cadreLeadershipLevelId = cadreLeadershipLevelId;
	}
	
	@Column(name="leadership_level")
	public String getLeadershipLevel() {
		return leadershipLevel;
	}
	public void setLeadershipLevel(String leadershipLevel) {
		this.leadershipLevel = leadershipLevel;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
