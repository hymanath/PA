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
@Table(name="tour_type_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TourTypeCategory {

	private Long tourTypeCategoryId;
	private String tourTypeCategory;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tour_type_category_id", unique=true, nullable=false)
	public Long getTourTypeCategoryId() {
		return tourTypeCategoryId;
	}
	public void setTourTypeCategoryId(Long tourTypeCategoryId) {
		this.tourTypeCategoryId = tourTypeCategoryId;
	}
	@Column(name = "tour_type_category")
	public String getTourTypeCategory() {
		return tourTypeCategory;
	}
	public void setTourTypeCategory(String tourTypeCategory) {
		this.tourTypeCategory = tourTypeCategory;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
