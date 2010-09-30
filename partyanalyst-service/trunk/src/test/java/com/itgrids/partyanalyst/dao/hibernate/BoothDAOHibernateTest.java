package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;

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

	/*public void testFindbyConstituencyIdPartnoAndElectionYear() {
		List<Booth> booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear("kavali", 19l, 2009l, "40");
	}*/
	
	public void testFindBoothInfoByConstituencyIdAndYear(){
		List list = boothDAO.findBoothInfoByConstituencyIdAndYear(232l, 2009l);
		System.out.println(list.size());
	}
	
	/*public void testUpdateLocalBodyInfoByBoothIdsAndWardId(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		int i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(7700l, boothIds);
		setComplete();
		System.out.println(i);
	}
	
	public void testGetBoothsByIds(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		List<Booth> booths = boothDAO.findByBoothIds(boothIds);
		System.out.println(booths.size());
	}*/
}
