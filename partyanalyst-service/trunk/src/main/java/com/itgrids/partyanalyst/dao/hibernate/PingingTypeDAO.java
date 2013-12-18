package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.model.PingingType;

public class PingingTypeDAO extends GenericDaoHibernate<PingingType, Long> implements IPingingTypeDAO{

	public PingingTypeDAO() {
		super(PingingType.class);
		
	}

}
