package com.itgrids.partyanalyst.service;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IAnnouncementsDao;
import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.service.impl.AnnouncementService;

public class AnnouncementServiceServiceTest {
	private IAnnouncementsDao announcementsDao;
	private IUserAnnouncementDAO userAnnouncementDAO;
	private IUserConstituencyScopeDAO userConstituencyScopeDAO;

@Test
public void updateAnnouncementDetails(){
	AnnouncementService s = new AnnouncementService();
	System.out.println(s);
	AnnouncementResultsVO announcementResultsVO = new AnnouncementResultsVO();
	announcementResultsVO.setAnnouncementsId(64L);
	announcementResultsVO.setConstituencyId(461L);
	System.out.println(announcementResultsVO);
	s.updateAnnouncementDetails(announcementResultsVO);
	System.out.println("end");
}

}
