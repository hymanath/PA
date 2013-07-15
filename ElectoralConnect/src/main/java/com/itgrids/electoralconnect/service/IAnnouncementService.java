package com.itgrids.electoralconnect.service;

import java.util.List;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;

public interface IAnnouncementService {
	
	public ResultStatus uploadFile(AnnouncementVO announcementVO,RegistrationVO user);
	
	public List<AnnouncementVO> getAllAnnouncementsForAdmin(int startRecord,int maxRecord,Long userId);
	
	public List<AnnouncementVO> getTop5Announcements();
	
	public List<AnnouncementVO> getAnnouncementById(Long announcementId);
	
	public List<AnnouncementVO> getAllAnnouncements(Long announcemetTypeId);
	
	public List<AnnouncementVO> getAnnouncementByAnnouncementFileId(Long announcemetFileId);
	
	public ResultStatus deleteSelctedAnnoncement(Long announcementid);
	
	public List<AnnouncementVO> getAllAnnouncementsBtSelDates(String startDate,String endDate,int startIndex,int maxIndex);
	
	public List<AnnouncementVO> getAllAnnouncementsForSelType(Long typeId,int startIndex,int maxIndex);
	
	public List<AnnouncementVO> getLatest50Announcements(Long announcemetTypeId);
}
