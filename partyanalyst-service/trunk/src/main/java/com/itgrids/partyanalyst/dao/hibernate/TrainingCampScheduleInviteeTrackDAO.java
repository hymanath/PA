package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeTrackDAO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;

public class TrainingCampScheduleInviteeTrackDAO extends GenericDaoHibernate<TrainingCampScheduleInviteeTrack, Long> implements ITrainingCampScheduleInviteeTrackDAO{

	public TrainingCampScheduleInviteeTrackDAO() {
		super(TrainingCampScheduleInviteeTrack.class);
	}
	
	public List<Object[]> getScheduleConfirmationDetails(Long purposeId,Long userId){
		Query query = getSession().createQuery(" select model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId, " +
				" model.trainingCampScheduleInvitee.tdpCadre.firstname," +
				" model.trainingCampScheduleInvitee.tdpCadre.lastname, " +
				" model.trainingCampScheduleInvitee.tdpCadre.mobileNo, " +
				" model.trainingCampScheduleInvitee.tdpCadre.image, " +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId, " +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.status, " +
				" model.trainingCampScheduleInvitee.tdpCadre.age, " +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtName, " +
				" model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId, " +
				" model.trainingCampScheduleInviteeCallerId, " +
				" model.remarks, " +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency.name, " +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId " +
				" from TrainingCampScheduleInviteeTrack model " +
				" where " +
				" model.campCallPurposeId=:purposeId and " +
				" model.trainingCampCallerId=:userId order by model.trainingCampScheduleInviteeTrackId desc");

		query.setParameter("purposeId", purposeId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	
	public List<Object[]> getMemberRemarks(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate,List<Long> cadreIds)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId," +
				" model.remarks,model.insertedTime" +
				" from TrainingCampScheduleInviteeTrack model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId " +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = :programId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId =:campId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId");
				//" and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(1,2) ");
		if(status.equalsIgnoreCase("undialed"))
			str.append(" and campCallStatus.campCallStatusId is null");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("callStatus"))
					str.append(" and campCallStatus.campCallStatusId in(:statusIds)");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("scheduleCallStatus"))
			str.append(" and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(:statusIds)");
		if(inputVo.getBatchId() > 0)
			str.append(" and model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId = :batchId " );
		str.append(" and model.campCallPurposeId = :callPurposeId");
		
		if(toDayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:toDayDate ");
		if(cadreIds != null && cadreIds.size() > 0)
		str.append(" and model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId in(:cadreIds)")	;
		str.append(" order by model.trainingCampScheduleInviteeTrackId desc");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", inputVo.getUserId());
		query.setParameter("callPurposeId", inputVo.getPurposeId());
		query.setParameter("programId", inputVo.getProgramId());
		query.setParameter("campId", inputVo.getCampId());
		query.setParameter("scheduleId", inputVo.getScheduleId());
		if(statusIds != null && statusIds.size() > 0)
		query.setParameterList("statusIds", statusIds);
		if(inputVo.getBatchId() > 0)
		query.setParameter("batchId", inputVo.getBatchId());	
		
		if(toDayDate != null)
		 query.setDate("toDayDate", toDayDate);
		if(cadreIds != null && cadreIds.size() > 0)
			query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
	
	public List<Object[]> getTrackDetailsOfCandidateByCallPurposeWise(Long programId,Long tdpCadreId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.trainingCampScheduleInviteeTrackId,model.campCallPurpose.campCallPurpose,model.campCallPurpose.purpose," +
				" model.remarks,model.insertedTime " +
				" from TrainingCampScheduleInviteeTrack model " +
				" where " +
				" model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId =:tdpCadreId " +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId =:programId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("programId", programId);
		
		return query.list();
		
	}
}
