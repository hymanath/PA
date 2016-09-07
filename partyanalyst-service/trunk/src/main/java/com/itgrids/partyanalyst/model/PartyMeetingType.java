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
@Table(name="party_meeting_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingType extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1678270444831744897L;
	private Long partyMeetingTypeId;
	private String type;
	private Integer orderNo;
	private String code;
	private PartyMeetingLevel partyMeetingLevel;
	
	private Long partyMeetingLevelId;
	private Long partyMeetingMainTypeId;
	
    private PartyMeetingMainType partyMeetingMainType;
	private String isActive;
	
	public PartyMeetingType(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_type_id", unique=true, nullable=false)
	public Long getPartyMeetingTypeId() {
		return partyMeetingTypeId;
	}

	public void setPartyMeetingTypeId(Long partyMeetingTypeId) {
		this.partyMeetingTypeId = partyMeetingTypeId;
	}

	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingLevel getPartyMeetingLevel() {
		return partyMeetingLevel;
	}

	public void setPartyMeetingLevel(PartyMeetingLevel partyMeetingLevel) {
		this.partyMeetingLevel = partyMeetingLevel;
	}

	@Column(name="party_meeting_level_id")
	public Long getPartyMeetingLevelId() {
		return partyMeetingLevelId;
	}

	public void setPartyMeetingLevelId(Long partyMeetingLevelId) {
		this.partyMeetingLevelId = partyMeetingLevelId;
	}

	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="party_meeting_main_type_id")
	public Long getPartyMeetingMainTypeId() {
		return partyMeetingMainTypeId;
	}

	public void setPartyMeetingMainTypeId(Long partyMeetingMainTypeId) {
		this.partyMeetingMainTypeId = partyMeetingMainTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_main_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMainType getPartyMeetingMainType() {
		return partyMeetingMainType;
	}
	public void setPartyMeetingMainType(PartyMeetingMainType partyMeetingMainType) {
		this.partyMeetingMainType = partyMeetingMainType;
	}
	
	
	
}
