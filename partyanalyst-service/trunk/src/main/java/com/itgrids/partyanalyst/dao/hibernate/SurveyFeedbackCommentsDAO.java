package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyFeedbackCommentsDAO;
import com.itgrids.partyanalyst.model.SurveyFeedbackComments;

public class SurveyFeedbackCommentsDAO extends GenericDaoHibernate<SurveyFeedbackComments, Long>implements ISurveyFeedbackCommentsDAO {
	
	public SurveyFeedbackCommentsDAO()
	{
		super(SurveyFeedbackComments.class);
		
	}
	
}
