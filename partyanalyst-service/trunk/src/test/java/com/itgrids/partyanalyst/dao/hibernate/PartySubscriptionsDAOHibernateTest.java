package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.model.PartySubscriptions;

public class PartySubscriptionsDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartySubscriptionsDAO partySubscriptionsDAO;

	public void setPartySubscriptionsDAO(
			IPartySubscriptionsDAO partySubscriptionsDAO) {
		this.partySubscriptionsDAO = partySubscriptionsDAO;
	}
	
	/*public void test()
	{
		partySubscriptionsDAO.getAll();
	}*/
	/*public void testGetSubscriptionCount()
	{
		System.out.println(partySubscriptionsDAO.getSubscriptionCount(611l,1117l));
	}*/
	
	/*public void testUnSubscriptionCount()
	{
		System.out.println(partySubscriptionsDAO.unSubscriptionDetails(1117l,611l));
	}*/
	
	public void testGetResults()
	{
		List<PartySubscriptions> results=partySubscriptionsDAO.getSubscriberDetails(1117l, 611l);
		for(PartySubscriptions subscriptions:results)
		{
			System.out.println(subscriptions.getParty().getLongName());
		}
	}


}
