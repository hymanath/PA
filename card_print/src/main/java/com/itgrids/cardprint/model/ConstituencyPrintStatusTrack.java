package com.itgrids.cardprint.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "constituency_print_status_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstituencyPrintStatusTrack extends BaseModel implements java.io.Serializable {

	private Long constituencyPrintStatusTrackId;
	private Long constituencyPrintStatusId;
	private Long constituencyId;
	private Long printVendorId;
	private Long printStatusId;
	private String remarks;
	private Long updatedUserId;
	private Date updatedTime;
	
	private ConstituencyPrintStatus constituencyPrintStatus;
	private Constituency constituency;
	private CardPrintVendor cardPrintVendor;
	private PrintStatus printStatus;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "constituency_print_status_track_id", unique = true, nullable = false)
	public Long getConstituencyPrintStatusTrackId() {
		return constituencyPrintStatusTrackId;
	}
	public void setConstituencyPrintStatusTrackId(
			Long constituencyPrintStatusTrackId) {
		this.constituencyPrintStatusTrackId = constituencyPrintStatusTrackId;
	}
	@Column(name = "constituency_print_status_id")
	public Long getConstituencyPrintStatusId() {
		return constituencyPrintStatusId;
	}
	public void setConstituencyPrintStatusId(Long constituencyPrintStatusId) {
		this.constituencyPrintStatusId = constituencyPrintStatusId;
	}
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name = "print_vendor_id")
	public Long getPrintVendorId() {
		return printVendorId;
	}
	public void setPrintVendorId(Long printVendorId) {
		this.printVendorId = printVendorId;
	}
	@Column(name = "print_status_id")
	public Long getPrintStatusId() {
		return printStatusId;
	}
	public void setPrintStatusId(Long printStatusId) {
		this.printStatusId = printStatusId;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "updated_by")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_print_status_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyPrintStatus getConstituencyPrintStatus() {
		return constituencyPrintStatus;
	}
	public void setConstituencyPrintStatus(
			ConstituencyPrintStatus constituencyPrintStatus) {
		this.constituencyPrintStatus = constituencyPrintStatus;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "print_vendor_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintVendor getCardPrintVendor() {
		return cardPrintVendor;
	}
	public void setCardPrintVendor(CardPrintVendor cardPrintVendor) {
		this.cardPrintVendor = cardPrintVendor;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "print_status_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PrintStatus getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(PrintStatus printStatus) {
		this.printStatus = printStatus;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
