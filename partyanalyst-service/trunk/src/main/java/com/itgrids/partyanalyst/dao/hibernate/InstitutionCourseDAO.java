package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInstitutionCourseDAO;
import com.itgrids.partyanalyst.model.InstitutionCourse;

public class InstitutionCourseDAO extends GenericDaoHibernate<InstitutionCourse, Long> implements IInstitutionCourseDAO{

	public InstitutionCourseDAO() {
		super(InstitutionCourse.class);
		
	}

}
