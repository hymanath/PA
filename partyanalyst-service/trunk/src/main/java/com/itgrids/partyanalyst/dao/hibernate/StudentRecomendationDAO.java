package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentRecomendationDAO;
import com.itgrids.partyanalyst.model.StudentRecomendation;

public class StudentRecomendationDAO extends GenericDaoHibernate<StudentRecomendation, Long> implements IStudentRecomendationDAO{

	public StudentRecomendationDAO() {
		super(StudentRecomendation.class);
		
	}

}
