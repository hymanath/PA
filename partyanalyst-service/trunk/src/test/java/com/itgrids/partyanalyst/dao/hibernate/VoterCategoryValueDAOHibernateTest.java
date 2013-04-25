package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.model.VoterCategoryValue;

public class VoterCategoryValueDAOHibernateTest  extends BaseDaoTestCase{

	private IVoterCategoryValueDAO voterCategoryValueDAO;

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}
	
	/*public void test(){
		voterCategoryValueDAO.getAll();
	}*/
	/*public void testgetVoterCategoryValues1(){
		java.util.List<VoterCategoryValue> list=voterCategoryValueDAO.getVoterCategoryValues1();
		System.out.println(list.size());
	}*/
	
	public void testCheckCategoeryValues()
	{
		List<Long> categoeryIds = new ArrayList<Long>();
		categoeryIds.add(7l);
		categoeryIds.add(9l);
		categoeryIds.add(1l);
		categoeryIds.add(14l);
		List<Object[]> values = voterCategoryValueDAO.checkCategoeryValues(categoeryIds ,1l);
		for (Object[] parms : values) 
		{
			System.out.println(parms[0]+ ":" + parms[1] + ";" +parms[2]);
		}
	}
	
}
