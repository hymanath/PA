package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserConstituencyScope;

public interface IUserConstituencyScopeDAO extends GenericDao<UserConstituencyScope, Long>{
	
	public List<Object[]> findAnnouncementsByConstituencyId(Long constituencyId,Date date);
	
	public List<Object[]> getConstituencyId(long announcementId);
	
	public List<UserConstituencyScope> getUserConstituencyScopeByAnnouncementId(Long announcementId);

}
