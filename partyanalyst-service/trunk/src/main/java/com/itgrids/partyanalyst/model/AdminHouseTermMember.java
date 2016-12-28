package com.itgrids.partyanalyst.model;

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
@Table(name = "admin_house_term_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdminHouseTermMember extends BaseModel implements Serializable{
	
	private Long adminHouseTermMemberId;
	private Long adminHouseTermId;
	private Long adminHouseMemberId;
	private String isDeleted;
	private Long seatNo;
	private Date fromDate;
	private Date toDate;

	private AdminHouseTerm adminHouseTerm;
	private AdminHouseMember adminHouseMember;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admin_house_term_member_id", unique = true, nullable = false)
	public Long getAdminHouseTermMemberId() {
		return adminHouseTermMemberId;
	}
	public void setAdminHouseTermMemberId(Long adminHouseTermMemberId) {
		this.adminHouseTermMemberId = adminHouseTermMemberId;
	}
	@Column(name = "admin_house_term_id")
	public Long getAdminHouseTermId() {
		return adminHouseTermId;
	}
	public void setAdminHouseTermId(Long adminHouseTermId) {
		this.adminHouseTermId = adminHouseTermId;
	}
	@Column(name = "admin_house_member_id")
	public Long getAdminHouseMemberId() {
		return adminHouseMemberId;
	}
	public void setAdminHouseMemberId(Long adminHouseMemberId) {
		this.adminHouseMemberId = adminHouseMemberId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "seat_no")
	public Long getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Long seatNo) {
		this.seatNo = seatNo;
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

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_term_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseTerm getAdminHouseTerm() {
		return adminHouseTerm;
	}
	public void setAdminHouseTerm(AdminHouseTerm adminHouseTerm) {
		this.adminHouseTerm = adminHouseTerm;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_member_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouseMember getAdminHouseMember() {
		return adminHouseMember;
	}
	public void setAdminHouseMember(AdminHouseMember adminHouseMember) {
		this.adminHouseMember = adminHouseMember;
	}
}
