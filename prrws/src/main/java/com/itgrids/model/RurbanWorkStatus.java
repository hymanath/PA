package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rurban_work_status")
public class RurbanWorkStatus {
	private Long rurbanWorkStatusId;
	private String statusName;
	@Id
	@Column(name="rurban_work_status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getRurbanWorkStatusId() {
		return rurbanWorkStatusId;
	}
	public void setRurbanWorkStatusId(Long rurbanWorkStatusId) {
		this.rurbanWorkStatusId = rurbanWorkStatusId;
	}
	@Column(name="status_name")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
