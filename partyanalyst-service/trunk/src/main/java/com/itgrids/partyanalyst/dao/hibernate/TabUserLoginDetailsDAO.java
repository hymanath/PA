package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITabUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.TabUserLoginDetails;

public class TabUserLoginDetailsDAO extends GenericDaoHibernate<TabUserLoginDetails, Long>implements ITabUserLoginDetailsDAO{

	public TabUserLoginDetailsDAO() {
		super(TabUserLoginDetails.class);
	}

}
