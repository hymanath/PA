package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackHealthCardDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackHealthCard;

public class TrainingCampCadreFeedbackHealthCardDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackHealthCard, Long> implements ITrainingCampCadreFeedbackHealthCardDAO{

	public TrainingCampCadreFeedbackHealthCardDAO() {
		super(TrainingCampCadreFeedbackHealthCard.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> getHealthCardAttachments(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select model.healthCardAttachment,model.trainingCampCadreFeedbackHealthCardId from  TrainingCampCadreFeedbackHealthCard model" +
				" where model.trainingCampCadreFeedbackDetails.tdpCadreId =:tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
		
	}
	
}
