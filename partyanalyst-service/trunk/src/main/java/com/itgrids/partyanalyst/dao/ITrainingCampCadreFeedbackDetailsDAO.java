package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;

public interface ITrainingCampCadreFeedbackDetailsDAO extends GenericDao<TrainingCampCadreFeedbackDetails, Long>{
   
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId,Long enrollmentYearId);
	
	public Long  checkFeedBackForCadreBycadreAndBatch(Long tdpCadreId,Long batchId);
	
	public List<Object[]> getFeedBackDetailsForBatches(List<Long> trainingCampBatchIds);
	  
	public List<Object[]> getattendedcount(String queryString,List<Long> programId,Long campId,Long batchId,Date fromDate,Date toDate,String callFrom,List<Long> enrollmentYrIds);
	public Long getattendedcount1(String queryString,List<Long> programId,Long campId,Long batchId,Date fromDate,Date toDate,String callFrom,List<Long> enrollmentYrIds);
	public List<Object[]> getFeedBackMembersCountProgramWise();
	 public List<Object[]> getTrainingFeedbackDetails(List<Long> tdpCadreIds);
	 public List<Long> getattendedcountClickDetails(String queryString,List<Long> programId,Long campId,Long batchId,Date fromDate,Date toDate,String callFrom,List<Long> enrollmentYrIds,String skillType,Long statusId);
	
	 public List<Object[]> getTrainingCampFeedBAckDeatilesByTdpCadreId(Long tdpCadreId);
	 public List<Object[]> getCommunicationFeedbackOnLeaders(Long userAccessLevelId,List<Long> userAccessLevelValues,List<Long> trainingProgramIds,Long traingCampEnrollmentYearId,List<Long> trainingCampLevelIds,Long groupType,Long questionType);
}
