package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;
import com.itgrids.partyanalyst.utils.IConstants;


public class AssemblyLocalElectionBodyDAOHibernateTest  extends BaseDaoTestCase {
	IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	/*public void testGetAll()
	{
		List<AssemblyLocalElectionBody> result = assemblyLocalElectionBodyDAO.getAll();
		System.out.println("Results Size:"+result.size());
		
	}*/
	
	/*public void testFindByConstituencyId()
	{
		List result = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituencyId(232L,IConstants.GREATER_ELECTION_TYPE);
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());	
		}		
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetConstituencyByLocalelectionBodyId(){
		List result = assemblyLocalElectionBodyDAO.findConstituencyByLocalELectionBody(562L, IConstants.DELIMITATION_YEAR.toString());
		
		if(result != null){
			Object[] values  =(Object[])result.get(0);
			System.out.println(" Constituency :" + values[1]);
		}
	}

	
	public void testGetLocalElectionBodyId()
	{
		List result = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(31L);
		System.out.println("Results Size:"+result.size());
		Object object = (Object)result.get(0);
		Long id = (Long)object;
		System.out.println("id:"+id);
				
		
	}*/
	public void testGetAssemblyLocalElectionBodyId()
	{
		System.out.println(assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(488L).get(0).toString());
		
	}
}
