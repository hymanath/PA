package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPartyImportantDatesDAO;
import com.itgrids.partyanalyst.model.PartyImportantDates;

import common.Logger;


public class PartyImportantDatesDAOHibernateTest  extends BaseDaoTestCase {
	private IPartyImportantDatesDAO partyImportantDatesDAO;
	
	public final static Logger log = Logger.getLogger(PartyImportantDatesDAOHibernateTest.class);

	public void setPartyImportantDatesDAO(
			IPartyImportantDatesDAO partyImpartantDatesDAO) {
		this.partyImportantDatesDAO = partyImpartantDatesDAO;
	}
	
	public void testFindTodaysPartyImpDates() {
		List<PartyImportantDates> impDatesList = partyImportantDatesDAO.findTodaysPartyImportantDates(new Long(62));
		Assert.assertEquals(1, impDatesList.size());
	}
}
