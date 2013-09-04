package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class AssemblyLocalElectionBodyWardDAOHibernateTest  extends BaseDaoTestCase {
	
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private INominationDAO nominationDAO;
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}	
		
	public void testFindByLocalElectionBody()
	{
		List result = assemblyLocalElectionBodyWardDAO.findWardsByAssemblyLocalBody(132l,"2009");
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());
		}
	}
	
	/*public void testFindByConstituencyIdAndYear(){
		List wardsInfo = assemblyLocalElectionBodyWardDAO.findByConstituencyIdAndYear(51l, IConstants.GREATER_ELECTION_TYPE);
		for(int i = 0;i<wardsInfo.size();i++)
		{
			Object[] obj = (Object[])wardsInfo.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			System.out.println("name:"+obj[1].toString());			
		}
		StringBuilder wardIds = new StringBuilder(); 
		for(Object[] values:(List<Object[]>)wardsInfo){
			wardIds.append(",").append(values[0]);
		}
		List list = nominationDAO.getALLPartiesByElectionId(33l,wardIds.substring(1));
		System.out.println(list.size());
		
	}*/
	
	/*public void testFindByAssemblyLocalElectionBodyWardAndYear(){
		List list = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBodyWardAndYear(2l, 7696l, "2009");
		System.out.println(list.size());
	}*/
	
	
	/*public void testFindByAssemblyLocalElectionBody()
	{
		List result = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBody(135l, "2009");
		System.out.println("Results Size:"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			Object[] obj = (Object[])result.get(i);
			System.out.println("id:"+Long.parseLong(obj[0].toString()));
			//System.out.println("name:"+obj[1].toString());
		//	System.out.println("name:"+obj[2].toString());
		}
	}*/
	
	/*public void testDeleteByWardsAndConstituency()
	{
		List<Long> ids = new ArrayList<Long>(); 
		ids.add(643l);
		ids.add(644l);
		int result=assemblyLocalElectionBodyWardDAO.deleteByWardsAndConstituency(ids);
		System.out.println("No of records deleted:"+result);
		setComplete();
	}*/
	
	/*public void testGetAssemblyLocalElectionBodyWardIds()
	{
		List<Long> ids = new ArrayList<Long>(); 
		ids.add(18302l);
		ids.add(18303l);
		
		List result = assemblyLocalElectionBodyWardDAO.getAssemblyLocalElectionBodyWardIds(313l,ids);
		System.out.print("Size"+result.size());
		for(int i = 0;i<result.size();i++)
		{
			System.out.println("id:"+result.get(i));
			
		}		
	}*/
	
	
}
