package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAnnouncement;

public interface IUserAnnouncementDAO extends GenericDao<UserAnnouncement, Long>{
	
	public List<Object[]> findAnnouncementDetailsByUserId(long userId);
	
	public List<UserAnnouncement> getUserAnnouncementByAnnouncementId(Long announcementId);

}
