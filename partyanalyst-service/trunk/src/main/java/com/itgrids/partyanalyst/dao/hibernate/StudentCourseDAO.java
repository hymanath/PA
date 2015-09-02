package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentCourseDAO;
import com.itgrids.partyanalyst.model.StudentCourse;

public class StudentCourseDAO extends GenericDaoHibernate<StudentCourse, Long> implements IStudentCourseDAO{

	public StudentCourseDAO() {
		super(StudentCourse.class);
		
	}

}
