package com.itgrids.partyanalyst.model;

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
@Table(name = "cadre_mahanadu_visitdetails")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreMahanaduVisitDetails {

	private Long cadreMahanaduVisitDetailsId;
	private CadreMahanaduVisitInfo cadreMahanaduVisitInfo;
	private String type;
	private Long count;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_mahanadu_visitdetails_id", unique = true, nullable = false)
	public Long getCadreMahanaduVisitDetailsId() {
		return cadreMahanaduVisitDetailsId;
	}
	
	public void setCadreMahanaduVisitDetailsId(Long cadreMahanaduVisitDetailsId) {
		this.cadreMahanaduVisitDetailsId = cadreMahanaduVisitDetailsId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_mahanadu_visitinfo_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreMahanaduVisitInfo getCadreMahanaduVisitInfo() {
		return cadreMahanaduVisitInfo;
	}
	
	public void setCadreMahanaduVisitInfo(
			CadreMahanaduVisitInfo cadreMahanaduVisitInfo) {
		this.cadreMahanaduVisitInfo = cadreMahanaduVisitInfo;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
