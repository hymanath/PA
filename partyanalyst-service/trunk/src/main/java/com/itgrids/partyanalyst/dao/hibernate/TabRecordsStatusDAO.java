package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITabRecordsStatusDAO;
import com.itgrids.partyanalyst.model.TabRecordsStatus;

public class TabRecordsStatusDAO extends GenericDaoHibernate<TabRecordsStatus, Long>implements ITabRecordsStatusDAO{

	public TabRecordsStatusDAO() {
		super(TabRecordsStatus.class);
	}

	
}
