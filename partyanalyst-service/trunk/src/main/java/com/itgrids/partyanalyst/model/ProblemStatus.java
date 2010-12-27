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

@Entity
@Table(name = "problem_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemStatus extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long problemStatusId;
	private String status;
	private Set<ProblemHistory> problemHistories = new HashSet<ProblemHistory>(0);

	public ProblemStatus(){
		
	}
	
	public ProblemStatus(Long problemStatusId){
		this.problemStatusId = problemStatusId;
	}

	public ProblemStatus(String status,
			Set<ProblemHistory> problemHistories) {
		this.status = status;
		this.problemHistories = problemHistories;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_status_id", unique = true, nullable = false)
	public Long getProblemStatusId() {
		return problemStatusId;
	}

	public void setProblemStatusId(Long problemStatusId) {
		this.problemStatusId = problemStatusId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemStatus")
	public Set<ProblemHistory> getProblemHistories() {
		return problemHistories;
	}

	public void setProblemHistories(Set<ProblemHistory> problemHistories) {
		this.problemHistories = problemHistories;
	}

	@Column(name = "status", length = 25)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
