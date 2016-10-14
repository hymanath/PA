package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.model.FieldVendorLocation;

public class FieldVendorLocationDAO extends GenericDaoHibernate<FieldVendorLocation, Long> implements IFieldVendorLocationDAO{

	public FieldVendorLocationDAO() {
		super(FieldVendorLocation.class);
	}

}
