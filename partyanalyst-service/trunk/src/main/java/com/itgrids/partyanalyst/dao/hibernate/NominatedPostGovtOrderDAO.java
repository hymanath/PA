package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

public class NominatedPostGovtOrderDAO extends GenericDaoHibernate<NominatedPostGovtOrder, Long> implements INominatedPostGovtOrderDAO{

	public NominatedPostGovtOrderDAO() {
		super(NominatedPostGovtOrder.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> gettingGoPassedDates(Long nominatedPostId ){
		Query query = getSession().createQuery("select distinct model.nominatedPost.nominatedPostId,date(model.govtOrder.fromDate)," +
				                               " date(model.govtOrder.toDate),model.govtOrder.govtOrderId " +
				   							   " from NominatedPostGovtOrder model " +
				   							   " where  model.nominatedPost.nominatedPostId =:nominatedPostId and " +
				   							   " model.isDeleted = 'N' and model.govtOrder.isDeleted = 'N' ");
		query.setParameter("nominatedPostId", nominatedPostId);
		return query.list();
	}


	private static final String NOMINATED_POST_ID =" model.nominatedPostId ";
	public List<Long> getExpiredNominatedPostIdsLsit(Date currentDate){
		Query query = getSession().createQuery(" select distinct "+NOMINATED_POST_ID+" from NominatedPostGovtOrder model where date(model.govtOrder.toDate) <:currentDate " +
				" and model.isDeleted='N' and model.govtOrder.isDeleted='N' and model.isExpired='N' ");
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	public int updateApplicationExpiredByPostIds(List<Long> nominatedPostIdsLsist, Date currentDate,Long userId){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("update NominatedPostGovtOrder model set model.isExpired='Y',model.updatedTime=:currentDate ");
		if(userId != null && userId.longValue()>0L)
			queryStr.append(" ,model.updatedBy=:updatedBy ");
		queryStr.append(" where model.isDeleted='N' and model.nominatedPostId in (:nominatedPostIdsLsist) and model.isExpired='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("currentDate", currentDate);
		query.setParameterList("nominatedPostIdsLsist", nominatedPostIdsLsist);
		if(userId != null && userId.longValue()>0L)
			query.setParameter("userId", userId);
		
		return query.executeUpdate();
	}
	public List<Long> getNominatedPostGovtOrderByPostId(Long postId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.nominatedPostGovtOrderId from  NominatedPostGovtOrder model where model.nominatedPost.nominatedPostId = :postId and " +
				" model.isDeleted = 'N' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(postId != null && postId.longValue() > 0l){
	    	query.setParameter("postId", postId);
	    }
		
		return query.list();
	}
	
	
}
