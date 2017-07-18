package com.itgrids.partyanalyst.service;
import java.util.Date;
import java.util.List;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
public interface IAnnouncementService {
	
	public AnnouncementVO saveAnnouncement(AnnouncementVO announcementVO);
	
    public List getAllAnnouncementsInConstituency(Long constituencyId);
    
    public List<AnnouncementVO> getAllAnnouncementsByUserId(Long userId);
    
    public AnnouncementVO getAnnouncementDetailsByAnnouncementId(long announcementId);
    
    public List<AnnouncementVO> searchAnnouncementDetailsByUserIdDateConstId(AnnouncementVO annVO);
    
    public List<AnnouncementVO> getAllUserAnnouncementDetails(Long userId,Date today);
    
    public  List<SelectOptionVO> getUserBasedAccessConstituencies(Long userId);
    public List<SelectOptionVO> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId, String cadreType);
} 
