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

	/*public void testFinBYElectionYear(){
		List<AllianceGroup> res = allianceGroupDAO.findByGroupId(new Long(2));
	}*/
	
	public void testFindAlliancePartiesByElectionAndParty(){
		List list = allianceGroupDAO.findAlliancePartiesByElectionAndParty(4l, 0l);
		long start = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]);
		long end = System.currentTimeMillis();
		System.out.println((end - start));
	}
	
}
