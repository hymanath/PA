package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencySubscriptionsDAO;
import com.itgrids.partyanalyst.model.ConstituencySubscriptions;
import com.itgrids.partyanalyst.model.PartySubscriptions;


public class ConstituencySubscriptionsDAO extends  GenericDaoHibernate<ConstituencySubscriptions,Long> implements IConstituencySubscriptionsDAO{

	public ConstituencySubscriptionsDAO()
	{
		super(ConstituencySubscriptions.class);
	}
	
	public Long getSubscriptionCount(Long userId,Long constituencyId)
	{
		Query queryObject = getSession().createQuery("select count(model.constituencySubscriptionsId) from ConstituencySubscriptions model where " +
				" model.user.userId = ? and model.constituency.constituencyId = ?");
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, constituencyId);
		return (Long) queryObject.uniqueResult();
	}
	public List<ConstituencySubscriptions> getSubscriberDetails(Long constituencyId ,Long userId)
	{
		Object[] params = {constituencyId,userId};
		return getHibernateTemplate().find("select model from ConstituencySubscriptions model where model.constituency.constituencyId = ? and model.user.userId = ?",params);
	}
	
	public Integer unSubscriptionDetails(Long constituencyId,Long userId)
	{
		Query queryObject=getSession().createQuery("delete from ConstituencySubscriptions model where model.constituency.constituencyId = ? and model.user.userId = ?");
		queryObject.setParameter(0,constituencyId);
		queryObject.setParameter(1,userId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserSubscribedConstituencyPages(Long userId)
	{
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name,model.user.userId,model2.electionScope.electionType.electionType from ConstituencySubscriptions model,Constituency model2 where model.user.userId = ? and model.constituency.constituencyId = model2.constituencyId",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllUserSubScribedConstituencies(Long userId)
	{
	  return getHibernateTemplate().find(" select model.constituency.constituencyId from ConstituencySubscriptions model, Constituency model2 where model.constituency.constituencyId = model2.constituencyId and model.user.userId =? ",userId);
	}
	
}
