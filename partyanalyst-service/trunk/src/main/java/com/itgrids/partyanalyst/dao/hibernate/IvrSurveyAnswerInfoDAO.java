package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerInfoDAO;
import com.itgrids.partyanalyst.model.IvrSurveyAnswerInfo;

public class IvrSurveyAnswerInfoDAO extends GenericDaoHibernate<IvrSurveyAnswerInfo, Long>  implements IIvrSurveyAnswerInfoDAO{

	public IvrSurveyAnswerInfoDAO() {
		super(IvrSurveyAnswerInfo.class);		
	}
	
	
}
