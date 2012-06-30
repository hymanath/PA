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
 * problem_files entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemFiles extends BaseModel implements Serializable{

	private static final long serialVersionUID = -769015229115549942L;
	
	private Long problemFilesId;
	private Problem problem;
	private File file;
	private User user;
	private Date insertedTime;
	private Date updatedTime;
	private String isApproved;
	private String isDelete;
	
	public ProblemFiles()
	{}
	
	public ProblemFiles(Problem problem,File file,User user,Date insertedTime,
			Date updatedTime,String isApproved,String isDelete)
	{
		this.problem = problem;
		this.file = file;
		this.user = user;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
		this.isApproved = isApproved;
		this.isDelete = isDelete;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_files_id", unique=true, nullable=false)
	public Long getProblemFilesId() {
		return problemFilesId;
	}
	
	public void setProblemFilesId(Long problemFilesId) {
		this.problemFilesId = problemFilesId;
	}
	

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}
	
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	@Column(name = "is_approved", length = 10)
	public String getIsApproved() {
		return isApproved;
	}
	
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
