package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SpecialPageSubscriptions;

public interface ISpecialPageSubscriptionsDAO extends GenericDao<SpecialPageSubscriptions,Long>{
	public Long getSubscriptionCount(Long userId,Long specialPageId);
	public List<SpecialPageSubscriptions> getSubscriberDetails(Long specialPageId ,Long userId);
	public Integer unSubscriptionDetails(Long specialPageId,Long userId);
	public List<Object[]> getAllSubscriberDetails();
	public List<Object[]> getAllUserSubscribedSpecialPages(Long userId);
	public List<Long> getUserSubscribedSpecialPageIds(Long userId);
	public List<Object[]> getAllSpecialPages();
	public List<Long> getAllSpecialPagesSubscribedByUser(Long userId);
 
}
