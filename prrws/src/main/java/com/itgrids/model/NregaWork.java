package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nrega_work")
public class NregaWork {

	private Long nregaWorkId;
	private Long nregaWorkTypeId;
	private Long noOfWorks;
	private Date completedDate;
	private String status;
	private String isDeleted;
	
	private NregaWorkType nregaWorkType;
	
	@Id
	@Column(name="nrega_work_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaWorkId() {
		return nregaWorkId;
	}
	public void setNregaWorkId(Long nregaWorkId) {
		this.nregaWorkId = nregaWorkId;
	}
	
	@Column(name="nrega_work_type_id")
	public Long getNregaWorkTypeId() {
		return nregaWorkTypeId;
	}
	public void setNregaWorkTypeId(Long nregaWorkTypeId) {
		this.nregaWorkTypeId = nregaWorkTypeId;
	}
	
	@Column(name="no_of_works")
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	
	@Column(name="completed_date")
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_work_type_id", insertable = false, updatable = false)
	public NregaWorkType getNregaWorkType() {
		return nregaWorkType;
	}
	public void setNregaWorkType(NregaWorkType nregaWorkType) {
		this.nregaWorkType = nregaWorkType;
	}
}
