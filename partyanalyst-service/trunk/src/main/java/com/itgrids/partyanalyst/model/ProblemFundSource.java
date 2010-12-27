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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "problem_fund_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemFundSource extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long problemFundSourceId;
	private String status;
	private String description;
	private Date assignedDate;
	private ProblemHistory problemHistory;
	private FundSource fundSource;
	
	public ProblemFundSource(){
		
	}
	
	public ProblemFundSource(Long problemFundSourceId){
		this.problemFundSourceId = problemFundSourceId;
	}

	public ProblemFundSource(String status,
			String description, Date assignedDate,
			ProblemHistory problemHistory, FundSource fundSource) {
		this.status = status;
		this.description = description;
		this.assignedDate = assignedDate;
		this.problemHistory = problemHistory;
		this.fundSource = fundSource;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_fund_source_id", unique = true, nullable = false)
	public Long getProblemFundSourceId() {
		return problemFundSourceId;
	}

	public void setProblemFundSourceId(Long problemFundSourceId) {
		this.problemFundSourceId = problemFundSourceId;
	}

	@Column(name = "status", length = 25)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "description", length = 25)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "date_assigned", length = 10)
	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_history_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemHistory getProblemHistory() {
		return problemHistory;
	}

	public void setProblemHistory(ProblemHistory problemHistory) {
		this.problemHistory = problemHistory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FundSource getFundSource() {
		return fundSource;
	}

	public void setFundSource(FundSource fundSource) {
		this.fundSource = fundSource;
	}	
	
}
