package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.model.Locality;

public class LocalityDAO extends GenericDaoHibernate<Locality, Long> implements ILocalityDAO{

	public LocalityDAO() {
		super(Locality.class);
		
	}

}
