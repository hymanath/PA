package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterFamilyRelationDAO;

public class VoterFamilyRelationDAOHibernateTest extends BaseDaoTestCase{
	
	private IVoterFamilyRelationDAO voterFamilyRelationDAO;
	
	
	
	public IVoterFamilyRelationDAO getVoterFamilyRelationDAO() {
		return voterFamilyRelationDAO;
	}


	public void test(){
		List<Object[]> list=voterFamilyRelationDAO.getAllRelations();
		System.out.println(list.size());
	}

}
