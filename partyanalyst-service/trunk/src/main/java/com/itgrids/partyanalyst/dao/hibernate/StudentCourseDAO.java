package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentCourseDAO;
import com.itgrids.partyanalyst.model.StudentCourse;

public class StudentCourseDAO extends GenericDaoHibernate<StudentCourse, Long> implements IStudentCourseDAO{

	public StudentCourseDAO() {
		super(StudentCourse.class);
	}
	public List<Object[]> getAcademicYearDetailsOfStudent(Long studentId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select model.academicYear.acadamicYearId,model.academicYear.startYear,model.academicYear.startMonth,model.academicYear.endYear," +
				" model.academicYear.endMonth,model.status from StudentCourse model " +
				" where model.student.studentId = :studentId");
		
		Query query = getSession().createQuery(str.toString());	
		query.setParameter("studentId", studentId);
		
		return query.list();
	}

}
