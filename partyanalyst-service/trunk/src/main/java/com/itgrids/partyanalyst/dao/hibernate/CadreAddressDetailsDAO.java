package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreAddressDetailsDAO;
import com.itgrids.partyanalyst.model.CadreAddressDetails;

public class CadreAddressDetailsDAO extends GenericDaoHibernate<CadreAddressDetails, Long> implements ICadreAddressDetailsDAO{

	public CadreAddressDetailsDAO() {
		super(CadreAddressDetails.class);
		
	}
	
}
