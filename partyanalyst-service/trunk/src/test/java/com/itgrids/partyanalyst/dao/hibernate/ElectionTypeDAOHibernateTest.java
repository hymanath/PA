package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;

import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.model.ElectionType;

public class ElectionTypeDAOHibernateTest extends BaseDaoTestCase {

	IElectionTypeDAO electionTypeDAO;

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}
	
	
	/*public void testFindByElectionType(){
		List<ElectionType> electionTypes=electionTypeDAO.findByElectionType("Assembly");
		if(electionTypes!=null){
			
		}
		Assert.assertNotNull(electionTypes);
	}*/
	
	public void testgetElectionTypeList()
	{
		electionTypeDAO.getElectionTypeList();
	}
}
