package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
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
    
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select  distinct acr.appointment_id as appid, a.reason as reason, ap.priority as priority, ass.status as status," +
				"            a.inserted_time as insertedTime,la.appointment_label_id as applabelId" +
				"   from    appointment_candidate_relation acr " +
				"           join appointment                  a    on  acr.appointment_id=a.appointment_id " +
				"           join appointment_priority         ap   on  a.appointment_priority_id=ap.appointment_priority_id " +
				"           join appointment_status           ass  on  a.appointment_status_id=ass.appointment_status_id " +
				"           join appointment_preferable_date  apd  on  acr.appointment_id=apd.appointment_id " +
				"           join appointment_candidate        ac   on  acr.appointment_candidate_id=ac.appointment_candidate_id " +
				"           join user_address                 ua   on  ac.address_id=ua.user_address_id " +
				"           left join label_appointment       la   on  la.appointment_id=a.appointment_id and la.is_deleted='N' " +
				" where     a.is_deleted='N' ");
		
		if(designationId!=null && designationId >0l){
		  sb.append(" and ac.designation_id = :designationId");	
		}
		
		if(priorityId!=null && priorityId >0l){
			  sb.append(" and a.appointment_priority_id = :priorityId");	
		}
		if(statusId!=null && statusId >0l){
			  sb.append(" and a.appointment_status_id = :statusId");	
		}
		if(districtId!=null && districtId >0l){
			  sb.append(" and ua.district_id = :districtId");	
		}
		if(constituencyId!=null && constituencyId >0l){
			  sb.append(" and ua.constituency_id = :constituencyId");	
		}
        if(fromDate!=null){
			sb.append(" and apd.appointment_date >= :fromDate");
		}
        if(toDate!=null){
        	sb.append(" and apd.appointment_date <= :toDate");
        }
		sb.append(" order by a.inserted_time desc ");
		
		Query query = getSession().createSQLQuery(sb.toString())
				 .addScalar("appid",Hibernate.LONG)
				 .addScalar("reason",Hibernate.STRING)
				 .addScalar("priority",Hibernate.STRING)
				 .addScalar("status",Hibernate.STRING)
				 .addScalar("insertedTime",Hibernate.TIMESTAMP)
				 .addScalar("applabelId",Hibernate.LONG);
		        
		
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
		if(fromDate!=null){
			query.setDate("fromDate",fromDate);	
		}
        if(toDate!=null){
        	query.setDate("toDate",toDate);	
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
		" from    AppointmentCandidateRelation model  " +
		" where   model.appointment.isDeleted = 'N' and model.appointmentCandidate.appointmentCandidateId in (:candidateIds) ");
		query.setParameterList("candidateIds",candidateIds);
		return query.list();
	}
    public List<Object[]> getCandidatePreviousApptDetails1(List<Long> candidateIds){
    	
    	Query query = getSession().createSQLQuery("" +
    	 " select distinct acr.appointment_candidate_id as candidId,acr.appointment_id as appId,date(a.inserted_time) as date, " +
    	 "        a.appointment_status_id as statusId,ass.status as status," +
    	 "        a.inserted_time as insertedtime,a.updated_time updatedtime,ats.from_date as fromDate,ats.to_date as toDate" +
    	 " from   appointment_candidate_relation acr left join appointment_time_slot ats on acr.appointment_id=ats.appointment_id and ats.is_deleted='N' " +
    	 "        join appointment a on a.appointment_id = acr.appointment_id " +
    	 "        join appointment_status ass on ass.appointment_status_id = a.appointment_status_id " +
    	 " where  a.is_deleted='N' and acr.appointment_candidate_id in (:candidateIds)" )
    	 .addScalar("candidId",Hibernate.LONG)
		 .addScalar("appId",Hibernate.LONG)
		 .addScalar("date",Hibernate.DATE)
		 .addScalar("statusId",Hibernate.LONG)
		 .addScalar("status",Hibernate.STRING)
		 .addScalar("insertedTime",Hibernate.TIMESTAMP)
		 .addScalar("updatedtime",Hibernate.TIMESTAMP)
		 .addScalar("fromDate",Hibernate.TIMESTAMP)
		 .addScalar("toDate",Hibernate.TIMESTAMP);
		
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
	
	public List<Object[]> getAppointmentSearchDetails(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType)
	{
		StringBuffer str = new StringBuffer();
		str.append("select model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name," +
				"model.appointmentCandidate.mobileNo, " +
				"model.appointmentCandidate.candidateDesignation.designation," +
				"model.appointment.reason,model.appointment.createdUser.userId,model.appointment.createdUser.firstName," +
				" model.appointment.createdUser.lastName,model1.fromDate,model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentStatus.status,model.appointment.appointmentId,model1.toDate" +
				" from AppointmentCandidateRelation model,AppointmentTimeSlot model1 " +
				" where model.appointment.isDeleted='N'" +
				" and model.appointment.appointmentId = model1.appointment.appointmentId");
		if(inputVo.getCreatedBy() != null && inputVo.getCreatedBy()  > 0)
			str.append(" and model.appointment.createdUser.userId = :createdBy");
		if(inputVo.getName()!= null && !inputVo.getName().isEmpty())
		{
			if(searchType.equalsIgnoreCase("name"))
			str.append(" and model.appointmentCandidate.name like '%"+inputVo.getName()+"%'");
			else
				str.append(" and model.appointmentCandidate.mobileNo like '%"+inputVo.getName()+"%'");	
		}
		if(inputVo.getUserId() != null && inputVo.getUserId()  > 0)
			str.append(" and model.appointment.appointmentUser.appointmenUserId =:appointmenUserId");		
		
		if(fromDate != null)
		{
			str.append(" and date(model1.fromDate) >=:fromDate and date(model1.toDate)<=:toDate");
		}
		str.append(" group by model.appointment.appointmentId,model.appointmentCandidate.appointmentCandidateId order by model1.insertedTime");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVo.getUserId() != null && inputVo.getUserId()  > 0)
			query.setParameter("appointmenUserId", inputVo.getUserId());
		if(inputVo.getCreatedBy() != null && inputVo.getCreatedBy()  > 0)
			query.setParameter("createdBy", inputVo.getCreatedBy());
		
		return query.list();
		
	}
public List<Object[]> getLastVisitsByCandidates(List<Long> candidateIds){
		
		Query query = getSession().createQuery("" +
				" select    model.appointmentCandidateId,max(model1.fromDate),max(model1.toDate)" +
				" from      AppointmentCandidateRelation model,AppointmentTimeSlot model1 " +
				" where     model.appointment.appointmentId = model1.appointment.appointmentId   and model.appointment.isDeleted='N' " +
				"           and model.appointmentCandidateId in (:candidateIds) and model1.isDeleted='N' " +
				" group by  model.appointmentCandidateId ");
		query.setParameterList("candidateIds",candidateIds);
		return query.list();
	}
	
	public List<Object[]> getAppointmentCandidateMobileNos(Long appointmentId){
		Query query = getSession().createQuery(" select model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name," +
				"model.appointmentCandidate.mobileNo " +
				" from AppointmentCandidateRelation model " +
				" where model.appointment.isDeleted='N' and model.appointment.appointmentId =:appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		return query.list();
	}
	
}
