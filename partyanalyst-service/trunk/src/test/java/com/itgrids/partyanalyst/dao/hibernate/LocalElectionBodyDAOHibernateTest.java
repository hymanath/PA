package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.LocalElectionBody;

public class LocalElectionBodyDAOHibernateTest extends BaseDaoTestCase{

	private ILocalElectionBodyDAO localElectionBodyDAO;

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	/*
	public void testGetAll(){
		List<LocalElectionBody> list = localElectionBodyDAO.getAll();
		assertEquals(list.size() >= 0 , true);
	}
	*/
	/*public void testFindByElectionTypeDistrictTehsilAndLEBName(){
		List<LocalElectionBody> list = localElectionBodyDAO.findByElectionTypeDistrictTehsilAndLEBName(2l, "Chittoor", "Madanapalli", "Madanapalli");
		assertEquals(1, list.size());
	}
	public void testFindByElectionTypeAndState(){
	
		List result = localElectionBodyDAO.findByElectionTypeAndState(6l, 1l);
		for(int i=0;i<result.size();i++)
		{
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString()));
			System.out.println(parms[1].toString());
				
		}
	}
	*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetLocalBodyElectionType(){
		List elecTypes = localElectionBodyDAO.getLocalELectionTypesInAState(2L);
		
		if(elecTypes != null && elecTypes.size() > 0){
		  for(int i=0;i<elecTypes.size();i++){
			  Object[] values = (Object[])elecTypes.get(i);
			  System.out.println(" Election Type Id :" + (Long)values[0] + " Election Type :" + (String)values[1]);
		  }
		}
	}*/

	/*public void testFindByDistrictId(){
		List list = localElectionBodyDAO.findByDistrictId(19l);
		System.out.println(list.size());
	}
	
	public void testFindByLocalElectionBodyIds(){
		List<Long> localBodyOrWardIds = new ArrayList<Long>();
		localBodyOrWardIds.add(488l);
		localBodyOrWardIds.add(478l);
		List<LocalElectionBody> lebs = localElectionBodyDAO.findByLocalElectionBodyIds(localBodyOrWardIds);
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testFindLocalBodysByElectionType(){
		
		List result = localElectionBodyDAO.getCountOfLocalBodysForALocalElectionBodyType(2L);
		
		if(result != null){
			Object values = (Object)result.get(0);
			Long localBodysCount = (Long)values;
			
			System.out.println(" Total Local Bodys Count :" + localBodysCount);
		}
	}*/
	
	public void testGetLocationTypeForLocalEleBodyByLocalEleBodyId()
	{
		System.out.println(localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(83l));
	}
	
}
