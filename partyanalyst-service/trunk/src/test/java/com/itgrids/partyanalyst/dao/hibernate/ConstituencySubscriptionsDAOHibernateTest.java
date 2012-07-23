package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencySubscriptionsDAO;
import com.itgrids.partyanalyst.model.ConstituencySubscriptions;
import com.itgrids.partyanalyst.model.PartySubscriptions;

public class ConstituencySubscriptionsDAOHibernateTest extends BaseDaoTestCase{

	private IConstituencySubscriptionsDAO constituencySubscriptionsDAO;

	public void setConstituencySubscriptionsDAO(
			IConstituencySubscriptionsDAO constituencySubscriptionsDAO) {
		this.constituencySubscriptionsDAO = constituencySubscriptionsDAO;
	}

	public void test()
	{
		constituencySubscriptionsDAO.getAll();
	}
	
	/*public void testSubscriptionCount()
	{
		System.out.println(constituencySubscriptionsDAO.getSubscriptionCount(1l,1l));
	}*/
	public void testGetResults()
	{
		List<ConstituencySubscriptions> results=constituencySubscriptionsDAO.getSubscriberDetails(218l, 1l);
		for(ConstituencySubscriptions subscriptions:results)
		{
			System.out.println(subscriptions.getConstituency().getName());
		}
	}
	/*public void testUnSubscriptionCount()
	{
		System.out.println(constituencySubscriptionsDAO.unSubscriptionDetails(218l,1l));
	}*/
	
	
}
