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
@Table(name="self_appraisal_tours_month")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalToursMonth {

	private Long selfAppraisalToursMonthId;
	private String toursMonth;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_tours_month_id", unique=true, nullable=false)
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	@Column(name="tours_month")
	public String getToursMonth() {
		return toursMonth;
	}
	public void setToursMonth(String toursMonth) {
		this.toursMonth = toursMonth;
	}
	
	
}
