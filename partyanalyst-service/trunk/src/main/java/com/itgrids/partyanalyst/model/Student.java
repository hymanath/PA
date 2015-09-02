package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentId;
	private String studentName;
	private Date dateOfBirth;
	private String gender;
	private String yearOfJoining;
	private InstitutionCourse institutionCourse;
	private Long joiningCourseId;
	private String casteId;
	private Long tdpCadreId;
	private Long membershipNo;
	private String guardianDetails;
	private String parentAliveStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id", unique = true, nullable = false)
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	@Column(name = "student_name")
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Column(name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "year_of_joining")
	public String getYearOfJoining() {
		return yearOfJoining;
	}
	public void setYearOfJoining(String yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "joining_course_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InstitutionCourse getInstitutionCourse() {
		return institutionCourse;
	}
	public void setInstitutionCourse(InstitutionCourse institutionCourse) {
		this.institutionCourse = institutionCourse;
	}
	
	@Column(name = "joining_course_id")
	public Long getJoiningCourseId() {
		return joiningCourseId;
	}
	public void setJoiningCourseId(Long joiningCourseId) {
		this.joiningCourseId = joiningCourseId;
	}
	
	@Column(name = "caste_id")
	public String getCasteId() {
		return casteId;
	}
	public void setCasteId(String casteId) {
		this.casteId = casteId;
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
	
	@Column(name = "guardian_details")
	public String getGuardianDetails() {
		return guardianDetails;
	}
	public void setGuardianDetails(String guardianDetails) {
		this.guardianDetails = guardianDetails;
	}
	
	@Column(name = "parent_alive_status")
	public String getParentAliveStatus() {
		return parentAliveStatus;
	}
	public void setParentAliveStatus(String parentAliveStatus) {
		this.parentAliveStatus = parentAliveStatus;
	}
}
