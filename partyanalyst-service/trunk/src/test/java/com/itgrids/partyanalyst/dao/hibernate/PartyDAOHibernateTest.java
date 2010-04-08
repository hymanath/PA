package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyDAOHibernateTest extends BaseDaoTestCase{

	private IPartyDAO partyDAO;

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	public void testGetAllParties(){
		List<Party> parties = partyDAO.findByShortNames(IConstants.STATIC_PARTIES);
		Assert.assertEquals(2, parties.size());
	}
}
