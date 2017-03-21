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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="custom_report_observer")
public class CustomReportObserver implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportObserverId;
	public  Long customReportId;
	public Long tdpCadreId;
	public String isDeleted;
	
	public CustomReport customReport;
	public TdpCadre tdpCadre;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_observer_id",nullable=false,unique=true)
	public Long getCustomReportObserverId() {
		return customReportObserverId;
	}
	public void setCustomReportObserverId(Long customReportObserverId) {
		this.customReportObserverId = customReportObserverId;
	}
	@Column(name="custom_report_id")
	public Long getCustomReportId() {
		return customReportId;
	}
	public void setCustomReportId(Long customReportId) {
		this.customReportId = customReportId;
	}
	@Column(name="tdpcadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_report_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public CustomReport getCustomReport() {
		return customReport;
	}
	public void setCustomReport(CustomReport customReport) {
		this.customReport = customReport;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

}
