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
@Table(name="party_meeting_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingLevel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3502176073912891560L;
	
	private Long partyMeetingLevelId;
	private String level;
	private String code;
	private Integer orderNo;
	private  RegionScopes regionScopes;
	private Long regionScopesId;
	
	public PartyMeetingLevel(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_level_id", unique=true, nullable=false)
	public Long getPartyMeetingLevelId() {
		return partyMeetingLevelId;
	}
	
	public void setPartyMeetingLevelId(Long partyMeetingLevelId) {
		this.partyMeetingLevelId = partyMeetingLevelId;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@Column(name="region_scopes_id")
	public Long getRegionScopesId() {
		return regionScopesId;
	}

	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}
	
}
