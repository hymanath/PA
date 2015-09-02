package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentParentDetailsDAO;
import com.itgrids.partyanalyst.model.StudentParentDetails;

public class StudentParentDetailsDAO extends GenericDaoHibernate<StudentParentDetails, Long> implements IStudentParentDetailsDAO{

	public StudentParentDetailsDAO() {
		super(StudentParentDetails.class);
		
	}

}
