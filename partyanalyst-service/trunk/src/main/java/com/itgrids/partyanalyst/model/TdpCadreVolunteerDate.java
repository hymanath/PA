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




@Entity
@Table(name = "tdp_cadre_volunteer_date")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreVolunteerDate extends BaseModel implements java.io.Serializable {
	
	private Long tdpCadreVolunteerDateId;
	private Date date;
	private Long tdpCadreVolunteerId;
	private TdpCadreVolunteer tdpCadreVolunteer;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_volunteer_date_id", unique = true, nullable = false)	
	public Long getTdpCadreVolunteerDateId() {
		return tdpCadreVolunteerDateId;
	}
	public void setTdpCadreVolunteerDateId(Long tdpCadreVolunteerDateId) {
		this.tdpCadreVolunteerDateId = tdpCadreVolunteerDateId;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Column(name = "tdp_cadre_volunteer_id")
	public Long getTdpCadreVolunteerId() {
		return tdpCadreVolunteerId;
	}
	public void setTdpCadreVolunteerId(Long tdpCadreVolunteerId) {
		this.tdpCadreVolunteerId = tdpCadreVolunteerId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_volunteer_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public TdpCadreVolunteer getTdpCadreVolunteer() {
		return tdpCadreVolunteer;
	}
	public void setTdpCadreVolunteer(TdpCadreVolunteer tdpCadreVolunteer) {
		this.tdpCadreVolunteer = tdpCadreVolunteer;
	}
	
	
	

}
