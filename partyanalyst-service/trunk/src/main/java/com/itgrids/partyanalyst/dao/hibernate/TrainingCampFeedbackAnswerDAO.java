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
	 
}
