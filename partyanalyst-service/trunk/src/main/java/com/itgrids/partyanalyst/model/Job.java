package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Job implements Serializable{
	

private Long jobId;

private String jobUniqueId;

private String description;

private Set<JobRunDetails> jobRunDetails = new HashSet<JobRunDetails>(0);


//Default Constructor
public Job()
{
	
}

//Full Constructor
public Job(Long jobId,String jobUniqueId,String description)
{
	this.jobId = jobId;
	this.jobUniqueId = jobUniqueId;
	this.description = description;
}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "job_id", unique = true, nullable = false)
public Long getJobId() {
	return jobId;
}

public void setJobId(Long jobId) {
	this.jobId = jobId;
}
@Column(name="job_unique_id",length =25 )
public String getJobUniqueId() {
	return jobUniqueId;
}

public void setJobUniqueId(String jobUniqueId) {
	this.jobUniqueId = jobUniqueId;
}
@Column(name="description",length = 300)
public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Set<JobRunDetails> getJobRunDetails() {
	return jobRunDetails;
}

public void setJobRunDetails(Set<JobRunDetails> jobRunDetails) {
	this.jobRunDetails = jobRunDetails;
}


}
