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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name="calltracking_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CallTrackingDetail extends BaseModel implements Serializable {

	private Long callTrackingDetailId;
	private CallTrackingProblem callTrackingProblem;
	private String name;
	private String mobile;
	private Date problemAddedDate;
	private String villageOrTown;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "calltracking_detail_id", unique = true, nullable = false)
	public Long getCallTrackingDetailId() {
		return callTrackingDetailId;
	}
	public void setCallTrackingDetailId(Long callTrackingDetailId) {
		this.callTrackingDetailId = callTrackingDetailId;
	}
	

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public CallTrackingProblem getCallTrackingProblem() {
		return callTrackingProblem;
	}
	public void setCallTrackingProblem(CallTrackingProblem callTrackingProblem) {
		this.callTrackingProblem = callTrackingProblem;
	}

	@Column(name = "name" , length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "mobile" , length = 25)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "problem_added_date", length = 10)
	public Date getProblemAddedDate() {
		return problemAddedDate;
	}
	public void setProblemAddedDate(Date problemAddedDate) {
		this.problemAddedDate = problemAddedDate;
	}
	
	@Column(name = "village_town" , length = 50)
	public String getVillageOrTown() {
		return villageOrTown;
	}
	public void setVillageOrTown(String villageOrTown) {
		this.villageOrTown = villageOrTown;
	}
	
}
