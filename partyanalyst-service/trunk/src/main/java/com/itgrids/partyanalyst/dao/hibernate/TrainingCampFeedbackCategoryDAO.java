package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampFeedbackCategoryDAO;
import com.itgrids.partyanalyst.model.TrainingCampFeedbackCategory;

public class TrainingCampFeedbackCategoryDAO extends GenericDaoHibernate<TrainingCampFeedbackCategory, Long> implements ITrainingCampFeedbackCategoryDAO{

	public TrainingCampFeedbackCategoryDAO() {
		super(TrainingCampFeedbackCategory.class);
		// TODO Auto-generated constructor stub
	}

	
	 public List<Object[]> getFeedBackCategories(Long programId,Long campId,Long batchId)
	 {
		 StringBuilder str = new StringBuilder();
		str.append("select distinct model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName from TrainingCampFeedbackCategory model" +
		 		" where model.parentFeedbackCategoryId is null and model.isDeleted ='N' and model.trainingCampBatch.attendeeTypeId=1 ");
		if(programId > 0)
			str.append(" and model.trainingCampProgramId = :programId");	
		if(campId > 0)
			str.append(" and model.trainingCampId = :campId");	
		if(batchId > 0)
			str.append(" and model.trainingCampBatchId = :batchId");	
		 Query query = getSession().createQuery(str.toString());
			if(programId > 0)
				query.setParameter("programId", programId);
			if(campId > 0)
				query.setParameter("campId", campId);
			
			if(batchId > 0)
				query.setParameter("batchId", batchId);
			
			
		return query.list();
	 }
	 
	 public List<Object[]> getCategoriesByIds(Long programId,Long campId,Long batchId,List<Long> catgoryIds)
	 {
		 StringBuilder str = new StringBuilder();
		str.append("select distinct model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName,model.isSubCategoryExist,model.trainingCampFeedbackCategoryId from TrainingCampFeedbackCategory model" +
		 		" where model.parentFeedbackCategoryId is null and model.isDeleted ='N' " +
		 		" and model.trainingCampBatch.attendeeTypeId=1 ");
		if(programId > 0)
			str.append(" and model.trainingCampProgramId = :programId");	
		if(campId > 0)
			str.append(" and model.trainingCampId = :campId");	
		if(batchId > 0)
			str.append(" and model.trainingCampBatchId = :batchId");
		if(catgoryIds != null && catgoryIds.size() > 0)
			str.append(" and model.feedbackCategory.feedbackCategoryId in(:catgoryIds)");
		 Query query = getSession().createQuery(str.toString());
		if(programId > 0)
			query.setParameter("programId", programId);
		if(campId > 0)
			query.setParameter("campId", campId);
		
		if(batchId > 0)
			query.setParameter("batchId", batchId);
		if(catgoryIds != null && catgoryIds.size() > 0)
			query.setParameterList("catgoryIds", catgoryIds);
		return query.list();
	 }
	 
	 public List<Object[]> getOptionsForQuestions(Long programId,Long campId,Long batchId,List<Long> catgoryIds)
	 {
		 StringBuilder str = new StringBuilder();
		str.append("select model.parentFeedbackCategory.feedbackCategoryId,model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName,model.trainingCampFeedbackCategoryId from TrainingCampFeedbackCategory model" +
		 		" where model.isDeleted ='N' and model.trainingCampBatch.attendeeTypeId=1 ");
		if(programId > 0)
			str.append(" and model.trainingCampProgramId = :programId");	
		if(campId > 0)
			str.append(" and model.trainingCampId = :campId");	
		if(batchId > 0)
			str.append(" and model.trainingCampBatchId = :batchId");
		if(catgoryIds != null && catgoryIds.size() > 0)
			str.append(" and model.parentFeedbackCategory.feedbackCategoryId in(:catgoryIds) group by model.parentFeedbackCategory.feedbackCategoryId,model.feedbackCategory.feedbackCategoryId");
		 Query query = getSession().createQuery(str.toString());
		if(programId > 0)
			query.setParameter("programId", programId);
		if(campId > 0)
			query.setParameter("campId", campId);
		
		if(batchId > 0)
			query.setParameter("batchId", batchId);
		if(catgoryIds != null && catgoryIds.size() > 0)
			query.setParameterList("catgoryIds", catgoryIds);
		return query.list();
	 }
	 
	 public List<Object[]> getParentAndChildCategoryIds(List<Long> fbcatIds){
		 Query query = getSession().createQuery(" select model.feedbackCategoryId,model.parentFeedbackCategoryId,model.trainingCampFeedbackCategoryId " +
		 		" from TrainingCampFeedbackCategory model where model.trainingCampFeedbackCategoryId in (:fbcatIds)");
		 query.setParameterList("fbcatIds", fbcatIds);
		 return query.list();
	 }
	 public Long getTrainingcampFeedbackcategoryId(Long fbcatId){
		 Query query = getSession().createQuery(" select distinct model.trainingCampFeedbackCategoryId from " +
		 		"TrainingCampFeedbackCategory model where model.feedbackCategoryId in (:fbcatId)");
		 query.setParameter("fbcatId", fbcatId);
		 return (Long) query.uniqueResult();
	 }
	 
	 public List<Object[]> getCategoriesHavingParent(String type){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName ");
		 if(type.equalsIgnoreCase("parent")){	
			 sb.append(",model.parentFeedbackCategory.feedbackCategoryId,model.parentFeedbackCategory.categoryName ");
		 }
		 sb.append(" from TrainingCampFeedbackCategory model");
		 if(type.equalsIgnoreCase("parent")){
			 sb.append(" where model.parentFeedbackCategoryId is not null");
		 }
		 else if(type.equalsIgnoreCase("child")){
			 sb.append(" where model.parentFeedbackCategoryId is null and model.isSubCategoryExist='N' ");
		 }
		 sb.append(" and model.isDeleted='N' group by model.feedbackCategory.feedbackCategoryId ");
		 Query query = getSession().createQuery(sb.toString());
		 return query.list();
	 }
}
