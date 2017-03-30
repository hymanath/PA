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
@Table(name = "admin_house_session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminHouseSession extends BaseModel implements Serializable{
	
	private Long adminHouseSessionId;
	private Long adminHouseTermId;
	private Long houseSessionId;
	private Date fromDate;
	private Date toDate;
	private String isActive;
	private String isDeleted;
	
	private AdminHouseTerm adminHouseTerm;
	private HouseSession houseSession;
	private String year;
	private String yearDesc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_session_id", unique = true, nullable = false)
	public Long getAdminHouseSessionId() {
		return adminHouseSessionId;
	}
	public void setAdminHouseSessionId(Long adminHouseSessionId) {
		this.adminHouseSessionId = adminHouseSessionId;
	}
	@Column(name = "admin_house_term_id")
	public Long getAdminHouseTermId() {
		return adminHouseTermId;
	}
	public void setAdminHouseTermId(Long adminHouseTermId) {
		this.adminHouseTermId = adminHouseTermId;
	}
	@Column(name = "house_session_id")
	public Long getHouseSessionId() {
		return houseSessionId;
	}
	public void setHouseSessionId(Long houseSessionId) {
		this.houseSessionId = houseSessionId;
	}
	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_house_term_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseTerm getAdminHouseTerm() {
		return adminHouseTerm;
	}
	public void setAdminHouseTerm(AdminHouseTerm adminHouseTerm) {
		this.adminHouseTerm = adminHouseTerm;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "house_session_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HouseSession getHouseSession() {
		return houseSession;
	}
	public void setHouseSession(HouseSession houseSession) {
		this.houseSession = houseSession;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name = "year_desc")
	public String getYearDesc() {
		return yearDesc;
	}
	public void setYearDesc(String yearDesc) {
		this.yearDesc = yearDesc;
	}
	
	
	
}
