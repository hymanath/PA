package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ivr_respondent_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrRespondentType {
	private Long ivrRespondentTypeId;
	private String respondentType;
	private String description;
	private String orderNo;
	private String isDeleted;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ivr_respondent_type_id", unique=true, nullable=false)
	public Long getIvrRespondentTypeId() {
		return ivrRespondentTypeId;
	}
	public void setIvrRespondentTypeId(Long ivrRespondentTypeId) {
		this.ivrRespondentTypeId = ivrRespondentTypeId;
	}
	@Column(name="respondent_type")
	public String getRespondentType() {
		return respondentType;
	}
	public void setRespondentType(String respondentType) {
		this.respondentType = respondentType;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
