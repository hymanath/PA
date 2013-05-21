package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;

public class VoterModificationAgeInfoDAOHiberbateTest extends BaseDaoTestCase{
	
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}
	
	
	/*public void test()
	{
		voterModificationAgeInfoDAO.getAll();
	}
   */
	/*public void test()
	{
		List<Long> ids1 = new ArrayList<Long>();
		ids1.add(1l);
		Set<Long> ids2 = new HashSet<Long>();
		ids2.add(1l);
		voterModificationAgeInfoDAO.getGenderWiseVoterModificationsBetweenPublications(1l,2l,3l,ids1,ids2);
	}*/
	
	public void testgetVoterModificationIds()
	{
		List<Long> modificationIdsList = new ArrayList<Long>(0);
		modificationIdsList.add(413l);
		modificationIdsList.add(414l);
		System.out.println(voterModificationAgeInfoDAO.getVoterModificationIds(modificationIdsList).size());
	}
}
