package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFeedbackDAO;
import com.itgrids.partyanalyst.model.FeedBack;

public class FeedbackDAO extends GenericDaoHibernate<FeedBack, Long> implements IFeedbackDAO {

	
	public FeedbackDAO() {
		super(FeedBack.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedBack> findByFeedbackId(Long feedbackId) {
		return getHibernateTemplate().find(" from FeedBack model where model.feedbackId = ?", feedbackId);
	}
	
	public Integer updateStatusToApproveOrReject(List<Long> commentIdList,String status)
	{
		Query queryObject = getSession().createQuery("update FeedBack model set model.status = ? where model.feedbackId in (:commentIdList)");
		queryObject.setParameter(0, status);
		queryObject.setParameterList("commentIdList", commentIdList);
		return queryObject.executeUpdate();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<FeedBack> findCommentTypeAndTaskByFeedBackId(Long feedBackId){
    	
    	return getHibernateTemplate().find("select  model.feedBackComment.commentType and model.feedBackTask.feedBackTaskName where feedbackId = ?" , feedBackId);
    }*/
	/*public Long getCountOfNewlyPostedFeedbackByFreeUser()
	{
		Query query = getSession().createQuery("select count(*) from FeedBack model where model.status = 'NEW'");
		return (Long)query.uniqueResult();
	}*/
	
	public Long getCountOfNewlyPostedFeedbackByFreeUser(Date currentDate)
	{
		Query query = getSession().createQuery("select count(*) from FeedBack model where Date(model.postedDate)=?");
		query.setParameter(0, currentDate);
		return (Long) query.uniqueResult();
	}
}

