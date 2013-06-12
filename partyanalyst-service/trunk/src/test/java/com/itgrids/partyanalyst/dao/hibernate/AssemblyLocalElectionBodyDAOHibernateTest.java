package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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

	*/
	/*public void testGetLocalElectionBodyId()
	
	public void testGetLocalElectionBodyId()
	{
		List result = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(31L);
		System.out.println("Results Size:"+result.size());
		Object object = (Object)result.get(0);
		Long id = (Long)object;
		System.out.println("id:"+id);		
	}*/
/*	public void testFindByConstituencyIds()
	{
		List result = assemblyLocalElectionBodyDAO.findByConstituencyIds("836,844,860");
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());	
		}
				

		
	}*/
	/*public void testGetAssemblyLocalElectionBodyId()
	{
		List ids= assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(4L);
		System.out.println(ids.size());
		
	}*/
	
	/*public void testDeleteByLocalElectionBodyAndConstituency()
	{
		List<Long> ids = new ArrayList<Long>(); 
		ids.add(566l);
		int result=assemblyLocalElectionBodyDAO.deleteByLocalElectionBodyAndConstituency(ids,318l);
		System.out.println("No of records deleted:"+result);
		setComplete();
	}*/
	
	/*public void testgetLocalElectionBodyIdByassemblyLocalElectionBodyId()
	{
		Long value = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByassemblyLocalElectionBodyId(80l);
		System.out.println(value);
	}*/
	
	/*public void testGetLocalElecBodyName()
	{
		List<Object[]> value = assemblyLocalElectionBodyDAO.getLocalElecBodyName("108");
		System.out.println(value.size());
	}*/
	
	/*public void testGetLocalElectionBodyIdByAssemblyLocalElectionBodyId()
	{
		System.out.println(assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByAssemblyLocalElectionBodyId(123l));
	}*/
	
	/*public void testGetLocalBodyIdBasedOnConstituencyId()
	{
		Long  localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(232l);
		System.out.println(localBodyId);
	}*/
	
	public void testgetAssemblyLocalElectionBodyIdsList()
	{
		List<Long> ids = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyIdsList(20l,347l);
		for (Long long1 : ids) {
			System.out.println(long1);
		}
	}
}
