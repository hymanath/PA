package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITabUserInfoDAO;
import com.itgrids.partyanalyst.model.TabUserInfo;

public class TabUserInfoDAO extends GenericDaoHibernate<TabUserInfo, Long> implements ITabUserInfoDAO{

	public TabUserInfoDAO() {
		super(TabUserInfo.class);      
	}
}