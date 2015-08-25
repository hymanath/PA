package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeTrackDAO;
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

}
