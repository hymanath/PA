package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencySubscriptionsDAO;
import com.itgrids.partyanalyst.model.ConstituencySubscriptions;
import com.itgrids.partyanalyst.model.PartySubscriptions;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class ConstituencySubscriptionsDAOHibernateTest extends BaseDaoTestCase{

	private IConstituencySubscriptionsDAO constituencySubscriptionsDAO;

	public void setConstituencySubscriptionsDAO(
			IConstituencySubscriptionsDAO constituencySubscriptionsDAO) {
		this.constituencySubscriptionsDAO = constituencySubscriptionsDAO;
	}

	/*public void test()
	{
		constituencySubscriptionsDAO.getAll();
	}*/
	
	/*public void testSubscriptionCount()
	{
		System.out.println(constituencySubscriptionsDAO.getSubscriptionCount(1l,1l));
	}*/
	/*public void testGetResults()
	{
		List<ConstituencySubscriptions> results=constituencySubscriptionsDAO.getSubscriberDetails(218l, 1l);
		for(ConstituencySubscriptions subscriptions:results)
		{
			System.out.println(subscriptions.getConstituency().getName());
		}
	}*/
	/*public void testUnSubscriptionCount()
	{
		System.out.println(constituencySubscriptionsDAO.unSubscriptionDetails(218l,1l));
	}*/
	
	/*public void testGetAllUserSubscribedConstituencyPages()
	{
		List<Object[]> list = constituencySubscriptionsDAO.getAllUserSubscribedConstituencyPages(1l);
		System.out.println(list.size());
		for(Object[] params : list)
			System.out.println(params[0]);
	}*/
	
	/*public void testgetAllUserSubScribedConstituencies()
	{
		System.out.println(constituencySubscriptionsDAO.getAllUserSubScribedConstituencies(1l).size());
	}*/
	
	public void testgetCommentDataForPublicStreeming()
	{
		DateUtilService dateUtilService = new DateUtilService();
		Date toDate = dateUtilService.getCurrentDateAndTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		cal.add(Calendar.DATE, -7);
		Date fromDate = cal.getTime();
		List<Object[]> values = constituencySubscriptionsDAO.getConctituencySubscriptionsForPublicProfileStreeming(1l,toDate,fromDate);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] );
		}
		
	}
	
}
