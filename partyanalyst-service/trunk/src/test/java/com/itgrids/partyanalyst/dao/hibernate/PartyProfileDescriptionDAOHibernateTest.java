package com.itgrids.partyanalyst.dao.hibernate;
import java.util.*;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.model.*;

public class PartyProfileDescriptionDAOHibernateTest extends BaseDaoTestCase {
	private IPartyProfileDescriptionDAO partyProfileDescriptionDAO;

	public void setPartyProfileDescriptionDAO(
			IPartyProfileDescriptionDAO partyProfileDescriptionDAO) {
		this.partyProfileDescriptionDAO = partyProfileDescriptionDAO;
	}

	/*public void test() {
		partyProfileDescriptionDAO.getAll();
	}*/
	public void testGetPartyProfileDescription()
	{
		
   List<Object> result = partyProfileDescriptionDAO.getPartyProfileDescription(163l);
   for (Object object : result) {
	System.out.println(object.toString());
}
	}
}
