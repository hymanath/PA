package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITabUserKeysDAO;
import com.itgrids.partyanalyst.model.TabUserKeys;

public class TabUserKeysDAO  extends GenericDaoHibernate<TabUserKeys, Long> implements ITabUserKeysDAO {

	public TabUserKeysDAO() {
		super(TabUserKeys.class);
		
	}

}
