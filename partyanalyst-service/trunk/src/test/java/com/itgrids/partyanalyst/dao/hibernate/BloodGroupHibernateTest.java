package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;

public class BloodGroupHibernateTest extends BaseDaoTestCase{

	private IBloodGroupDAO bloodGroupDAO;

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}
	
	public void test()
	{
		bloodGroupDAO.getAll();
	}
}
