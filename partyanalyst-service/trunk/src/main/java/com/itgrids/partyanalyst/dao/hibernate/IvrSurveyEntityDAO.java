package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.dao.IIvrSurveyEntityDAO;
import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntity;


public class IvrSurveyEntityDAO extends GenericDaoHibernate<IvrSurveyEntity, Long> implements IIvrSurveyEntityDAO{

	public IvrSurveyEntityDAO() {
		super(IvrSurveyEntity.class);
	}
	
	public List<Object[]> getSurveyEntityTypeDetails(List<Long> surveyIds){
		
		Query query = getSession().createQuery("select model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model.ivrSurveyEntityType.type " +
				" from IvrSurveyEntity model" +
				" where " +
				" model.ivrSurveyId  in (:surveyIds)" +
				" and model.isDeleted='false' ");
		
		query.setParameterList("surveyIds", surveyIds);        		
		return query.list();		
	}
	
	public List<Object[]> getSurveyEntityTypeAndCountDetails(List<Long> surveyIds,Long ivrRespondentId){
		
		Query query = getSession().createQuery("select model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model.ivrSurveyEntityType.type,count(model1.ivrRespondentId)," +
				" model1.ivrResponseType.ivrResponseTypeId, model1.ivrResponseType.responseType " +
				" from IvrSurveyEntity model,IvrSurveyAnswer model1 " +
				" where " +
				" model.ivrSurveyId = model1.ivrSurveyId " +
				" and model.ivrSurveyId  in (:surveyIds) " +
				" and model1.ivrRespondentId =:ivrRespondentId " +
				" and model.isDeleted='false'" +
				" and model1.isDeleted = 'false' " +
				" group by model.ivrSurveyEntityType.ivrSurveyEntityTypeId,model1.ivrResponseType.ivrResponseTypeId ");
		
		query.setParameterList("surveyIds", surveyIds);    
		query.setParameter("ivrRespondentId",ivrRespondentId);
		return query.list();		
	}

}
