package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="job_run_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class JobRunDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8017716386817086943L;

	private Long jobRunDetailsId;
	
	private Job job;
	
	private Date startTime;
	
	private Date endTime;
	
	private String status;
	
	//Default Constructor
	
	public JobRunDetails()
	{
		
	}
	
	//Full Constructor
	public JobRunDetails(Long jobRunDetailsId,Job job,Date startTime,Date endTime,String status)
	{
		
		this.jobRunDetailsId = jobRunDetailsId;
		this.job = job;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_run_details_id", unique = true, nullable = false)
	public Long getJobRunDetailsId() {
		return jobRunDetailsId;
	}

	public void setJobRunDetailsId(Long jobRunDetailsId) {
		this.jobRunDetailsId = jobRunDetailsId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	@Column(name = "start_time", length = 10)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "end_time", length = 10)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="status", length = 45)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
