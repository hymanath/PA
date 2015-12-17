package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;

public class TrainingCampAttendanceDAO extends GenericDaoHibernate<TrainingCampAttendance,Long> implements ITrainingCampAttendanceDAO{

	public TrainingCampAttendanceDAO()
	{
		super(TrainingCampAttendance.class);
	}
	
  public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds){
	
	  Query query=getSession().createQuery("  " +
	  " select    model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct model.attendance.tdpCadreId) " +
	  " from      TrainingCampAttendance model,TdpCommitteeMember model2 " +
	  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
	  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' " +
	  " group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
	  query.setParameterList("trainingCampBatchIds",batchIds);
	  return query.list();
  }

  public List<Object[]> getInvitedCadreCountByBatchIds(List<Long> batchIds,String type){
	
		 StringBuilder sb=new StringBuilder();
		 sb.append(" select ");
		 
		 if(type.equalsIgnoreCase("constituency")){
			 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
		 }else{
			 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
		 }
		 sb.append(",count(distinct model.tdpCadreId)");
		 
		 sb.append(" from TrainingCampBatchAttendee model,TdpCadre model1 " +
		 		   " where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) and model.isDeleted = 'false' ");
		 
		 if(type.equalsIgnoreCase("constituency")){
			 sb.append(" group by model1.userAddress.constituency.constituencyId " +
			 		   " order by model1.userAddress.constituency.name");
			 
		 }else{
			 sb.append(" group by model1.userAddress.district.districtId " +
			 		   " order by model1.userAddress.district.districtName");
		 }
		 
		Query query=getSession().createQuery(sb.toString());
		query.setParameterList("trainingCampBatchIds",batchIds);
		return query.list();
		 
	 }
 public List<Object[]> getAttendedCadreCountByBatchIds(List<Long> batchIds,String type){
	
	 StringBuilder sb=new StringBuilder();
	 sb.append(" select ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
	 }else{
		 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
	 }
	 sb.append(",count(distinct model1.tdpCadreId)");
	 
	 sb.append(" from TrainingCampAttendance model,TdpCadre model1 " +
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" group by model1.userAddress.constituency.constituencyId " +
		 		   " order by model1.userAddress.constituency.name");
		 
	 }else{
		 sb.append(" group by model1.userAddress.district.districtId " +
		 		   " order by model1.userAddress.district.districtName");
	 }
	 
	Query query=getSession().createQuery(sb.toString());
	query.setParameterList("trainingCampBatchIds",batchIds);
	return query.list();
	 
 }
 public List<Object[]> getInviteeCountsinAttendedCounts(List<Long> batchIds,String type){
		
	 StringBuilder sb=new StringBuilder();
	 sb.append(" select ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" model1.userAddress.constituency.constituencyId,model1.userAddress.constituency.name");
	 }else{
		 sb.append(" model1.userAddress.district.districtId,model1.userAddress.district.districtName"); 
	 }
	 sb.append(",count(distinct model1.tdpCadreId)");
	 
	 sb.append(" from TrainingCampAttendance model,TdpCadre model1,TrainingCampBatchAttendee model2 " +
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.attendance.tdpCadreId=model2.tdpCadreId and  model.trainingCampBatchId in (:trainingCampBatchIds) ");
	 
	 if(type.equalsIgnoreCase("constituency")){
		 sb.append(" group by model1.userAddress.constituency.constituencyId " +
		 		   " order by model1.userAddress.constituency.name");
		 
	 }else{
		 sb.append(" group by model1.userAddress.district.districtId " +
		 		   " order by model1.userAddress.district.districtName");
	 }
	 
	Query query=getSession().createQuery(sb.toString());
	query.setParameterList("trainingCampBatchIds",batchIds);
	return query.list();
	 
 }
 
 public List<Object[]> getCompletedCounts(List<Long> batchIds){
		Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampBatchId, count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model,TrainingCampBatchAttendee model1 " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) and model.trainingCampBatch.trainingCampBatchId=model1.trainingCampBatch.trainingCampBatchId and model.attendance.tdpCadre.tdpCadreId=model1.tdpCadre.tdpCadreId " +
				" and model1.isDeleted='false' and model.trainingCampBatch.isCancelled='false' and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
				" group by model.trainingCampBatch.trainingCampBatchId ");
		query.setParameterList("batchIds", batchIds);
		return query.list();
 }
 
 public List<Object[]> getCompletedCountDetails(List<Long> batchIds){
		Query query = getSession().createQuery(" select distinct model.trainingCampBatch.trainingCampBatchId, model.attendance.tdpCadre.tdpCadreId " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) ");
		query.setParameterList("batchIds", batchIds);
		return (List<Object[]>)query.list();
}
 
  public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate,Date currDate,String callFrom){
	 
	  Query query=getSession().createQuery(queryString);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  
	  if(!callFrom.equalsIgnoreCase("all")){		
		  query.setParameter("currDate", currDate);
	  }
	  
	  if(batchId==null && campId==null && programId!=null ){
		 query.setParameter("programId",programId);
	  }
	  else if(batchId==null && campId!=null){
		 query.setParameter("campId",campId);
		 if(programId!=null)
			 query.setParameter("programId",programId);
	  }else if(batchId!=null){
		 query.setParameter("batchId",batchId);
		 if(programId!=null)
			 query.setParameter("programId",programId);
		 if(campId!=null)
			 query.setParameter("campId",campId);
	  }
	  return query.list();
 }
  public Long getAttendedCountByBatch(Long batchId,Date fromDate,Date toDate){
	  StringBuilder sb=new StringBuilder();
	  sb.append(" select count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId =:batchId ");
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate");
	  }
	  
		Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		query.setParameter("batchId", batchId);
		return (Long)query.uniqueResult();
  }
  public List<Object[]> getDateWiseCountsByBatch(Long batchId,Date fromDate,Date toDate){
	  StringBuilder sb=new StringBuilder();
	  
	  sb.append(" select date(model.attendance.attendedTime),count(distinct model.attendance.tdpCadre.tdpCadreId) " +
	  " from TrainingCampAttendance model " +
	  " where model.trainingCampBatch.trainingCampBatchId =:batchId ");
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
	 "       tca.trainingCampProgramId=:programId ");
	 
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
	 "       tca.trainingCampProgramId=:programId and tcs.trainingCampId=:campId ");
	 
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
 public List<Object[]> getDateWiseAttendedAndAbsentCandidates(Long batchId){
	 
	 Query query=getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime) " +
	 		" from TrainingCampAttendance model " +
	 		" where" +
	 		" model.trainingCampBatch.trainingCampBatchId = :batchId" +
	 		" and date(model.attendance.attendedTime) between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) ");
	 
	 query.setParameter("batchId", batchId);
	 return query.list();
 }
 public List<Object[]> getAttendedCountOfCadreProgramWise(Long cadreId){
	 
	 Query query = getSession().createQuery("select model.trainingCampProgram.trainingCampProgramId,count(distinct model.attendance.tdpCadreId),model.trainingCampProgram.programName " +
	 		" from  TrainingCampAttendance model" +
	 		" where model.attendance.tdpCadre.tdpCadreId =:cadreId " +
	 		" group by model.trainingCampProgram.trainingCampProgramId ");
	 
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
	 		" and model.trainingCampProgram.trainingCampProgramId = :programId  ");
	 
	 query.setParameter("cadreId",cadreId);
	 query.setParameter("programId", programId);
	 
	 return query.list();
 }
 
 public List<Long> getCompletedCountsForADay(Long batchId,Date dates){
		Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,model.attendance.attendedTime  " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId=:batchId and date(model.attendance.attendedTime)=:dates ");
		query.setParameter("batchId", batchId);
		query.setParameter("dates", dates);
		return (List<Long>)query.list();
}
 
 public List<Object[]> getCompletedCountsForABatch(Long batchId,List<Date> dates){
		Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime)  " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId=:batchId and date(model.attendance.attendedTime) in (:dates) ");
		query.setParameter("batchId", batchId);
		query.setParameterList("dates", dates);
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
  
  public List<Object[]> getInviteeAttendedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList){
	   
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
	   
	   sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
	   }
	   
	   return query.list();
  }
  
  public List<Object[]> getInviteeAttendedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList){
	   
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
	   
	   //sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
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
  
  public List<Object[]> getSpeakersAttendedAreaDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate){
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
	  
	  Query query = getSession().createQuery(sb.toString());
	  if(centerId != null && centerId.longValue()>0L)
		  query.setParameter("centerId", centerId);
	  if(programId != null && programId.longValue()>0L)	  
		  query.setParameter("programId", programId);
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
	  }
	  return query.list();
  }
  
  public List<Object[]> getSpeakersAttendedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct A.tdp_cadre_id,count(distinct date(A.attended_time))  from training_camp_attendance TCA, attendance A,  " +
		  		" training_camp_batch TCB , attendee_type AT1   where  TCA.training_camp_batch_id = TCB.training_camp_batch_id and ");
	  sb.append(" TCA.attendance_id = A.attendance_id and TCB.attendee_type_id = AT1.attendee_type_id and  " );
	  sb.append(" TCB.attendee_type_id = 2 and AT1.is_deleted='false' and TCB.is_cancelled = 'false'  ");
	  
	  if(fromDate!=null && toDate!=null){
		  sb.append(" and ( date(A.attended_time) >=:fromDate and date(A.attended_time) <= :toDate ) ");
	  }
	  sb.append(" group by A.tdp_cadre_id ");
	  
	  Query query = getSession().createSQLQuery(sb.toString());
	 
	  if(fromDate!=null && toDate!=null){
		  query.setParameter("fromDate", fromDate);
		  query.setParameter("toDate", toDate);
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
  
  public List<Object[]> getTodaySpeakersAttendedDetails(Date toDayDate){
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append(" select distinct A.tdp_cadre_id,TCB.training_camp_batch_id,A.attended_time  from training_camp_attendance TCA, attendance A,  " +
		  		" training_camp_batch TCB , attendee_type AT1   where  TCA.training_camp_batch_id = TCB.training_camp_batch_id and ");
	  sb.append(" TCA.attendance_id = A.attendance_id and TCB.attendee_type_id = AT1.attendee_type_id and  " );
	  sb.append(" TCB.attendee_type_id = 2 and AT1.is_deleted='false' and TCB.is_cancelled = 'false'  ");
	  
	  if(toDayDate!=null){
		  sb.append(" and ( date(A.attended_time) =:toDayDate ) ");
	  }
	  
	  Query query = getSession().createSQLQuery(sb.toString());
	 
	  if(toDayDate!=null){
		  query.setDate("toDayDate", toDayDate);
	  }
	  return query.list();
  }
  
  public List<Object[]> getDayWiseInviteeCountsForBatch(Long batchId){
	  Query query = getSession().createQuery(" select count(distinct tcba.tdpCadreId),date(tca.insertedTime) " +
	  									" from TrainingCampAttendance tca,TrainingCampBatchAttendee tcba " +
	  									" where " +
	  									" tca.trainingCampBatchId = tcba.trainingCampBatchId and " +
	  									" tca.trainingCampBatchId=:batchId and tca.attendance.tdpCadreId=tcba.tdpCadreId and tcba.isDeleted='false' " +
	  									" group by date(tca.insertedTime) ");
	  query.setParameter("batchId", batchId);
	  return query.list();
  }

  public List<Long> getInviteeCadreIdsForADay(Long batchId,Date date){
	  Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId " +
	  		" from TrainingCampAttendance model " +
	  		" where model.trainingCampBatch.trainingCampBatchId=:batchId " +
	  		" and date(model.attendance.attendedTime)=:date ");
	  query.setParameter("batchId", batchId);
	  query.setParameter("date", date);
	  return query.list();
  }
  
  public List<Long> getNonInviteesNoDaysCount(Long batchId){
	  Query query = getSession().createQuery("select  a.tdpCadreId from TrainingCampAttendance tca,Attendance a " +
	  		"where tca.attendance.attendanceId=a.attendanceId and " +
	  		"a.tdpCadreId not in (select tdpCadreId from TrainingCampBatchAttendee where isDeleted='false' and trainingCampBatchId=:batchId) " +
	  		"and tca.trainingCampBatchId=:batchId group by date(a.attendedTime),a.tdpCadreId");
	 /* Query query = getSession().createSQLQuery("select  a.tdp_cadre_id from training_camp_attendance tca,attendance a " +
	  		"where tca.attendance_id=a.attendance_id and " +
	  		"a.tdp_cadre_id not in (select tdp_cadre_id from training_camp_batch_attendee where is_deleted='false' and training_camp_batch_id=:batchId) " +
	  		"and tca.training_camp_batch_id=:batchId group by date(a.attended_time),a.tdp_cadre_id");*/
	  query.setParameter("batchId",batchId);
	  return query.list();
  }
  
  public List<Object[]> getNonInviteesCadreBtBatch(Long batchId){
	  Query query = getSession().createQuery("select distinct model.attendance.tdpCadre.tdpCadreId,model.attendance.tdpCadre.memberShipNo," +
	   		" model.attendance.tdpCadre.firstname,model.attendance.tdpCadre.mobileNo,model.attendance.tdpCadre.image," +
	   		"model.attendance.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.attendance.tdpCadre.userAddress.constituency.name " +
	  		" from TrainingCampAttendance model " +
	  		" where model.trainingCampBatchId=:batchId " +
	  		" and model.attendance.tdpCadre.tdpCadreId not in (select model1.tdpCadre.tdpCadreId from TrainingCampBatchAttendee model1 " +
	  		"	where model1.trainingCampBatchId=:batchId and model1.isDeleted='false' ) ");
	  query.setParameter("batchId",batchId);
	  return query.list();
  }
}