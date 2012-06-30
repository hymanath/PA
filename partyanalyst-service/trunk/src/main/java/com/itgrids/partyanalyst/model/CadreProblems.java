package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
/**
 * cadre_problems entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="cadre_problems")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreProblems extends BaseModel implements Serializable{

	private static final long serialVersionUID = 3412990512032360366L;
	
	private Long cadreProblemsId;
	private Problem problem;
	private Cadre cadre;
	private Date insertedTime;
	private Date updatedTime;
	
	public CadreProblems()
	{}
	
	public CadreProblems(Problem problem,Cadre cadre,Date insertedTime,Date updatedTime)
	{
		this.problem = problem;
		this.cadre = cadre;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cadre_problems_id", unique=true, nullable=false)
	public Long getCadreProblemsId() {
		return cadreProblemsId;
	}

	public void setCadreProblemsId(Long cadreProblemsId) {
		this.cadreProblemsId = cadreProblemsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
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

	@Column(name = "inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time", length = 10)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
