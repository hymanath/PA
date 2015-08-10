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
@Table(name="party_meeting_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingType extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1678270444831744897L;
	private Long partyMeetingTypeId;
	private String type;
	private Integer orderNo;
	private String code;
	
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
	
}
