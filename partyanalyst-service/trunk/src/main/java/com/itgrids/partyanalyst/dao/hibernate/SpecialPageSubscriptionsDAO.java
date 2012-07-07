package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.model.SpecialPageSubscriptions;

public class SpecialPageSubscriptionsDAO extends GenericDaoHibernate<SpecialPageSubscriptions,Long> implements ISpecialPageSubscriptionsDAO{

	public SpecialPageSubscriptionsDAO()
	{
		super(SpecialPageSubscriptions.class);
	}
	
	public Long getSubscriptionCount(Long userId,Long specialPageId)
	{
		Query queryObject = getSession().createQuery("select count(model.specialPageSubscriptionId) from SpecialPageSubscriptions model where " +
				" model.user.userId = ? and model.specialpage.specialPageId = ?");
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, specialPageId);
		return (Long) queryObject.uniqueResult();
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<SpecialPageSubscriptions> getSubscriberDetails(Long specialPageId ,Long userId)
	{
		Object[] params = {specialPageId,userId};
		return getHibernateTemplate().find("select model from SpecialPageSubscriptions model where model.specialpage.specialPageId = ? and model.user.userId = ?",params);
	}
	
	public Integer unSubscriptionDetails(Long specialPageId,Long userId)
	{
		Query queryObject=getSession().createQuery("delete from SpecialPageSubscriptions model where model.specialpage.specialPageId = ? and model.user.userId = ?");
		queryObject.setParameter(0,specialPageId);
		queryObject.setParameter(1,userId);
		return queryObject.executeUpdate();
	}
}
