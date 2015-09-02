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
@Table(name = "student_course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentCourse extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long studentCourseId;
	private Student student;
	private Long studentId;
	private AcademicYear academicYear;
	private Long acadamicYearId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_course_id", unique = true, nullable = false)
	public Long getStudentCourseId() {
		return studentCourseId;
	}
	public void setStudentCourseId(Long studentCourseId) {
		this.studentCourseId = studentCourseId;
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
	@JoinColumn(name = "acadamic_year_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	
	@Column(name = "acadamic_year_id")
	public Long getAcadamicYearId() {
		return acadamicYearId;
	}
	public void setAcadamicYearId(Long acadamicYearId) {
		this.acadamicYearId = acadamicYearId;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
