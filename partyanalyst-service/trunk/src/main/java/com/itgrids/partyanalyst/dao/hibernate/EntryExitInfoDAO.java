package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEntryExitInfoDAO;
import com.itgrids.partyanalyst.model.EntryExitInfo;

public class EntryExitInfoDAO  extends GenericDaoHibernate<EntryExitInfo, Long> implements IEntryExitInfoDAO {
	public EntryExitInfoDAO()
	{
		super(EntryExitInfo.class);
	}
}
