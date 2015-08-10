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
@Table(name="party_meeting_occurrence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingOccurrence extends BaseModel implements Serializable{

	private static final long serialVersionUID = 2279573506902782347L;
	
	private Long partyMeetingOccurrenceId;
	private String occurrence;
	private Integer orderNo;
	
	public PartyMeetingOccurrence(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_occurrence_id", unique=true, nullable=false)
	public Long getPartyMeetingOccurrenceId() {
		return partyMeetingOccurrenceId;
	}

	public void setPartyMeetingOccurrenceId(Long partyMeetingOccurrenceId) {
		this.partyMeetingOccurrenceId = partyMeetingOccurrenceId;
	}

	@Column(name="occurrence")
	public String getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}

	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
