package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampCadreFeedbackDetailsDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDetails, Long> implements ITrainingCampCadreFeedbackDetailsDAO{

	public TrainingCampCadreFeedbackDetailsDAO() {
		super(TrainingCampCadreFeedbackDetails.class);
	}
	
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select model.cadreLeadershipLevelId,model.cadreComminicationSkillsStatusId,model.cadreLeadershipSkillsStatusId,model.cadreHealthStatusId,model.remarks," +
		"        model.smartPhoneExist,model.watsappUsing,model.watsappShare,model.facebookUsing " +
		" from TrainingCampCadreFeedbackDetails model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId " +
		" and model.trainingCampBatch.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("trainingCampBatchId", batchId);
		return (Object[])query.uniqueResult();
	}
    public Long  checkFeedBackForCadreBycadreAndBatch(Long tdpCadreId,Long batchId){
    	Query query=getSession().createQuery("" +
    			" select model.trainingCampCadreFeedbackDetailsId" +
    			" from TrainingCampCadreFeedbackDetails model " +
    			" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId " +
    			" and model.trainingCampBatch.attendeeTypeId=1 ");
        query.setParameter("tdpCadreId", tdpCadreId);
    	query.setParameter("trainingCampBatchId", batchId);
    	return (Long)query.uniqueResult();
    }
   
    public List<Object[]> getFeedBackDetailsForBatches(List<Long> trainingCampBatchIds){
    	
    	Query query=getSession().createQuery("" +
    	" select  model.trainingCampCadreFeedbackDetailsId," +
    	"         ll.cadreLeadershipLevelId,ll.leadershipLevel," +
    	"         cs.cadreComminicationSkillsStatusId,cs.status," +
    	"         ls.cadreLeadershipSkillsStatusId,ls.status," +
    	"         hs.cadreHealthStatusId,hs.status " +
    	" from TrainingCampCadreFeedbackDetails model left join model.cadreLeadershipLevel ll " +
    	"      left join model.cadreComminicationSkillsStatus cs" +
    	"      left join model.cadreLeadershipSkillsStatus ls" +
    	"      left join model.cadreHealthStatus hs" +
    	" where model.trainingCampBatchId in (:batches) and model.trainingCampBatch.attendeeTypeId=1 " +
    	" order by  model.trainingCampCadreFeedbackDetailsId");
    	
    	query.setParameterList("batches",trainingCampBatchIds);
    	return query.list();
    }
    public List<Object[]> getattendedcount(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate,String type){
    	
    	Query query=getSession().createQuery(queryString);
    	Date currDate=new DateUtilService().getCurrentDateAndTime();
    	
    	if(!type.equalsIgnoreCase("all")){
    		query.setParameter("currDate", currDate);
    	}
    	
    	if(fromDate!=null && toDate!=null){
	    	query.setParameter("fromDate", fromDate);
	    	query.setParameter("toDate", toDate);
    	}
    	if(batchId==null && campId==null && programId!=null){
    		query.setParameter("programId",programId);
    		
		}else if(batchId==null && campId!=null){
			query.setParameter("campId",campId);
			if(programId!=null)
				query.setParameter("programId",programId);
			
		}else if(batchId!=null){
			
			if(programId!=null)
				query.setParameter("programId",programId);
			if(campId!=null)
				query.setParameter("campId",campId);
			
			query.setParameter("batchId",batchId);
			
		}
    	return query.list();
    }
 public Long getattendedcount1(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate,String type){
    	
    	Query query=getSession().createQuery(queryString);
    	
    	Date currDate=new DateUtilService().getCurrentDateAndTime();
    	
    	if(!type.equalsIgnoreCase("all")){
    		query.setParameter("currDate", currDate);
    	}
    	if(fromDate!=null && toDate!=null){
	    	query.setParameter("fromDate", fromDate);
	    	query.setParameter("toDate", toDate);
    	}
    	if(batchId==null && campId==null && programId!=null){
    		query.setParameter("programId",programId);
    		
		}else if(batchId==null && campId!=null){
			query.setParameter("campId",campId);
			if(programId!=null)
				query.setParameter("programId",programId);
			
		}else if(batchId!=null){
			
			if(programId!=null)
				query.setParameter("programId",programId);
			if(campId!=null)
				query.setParameter("campId",campId);
			
			query.setParameter("batchId",batchId);
			
		}
    	return (Long)query.uniqueResult();
    }
 
 public List<Object[]> getFeedBackMembersCountProgramWise(){
	 
	 Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId , " +
	 		" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName," +
	 		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId," +
	 		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName,count(distinct model.tdpCadre.tdpCadreId) " +
	 		" from " +
	 		" TrainingCampCadreFeedbackDetails model " +
	 		" where model.trainingCampBatch.isCancelled = 'false' and model.trainingCampBatch.attendeeTypeId=1 " +
	 		" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
	 		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	 
	 return query.list();
 }
    
  public List<Object[]> getTrainingFeedbackDetails(List<Long> tdpCadreIds){
	  Query query = getSession().createQuery(" select  model.tdpCadre.tdpCadreId,model.cadreLeadershipSkillsStatus.status " +
	  		" from TrainingCampCadreFeedbackDetails model where model.tdpCadre.tdpCadreId in(:tdpCadreIds) ");
	  query.setParameterList("tdpCadreIds", tdpCadreIds);
	  return query.list();
  }
}
