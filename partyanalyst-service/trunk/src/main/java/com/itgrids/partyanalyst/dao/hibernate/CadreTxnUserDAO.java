package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.model.CadreTxnUser;

public class CadreTxnUserDAO extends GenericDaoHibernate<CadreTxnUser, Long> implements ICadreTxnUserDAO{

	public CadreTxnUserDAO() {
		super(CadreTxnUser.class);
	}
	


}
