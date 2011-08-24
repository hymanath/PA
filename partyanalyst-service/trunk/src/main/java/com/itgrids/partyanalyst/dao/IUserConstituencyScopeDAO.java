package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserConstituencyScope;

public interface IUserConstituencyScopeDAO extends GenericDao<UserConstituencyScope, Long>{
	
	public List findAnnouncementsByConstituencyId(Long constituencyId,Date date);
	public List getConstituencyId(long announcementId);

}
