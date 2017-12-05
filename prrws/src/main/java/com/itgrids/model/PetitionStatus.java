package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_status")
public class PetitionStatus {

	private Long petitionStatusId;
	private String description;
	private Long orderNo;
	private String isDeleted ;
	@Id
	@Column(name="petition_status_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionStatusId() {
		return petitionStatusId;
	}
	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
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
