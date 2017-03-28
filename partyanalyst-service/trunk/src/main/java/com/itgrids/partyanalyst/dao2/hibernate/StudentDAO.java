package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentDAO;
import com.itgrids.partyanalyst.model.Student;

public class StudentDAO extends GenericDaoHibernate<Student, Long> implements IStudentDAO{

	public StudentDAO() {
		super(Student.class);
		
	}

}
