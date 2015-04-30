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
@Table(name="event_rfid_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventRfidDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = -6616290358560140697L;
	
	private Long eventRfidDetailsId;
	private Event event;
	private String rfidOperation;
	private String regText;
	private Integer sectorNo;
	private Integer blockNo;
	private Integer entryLimit;
	private String serverWorkMode;
	private String tabWorkMode;
	
	public EventRfidDetails(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_rfid_details_id", unique=true, nullable=false)
	public Long getEventRfidDetailsId() {
		return eventRfidDetailsId;
	}

	public void setEventRfidDetailsId(Long eventRfidDetailsId) {
		this.eventRfidDetailsId = eventRfidDetailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Column(name="rfid_operation")
	public String getRfidOperation() {
		return rfidOperation;
	}

	public void setRfidOperation(String rfidOperation) {
		this.rfidOperation = rfidOperation;
	}

	@Column(name="reg_text")
	public String getRegText() {
		return regText;
	}

	public void setRegText(String regText) {
		this.regText = regText;
	}

	@Column(name="sector_no")
	public Integer getSectorNo() {
		return sectorNo;
	}

	public void setSectorNo(Integer sectorNo) {
		this.sectorNo = sectorNo;
	}

	@Column(name="block_no")
	public Integer getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(Integer blockNo) {
		this.blockNo = blockNo;
	}

	@Column(name="entry_limit")
	public Integer getEntryLimit() {
		return entryLimit;
	}

	public void setEntryLimit(Integer entryLimit) {
		this.entryLimit = entryLimit;
	}

	@Column(name="server_work_mode")
	public String getServerWorkMode() {
		return serverWorkMode;
	}

	public void setServerWorkMode(String serverWorkMode) {
		this.serverWorkMode = serverWorkMode;
	}

	@Column(name="tab_work_mode")
	public String getTabWorkMode() {
		return tabWorkMode;
	}

	public void setTabWorkMode(String tabWorkMode) {
		this.tabWorkMode = tabWorkMode;
	}
}
