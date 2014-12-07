package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallCenterFeedbackDAO;
import com.itgrids.partyanalyst.model.CallCenterFeedback;


public class CallCenterFeedbackDAO extends GenericDaoHibernate<CallCenterFeedback, Long> implements ICallCenterFeedbackDAO {
	
	
	public CallCenterFeedbackDAO() {
		super(CallCenterFeedback.class);	
	}
	
	
	
	public List<Object[]> getAllFeedbackData()
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.callCenterFeedbackId,model.comments from  CallCenterFeedback model ");
		Query query = getSession().createQuery(queryStr.toString()); 		
		return query.list();
	}
	
}
