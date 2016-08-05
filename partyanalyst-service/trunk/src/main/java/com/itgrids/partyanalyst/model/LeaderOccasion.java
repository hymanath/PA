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
@Table(name = "leader_occasion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LeaderOccasion extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long leaderOccasionId;
	private Date occasionDate;
	private String isActive;
	private String isDeleted;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private OccasionType occasionType;
	private Long occastionTypeId;
	private Long dateNo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leader_occasion_id", unique = true, nullable = false)
	public Long getLeaderOccasionId() {
		return leaderOccasionId;
	}

	public void setLeaderOccasionId(Long leaderOccasionId) {
		this.leaderOccasionId = leaderOccasionId;
	}

	@Column(name = "occasion_date")
	public Date getOccasionDate() {
		return occasionDate;
	}

	public void setOccasionDate(Date occasionDate) {
		this.occasionDate = occasionDate;
	}
	@Column(name = "date_no")
	public Long getDateNo() {
		return dateNo;
	}

	public void setDateNo(Long dateNo) {
		this.dateNo = dateNo;
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
	@JoinColumn(name = "occasion_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public OccasionType getOccasionType() {
		return occasionType;
	}

	public void setOccasionType(OccasionType occasionType) {
		this.occasionType = occasionType;
	}

	@Column(name = "occasion_type_id")
	public Long getOccastionTypeId() {
		return occastionTypeId;
	}

	public void setOccastionTypeId(Long occastionTypeId) {
		this.occastionTypeId = occastionTypeId;
	}

}
