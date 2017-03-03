package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyCandidateQuestionDAO;
import com.itgrids.partyanalyst.model.HomePageQuestion;
import com.itgrids.partyanalyst.model.IvrSurveyCandidateQuestion;

public class IvrSurveyCandidateQuestionDAO extends GenericDaoHibernate<IvrSurveyCandidateQuestion, Long> implements IIvrSurveyCandidateQuestionDAO {

    public IvrSurveyCandidateQuestionDAO(){
    	super(IvrSurveyCandidateQuestion.class);
    }
    
	public Long getIvrSurveyCountByCandiate(Long candidateId,Long cadreId){
		Query query = getSession().createQuery(" select count(distinct model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId) from IvrSurveyCandidateQuestion model " +
				" where (model.candidate.candidateId = :candidateId OR model.tdpCadre.tdpCadreId = :cadreId)" +
				" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false' ");
		query.setParameter("candidateId", candidateId);
		query.setParameter("cadreId", cadreId);
		return (Long) query.uniqueResult();
	}
	
	public List<Long> getIvrSurveyQuestionsByCandiate(Long candidateId,Long cadreId){
		Query query = getSession().createQuery(" select distinct model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId from IvrSurveyCandidateQuestion model " +
				" where (model.candidate.candidateId = :candidateId OR model.tdpCadre.tdpCadreId = :cadreId)" +
				" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false' and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false'");
		query.setParameter("candidateId", candidateId);
		query.setParameter("cadreId", cadreId);
		return  query.list();
	}
	
	public List<Object[]> getIvrSurveyQuestionsOptionsByCandiate(List<Long> questionIds){
		Query query = getSession().createQuery("select distinct model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId," +
				" model.ivrSurveyQuestion.ivrSurvey.surveyName," +
				" model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId,model.ivrSurveyQuestion.ivrQuestion.question," +
				" ivrOption.ivrOptionId,ivrOption.option," +
				" model.candidate.candidateId,model.candidate.firstname" +
				" from IvrSurveyCandidateQuestion model left join model.ivrOption ivrOption" +
				" where model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId in(:questionIds) " +
				" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false'" +
				" and ivrOption.isDeleted = 'false' and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false' ");
		query.setParameterList("questionIds", questionIds);
		return query.list();
	}
    
}
