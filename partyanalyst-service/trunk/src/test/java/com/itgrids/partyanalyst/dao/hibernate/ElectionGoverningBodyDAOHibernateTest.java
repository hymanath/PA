package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public class ElectionGoverningBodyDAOHibernateTest extends BaseDaoTestCase{

	private IElectionGoverningBodyDAO electionGoverningBodyDAO;

	
	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}


	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}


	/*public void testGetAll(){
		List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}*/
	
	/*public void testGetAllStatesForMinisters(){
		List<Object[]> list = electionGoverningBodyDAO.getAllStatesForMinisters();
		for(Object[] states:list)
		{
			System.out.println("id: "+states[0].toString()+"  name: "+states[1].toString());
		}
	}
	public void testGetAllYearsAndElecIds(){
		List<Object[]> list = electionGoverningBodyDAO.getAllYearsAndElecIdsForAssembly(27l);
		for(Object[] years:list)
		{
			System.out.println("id: "+years[0].toString()+"  name: "+years[1].toString());
		}
	}*/
	public void testGetAllYearsAndElecIdsForParliament(){
		List<Object[]> list = electionGoverningBodyDAO.getAllYearsAndElecIdsForParliament();
		for(Object[] years:list)
		{
			System.out.println("id: "+years[0].toString()+"  name: "+years[1].toString());
		}
	}
}
