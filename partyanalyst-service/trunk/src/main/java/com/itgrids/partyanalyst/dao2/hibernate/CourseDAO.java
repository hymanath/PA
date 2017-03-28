package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICourseDAO;
import com.itgrids.partyanalyst.model.Course;

public class CourseDAO extends GenericDaoHibernate<Course, Long> implements ICourseDAO{

	public CourseDAO() {
		super(Course.class);
		
	}

}
