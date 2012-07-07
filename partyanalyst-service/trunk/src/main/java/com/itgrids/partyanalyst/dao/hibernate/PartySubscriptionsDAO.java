package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.model.PartySubscriptions;

public class PartySubscriptionsDAO extends GenericDaoHibernate<PartySubscriptions,Long> implements IPartySubscriptionsDAO{

	public PartySubscriptionsDAO()
	{
		super(PartySubscriptions.class);
	}
	
	public Long getSubscriptionCount(Long userId,Long partyId)
	{
		Query queryObject = getSession().createQuery("select count(model.partySubscriptionsId) from PartySubscriptions model where " +
				" model.user.userId = ? and model.party.partyId = ?");
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, partyId);
		return (Long) queryObject.uniqueResult();
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<PartySubscriptions> getSubscriberDetails(Long partyId ,Long userId)
	{
		Object[] params = {partyId,userId};
		return getHibernateTemplate().find("select model from PartySubscriptions model where model.party.partyId = ? and model.user.userId = ?",params);
	}
	
	public Integer unSubscriptionDetails(Long partyId,Long userId)
	{
		Query queryObject=getSession().createQuery("delete from PartySubscriptions model where model.party.partyId = ? and model.user.userId = ?");
		queryObject.setParameter(0,partyId);
		queryObject.setParameter(1,userId);
		return queryObject.executeUpdate();
	}
}
