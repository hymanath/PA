package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRwsIvrTypeDAO;
import com.itgrids.partyanalyst.model.RwsIvrType;

public class RwsIvrTypeDAO extends
		GenericDaoHibernate<RwsIvrType, Long> implements
		IRwsIvrTypeDAO {
	public RwsIvrTypeDAO() {
		super(RwsIvrType.class);

	}
}
