package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;

public class VoterCastBasicInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;

	public void setVoterCastBasicInfoDAO(
			IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
		this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
	}
	
	/*public void test()
	{
		voterCastBasicInfoDAO.getAll();
	}*/
	
	/*public void testTotalVoters()
	{
		List<Long> levelValues = new ArrayList<Long>();
		levelValues.add(11l);
		List<Long> values = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(levelValues,1l,8l,3l,232l);
		System.out.println(values);
	}*/
	
	/*public void testgetVoterCastBasicInfoList()
	{
		voterCastBasicInfoDAO.getVoterCastBasicInfoList(232l,8l,1l);
	}*/
	
	public void testData()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(232l);
		List<Object[]> values = voterCastBasicInfoDAO.getCasteAvaliableConstituencyes(ids,1l);
		for (Object[] objects : values) {
			System.out.println(objects[0]);
		}
	}
}
