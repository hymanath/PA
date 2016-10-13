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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tdp_cadre_target_count")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreTargetCount {
    
	private Long tdpCadreTargetCountId;
	private Long enrollmentYearId;
	private Long constituencyId;
	private Long targetCount;
	private Long parliamentConstituencyId;
	private String isDeleted;
	
	private EnrollmentYear enrollmentYear;
	private Constituency constituency;
	private Constituency parliamentConstituency;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_target_count_id", unique = true, nullable = false)
	public Long getTdpCadreTargetCountId() {
		return tdpCadreTargetCountId;
	}
	public void setTdpCadreTargetCountId(Long tdpCadreTargetCountId) {
		this.tdpCadreTargetCountId = tdpCadreTargetCountId;
	}
	@Column(name="enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="target_count")
	public Long getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(Long targetCount) {
		this.targetCount = targetCount;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "enrollment_year_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EnrollmentYear getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(EnrollmentYear enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@Column(name = "parliament_constituency_id")
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "parliament_constituency_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

	public Constituency getParliamentConstituency() {
		return parliamentConstituency;
	}
	public void setParliamentConstituency(Constituency parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
   	
}
