package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFieldVendorDAO;
import com.itgrids.partyanalyst.model.FieldVendor;

public class FieldVendorDAO extends GenericDaoHibernate<FieldVendor, Long> implements IFieldVendorDAO{

	public FieldVendorDAO() {
		super(FieldVendor.class);
		
	}

}
