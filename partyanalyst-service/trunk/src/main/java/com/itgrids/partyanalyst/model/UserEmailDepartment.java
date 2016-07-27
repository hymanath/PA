package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

@Entity
@Table(name="user_email_department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEmailDepartment extends BaseModel implements Serializable {
	private static final long serialVersionUID = 7639868344056384163L;
	
	private Long userEmailDepartmentId;
	private Long userEmailId;
	private Long departmentId;
	
	private UserEmail userEmail;
	private Department department;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_email_department_id", unique = true, nullable = false)
	public Long getUserEmailDepartmentId() {
		return userEmailDepartmentId;
	}
	public void setUserEmailDepartmentId(Long userEmailDepartmentId) {
		this.userEmailDepartmentId = userEmailDepartmentId;
	}
	@Column(name = "user_email_id")
	public Long getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(Long userEmailId) {
		this.userEmailId = userEmailId;
	}
	@Column(name = "department_id")  
	public Long getDepartmentId() {  
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_email_id",  insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserEmail getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(UserEmail userEmail) {
		this.userEmail = userEmail;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="department_id",  insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {  
		this.department = department;
	}
}
