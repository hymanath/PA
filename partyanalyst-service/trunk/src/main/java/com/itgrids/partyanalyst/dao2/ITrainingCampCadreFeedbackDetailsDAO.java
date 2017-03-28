package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;

public interface ITrainingCampCadreFeedbackDetailsDAO extends GenericDao<TrainingCampCadreFeedbackDetails, Long>{
   
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId);
	
	public Long  checkFeedBackForCadreBycadreAndBatch(Long tdpCadreId,Long batchId);
	
	public List<Object[]> getFeedBackDetailsForBatches(List<Long> trainingCampBatchIds);
	  
	public List<Object[]> getattendedcount(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate,String callFrom);
	public Long getattendedcount1(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate,String callFrom);
	public List<Object[]> getFeedBackMembersCountProgramWise();
	
}
