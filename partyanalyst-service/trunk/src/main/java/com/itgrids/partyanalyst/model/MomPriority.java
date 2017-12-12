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
@Table(name = "mom_priority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MomPriority {

	private Long momPriorityId;
	private String priority;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mom_priority_id", unique=true, nullable=false)
	public Long getMomPriorityId() {
		return momPriorityId;
	}
	public void setMomPriorityId(Long momPriorityId) {
		this.momPriorityId = momPriorityId;
	}
	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
