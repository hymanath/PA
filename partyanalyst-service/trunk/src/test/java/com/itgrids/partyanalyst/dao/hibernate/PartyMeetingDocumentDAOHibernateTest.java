package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;

public class PartyMeetingDocumentDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;

	public void setPartyMeetingDocumentDAO(
			IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}
	
	public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(32l);
		List<Object[]> list = partyMeetingDocumentDAO.getPartyMeetingDocsOfMeetingIds(partyMeetingIds);
		System.out.println(list.size());
	}
}
