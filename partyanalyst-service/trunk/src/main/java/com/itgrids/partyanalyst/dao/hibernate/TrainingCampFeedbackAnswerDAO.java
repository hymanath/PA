package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampFeedbackAnswerDAO;
import com.itgrids.partyanalyst.model.TrainingCampFeedbackAnswer;

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
	
	public List<Object[]> getFeedbackCategoryCountsCenterWise(Long programId,Date fromDate,Date toDate){
		
		/*select tc.training_camp_id,tc.camp_name,fc.feedback_category_id,fc.category_name,count(distinct tcfc.training_camp_feedback_category_id)
		 from training_camp_feedback_answer tcfa,training_camp_feedback_category tcfc,feedback_category fc,training_camp tc
		 where 
		 tcfa.training_camp_feedback_category_id=tcfc.training_camp_feedback_category_id and
		 tcfc.training_camp_id=tc.training_camp_id and
		 tcfc.feedback_category_id=fc.feedback_category_id and
		 tcfc.training_camp_program_id=1 and tcfc.is_deleted='N'
		 group by tc.training_camp_id,fc.feedback_category_id;*/
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.trainingCampFeedbackCategory.trainingCamp.trainingCampId," +
				"  model.trainingCampFeedbackCategory.trainingCamp.campName,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
				" model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackAnswerId) " +
				"  from " +
				" TrainingCampFeedbackAnswer model" +
				" where " +
				" model.trainingCampFeedbackCategory.isDeleted='N' and model.trainingCampFeedbackCategory.trainingCampProgramId=:programId " +
				" and model.tdpCadre.isDeleted='N' ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.updatedTime) between :fromDate and :toDate ");
		}
		
		sb.append(" group by " +
				"  model.trainingCampFeedbackCategory.trainingCamp.trainingCampId,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("programId", programId);
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}

		return query.list();
	}
	
	public List<Object[]> getFeedbackDetailsOfEachDistrictAndConstituencyWise(List<Long> districtIds,List<Long> constituencIds, List<Long> categoryIds,Long programId,String type,Date fromDate,Date toDate){
		
		StringBuilder str = new StringBuilder();	
		
		if(type !=null && type.equalsIgnoreCase("districts")){
			str.append(" select dist.districtId,dist.districtName,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
					"  model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackAnswerId) " +
					" from  TrainingCampFeedbackAnswer tcfa,TrainingCampFeedbackCategory tcfc,TdpCadre tc,District dist " +
					" where model.tdpCadreId = tc.tdpCadreId" +
					" and tc.userAddress.district.districtId=dist.districtId  ");
		}
		else if(type !=null && type.equalsIgnoreCase("constituecys")){
			str.append(" select dist.districtId,dist.districtName,const.constituencyId,const.name" +
				"  ,model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId," +
					"  model.trainingCampFeedbackCategory.feedbackCategory.categoryName,count(distinct model.trainingCampFeedbackAnswerId) " +
				
					" from  TrainingCampFeedbackAnswer model,TrainingCampFeedbackCategory tcfc,TdpCadre tc,District dist,Constituency const " +
					
					" where model.tdpCadreId = tc.tdpCadreId " +
					" and tc.userAddress.district.districtId=dist.districtId " +
					" and tc.userAddress.constituency.constituencyId = const.constituencyId  ");
		}
		
		str.append(" and model.trainingCampFeedbackCategory.isDeleted='N' and model.tdpCadre.isDeleted='N' ");
		
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
		if(fromDate != null && toDate != null){
			str.append(" and date(model.updatedTime) between :fromDate and :toDate ");
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
		
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getFeedbackDetailsOfCadre(Long locationId,Long programId,String type,Date fromDate,Date toDate,Long categoryId){
		
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
		
		str.append(" where model.trainingCampFeedbackCategory.isDeleted='N' and model.tdpCadre.isDeleted='N' ");
		
		if(categoryId!=null && categoryId>0l){
			str.append(" and model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId=:categoryId ");
		}
		
		if(type !=null && type.equalsIgnoreCase("district")){
			if(locationId !=null && locationId>0)
			str.append(" and model.tdpCadre.userAddress.district.districtId = :locationId ");
		}else if(type !=null && type.equalsIgnoreCase("constituecy")){
			if(locationId !=null && locationId>0)
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationId ");
		}else if(type !=null && type.equalsIgnoreCase("center")){
			if(locationId !=null && locationId>0)
			str.append(" and model.trainingCampFeedbackCategory.trainingCampId = :locationId ");
		}
		
		if(programId !=null && programId>0){
			str.append(" and  model.trainingCampFeedbackCategory.trainingCampProgramId = :programId  ");
		}
		
		if(fromDate != null && toDate != null){
			str.append(" and date(model.updatedTime) between :fromDate and :toDate ");
		}
		
		str.append(" " +
				" order by  model.tdpCadre.firstname ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(locationId !=null && locationId>0){
			query.setParameter("locationId", locationId);
		}
		if(programId !=null && programId>0){
			query.setParameter("programId", programId);
		}
		
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(categoryId!=null && categoryId>0l){
			query.setParameter("categoryId", categoryId);
		}
		return query.list();		
	}
	
	 public List<Object[]> getFeedBackMembersCountProgramWise(Date startDate,Date endDate){
		 
		 StringBuilder str = new StringBuilder();
		 
		 str.append(" select model.trainingCampFeedbackCategory.trainingCampProgram.trainingCampProgramId , " +
			 		" model.trainingCampFeedbackCategory.trainingCampProgram.programName," +
			 		" model.trainingCampFeedbackCategory.trainingCamp.trainingCampId ," +
			 		" model.trainingCampFeedbackCategory.trainingCamp.campName,count(distinct model.trainingCampFeedbackAnswerId) " +
			 		" from " +
			 		" TrainingCampFeedbackAnswer model" +
			 		" where model.trainingCampFeedbackCategory.isDeleted='N' and model.tdpCadre.isDeleted='N' ");
		 
		 if(startDate !=null && endDate !=null){
			 str.append(" and date(model.updatedTime) between :startDate and :endDate  ");
		 }
		 
		 str.append("group by model.trainingCampFeedbackCategory.trainingCampProgram.trainingCampProgramId," +
			 		" model.trainingCampFeedbackCategory.trainingCamp.trainingCampId" +
			 		" order by model.trainingCampFeedbackCategory.trainingCampProgram.programName," +
			 		" model.trainingCampFeedbackCategory.trainingCamp.campName ");
		 
		 Query query = getSession().createQuery(str.toString());
		 
		 if(startDate !=null && endDate !=null){
			 query.setParameter("startDate", startDate);
			 query.setParameter("endDate", endDate);
		 }
		 
		 return query.list();
	 }
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseQuestionsAnswersDetails(Long locationId,String location)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId,model.trainingCampFeedbackCategory.feedbackCategory.categoryName,model.answer,   ");
		str.append(" model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.lastname,model.tdpCadre.userAddress.district.districtName, " +
				" model.tdpCadre.userAddress.constituency.name,model.tdpCadre.userAddress.constituencyId,model.tdpCadre.memberShipNo ");
		str.append(" from TrainingCampFeedbackAnswer model where model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(location != null && locationId > 0 &&  location.equalsIgnoreCase("district")){
			str.append(" and model.tdpCadre.userAddress.district.districtId = :locationId  ");
		}
		else if(location != null && locationId > 0 && location.equalsIgnoreCase("constituency")){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationId  ");
		}
		
		str.append(" order by model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(locationId > 0)
		 query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBasicLocationDetails(Long locationId,String location)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.tdpCadre.userAddress.district.districtName,model.tdpCadre.userAddress.constituency.name,model.trainingCampFeedbackCategory.trainingCampBatch.trainingCampBatchId," +
				" model.trainingCampFeedbackCategory.trainingCampBatch.trainingCampBatchCode  ");
				
		str.append(" from TrainingCampFeedbackAnswer model where model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(location != null && locationId > 0 &&  location.equalsIgnoreCase("district")){
			str.append(" and model.tdpCadre.userAddress.district.districtId = :locationId  ");
		}
		else if(location != null && locationId > 0 && location.equalsIgnoreCase("constituency")){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :locationId  ");
		}
		
		str.append(" order by model.trainingCampFeedbackCategory.feedbackCategory.feedbackCategoryId ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(locationId > 0)
		 query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	 
}
