package com.itgrids.partyanalyst.service;
import java.util.List;

import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
public interface IAnnouncementService {
	
	public AnnouncementVO saveAnnouncement(AnnouncementVO announcementVO);
	
    public List getAllAnnouncementsInConstituency(Long constituencyId);
    
    public List<AnnouncementVO> getAllAnnouncementsByUserId(Long userId);
    
    public void updateAnnouncementDetails(final AnnouncementResultsVO announcementResultsVO);
    
    public AnnouncementVO getAnnouncementDetailsByAnnouncementId(long announcementId);
}
