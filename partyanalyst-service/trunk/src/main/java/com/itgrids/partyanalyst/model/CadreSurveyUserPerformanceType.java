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
@Table(name = "cadre_survey_user_performance_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreSurveyUserPerformanceType  extends BaseModel implements Serializable{
	
	private Long cadreSurveyUserPerformanceTypeId;
	private String type;
	private String description;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cadre_survey_user_performance_type_id", unique=true, nullable=false)
	public Long getCadreSurveyUserPerformanceTypeId() {
		return cadreSurveyUserPerformanceTypeId;
	}
	public void setCadreSurveyUserPerformanceTypeId(Long cadreSurveyUserPerformanceTypeId) {
		this.cadreSurveyUserPerformanceTypeId = cadreSurveyUserPerformanceTypeId;
	}
	
	@Column(name = "type", length = 70)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "description", length = 70)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "order_no", length = 70)
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "is_deleted", length = 70)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
