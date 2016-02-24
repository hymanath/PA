package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerDAO;
import com.itgrids.partyanalyst.model.IvrSurveyAnswer;


public class IvrSurveyAnswerDAO extends GenericDaoHibernate<IvrSurveyAnswer, Long> implements IIvrSurveyAnswerDAO{

	public IvrSurveyAnswerDAO() {
		super(IvrSurveyAnswer.class);
	}
	
	public List<Long> getIvrSurveyIdsByRespondantId(Long ivrRespondantId){
		
		Query query= getSession().createQuery("select model.ivrSurveyId from IvrSurveyAnswer model " +
				"  where model.ivrRespondentId  = :ivrRespondantId " +
				" and model.isDeleted = 'false' " +
				" and model.ivrSurvey.isDeleted ='false'  ");
		
		query.setParameter("ivrRespondantId", ivrRespondantId);
		
		return query.list(); 
		
	}

}
