/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13, 2010
 */
package com.itgrids.partyanalyst.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * cadre_problem_details entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="cadre_problem_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreProblemDetails extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -980228419029359330L;
	
	private Long cadreProblemDetailsId;
	private Cadre cadre;
	private ProblemHistory problemHistory;
	private String status;
	private Date updatedDate;
	private String description;
	
	/** default constructor */  
	
 	public CadreProblemDetails() {
}

  /** full constructor */
  
 	public CadreProblemDetails(Cadre cadre,ProblemHistory problemHistory,String status,Date updatedDate,String description)
 	{
 		this.cadre = cadre;
 		this.problemHistory = problemHistory;
 		this.status = status;
 		this.updatedDate = updatedDate;
 		this.description = description;
 	}

 	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cadre_problem_details_id", unique=true, nullable=false)
	public Long getCadreProblemDetailsId() {
		return cadreProblemDetailsId;
	}
	
	public void setCadreProblemDetailsId(Long cadreProblemDetailsId) {
		this.cadreProblemDetailsId = cadreProblemDetailsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Cadre getCadre() {
		return cadre;
	}
	
	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_history_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemHistory getProblemHistory() {
		return problemHistory;
	}
	
	public void setProblemHistory(ProblemHistory problemHistory) {
		this.problemHistory = problemHistory;
	}
	
	@Column(name="status",length=30)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name="description",length=300)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
 	
 	
 	
}
