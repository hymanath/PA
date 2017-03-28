package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.utils.IConstants;


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
				" and model2.voterFamilyRelation.voterFamilyRelationId = 1" +
				"  and model.hhSurveyQuestion.surveyQuestionId =:questionId" +
				"  group by model2.houseHolds.houseHoldId");
		
		query.setParameter("questionId", questionId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getQuestionWiseSummaryCount(Long questionId,Long constituencyId){
		Query query = getSession().createQuery(" select count(distinct model.hhSurveyAnswerId)," +//0 -- COUNT
				" model.hhOptions.optionsId," +//1 -- OPTION ID
				" model.hhOptions.options," +//2 -- OPTION
				" model.hhSurveyQuestion.surveyQuestionId," + // 3 -- QUESTION ID
				" model.hhSurveyQuestion.question" + // 4 -- QUESTION
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				"  and model2.hhLeader.id = model1.hhLeader.id  " +
				"  and model1.constituency.constituencyId = :constituencyId" +
				"  and model1.hhLeader.is_active = 'YES'" +
				"  and model2.isDelete = 'FALSE' " +
				" and model2.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.hhSurveyQuestion.surveyQuestionId =:questionId" +
				" group by model.hhOptions.optionsId");
		
		query.setParameter("questionId", questionId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getQuestionWiseSummaryCountByPanchayat(Long questionId,Long constituencyId){
		Query query = getSession().createQuery(" select count(distinct model.hhSurveyAnswerId)," +//0 -- COUNT
				" model.hhOptions.optionsId," +//1 -- OPTION ID
				" model.hhOptions.options," +//2 -- OPTION
				" model.hhSurveyQuestion.surveyQuestionId," + // 3 -- QUESTION ID
				" model.hhSurveyQuestion.question," +// 4 -- QUESTION
				" model.houseHold.panchayat.panchayatId," +// 5 -- PANCHAYAT ID
				" model.houseHold.panchayat.panchayatName" +  // 6 -- PANCHAYAT NAME
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				"  and model2.hhLeader.id = model1.hhLeader.id  " +
				"  and model1.constituency.constituencyId = :constituencyId" +
				" and model2.voterFamilyRelation.voterFamilyRelationId = 1" +
				"  and model2.isDelete = 'FALSE' " +
				"  and model1.hhLeader.is_active = 'YES'" +
				" and model.hhSurveyQuestion.surveyQuestionId =:questionId" +
				" group by model.houseHold.panchayat.panchayatId, model.hhOptions.optionsId");
		
		query.setParameter("questionId", questionId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getHouseHoldsOfPanchayatWithOption(Long optionId,Long panchayatId){
		Query query = getSession().createQuery(" select distinct model2.houseHoldVoterId," + // 0 -- HOUSEHOLD VOTER ID
				" model2.houseHolds.houseHoldId," +//1 -- HOUSEHOLD ID
				" model2.houseHolds.houseNo," +//2 -- HOUSE NO
				" model2.voter.name," + // 3 -- VOTER NAME(FAMILY HEAD)
				" model2.voter.voterIDCardNo," +// 4 -- VOTER CARD NO
				" model.houseHold.panchayat.panchayatId," +// 5 -- PANCHAYAT ID
				" model.houseHold.panchayat.panchayatName," +  // 6 -- PANCHAYAT NAME
				" model.hhOptions.optionsId," +//7 -- OPTION ID
				" model.hhOptions.options" +//8 -- OPTION
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				"  and model2.hhLeader.id = model1.hhLeader.id  " +
				"  and model.houseHold.panchayat.panchayatId = :panchayatId" +
				"  and model1.hhLeader.is_active = 'YES'" +
				"  and model2.isDelete = 'FALSE' " +
				" and model2.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.hhOptions.optionsId =:optionId");
		
		query.setParameter("optionId", optionId);
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	public List<Object[]> getHouseHoldsOfPanchayatsWithOption(Long optionId,List<Long> panchayatIds){
		Query query = getSession().createQuery(" select distinct model2.houseHoldVoterId," + // 0 -- HOUSEHOLD VOTER ID
				" model2.houseHolds.houseHoldId," +//1 -- HOUSEHOLD ID
				" model2.houseHolds.houseNo," +//2 -- HOUSE NO
				" model2.voter.name," + // 3 -- VOTER NAME(FAMILY HEAD)
				" model2.voter.voterIDCardNo," +// 4 -- VOTER CARD NO
				" model.houseHold.panchayat.panchayatId," +// 5 -- PANCHAYAT ID
				" model.houseHold.panchayat.panchayatName," +  // 6 -- PANCHAYAT NAME
				" model.hhOptions.optionsId, " +//7 -- OPTION ID
				" model2.voter.voterId, " +
				" model2.ownerMobileNo," +
				" model2.voter.relativeName, " +
				" model2.voter.relationshipType, " +
				" model.houseHold.panchayat.tehsil.tehsilId," +// 12 -- TEHSIL ID
				" model.houseHold.panchayat.tehsil.tehsilName" +  // 13 -- TEHSIL NAME
				//" model.hhOptions.options" +//8 -- OPTION
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				" and model2.hhLeader.id = model1.hhLeader.id  " +
				" and model.houseHold.panchayat.panchayatId in( :panchayatIds)" +
				" and model1.hhLeader.is_active = 'YES'" +
				" and model2.isDelete = 'FALSE' " +
				" and model2.voterFamilyRelation.voterFamilyRelationId = 1" +
				" and model.hhOptions.optionsId =:optionId" +
				" order by model.houseHold.panchayat.tehsil.tehsilName,model.houseHold.panchayat.panchayatName ");
		
		query.setParameter("optionId", optionId);
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();
	}
	
	public List<Object[]> getVoterAndNonVotersUnderOption(Long optionId,Long panchayatId){
		Query query = getSession().createQuery(" select "+
				" model2.houseHolds.houseHoldId," +//1 -- HOUSEHOLD ID
				" count(distinct model2.voter.voterId)" +//2 -- HOUSE NO
				//" count(model2.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId)" + // 3 -- VOTER NAME(FAMILY HEAD)
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				" and model2.hhLeader.id = model1.hhLeader.id  " +
				" and model.houseHold.panchayat.panchayatId = :panchayatId" +
				" and model1.hhLeader.is_active = 'YES'" +
				" and model2.isDelete = 'FALSE' " +
				" and model.hhOptions.optionsId =:optionId" +
				" and model2.voter.voterId is not null" +
				" group by model2.houseHolds.houseHoldId ");
		
		query.setParameter("optionId", optionId);
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	public List<Object[]> getVoterAndNonVotersUnderOption1(Long optionId,Long panchayatId){
		Query query = getSession().createQuery(" select "+
				" model2.houseHolds.houseHoldId," +//1 -- HOUSEHOLD ID
				//" count(model2.voter.voterId)," +//2 -- HOUSE NO
				" count(distinct model2.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId)" + // -- VOTER NAME(FAMILY HEAD)
				" from HHSurveyAnswers model,HHBoothLeader model1,HouseHoldVoter model2 " +
				" where " +
				" model.houseHold.houseHoldId = model2.houseHolds.houseHoldId " +
				" and model2.hhLeader.id = model1.hhLeader.id  " +
				" and model.houseHold.panchayat.panchayatId = :panchayatId" +
				" and model1.hhLeader.is_active = 'YES'" +
				" and model2.isDelete = 'FALSE' " +
				" and model.hhOptions.optionsId =:optionId" +
				" and model2.houseHoldsFamilyDetails.houseHoldsFamilyDetailsId is not null" +
				" group by model2.houseHolds.houseHoldId ");
		
		query.setParameter("optionId", optionId);
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
}
