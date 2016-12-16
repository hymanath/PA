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
@Table(name="self_appraisal_tour_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalTourCategory {
	 /*
	  * Author:santosh
	 */
	private Long selfAppraisalTourCategoryId;
	private String tourCategory;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_tour_category_id", unique=true, nullable=false)
	public Long getSelfAppraisalTourCategoryId() {
		return selfAppraisalTourCategoryId;
	}
	public void setSelfAppraisalTourCategoryId(Long selfAppraisalTourCategoryId) {
		this.selfAppraisalTourCategoryId = selfAppraisalTourCategoryId;
	}
	@Column(name="tour_category")
	public String getTourCategory() {
		return tourCategory;
	}
	public void setTourCategory(String tourCategory) {
		this.tourCategory = tourCategory;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	
}
