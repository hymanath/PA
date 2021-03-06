package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CandidateSubscriptionsDAOHibernateTest extends BaseDaoTestCase
{

	private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;

	public void setCandidateSubscriptionsDAO(
			ICandidateSubscriptionsDAO candidateSubscriptionsDAO) {
		this.candidateSubscriptionsDAO = candidateSubscriptionsDAO;
	}
	
	/*public void test()
	{
		candidateSubscriptionsDAO.getAll();
	}*/
	
	/*public void testGetSubscriptionCount()
	{
		System.out.println(candidateSubscriptionsDAO.getSubscriptionCount(1l,1l));
	}*/
	
	/*public void testUnSubscriptionCount()
	{
		System.out.println(candidateSubscriptionsDAO.unSubscriptionDetails(1l,1l));
	}*/
	
	/*public void testgetResults()
	{
		List<CandidateSubscriptions> results = candidateSubscriptionsDAO.getSubscriberDetails(3424l, 611l);
		System.out.println("Size :" + results.size());
		
		for(CandidateSubscriptions subscriptions : results)
		{
			System.out.println(subscriptions.getCandidate().getLastname());
		}
	}*/
	
	/*public void testGetAllUserSubscribedCandidatePages()
	{
		List<Object[]> list = candidateSubscriptionsDAO.getAllUserSubscribedCandidatePages(1l);
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println(params[0]);
		}
	}*/
	/*public void testGetAllSubscriberDetails()
	{
		List<Object[]> list = candidateSubscriptionsDAO.getAllSubscriberDetails();
		System.out.println(list.size());
		for(Object[] params : list)
		{
			System.out.println(params[0]);
		}
	}*/
	
	public void testgetAllCandidatesSubscribedByUser(){
		List<Long> list = candidateSubscriptionsDAO.getAllCandidatesSubscribedByUser(1l);
		System.out.println(list.size());
	}
	
	public void testgetCandidateSubscriptionsForPublicProfileStreeming()
	{
		DateUtilService dateUtilService = new DateUtilService();
		Date toDate = dateUtilService.getCurrentDateAndTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		cal.add(Calendar.DATE, -7);
		Date fromDate = cal.getTime();
		List<Object[]> values = candidateSubscriptionsDAO.getCandidateSubscriptionsForPublicProfileStreeming(1l,toDate,fromDate);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] );
		}
	}
}
