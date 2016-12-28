package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

@Entity
@Table(name="admin_house_term")
public class AdminHouseTerm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long adminHouseTermId;
	private Long adminHouseId;
	private Date fromDate;
	private Date toDate;
	private String isActive;
	private String isDeleted;
	
	private AdminHouse adminHouse;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="admin_house_term_id", unique=true, nullable=false)
	
	public Long getAdminHouseTermId() {
		return adminHouseTermId;
	}
	public void setAdminHouseTermId(Long adminHouseTermId) {
		this.adminHouseTermId = adminHouseTermId;
	}
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="admin_house_id")
	public Long getAdminHouseId() {
		return adminHouseId;
	}
	public void setAdminHouseId(Long adminHouseId) {
		this.adminHouseId = adminHouseId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_house_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouse getAdminHouse() {
		return adminHouse;
	}
	public void setAdminHouse(AdminHouse adminHouse) {
		this.adminHouse = adminHouse;
	}
	

}