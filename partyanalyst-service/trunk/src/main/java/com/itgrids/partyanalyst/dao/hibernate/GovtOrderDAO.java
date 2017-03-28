package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOrderDAO;
import com.itgrids.partyanalyst.model.GovtOrder;

public class GovtOrderDAO extends GenericDaoHibernate<GovtOrder, Long> implements IGovtOrderDAO{

	public GovtOrderDAO() {
		super(GovtOrder.class);
		// TODO Auto-generated constructor stub
	}

}
