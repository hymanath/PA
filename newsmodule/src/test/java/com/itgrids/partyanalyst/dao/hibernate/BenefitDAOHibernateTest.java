package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBenefitDAO;

public class BenefitDAOHibernateTest extends BaseDaoTestCase{

	private IBenefitDAO benefitDAO;

	public void setBenefitDAO(IBenefitDAO benefitDAO) {
		this.benefitDAO = benefitDAO;
	}
	
	/*public void test()
	{
		benefitDAO.getAll();	
	}*/
	
	/*public void testGetBenifitsList()
	{
		System.out.println(benefitDAO.getBenifitsList().size());
	}*/
	
}
