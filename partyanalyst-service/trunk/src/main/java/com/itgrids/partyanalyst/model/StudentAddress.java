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
@Table(name = "student_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentAddress extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentAddressId;
	private Student student;
	private Long studentId;
	private AddressForStudent addressForStudent;
	private Long addressId;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_address_id", unique = true, nullable = false)
	public Long getStudentAddressId() {
		return studentAddressId;
	}
	public void setStudentAddressId(Long studentAddressId) {
		this.studentAddressId = studentAddressId;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AddressForStudent getAddressForStudent() {
		return addressForStudent;
	}
	public void setAddressForStudent(AddressForStudent addressForStudent) {
		this.addressForStudent = addressForStudent;
	}
	
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
