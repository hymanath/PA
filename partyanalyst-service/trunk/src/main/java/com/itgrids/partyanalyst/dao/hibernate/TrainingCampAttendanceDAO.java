package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;
import com.itgrids.partyanalyst.utils.IConstants;

public class TrainingCampAttendanceDAO extends GenericDaoHibernate<TrainingCampAttendance,Long> implements ITrainingCampAttendanceDAO{

	public TrainingCampAttendanceDAO()
	{
		super(TrainingCampAttendance.class);
	}
	
  public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb=new StringBuilder();
	  sb.append(
	  " select    model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct model.attendance.tdpCadreId) " +
	  " from      TrainingCampAttendance model,TdpCommitteeMember model2,TrainingCampBatchAttendee tcba " +
	  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
	  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' " +
	  "			and model.attendance.tdpCadreId=tcba.tdpCadreId and model.trainingCampBatch.trainingCampBatchId=tcba.trainingCampBatchId and tcba.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=1 " );
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	  }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	  }
	  sb.append(" group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
	  Query query=getSession().createQuery(sb.toString());
	  query.setParameterList("trainingCampBatchIds",batchIds);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  query.setParameterList("enrollmentYearIds",enrollmentYearIds);
	  }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds",programYearIds);
	  }
	  return query.list();
  }
  
  public List<Long> getInviteeCadreIds(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb=new StringBuilder();
	  sb.append(
			  " select    distinct model.attendance.tdpCadreId " +
			  " from      TrainingCampAttendance model,TdpCommitteeMember model2,TrainingCampBatchAttendee tcba " +
			  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
			  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' " +
			  "			and model.attendance.tdpCadreId=tcba.tdpCadreId and model.trainingCampBatch.trainingCampBatchId=tcba.trainingCampBatchId and tcba.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=1  ");
	    if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	     }
	    if(programYearIds != null && programYearIds.size()>0){
	    	sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	    }
	  Query query=getSession().createQuery(sb.toString());
			  query.setParameterList("trainingCampBatchIds",batchIds);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
			  } 
        if(programYearIds != null && programYearIds.size()>0){
        	query.setParameterList("programYearIds",programYearIds);
	    }
			  return query.list();
  }

  public List<Object[]> getAttendedNonInviteesCountForBatchesByLocation(List<Long> batchIds,List<Long> cadreIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb=new StringBuilder();
	  sb.append(
			  " select    model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct model.attendance.tdpCadreId) " +
			  " from      TrainingCampAttendance model,TdpCommitteeMember model2 " +
			  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
			  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' and model.attendance.tdpCadreId not in (:cadreIds) and model.trainingCampBatch.attendeeTypeId=1  " );
	     if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
	    	 sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	     if(programYearIds != null && programYearIds.size()>0){
	    	 sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds) ");
	     }
	     sb.append(" group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
	      Query query=getSession().createQuery(sb.toString());
			  query.setParameterList("trainingCampBatchIds",batchIds);
			  query.setParameterList("cadreIds",cadreIds);
			if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
				   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
				  }
			 if(programYearIds != null && programYearIds.size()>0){
				 query.setParameterList("programYearIds",programYearIds);
			 }
			  return query.list();
  }
  public List<Object[]> getInvitedCadreCountByBatchIds(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds){
	
		 StringBuilder sb=new StringBuilder();
		 sb.append(" select ");
		 
		 if(type.equalsIgnoreCase("constituency")){
			 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
		 }else{
			 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
		 }
		 sb.append(",count(distinct model.tdpCadreId)");
		 
		 sb.append(" from TrainingCampBatchAttendee model,TdpCadre model1 " +
		 		   " where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1  ");
		 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			 sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
   	        }
		 if(programYearIds != null && programYearIds.size()>0){
			 sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
		 }
		 if(type.equalsIgnoreCase("constituency")){
			 sb.append(" group by model1.userAddress.constituency.constituencyId " +
			 		   " order by model1.userAddress.constituency.name");
			 
		 }else{
			 sb.append(" group by model1.userAddress.district.districtId " +
			 		   " order by model1.userAddress.district.districtName");
		 }
		 
		Query query=getSession().createQuery(sb.toString());
		query.setParameterList("trainingCampBatchIds",batchIds);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds",programYearIds);
		 }
		return query.list();
		 
	 }
 public List<Object[]> getAttendedCadreCountByBatchIds(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds){
	
	 StringBuilder sb=new StringBuilder();
	 sb.append(" select ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
	 }else{
		 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
	 }
	 sb.append(",count(distinct model1.tdpCadreId)");
	 
	 sb.append(" from TrainingCampAttendance model,TdpCadre model1 " +
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) and model.trainingCampBatch.attendeeTypeId=1 ");
	 
	 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	 if(programYearIds != null && programYearIds.size()>0){
		 sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
	 }
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" group by model1.userAddress.constituency.constituencyId " +
		 		   " order by model1.userAddress.constituency.name");
		 
	 }else{
		 sb.append(" group by model1.userAddress.district.districtId " +
		 		   " order by model1.userAddress.district.districtName");
	 }
	 
	Query query=getSession().createQuery(sb.toString());
	 query.setParameterList("trainingCampBatchIds",batchIds);
	if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		query.setParameterList("enrollmentYearIds",enrollmentYearIds);
	}
	if(programYearIds != null && programYearIds.size()>0){
		query.setParameterList("programYearIds",programYearIds);
	}
	return query.list();
	 
 }
 public List<Object[]> getInviteeCountsinAttendedCounts(List<Long> batchIds,String type,List<Long> enrollmentYearIds,List<Long> programYearIds){
		
	 StringBuilder sb=new StringBuilder();
	 sb.append(" select ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
	 }else{
		 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
	 }
	 sb.append(",count(distinct model1.tdpCadreId)");
	 
	 sb.append(" from TrainingCampAttendance model,TdpCadre model1,TrainingCampBatchAttendee model2 " +
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.attendance.tdpCadreId=model2.tdpCadreId and  model.trainingCampBatchId in (:trainingCampBatchIds) " +
	 		   " and model2.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1  ");
	 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	 if(programYearIds != null && programYearIds.size()>0){
		 sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
	 }
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" group by model1.userAddress.constituency.constituencyId " +
		 		   " order by model1.userAddress.constituency.name");
		 
	 }else{
		 sb.append(" group by model1.userAddress.district.districtId " +
		 		   " order by model1.userAddress.district.districtName");
	 }
	 
	Query query=getSession().createQuery(sb.toString());
	query.setParameterList("trainingCampBatchIds",batchIds);
	if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		query.setParameterList("enrollmentYearIds",enrollmentYearIds);
	}
	if(programYearIds != null && programYearIds.size()>0){
		query.setParameterList("programYearIds",programYearIds);
	 }
	return query.list();
	 
 }
 
 public List<Object[]> getCompletedCounts(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	 StringBuilder sb = new StringBuilder();
	   sb.append(" select model.trainingCampBatch.trainingCampBatchId, " +
	   		    "  count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model,TrainingCampBatchAttendee model1 " );
	   sb.append(" where " );
	   if(batchIds != null && batchIds.size()>0){
	    sb.append(" model.trainingCampBatch.trainingCampBatchId in (:batchIds) and " );
	   }
	   sb.append("  model.trainingCampBatch.trainingCampBatchId=model1.trainingCampBatch.trainingCampBatchId " +
	   		" and model.attendance.tdpCadre.tdpCadreId=model1.tdpCadre.tdpCadreId " +
				" and model1.isDeleted='false' and model.trainingCampBatch.isCancelled='false' " +
				" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' " );
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYearIds)");
	   }
      if(programYearIds != null && programYearIds.size()>0){
    	  sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   }
	   sb.append(" group by model.trainingCampBatch.trainingCampBatchId ");
	   Query query=getSession().createQuery(sb.toString());
	   if(batchIds != null && batchIds.size()>0){
		query.setParameterList("batchIds", batchIds);
	   }
      if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
    	  query.setParameterList("enrollmentYearIds", enrollmentYearIds);  
	   }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds); 
	   }
		return query.list();
 }
 
 public List<Object[]> getCompletedCountDetails(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	 StringBuilder sb = new StringBuilder();
	 sb.append(" select distinct model.trainingCampBatch.trainingCampBatchId, model.attendance.tdpCadre.tdpCadreId " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled='false'  ");
	 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		 sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	 if(programYearIds != null && programYearIds.size()>0){
		 sb.append(" and  model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) ");
	 }
	  Query query =getSession().createQuery(sb.toString());
		query.setParameterList("batchIds", batchIds);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size()>0){
			 query.setParameterList("programYearIds", programYearIds);
		 }
		return (List<Object[]>)query.list();
}
 
 public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,List<Long> programIds,Long campId,Long batchId,Date fromDate,Date toDate,Date currDate,String callFrom,List<Long> enrollmentYrIds){
	 
	  Query query=getSession().createQuery(queryString);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  
	  if(!callFrom.equalsIgnoreCase("all")){		
		  query.setParameter("currDate", currDate);
	  }
	  if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		}
	  
	  if(batchId==null && campId==null && programIds!=null ){
		 query.setParameterList("programIds",programIds);
	  }
	  else if(batchId==null && campId!=null){
		 query.setParameter("campId",campId);
		 if(programIds!=null)
			 query.setParameterList("programIds",programIds);
	  }else if(batchId!=null){
		 query.setParameter("batchId",batchId);
		 if(programIds!=null)
			 query.setParameterList("programIds",programIds);
		 if(campId!=null)
			 query.setParameter("campId",campId);
	  }
	  return query.list();
 }
  public Long getAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb=new StringBuilder();
	  sb.append(" select count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId =:batchId and model.trainingCampBatch.attendeeTypeId=1 and  model.trainingCampBatch.isCancelled ='false' ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and (date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate) ");
	  }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
		}
		Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds", programYearIds);
		}
		query.setParameter("batchId", batchId);
		
		 if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
			   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		return (Long)query.uniqueResult();
  }
  
  public Long getInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr ,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds)
  {	  StringBuilder sb=new StringBuilder();
	  List<Long> tdpCommitteeEnrolmentYearIdsList = new ArrayList<Long>(0);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  for (Long tdpEnrollmentYearId : enrollmentYearIds) {
			  if(tdpEnrollmentYearId != null)
				  tdpCommitteeEnrolmentYearIdsList.add(tdpEnrollmentYearId-2);
			  
		}
	  }
	  sb.append(" select count(distinct TCA.attendance.tdpCadreId) " +
				" from TrainingCampAttendance TCA, TdpCommitteeMember TCM   " + 
				" where  TCA.attendance.tdpCadre.tdpCadreId = TCM.tdpCadreId and TCM.isActive='Y' and " +
				" TCM.tdpCommitteeRole.tdpRolesId in ("+IConstants.TRAINING_INVITEE_ROLE_IDS+") and  " +
				" TCA.trainingCampBatch.attendeeTypeId=1 and TCA.trainingCampBatch.isCancelled ='false' and  " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (5,7,9,6,8) ");
	  if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
		  sb.append(" and TCM.tdpCommitteeEnrollmentId in (:tdpCommitteeEnrolmentYearIdsList) ");
	  if(batchId != null && batchId.longValue()>0L){
		  sb.append(" and TCA.trainingCampBatch.trainingCampBatchId =:batchId ");
		  if(fromDate!=null && toDate!=null){
			  sb.append(" and date(TCA.trainingCampBatch.fromDate) >= :fromDate and date(TCA.trainingCampBatch.toDate) <= :toDate");
		  }
	  }
	  else if(searchTypeStr != null && fromDate!=null && toDate!=null){		   
		   if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) )  )");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(TCA.trainingCampBatch.fromDate) < :fromDate and date(TCA.trainingCampBatch.toDate) < :toDate ) ");
	   }
	  if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and TCM.tdpCadreId not in (:staffCadreIdsList) " );
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and TCA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  
	  Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(batchId != null && batchId.longValue()>0L)			
			query.setParameter("batchId", batchId);
		if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		}
		if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
			query.setParameterList("tdpCommitteeEnrolmentYearIdsList",tdpCommitteeEnrolmentYearIdsList);
		
		return (Long)query.uniqueResult();
  }
  
  public Long getNonInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds)
  {
	  StringBuilder sb=new StringBuilder();
	  List<Long> tdpCommitteeEnrolmentYearIdsList = new ArrayList<Long>(0);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  for (Long tdpEnrollmentYearId : enrollmentYearIds) {
			  if(tdpEnrollmentYearId != null)
				  tdpCommitteeEnrolmentYearIdsList.add(tdpEnrollmentYearId-2);
			  
		}
	  }
	  sb.append(" select count(distinct TCA.attendance.tdpCadreId) " +
				" from TrainingCampAttendance TCA, TdpCommitteeMember TCM   " +
				" where  TCA.attendance.tdpCadre.tdpCadreId = TCM.tdpCadreId and TCM.isActive='Y' and " +
				" TCM.tdpCommitteeRole.tdpRolesId not in ("+IConstants.TRAINING_INVITEE_ROLE_IDS+") and  " +
				" TCA.trainingCampBatch.attendeeTypeId=1 and TCA.trainingCampBatch.isCancelled ='false' and " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (5,7,9,6,8)  ");
	  if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
		  sb.append(" and TCM.tdpCommitteeEnrollmentId in (:tdpCommitteeEnrolmentYearIdsList) ");
	  if(batchId != null && batchId.longValue()>0L){
		  sb.append(" and TCA.trainingCampBatch.trainingCampBatchId =:batchId ");
		  if(fromDate!=null && toDate!=null){
			  sb.append(" and date(TCA.trainingCampBatch.fromDate) >= :fromDate and date(TCA.trainingCampBatch.toDate) <= :toDate");
		  }
	  }
	  else if(searchTypeStr != null && fromDate!=null && toDate!=null){		   
		   if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) )  )");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(TCA.trainingCampBatch.fromDate) < :fromDate and date(TCA.trainingCampBatch.toDate) < :toDate ) ");
	   }
	  if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and TCM.tdpCadreId not in (:staffCadreIdsList) " );
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and TCA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  
	  Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(batchId != null && batchId.longValue()>0L)			
			query.setParameter("batchId", batchId);
		if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		}
		if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
			query.setParameterList("tdpCommitteeEnrolmentYearIdsList",tdpCommitteeEnrolmentYearIdsList);
		
		return (Long)query.uniqueResult();
  }
  
  public Long getTotalAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds)
  {
	  StringBuilder sb=new StringBuilder();
	  List<Long> tdpCommitteeEnrolmentYearIdsList = new ArrayList<Long>(0);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  for (Long tdpEnrollmentYearId : enrollmentYearIds) {
			  if(tdpEnrollmentYearId != null)
				  tdpCommitteeEnrolmentYearIdsList.add(tdpEnrollmentYearId-2);
			  
		}
	  }
	  
	  fromDate = null;  toDate = null; searchTypeStr = null;
	  
	  sb.append(" select count(distinct TCA.attendance.tdpCadreId) " +
				" from TrainingCampAttendance TCA, TdpCommitteeMember TCM   " +
				" where  TCA.attendance.tdpCadre.tdpCadreId = TCM.tdpCadreId and TCM.isActive='Y' and " +
				" TCM.tdpCommitteeRole.tdpRolesId in ("+IConstants.TRAINING_INVITEE_ROLE_IDS+") and  " +
				" TCA.trainingCampBatch.attendeeTypeId=1 and TCA.trainingCampBatch.isCancelled ='false' and " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (5,7,9,6,8)  ");
	  if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
		  sb.append(" and TCM.tdpCommitteeEnrollmentId in (:tdpCommitteeEnrolmentYearIdsList) ");
	  if(batchId != null && batchId.longValue()>0L){
		  sb.append(" and TCA.trainingCampBatch.trainingCampBatchId =:batchId ");
		  if(fromDate!=null && toDate!=null){
			  sb.append(" and date(TCA.trainingCampBatch.fromDate) >= :fromDate and date(TCA.trainingCampBatch.toDate) <= :toDate");
		  }
	  }
	  else if(searchTypeStr != null && fromDate!=null && toDate!=null){		   
		   if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(TCA.trainingCampBatch.fromDate) and date(TCA.trainingCampBatch.toDate) )  )");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(TCA.trainingCampBatch.fromDate) < :fromDate and date(TCA.trainingCampBatch.toDate) < :toDate ) ");
	   }
	  if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and TCM.tdpCadreId not in (:staffCadreIdsList) " );
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and TCA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  
	  Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(batchId != null && batchId.longValue()>0L)			
			query.setParameter("batchId", batchId);
		if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		}
		if(tdpCommitteeEnrolmentYearIdsList != null && tdpCommitteeEnrolmentYearIdsList.size()>0)
			query.setParameterList("tdpCommitteeEnrolmentYearIdsList",tdpCommitteeEnrolmentYearIdsList);
		
		return (Long)query.uniqueResult();
  }
  
  public List<Object[]> getDateWiseCountsByBatch(Long batchId,Date fromDate,Date toDate){
	  StringBuilder sb=new StringBuilder();
	  
	  sb.append(" select date(model.attendance.attendedTime),count(distinct model.attendance.tdpCadre.tdpCadreId) " +
	  " from TrainingCampAttendance model " +
	  " where model.trainingCampBatch.trainingCampBatchId =:batchId and model.trainingCampBatch.attendeeTypeId=1 ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate ");
	  }
	  
	  sb.append(" group by date(model.attendance.attendedTime)");
	  
	  
	  Query query=getSession().createQuery(sb.toString());
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate",fromDate);
		  query.setParameter("toDate",toDate);
	  }
	  
	  query.setParameter("batchId",batchId);
	  return query.list();
  }
 public List<Object[]> getCampWiseAttendedCountByProgram(Long programId,Date fromDate,Date toDate){
	 
	 StringBuilder sb = new StringBuilder();
	 
	 sb.append(" select  tcs.trainingCampId,count(distinct tca.attendance.tdpCadreId)" +
	 " from  TrainingCampAttendance tca,TrainingCampSchedule tcs" +
	 " where tca.trainingCampProgramId=tcs.trainingCampProgramId and " +
	 "       tca.trainingCampSchedule.trainingCampScheduleId = tcs.trainingCampScheduleId and " +
	 "       tca.trainingCampProgramId=:programId and tca.trainingCampBatch.attendeeTypeId=1  ");
	 
	 if(fromDate!=null && toDate!=null){
		sb.append(" and date(tca.trainingCampBatch.fromDate) >= :fromDate and date(tca.trainingCampBatch.toDate) <= :toDate "); 
	 }
	 
 	sb.append(" group by tcs.trainingCampId");
	 
	 Query query=getSession().createQuery(sb.toString());
	 if(fromDate!=null && toDate!=null){
		 query.setParameter("fromDate",fromDate);
		 query.setParameter("toDate",toDate);
	 }
	 query.setParameter("programId",programId);
	 return query.list();
 }
 public Long getAttendedCountByCamp(Long programId,Long campId,Date fromDate,Date toDate){
	 
	 StringBuilder sb = new StringBuilder();
	 
	 sb.append(" select  count(distinct tca.attendance.tdpCadreId)" +
	 " from  TrainingCampAttendance tca,TrainingCampSchedule tcs" +
	 " where tca.trainingCampProgramId=tcs.trainingCampProgramId and" +
	 "       tcs.trainingCampScheduleId=tca.trainingCampScheduleId and " +
	 "       tca.trainingCampProgramId=:programId and tcs.trainingCampId=:campId and tca.trainingCampBatch.attendeeTypeId=1 and tca.trainingCampBatch.isCancelled ='false' ");
	 
	 if(fromDate!=null && toDate!=null){
		 sb.append("and date(tca.trainingCampBatch.fromDate) >= :fromDate and date(tca.trainingCampBatch.toDate) <= :toDate");
	 }
	 
	 
	 Query query=getSession().createQuery(sb.toString());
	 
	 query.setParameter("programId",programId);
	 if(fromDate!=null && toDate!=null){
		 query.setParameter("fromDate",fromDate);
		 query.setParameter("toDate",toDate);
	 }
	 
	 query.setParameter("campId",campId);
	 return (Long)query.uniqueResult();
 }
 public List<Object[]> getDateWiseAttendedAndAbsentCandidates(Long batchId ,Long enrollmentYearId ){
	  StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
	 Query query=getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime) " +
	 		" from TrainingCampAttendance model " +
	 		" where" +
	 		" model.trainingCampBatch.trainingCampBatchId = :batchId" +
	 		" and date(model.attendance.attendedTime) between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) " +
	 		" and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" ");
	 
	 query.setParameter("batchId", batchId);
	  if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
		   query.setParameter("enrollmentYearId",enrollmentYearId);
	 return query.list();
 }
 public List<Object[]> getAttendedCountOfCadreProgramWise(Long cadreId){
	 
	 Query query = getSession().createQuery("select model.trainingCampProgram.trainingCampProgramId,model.attendance.tdpCadreId,model.trainingCampProgram.programName,date(model.attendance.attendedTime) " +
	 		" from  TrainingCampAttendance model" +
	 		" where model.attendance.tdpCadre.tdpCadreId =:cadreId and model.attendance.tdpCadre.isDeleted='N' ");//and model.trainingCampBatch.attendeeTypeId=1 " +
	 		//" group by model.trainingCampProgram.trainingCampProgramId  ");
	 
	 query.setParameter("cadreId", cadreId);
	 return query.list();
 }
 public List<Object[]> getAttendedTrainingCampBatchDetailsOfCadre(Long programId,Long cadreId){
	 
	 Query query = getSession().createQuery("select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime)," +
	 		"model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchName,model.trainingCampSchedule.trainingCamp.trainingCampId," +
	 		" model.trainingCampSchedule.trainingCamp.campName,model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
	 		"model.trainingCampSchedule.trainingCampProgram.programName  " +
	 		" from TrainingCampAttendance model " +
	 		" where  model.attendance.tdpCadre.tdpCadreId =:cadreId " +
	 		" and model.trainingCampProgram.trainingCampProgramId = :programId ");//and model.trainingCampBatch.attendeeTypeId=1  ");
	 
	 query.setParameter("cadreId",cadreId);
	 query.setParameter("programId", programId);
	 
	 return query.list();
 }
 
 public List<Long> getCompletedCountsForADay(Long batchId,Date dates){
		Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,model.attendance.attendedTime  " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId=:batchId and date(model.attendance.attendedTime)=:dates" +
				" and model.trainingCampBatch.attendeeTypeId=1  ");
		query.setParameter("batchId", batchId);
		query.setParameter("dates", dates);
		return (List<Long>)query.list();
}
 
 public List<Object[]> getCompletedCountsForABatch(Long batchId,List<Date> dates,List<Long> enrollmentYearIds,List<Long> programYearIds){
	 StringBuilder sb = new StringBuilder();
	 sb.append(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime)  " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId=:batchId ");
	 if(dates != null && dates.size()>0)
	   sb.append(" and date(model.attendance.attendedTime) in (:dates)" );
	   sb.append(" and model.trainingCampBatch.attendeeTypeId=1  ");
	 if(programYearIds != null && programYearIds.size()>0){
		 sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
	        }
	 if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
			sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	 Query query = getSession().createQuery(sb.toString());
		query.setParameter("batchId", batchId);
		if(dates != null && dates.size()>0)
		query.setParameterList("dates", dates);
	  if(programYearIds != null && programYearIds.size()>0){
		query.setParameterList("programYearIds", programYearIds);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		return query.list();
}
 
  /*public List<Object[]> getInviteeAttendedCounts(List<Long> batchIds,Date fromDate,Date toDate){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select model.trainingCampBatch.trainingCampBatchId, count(distinct model.attendance.tdpCadre.tdpCadreId)" +
	  		" from TrainingCampAttendance model,TrainingCampBatchAttendee model1 " +
	  		" where model.trainingCampBatch.trainingCampBatchId=model1.trainingCampBatchId and model.attendance.tdpCadre.tdpCadreId=model1.tdpCadre.tdpCadreId " +
	  		" and model.trainingCampBatch.trainingCampBatchId in (:batchIds) ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  }
	  sb.append(" group by model.trainingCampBatch.trainingCampBatchId");
	  
	  Query query = getSession().createQuery(sb.toString());
	  
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  query.setParameterList("batchIds", batchIds);
	  return query.list();
  }*/
  
  /*public List<Object[]> getInviteeAttendedDetails(List<Long> batchIds,Date fromDate,Date toDate){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct model.trainingCampBatch.trainingCampBatchId, model.attendance.tdpCadre.tdpCadreId" +
		  		" from TrainingCampAttendance model,TrainingCampBatchAttendee model1 " +
		  		" where model.trainingCampBatch.trainingCampBatchId=model1.trainingCampBatchId and model.attendance.tdpCadre.tdpCadreId=model1.tdpCadre.tdpCadreId " +
		  		" and model.trainingCampBatch.trainingCampBatchId in (:batchIds) ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	  }
	  Query query = getSession().createQuery(sb.toString());
	  query.setParameterList("batchIds", batchIds);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  return query.list();
  }*/
  
  public List<Object[]> getInviteeAttendedCountsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId, count(distinct model.attendance.tdpCadre.tdpCadreId)" +
	  		" from TrainingCampAttendance model,TrainingCampBatchAttendee model1 " +
	  		" where model.trainingCampBatch.trainingCampBatchId=model1.trainingCampBatchId and model.attendance.tdpCadre.tdpCadreId=model1.tdpCadre.tdpCadreId " +
	  		" and model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId=:centerId " +
	  		" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId " +
	  		" and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and (date(model.attendance.attendedTime) between :fromDate and :toDate)  ");
	  }
	  if(cadreIdsLsit != null && cadreIdsLsit.size()>0)
	   {
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:cadreIdsLsit) ");		  
	   }
	  Query query = getSession().createQuery(sb.toString());
	  if(cadreIdsLsit!=null && cadreIdsLsit!=null){
		   query.setParameterList("cadreIdsLsit", cadreIdsLsit);
	  }
	  if(fromDate!=null && toDate!=null){
		  query.setDate("fromDate", fromDate);
		  query.setDate("toDate", toDate);
	  }
	  query.setParameter("centerId", centerId);
	  query.setParameter("programId", programId);
	  return query.list();
  }
  
  public List<Object[]> getInviteeAttendedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	   			" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	   			" count(distinct model.attendance.tdpCadre.tdpCadreId) from TrainingCampBatchAttendee model1,TrainingCampAttendance model " +
	   			" where model1.tdpCadre.tdpCadreId = model.attendance.tdpCadre.tdpCadreId and model1.trainingCampBatch.trainingCampBatchId=model.trainingCampBatchId " +
	   			" and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' ");
	   
	   if(fromDate!=null && toDate!=null){
		  sb.append(" and (date(model.attendance.attendedTime) between :fromDate and :toDate)  ");
	  }
	  if(cadreIdsList != null && cadreIdsList.size()>0)
	   {
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:cadreIdsList) ");		  
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		    sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   }
	   sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
       if(programYearIds != null && programYearIds.size()>0){
    	   query.setParameterList("programYearIds", programYearIds);  
	   }
	   return query.list();
  }
  
  public List<Object[]> getInviteeAttendedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select distinct model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	   			" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	   			" model.attendance.tdpCadre.tdpCadreId from TrainingCampAttendance model " +
	   			" where model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' ");
	   
	   if(fromDate!=null && toDate!=null){
		  sb.append(" and (date(model.attendance.attendedTime) between :fromDate and :toDate)  ");
	  }
	  if(cadreIdsList != null && cadreIdsList.size()>0)
	   {
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:cadreIdsList) ");		  
	   }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		    sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	  }
	   //sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds); 
	   }
	   return query.list();
 }
  
  public List<Long> getAttendedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct model.attendance.tdpCadre.tdpCadreId" +
		  		" from TrainingCampAttendance model where ");
	  sb.append(" model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " );
	  sb.append(" and model.trainingCampBatch.isCancelled='false' ");
	  if(centerId != null && centerId.longValue()>0L)
		  sb.append(" and  model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId=:centerId ");
	  if(programId != null && programId.longValue()>0L)	  
		  sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId ");
	  
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and ( date(model.attendance.attendedTime) between :fromDate and :toDate )  ");
	  }
	  if(cadreIdsLsit != null && cadreIdsLsit.size()>0)
	   {
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:cadreIdsLsit) ");		  
	   }
	  Query query = getSession().createQuery(sb.toString());
	  if(cadreIdsLsit!=null && cadreIdsLsit!=null){
		   query.setParameterList("cadreIdsLsit", cadreIdsLsit);
	  }
	  if(centerId != null && centerId.longValue()>0L)
		  query.setParameter("centerId", centerId);
	  if(programId != null && programId.longValue()>0L)	  
		  query.setParameter("programId", programId);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  return (List<Long>)query.list();
  }
  
  public List<Object[]> getSpeakersAttendedAreaDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct model.attendance.tdpCadre.tdpCadreId,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	  		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	  		" model.trainingCampBatch.trainingCampBatchName,model.trainingCampBatch.trainingCampSchedule.trainingCampId,date(model.attendance.attendedTime) " +
		  		" from TrainingCampAttendance model where ");
	  sb.append(" model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " );
	  sb.append(" and model.trainingCampBatch.isCancelled='false' ");
	  if(centerId != null && centerId.longValue()>0L)
		  sb.append(" and  model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId=:centerId ");
	  if(programId != null && programId.longValue()>0L)	  
		  sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId ");
	  
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and ( date(model.attendance.attendedTime) >= :fromDate and date(model.attendance.attendedTime) <= :toDate ) ");
	  }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) ");
	  }
	  Query query = getSession().createQuery(sb.toString());
	  if(centerId != null && centerId.longValue()>0L)
		  query.setParameter("centerId", centerId);
	  if(programId != null && programId.longValue()>0L)	  
		  query.setParameter("programId", programId);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	  }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds);
	  }
	  return query.list();
  }
  
  public List<Object[]> getSpeakersAttendedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct A.tdp_cadre_id,count(distinct date(A.attended_time))  from training_camp_attendance TCA, attendance A,  " +
		  		" training_camp_batch TCB , attendee_type AT1,training_camp_schedule TCS  where  TCA.training_camp_batch_id = TCB.training_camp_batch_id and ");
	  sb.append(" TCA.attendance_id = A.attendance_id and TCB.attendee_type_id = AT1.attendee_type_id and  TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and " );
	  sb.append(" TCB.attendee_type_id = 2 and AT1.is_deleted='false' and TCB.is_cancelled = 'false'  ");
	  
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and ( date(A.attended_time) >=:fromDate and date(A.attended_time) <= :toDate ) ");
	  }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and TCS.enrollment_year_id in (:enrollmentYearIds)");
	        }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and TCS.training_camp_program_id in (:programYearIds)");
	  }
	  sb.append(" group by A.tdp_cadre_id ");
	  
	  Query query = getSession().createSQLQuery(sb.toString());
	 
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	  }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds); 
	  }
	  return query.list();
  }
  
  
  public List<Object[]> getProgramCampBatchDetailsForAMemberBasedOnCadreId(List<Long> cadreIdList,Date fromDate,Date toDate){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select model.attendance.tdpCadre.tdpCadreId,model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchName, " +
	  		"model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId," +
	  		"model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	  		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
	  		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	  		"model.attendance.attendedTime " +
	  		"from TrainingCampAttendance model " +
	  		"where model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
	  		"and model.trainingCampBatch.isCancelled='false' and model.attendance.tdpCadre.tdpCadreId in (:cadreIdList) ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and date(model.attendance.attendedTime) between :fromDate and :toDate ");
	  }
	  
	  sb.append("group by date(model.attendance.attendedTime), model.trainingCampBatch.trainingCampBatchId " +
	  		"order by date(model.attendance.attendedTime) desc ");
	  
	  Query query = getSession().createQuery(sb.toString());
	  
	  query.setParameterList("cadreIdList", cadreIdList);
	  
	  if(fromDate != null && toDate != null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  
	  return query.list();
	  
  }
  
  public List<Object[]> getTodaySpeakersAttendedDetails(Date toDayDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct A.tdp_cadre_id,TCB.training_camp_batch_id,A.attended_time  from training_camp_attendance TCA, attendance A,  " +
		  		" training_camp_batch TCB , attendee_type AT1,training_camp_schedule TCS" +
		  		"   where  TCA.training_camp_batch_id = TCB.training_camp_batch_id and ");
	  sb.append(" TCA.attendance_id = A.attendance_id and TCB.attendee_type_id = AT1.attendee_type_id and  TCB.training_camp_schedule_id = TCS.training_camp_schedule_id " );
	  sb.append(" and TCB.attendee_type_id = 2 and AT1.is_deleted='false' and TCB.is_cancelled = 'false'");
	  
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and TCS.enrollment_year_id in (:enrollmentYearIds)");
	        }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and TCS.training_camp_program_id in(:programYearIds)");
	  }
	  if(toDayDate!=null){
		  sb.append(" and ( date(A.attended_time) <=:toDayDate ) ");
	  }
	  sb.append(" group by A.tdp_cadre_id ");
	  
	  Query query = getSession().createSQLQuery(sb.toString());
	 
	  if(toDayDate!=null){
		  query.setDate("toDayDate", toDayDate);
	  }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds);
	  }
	  return query.list();
  }
  
  public List<Object[]> getDayWiseInviteeCountsForBatch(Long batchId,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  sb.append(" select count(distinct tcba.tdpCadreId),date(tca.insertedTime) " +
	  									" from TrainingCampAttendance tca,TrainingCampBatchAttendee tcba " +
	  									" where " +
	  									" tca.trainingCampBatchId = tcba.trainingCampBatchId and " +
	  									" tca.trainingCampBatchId=:batchId and tca.attendance.tdpCadreId=tcba.tdpCadreId and tcba.isDeleted='false' " +
	  									" and tca.trainingCampBatch.attendeeTypeId=1 ");
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		  sb.append(" and tcba.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and tcba.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) ");
	  }
	  sb.append(" group by date(tca.insertedTime) ");
	  Query query = getSession().createQuery(sb.toString());
	  query.setParameter("batchId", batchId);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds",programYearIds);
	  }
	  return query.list();
  }
  public List<Long> getInviteeCadreIdsForADay(Long batchId,Date date,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  sb.append("  select distinct model.attendance.tdpCadre.tdpCadreId " +
	  		" from TrainingCampAttendance model " +
	  		" where model.trainingCampBatch.trainingCampBatchId=:batchId " +
	  		" and date(model.attendance.attendedTime)=:date and model.trainingCampBatch.attendeeTypeId=1  ");
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) ");
	  }
	  Query query = getSession().createQuery(sb.toString());
	  query.setParameter("batchId", batchId);
	  query.setParameter("date", date);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds);
	  }
	  return query.list();
  }
  
  public List<Long> getNonInviteesNoDaysCount(Long batchId,List<Long> enrollmentYearIds,List<Long> programYearIds){
	  StringBuilder sb =new StringBuilder();
	  sb.append(" select distinct a.tdpCadreId from TrainingCampAttendance tca,Attendance a " +
	  		"where tca.attendance.attendanceId=a.attendanceId and " +
	  		"a.tdpCadreId not in (select tdpCadreId from TrainingCampBatchAttendee where isDeleted='false' and trainingCampBatchId=:batchId) " +
	  		"and tca.trainingCampBatchId=:batchId and tca.trainingCampBatch.attendeeTypeId=1 " );
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  sb.append(" and tca.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	        }
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and tca.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)"); 
	  }
	  sb.append(" group by date(a.attendedTime),a.tdpCadreId");
	  Query query =getSession().createQuery(sb.toString());
	 /* Query query = getSession().createSQLQuery("select  a.tdp_cadre_id from training_camp_attendance tca,attendance a " +
	  		"where tca.attendance_id=a.attendance_id and " +
	  		"a.tdp_cadre_id not in (select tdp_cadre_id from training_camp_batch_attendee where is_deleted='false' and training_camp_batch_id=:batchId) " +
	  		"and tca.training_camp_batch_id=:batchId group by date(a.attended_time),a.tdp_cadre_id");*/
	  query.setParameter("batchId",batchId);
	  if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	  }
      if(programYearIds != null && programYearIds.size()>0){
    	  query.setParameterList("programYearIds", programYearIds);
	  }
	  return query.list();
  }
  
  public List<Object[]> getNonInviteesCadreBtBatch(Long batchId,Long enrollmentYearId ){
	  StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
	  Query query = getSession().createQuery("select distinct model.attendance.tdpCadre.tdpCadreId,model.attendance.tdpCadre.memberShipNo," +
	   		" model.attendance.tdpCadre.firstname,model.attendance.tdpCadre.mobileNo,model.attendance.tdpCadre.image," +
	   		"model.attendance.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.attendance.tdpCadre.userAddress.constituency.name " +
	  		" from TrainingCampAttendance model " +
	  		" where model.trainingCampBatchId=:batchId and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" " +
	  		" and model.attendance.tdpCadre.tdpCadreId not in (select model1.tdpCadre.tdpCadreId from TrainingCampBatchAttendee model1 " +
	  		"	where model1.trainingCampBatchId=:batchId and model1.isDeleted='false' )  ");
	  query.setParameter("batchId",batchId);
	  if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
		   query.setParameter("enrollmentYearId",enrollmentYearId);
	  
	  return query.list();
  }
  
  public List<Object[]> getAttendedCountsForBatches(List<Long> batchIds,List<Long> enrollmentYrIds,List<Long> programYearIds){
	  StringBuilder sb = new StringBuilder();
	  sb.append(" " +
	  	"select distinct tca.trainingCampBatchId,date(tca.attendance.attendedTime),tca.attendance.tdpCadreId " +
	  	"from   TrainingCampAttendance tca " +
	  	"where  tca.trainingCampBatchId in (:batchIds) and tca.trainingCampBatch.attendeeTypeId=1 and tca.trainingCampBatch.isCancelled='false' ");
	  if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			sb.append(" and tca.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYrIds) ");
		}
	  if(programYearIds != null && programYearIds.size()>0){
		  sb.append(" and tca.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	  }
	  Query query=getSession().createQuery(sb.toString());
	  query.setParameterList("batchIds",batchIds);
	  if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		}
	  if(programYearIds != null && programYearIds.size()>0){
		  query.setParameterList("programYearIds",programYearIds); 
	  }
	  return query.list();
  }
  
  public List<Object[]> getAttendedBatchDetailsByTdpCadreId(Long tdpCadreId,Long programId){
	  
	  Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampBatchId," +
	  						" model.trainingCampBatch.trainingCampBatchName," +
	  						" model.trainingCampProgram.trainingCampProgramId," +
	  						" model.trainingCampProgram.programName" +
	  						" from TrainingCampAttendance model" +
	  						" where model.attendance.tdpCadre.tdpCadreId = :tdpCadreId" +
	  						" and model.trainingCampProgram.trainingCampProgramId = :programId");
	  
	  query.setParameter("tdpCadreId", tdpCadreId);
	  query.setParameter("programId", programId);
	  return query.list();
  }
  
  public Long getAttendedCountByLocation(Long id,String searchType){
	  
	  StringBuilder str = new StringBuilder();
	  str.append("select count(distinct model.attendance.tdpCadre.tdpCadreId)" +
	  					" from TrainingCampAttendance model");
	  
	  if(searchType.equalsIgnoreCase("panchayat"))
		   str.append(" where model.attendance.tdpCadre.userAddress.panchayat.panchayatId = :id");
	   else if(searchType.equalsIgnoreCase("ward"))
		   str.append(" where model.attendance.tdpCadre.userAddress.ward.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("mandal"))
		   str.append(" where model.attendance.tdpCadre.userAddress.tehsil.tehsilId = :id");
	   else if(searchType.equalsIgnoreCase("leb"))
		   str.append(" where model.attendance.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :id");
	   else if(searchType.equalsIgnoreCase("constituency"))
		   str.append(" where model.attendance.tdpCadre.userAddress.constituency.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("parliament"))
		   str.append(" where model.attendance.tdpCadre.userAddress.parliamentConstituency.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("district"))
		   str.append(" where model.attendance.tdpCadre.userAddress.district.districtId = :id");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	  if(searchType != null)
		   query.setParameter("id", id);
	  
	  return (Long) query.uniqueResult();
  }
  
  public List<Long> getAttendedCadreIdsByLocation(Long id,String searchType){
	  
	  StringBuilder str = new StringBuilder();
	  str.append("select distinct model.attendance.tdpCadre.tdpCadreId" +
	  					" from TrainingCampAttendance model");
	  
	  if(searchType.equalsIgnoreCase("panchayat"))
		   str.append(" where model.attendance.tdpCadre.userAddress.panchayat.panchayatId = :id");
	   else if(searchType.equalsIgnoreCase("ward"))
		   str.append(" where model.attendance.tdpCadre.userAddress.ward.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("mandal"))
		   str.append(" where model.attendance.tdpCadre.userAddress.tehsil.tehsilId = :id");
	   else if(searchType.equalsIgnoreCase("leb"))
		   str.append(" where model.attendance.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :id");
	   else if(searchType.equalsIgnoreCase("constituency"))
		   str.append(" where model.attendance.tdpCadre.userAddress.constituency.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("parliament"))
		   str.append(" where model.attendance.tdpCadre.userAddress.parliamentConstituency.constituencyId = :id");
	   else if(searchType.equalsIgnoreCase("district"))
		   str.append(" where model.attendance.tdpCadre.userAddress.district.districtId = :id");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	  if(searchType != null)
		   query.setParameter("id", id);
	  
	  return query.list();
  }
  public List<Object[]> getTotalAttenedCadresByTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate){

	  StringBuilder queryStr= new StringBuilder();
		  
	  queryStr.append(" select model.trainingCampProgram.trainingCampProgramId,model.trainingCampProgram.programName,count(distinct model.attendance.tdpCadre.tdpCadreId) " +
	  		          " from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 where " +
	  		          " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
	  		          " model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
	  		          " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
	  		          " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
	  		          " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and " +
	  		          " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y'" +
	  		          " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender  ");
	
	 if(stateId != null && stateId.longValue() > 0){
		 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId");
	 }
	 if(toDate!=null){
	  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
	 }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
        queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
         queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	}
	   queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	   }
	   if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);  
	   }
	   if(toDate!=null){
		 query.setDate("toDate", toDate);  
	   }
		  return query.list();  
	  }
    public List<Object[]> getTotalAttenedCadresByCommitteeLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate)
	{
		StringBuilder queryStr= new StringBuilder();
		  
		queryStr.append(" select model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,count(distinct model.attendance.tdpCadre.tdpCadreId) " +
	  		          " from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 " +
	  		          " where model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
	  		          " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
	  		          " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
	  		          " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
	  		          " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and" +
	  		          " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y'" +
	  		          " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender ");
		 if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId");
		 }
		 if(toDate!=null){
			  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		}
		queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
	   
		Query query = getSession().createQuery(queryStr.toString());
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(stateId != null && stateId.longValue() > 0){
				 query.setParameter("stateId", stateId);  
		}
		if(toDate!=null){
		  query.setDate("toDate", toDate);    
		}
		  	return query.list();  
	}
  		
    public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate, String status, Long distId){

		  StringBuilder queryStr= new StringBuilder();
			  
		  queryStr.append(" select ");
		  if(status.equalsIgnoreCase("camp")){ 
			  queryStr.append(" distinct ");
		  }
		  queryStr.append(" model.trainingCampProgram.trainingCampProgramId," +//0
		  	 	          " model.trainingCampProgram.programName," +//1
		  		          " model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId," +//2
		  		          " model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName,");//3
		  if(status.equalsIgnoreCase("camp")){
			  queryStr.append(" model.attendance.tdpCadre.tdpCadreId ");//4
		  }else{
			  queryStr.append(" count(distinct model.attendance.tdpCadre.tdpCadreId) ");//4
		  }
		  		        
		  queryStr.append(" from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 " +
	  		              " where model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
	  		              " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
	  		              " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
	  		              " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
	  		              " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and" +
	  		              " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y'" +
	  		              " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender ");
		  if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId");
		  }
		  if(distId != null && distId.longValue() > 0){
				 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId=:distId");
		  }
		  if(toDate!=null){
			  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		}
		if(status.equalsIgnoreCase("leadership")){
			queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId,model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId ");
		}
		
		queryStr.append(" order by  model.trainingCampProgram.trainingCampProgramId asc ");
	   
		Query query = getSession().createQuery(queryStr.toString());
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
	   	if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);  
	   	}
	   	if(distId != null && distId.longValue() > 0){
			 query.setParameter("distId", distId);     
	   	}
	   	if(toDate!=null){
			  query.setDate("toDate", toDate);  
	   	}

		  return query.list();  
	  }
  	  public List<Object[]> getUserWiseTotalAttenedCadresCntForTrainingProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate)
  		{
  			StringBuilder queryStr= new StringBuilder();
  			
  			    queryStr.append("select ");
  		      if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
  		         queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId,");  
  		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
  		        queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId, ");  
  		     // queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId, ");  
  		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
  		          queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId, ");  
  		      //  queryStr.append(" model.attendance.tdpCadre.userAddress.parliamentConstituency.constituencyId, ");  
  			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
  		          queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId, ");  
  			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
  		         queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");  
    		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
  		         queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,"); 
    		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
  		       queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,"); 
    		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
  		      queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,"); 
    		  }
  			   queryStr.append(" count(distinct model.attendance.tdpCadre.tdpCadreId) " +
		  		          " from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 " +
	  		              " where model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
	  		              " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
	  		              " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
	  		              " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
	  		              " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and" +
	  		              " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y' " +
	  		              " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender ");
  			 if(stateId != null && stateId.longValue() > 0){
  				 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId ");
  			 }
  			  if(toDate!=null){
  				  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
  			 }
		   /* if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
  			}*/
	        if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append("group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId ");  
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId "); 
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" group by  model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId "); 
  			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){
  				 queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId");	
  			} 
		   
  			Query query = getSession().createQuery(queryStr.toString());
  			/*if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
  				query.setParameterList("userAccessLevelValues", userAccessLevelValues);
  			}*/
  		  if(stateId != null && stateId.longValue() > 0){
  			 query.setParameter("stateId", stateId);  
  		   }
  		  if(toDate!=null){
			  query.setDate("toDate", toDate);  
	     }
		 return query.list();  
  		}
  	
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByLocationType(Long userAccessLevelId,List<Long> userAccessLevelValues,String locationType,Long stateId,Date toDate,List<Long> enrollmentYearIds,List<Long> programIdList){

	     StringBuilder queryStr= new StringBuilder();
		  
         queryStr.append(" select " );
	      if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,"); //1
	         queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName,"); //2
	        } 
	        if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,"); //3
	  	     queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.name,"); //4
	  	    }
	        if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");
	         queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilName,");
	        }
            if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,");
	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatName,");
	        }
            if(locationType != null && locationType.equalsIgnoreCase("Ward")){
    	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,");
    	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.name,");
    	     }
            if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
    	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,");
    	        queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.name,");
    	    }
    	        
            
		     queryStr.append(" count(distinct model.attendance.tdpCadre.tdpCadreId),model3.tdpCommitteeRole.tdpRolesId " + //5
		                  " from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 " +
	  		              " where model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
	  		              " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
	  		              " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
	  		              " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
	  		              " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and" +
	  		              " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y'" +
	  		              " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender ");
	 if(programIdList != null && programIdList.size()>0){
		 queryStr.append(" and  model.trainingCampProgram.trainingCampProgramId in (:programIdList) ");
	 }
	 
	 if(stateId != null && stateId.longValue() > 0){
		queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId");
	 }
	  if(toDate!=null){
			  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
	  }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
       queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
        queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	}
	if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  queryStr.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
	    }
   if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId asc"); //1
   } 
   if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
    queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId asc"); //3
	    }
   if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	   queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId asc"); //1  
   }  
   if(locationType != null && locationType.equalsIgnoreCase("Village")){
	   queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId asc"); //1   
   }
   if(locationType != null && locationType.equalsIgnoreCase("Ward")){
	queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId asc");   
   }
   if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
	 queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,model3.tdpCommitteeRole.tdpRolesId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId asc");  
   }
   Query query = getSession().createQuery(queryStr.toString());
   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
   }
   if(stateId != null && stateId.longValue() > 0){
	 query.setParameter("stateId", stateId);  
   }
	  if(toDate!=null){
	   query.setDate("toDate", toDate);    
  }
   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		  query.setParameterList("enrollmentYearIds", enrollmentYearIds);
   }
   if(programIdList != null && programIdList.size()>0){
	   query.setParameterList("programIdList", programIdList);
   }
		  return query.list();    
  }
	public List<Object[]> getTotalAttendedForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate, List<Date> dateList, String option,List<Long> enrollYrIds){   
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName ,");
		if(option.equalsIgnoreCase("dayWise") && programIdList.size() == 1 && programIdList.get(0) != 6){
			queryString.append(" date(TCBA.attendedTime), ");     
		}
		queryString.append(" count(distinct TC.tdpCadreId) from " +
						   " TdpCadre TC, TrainingCampBatchAttendee TCBA where  TCBA.trainingCampBatch.attendeeTypeId = 1  ");  
		if(programIdList != null && programIdList.size()>0){
			queryString.append(" and TCBA.trainingCampBatch.isCancelled='false' and  TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList)  ");  
		}
		if(option.equalsIgnoreCase("dayWise") && programIdList.size() == 1 && programIdList.get(0) != 6){
			queryString.append(" and date(TCBA.attendedTime) in (:dateList)  ");  
		}else{
			queryString.append(" and date(TCBA.attendedTime) <= (:toDate)  ");  
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" and TCBA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollYrIds)   " );
		}
		if(stateId.longValue() == 1L){
			queryString.append(" and TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")  ");
		}else{
			queryString.append(" and TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")  ");
		}
		queryString.append(" and TCBA.tdpCadre.tdpCadreId = TC.tdpCadreId and TC.enrollmentYear = 2014 and TC.isDeleted='N' ");
		if(option.equalsIgnoreCase("dayWise") && programIdList.size() == 1 && programIdList.get(0) != 6){
			queryString.append("group by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, date(TCBA.attendedTime) " +
							   " order by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, date(TCBA.attendedTime) ");
		}else{
			queryString.append(" group by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId " +
			           		   " order by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		}
				    
		Query query = getSession().createQuery(queryString.toString()); 
		if(programIdList != null && programIdList.size()>0){
			query.setParameterList("programIdList", programIdList);
		}
		if(option.equalsIgnoreCase("dayWise") && programIdList.size() == 1 && programIdList.get(0) != 6){
			query.setParameterList("dateList", dateList);
		}else{
			query.setDate("toDate", toDate);  
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
		return  query.list();
	}
	public List<Object[]> getStateDistrictTrainingProgramAttendedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select " +
						   " TCL.tdp_committee_level_id as id, " +
						   " TCL.tdp_committee_level as level, " +
						   " count(distinct TCM.tdp_cadre_id) as total " +
						   " from "+
						   " tdp_committee_member TCM, " +
						   " tdp_committee_role TCR, " +
						   " tdp_committee TC, " +
						   " tdp_committee_level TCL, " +
						   " tdp_cadre TDP, "+
						   " training_camp_attendance TCA, " +
						   " training_camp_schedule TCS, " +
						   " attendance A, " +
						   " training_camp_batch_attendee TCBA, " + 
						   " training_camp_batch TCB, " +
						   " user_address UA, " +
						   " district D "+    
						   " where "+
						   " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
						   " TCA.attendance_id = A.attendance_id and " +
						   " date(A.attended_time) <= (:toDate) and "+  
						   " TDP.tdp_cadre_id = A.tdp_cadre_id and " +
						   " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
						   " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
				   		   " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+  
						   " TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and "+
						   " TCR.tdp_committee_id = TC.tdp_committee_id and "+
						   " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and "+
						   " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
						   " TDP.address_id = UA.user_address_id and " +
						   " UA.district_id = D.district_id and ");
		if(programIdList != null && programIdList.size() >0){
			queryString.append("  TCS.training_camp_program_id in (:programIdList) and ");
		}
		if(stateId == 1l){
			queryString.append(" (D.district_id BETWEEN 1 and 23) ");
		}else{
			queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		}	
		
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" and TCS.enrollment_year_id in (:enrollYrIds)  " );
		}
		queryString.append(" group by "+
						   " TCL.tdp_committee_level_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("total", Hibernate.LONG);  
		//query.setParameter("campId", campId);
		query.setDate("toDate", toDate);  
		if(programIdList != null && programIdList.size() >0){
			query.setParameterList("programIdList", programIdList);
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
		return query.list();
	}
	 public List<Object[]> getMlaMpInchargeTrainingProgramAttendedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){
		 StringBuilder queryString = new StringBuilder();
		 queryString.append(" select " +
		 					" PRT.public_representative_type_id as id," +
		 					" PRT.position as position," +
		 					" count(distinct TDP.tdp_cadre_id) as total " +
		 					" from "+
				 			" candidate CND, " +
				 			" tdp_cadre_candidate TCC, " +
				 			" public_representative PR, " +
				 			" public_representative_type PRT, " +
				 			" tdp_cadre TDP, "+
				 			" training_camp_attendance TCA, " +
				 			" training_camp_schedule TCS, " +
				 			" attendance A, " +
				 			" training_camp_batch_attendee TCBA, " +
				 			" training_camp_batch TCB, " +
				 			" user_address UA, " +
				 			" district D "+
				 			" where " +
				 			" TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
		   					" TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+  
				 			" TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
				 			//" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				 			" TCA.attendance_id = A.attendance_id and " +
				 			" date(A.attended_time) <= (:toDate) and "+
				 			" TDP.tdp_cadre_id = A.tdp_cadre_id and " +
				 			" TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
				 			" TCC.candidate_id = CND.candidate_id and "+
				 			" PR.candidate_id = CND.candidate_id and "+
				 			" PR.public_representative_type_id = PRT.public_representative_type_id and "+
				 			" TCC.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
				 			" TDP.address_id = UA.user_address_id and " +
				 			" UA.district_id = D.district_id and ");
		 if(programIdList != null && programIdList.size() >0){
			 queryString.append(" TCS.training_camp_program_id in (:programIdList) and ");
		 }
		 if(stateId == 1l){
				queryString.append(" (D.district_id BETWEEN 1 and 23) ");
		}else{
				queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		}	
		 
		 if(enrollYrIds != null && enrollYrIds.size() >0){
				queryString.append(" and TCS.enrollment_year_id in (:enrollYrIds)   " );
			}
		 queryString.append(" group by PRT.public_representative_type_id ");  
		 SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("total", Hibernate.LONG);
		 //query.setParameter("campId", campId);
		 if(programIdList != null && programIdList.size() >0){
			 query.setParameterList("programIdList", programIdList);
		 }
		 query.setDate("toDate", toDate); 
		 if(enrollYrIds != null && enrollYrIds.size() >0){
				query.setParameterList("enrollYrIds", enrollYrIds);
			}
		 return query.list();
	 }
	 public List<Object[]> getDestWiseAttendedMembers(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){
		 StringBuilder queryString = new StringBuilder();
		 queryString.append(" select TCP.training_camp_program_id as programId,TCP.program_name as programName," +
		 					" D.district_id as id,D.district_name as name, count(distinct A.tdp_cadre_id) as total " +
		 					" from " +
		 					" training_camp_attendance TCA, training_camp_schedule TCS, training_camp_program TCP," +
		 					" attendance A, training_camp_batch_attendee TCBA,  " +
		 					" tdp_cadre TC, user_address UA, district D, training_camp_batch TCB "+
				   			  " where "+
				   			  " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and " +
				   			  " TCS.training_camp_program_id = TCP.training_camp_program_id and "+
				   			  //" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				   			  " TCA.attendance_id = A.attendance_id and "+
				   			  " TC.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TC.tdp_cadre_id and " +
				   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and " +
				   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+    
				   			  " TC.address_id = UA.user_address_id and " +  
				   			  " UA.district_id = D.district_id and " + 
				   			  " date(A.attended_time) <= (:toDate) and ");
		 
		 if(programIdList != null && programIdList.size() >0){
			 queryString.append(" TCP.training_camp_program_id in (:programIdList) and ");  
		 }
		 if(stateId == 1l){
			   queryString.append(" (D.district_id BETWEEN 1 and 23) ");  
		 }else{
			   queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		 }
		 
		 if(enrollYrIds != null && enrollYrIds.size() >0){
				queryString.append(" and TCS.enrollment_year_id in (:enrollYrIds)   " );
			}
		 queryString.append(" group by TCP.training_camp_program_id,D.district_id order by D.district_id ");
		 SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("programId", Hibernate.LONG).addScalar("programName", Hibernate.STRING).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("total", Hibernate.LONG);
		 //query.setParameter("campId", campId);
		 if(programIdList != null && programIdList.size() >0){
			 query.setParameterList("programIdList", programIdList);  
		 }
		 query.setDate("toDate", toDate);
		 if(enrollYrIds != null && enrollYrIds.size() >0){
				query.setParameterList("enrollYrIds", enrollYrIds);
			}
		 return query.list();
	}
	
	 public List<Object[]> getAttendedMemberCadreOverview(Long distId,Long programId){
			StringBuilder queryString = new StringBuilder();
			   queryString.append(" select distinct D.district_id as id,D.district_name as name, A.tdp_cadre_id as tid,count(distinct date(A.inserted_time)) as count from "+
					   			  " training_camp_attendance TCA, training_camp_schedule TCS, attendance A, training_camp_batch_attendee TCBA, "+
					   			  " tdp_cadre TC, user_address UA, district D "+
					   			  " where "+
					   			  " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
					   			  " TCS.training_camp_program_id = :programId and "+
					   			  " TCA.attendance_id = A.attendance_id and "+
					   			  " TC.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TC.tdp_cadre_id and "+
					   			  " TC.address_id = UA.user_address_id and "+
					   			  " D.district_id = (:distId) and "+
					   			  " UA.district_id = D.district_id ");
			   queryString.append(" group by D.district_id, A.tdp_cadre_id " +
			   		" order by count(distinct date(A.inserted_time)) desc ");
			 
			   SQLQuery query = getSession().createSQLQuery(queryString.toString())
					   .addScalar("id", Hibernate.LONG)
					   .addScalar("name", Hibernate.STRING)
					   .addScalar("tid", Hibernate.LONG)
					   .addScalar("count", Hibernate.LONG);
			   query.setParameter("distId", distId);
			   query.setParameter("programId", programId);
			   
			   return query.list(); 
		}
	 
	public List<Object[]> getAttendedMemberCadreId(Long distId,Long programId,List<Date> datesList){
		StringBuilder queryString = new StringBuilder();
		   queryString.append(" select distinct D.district_id as id,D.district_name as name,  A.tdp_cadre_id as tid from "+
				   			  " training_camp_attendance TCA, training_camp_schedule TCS, attendance A, training_camp_batch_attendee TCBA, "+
				   			  " tdp_cadre TC, user_address UA, district D "+
				   			  " where "+
				   			  " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
				   			  " TCS.training_camp_program_id = (:programId) and "+
				   			  " TCA.attendance_id = A.attendance_id and "+
				   			  " TC.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TC.tdp_cadre_id and "+
				   			  " TC.address_id = UA.user_address_id and "+
				   			  " D.district_id = (:distId) and "+
				   			  " UA.district_id = D.district_id ");
		   
		   if(datesList != null && datesList.size()>0)
			   queryString.append(" and date(A.inserted_time) in (:datesList) ");
		   
		   queryString.append(" order by D.district_id  ");
		   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("tid", Hibernate.LONG);
		   query.setParameter("distId", distId);
		   query.setParameter("programId", programId);
		   if(datesList != null && datesList.size()>0)
			   query.setParameterList("datesList", datesList);
		   
		   return query.list(); 
	}
	public List<Object[]> getMembersDetails(List<Long> attendedCadreIds){
	    StringBuilder queryString = new StringBuilder();
	       queryString.append(" SELECT TC.tdp_cadre_id,TC.first_name,PRT.position,TR.role,TCL.tdp_committee_level,TC.mobile_no,  D.district_name , PRT.public_representative_type_id" +//
	                   " FROM tdp_cadre TC "+
	                   " LEFT OUTER JOIN tdp_cadre_candidate TCC ON TC.tdp_cadre_id = TCC.tdp_cadre_id "+
	                   " LEFT OUTER JOIN public_representative  PR ON TCC.candidate_id = PR.candidate_id "+
	                   " LEFT OUTER JOIN public_representative_type PRT ON PR.public_representative_type_id = PRT.public_representative_type_id "+
	                   " LEFT OUTER JOIN tdp_committee_member TCM ON TC.tdp_cadre_id = TCM.tdp_cadre_id AND TCM.is_active = 'Y' "+
	                   " LEFT OUTER JOIN tdp_committee_role TCR ON TCM.tdp_committee_role_id = TCR.tdp_committee_role_id "+
	                   " LEFT OUTER JOIN tdp_roles TR ON TCR.tdp_roles_id = TR.tdp_roles_id "+
	                   " LEFT OUTER JOIN tdp_committee TCT ON TCR.tdp_committee_id = TCT.tdp_committee_id "+
	                   " LEFT OUTER JOIN tdp_committee_level TCL ON TCT.tdp_committee_level_id = TCL.tdp_committee_level_id " +
	                   " LEFT OUTER JOIN user_address UA ON UA.user_address_id =  TC.address_id " +
	                   " LEFT OUTER JOIN district D ON D.district_id = UA.district_id "+
	                   " WHERE "+
	                   " TC.enrollment_year = 2014 AND "+
	                   " TC.is_deleted = 'N' AND "+
	                   " TC.tdp_cadre_id IN (:attendedCadreIds) ");
	       SQLQuery query = getSession().createSQLQuery(queryString.toString())
	           .addScalar("tdp_cadre_id", Hibernate.LONG)
	           .addScalar("first_name", Hibernate.STRING)
	           .addScalar("position", Hibernate.STRING)
	           .addScalar("role", Hibernate.STRING)
	           .addScalar("tdp_committee_level", Hibernate.STRING)
	           .addScalar("mobile_no", Hibernate.STRING)
	           .addScalar("district_name", Hibernate.STRING)
	           .addScalar("public_representative_type_id", Hibernate.LONG);
	           
	       query.setParameterList("attendedCadreIds", attendedCadreIds);
	        
	       return query.list(); 
	  }
	/*public List<Object[]> getAbsaentMembersForDist(List<Long> absentCadreIds){
		StringBuilder queryString = new StringBuilder();
		   queryString.append(" SELECT TC.tdp_cadre_id,TC.first_name,PRT.position,TR.role,TCL.tdp_committee_level,TC.mobile_no "+
				   			  " FROM tdp_cadre TC "+
				   			  " LEFT OUTER JOIN tdp_cadre_candidate TCC ON TC.tdp_cadre_id = TCC.tdp_cadre_id "+
				   			  " LEFT OUTER JOIN public_representative  PR ON TCC.candidate_id = PR.candidate_id "+
				   			  " LEFT OUTER JOIN public_representative_type PRT ON PR.public_representative_type_id = PRT.public_representative_type_id "+
				   			  " LEFT OUTER JOIN tdp_committee_member TCM ON TC.tdp_cadre_id = TCM.tdp_cadre_id AND TCM.is_active = 'Y' "+
				   			  " LEFT OUTER JOIN tdp_committee_role TCR ON TCM.tdp_committee_role_id = TCR.tdp_committee_role_id "+
				   			  " LEFT OUTER JOIN tdp_roles TR ON TCR.tdp_roles_id = TR.tdp_roles_id "+
				   			  " LEFT OUTER JOIN tdp_committee TCT ON TCR.tdp_committee_id = TCT.tdp_committee_id "+
				   			  " LEFT OUTER JOIN tdp_committee_level TCL ON TCT.tdp_committee_level_id = TCL.tdp_committee_level_id "+
				   			  " WHERE "+
				   			  " TC.enrollment_year = 2014 AND "+
				   			  " TC.is_deleted = 'N' AND "+
				   			  " TC.tdp_cadre_id IN (:absentCadreIds) ");
		   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("tdp_cadre_id", Hibernate.LONG).addScalar("first_name", Hibernate.STRING).addScalar("position", Hibernate.STRING).addScalar("role", Hibernate.STRING).addScalar("tdp_committee_level", Hibernate.STRING).addScalar("mobile_no", Hibernate.STRING);
		   query.setParameterList("absentCadreIds", absentCadreIds);
		    
		   return query.list();   
	}*/
	// attended count query
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate, String status, Long locationId,String locationType,Long userType,String levelType,List<Long> enrollmentYearIds,List<Long> trainingCampProgramIds){

		  StringBuilder queryStr= new StringBuilder();
			  
		  queryStr.append(" select ");
		  if(status.equalsIgnoreCase("camp")){
			  queryStr.append(" distinct ");
		  }
		  queryStr.append(" model.trainingCampProgram.trainingCampProgramId," +//0
  	 	                   " model.trainingCampProgram.programName,"); //1
  
		  if(status.equalsIgnoreCase("leadership")){
		  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	  	          queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,");
	  	          queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName,"); 
	     }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	  	|| userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userType.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
	  	 	      queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,");
		    	  queryStr.append("model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.name,"); 
		   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
			     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
			     queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");
			     queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilName,");
			     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
			     queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,");
			     queryStr.append(" model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.name,");
			     }
		   }
	     }
		
		  if(status.equalsIgnoreCase("camp")){
			  queryStr.append(" model.attendance.tdpCadre.tdpCadreId ");//0
		  }else{
			  queryStr.append(" count(distinct model.attendance.tdpCadre.tdpCadreId) ");//4
		  }
		  		        
		  queryStr.append(" from TrainingCampAttendance model,TrainingCampEligbleDesignation model2,TdpCommitteeMember model3 " +
			              " where model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId and " +
			              " model.trainingCampProgram.trainingCampProgramId = model2.trainingCampProgram.trainingCampProgramId and " +
			              " model2.tdpBasicCommittee.tdpBasicCommitteeId = model3.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId and " +
			              " model2.tdpCommitteeLevel.tdpCommitteeLevelId = model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId and " +
			              " model2.tdpRoles.tdpRolesId = model3.tdpCommitteeRole.tdpRoles.tdpRolesId and" +
			              " model.attendance.tdpCadre.isDeleted='N' and model.attendance.tdpCadre.enrollmentYear=2014 and model3.isActive='Y'" +
			              " and model3.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and model3.tdpCadre.gender=model2.gender ");
		  if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId");
		  }
		  if(status != null && status.equalsIgnoreCase("camp")){
			  if(locationType != null && locationType.equalsIgnoreCase("district")){
				  if(locationId != null && locationId.longValue() > 0){
					  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId=:locationId "); 
				  }
			  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
				  if(locationId != null && locationId.longValue() > 0){
					  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId=:locationId");  
				  }
			  }else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
				  if(locationId != null && locationId.longValue() > 0){
					   queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId=:locationId"); 
				  } 
			  }else if(locationType != null && locationType.equalsIgnoreCase("townDivision")){
				  if(locationId != null && locationId.longValue() > 0){
					  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId=:locationId");  
				  }
			  }
		  }
		  if(toDate!=null){
			  queryStr.append(" and date(model.attendance.attendedTime)<=:toDate ");	 
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		}
		 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			 queryStr.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYearIds) ");
		 }
		 if(trainingCampProgramIds != null && trainingCampProgramIds.size() > 0){
			queryStr.append(" and model.trainingCampProgram.trainingCampProgramId in (:trainingCampProgramIds) ");
		 }
		 
		if(status.equalsIgnoreCase("leadership")){
			
			queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId");
			 if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		    	  queryStr.append(",model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId");
		       }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		    	|| userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userType.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		    	 	  queryStr.append(",model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId");
			   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
				     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
				     queryStr.append(",model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId");
				     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
				     queryStr.append(",model3.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId");
				     }
			   }
		}
		
		queryStr.append(" order by  model.trainingCampProgram.trainingCampProgramId asc ");
	 
		Query query = getSession().createQuery(queryStr.toString());
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
	 	if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);  
	 	}
	 	if(locationId != null && locationId.longValue() > 0){
			 query.setParameter("locationId", locationId);     
	 	}
	 	if(toDate!=null){
			  query.setDate("toDate", toDate);  
	 	}
	 	if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
	 		query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	 	}
	 	if(trainingCampProgramIds != null && trainingCampProgramIds.size() > 0){
			   query.setParameterList("trainingCampProgramIds", trainingCampProgramIds);
		}
		  return query.list();  
	}
	public Date getLastUpdatedTime()
	{
		Query query=getSession().createQuery("select max(model.insertionTime) from TrainingCampAttendance model");
		  return (Date) query.uniqueResult();
	}
	public List<Object[]> getStDistTrainingPrgAttendedDtlsCmtLvL(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList){
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select distinct " +
						   " TCL.tdp_committee_level_id as id, " +
						   " TCL.tdp_committee_level as level, " +
						   " TCM.tdp_cadre_id as tdpCadre " +
						   " from "+
						   " tdp_committee_member TCM, " +
						   " tdp_committee_role TCR, " +
						   " tdp_committee TC, " +
						   " tdp_committee_level TCL, " +
						   " tdp_cadre TDP, "+
						   " training_camp_attendance TCA, " +
						   " training_camp_schedule TCS, " +
						   " attendance A, " +
						   " training_camp_batch_attendee TCBA, " + 
						   " training_camp_batch TCB, " +
						   " user_address UA, " +
						   " district D "+    
						   " where "+
						   " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
						   " TCS.training_camp_program_id in (:programIdList) and "+
						   " TCA.attendance_id = A.attendance_id and " +
						   " date(A.attended_time) <= (:toDate) and "+  
						   " TDP.tdp_cadre_id = A.tdp_cadre_id and " +
						   " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
						   " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
				   		   " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+  
						   " TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and "+
						   " TCR.tdp_committee_id = TC.tdp_committee_id and "+
						   " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and " +
						   " TCL.tdp_committee_level_id in (:designationIdList) and "+
						   " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
						   " TDP.address_id = UA.user_address_id and " +
						   " UA.district_id = D.district_id and ");
		if(stateId == 1l){
			queryString.append(" (D.district_id BETWEEN 1 and 23) ");
		}else{
			queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		}  
		SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("tdpCadre", Hibernate.LONG);  
		//query.setParameter("campId", campId);
		query.setDate("toDate", toDate);  
		query.setParameterList("programIdList", programIdList);
		query.setParameterList("designationIdList", designationIdList); 
		return query.list();
	}
	public List<Object[]> getMlaMpInchargeTrngPrgAttendedDtlsPubRep(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList){
		 StringBuilder queryString = new StringBuilder();
		 queryString.append(" select distinct " +
		 					" PRT.public_representative_type_id as id," +
		 					" PRT.position as position," +
		 					" TDP.tdp_cadre_id as tdpCadre " +  
		 					" from "+
				 			" candidate CND, " +
				 			" tdp_cadre_candidate TCC, " +
				 			" public_representative PR, " +
				 			" public_representative_type PRT, " +
				 			" tdp_cadre TDP, "+
				 			" training_camp_attendance TCA, " +
				 			" training_camp_schedule TCS, " +
				 			" attendance A, " +
				 			" training_camp_batch_attendee TCBA, " +
				 			" training_camp_batch TCB, " +
				 			" user_address UA, " +
				 			" district D "+
				 			" where " +
				 			" TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
		   					" TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+  
				 			" TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
				 			//" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				 			" TCS.training_camp_program_id in (:programIdList) and "+
				 			" TCA.attendance_id = A.attendance_id and " +
				 			" date(A.attended_time) <= (:toDate) and "+
				 			" TDP.tdp_cadre_id = A.tdp_cadre_id and " +
				 			" TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
				 			" TCC.candidate_id = CND.candidate_id and "+
				 			" PR.candidate_id = CND.candidate_id and "+
				 			" PR.public_representative_type_id = PRT.public_representative_type_id and " +
				 			" PRT.public_representative_type_id in (:designationIdList) and "+
				 			" TCC.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
				 			" TDP.address_id = UA.user_address_id and " +
				 			" UA.district_id = D.district_id and ");
		 if(stateId == 1l){  
				queryString.append(" (D.district_id BETWEEN 1 and 23) ");
		}else{
				queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		}	   		  
		
		 SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("tdpCadre", Hibernate.LONG);
		 //query.setParameter("campId", campId);
		 query.setParameterList("programIdList", programIdList);
		 query.setParameterList("designationIdList", designationIdList);
		 query.setDate("toDate", toDate);  
		 return query.list();
	 }
	public List<Object[]> getDestictWiseAttendedMembers(List<Long> programIdList, Long stateId, Date toDate){
		 StringBuilder queryString = new StringBuilder();
		 queryString.append(" select TCP.training_camp_program_id as programId,TCP.program_name as programName,D.district_id as id,D.district_name as name, " +
		 		" count(distinct A.tdp_cadre_id) as total,date(A.attended_time) as date from training_camp_attendance TCA, training_camp_schedule TCS, training_camp_program TCP,  attendance A, training_camp_batch_attendee TCBA, " +
		 					" tdp_cadre TC, user_address UA, district D "+
				   			  " where "+
				   			  " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and " +
				   			  " TCS.training_camp_program_id = TCP.training_camp_program_id and "+
				   			  //" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				   			  " TCP.training_camp_program_id in (:programIdList) and "+
				   			  " TCA.attendance_id = A.attendance_id and "+
				   			  " TC.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TC.tdp_cadre_id and "+
				   			  " TC.address_id = UA.user_address_id and " +
				   			  " UA.district_id = D.district_id  and " +
				   			  " date(A.attended_time) <= (:toDate) and ");
		 if(stateId == 1l){
			   queryString.append(" (D.district_id BETWEEN 1 and 23) ");  
		 }else{
			   queryString.append(" (D.district_id BETWEEN 1 and 10) ");
		 }
		 queryString.append(" group by TCP.training_camp_program_id,D.district_id,date(A.attended_time) order by D.district_id ");
		 SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("programId", Hibernate.LONG).addScalar("programName", Hibernate.STRING).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("total", Hibernate.LONG).addScalar("date", Hibernate.DATE);
		// query.setParameter("campId", campId);
		 query.setParameterList("programIdList", programIdList);  
		 query.setDate("toDate", toDate);  
		 return query.list();
	}
	public List<Object[]> getDayWisePresent(List<Long> programIdList,Long stateId,List<String> dateList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" date(A.attended_time) as date, A.tdp_cadre_id as  tdpCadre "+
						" from "+
						" training_camp_attendance TCA, training_camp_schedule TCS, "+
						" training_camp_program TCP,  attendance A, "+
						" training_camp_batch_attendee TCBA,  tdp_cadre TC, "+
						" user_address UA, district D, training_camp_batch TCB  "+
						" where  "+
						" TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+
						" TCS.training_camp_program_id = TCP.training_camp_program_id and  "+
						" TCP.training_camp_program_id in (:programIdList) and  "+
						" TCA.attendance_id = A.attendance_id and  "+
						" TC.tdp_cadre_id = A.tdp_cadre_id and "+
						" TCBA.tdp_cadre_id = TC.tdp_cadre_id and "+
						" TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
						" TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
						" TC.address_id = UA.user_address_id and  "+
						" UA.district_id = D.district_id and  "+
						" date(A.attended_time) in (:dateList) and  ");
		if(stateId == 1l){
			queryStr.append(" (D.district_id BETWEEN 1 and 23) ");  
		}else{
			queryStr.append(" (D.district_id BETWEEN 1 and 10) ");
		}
		queryStr.append(" group by date(A.attended_time),A.tdp_cadre_id order by A.tdp_cadre_id");
		SQLQuery query = getSession().createSQLQuery(queryStr.toString()).addScalar("date", Hibernate.STRING).addScalar("tdpCadre",Hibernate.LONG);
		query.setParameterList("programIdList",programIdList);
		query.setParameterList("dateList",dateList);
		return query.list();
		
	}
	
	public List<Object[]> getTrainingCampAttendanceSummary(List<Long> cadreIds){
		Query query = getSession().createQuery(" select model.attendance.tdpCadre.tdpCadreId, count(distinct model.trainingCampBatchId) " +
				" from TrainingCampAttendance model " +
				" where model.attendance.tdpCadre.tdpCadreId in (:cadreIds) " +
				" group by model.attendance.tdpCadre.tdpCadreId ");
		
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	
	public List<Object[]> getBatchIds(List<Long> tdpCadreIdsList){
		Query query = getSession().createQuery(" select distinct model.trainingCampBatch.trainingCampBatchId,model.attendance.tdpCadre.tdpCadreId " +
				" ,model.attendance.attendanceId," +
				" date(model.attendance.attendedTime),model.trainingCampProgram.durationInDays " +
				" from TrainingCampAttendance model " +
				" where model.attendance.tdpCadre.tdpCadreId in (:tdpCadreIdsList) ");	
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getCampDetails(List<Long> batchIds){
		Query query = getSession().createQuery(" select distinct model.trainingCampBatch.trainingCampBatchId, " +
				" model.trainingCampProgram.programName," +
				" model.trainingCampSchedule.trainingCamp.campName, model.trainingCampBatch.trainingCampBatchName," +
				" date(model.trainingCampBatch.fromDate), date(model.trainingCampBatch.toDate), " +
				" model.trainingCampProgram.durationInDays " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) " );
		query.setParameterList("batchIds", batchIds);
	  return query.list();
	}
	
	public List<Object[]> getInviteeAttendedCount(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programIds,List<Long> campIds ,List<Long> scheduleIds,List<Long> roleIds,Date startDate,Date endDate,Set<Long> staffCadreIds){
		   Long committeeEnrollmentYrId = 0l;
		   if(enrollmentYearIds != null && enrollmentYearIds.contains(4l)){
			   committeeEnrollmentYrId = 2l;
		   }else if(enrollmentYearIds != null && enrollmentYearIds.contains(3l)){
			   committeeEnrollmentYrId = 1l;
		   }
		   StringBuilder sb=new StringBuilder();
		   sb.append(" select  model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId," +//0
		   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +//1
		   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName," +//2
		   		"model.trainingCampSchedule.trainingCamp.trainingCampId," +//3
		   		"model.trainingCampSchedule.trainingCamp.campName," +//4
		   		"model.trainingCampSchedule.trainingCampScheduleId," +//5
		   		"model.trainingCampSchedule.fromDate," +//6
		   		"model.trainingCampSchedule.toDate," +//7
		   		"model.trainingCampSchedule.trainingCampScheduleCode," +//8
		   		"model.trainingCampBatch.trainingCampBatchId," +//9
		   		"model.trainingCampBatch.trainingCampBatchName," +//10
		   		"model.trainingCampBatch.fromDate," +//11
		   		"model.trainingCampBatch.toDate," +//12
		   		"TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId," +//13
		   		"TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel, "+//14
		   		"TCM.tdpCommitteeRole.tdpRoles.tdpRolesId," +//15
		   		"TCM.tdpCommitteeRole.tdpRoles.role," +//16
		   		" model.attendance.tdpCadre.tdpCadreId " +//17
		   		" from TrainingCampAttendance model ,TdpCommitteeMember TCM  " +
		   		" where TCM.tdpCadre.tdpCadreId=model.attendance.tdpCadre.tdpCadreId   and model.trainingCampBatch.isCancelled='false' " +
		   		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' and TCM.isActive = 'Y' ");
		  if(batchIds != null && batchIds.size() >0){
			  sb.append(" and model.trainingCampBatchId in (:batchIds) ");
		  }
		   
		   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
			   sb.append(" and TCM.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :committeeEnrollmentYrId ");
	 	   }
		   if(campIds != null && campIds.size() >0){
			   sb.append(" and model.trainingCampSchedule.trainingCamp.trainingCampId in (:campIds) ");
		   }
	 	  	if(scheduleIds != null && scheduleIds.size() >0){
			   sb.append(" and model.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) ");
		   }
	 	  	if(roleIds != null && roleIds.size() >0){
			   sb.append(" and model.tdpCommitteeRole.tdpRoles.tdpRolesId in (:roleIds) ");
		   }
		   if(programIds != null && programIds.size()>0){
			   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programIds) ");
		   }
		   if(staffCadreIds != null && staffCadreIds.size() >0){
			   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:staffCadreIds) " );
		   }
		   if(startDate!=null && endDate!=null){
			   sb.append(" and ( date(model.insertedTime) between :startDate and :endDate) ");
		   }
		   
		   sb.append(" group by  model.trainingCampBatch.trainingCampBatchId,model.attendance.tdpCadre.tdpCadreId " );
		   Query query =getSession().createQuery(sb.toString());
		   if(batchIds != null && batchIds.size() >0){
			   query.setParameterList("batchIds", batchIds);
		   }
		   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
			   query.setParameter("committeeEnrollmentYrId", committeeEnrollmentYrId);
		   }
		   if(programIds != null && programIds.size()>0){
			   query.setParameterList("programIds", programIds);
		   }
		   if(staffCadreIds != null && staffCadreIds.size() >0){
			   query.setParameterList("staffCadreIds", staffCadreIds);
		   }
		   if(campIds != null && campIds.size() >0){
			   query.setParameterList("campIds", campIds);
		   }
		   if(scheduleIds != null && scheduleIds.size() >0){
			   query.setParameterList("scheduleIds", scheduleIds);
		   }
		   if(roleIds != null && roleIds.size() >0){
			   query.setParameterList("roleIds", roleIds);
		   }
		   if(startDate!=null && endDate!=null){
			   query.setDate("startDate", startDate);
			   query.setDate("endDate", endDate);
		   }
		   return (List<Object[]>)query.list();
	   }
	public List<Object[]> getTotalAttendedCount(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programIds,List<Long> campIds ,List<Long> scheduleIds,Date startDate,Date endDate,Set<Long> staffCadreIds){
		   
		   StringBuilder sb=new StringBuilder();
		   sb.append(" select  model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId," +//0
		   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +//1
		   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName," +//2
		   		"model.trainingCampSchedule.trainingCamp.trainingCampId," +//3
		   		"model.trainingCampSchedule.trainingCamp.campName," +//4
		   		"model.trainingCampSchedule.trainingCampScheduleId," +//5
		   		"model.trainingCampBatchId," +//6
		   		"model.trainingCampBatch.trainingCampBatchName," +//7
		   		" model.attendance.tdpCadre.tdpCadreId " +//8
		   		" from TrainingCampAttendance model  " +
		   		" where    model.trainingCampBatch.isCancelled='false' " +
		   		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false'  ");
		   if(batchIds != null && batchIds.size() >0){
			   sb.append(" and model.trainingCampBatchId in (:batchIds)  ");
		   }
		   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
			}
		   if(campIds != null && campIds.size() >0){
			   sb.append(" and model.trainingCampSchedule.trainingCamp.trainingCampId in (:campIds) ");
		   }
	 	  	if(scheduleIds != null && scheduleIds.size() >0){
			   sb.append(" and model.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) ");
		   }
		   if(programIds != null && programIds.size()>0){
			   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programIds) ");
		   }
		   if(startDate!=null && endDate!=null){
			   sb.append(" and ( date(model.insertedTime) between :startDate and :endDate) ");
		   }
		   if(staffCadreIds != null && staffCadreIds.size() >0){
			   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:staffCadreIds) " );
		   }
		   
		   sb.append(" group by model.trainingCampBatchId,model.attendance.tdpCadre.tdpCadreId ");
		   Query query =getSession().createQuery(sb.toString());
		   if(batchIds != null && batchIds.size() >0){
			   query.setParameterList("batchIds", batchIds);
		   }
		   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
			}
		   if(programIds != null && programIds.size()>0){
			   query.setParameterList("programIds", programIds);
		   }
		   if(campIds != null && campIds.size() >0){
			   query.setParameterList("campIds", campIds);
		   }
		   if(scheduleIds != null && scheduleIds.size() >0){
			   query.setParameterList("scheduleIds", scheduleIds);
		   }
		  
		   if(startDate!=null && endDate!=null){
			   query.setDate("startDate", startDate);
			   query.setDate("endDate", endDate);
		   }
		   if(staffCadreIds != null && staffCadreIds.size() >0){
			   query.setParameterList("staffCadreIds", staffCadreIds);
		   }
		   return (List<Object[]>)query.list();
	   }
	
	 public List<Object[]> getDayWiseTrainingCampDetailsCount(List<Long> enrollmentYearIds,List<Long> programIdsList,List<Long> batchIdsList)
	 { 
		//Query query = getSession().createSQLQuery("call get_training_camp_attendance_details('8','2010-07-23','2050-08-02','2','1','5,7,9,6,8','2','1')");
		
		//return query.list();
	 
	 	String  committeeEnrollmetYrId = "";
	   if(enrollmentYearIds.contains(4L) && enrollmentYearIds.contains(3L) ){
		   committeeEnrollmetYrId="1,2";
	   }else if(enrollmentYearIds.contains(3L)){
		   committeeEnrollmetYrId="1";
	   }else if(enrollmentYearIds.contains(4L)){
		   committeeEnrollmetYrId="2";
	   }
	   
	   String batchIdStr="";
	   if(batchIdsList != null && batchIdsList.size()>0){
		   for (Long batchId : batchIdsList) {
			   if(batchIdStr.trim().length()==0)
				   batchIdStr = ""+batchId;
			   else
				   batchIdStr = batchIdStr+","+batchId;
		}
	   }
	   if(batchIdStr.isEmpty())
		   batchIdStr = null;
	   
	   //Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_details(:programId,'2010-07-23','2050-08-02',:enrollemntYrId,:basicCommitteeId,'5,7,9,6,8','2','1')")
	   Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_batch(:programId,'2010-07-23','2050-08-02',:enrollemntYrId,:basicCommitteeId,'5,7,9,6,8','2','1',:batchIdStr)")
			.addScalar("tdp_cadre_id", Hibernate.LONG)
			.addScalar("date(A.attended_time)", Hibernate.STRING)
			.addScalar("training_camp_batch_id", Hibernate.LONG)
			.addScalar("tdp_roles_id", Hibernate.LONG)
			.addScalar("tdp_committee_level_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("scope_value", Hibernate.LONG)
			.addScalar("Attended_Status", Hibernate.STRING)
			.addScalar("training_camp_program_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("state_name", Hibernate.STRING)
			.addScalar("district_id", Hibernate.LONG)
			.addScalar("district_name", Hibernate.STRING)
			.addScalar("parliament_constituency_id", Hibernate.LONG)
			.addScalar("parliament_constituency_name", Hibernate.STRING)
			.addScalar("constituency_id", Hibernate.LONG)
			.addScalar("constituency", Hibernate.STRING)
			.addScalar("tehsil_id", Hibernate.LONG)
			.addScalar("tehsil_name", Hibernate.STRING)
			.addScalar("local_election_body", Hibernate.LONG)
			.addScalar("town", Hibernate.STRING)
			.addScalar("panchayat_id", Hibernate.LONG)
			.addScalar("panchayat_name", Hibernate.STRING)
			.addScalar("ward_id", Hibernate.LONG)
			.addScalar("ward_name", Hibernate.STRING);
			query.setParameter("programId", programIdsList.get(0)).setParameter("enrollemntYrId", committeeEnrollmetYrId).setParameter("basicCommitteeId", 1L)
			.setParameter("batchIdStr", batchIdStr); 	
			return query.list();
	}
	 public List<Object[]> getAttendedCountForTrainingCamp(Long accessLevelValue, List<Long> userAccessLevelValues, Date fromDate, Date toDate, Long enrollmentYearId,List<Long> programIds){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select distinct " +
		 		   " trainingCampAttendance.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId, " +
		 		   " trainingCampAttendance.trainingCampBatch.trainingCampBatchId, " +
		 		   " trainingCampAttendance.attendance.tdpCadre.tdpCadreId, " +
		 		   " date(trainingCampAttendance.insertedTime) " +
		 		   " from TrainingCampAttendance trainingCampAttendance  " +
		 		   " where trainingCampAttendance.trainingCampBatch.isCancelled = 'false' " +
		 		   " and trainingCampAttendance.trainingCampBatch.trainingCampSchedule.status is null " +
		 		   " and trainingCampAttendance.trainingCampBatch.attendeeTypeId=1 ");
		 if(programIds != null && programIds.size() > 0){
			 sb.append(" and trainingCampAttendance.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIds)");
		 }
		 if(accessLevelValue != null && accessLevelValue.longValue() == IConstants.STATE_LEVEl_ACCESS_ID){
			 sb.append(" and trainingCampAttendance.attendance.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		 }else if(accessLevelValue != null && accessLevelValue.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sb.append(" and trainingCampAttendance.attendance.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues) ");
		 }else if(accessLevelValue != null && accessLevelValue.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sb.append(" and trainingCampAttendance.attendance.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");
		 }else if(accessLevelValue != null && accessLevelValue.longValue() == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sb.append(" and trainingCampAttendance.attendance.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");
		 }
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameterList("programIds", programIds);
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0 && accessLevelValue != null && accessLevelValue.longValue() != IConstants.STATE_LEVEl_ACCESS_ID){
			 query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 return query.list();
	 }
	 public List<Object[]> getInviteAttendedCountForTrainingCamp(Long accessLevelValue, List<Long> userAccessLevelValues, Long enrollmentYearId,List<Long> programIdsList)
	 { 
		 StringBuilder program = new StringBuilder();
		 if(programIdsList != null && programIdsList.size() > 0){
			 program.append(programIdsList.get(0).toString());
			 for(int i=1; i<programIdsList.size(); i++){
				 program.append(","+programIdsList.get(i));
			 }
		 }
		 
		 StringBuilder location = new StringBuilder();
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			 location.append(userAccessLevelValues.get(0).toString());
			 for(int i=1; i<userAccessLevelValues.size(); i++){
				 location.append(","+userAccessLevelValues.get(i));
			 }
		 }//get_training_camp_attendance_batch
		 //Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_details(:programId,'2010-07-23','2050-08-02',:enrollemntYrId,:basicCommitteeId,'5,7,9,6,8','2','1')")
		 Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_batch(:programIds,'2012-01-01','2030-12-30',:enrollemntYrId,:basicCommitteeId,'5,7,9,6,8',:accessLevelValue,:userAccessLevelValues,null)")
			.addScalar("tdp_cadre_id", Hibernate.LONG)
			.addScalar("date(A.attended_time)", Hibernate.STRING)
			.addScalar("training_camp_batch_id", Hibernate.LONG)
			.addScalar("tdp_roles_id", Hibernate.LONG)
			.addScalar("tdp_committee_level_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("scope_value", Hibernate.LONG)
			.addScalar("Attended_Status", Hibernate.STRING)
			.addScalar("training_camp_program_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("state_name", Hibernate.STRING)
			.addScalar("district_id", Hibernate.LONG)
			.addScalar("district_name", Hibernate.STRING)
			.addScalar("parliament_constituency_id", Hibernate.LONG)
			.addScalar("parliament_constituency_name", Hibernate.STRING)
			.addScalar("constituency_id", Hibernate.LONG)
			.addScalar("constituency", Hibernate.STRING)
			.addScalar("tehsil_id", Hibernate.LONG)
			.addScalar("tehsil_name", Hibernate.STRING)
			.addScalar("local_election_body", Hibernate.LONG)
			.addScalar("town", Hibernate.STRING)
			.addScalar("panchayat_id", Hibernate.LONG)
			.addScalar("panchayat_name", Hibernate.STRING)
			.addScalar("ward_id", Hibernate.LONG)
			.addScalar("ward_name", Hibernate.STRING)
			.addScalar("training_camp_id", Hibernate.LONG);   
			query.setParameter("programIds", program.toString()).setParameter("enrollemntYrId", enrollmentYearId).setParameter("basicCommitteeId", 1L)
			.setParameter("accessLevelValue", accessLevelValue).setParameter("userAccessLevelValues", location.toString());
			
			return query.list();
	}
}