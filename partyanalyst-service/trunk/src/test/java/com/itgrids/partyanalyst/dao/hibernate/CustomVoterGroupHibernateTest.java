package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;

public class CustomVoterGroupHibernateTest extends BaseDaoTestCase{
	
private ICustomVoterGroupDAO customVoterGroupDAO;

public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
	this.customVoterGroupDAO = customVoterGroupDAO;
}


		/*public void test()
	    {
	    	List<Object[]> list = customVoterGroupDAO.checkDuplicateGroupName(1l,844l,"j");
	    	System.out.println(list.size());
	    }*/
public void testGetVotersCountBasedOnAgeInGroup()
{
	List<Object[]> list = customVoterGroupDAO.getVotersCountBasedOnAgeInGroup(1l,1l,"36to45",3l);
	System.out.println(list.size());
	for(Object[] obj:list){
		System.out.print(obj[1].toString()+":");
		System.out.println((Long)obj[0]);
	}
		
	
}
}
