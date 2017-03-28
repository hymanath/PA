package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEntryExitInfoDAO;
import com.itgrids.partyanalyst.model.EntryExitInfo;

public class EntryExitInfoDAO  extends GenericDaoHibernate<EntryExitInfo, Long> implements IEntryExitInfoDAO {
	public EntryExitInfoDAO()
	{
		super(EntryExitInfo.class);
	}
	
	public EntryExitInfo getEntryDetails(Long parentEventId){
		
		Query query = getSession().createQuery("select model from EntryExitInfo model " +
				" where model.parentEventId = :parentEventId ");
		
		query.setParameter("parentEventId", parentEventId);
		return (EntryExitInfo) query.uniqueResult();
	}
	
}
