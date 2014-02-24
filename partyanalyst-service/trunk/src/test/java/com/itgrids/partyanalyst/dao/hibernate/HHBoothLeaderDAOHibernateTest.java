package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHBoothLeaderDAO;
import com.itgrids.partyanalyst.model.HHBoothLeader;

public class HHBoothLeaderDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHBoothLeaderDAO hhBoothLeaderDAO;
	
	public void setHhBoothLeaderDAO(IHHBoothLeaderDAO hhBoothLeaderDAO) {
		this.hhBoothLeaderDAO = hhBoothLeaderDAO;
	}

	public void test(){
		List<HHBoothLeader> list=hhBoothLeaderDAO.getAllLeaderModelByBoothId(134868l);
		System.out.println(list.size());
	}

}
