package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.model.VoterCategoryValue;

public class VoterCategoryValueDAOHibernateTest  extends BaseDaoTestCase{

	private IVoterCategoryValueDAO voterCategoryValueDAO;

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}
	
	public void test(){
		voterCategoryValueDAO.getAll();
	}
	/*public void testgetVoterCategoryValues1(){
		java.util.List<VoterCategoryValue> list=voterCategoryValueDAO.getVoterCategoryValues1();
		System.out.println(list.size());
	}*/
	
}
