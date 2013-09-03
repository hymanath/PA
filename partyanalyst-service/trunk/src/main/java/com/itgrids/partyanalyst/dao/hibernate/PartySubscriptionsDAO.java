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
	
	public List<Object[]> getAllSubscriberDetails()
	{
		
		return getHibernateTemplate().find("select model.party.partyId,model.user.email,model.user.userId,model.user.firstName,model.user.lastName,model.party.longName from PartySubscriptions model where model.user.email is not null ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSubScribedPartyPages(Long userId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName from PartySubscriptions model where model.user.userId =?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserSubscribedPartyPages(Long userId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.shortName,model.party.longName,model.user.userId from PartySubscriptions model,Party model2 where model.user.userId = ? and model.party.partyId = model2.partyId ",userId);
	}
	
	public List<Object[]> getAllParties(){
		return getHibernateTemplate().find("select distinct model.party.partyId,model.party.shortName from PartySubscriptions model ");
	}
	
	public List<Long> getAllPartiesSubscribedByUser(Long userId){
		return getHibernateTemplate().find("select model.party.partyId from PartySubscriptions model where model.user.userId = ?",userId);	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSubscribedParties(Long userId)
	{
		Query query = getSession().createQuery(" select model.party.partyId, model.party.longName, model.party.shortName from " +
				" PartySubscriptions model where model.user.userId =:userId order by model.party.longName ");
		
		query.setParameter("userId", userId);
		return query.list();
	}
}
