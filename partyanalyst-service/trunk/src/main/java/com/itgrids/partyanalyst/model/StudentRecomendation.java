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
@Table(name = "student_recomendation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentRecomendation extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentRecomendationId;
	private Student student;
	private Long studentId;
	private String personName;
	private ReferalDesignation referalDesignation;
	private Long referalDesignationId;
	private Long contactNo;
	private Long tdpCadreId;
	private Long membershipNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_recomendation_id", unique = true, nullable = false)
	public Long getStudentRecomendationId() {
		return studentRecomendationId;
	}
	public void setStudentRecomendationId(Long studentRecomendationId) {
		this.studentRecomendationId = studentRecomendationId;
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
	
	@Column(name = "person_name")
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referal_designation_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ReferalDesignation getReferalDesignation() {
		return referalDesignation;
	}
	public void setReferalDesignation(ReferalDesignation referalDesignation) {
		this.referalDesignation = referalDesignation;
	}
	
	@Column(name = "refferal_designation_id")
	public Long getReferalDesignationId() {
		return referalDesignationId;
	}
	public void setReferalDesignationId(Long referalDesignationId) {
		this.referalDesignationId = referalDesignationId;
	}
	
	@Column(name = "contact_no")
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "membership_nol")
	public Long getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(Long membershipNo) {
		this.membershipNo = membershipNo;
	}
}
