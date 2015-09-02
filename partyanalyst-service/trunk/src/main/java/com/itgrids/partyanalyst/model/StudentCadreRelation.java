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
@Table(name = "student_cadre_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentCadreRelation extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentCadreRelationId;
	private Student student;
	private Long studentId;
	private Long tdpCadreId;
	private Long membershipNo;
	private String cadreName;
	private String relation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_cadre_relation_id", unique = true, nullable = false)
	public Long getStudentCadreRelationId() {
		return studentCadreRelationId;
	}
	public void setStudentCadreRelationId(Long studentCadreRelationId) {
		this.studentCadreRelationId = studentCadreRelationId;
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
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "membership_no")
	public Long getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(Long membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	@Column(name = "cadre_name")
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	
	@Column(name = "relation")
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
}
