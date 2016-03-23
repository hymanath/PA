package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;

public class AppointmentCandidateRelationDAO extends GenericDaoHibernate<AppointmentCandidateRelation, Long> implements	IAppointmentCandidateRelationDAO {

	public AppointmentCandidateRelationDAO(){
		super(AppointmentCandidateRelation.class);
	}
	public List<Object[]> getAllAppointmentDetails(int startIndex,int maxIndex){
		Query query = getSession().createQuery("select model.appointmentCandidate.name, model.appointmentCandidate.mobileNo,model.appointmentCandidate.updatedTime,model.appointment.appointmentUniqueId,model.appointmentCandidate.candidateDesignation.designation from AppointmentCandidateRelation model where model.appointment.isDeleted='N'");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	public List<Object[]> countAppointmentDetails(){
		Query query = getSession().createQuery("select model.appointmentCandidate.name, model.appointmentCandidate.mobileNo,model.appointmentCandidate.updatedTime,model.appointment.appointmentUniqueId,model.appointmentCandidate.candidateDesignation.designation from AppointmentCandidateRelation model where model.appointment.isDeleted='N'");
		return query.list();
	}
    
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(" select  model.appointment.appointmentId,model.appointment.reason," +
				"           model.appointment.appointmentPriority.priority,model.appointment.appointmentStatus.status,model.appointment.insertedTime " +
				  " from    AppointmentCandidateRelation model " +
				  " where   model.appointment.isDeleted = 'N' ");
		
		if(designationId!=null && designationId >0l){
		  sb.append(" and model.appointmentCandidate.designationId = :designationId");	
		}
		
		if(priorityId!=null && priorityId >0l){
			  sb.append(" and model.appointment.appointmentPriorityId = :priorityId");	
		}
		if(statusId!=null && statusId >0l){
			  sb.append(" and model.appointment.appointmentStatusId = :statusId");	
		}
		if(districtId!=null && districtId >0l){
			  sb.append(" and model.appointmentCandidate.userAddress.district.districtId = :districtId");	
		}
		if(constituencyId!=null && constituencyId >0l){
			  sb.append(" and model.appointmentCandidate.userAddress.constituency.constituencyId = :constituencyId");	
		}
		sb.append(" order by model.appointment.insertedTime desc ");
		
		Query query = getSession().createQuery(sb.toString());
		if(designationId!=null && designationId >0l){
			  query.setParameter("designationId",designationId);
		}
		
		if(priorityId!=null && priorityId >0l){
			query.setParameter("priorityId",priorityId);
		}
		if(statusId!=null && statusId >0l){
			query.setParameter("statusId",statusId);	
		}
		if(districtId!=null && districtId >0l){
			query.setParameter("districtId",districtId);	
		}
		if(constituencyId!=null && constituencyId >0l){
			query.setParameter("constituencyId",constituencyId);	
		}
		return query.list();
	}
	
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds){
		
		Query query = getSession().createQuery("" +
		 " select  model.appointment.appointmentId ," +
		 "          model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name,model.appointmentCandidate.tdpCadreId," +
		 "         model.appointmentCandidate.mobileNo,model.appointmentCandidate.candidateDesignation.designation," +
		 "         model.appointmentCandidate.userAddress.constituency.name " +
		 " from    AppointmentCandidateRelation model " +
		 " where   model.appointment.isDeleted = 'N' and model.appointment.appointmentId in (:appointmentIds)");
		
		query.setParameterList("appointmentIds",appointmentIds);
		return query.list();
	}
	public List<Object[]> getCandidatePreviousApptDetails(List<Long> candidateIds){
		
		Query query = getSession().createQuery("" +
		" select  model.appointmentCandidate.appointmentCandidateId,model.appointment.appointmentId," +
		"         date(model.appointment.insertedTime),model.appointment.appointmentStatus.appointmentStatusId ,model.appointment.appointmentStatus.status " +
		" from    AppointmentCandidateRelation model " +
		" where   model.appointment.isDeleted = 'N' and model.appointmentCandidate.appointmentCandidateId in (:candidateIds) ");
		query.setParameterList("candidateIds",candidateIds);
		return query.list();
	}
	
	public List<Object[]> getAppointmentCandidateDetails(List<Long> appointmentIds){
		Query query = getSession().createQuery(" select model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name," +
				"model.appointmentCandidate.mobileNo, " +
				"model.appointmentCandidate.candidateDesignation.designation," +
				"model.appointmentCandidate.tdpCadreId,model.appointment.appointmentId  " +
				" from AppointmentCandidateRelation model " +
				" where model.appointment.isDeleted='N' and model.appointment.appointmentId in (:appointmentIds) ");
		query.setParameterList("appointmentIds", appointmentIds);
		return query.list();
	}
}
