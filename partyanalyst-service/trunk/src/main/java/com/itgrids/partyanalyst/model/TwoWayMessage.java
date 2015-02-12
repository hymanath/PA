package com.itgrids.partyanalyst.model;

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
@Table(name="two_way_messages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TwoWayMessage {
	private Long twoWayMessagesId;
	private String message;
	private Date insertedTime;
	private TwoWaySmsMobile twoWaySmsMobile;
	private Booth booth;
	private String mobileNo;
	private String partNo;
	private String actualMsg;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "two_way_messages_id", unique = true, nullable = false)
	public Long getTwoWayMessagesId() {
		return twoWayMessagesId;
	}
	
	public void setTwoWayMessagesId(Long twoWayMessagesId) {
		this.twoWayMessagesId = twoWayMessagesId;
	}
	
	@Column(name = "message")
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "twoway_sms_mobile_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TwoWaySmsMobile getTwoWaySmsMobile() {
		return twoWaySmsMobile;
	}
	
	public void setTwoWaySmsMobile(TwoWaySmsMobile twoWaySmsMobile) {
		this.twoWaySmsMobile = twoWaySmsMobile;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "part_no")
	public String getPartNo() {
		return partNo;
	}
	
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	@Column(name = "actual_msg")
	public String getActualMsg() {
		return actualMsg;
	}
	
	public void setActualMsg(String actualMsg) {
		this.actualMsg = actualMsg;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
