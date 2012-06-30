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
 * problem_assigned_cadre entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_assigned_cadre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemAssignedCadre extends BaseModel implements Serializable{

	private static final long serialVersionUID = 8050243463187485268L;
	
	private Long problemAssignedCadreId;
	private UserProblem userProblem;
	private Cadre cadre;
	private String status;
	private Date insertedTime;
	private Date updatedTime;
	
	public ProblemAssignedCadre()
	{}
	
	public ProblemAssignedCadre(UserProblem userProblem,Cadre cadre,String status,
			Date insertedTime,Date updatedTime)
	{
		this.userProblem = userProblem;
		this.cadre = cadre;
		this.status = status;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_assigned_cadre_id", unique=true, nullable=false)
	public Long getProblemAssignedCadreId() {
		return problemAssignedCadreId;
	}

	public void setProblemAssignedCadreId(Long problemAssignedCadreId) {
		this.problemAssignedCadreId = problemAssignedCadreId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserProblem getUserProblem() {
		return userProblem;
	}

	public void setUserProblem(UserProblem userProblem) {
		this.userProblem = userProblem;
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

	@Column(name = "status", length = 10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
