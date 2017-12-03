package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="petition_subject")
public class PetitionSubject {

	private Long petitionSubjectId ;
	private String subject;
	private Long parentPetitionSubjectId;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@Column(name="petition_subject_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionSubjectId() {
		return petitionSubjectId;
	}
	public void setPetitionSubjectId(Long petitionSubjectId) {
		this.petitionSubjectId = petitionSubjectId;
	}	
	
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name="parent_petition_subject_id")
	public Long getParentPetitionSubjectId() {
		return parentPetitionSubjectId;
	}
	public void setParentPetitionSubjectId(Long parentPetitionSubjectId) {
		this.parentPetitionSubjectId = parentPetitionSubjectId;
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
