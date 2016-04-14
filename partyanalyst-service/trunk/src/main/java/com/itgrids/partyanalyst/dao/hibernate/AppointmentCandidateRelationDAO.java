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
	public List<Object[]> getAllAppointmentDetails(int startIndex,int maxIndex,Long aptUserId){
		Query query = getSession().createQuery("select model.appointmentCandidate.name, model.appointmentCandidate.mobileNo,model.appointmentCandidate.updatedTime,model.appointment.appointmentUniqueId,model.appointmentCandidate.candidateDesignation.designation from AppointmentCandidateRelation model where model.appointment.isDeleted='N'" +
				" and model.appointment.appointmentUserId = :aptUserId");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("aptUserId", aptUserId);
		return query.list();
	}
	public List<Object[]> countAppointmentDetails(){
		Query query = getSession().createQuery("select model.appointmentCandidate.name, model.appointmentCandidate.mobileNo,model.appointmentCandidate.updatedTime,model.appointment.appointmentUniqueId,model.appointmentCandidate.candidateDesignation.designation from AppointmentCandidateRelation model where model.appointment.isDeleted='N'");
		return query.list();
	}
    
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate,Long selUserId,
			Long candidateTypeId,Long dateTypeValue){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select  distinct acr.appointment_id as appid, a.reason as reason, ap.priority as priority, ass.status as status," +
				"            a.inserted_time as insertedTime,la.appointment_label_id as applabelId,a.appointment_unique_id as uniqueId" +
				"   from    appointment_candidate_relation acr " +
				"           join appointment                  a    on  acr.appointment_id=a.appointment_id " +
				"           join appointment_priority         ap   on  a.appointment_priority_id=ap.appointment_priority_id " +
				"           join appointment_status           ass  on  a.appointment_status_id=ass.appointment_status_id " +
				"           join appointment_preferable_date  apd  on  acr.appointment_id=apd.appointment_id " +
				"           join appointment_candidate        ac   on  acr.appointment_candidate_id=ac.appointment_candidate_id " +
				"           join user_address                 ua   on  ac.address_id=ua.user_address_id " +
				"           left join label_appointment       la   on  la.appointment_id=a.appointment_id and la.is_deleted='N' " +
				" where     a.is_deleted='N' and a.is_labelled = 'N' ");
		
		if(selUserId != null && selUserId > 0l){
			sb.append(" and a.appointment_user_id = :selUserId ");
		}
		
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
		
		if(dateTypeValue !=null && dateTypeValue >0){
			if(dateTypeValue == 1l){
				if(fromDate!=null){
					sb.append(" and date(apd.appointment_date) >= :fromDate");
				}
		        if(toDate!=null){
		        	sb.append(" and date(apd.appointment_date) <= :toDate");
		        }
			}else{
				if(fromDate!=null){ 
					sb.append(" and date(a.updated_time) >= :fromDate");
				}
		        if(toDate!=null){
		        	sb.append(" and date(a.updated_time) <= :toDate");
		        }
			}
			 
		}
		
		if(candidateTypeId !=null && candidateTypeId>0){
			sb.append(" and ac.appointment_candidate_type_id = :candidateTypeId ");
		}
		
		sb.append(" order by a.inserted_time desc ");
		
		Query query = getSession().createSQLQuery(sb.toString())
				 .addScalar("appid",Hibernate.LONG)
				 .addScalar("reason",Hibernate.STRING)
				 .addScalar("priority",Hibernate.STRING)
				 .addScalar("status",Hibernate.STRING)
				 .addScalar("insertedTime",Hibernate.TIMESTAMP)
				 .addScalar("applabelId",Hibernate.LONG)
				 .addScalar("uniqueId",Hibernate.STRING);
		        
		if(selUserId != null && selUserId > 0l){
			query.setParameter("selUserId",selUserId);
		}
		
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
	if(dateTypeValue !=null && dateTypeValue >0){
		if(fromDate!=null){
			query.setDate("fromDate",fromDate);	
		}
        if(toDate!=null){
        	query.setDate("toDate",toDate);	
        }
	}
	
	if(candidateTypeId !=null && candidateTypeId>0){
		query.setParameter("candidateTypeId",candidateTypeId);
	}
	
		return query.list();
	}
	
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds){
		
		Query query = getSession().createQuery("" +
		 " select  model.appointment.appointmentId ," +
		 "          appointmentCandidate.appointmentCandidateId,appointmentCandidate.name,appointmentCandidate.tdpCadreId," +
		 "         appointmentCandidate.mobileNo,appointmentCandidate.candidateDesignation.designation," +
		 "         constituency.name " +
		 " from    AppointmentCandidateRelation model " +
		 " LEFT JOIN model.appointmentCandidate appointmentCandidate " +
		 " LEFT JOIN appointmentCandidate.userAddress.constituency constituency " +
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
				" model.appointment.appointmentStatus.status,model.appointment.appointmentId,model1.toDate," +
				" model.appointment.appointmentUniqueId,model1.date " +
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
	
	public List<Object[]> getAppointmentStatusOverview()
	{
		StringBuffer str = new StringBuffer();
		str.append("select count(distinct model.appointment.appointmentId)," +
				" model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentStatus.status" +
				" from AppointmentCandidateRelation model" +
				" where model.appointment.isDeleted='N'");
		
		str.append(" group by model.appointment.appointmentStatus.appointmentStatusId ");
		Query query = getSession().createQuery(str.toString());
		
		return query.list();
		
	}
	
	public List<Object[]> getAppointStatusOverviewforCandidate(Long apointmntcandidteId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select count(distinct model.appointment.appointmentId)," +
	        " model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status" +
	        " from AppointmentCandidateRelation model" +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId");
	    																									  
	    str.append(" group by model.appointment.appointmentStatus.appointmentStatusId ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	    return query.list();
	}
	
public List<String> getAppointmentIdsforSendSms(Long appointmentId){
		
		Query query = getSession().createQuery("" +
		 " select   distinct model.appointmentCandidate.mobileNo " +
		 " from    AppointmentCandidateRelation model " +
		 " where   model.appointmentId =:appointmentId and model.appointment.isDeleted='N'");
		
		query.setParameter("appointmentId",appointmentId);
		return query.list();
	}


public List<Object[]> getApptAndMembersCountsByStatus(Long apptUserId){
  
    Query query = getSession().createQuery("" +
     " select   model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status," +
     "          count(distinct model.appointment.appointmentId),count(distinct model.appointmentCandidate.appointmentCandidateId)" +
     " from     AppointmentCandidateRelation model  " +
     " where    model.appointment.isDeleted='N' and model.appointment.appointmentUserId = :apptUserId " +
     " group by model.appointment.appointmentStatusId ");
    query.setParameter("apptUserId", apptUserId);
    return query.list();
    
  }
  public List<Object[]> getLabelledAndNonLabelledApptIdsForWaitingStatus(Long apptUserId,String labelStatus,Long waitingAppointmentStatusId){
    
    Query query = getSession().createQuery("" +
    "select count(distinct model.appointment.appointmentId),count(distinct model.appointmentCandidate.appointmentCandidateId) " +
    "from AppointmentCandidateRelation model " +
    "where model.appointment.isDeleted='N' and model.appointment.appointmentUserId = :apptUserId and model.appointment.appointmentStatusId =:waitingAppointmentStatusId and model.appointment.isLabelled =:labelStatus ");
    query.setParameter("apptUserId", apptUserId);
    query.setParameter("labelStatus", labelStatus);
    query.setParameter("waitingAppointmentStatusId", waitingAppointmentStatusId);
    return query.list();
  }
  public List<Object[]> getOnlyFixedStatusCounts(Long apptUserId,Date currentDateAndTime,Long fixedAppointmentStatusId){
    
    Query query = getSession().createQuery("" +
    " select  count(distinct ats.appointment.appointmentId),count(distinct acr.appointmentCandidate.appointmentCandidateId)" +
    " from   AppointmentTimeSlot ats,AppointmentCandidateRelation acr" +
    " where  ats.appointment.appointmentId = acr.appointment.appointmentId " +
    "        and acr.appointment.appointmentStatusId = :fixedAppointmentStatusId and ats.toDate > :date" +
    "        and ats.isDeleted='N' and ats.appointment.isDeleted='N' and ats.appointment.appointmentUserId =:apptUserId ");
             
    
    query.setParameter("apptUserId", apptUserId);
    query.setTimestamp("date",currentDateAndTime);
    query.setParameter("fixedAppointmentStatusId",fixedAppointmentStatusId);
    return query.list();
  }
  
  public List<Object[]> getAttendedStatusCounts(Long apptUserId,Date currentDateAndTime,Long attendedAppointmentStatusId,Long fixedAppointmentStatusId){
    
    Query query = getSession().createQuery("" +
    " select  count(distinct ats.appointment.appointmentId),count(distinct acr.appointmentCandidate.appointmentCandidateId)" +
    " from   AppointmentTimeSlot ats,AppointmentCandidateRelation acr" +
    " where  ats.appointment.appointmentId = acr.appointment.appointmentId " +
    "        and ats.isDeleted='N' and ats.appointment.isDeleted='N'  and ats.appointment.appointmentUserId =:apptUserId " +
    "        and (acr.appointment.appointmentStatusId = :attendedAppointmentStatusId OR (acr.appointment.appointmentStatusId =:fixedAppointmentStatusId " +
    "        and ats.toDate < :date ))  " +
    "         ");
    
    query.setParameter("apptUserId", apptUserId);
    query.setTimestamp("date",currentDateAndTime);
    query.setParameter("fixedAppointmentStatusId",fixedAppointmentStatusId);
    query.setParameter("attendedAppointmentStatusId",attendedAppointmentStatusId);
    return query.list();
  }
  
	public List<Object[]> getFixedAttendedCount(Long apointmntcandidteId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select count(distinct model.appointment.appointmentId)," +
	        " model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status" +
	        " from AppointmentCandidateRelation model,AppointmentTimeSlot model1 " +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId"
	        + " and model.appointment.appointmentId = model1.appointment.appointmentId "
	        + " and model.appointment.appointmentStatus.appointmentStatusId =2 and "
	        + " model1.toDate <= :date");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	    query.setTimestamp("date", new Date());
	    return query.list();
	}
	
	public List<Object[]> getAppointmentHistoryDetailsByCandidateId(Long apointmntcandidteId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select model.appointment.appointmentUniqueId,model.appointment.reason,"
	    		+ "date(model.appointment.insertedTime),"
	    		+ "model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status,model.appointment.appointmentId" +
	        " from AppointmentCandidateRelation model " +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	   return query.list();
	}

}
