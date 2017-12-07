package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_work_type")
public class PetitionWorkType {

	private Long petitionWorkTypeId;
	private String type;
	private Long  	orderNo;
	private String  isDeleted;
	
	@Id
	@Column(name="petition_work_member_ref_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionWorkTypeId() {
		return petitionWorkTypeId;
	}
	public void setPetitionWorkTypeId(Long petitionWorkTypeId) {
		this.petitionWorkTypeId = petitionWorkTypeId;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
