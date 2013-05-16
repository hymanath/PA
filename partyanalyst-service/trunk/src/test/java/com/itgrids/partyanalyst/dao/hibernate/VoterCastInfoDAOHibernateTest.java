package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;

public class VoterCastInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastInfoDAO voterCastInfoDAO;

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	
	public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(844l);
		List<Object[]> values = voterCastInfoDAO.getCastAndPartyForSelectedLevel(1l,2l,ids,8l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}
}
