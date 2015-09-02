package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "student_parent_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentParentDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentParentDetailsId;
	private Student student;
	private Long studentId;
	private String parentName;
	private String relation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_parent_details_id", unique = true, nullable = false)
	public Long getStudentParentDetailsId() {
		return studentParentDetailsId;
	}
	public void setStudentParentDetailsId(Long studentParentDetailsId) {
		this.studentParentDetailsId = studentParentDetailsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Column(name = "student_id")
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	@Column(name = "parent_name")
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	@Column(name = "relation")
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
}
