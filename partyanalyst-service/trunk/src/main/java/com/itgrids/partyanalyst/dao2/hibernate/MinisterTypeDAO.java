package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMinisterTypeDAO;
import com.itgrids.partyanalyst.model.MinisterType;

public class MinisterTypeDAO extends GenericDaoHibernate<MinisterType, Long> implements IMinisterTypeDAO{

	public MinisterTypeDAO(){
		super(MinisterType.class);
	}
}
