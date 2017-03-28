package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDesignationDAO;
import com.itgrids.partyanalyst.model.Designation;

public class DesignationDAO extends GenericDaoHibernate<Designation, Long> implements IDesignationDAO{

	public DesignationDAO() {
		super(Designation.class);
		
	}

}
