package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSmsTypeDAO;
import com.itgrids.partyanalyst.model.GovtSmsType;


public class GovtSmsTypeDAO extends GenericDaoHibernate<GovtSmsType, Long> implements
IGovtSmsTypeDAO {

	public GovtSmsTypeDAO() {
		super(GovtSmsType.class);
	}

}
