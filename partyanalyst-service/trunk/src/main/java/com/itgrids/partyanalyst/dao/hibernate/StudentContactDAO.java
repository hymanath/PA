package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentContactDAO;
import com.itgrids.partyanalyst.model.StudentContact;

public class StudentContactDAO extends GenericDaoHibernate<StudentContact, Long> implements IStudentContactDAO{

	public StudentContactDAO() {
		super(StudentContact.class);
		
	}

}
