package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.electoralconnect.model.Announcements;


public interface IAnnouncementsDAO  extends GenericDao<Announcements, Long> {
	
	public List<Announcements> getTopAnnouncements(Long announcementType,int startIndex,int maxIndex);
}
