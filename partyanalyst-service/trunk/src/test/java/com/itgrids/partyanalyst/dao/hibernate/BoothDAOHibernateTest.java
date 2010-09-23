package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.model.Booth;

public class BoothDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothDAO boothDAO;

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	/*public void testFindTehsilsByConstituencyElectionScopeAndElection(){
		List<Tehsil> mandals = boothDAO.findTehsilsByElectionAndConstituency("2009", new Long(232));
		assertEquals(mandals.size(), 7);
	}*/
	
	/*public void testFindByConstituencyAndElectionYear(){
		List list = boothDAO.findByConstituencyAndElectionYear(231l, 2009l);
		System.out.println((Long)list.get(0));
	}*/
	
	/*public void testFindByPartNoConstituencyIdAndYear(){
		Long count = (Long)boothDAO.findByPartNoConstituencyIdAndYear(231l, 2009l, "30").get(0);
		System.out.println(count);
	}*/

	public void testFindbyConstituencyIdPartnoAndElectionYear() {
		List<Booth> booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear("kavali", 19l, 2009l, "40");
	}
}
