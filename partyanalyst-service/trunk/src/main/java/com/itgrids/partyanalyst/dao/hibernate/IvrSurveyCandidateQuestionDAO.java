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
				" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false' " +
				" and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false' ");
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
    
	public List<Object[]> getIvrSurveyScopeIdsByCandidate(Long candidateId,Long cadreId){
		Query query = getSession().createQuery("select distinct model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId," +
													" model.ivrSurveyQuestion.ivrSurvey.surveyName," +
													" model.locationScopeId," +
													" model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId," +
													" model.ivrSurveyQuestion.ivrQuestion.question" +
													" from IvrSurveyCandidateQuestion model" +
													" where (model.candidate.candidateId = :candidateId OR model.tdpCadre.tdpCadreId = :cadreId)" +
													" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false'" +
													" and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false'");
		query.setParameter("candidateId", candidateId);
		query.setParameter("cadreId", cadreId);
		return  query.list();
	}
	
	public List<Object[]> getLocationScopeWiseQuestionOptions(Long surveyId,Long questionId,String locationType,Long locationVal){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.ivrOption.ivrOptionId,model.ivrOption.option,count(model1.ivrSurveyAnswerId)" +
					" from IvrSurveyCandidateQuestion model,IvrSurveyAnswer model1" +
					" where model.ivrSurveyQuestion.ivrSurveyQuestionId = model1.ivrSurveyQuestion.ivrSurveyQuestionId" +
					" and model.ivrOption.ivrOptionId = model1.ivrOption.ivrOptionId" +
					" and model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId = :surveyId" +
					" and model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId = :questionId" +
					" and model.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false'" +
					" and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false'" +
					" and model1.isDeleted = 'false' and model1.isValid = 'Y'");
		if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("state"))
			sb.append(" and model.userAddress.state.stateId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("district"))
			sb.append(" and model.userAddress.district.districtId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("constituency"))
			sb.append(" and model.userAddress.constituency.constituencyId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("mandal"))
			sb.append(" and model.userAddress.tehsil.tehsilId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" and model.userAddress.panchayat.panchayatId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("leb"))
			sb.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationVal");
		else if(locationType != null && locationVal > 0l && locationType.trim().equalsIgnoreCase("ward"))
			sb.append(" and model.userAddress.ward.constituencyId = :locationVal");
		
		sb.append(" group by model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId,model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId, model.ivrOption.ivrOptionId");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("surveyId", surveyId);
		query.setParameter("questionId", questionId);
		if(locationType != null && locationVal > 0l)
			query.setParameter("locationVal", locationVal);
		return query.list();
	}
}
