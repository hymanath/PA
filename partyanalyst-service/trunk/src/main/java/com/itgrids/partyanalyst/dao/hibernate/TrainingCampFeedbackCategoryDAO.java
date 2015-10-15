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
		str.append("select model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName from TrainingCampFeedbackCategory model" +
		 		" where model.parentFeedbackCategoryId is null and model.isDeleted ='N' ");
		if(programId > 0)
			str.append(" and model.trainingCampProgramId = :programId");	
		if(campId > 0)
			str.append(" and model.trainingCampId = :campId");	
		if(batchId > 0)
			str.append("and model.trainingCampBatchId = :batchId");	
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
		str.append("select distinct model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName,model.isSubCategoryExist from TrainingCampFeedbackCategory model" +
		 		" where model.parentFeedbackCategoryId is null and model.isDeleted ='N' ");
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
		str.append("select model.parentFeedbackCategory.feedbackCategoryId,model.feedbackCategory.feedbackCategoryId,model.feedbackCategory.categoryName from TrainingCampFeedbackCategory model" +
		 		" where model.isDeleted ='N' ");
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
	 
	 
	 
}
