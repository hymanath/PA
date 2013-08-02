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
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(844l);
		List<Object[]> values = voterCastInfoDAO.getCastAndPartyForSelectedLevel(1l,2l,ids,8l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	/*public void test()
	{
		
		List<Object[]> values = voterCastInfoDAO.getAllCasteInfoByUserId(1l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	
	public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(11l);
		ids.add(12l);
		ids.add(1l);
		ids.add(7l);
		ids.add(4l);
		ids.add(13l);
		ids.add(10l);
		ids.add(6l);
		ids.add(9l);
		ids.add(8l);
		ids.add(3l);
		ids.add(2l);
		ids.add(5l);
		List<Object[]> values = voterCastInfoDAO.getTopCasteFoeSelctedLevel(ids,3l,8l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1] +":"+ parms[2] +":"+ parms[3]);
		}
	}
}
