package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import antlr.collections.List;

import com.itgrids.partyanalyst.dao.IVoterCategoryValuesDAO;
import com.itgrids.partyanalyst.model.VoterCategoryValues;

public class VoterCategoryValuesDAOHibernateTest  extends BaseDaoTestCase{

	private IVoterCategoryValuesDAO voterCategoryValuesDAO;

	public void setVoterCategoryValuesDAO(
			IVoterCategoryValuesDAO voterCategoryValuesDAO) {
		this.voterCategoryValuesDAO = voterCategoryValuesDAO;
	}
	
	public void testgetVoterCategoryValues1(){
		java.util.List<VoterCategoryValues> list=voterCategoryValuesDAO.getVoterCategoryValues1();
		System.out.println(list.size());
	}
	
}
