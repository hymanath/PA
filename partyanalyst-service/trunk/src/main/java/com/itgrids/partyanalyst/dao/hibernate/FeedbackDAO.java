package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFeedbackDAO;
import com.itgrids.partyanalyst.model.FeedBack;
import com.itgrids.partyanalyst.model.Registration;

public class FeedbackDAO extends GenericDaoHibernate<FeedBack, Long> implements IFeedbackDAO {

	
	public FeedbackDAO() {
		super(FeedBack.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedBack> findByFeedbackId(Long feedbackId) {
		return getHibernateTemplate().find(" from feedBack model where model.feedbackId = ?", feedbackId);
	}
    /*@SuppressWarnings("unchecked")
	public List<FeedBack> findCommentTypeAndTaskByFeedBackId(Long feedBackId){
    	
    	return getHibernateTemplate().find("select  model.feedBackComment.commentType and model.feedBackTask.feedBackTaskName where feedbackId = ?" , feedBackId);
    }*/
}

