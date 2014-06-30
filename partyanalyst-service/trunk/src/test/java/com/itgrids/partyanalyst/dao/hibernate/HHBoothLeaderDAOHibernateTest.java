package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.hibernate.mapping.Array;

import com.itgrids.partyanalyst.dao.IHHBoothLeaderDAO;
import com.itgrids.partyanalyst.model.HHBoothLeader;

public class HHBoothLeaderDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHBoothLeaderDAO hhBoothLeaderDAO;
	
	public void setHhBoothLeaderDAO(IHHBoothLeaderDAO hhBoothLeaderDAO) {
		this.hhBoothLeaderDAO = hhBoothLeaderDAO;
	}

	public void test(){
		//List<Object[]> list=hhBoothLeaderDAO.getLeadersOfConstituency(228l);
		
		/*List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(134868l);
		
		List<Object[]> count = hhBoothLeaderDAO.getLeadersOfConstituency(228l);
		System.out.println(count);*/
		
		List<Object[]> list = hhBoothLeaderDAO.getConstituenciesOfHouseHolds();
		System.out.println(list.size());
	}

}
