package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFeedbackTaskDAO;
import com.itgrids.partyanalyst.model.FeedBack;
import com.itgrids.partyanalyst.model.FeedBackTask;

public class FeedbackTaskDAO extends GenericDaoHibernate<FeedBackTask, Long> implements IFeedbackTaskDAO{

	public FeedbackTaskDAO() {
		super(FeedBackTask.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedBackTask> findByFeedbackTaskId(Long feedbackTaskId) {
		return getHibernateTemplate().find(" from feedBackTask model where model.feedbackTaskId = ?", feedbackTaskId);
	}
   /** @SuppressWarnings("unchecked")
	public List<String> findByFeedbackTask(String feedbackTask){
    	return getHibernateTemplate().find("select feedBackTaskName from model where feedbackTask = ?" ,feedbackTask);
    } */
}
