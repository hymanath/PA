package com.itgrids.electoralconnect.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.AnnouncementFiles;


public interface IAnnouncementFilesDAO  extends GenericDao<AnnouncementFiles, Long> {

	
	public List<Object[]> getAnnoncementById(Long announcementId);
	
	public List<AnnouncementFiles> getAllAnnouncements(int startRecord,int maxRecord);
	
	public int getAllAnnouncementsCountOfUser();
	
	public List<Object[]> getAllAnnoncement(Long announcementTypeId,int startRecord,int maxRecord);
	
	public List<Object[]> getAnnouncementsByAnnouncementFileId(Long announcementFileId);
	
	public List<AnnouncementFiles> getAnnouncemetsBtSelDates(Date startDate,Date endDate,int startIndex,int maxIndex);
	
	public int getSelBtDatesAnnouncementsCountOfUser(Date startDate,Date endDate);
	
	public List<AnnouncementFiles> getAllAnnouncemetsForSelectedType(Long typeId,int startIndex,int maxIndex);
	
	public Long getCountForSelAnnouncemetType(Long typeId);
	
	public List<Object[]> getLatest50Annoncements(Long announcementTypeId);
	
	public int getAllAnnoncementCountForPaging(Long announcementTypeId);
}
