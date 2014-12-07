package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "tdp_cadre_call_center_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCallCenterFeedback extends BaseModel implements java.io.Serializable {


	private static final long serialVersionUID = -7276122905619804507L;
		
	private Long tdpCadreCallCenterFeedbackId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private String remarks;
	private String userName;
	private Date insertedTime;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_call_center_feedback_id", unique = true, nullable = false)
	public Long getTdpCadreCallCenterFeedbackId() {
		return tdpCadreCallCenterFeedbackId;
	}
	public void setTdpCadreCallCenterFeedbackId(Long tdpCadreCallCenterFeedbackId) {
		this.tdpCadreCallCenterFeedbackId = tdpCadreCallCenterFeedbackId;
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
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	

	
		
}
