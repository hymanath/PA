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
@Table(name = "tdp_committee_electrols")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCommitteeElectrols {
	private Long tdpCommitteeElectrolsId;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	private Long tdpCommitteeLevelId;
	private TdpCommitteeLevel tdpCommitteeLevel;
	private Long levelValue;
	private Long tdpCommitteeEnrollmentId;
	private TdpCommitteeEnrollment tdpCommitteeEnrollment;
	private Long tdpCommitteeTypeId;
	private TdpCommitteeType tdpCommitteeType;
	private TdpCommittee tdpCommittee;
	private Long tdpCommitteeId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_committee_electrols_id", unique = true, nullable = false)
	public Long getTdpCommitteeElectrolsId() {
		return tdpCommitteeElectrolsId;
	}
	
	public void setTdpCommitteeElectrolsId(Long tdpCommitteeElectrolsId) {
		this.tdpCommitteeElectrolsId = tdpCommitteeElectrolsId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name = "tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_level_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	
	@Column(name = "level_value")
	public Long getLevelValue() {
		return levelValue;
	}
	
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	
	@Column(name = "tdp_committee_enrollment_id")
	public Long getTdpCommitteeEnrollmentId() {
		return tdpCommitteeEnrollmentId;
	}
	
	public void setTdpCommitteeEnrollmentId(Long tdpCommitteeEnrollmentId) {
		this.tdpCommitteeEnrollmentId = tdpCommitteeEnrollmentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_enrollment_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeEnrollment getTdpCommitteeEnrollment() {
		return tdpCommitteeEnrollment;
	}
	
	public void setTdpCommitteeEnrollment(
			TdpCommitteeEnrollment tdpCommitteeEnrollment) {
		this.tdpCommitteeEnrollment = tdpCommitteeEnrollment;
	}

	@Column(name = "tdp_committee_type_id")
	public Long getTdpCommitteeTypeId() {
		return tdpCommitteeTypeId;
	}

	public void setTdpCommitteeTypeId(Long tdpCommitteeTypeId) {
		this.tdpCommitteeTypeId = tdpCommitteeTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeType getTdpCommitteeType() {
		return tdpCommitteeType;
	}

	public void setTdpCommitteeType(TdpCommitteeType tdpCommitteeType) {
		this.tdpCommitteeType = tdpCommitteeType;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommittee getTdpCommittee() {
		return tdpCommittee;
	}

	public void setTdpCommittee(TdpCommittee tdpCommittee) {
		this.tdpCommittee = tdpCommittee;
	}

	@Column(name = "tdp_committee_id")
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}

	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}
	
	
}
