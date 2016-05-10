package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EntryExitInfo;

public interface IEntryExitInfoDAO  extends GenericDao<EntryExitInfo, Long>{
	
	public EntryExitInfo getEntryDetails(Long parentEventId);

}
