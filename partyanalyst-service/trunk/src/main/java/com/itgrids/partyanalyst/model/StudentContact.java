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
@Table(name = "student_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentContact extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentContactId;
	private Student student;
	private Long studentId;
	private String phoneNo;
	private String phoneType;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_contact_id", unique = true, nullable = false)
	public Long getStudentContactId() {
		return studentContactId;
	}
	public void setStudentContactId(Long studentContactId) {
		this.studentContactId = studentContactId;
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
	
	@Column(name = "phone_no")
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name = "phone_type")
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
