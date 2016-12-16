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
@Table(name="tour_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TourType {

	private Long tourTypeId;
	private String tourType;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tour_type_id", unique=true, nullable=false)
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	@Column(name="tour_type")
	public String getTourType() {
		return tourType;
	}
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
