package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAreaTypeDAO;
import com.itgrids.partyanalyst.model.AreaType;

public class AreaTypeDAO extends GenericDaoHibernate<AreaType,Long> implements IAreaTypeDAO{
	
	public AreaTypeDAO() {
		super(AreaType.class);
	}

}
