package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_report_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreReportStatus extends BaseModel implements Serializable{

	private Long tdpCadreReportStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_report_status_id", unique = true, nullable = false)
	public Long getTdpCadreReportStatusId() {
		return tdpCadreReportStatusId;
	}
	public void setTdpCadreReportStatusId(Long tdpCadreReportStatusId) {
		this.tdpCadreReportStatusId = tdpCadreReportStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
