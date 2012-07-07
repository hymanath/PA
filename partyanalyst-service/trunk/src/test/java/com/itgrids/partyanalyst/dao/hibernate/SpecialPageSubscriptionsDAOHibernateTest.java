package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.model.SpecialPageSubscriptions;

public class SpecialPageSubscriptionsDAOHibernateTest extends BaseDaoTestCase{
	
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;

	public void setSpecialPageSubscriptionsDAO(
			ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO) {
		this.specialPageSubscriptionsDAO = specialPageSubscriptionsDAO;
	}


	/*public void test()
	{
		specialPageSubscriptionsDAO.getAll();
	}*/
	/*public void testGetSubscriptionCount()
	{
		System.out.println(specialPageSubscriptionsDAO.getSubscriptionCount(611l,10l));
	}
	
	public void testUnSubscriptionCount()
	{
		System.out.println(specialPageSubscriptionsDAO.unSubscriptionDetails(10l,611l));
	}*/
	
	public void testGetResult()
	{
		List<SpecialPageSubscriptions> results=specialPageSubscriptionsDAO.getSubscriberDetails(10l,611l);
				System.out.println("Size :" + results.size());
		for (SpecialPageSubscriptions subscriptions:results)
		{
			System.out.println(subscriptions.getSpecialpage().getName());
		}
			
	}

}
