package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletBoothDAO;

public class HamletBoothDAOHibernateTest extends BaseDaoTestCase{
	private IHamletBoothDAO hamletBoothDAO;

	public void setHamletBoothDAO(IHamletBoothDAO hamletBoothDAO) {
		this.hamletBoothDAO = hamletBoothDAO;
	}
	
	/*public void test()
	{
		hamletBoothDAO.getAll();
	}*/
	
	/*public void testdeleteHamletBoothsByBoothIdsList()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(1l);
		System.out.println(hamletBoothDAO.deleteHamletBoothsByBoothIdsList(boothIdsList));
	}*/
	
	/*public void testgetVotersCountForHamletBooth()
	{
		System.out.println(hamletBoothDAO.getVotersCountForHamletBooth(1l, 232l, 8l).size());
	}*/
	
	/*public void testgetFamiliesCountByHamletBoothIdsList()
	{
		List<Long> hamletBoothIdsList = new ArrayList<Long>(0);
		hamletBoothIdsList.add(1L);
		List<Object[]> list = hamletBoothDAO.getFamiliesCountByHamletBoothIdsList(1l, 232l, 8l);
		if(list != null)
	     for(Object[] params:list)
	      System.out.println(params[0]+" "+params[1]);
		System.out.println(list.size());
	}*/
	
	/*public void testgetAgeWiseHamletBoothList()
	{
		List<Object[]> list = hamletBoothDAO.getAgeWiseHamletBoothList(1l, 232l, 8l, 18l, 22l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetVoterFamilyInfoForHamletBooth()
	{
		System.out.println(hamletBoothDAO.getVoterFamilyInfoForHamletBooth(1l, 232l, 8l).size());
	}*/
	
	public void testgetCastesForHamletBooth()
	{
		List<Object[]> list = hamletBoothDAO.getCastesForHamletBooth(232l, 8l, 1l);
		System.out.println(list.size());
	}

}
