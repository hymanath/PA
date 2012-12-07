package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencySubscriptions;
import com.itgrids.partyanalyst.model.PartySubscriptions;


public interface IConstituencySubscriptionsDAO extends GenericDao<ConstituencySubscriptions, Long>{ 

	public Long getSubscriptionCount(Long userId,Long constituencyId);
	
	public List<ConstituencySubscriptions> getSubscriberDetails(Long constituencyId ,Long userId);
	
	public Integer unSubscriptionDetails(Long constituencyId,Long userId);
	
	public List<Object[]> getAllUserSubscribedConstituencyPages(Long userId);
}
