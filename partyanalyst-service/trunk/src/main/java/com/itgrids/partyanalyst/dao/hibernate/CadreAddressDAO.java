package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreAddressDAO;
import com.itgrids.partyanalyst.model.CadreAddress;

public class CadreAddressDAO extends GenericDaoHibernate<CadreAddress, Long>  implements ICadreAddressDAO {
	public CadreAddressDAO( ) {
		super(CadreAddress.class);
		
	}
}
