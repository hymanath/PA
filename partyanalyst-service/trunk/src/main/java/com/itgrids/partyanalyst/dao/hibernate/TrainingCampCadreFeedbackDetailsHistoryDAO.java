package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsHistoryDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetailsHistory;

public class TrainingCampCadreFeedbackDetailsHistoryDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDetailsHistory,Long> implements ITrainingCampCadreFeedbackDetailsHistoryDAO{
	
	public TrainingCampCadreFeedbackDetailsHistoryDAO() {
		super(TrainingCampCadreFeedbackDetailsHistory.class);
	}
}
