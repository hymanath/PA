package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartySubscriptions;

public interface IPartySubscriptionsDAO extends GenericDao<PartySubscriptions, Long>{
	public Long getSubscriptionCount(Long userId,Long partyId);
	public List<PartySubscriptions> getSubscriberDetails(Long partyId ,Long userId);
	public Integer unSubscriptionDetails(Long partyId,Long userId);
	public List<Object[]> getAllSubscriberDetails();
	public List<Object[]> getAllSubScribedPartyPages(Long userId);
	public List<Object[]> getAllUserSubscribedPartyPages(Long userId);
	public List<Object[]> getAllParties();
	public List<Long> getAllPartiesSubscribedByUser(Long userId);
	public List<Object[]> getAllSubscribedParties(Long userId);
	public List<Object[]> getpartySubscriptionsForPublicProfileStreeming(Long userId,Date toDate,Date fromDate);
}
