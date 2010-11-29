package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDesignationDAO;
import com.itgrids.partyanalyst.model.PartyWorkingCommittee;
import com.itgrids.partyanalyst.model.PartyWorkingCommitteeDesignation;

public class PartyWorkingCommitteeDesignationDAOHibernateTest extends BaseDaoTestCase {
	IPartyWorkingCommitteeDesignationDAO partyWorkingCommitteeDesignationDAO;

	public IPartyWorkingCommitteeDesignationDAO getPartyWorkingCommitteeDesignationDAO() {
		return partyWorkingCommitteeDesignationDAO;
	}

	public void setPartyWorkingCommitteeDesignationDAO(
			IPartyWorkingCommitteeDesignationDAO partyWorkingCommitteeDesignationDAO) {
		this.partyWorkingCommitteeDesignationDAO = partyWorkingCommitteeDesignationDAO;
	}
	
	public void test()
	{
		List<PartyWorkingCommitteeDesignation> result=partyWorkingCommitteeDesignationDAO.getDesignationsForPartyCommittee(1l);
		for(PartyWorkingCommitteeDesignation partyWorkingCommitteeDesignation: result){
			System.out.println("ID:"+partyWorkingCommitteeDesignation.getPartyWkgCommitteeDesignationId());
			System.out.println("Name:"+partyWorkingCommitteeDesignation.getDesignation());
		}	
		
	}
}
