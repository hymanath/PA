package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampFeedbackAnswerDAO;
import com.itgrids.partyanalyst.model.TrainingCampFeedbackAnswer;
import com.itgrids.partyanalyst.model.TrainingCampFeedbackCategory;

public class TrainingCampFeedbackAnswerDAO extends GenericDaoHibernate<TrainingCampFeedbackAnswer, Long> implements ITrainingCampFeedbackAnswerDAO{

	public TrainingCampFeedbackAnswerDAO() {
		super(TrainingCampFeedbackAnswer.class);
		// TODO Auto-generated constructor stub
	}	 
	 
	public List<Object[]> getFeedbackDetailsForCadre(Long cadreId){
		Query query = getSession().createQuery(" select model.trainingCampFeedbackCategory.feedbackCategoryId, model.answer,model.trainingCampFeedbackCategoryId " +
				"from TrainingCampFeedbackAnswer model where model.tdpCadreId=:cadreId and model.isDeleted='N' ");
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	
	public List<Object[]> getAnswresCountForCadreWise(List<Long> tdpCadreIds){
		Query query = getSession().createQuery(" select count(distinct model.trainingCampFeedbackCategoryId),model.tdpCadreId " +
				" from TrainingCampFeedbackAnswer model where model.tdpCadreId in (:tdpCadreIds) and model.isDeleted='N' group by model.tdpCadreId ");
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	
	public List<Object[]> getFeedbackCategoryCountsCenterWise(Long programId){
		
		/*select tc.training_camp_id,tc.camp_name,fc.feedback_category_id,fc.category_name,count(distinct tcfc.training_camp_feedback_category_id)
		 from training_camp_feedback_answer tcfa,training_camp_feedback_category tcfc,feedback_category fc,training_camp tc
		 where 
		 tcfa.training_camp_feedback_category_id=tcfc.training_camp_feedback_category_id and
		 tcfc.training_camp_id=tc.training_camp_id and
		 tcfc.feedback_category_id=fc.feedback_category_id and
		 tcfc.training_camp_program_id=1 and tcfc.is_deleted='N'
		 group by tc.training_camp_id,fc.feedback_category_id;*/
		
		Query query = getSession().createQuery(" select model.trainingCampFeedbackCategory.trainingCamp.trainingCampId," +
				"  model.trainingCampFeedbackCategory.trainingCamp.campName,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
				" model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackCategoryId ) " +
				"  from " +
				" TrainingCampFeedbackAnswer model" +
				" where " +
				" model.trainingCampFeedbackCategory.isDeleted='N' and model.trainingCampFeedbackCategory.trainingCampProgramId=:programId " +
				" group by " +
				"  model.trainingCampFeedbackCategory.trainingCamp.trainingCampId,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId ");
		query.setParameter("programId", programId);
		return query.list();
	}
	
	public List<Object[]> getFeedbackDetailsOfEachDistrictAndConstituencyWise(List<Long> districtIds,List<Long> constituencIds, List<Long> categoryIds,Long programId,String type){
		
		StringBuilder str = new StringBuilder();	
		
		if(type !=null && type.equalsIgnoreCase("districts")){
			str.append(" select dist.districtId,dist.districtName,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
					"  model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackCategoryId) " +
					" from  TrainingCampFeedbackAnswer tcfa,TrainingCampFeedbackCategory tcfc,TdpCadre tc,District dist " +
					" where model.tdpCadreId = tc.tdpCadreId" +
					" and tc.userAddress.district.districtId=dist.districtId  ");
		}
		else if(type !=null && type.equalsIgnoreCase("constituecys")){
			str.append(" select dist.districtId,dist.districtName,const.constituencyId,const.name" +
				"  ,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
					"  model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackCategoryId) " +
				
					" from  TrainingCampFeedbackAnswer model,TrainingCampFeedbackCategory tcfc,TdpCadre tc,District dist,Constituency const " +
					
					" where model.tdpCadreId = tc.tdpCadreId " +
					" and tc.userAddress.district.districtId=dist.districtId " +
					" and tc.userAddress.constituency.constituencyId = const.constituencyId  ");
		}
		
		str.append(" and model.trainingCampFeedbackCategory.isDeleted='N' ");
		
		if(districtIds !=null && districtIds.size()>0){
			str.append(" and dist.districtId in (:districtIds) ");
		}
		if(constituencIds !=null && constituencIds.size()>0){
			str.append(" and const.constituencyId in (:constituencIds) ");
		}
		if(categoryIds != null && categoryIds.size() > 0){
			str.append(" and model.trainingCampFeedbackCategory.feedbackCategoryId in (:categoryIds) ");
		}
		if(programId !=null && programId>0){
			str.append(" and  model.trainingCampFeedbackCategory.trainingCampProgramId = :programId  ");
		}
		
		if(type !=null && type.equalsIgnoreCase("districts")){
			str.append(" group by dist.districtId,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId order by dist.districtName," +
					" model.trainingCampFeedbackCategory.feedbackCategory.categoryName ");
		}else if(type !=null && type.equalsIgnoreCase("constituecys")){
			str.append(" group by dist.districtId,const.constituencyId,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId order by dist.districtName,const.name," +
					" model.trainingCampFeedbackCategory.feedbackCategory.categoryName ");
		}
				
		Query query = getSession().createQuery(str.toString());
		
		if(districtIds !=null && districtIds.size()>0){
			query.setParameterList("districtIds", districtIds);
		}
		if(constituencIds !=null && constituencIds.size()>0){
			query.setParameterList("constituencIds", constituencIds);
		}
		if(categoryIds != null && categoryIds.size() > 0){
			query.setParameterList("categoryIds", categoryIds);
		}
		
		if(programId !=null && programId>0){
			query.setParameter("programId", programId);
		}
		
		return query.list();
	}
	
	public List<Object[]> getFeedbackDetailsOfCadre(Long locationId,Long programId,String type){
		
		/*Minimum Query
		 *  select tc.tdp_cadre_id,fc.feedback_category_id,tcfa.answer
		 from  
		 training_camp_feedback_answer tcfa,training_camp_feedback_category tcfc,tdp_cadre tc,feedback_category fc
		 where 
		 tcfa.training_camp_feedback_category_id = tcfc.training_camp_feedback_category_id
		 and tcfa.cadre_id = tc.tdp_cadre_id
		and tcfc.feedback_category_id=fc.feedback_category_id
		group by tc.tdp_cadre_id,fc.feedback_category_id;*/
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCadre.mobileNo," +
				" model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId,model.trainingCampFeedbackCategory.feedbackCategory.categoryName," +
				" model.answer,model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name,model.tdpCadre.image " +
				" from TrainingCampFeedbackAnswer model ");
		
		str.append(" where model.trainingCampFeedbackCategory.isDeleted='N' ");
		
		if(type !=null && type.equalsIgnoreCase("district")){
			str.append(" and model.tdpCadre.userAddress.district.districtId = :locationId ");
		}else if(type !=null && type.equalsIgnoreCase("constituecy")){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationId ");
		}
		
		if(programId !=null && programId>0){
			str.append(" and  model.trainingCampFeedbackCategory.trainingCampProgramId = :programId  ");
		}
		
		str.append(" group by model.tdpCadre.tdpCadreId,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId " +
				" order by  model.tdpCadre.firstname ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("locationId", locationId);
		query.setParameter("programId", programId);
		
		return query.list();		
	}
	
	
	 
}
