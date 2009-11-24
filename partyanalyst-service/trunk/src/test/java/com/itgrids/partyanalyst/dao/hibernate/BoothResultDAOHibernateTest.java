package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.model.BoothResult;

public class BoothResultDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothResultDAO boothResultDAO;

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}
	
	public void testFindByBoothConstituencyElection(){
		List<BoothResult> list = boothResultDAO.findByBoothConstituencyElection(new Long(200));
		assertEquals(1, list.size());
	}
	
	public void testFindByConstituencyAndElection(){
		List<BoothResult> list = boothResultDAO.findByConstituencyAndElection("allur", "2004", new Long(2));
		assertEquals(1, list.size());
	}

}
