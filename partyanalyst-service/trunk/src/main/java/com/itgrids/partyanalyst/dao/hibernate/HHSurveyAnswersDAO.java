package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;


public class HHSurveyAnswersDAO extends GenericDaoHibernate<HHSurveyAnswers,Long> implements IHHSurveyAnswersDAO {
	
	public HHSurveyAnswersDAO() {
		super(HHSurveyAnswers.class);
	}
	
	public List<HHSurveyAnswers> getSurveyAnswersByHouseHoldId(Long houseHoldId){
		Query qry=getSession().createQuery("select model from HHSurveyAnswers model where model.houseHold.houseHoldId=:houseHoldId");
		qry.setParameter("houseHoldId", houseHoldId);
		return qry.list();
	}
	
	
	public int deleteAllPreviousAnswersByHouseHoldsId(Long houseHoldsId)
	{
		Query query = getSession()
				.createQuery(
						"delete from HHSurveyAnswers HHSA where HHSA.houseHoldId = :houseHoldsId");	
		
		
		query.setParameter("houseHoldsId", houseHoldsId);
		
		return query.executeUpdate();
		
	}
	
	public List<Object[]> getQuestionWiseSummary(Long questionId,Long constituencyId){
		Query query = getSession().createQuery(" select model2.houseHolds.houseHoldId," + // HOUSEHOLD ID -- 0
				" model.hhOptions.optionsId," + // 1 -- OPTION ID
				"  model.hhOptions.options," + // 2 -- OPTION
				"  model.hhSurveyQuestion.hhoptionType.optionTypeId," +// 3 -- OPTION TYPE
				"  model.remarks," + // 4 -- COMMENT
				"  model.hhSurveyQuestion.surveyQuestionId," + // 5 -- QUESTION ID
				"  model.hhSurveyQuestion.question" + // 6 -- QUESTION
				"  from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2" +
				"  where" +
				"  model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				"  and model2.hhLeader.id = model1.hhLeader.id" +
				"  and model1.constituency.constituencyId = :constituencyId" +
				"  and model1.hhLeader.is_active = 'YES'" +
				"  and model2.isDelete = 'FALSE'" +
				"  and model.hhSurveyQuestion.surveyQuestionId =:questionId" +
				"  group by model2.houseHolds.houseHoldId");
		
		query.setParameter("questionId", questionId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	
}
