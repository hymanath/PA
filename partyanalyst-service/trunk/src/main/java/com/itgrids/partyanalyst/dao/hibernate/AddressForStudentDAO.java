package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAddressForStudentDAO;
import com.itgrids.partyanalyst.model.AddressForStudent;

public class AddressForStudentDAO extends GenericDaoHibernate<AddressForStudent, Long> implements IAddressForStudentDAO{

	public AddressForStudentDAO() {
		super(AddressForStudent.class);
		
	}

}
