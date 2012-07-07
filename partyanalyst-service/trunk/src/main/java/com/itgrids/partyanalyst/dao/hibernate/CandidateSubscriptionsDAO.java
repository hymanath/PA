package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;

public class CandidateSubscriptionsDAO extends GenericDaoHibernate<CandidateSubscriptions,Long> implements ICandidateSubscriptionsDAO{

	public CandidateSubscriptionsDAO()
	{
		super(CandidateSubscriptions.class);
	}
	
	public Long getSubscriptionCount(Long userId,Long candidateId)
	{
		Query queryObject = getSession().createQuery("select count(model.candidateSubscriptionsId) from CandidateSubscriptions model where " +
				" model.user.userId = ? and model.candidate.candidateId = ?");
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, candidateId);
		return (Long) queryObject.uniqueResult();
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<CandidateSubscriptions> getSubscriberDetails(Long candidateId ,Long userId)
	{
		Object[] params = {candidateId,userId};
		return getHibernateTemplate().find("select model from CandidateSubscriptions model where model.candidate.candidateId = ? and model.user.userId = ?",params);
	}
	
	public Integer unSubscriptionDetails(Long candidateId,Long userId)
	{
		Query queryObject=getSession().createQuery("delete from CandidateSubscriptions model where model.candidate.candidateId = ? and model.user.userId = ?");
		queryObject.setParameter(0,candidateId);
		queryObject.setParameter(1,userId);
		return queryObject.executeUpdate();
	}
}
