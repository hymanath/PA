package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;


@Entity
@Table(name="house_session")
public class HouseSession implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long houseSessionId;
	private Long adminHouseId;
	private String sessionName;
	private String isDeleted;
	private String isActive;
	
	private AdminHouse adminHouse;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="house_session_id", unique=true, nullable=false)
	public Long getHouseSessionId() {
		return houseSessionId;
	}
	public void setHouseSessionId(Long houseSessionId) {
		this.houseSessionId = houseSessionId;
	}
	@Column(name="admin_house_id")
	public Long getAdminHouseId() {
		return adminHouseId;
	}
	public void setAdminHouseId(Long adminHouseId) {
		this.adminHouseId = adminHouseId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "admin_house_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AdminHouse getAdminHouse() {
		return adminHouse;
	}
	public void setAdminHouse(AdminHouse adminHouse) {
		this.adminHouse = adminHouse;
	}
	@Column(name="session_name")
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
}