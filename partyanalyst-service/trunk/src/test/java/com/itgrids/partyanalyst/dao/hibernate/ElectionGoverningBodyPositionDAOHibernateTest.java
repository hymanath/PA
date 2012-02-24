package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionGoverningBodyPositionDAO;
import com.itgrids.partyanalyst.model.ElectionGoverningBodyPosition;

public class ElectionGoverningBodyPositionDAOHibernateTest extends BaseDaoTestCase{

	private IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO;

	
	public IElectionGoverningBodyPositionDAO getElectionGoverningBodyPositionDAO() {
		return electionGoverningBodyPositionDAO;
	}


	public void setElectionGoverningBodyPositionDAO(
			IElectionGoverningBodyPositionDAO electionGoverningBodyPositionDAO) {
		this.electionGoverningBodyPositionDAO = electionGoverningBodyPositionDAO;
	}


	/*public void testGetAll(){
		List<ElectionGoverningBodyPosition> list = electionGoverningBodyPositionDAO.getAll();
		assertEquals(list.size() >= 0, true);
	}
	
	public void testFindByPosition(){
		List<ElectionGoverningBodyPosition> list = electionGoverningBodyPositionDAO.findByPosition("CHAIRPERSON");
		assertEquals(list.size(), 1);
	}*/
	
	public void testGetAllPositions(){
		List<Object[]> list = electionGoverningBodyPositionDAO.getAllPositions();
		for(Object[] data:list)
		{
		 System.out.println(data[0].toString()+"     "+data[1].toString());
		}
	}
	/*public void testGetPositionsByValue(){
		List<ElectionGoverningBodyPosition> list = electionGoverningBodyPositionDAO.getPositionsByValue("chief mInistEr");
		System.out.println(list.size());
	}*/
}
