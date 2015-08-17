package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;

public class TrainingCampCadreFeedbackDetailsDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDetails, Long> implements ITrainingCampCadreFeedbackDetailsDAO{

	public TrainingCampCadreFeedbackDetailsDAO() {
		super(TrainingCampCadreFeedbackDetails.class);
		
	}

}
