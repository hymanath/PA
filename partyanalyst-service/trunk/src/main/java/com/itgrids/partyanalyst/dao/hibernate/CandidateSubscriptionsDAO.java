package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllSubscriberDetails(){
		return getHibernateTemplate().find("select model.candidate.candidateId,model.user.email,model.user.userId,model.user.firstName,model.user.lastName from CandidateSubscriptions model where model.user.email is not null");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserSubscribedCandidatePages(Long userId)
	{
		return getHibernateTemplate().find("select model.candidate.candidateId, model.candidate.lastname, model.user.userId from CandidateSubscriptions model,Candidate model2 where model.user.userId = ? and model.candidate.candidateId = model2.candidateId",userId);
	}
	
	public List<Object[]> getAllCandidates(){
		return getHibernateTemplate().find("select distinct model.candidate.candidateId, model.candidate.lastname from CandidateSubscriptions model ");
	}
	
	public List<Long> getAllCandidatesSubscribedByUser(Long userId){
		return getHibernateTemplate().find("select model.candidate.candidateId from CandidateSubscriptions model where model.user.userId = ?",userId);	
	}
	
	public List<Object[]> getCandidateSubscriptionsForPublicProfileStreeming(Long userId,Date toDate,Date fromDate)
	{
		Query query = getSession().createQuery("select model.candidate.lastname ," +//0
				" model.updatedTime ," +//1
				" model.user.firstName , " +//2
				" model.user.lastName , " +//3
				" model.user.profileImg , " +//4
				" model.candidate.image , " +//5
				" model.candidate.candidateId " +//6
				" from CandidateSubscriptions model " +
				" where model.user.userId = :userId and " +
				" date(model.updatedTime) >= :fromDate  and " +
				" date(model.updatedTime) <= :toDate");
		query.setParameter("userId", userId);
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
		return query.list();
	}
}
