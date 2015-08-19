package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;

public interface ITrainingCampCadreFeedbackDetailsDAO extends GenericDao<TrainingCampCadreFeedbackDetails, Long>{
   
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId);
	
	public Long  checkFeedBackForCadreBycadreAndBatch(Long tdpCadreId,Long batchId);
}
