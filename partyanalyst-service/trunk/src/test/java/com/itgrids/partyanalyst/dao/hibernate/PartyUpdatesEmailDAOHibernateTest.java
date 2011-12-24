package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;

public class PartyUpdatesEmailDAOHibernateTest extends BaseDaoTestCase {
	private IPartyUpdatesEmailDAO partyUpdatesEmailDAO;

	public void setPartyUpdatesEmailDAO(
			IPartyUpdatesEmailDAO partyUpdatesEmailDAO) {
		this.partyUpdatesEmailDAO = partyUpdatesEmailDAO;
	}

	public void test() {
		partyUpdatesEmailDAO.getAll();

	}
}
