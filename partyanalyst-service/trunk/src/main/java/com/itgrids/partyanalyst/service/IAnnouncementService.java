package com.itgrids.partyanalyst.service;
import java.util.List;

import com.itgrids.partyanalyst.dto.AnnouncementVO;
public interface IAnnouncementService {
	
	public AnnouncementVO saveAnnouncement(AnnouncementVO announcementVO);
	
    public List getAllAnnouncementsInConstituency(Long constituencyId);
    
    public List<AnnouncementVO> getAllAnnouncementsByUserId(Long userId);
    
    public AnnouncementVO getAnnouncementDetailsByAnnouncementId(long announcementId);
}
