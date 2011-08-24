package com.itgrids.partyanalyst.service;
import java.util.List;

import com.itgrids.partyanalyst.dto.AnnouncementInfo;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.dto.AnnouncementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
public interface IAnnocementsService {
	public void saveAnnouncement(AnnouncementVO anos,RegistrationVO registrationVO);
    public List getAllAnnouncementsInConstituency(Long constituencyId);
    public List<AnnouncementResultsVO> getAllAnnouncementsByUserId(Long userId);
 //   public void deleteAnnouncement(long announcementId);
    public AnnouncementInfo getAnnouncementDetailsByAnnouncementId(long announcementId);
    public List getAnnouncementDetails(long announcementId);
    public void updateAnnouncementDetails(final AnnouncementResultsVO announcementResultsVO);
}
