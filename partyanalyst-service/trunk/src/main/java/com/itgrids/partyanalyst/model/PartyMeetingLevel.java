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
@Table(name="party_meeting_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingLevel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3502176073912891560L;
	
	private Long partyMeetingLevelId;
	private String level;
	private String code;
	private Integer orderNo;
	
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
}
