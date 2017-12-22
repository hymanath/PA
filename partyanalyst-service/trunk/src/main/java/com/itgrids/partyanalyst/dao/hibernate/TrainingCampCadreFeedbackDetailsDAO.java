package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDetailsDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDetails;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampCadreFeedbackDetailsDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDetails, Long> implements ITrainingCampCadreFeedbackDetailsDAO{

	public TrainingCampCadreFeedbackDetailsDAO() {
		super(TrainingCampCadreFeedbackDetails.class);
	}
	
	public Object[] getFeedBackDetailsforCadre(Long tdpCadreId,Long batchId,Long enrollmentYearId){
		 StringBuilder queryStr = new StringBuilder();
			queryStr.append("");
			 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
				 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
			 
		Query query=getSession().createQuery("" +
		" select model.cadreLeadershipLevelId,model.cadreComminicationSkillsStatusId,model.cadreLeadershipSkillsStatusId,model.cadreHealthStatusId,model.remarks," +
		"        model.smartPhoneExist,model.watsappUsing,model.watsappShare,model.facebookUsing " +
		" from TrainingCampCadreFeedbackDetails model " +
		" where model.tdpCadreId=:tdpCadreId and model.trainingCampBatchId=:trainingCampBatchId " +
		" and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("trainingCampBatchId", batchId);
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
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
    public List<Object[]> getattendedcount(String queryString,List<Long> programIds,Long campId,Long batchId,Date fromDate,Date toDate,String type,List<Long> enrollmentYrIds){
    	
    	Query query=getSession().createQuery(queryString);
    	Date currDate=new DateUtilService().getCurrentDateAndTime();
    	
    	if(!type.equalsIgnoreCase("all")){
    		query.setParameter("currDate", currDate);
    	}
    	
    	if(fromDate!=null && toDate!=null){
	    	query.setParameter("fromDate", fromDate);
	    	query.setParameter("toDate", toDate);
    	}
    	if(batchId==null && campId==null && programIds!=null){
    		query.setParameterList("programIds",programIds);
    		
		}else if(batchId==null && campId!=null){
			query.setParameter("campId",campId);
			if(programIds!=null)
				query.setParameterList("programIds",programIds);
			
		}else if(batchId!=null){
			
			if(programIds!=null)
				query.setParameterList("programIds",programIds);
			if(campId!=null)
				query.setParameter("campId",campId);
			
			query.setParameter("batchId",batchId);
			
		}
    	
    	if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		}
    	return query.list();
    }
 public Long getattendedcount1(String queryString,List<Long> programIds,Long campId,Long batchId,Date fromDate,Date toDate,String type,List<Long> enrollmentYrIds){
    	
    	Query query=getSession().createQuery(queryString);
    	
    	Date currDate=new DateUtilService().getCurrentDateAndTime();
    	
    	if(!type.equalsIgnoreCase("all")){
    		query.setParameter("currDate", currDate);
    	}
    	if(fromDate!=null && toDate!=null){
	    	query.setParameter("fromDate", fromDate);
	    	query.setParameter("toDate", toDate);
    	}
    	if(batchId==null && campId==null && programIds!=null){
    		query.setParameterList("programIds",programIds);
    		
		}else if(batchId==null && campId!=null){
			query.setParameter("campId",campId);
			if(programIds!=null)
				query.setParameterList("programIds",programIds);
			
		}else if(batchId!=null){
			
			if(programIds!=null)
				query.setParameterList("programIds",programIds);
			if(campId!=null)
				query.setParameter("campId",campId);
			
			query.setParameter("batchId",batchId);
			
		}
    	
    	if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
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
  
  public List<Long> getattendedcountClickDetails(String queryString,List<Long> programIds,Long campId,Long batchId,Date fromDate,Date toDate,String type,List<Long> enrollmentYrIds,String skillType,Long statusTypeId){
	  Query query = getSession().createSQLQuery(queryString).addScalar("cadreId", Hibernate.LONG);
	  
	  Date currDate=new DateUtilService().getCurrentDateAndTime();
  	
  		if(!type.equalsIgnoreCase("all")){
  			query.setParameter("currDate", currDate);
  		}
  	
  		if(fromDate!=null && toDate!=null){
	    	query.setParameter("fromDate", fromDate);
	    	query.setParameter("toDate", toDate);
  		}
  		if(batchId==null && campId==null && programIds!=null){
  			query.setParameterList("programIds",programIds);
  		}else if(batchId==null && campId!=null){
  			query.setParameter("campId",campId);
  			if(programIds!=null)
  				query.setParameterList("programIds",programIds);
  		}else if(batchId!=null){
  			if(programIds!=null)
  				query.setParameterList("programIds",programIds);
  			if(campId!=null)
  				query.setParameter("campId",campId);
			
  			query.setParameter("batchId",batchId);
			
  		}
  	
  		if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
  			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
  		}
  		
  		if(statusTypeId != null && statusTypeId > 0l){
  			query.setParameter("statusTypeId", statusTypeId);
  		}
  		return query.list();
	  
  	}
  
  public List<Object[]> getTrainingCampFeedBAckDeatilesByTdpCadreId(Long tdpCadreId){
	  StringBuilder sb= new StringBuilder();
	  sb.append("select  model.trainingCampCadreFeedbackDetailsId,model.tdpCadreId,");//00 feedback Id, 01 cadereId
	  sb.append("model.trainingCampProgramId,model2.programName," );// 02 programId,03 programName
	  sb.append("model.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchName,");// 04 batchId, 05  batchName
	  sb.append("model.cadreLeadershipLevel.cadreLeadershipLevelId,model.cadreLeadershipLevel.leadershipLevel,");//06 leadershiplevelId,07  leaderShiplevel
	  sb.append("model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId,model.cadreComminicationSkillsStatus.status," );// 08 communationSkillsId, 09 communicationSkillsStatus
	  sb.append("model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId,model.cadreLeadershipSkillsStatus.status," );//10 leaderShipSkillsId, 11 leaderShipSkillsStatus
	  sb.append("model.cadreHealthStatus.cadreHealthStatusId,model.cadreHealthStatus.status,");	// 12 healthStatusId, 13 healthStatus
	  sb.append("model.remarks,model.smartPhoneExist,model.watsappUsing,model.watsappShare,model.facebookUsing,");// 14 remarks, 15 smartPhoneExist, 16 watsappUsing 17 watsappShare, 18 facebookUsing
	  sb.append("model.healthCardAttachment,");//19  healthCardAttachment
	  sb.append("model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId,model.trainingCampBatch.trainingCampSchedule.enrollmentYear.description, "); //20  yearId ,21 year
	  sb.append("model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName "); //22  campId ,23 campName
	  sb.append("from TrainingCampCadreFeedbackDetails model,TrainingCampProgram model2  " );
	  sb.append("where model.trainingCampProgramId=model2.trainingCampProgramId ");
	  if(tdpCadreId != null && tdpCadreId.longValue() > 0)
	  sb.append("and model.tdpCadreId = :tdpCadreId ");
	  Query query = getSession().createQuery(sb.toString());
	  if(tdpCadreId != null && tdpCadreId.longValue() > 0)
	  query.setParameter("tdpCadreId", tdpCadreId);
	  return query.list();
  }
 public List<Object[]> getCommunicationFeedbackOnLeaders(Long userAccessLevelId,List<Long> userAccessLevelValues,List<Long> trainingProgramIds,Long traingCampEnrollmentYearId,List<Long> trainingCampLevelIds,Long groupType,Long questionType){
	StringBuilder sb = new StringBuilder();
	StringBuilder sg = new StringBuilder();
	sb.append(" select ");
	sg.append(" group by ");
	if(groupType != null && groupType.longValue() == 1L){//center wise 
		sb.append(" model2.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId," +
				 "  model2.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, ");
		if(questionType != null && questionType.longValue() == 1L){//communication
			sb.append(" model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId, ");
		}else if(questionType != null && questionType.longValue() == 2L){//leadership skill
			sb.append(" model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId, ");
		}
		sb.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		sg.append(" model2.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId , ");
	}else if(groupType != null && groupType.longValue() == 2L){// or district wise
		sb.append(" model.tdpCadre.userAddress.district.districtId, " +
	  			  " model.tdpCadre.userAddress.district.districtName, ");
		if(questionType != null && questionType.longValue() == 1L){//communication
			sb.append(" model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId, ");
		}else if(questionType != null && questionType.longValue() == 2L){//leadership skill
			sb.append(" model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId, ");
		}
	  	sb.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		sg.append(" model.tdpCadre.userAddress.district.districtId, ");
	}else if(groupType != null && groupType.longValue() == 3L){
		sb.append(" model.tdpCadre.userAddress.parliamentConstituency.constituencyId, " +
	  			  " model.tdpCadre.userAddress.parliamentConstituency.name, ");
		if(questionType != null && questionType.longValue() == 1L){//communication
			sb.append(" model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId, ");
		}else if(questionType != null && questionType.longValue() == 2L){//leadership skill
			sb.append(" model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId, ");
		}
		sb.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		sg.append(" model.tdpCadre.userAddress.parliamentConstituency.constituencyId, ");
	}else if(groupType != null && groupType.longValue() == 4L){
		sb.append(" model.tdpCadre.userAddress.constituency.constituencyId, " +
	  			  " model.tdpCadre.userAddress.constituency.name, ");
		if(questionType != null && questionType.longValue() == 1L){//communication
			sb.append(" model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId, ");
		}else if(questionType != null && questionType.longValue() == 2L){//leadership skill
			sb.append(" model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId, ");
		}
		sb.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		sg.append(" model.tdpCadre.userAddress.constituency.constituencyId, ");
	}
	sb.append(" from TrainingCampCadreFeedbackDetails model, TdpCommitteeMember model1 ");
	if(groupType != null && groupType.longValue() == 1L){
		sb.append(" ,TrainingCampBatchAttendee model2 ");
	}
	sb.append(" where model.tdpCadre.isDeleted = 'N' ");
	if(groupType != null && groupType.longValue() == 1L){
		sb.append(" and model.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId ");
	}
	sb.append(" and model.trainingCampProgramId in (:trainingProgramIds) " +
	  		  " and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId " +
	  		  " and model1.tdpCommitteeRole.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :traingCampEnrollmentYearId " +
	  		  " and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:trainingCampLevelIds) " +
	  		  " and model1.isActive = 'Y' " +
	  		  " and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :traingCampEnrollmentYearId " +
	  		  " and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :traingCampEnrollmentYearId and ");
	  if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		  if(userAccessLevelId != null && userAccessLevelId.longValue() == 2L){
			  sb.append(" model.tdpCadre.userAddress.district.districtId in (11,12,13,14,15,16,17,18,19,20,21,22,23,517) ");
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue() == 3L){
			  sb.append(" model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues) ");
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue() == 4L){
			  sb.append(" model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue() == 5L){
			  sb.append(" model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");
		  }
	  }
	  if(questionType != null && questionType.longValue() == 1L){//communication
		  sg.append(" model.cadreComminicationSkillsStatus.cadreComminicationSkillsStatusId ");
	  }else if(questionType != null && questionType.longValue() == 2L){//leadership skill
		  sg.append(" model.cadreLeadershipSkillsStatus.cadreLeadershipSkillsStatusId ");
	  }
	  Query query = getSession().createQuery(sb.toString()+" "+sg.toString());
	  if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		  if(userAccessLevelId != null && userAccessLevelId.longValue() != 2L){
			  query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		  }
	  }
	  if(trainingProgramIds != null && trainingProgramIds.size() > 0){
		  query.setParameterList("trainingProgramIds", trainingProgramIds);
	  }
	  if(trainingCampLevelIds != null && trainingCampLevelIds.size() > 0){
		  query.setParameterList("trainingCampLevelIds", trainingCampLevelIds);
	  }
	  if(traingCampEnrollmentYearId != null && traingCampEnrollmentYearId.longValue() > 0L){
		  query.setParameter("traingCampEnrollmentYearId", traingCampEnrollmentYearId);
	  }
	  return query.list();
  }
 public Long getLeaderFeedBackDetails(List<Long> programIdList,Long userAccessLevelId, List<Long> userAccessLevelValueList,List<Long> commiteeLevel) {
	  StringBuilder sb= new StringBuilder();
	  sb.append(" select count(distinct model.tdpCadreId)" +
	  		    " from TdpCommitteeMember model2 " );
		    if(userAccessLevelValueList!= null && userAccessLevelValueList.size()>0L){
	    	 if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 2l){
	    	      sb.append(" left join model2.tdpCadre.userAddress.district state ");
	    	  }else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 3l){
	    	      sb.append(" left join model2.tdpCadre.userAddress.district district ");
	    	    }else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 4l){
	    	      sb.append(" left join  model2.tdpCadre.userAddress.parliamentConstituency parliamentConstituency ");
	    	    }else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 5l){
	    	    	sb.append(" left join model2.tdpCadre.userAddress.constituency constituency ");
	    	    }
		      }
		  sb.append(",TrainingCampCadreFeedbackDetails model where model.tdpCadreId = model2.tdpCadreId and " +
		    " model.trainingCampProgramId in(:trainingCampProgramId) and " +
		    " model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in(:tdpCommitteeLevelId) " );
		   if(userAccessLevelId != null && userAccessLevelId.longValue()  == 2l && userAccessLevelValueList != null && userAccessLevelValueList.size() >0l ){
		      sb.append(" and state.districtId  in (11,12,13,14,15,16,17,18,19,20,21,22,23,517) ");
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 3l && userAccessLevelValueList != null && userAccessLevelValueList.size() >0l ){
		      sb.append(" and district.districtId  in (:userAccessLevelValueList) ");
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 4l && userAccessLevelValueList != null && userAccessLevelValueList.size() >0l){
		      sb.append("   and parliamentConstituency.constituencyId in (:userAccessLevelValueList)  ");
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 5l && userAccessLevelValueList != null && userAccessLevelValueList.size() >0l){
		    	 sb.append(" and constituency.constituencyId in (:userAccessLevelValueList)");
		     }
		 Query query = getSession().createQuery(sb.toString());
	  		query.setParameterList("trainingCampProgramId", programIdList );
	  		query.setParameterList("tdpCommitteeLevelId", commiteeLevel);
	  		if(userAccessLevelId != null && userAccessLevelId.longValue()  != 2l && userAccessLevelValueList != null && userAccessLevelValueList.size() >0l ){
		  		query.setParameterList("userAccessLevelValueList", userAccessLevelValueList);
		  		}
	   return (Long)query.uniqueResult();
}
}
