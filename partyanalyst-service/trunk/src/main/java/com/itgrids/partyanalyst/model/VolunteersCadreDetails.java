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
@Table(name = "volunteers_cadre_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VolunteersCadreDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long volunteersCadreDetailsId;
	private Long tdpCadreId;
	private Long eventId;
	private Long volunteerDesignationId;
	private String workArea;
	private String mobileNo;
	private String isDelete;
	
	private TdpCadre tdpCadre;
	private Event event;
	private VolunteerDesgination volunteerDesgination;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "volunteers_cadre_details_id", unique = true, nullable = false)
	public Long getVolunteersCadreDetailsId() {
		return volunteersCadreDetailsId;
	}
	public void setVolunteersCadreDetailsId(Long volunteersCadreDetailsId) {
		this.volunteersCadreDetailsId = volunteersCadreDetailsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "volunteer_designation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public VolunteerDesgination getVolunteerDesgination() {
		return volunteerDesgination;
	}
	public void setVolunteerDesgination(VolunteerDesgination volunteerDesgination) {
		this.volunteerDesgination = volunteerDesgination;
	}
	
	@Column(name = "volunteer_designation_id")
	public Long getVolunteerDesignationId() {
		return volunteerDesignationId;
	}
	public void setVolunteerDesignationId(Long volunteerDesignationId) {
		this.volunteerDesignationId = volunteerDesignationId;
	}
	
	@Column(name = "work_area")
	public String getWorkArea() {
		return workArea;
	}
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
