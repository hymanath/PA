package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;

public class PartyManifestoDAOHibernateTest extends BaseDaoTestCase {

	private IPartyManifestoDAO partyManifestoDAO;

	public void setPartyManifestoDAO(IPartyManifestoDAO partyManifestoDAO) {
		this.partyManifestoDAO = partyManifestoDAO;
	}

	/*public void test() {
		partyManifestoDAO.getAll();
	}*/
	//public List<Object[]> getPartyManifestoInfo(Long partyId) {
	public void testGetPartyManifestoInfo()
	{
		 //List<Object[]> result = partyManifestoDAO.getPartyManifestoInfo(362l);
		List<Object[]> result = partyManifestoDAO.getPartyManifestoInfo(362l,"and model.state.stateId=1");
		 System.out.println(result.size());
		 for (Object[] objects : result) {
			System.out.println(objects[0].toString());
			System.err.println(objects[1].toString());
			System.out.println(objects[2].toString());
			System.out.println(objects[3].toString());
			System.out.println(objects[4].toString());
		}
	}
	//public List<Object[]> getSelectedState(Long partyId)
	/*public void testGetSelectedState()
	{
		List<Object[]> result= partyManifestoDAO.getSelectedState(163l);
		System.out.println(result.size());
		for(Object[] object:result)
		{
			System.out.println((Long)object[0]);
			System.out.println(object[1].toString());
			
		}
		
	}
	
	public void testGetElectionTypes(){
		List<Object[]> result = partyManifestoDAO.getElectionTypes(163l, 1l);
		for(Object[] object:result)
		{
			System.out.println((Long)object[0]);
			System.out.println(object[1].toString());
			
		}
	}
	
	public void testGetElectionYearsBasedOnElectionTypeIdPartyIdAndStateId(){
		List<Object[]> result = partyManifestoDAO.getElectionYearsBasedOnElectionTypeId(2l, 163l, 1l);
		for(Object[] object:result)
		{
			System.out.println(object[0]);
			System.out.println((Long)object[1]);
			
		}
	}*/
	/*public void testGetElectionTypeBasedOnPartyId(){
		List list = partyManifestoDAO.getElectionTypeBasedOnPartyId(362l);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}*/
}
