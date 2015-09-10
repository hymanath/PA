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
	  "           model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (5,6,11) and " +
	  "           model.trainingCampBatchId in (:trainingCampBatchIds) " +
	  " group by  model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId " +
	  " order by  FIELD(model2.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId , 6,5,11)");
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
		 		   " where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId in (:trainingCampBatchIds) ");
		 
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
 public List<Object[]> getCompletedCounts(List<Long> batchIds){
		Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampBatchId, count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId in (:batchIds) ");
		query.setParameterList("batchIds", batchIds);
		return query.list();
 }
 
  public List<Object[]> getAttendedlocWiseCountsByProgramOrCampOrBatch(String queryString,Long programId,Long campId,Long batchId,Date fromDate,Date toDate){
	 
	  Query query=getSession().createQuery(queryString);
	  query.setParameter("fromDate", fromDate);
	  query.setParameter("toDate", toDate);
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
		Query query = getSession().createQuery(" select count(distinct model.attendance.tdpCadre.tdpCadreId) " +
				" from TrainingCampAttendance model " +
				" where model.trainingCampBatch.trainingCampBatchId =:batchId and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		query.setParameter("batchId", batchId);
		return (Long)query.uniqueResult();
  }
  public List<Object[]> getDateWiseCountsByBatch(Long batchId,Date fromDate,Date toDate){
	  Query query=getSession().createQuery(" select date(model.attendance.attendedTime),count(distinct model.attendance.tdpCadre.tdpCadreId) " +
	  " from TrainingCampAttendance model " +
	  " where model.trainingCampBatch.trainingCampBatchId =:batchId and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate " +
	  " group by date(model.attendance.attendedTime)");
	  query.setParameter("fromDate",fromDate);
	  query.setParameter("toDate",toDate);
	  query.setParameter("batchId",batchId);
	  return query.list();
  }
 public List<Object[]> getCampWiseAttendedCountByProgram(Long programId,Date fromDate,Date toDate){
	 Query query=getSession().createQuery("" +
	 " select  tcs.trainingCampId,count(distinct tca.attendance.tdpCadreId)" +
	 " from  TrainingCampAttendance tca,TrainingCampSchedule tcs" +
	 " where tca.trainingCampProgramId=tcs.trainingCampProgramId and" +
	 "       tca.trainingCampProgramId=:programId and date(tca.trainingCampBatch.fromDate) >= :fromDate and date(tca.trainingCampBatch.toDate) <= :toDate " +
	 " group by tcs.trainingCampId");
	 query.setParameter("fromDate",fromDate);
	 query.setParameter("toDate",toDate);
	 query.setParameter("programId",programId);
	 return query.list();
 }
 public Long getAttendedCountByCamp(Long programId,Long campId,Date fromDate,Date toDate){
	 Query query=getSession().createQuery("" +
	 " select  count(distinct tca.attendance.tdpCadreId)" +
	 " from  TrainingCampAttendance tca,TrainingCampSchedule tcs" +
	 " where tca.trainingCampProgramId=tcs.trainingCampProgramId and" +
	 "       tca.trainingCampProgramId=:programId and tcs.trainingCampId=:campId and date(tca.trainingCampBatch.fromDate) >= :fromDate and date(tca.trainingCampBatch.toDate) <= :toDate");
	 
	 query.setParameter("programId",programId);
	 query.setParameter("fromDate",fromDate);
	 query.setParameter("toDate",toDate);
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
 
}
