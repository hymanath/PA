package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.Announcement;

public interface IAnnouncementDAO extends GenericDao<Announcement, Long>{

	public List findAnnouncementsByConstituencyId(Long constituencyId);
}
