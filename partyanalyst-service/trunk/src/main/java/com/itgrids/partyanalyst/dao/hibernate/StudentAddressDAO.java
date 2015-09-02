package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStudentAddressDAO;
import com.itgrids.partyanalyst.model.StudentAddress;

public class StudentAddressDAO extends GenericDaoHibernate<StudentAddress, Long> implements IStudentAddressDAO{

	public StudentAddressDAO() {
		super(StudentAddress.class);
		
	}

}
