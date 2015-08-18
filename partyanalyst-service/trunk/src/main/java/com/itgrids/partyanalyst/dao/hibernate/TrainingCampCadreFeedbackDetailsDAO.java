package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;

public class TrainingCampCadreFeedbackDetailsDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDetails, Long> implements ITrainingCampCadreFeedbackDetailsDAO{

	public TrainingCampCadreFeedbackDetailsDAO() {
		super(TrainingCampCadreFeedbackDetails.class);
	}
	
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select model.cadreLeadershipLevelId,model.cadreComminicationSkillsStatusId,model.cadreLeadershipSkillsStatusId,model.cadreHealthStatusId,model.remarks " +
		" from TrainingCampCadreFeedbackDetails model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("trainingCampBatchId", batchId);
		return (Object[])query.uniqueResult();
	}

}
