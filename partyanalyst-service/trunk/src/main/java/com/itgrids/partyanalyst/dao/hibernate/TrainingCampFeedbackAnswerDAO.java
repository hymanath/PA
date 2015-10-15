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
				"from TrainingCampFeedbackAnswer model where model.tdpCadreId=:cadreId ");
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	 
}
