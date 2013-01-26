package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterInfoDAO voterInfoDAO;

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	
	/*public void test()
	{
		voterInfoDAO.getAll();
	}*/
	
	
	/*public void testGetVotersCount1()
	{
		List<VoterInfo> list = voterInfoDAO.getVotersCount(1l, 232l, 7l);
		if(list != null && list.size() > 0)
		{
			for(VoterInfo params : list)
			{
				System.out.println(params.getMaleVoters());
			}
		}
	}*/
	
	public void testDeleteVotersInfoByReportLevelValue()
	{
		System.out.println(voterInfoDAO.deleteVotersInfoByReportLevelValue(1l, 232l, 7l));
		
	}
}
