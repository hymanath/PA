package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

public class NominatedPostGovtOrderDAO extends GenericDaoHibernate<NominatedPostGovtOrder, Long> implements INominatedPostGovtOrderDAO{

	public NominatedPostGovtOrderDAO() {
		super(NominatedPostGovtOrder.class);
		// TODO Auto-generated constructor stub
	}

}
