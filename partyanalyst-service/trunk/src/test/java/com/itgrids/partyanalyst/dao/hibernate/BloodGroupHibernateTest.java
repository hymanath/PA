package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.model.BloodGroup;

public class BloodGroupHibernateTest extends BaseDaoTestCase{

	private IBloodGroupDAO bloodGroupDAO;

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}
	
	/*public void test()
	{
		bloodGroupDAO.getAll();
	}*/
	
	public void testGetBloodGroupList()
	{
		List<BloodGroup> list = bloodGroupDAO.getBloodGroupList();
		if(list != null && list.size() > 0)
		{
		 for(BloodGroup bloodGroup:list)
		  System.out.println(bloodGroup.getBloodGroup());
		}
	}
}
