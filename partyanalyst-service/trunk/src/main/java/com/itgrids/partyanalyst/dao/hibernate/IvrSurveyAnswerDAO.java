package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrSurveyAnswerDAO;
import com.itgrids.partyanalyst.model.IvrSurveyAnswer;
import com.itgrids.partyanalyst.utils.IConstants;


public class IvrSurveyAnswerDAO extends GenericDaoHibernate<IvrSurveyAnswer, Long> implements IIvrSurveyAnswerDAO{

	public IvrSurveyAnswerDAO() {
		super(IvrSurveyAnswer.class);
	}
	
	public List<Long> getIvrSurveyIdsByRespondantId(Long ivrRespondantId){
		
		Query query= getSession().createQuery("select distinct model.ivrSurveyId from IvrSurveyAnswer model " +
				"  where model.ivrRespondentId  = :ivrRespondantId " +
				" and model.isDeleted = 'false' " +
				" and model.ivrSurvey.isDeleted ='false'" +
				" and model.isValid='Y'  ");
		
		query.setParameter("ivrRespondantId", ivrRespondantId);
		
		return query.list(); 
		
	}

	public List<Object[]> getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestion.ivrSurveyQuestionId," +
							" model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId," +
							" model.ivrSurveyQuestion.ivrQuestion.question," +
							" model.ivrOption.ivrOptionId," +
							" model.ivrOption.option" +
							" from IvrSurveyAnswer model" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestionId," +
							" question.ivrQuestionId," +
							" question.question," +
							" option.ivrOptionId," +
							" option.option" +
							" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
							" left join model.ivrOption option" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId){
		
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
							" model.ivrSurvey.surveyName," +
							" model.ivrSurveyRound.ivrSurveyRoundId," +
							" model.ivrSurveyRound.roundName," +
							" model.ivrSurveyQuestionId," +
							" question.ivrQuestionId," +
							" question.question," +
							" option.ivrOptionId," +
							" option.option" +
							" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
							" left join model.ivrOption option" +
							" where model.ivrSurvey.ivrSurveyId in (:surveyIds)" +
							" and model.ivrRespondent.ivrRespondentId = :respondentId" +
							" and model.isValid = 'Y' and model.isDeleted = 'false'" +
							" and model.ivrOptionId is null");
		query.setParameterList("surveyIds", surveyIds);
		query.setParameter("respondentId", respondentId);
		
		return query.list();
	}
	
	public List<Object[]> getIvrSurveyAnswerInfoForTdpCadre(Long ivrResondentId)
	{
		Query query = getSession().createQuery(" select model.ivrSurvey.ivrSurveyId," +
				" model.ivrSurvey.surveyName," +
				" model.ivrSurveyRound.ivrSurveyRoundId," +
				" model.ivrSurveyRound.roundName," +
				" model.ivrSurveyQuestionId," +
				" question.ivrQuestionId," +
				" question.question," +
				" option.ivrOptionId," +
				" option.option," +
				" model.ivrResponseType.ivrResponseTypeId,model.ivrResponseType.responseType" +
				" from IvrSurveyAnswer model left join model.ivrSurveyQuestion.ivrQuestion question" +
				" left join model.ivrOption option" +
				" where " +
				"  model.ivrRespondent.ivrRespondentId = :ivrResondentId" +
				" and model.isValid = 'Y' and model.isDeleted = 'false'");
		query.setParameter("ivrResondentId", ivrResondentId);
		return query.list();
		
	}
	public Long getCandateParticipatedSurveyCnt(Long ivrResondentId){
		Query query = getSession().createQuery(" select count(distinct model.ivrSurvey.ivrSurveyId) from IvrSurveyAnswer model where " +
				" model.ivrRespondent.ivrRespondentId =:ivrResondentId and model.isDeleted = 'false' and model.isValid = 'Y' ");
	
	query.setParameter("ivrResondentId", ivrResondentId);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getOptionsCountByQuestionIds(List<Long> surveyQuestionIds)
	{
		Query query = getSession().createQuery("select count(model.ivrSurveyAnswerId),model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId," +
				"model.ivrSurveyQuestion.ivrSurvey.surveyName," +
				"model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId,model.ivrSurveyQuestion.ivrQuestion.question," +
				" model.ivrOption.ivrOptionId,model.ivrOption.option" +
				" from IvrSurveyAnswer model where " +
				" model.isDeleted = 'false' and model.isValid = 'Y'" +
				" and model.ivrOption.isDeleted = 'false' and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false'" +
				" and model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId in(:surveyQuestionIds)" +
				" group by model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId,model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId, model.ivrOption.ivrOptionId");
		query.setParameterList("surveyQuestionIds", surveyQuestionIds);
		return query.list();
	}
	
	public Long getIvrSurveyCountByDistrict(Long districtId){
		Query query = getSession().createQuery(" select count(distinct ISA.ivrSurveyQuestion.ivrSurvey.ivrSurveyId) from IvrRespondentLocation IRL," +
				"IvrSurveyAnswer ISA " +
				" where ISA.ivrRespondent.ivrRespondentId = IRL.ivrRespondent.ivrRespondentId" +
				" and ISA.ivrSurveyQuestion.ivrSurvey.isDeleted = 'false' and  " +
				" ISA.isDeleted = 'false' and ISA.isValid = 'Y'" +
				" and ISA.ivrOption.isDeleted = 'false' and ISA.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false' " +
				" and IRL.userAddress.district.districtId = :districtId");
		query.setParameter("districtId", districtId);
		return (Long) query.uniqueResult();
	}
	
	
	
	
	
	public List<Object[]> getOptionsCountByQuestionIdsForLocation(String locType,Long locationId)
	{		StringBuilder str = new StringBuilder();
		str.append("select count(model.ivrSurveyAnswerId),model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId," +
				"model.ivrSurveyQuestion.ivrSurvey.surveyName," );
		str.append(" model.ivrSurveyRound.ivrSurveyRoundId," +
						" model.ivrSurveyRound.roundName," );
		str.append("model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId,model.ivrSurveyQuestion.ivrQuestion.question," +
				" model.ivrOption.ivrOptionId,model.ivrOption.option,model.ivrSurveyQuestion.ivrSurveyQuestionId,date(model.ivrSurveyQuestion.ivrSurvey.startDate)" +
				" from IvrSurveyAnswer model,IvrRespondentLocation model1 where model.ivrRespondent.ivrRespondentId = model1.ivrRespondent.ivrRespondentId and" +
				" model.isDeleted = 'false' and model.isValid = 'Y'" +
				" and model.ivrOption.isDeleted = 'false' and model.ivrSurveyQuestion.ivrQuestion.isDeleted = 'false'");
		if(locType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" and model1.userAddress.district.districtId = :locationId");
		if(locType.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE))
			str.append(" and model1.userAddress.constituency.constituencyId = :locationId");
		if(locType.equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY_TYPE))
			str.append(" and model1.userAddress.parliamentConstituency.constituencyId = :locationId");
		if(locType.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model1.userAddress.tehsil.tehsilId = :locationId");
		if(locType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			str.append(" and model1.userAddress.localElectionBody.localElectionBodyId = :locationId");
		str.append(" group by model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId,model.ivrSurveyQuestion.ivrQuestion.ivrQuestionId, model.ivrOption.ivrOptionId");
		str.append(" order by model.ivrSurveyQuestion.ivrSurvey.ivrSurveyId desc");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationId", locationId);
		return query.list();
	}
}
