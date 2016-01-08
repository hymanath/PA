package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITabDetailsDAO;
import com.itgrids.partyanalyst.model.TabDetails;

public class TabDetailsDAO extends GenericDaoHibernate<TabDetails, Long> implements ITabDetailsDAO{

	public TabDetailsDAO() {
		super(TabDetails.class);
		
	}

}
