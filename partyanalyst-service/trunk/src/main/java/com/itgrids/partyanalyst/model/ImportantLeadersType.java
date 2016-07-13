package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "important_leaders_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImportantLeadersType extends BaseModel implements Serializable{

	private Long importantLeadersTypeId;
	private String position;
	private Long importantLeadersLevelId;
	private int orderNo;
	private Long userId;
	private Date insertedTime;
	private String isActive;
	
	private ImportantLeadersLevel importantLeadersLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "important_leaders_type_id", unique = true, nullable = false)
	public Long getImportantLeadersTypeId() {
		return importantLeadersTypeId;
	}
	public void setImportantLeadersTypeId(Long importantLeadersTypeId) {
		this.importantLeadersTypeId = importantLeadersTypeId;
	}
	
	@Column(name="position")
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name="important_leaders_level_id")
	public Long getImportantLeadersLevelId() {
		return importantLeadersLevelId;
	}
	public void setImportantLeadersLevelId(Long importantLeadersLevelId) {
		this.importantLeadersLevelId = importantLeadersLevelId;
	}
	
	@Column(name="order_no")
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="important_leaders_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ImportantLeadersLevel getImportantLeadersLevel() {
		return importantLeadersLevel;
	}
	public void setImportantLeadersLevel(ImportantLeadersLevel importantLeadersLevel) {
		this.importantLeadersLevel = importantLeadersLevel;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
