package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
	
  public List<Object[]>	 getAttendedCountForBatchesByLocation(List<Long> batchIds){
	
	  Query query=getSession().createQuery("  " +
	  " select    model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct model.attendance.tdpCadreId) " +
	  " from      TrainingCampAttendance model,TdpCommitteeMember model2,TrainingCampBatchAttendee tcba " +
	  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
	  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' " +
	  "			and model.attendance.tdpCadreId=tcba.tdpCadreId and model.trainingCampBatch.trainingCampBatchId=tcba.trainingCampBatchId and tcba.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=1 " +
	  " group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
	  query.setParameterList("trainingCampBatchIds",batchIds);
	  return query.list();
  }
  
  public List<Long> getInviteeCadreIds(List<Long> batchIds){
	  Query query=getSession().createQuery("  " +
			  " select    distinct model.attendance.tdpCadreId " +
			  " from      TrainingCampAttendance model,TdpCommitteeMember model2,TrainingCampBatchAttendee tcba " +
			  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
			  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' " +
			  "			and model.attendance.tdpCadreId=tcba.tdpCadreId and model.trainingCampBatch.trainingCampBatchId=tcba.trainingCampBatchId and tcba.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=1  ");
			  query.setParameterList("trainingCampBatchIds",batchIds);
			  return query.list();
  }

  public List<Object[]> getAttendedNonInviteesCountForBatchesByLocation(List<Long> batchIds,List<Long> cadreIds){
	  Query query=getSession().createQuery("  " +
			  " select    model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct model.attendance.tdpCadreId) " +
			  " from      TrainingCampAttendance model,TdpCommitteeMember model2 " +
			  " where     model.attendance.tdpCadreId=model2.tdpCadreId and " +
			  "           model.trainingCampBatchId in (:trainingCampBatchIds) and model2.isActive='Y' and model.attendance.tdpCadreId not in (:cadreIds) and model.trainingCampBatch.attendeeTypeId=1  " +
			  " group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
			  query.setParameterList("trainingCampBatchIds",batchIds);
			  query.setParameterList("cadreIds",cadreIds);
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
		 		   " where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1  ");
		 
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
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) and model.trainingCampBatch.attendeeTypeId=1 ");
	 
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
	 		   " where model.attendance.tdpCadreId=model1.tdpCadreId and model.attendance.tdpCadreId=model2.tdpCadreId and  model.trainingCampBatchId in (:trainingCampBatchIds) " +
	 		   " and model2.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1  ");
	 
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
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled='false'  ");
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
				" where model.trainingCampBatch.trainingCampBatchId =:batchId and model.trainingCampBatch.attendeeTypeId=1 and  model.trainingCampBatch.isCancelled ='false' ");
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
  
  public Long getInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr ,List<Long> staffCadreIdsList)
  {
	  StringBuilder sb=new StringBuilder();
	  
	  sb.append(" select count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model, TrainingCampBatchAttendee model1  " +
				" where  model1.tdpCadre.tdpCadreId = model.attendance.tdpCadre.tdpCadreId and model1.isDeleted ='false' and " +
				" model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
	  if(batchId != null && batchId.longValue()>0L){
		  sb.append(" and model.trainingCampBatch.trainingCampBatchId =:batchId ");
		  if(fromDate!=null && toDate!=null){
			  sb.append(" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate");
		  }
	  }
	  else if(searchTypeStr != null && fromDate!=null && toDate!=null){		   
		   if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) )  )");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(model.trainingCampBatch.fromDate) < :fromDate and date(model.trainingCampBatch.toDate) < :toDate ) ");
	   }
	  if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:staffCadreIdsList) " );
	  
	  Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(batchId != null && batchId.longValue()>0L)			
			query.setParameter("batchId", batchId);
		if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
		
		return (Long)query.uniqueResult();
  }
  
  public Long getNonInviteesAttendedCountByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList)
  {
	  StringBuilder sb=new StringBuilder();
	  
	  sb.append(" select count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model  " +
				" where  model.attendance.tdpCadre.tdpCadreId not in ( select distinct model1.tdpCadre.tdpCadreId from  TrainingCampBatchAttendee model1  " +
				" where  model1.isDeleted ='false' )  and " +
				" model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
	  if(batchId != null && batchId.longValue()>0L){
		  sb.append(" and model.trainingCampBatch.trainingCampBatchId =:batchId ");
		  if(fromDate!=null && toDate!=null){
			  sb.append(" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate");
		  }
	  }
	  else if(searchTypeStr != null && fromDate!=null && toDate!=null){		   
		   if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate))) ");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(model.trainingCampBatch.fromDate) < :fromDate and date(model.trainingCampBatch.toDate) < :toDate ) ");
	   }
	  if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and model.attendance.tdpCadre.tdpCadreId not in (:staffCadreIdsList) " );
	  Query query = getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(batchId != null && batchId.longValue()>0L)			
			query.setParameter("batchId", batchId);
		if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
			query.setParameterList("staffCadreIdsList",staffCadreIdsList);
		
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
 public List<Object[]> getDateWiseAttendedAndAbsentCandidates(Long batchId){
	 
	 Query query=getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime) " +
	 		" from TrainingCampAttendance model " +
	 		" where" +
	 		" model.trainingCampBatch.trainingCampBatchId = :batchId" +
	 		" and date(model.attendance.attendedTime) between date(model.trainingCampBatch.fromDate) and date(model.trainingCampBatch.toDate) " +
	 		" and model.trainingCampBatch.attendeeTypeId=1 ");
	 
	 query.setParameter("batchId", batchId);
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
 
 public List<Object[]> getCompletedCountsForABatch(Long batchId,List<Date> dates){
		Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId,date(model.attendance.attendedTime)  " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId=:batchId and date(model.attendance.attendedTime) in (:dates)" +
				" and model.trainingCampBatch.attendeeTypeId=1  ");
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
	  sb.append(" group by A.tdp_cadre_id ");
	  
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
	  									" and tca.trainingCampBatch.attendeeTypeId=1  " +
	  									" group by date(tca.insertedTime) ");
	  query.setParameter("batchId", batchId);
	  return query.list();
  }

  public List<Long> getInviteeCadreIdsForADay(Long batchId,Date date){
	  Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId " +
	  		" from TrainingCampAttendance model " +
	  		" where model.trainingCampBatch.trainingCampBatchId=:batchId " +
	  		" and date(model.attendance.attendedTime)=:date and model.trainingCampBatch.attendeeTypeId=1 ");
	  query.setParameter("batchId", batchId);
	  query.setParameter("date", date);
	  return query.list();
  }
  
  public List<Long> getNonInviteesNoDaysCount(Long batchId){
	  Query query = getSession().createQuery("select  a.tdpCadreId from TrainingCampAttendance tca,Attendance a " +
	  		"where tca.attendance.attendanceId=a.attendanceId and " +
	  		"a.tdpCadreId not in (select tdpCadreId from TrainingCampBatchAttendee where isDeleted='false' and trainingCampBatchId=:batchId) " +
	  		"and tca.trainingCampBatchId=:batchId and tca.trainingCampBatch.attendeeTypeId=1 group by date(a.attendedTime),a.tdpCadreId");
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
	  		" where model.trainingCampBatchId=:batchId and model.trainingCampBatch.attendeeTypeId=1 " +
	  		" and model.attendance.tdpCadre.tdpCadreId not in (select model1.tdpCadre.tdpCadreId from TrainingCampBatchAttendee model1 " +
	  		"	where model1.trainingCampBatchId=:batchId and model1.isDeleted='false' )  ");
	  query.setParameter("batchId",batchId);
	  return query.list();
  }
  
  public List<Object[]> getAttendedCountsForBatches(List<Long> batchIds){
	  
	  Query query=getSession().createQuery(" " +
	  	"select distinct tca.trainingCampBatchId,date(tca.attendance.attendedTime),tca.attendance.tdpCadreId " +
	  	"from   TrainingCampAttendance tca " +
	  	"where  tca.trainingCampBatchId in (:batchIds) and tca.trainingCampBatch.attendeeTypeId=1 and tca.trainingCampBatch.isCancelled='false' ");
	  query.setParameterList("batchIds",batchIds);
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
  		
    public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date toDate){

		  StringBuilder queryStr= new StringBuilder();
			  
		  queryStr.append(" select " +
		  		          " model.trainingCampProgram.trainingCampProgramId," +
		  	 	          " model.trainingCampProgram.programName," +
		  		          " model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId," +
		  		          " model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName," +
		  		          " count(distinct model.attendance.tdpCadre.tdpCadreId) " +
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
		   queryStr.append(" group by model.trainingCampProgram.trainingCampProgramId,model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId " +
		   		           " order by " +
		   		           " model.trainingCampProgram.trainingCampProgramId asc ");
		   
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
  	
	public List<Object[]> getTotalAttenedCadresOfTrainingCampProgramByLocationType(Long userAccessLevelId,List<Long> userAccessLevelValues,String locationType,Long stateId,Date toDate){

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
	        
		     queryStr.append(" count(distinct model.attendance.tdpCadre.tdpCadreId) " + //5
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
   if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId asc"); //1
   } 
   if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
    queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId asc"); //3
	    }
   if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	   queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId asc"); //1  
   }  
   if(locationType != null && locationType.equalsIgnoreCase("Village")){
	   queryStr.append(" group by model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId order by model3.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId asc"); //1   
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
		  return query.list();    
  }
	public Long getTotalAttendedForTrainingCampStateLevel(Long campId, Long programId){ 
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select count(distinct ATT.tdpCadre.tdpCadreId) from TrainingCampAttendance TCA, Attendance ATT, TdpCadre TC, TrainingCampBatchAttendee TCBA where " +
				           " TCA.trainingCampSchedule.trainingCamp.trainingCampId = (:campId) and " +
				           " TCA.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = (:programId) and " +
				           " TCA.attendance.attendanceId = ATT.attendanceId and " +
				           " ATT.tdpCadre.tdpCadreId = TC.tdpCadreId and " +    
				           " TCBA.tdpCadre.tdpCadreId = TC.tdpCadreId ");  
		Query query = getSession().createQuery(queryString.toString()); 
		query.setParameter("campId", campId);
		query.setParameter("programId", programId);  
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getStateDistrictTrainingProgramAttendedDetails(Long campId, Long programId){
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select TCL.tdp_committee_level_id as id, TCL.tdp_committee_level as level,count(distinct TCM.tdp_cadre_id) as total from "+
						   " tdp_committee_member TCM, tdp_committee_role TCR, tdp_committee TC, tdp_committee_level TCL, tdp_cadre TDP, "+
						   " training_camp_attendance TCA, training_camp_schedule TCS, attendance A, training_camp_batch_attendee TCBA "+
						   " where "+
						   " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
						   " TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
						   " TCA.attendance_id = A.attendance_id and "+
						   " TDP.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
						   " TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and "+
						   " TCR.tdp_committee_id = TC.tdp_committee_id and "+
						   " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and "+
						   " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 "+
						   " group by "+
						   " TCL.tdp_committee_level_id ");
		SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("total", Hibernate.LONG);  
		query.setParameter("campId", campId);
		query.setParameter("programId", programId);
		return query.list();
	}
	 public List<Object[]> getMlaMpInchargeTrainingProgramAttendedDetails(Long campId, Long programId){
		 StringBuilder queryString = new StringBuilder();
		 queryString.append(" select PRT.public_representative_type_id as id,PRT.position as position,count(distinct TDP.tdp_cadre_id) as total from "+
				 			" candidate CND, tdp_cadre_candidate TCC, public_representative PR, public_representative_type PRT, tdp_cadre TDP, "+
				 			" training_camp_attendance TCA, training_camp_schedule TCS, attendance A, training_camp_batch_attendee TCBA "+
				 			" where "+
				 			" TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
				 			" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				 			" TCA.attendance_id = A.attendance_id and "+
				 			" TDP.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
				 			" TCC.candidate_id = CND.candidate_id and "+
				 			" PR.candidate_id = CND.candidate_id and "+
				 			" PR.public_representative_type_id = PRT.public_representative_type_id and "+
				 			" TCC.tdp_cadre_id = TDP.tdp_cadre_id "+
				 			" group by PRT.public_representative_type_id ");
		 SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("total", Hibernate.LONG);
		 query.setParameter("campId", campId);
		 query.setParameter("programId", programId);    
		 return query.list();
	 }
	 public List<Object[]> getDestWiseAttendedMembers(Long stateId, Long campId, Long programId){
		 StringBuilder queryString = new StringBuilder();
		   queryString.append(" select D.district_id as id,D.district_name as name, count(distinct A.tdp_cadre_id) as total from training_camp_attendance TCA, training_camp_schedule TCS, attendance A, training_camp_batch_attendee TCBA, tdp_cadre TC, user_address UA, district D "+
				   			  " where "+
				   			  " TCA.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
				   			  " TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
				   			  " TCA.attendance_id = A.attendance_id and "+
				   			  " TC.tdp_cadre_id = A.tdp_cadre_id and TCBA.tdp_cadre_id = TC.tdp_cadre_id and "+
				   			  " TC.address_id = UA.user_address_id and " +
				   			  " D.district_id BETWEEN 11 AND 23 AND"+
				   			  " UA.district_id = D.district_id "+
				   			  " group by D.district_id order by D.district_id ");
		   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("total", Hibernate.LONG);
		   query.setParameter("campId", campId);
		   query.setParameter("programId", programId);  
		 
		   return query.list();
	 }
	
	
}