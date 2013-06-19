package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISurveyAnswerInfoDAO;
import com.itgrids.partyanalyst.model.SurveyAnswerInfo;

public class SurveyAnswerInfoDAO extends GenericDaoHibernate<SurveyAnswerInfo, Long> implements ISurveyAnswerInfoDAO{

	public SurveyAnswerInfoDAO() {
		super(SurveyAnswerInfo.class);
		// TODO Auto-generated constructor stub
	}

}
