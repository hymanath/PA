package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.AnnouncementFiles;


public interface IAnnouncementFilesDAO  extends GenericDao<AnnouncementFiles, Long> {

	
	public List<Object[]> getAnnoncementById(Long announcementId);
	
	public List<AnnouncementFiles> getAllAnnouncements(int startRecord,int maxRecord,Long userId);
	
	public int getAllAnnouncementsCountOfUser(Long userId);
	
	public List<Object[]> getAllAnnoncement(Long announcementTypeId);
}
