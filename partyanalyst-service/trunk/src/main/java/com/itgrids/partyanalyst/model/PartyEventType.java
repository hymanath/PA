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
@Table(name = "party_event_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyEventType extends BaseModel implements Serializable {
	private Long partyEventTypeId;
	private String eventType;
	private String description;
	private Long orderNo;
	private String isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_event_type_id", unique = true, nullable = false)
	public Long getPartyEventTypeId() {
		return partyEventTypeId;
	}

	public void setPartyEventTypeId(Long partyEventTypeId) {
		this.partyEventTypeId = partyEventTypeId;
	}

	@Column(name = "event_type", length = 20)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "is_deleted", unique = true, nullable = false)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
