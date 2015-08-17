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
@Table(name = "cadre_comminication_skills_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreComminicationSkillsStatus extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long cadreComminicationSkillsStatusId;
	private String status;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_comminication_skills_status_id", unique = true, nullable = false)
	public Long getCadreComminicationSkillsStatusId() {
		return cadreComminicationSkillsStatusId;
	}
	public void setCadreComminicationSkillsStatusId(
			Long cadreComminicationSkillsStatusId) {
		this.cadreComminicationSkillsStatusId = cadreComminicationSkillsStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
