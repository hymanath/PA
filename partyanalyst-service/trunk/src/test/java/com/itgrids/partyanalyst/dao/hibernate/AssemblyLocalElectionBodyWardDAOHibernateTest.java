package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class AssemblyLocalElectionBodyWardDAOHibernateTest  extends BaseDaoTestCase {
	
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}	
		
	/*public void testFindByLocalElectionBody()
	{
		List result = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(562l, "2010");
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());			
		}
	}*/
	
	public void testFindByConstituencyIdAndYear(){
		List list = assemblyLocalElectionBodyWardDAO.findByConstituencyIdAndYear(315l, IConstants.GREATER_ELECTION_TYPE);
		System.out.println(list.size());
	}
}
