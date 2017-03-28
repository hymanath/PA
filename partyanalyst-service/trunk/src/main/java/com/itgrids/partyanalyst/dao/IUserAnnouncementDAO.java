package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserAnnouncement;

public interface IUserAnnouncementDAO extends GenericDao<UserAnnouncement, Long>{
	
	public List<Object[]> findAnnouncementDetailsByUserId(Long userId);
	
	public List<UserAnnouncement> getUserAnnouncementByAnnouncementId(Long announcementId);

	public List<Object[]> getUserAnnouncementDetailsRecent(Long userId,Date today);
}
