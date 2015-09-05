package com.itgrids.partyanalyst.dao.hibernate;

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
}
