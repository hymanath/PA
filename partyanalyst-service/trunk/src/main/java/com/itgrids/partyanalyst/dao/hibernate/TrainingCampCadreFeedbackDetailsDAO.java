package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
		" select model.cadreLeadershipLevelId,model.cadreComminicationSkillsStatusId,model.cadreLeadershipSkillsStatusId,model.cadreHealthStatusId,model.remarks," +
		"        model.smartPhoneExist,model.watsappUsing,model.watsappShare,model.facebookUsing " +
		" from TrainingCampCadreFeedbackDetails model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("trainingCampBatchId", batchId);
		return (Object[])query.uniqueResult();
	}
    public Long  checkFeedBackForCadreBycadreAndBatch(Long tdpCadreId,Long batchId){
    	Query query=getSession().createQuery("" +
    			" select model.trainingCampCadreFeedbackDetailsId" +
    			" from TrainingCampCadreFeedbackDetails model " +
    			" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId");
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
    	" where model.trainingCampBatchId in (:batches) " +
    	" order by  model.trainingCampCadreFeedbackDetailsId");
    	
    	query.setParameterList("batches",trainingCampBatchIds);
    	return query.list();
    }
    public List<Object[]> getattendedcount(String queryString,Long programId,Long campId,Long batchId){
    	
    	Query query=getSession().createQuery(queryString);
    	
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
 public Long getattendedcount1(String queryString,Long programId,Long campId,Long batchId){
    	
    	Query query=getSession().createQuery(queryString);
    	
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
    
    
}
