package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyElectionStateResultDAO;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;

public class PartyElectionStateResultDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartyElectionStateResultDAO partyElectionStateResultDAO;

	public IPartyElectionStateResultDAO getPartyElectionStateResultDAO() {
		return partyElectionStateResultDAO;
	}

	public void setPartyElectionStateResultDAO(
			IPartyElectionStateResultDAO partyElectionStateResultDAO) {
		this.partyElectionStateResultDAO = partyElectionStateResultDAO;
	}
	
	/*public void testFindByPartyElectionAndCountry(){
		List list = partyElectionStateResultDAO.findStatewiseResultsForPartyElectionAndCountry(24l, 1l, 1l);
		System.out.println(list.size());
	}*/

	public void testGetByPartyIdElectionIdAndStateId(){
		List list = partyElectionStateResultDAO.getByPartyIdElectionIdAndStateId(24l, 2l, 19l);
		System.out.println(list.size());
	}
	
}
