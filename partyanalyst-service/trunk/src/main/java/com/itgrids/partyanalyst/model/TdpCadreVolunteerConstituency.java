package com.itgrids.partyanalyst.model;

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
@Table(name = "tdp_cadre_volunteer_constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreVolunteerConstituency extends BaseModel implements java.io.Serializable {
	
	private Long tdpCadreVolunteerConstituencyId;
	private Long constituencyId;
	private Long tdpCadreVolunteerId;
	private Constituency constituency;
	private TdpCadreVolunteer tdpCadreVolunteer;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_volunteer_constituency_id", unique = true, nullable = false)	
	public Long getTdpCadreVolunteerConstituencyId() {
		return tdpCadreVolunteerConstituencyId;
	}
	public void setTdpCadreVolunteerConstituencyId(
			Long tdpCadreVolunteerConstituencyId) {
		this.tdpCadreVolunteerConstituencyId = tdpCadreVolunteerConstituencyId;
	}
	
	
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name = "tdp_cadre_volunteer_id")
	public Long getTdpCadreVolunteerId() {
		return tdpCadreVolunteerId;
	}
	public void setTdpCadreVolunteerId(Long tdpCadreVolunteerId) {
		this.tdpCadreVolunteerId = tdpCadreVolunteerId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
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
