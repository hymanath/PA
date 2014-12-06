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
@Table(name = "tdp_cadre_call_center_remarks")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCallCenterRemarks {
	
	
	private Long tdpCadreCallCenterRemarksId;
	private TdpCadre tdpCadre;
	private String remarks;
	private Long tdpCadreId;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_call_center_remarks_id", unique = true, nullable = false)
	public Long getTdpCadreCallCenterRemarksId() {
		return tdpCadreCallCenterRemarksId;
	}
	public void setTdpCadreCallCenterRemarksId(Long tdpCadreCallCenterRemarksId) {
		this.tdpCadreCallCenterRemarksId = tdpCadreCallCenterRemarksId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	
}
