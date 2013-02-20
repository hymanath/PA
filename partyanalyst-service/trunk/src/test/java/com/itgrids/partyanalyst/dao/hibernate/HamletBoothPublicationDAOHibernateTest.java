package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.model.HamletBoothPublication;

public class HamletBoothPublicationDAOHibernateTest extends BaseDaoTestCase{
	
	private IHamletBoothPublicationDAO hamletBoothPublicationDAO;
	
	
	public void setHamletBoothPublicationDAO(
			IHamletBoothPublicationDAO hamletBoothPublicationDAO) {
		this.hamletBoothPublicationDAO = hamletBoothPublicationDAO;
	}


	public void testgetHameletDetailsByBoothId()
	{
		List<HamletBoothPublication> list = hamletBoothPublicationDAO.getHameletDetailsByBoothId(121884l);
		for (HamletBoothPublication hamletBoothPublication : list) {
			System.out.println(hamletBoothPublication.getBooth().getLocalBody());
		}
	}

}
