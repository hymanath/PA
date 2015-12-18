package com.itgrids.partyanalyst.dao;

import java.util.List;


import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampFeedbackCategory;

public interface ITrainingCampFeedbackCategoryDAO extends GenericDao<TrainingCampFeedbackCategory, Long>{
	 public List<Object[]> getFeedBackCategories(Long programId,Long campId,Long batchId);
	 public List<Object[]> getCategoriesByIds(Long programId,Long campId,Long batchId,List<Long> catgoryIds);
	 public List<Object[]> getOptionsForQuestions(Long programId,Long campId,Long batchId,List<Long> catgoryIds);
	 public List<Object[]> getParentAndChildCategoryIds(List<Long> fbcatIds);
	 public Long getTrainingcampFeedbackcategoryId(Long fbcatId);
	 public List<Object[]> getCategoriesHavingParent(String type);
}
