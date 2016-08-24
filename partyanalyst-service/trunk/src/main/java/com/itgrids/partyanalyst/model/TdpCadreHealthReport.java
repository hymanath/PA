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
@Table(name = "tdp_cadre_health_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreHealthReport extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tdpCadreHealthReportId;
	private TdpCadre tdpCadre;
	 private Long tdpCadreId;
	 private String reportPath;
	 private Date reportDate;
	 private Date insertedTime;
	 private Long insertedBy;
	 private String isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_health_report_id", unique = true, nullable = false)
	public Long getTdpCadreHealthReportId() {
	return tdpCadreHealthReportId;
	}
	
	public void setTdpCadreHealthReportId(Long tdpCadreHealthReportId) {
		this.tdpCadreHealthReportId = tdpCadreHealthReportId;
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
	@Column(name = "report_path")
	public String getReportPath() {
		return reportPath;
	}
	
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	@Column(name = "report_date")
	public Date getReportDate() {
		return reportDate;
	}
	
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
