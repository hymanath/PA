package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Party;


public class AllianceGroupDAOHibernateTest extends BaseDaoTestCase {

	private IAllianceGroupDAO allianceGroupDAO;
	
	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public void testFinBYElectionYear(){
		List<AllianceGroup> res = allianceGroupDAO.findByGroupId(new Long(2));
	}
	
}
