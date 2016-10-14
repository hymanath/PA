package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.model.FieldVendorTabUser;

public class FieldVendorTabUserDAO extends GenericDaoHibernate<FieldVendorTabUser, Long> implements IFieldVendorTabUserDAO{

	public FieldVendorTabUserDAO() {
		super(FieldVendorTabUser.class);
		
	}

}
