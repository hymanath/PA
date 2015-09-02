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
@Table(name = "institution_course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstitutionCourse extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long institutionCourseId;
	private Institution institution;
	private Long institutionId;
	private Course course;
	private Long courseId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "institution_course_id", unique = true, nullable = false)
	public Long getInstitutionCourseId() {
		return institutionCourseId;
	}
	public void setInstitutionCourseId(Long institutionCourseId) {
		this.institutionCourseId = institutionCourseId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	@Column(name = "institution_id")
	public Long getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Column(name = "course_id")
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
