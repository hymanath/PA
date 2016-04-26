package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;
import com.itgrids.partyanalyst.utils.IConstants;

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
    
	public List<Object[]> getAppointmentsBySearchCriteria1(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate,Long selUserId,
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
				//" where     a.is_deleted='N' and a.is_labelled = 'N' ");
				" where a.is_deleted='N' and a.appointment_status_id not in (:labelStatus) ");
		
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
					sb.append(" and date(a.inserted_time) >= :fromDate");
				}
		        if(toDate!=null){
		        	sb.append(" and date(a.inserted_time) <= :toDate");
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
	
	query.setParameterList("labelStatus", IConstants.APPOINTMENT_STATUS_LABELED_LIST);
		return query.list();
	}
	
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds){
		
		Query query = getSession().createQuery("" +
		 " select  model.appointment.appointmentId ," +
		 "          appointmentCandidate.appointmentCandidateId,appointmentCandidate.name,appointmentCandidate.tdpCadreId," +
		 "         appointmentCandidate.mobileNo,appointmentCandidate.candidateDesignation.designation," +
		 "         constituency.name,appointmentCandidate.imageURL,appointmentCandidate.appointmentCandidateTypeId " +
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
    public List<Object[]> getCandidatePreviousApptDetails1(List<Long> candidateIds,Long apptUserID){
    	
    	Query query = getSession().createSQLQuery("" +
    	 " select distinct acr.appointment_candidate_id as candidId,acr.appointment_id as appId,date(a.inserted_time) as date, " +
    	 "        a.appointment_status_id as statusId,ass.status as status," +
    	 "        a.inserted_time as insertedtime,a.updated_time updatedtime,ats.from_date as fromDate,ats.to_date as toDate,a.appointment_unique_id as uniqueId" +
    	 " from   appointment_candidate_relation acr left join appointment_time_slot ats on acr.appointment_id=ats.appointment_id and ats.is_deleted='N' " +
    	 "        join appointment a on a.appointment_id = acr.appointment_id " +
    	 "        join appointment_status ass on ass.appointment_status_id = a.appointment_status_id " +
    	 " where  a.is_deleted='N' and acr.appointment_candidate_id in (:candidateIds) and a.appointment_user_id=:apptUserID " +
    	 " order by a.inserted_time " )
    	 .addScalar("candidId",Hibernate.LONG)
		 .addScalar("appId",Hibernate.LONG)
		 .addScalar("date",Hibernate.DATE)
		 .addScalar("statusId",Hibernate.LONG)
		 .addScalar("status",Hibernate.STRING)
		 .addScalar("insertedTime",Hibernate.TIMESTAMP)
		 .addScalar("updatedtime",Hibernate.TIMESTAMP)
		 .addScalar("fromDate",Hibernate.TIMESTAMP)
		 .addScalar("toDate",Hibernate.TIMESTAMP)
		 .addScalar("uniqueId",Hibernate.STRING);
		
		query.setParameterList("candidateIds",candidateIds);
		query.setMaxResults(IConstants.APPOINTMENT_HISTORY_MAX_RESULT);
		query.setParameter("apptUserID", apptUserID);
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
				" model.appointment.appointmentUniqueId,model1.date,model.appointmentCandidate.imageURL ," +
				" model.appointment.appointmentStatus.statusColor "+
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
public List<Object[]> getLastVisitsByCandidates(List<Long> candidateIds,Long apptUserId){
		
		Query query = getSession().createQuery("" +
				" select    model.appointmentCandidateId,max(model1.fromDate),max(model1.toDate)" +
				" from      AppointmentCandidateRelation model,AppointmentTimeSlot model1 " +
				" where     model.appointment.appointmentId = model1.appointment.appointmentId   and model.appointment.isDeleted='N' " +
				"           and model.appointmentCandidateId in (:candidateIds) and model1.isDeleted='N' " +
				"			and model.appointment.appointmentUserId=:apptUserId " +
				" group by  model.appointmentCandidateId ");
		query.setParameterList("candidateIds",candidateIds);
		query.setParameter("apptUserId", apptUserId);
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
	
	public List<Object[]> getAppointmentStatusOverview(Long appointmentUserId)
	{
		StringBuffer str = new StringBuffer();
		str.append("select count(distinct model.appointment.appointmentId)," +
				" model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentStatus.status" +
				" from AppointmentCandidateRelation model" +
				" where model.appointment.isDeleted='N' and model.appointment.appointmentUser.appointmenUserId = :appointmentUserId");
		
		str.append(" group by model.appointment.appointmentStatus.appointmentStatusId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("appointmentUserId",appointmentUserId);
		return query.list();
		
	}
	
	public List<Object[]> getAppointStatusOverviewforCandidate(Long apointmntcandidteId,Long apptUserId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select count(distinct model.appointment.appointmentId)," +
	        " model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status" +
	        " from AppointmentCandidateRelation model" +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId " +
	        "and model.appointment.appointmentUserId=:apptUserId ");
	    																									  
	    str.append(" group by model.appointment.appointmentStatus.appointmentStatusId ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	    query.setParameter("apptUserId", apptUserId);
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
  
	public List<Object[]> getFixedAttendedCount(Long apointmntcandidteId,Date curDateAndTime,Long apptUserId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select count(distinct model.appointment.appointmentId)," +
	        " model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status" +
	        " from AppointmentCandidateRelation model,AppointmentTimeSlot model1 " +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId"
	        + " and model.appointment.appointmentId = model1.appointment.appointmentId "
	        + " and model.appointment.appointmentStatus.appointmentStatusId =:statusId and "
	        + " model1.toDate <= :date and model.appointment.appointmentUserId=:apptUserId ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	    query.setTimestamp("date", curDateAndTime);
	    query.setParameter("statusId", IConstants.APPOINTMENT_STATUS_FIXED);
	    query.setParameter("apptUserId", apptUserId);
	    return query.list();
	}
	
	public List<Object[]> getAppointmentHistoryDetailsByCandidateId(Long apointmntcandidteId,Long apptUserId)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select model.appointment.appointmentUniqueId,model.appointment.reason,"
	    		+ "date(model.appointment.insertedTime),"
	    		+ "model.appointment.appointmentStatus.appointmentStatusId," +
	        " model.appointment.appointmentStatus.status,model.appointment.appointmentId" +
	        " from AppointmentCandidateRelation model " +
	        " where model.appointment.isDeleted='N' and  model.appointmentCandidate.appointmentCandidateId = :apointmntcandidteId " +
	        " and model.appointment.appointmentUserId=:apptUserId ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameter("apointmntcandidteId", apointmntcandidteId);
	    query.setParameter("apptUserId", apptUserId);
	   return query.list();
	}
    
	
	public List<Long> getApptCandidIds(Long appointmentId){
		Query query = getSession().createQuery(" select model.appointmentCandidate.appointmentCandidateId" +
				" from AppointmentCandidateRelation model " +
				" where model.appointment.isDeleted='N' and model.appointment.appointmentId =:appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		return query.list();
	}
	
	public List<Object[]> getApptCandidIdsAndInsertedTime(Long appointmentId){
		Query query = getSession().createQuery(" select model.appointmentCandidate.appointmentCandidateId,model.appointment.insertedTime" +
				" from AppointmentCandidateRelation model " +
				" where model.appointment.isDeleted='N' and model.appointment.appointmentId =:appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		return query.list();
	}
	public List<Object[]> checkApptsAsVoid(Date insertedTime,Long apptStatusId,List<Long> apptCandiIds,Long apptUserId){
		
		Query query = getSession().createSQLQuery(" " +
		" select distinct app.appointment_id as apptId,acr.appointment_candidate_id as apptCandiId" +
		" from   appointment app join appointment_candidate_relation acr on app.appointment_id = acr.appointment_id" +
		" where  app.appointment_id in " +
		"       ( " +
		"         select distinct a.appointment_id from   appointment a join appointment_candidate_relation acr on a.appointment_id = acr.appointment_id " +
		"         where  a.inserted_time < :insertedTime and a.appointment_status_id= :apptStatusId and acr.appointment_candidate_id in (:apptCandiIds) " +
		"                and a.is_deleted = 'N' and a.appointment_user_id = :apptUserId " +
		"        )" +
		
		"  ").addScalar("apptId",Hibernate.LONG).addScalar("apptCandiId",Hibernate.LONG);
		
		query.setParameter("apptStatusId", apptStatusId);
		query.setParameterList("apptCandiIds",apptCandiIds);
		query.setTimestamp("insertedTime",insertedTime);
		query.setParameter("apptUserId", apptUserId);
		return query.list();
	}
	public List<Object[]> getCandidCountsByStatesAndStatus(Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate){
	
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId," +
				"          model.appointmentCandidate.userAddress.district.districtId,model.appointmentCandidate.userAddress.district.districtName," +
				"          count(model.appointmentCandidate.appointmentCandidateId),count(distinct model.appointmentCandidate.appointmentCandidateId) " +
				"   from   AppointmentCandidateRelation model" +
				"   where  model.appointment.isDeleted='N' and model.appointment.appointmentUser.appointmenUserId =:appointmentUserId "); 
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			sb.append(" and model.appointment.appointmentStatusId in (:apptStatusIds)");
		}
		if(startDate!=null){
			sb.append(" and date(model.appointment.updatedTime) >= : startDate");
		}
		if(endDate!=null){
			sb.append(" and date(model.appointment.updatedTime) <= : endDate");
		}
		sb.append(" group by model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId,model.appointmentCandidate.userAddress.district.districtId " +
				  " order by model.appointmentCandidate.userAddress.district.districtId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			query.setParameterList("apptStatusIds", apptStatusIds);
		}
		query.setParameter("appointmentUserId",appointmentUserId);
		
		return query.list();
	}
	public List<Object[]> getTotalCountCandiByLocation(String queryString,Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate){
		
		
		Query query = getSession().createQuery(queryString);
		
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			query.setParameterList("apptStatusIds", apptStatusIds);
		}
		query.setParameter("appointmentUserId",appointmentUserId);
		
		return query.list();
	}
	public List<Object[]> getCountsOfCandiByLocation(String queryString,Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate,List<Long> candiTypeIds){
		
		
		Query query = getSession().createQuery(queryString);
		
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		if(apptStatusIds!=null && apptStatusIds.size()>0){
			query.setParameterList("apptStatusIds", apptStatusIds);
		}
		query.setParameter("appointmentUserId",appointmentUserId);
		if(candiTypeIds!=null && candiTypeIds.size()>0 && !candiTypeIds.contains(0l)){
			query.setParameterList("candiTypeIds", candiTypeIds);
		}
		return query.list();
	}
	public List<Object[]>  getCommitteeLevelAppointments(List<Long> statusIds,String type){
		StringBuilder str=new StringBuilder();
		str.append("select ");
		if(type.equalsIgnoreCase("unique"))
		str.append("count(distinct model.appointmentCandidate.tdpCadreId),");
		else
			str.append("count(model.appointmentCandidate.tdpCadreId),");
		str.append(" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId, TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel " + 
		"from TdpCommitteeMember TCM, AppointmentCandidateRelation model " +
		" where  model.appointmentCandidate.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId ");
		if(statusIds != null && statusIds.size() > 0)
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		str.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
		
		 Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 return query.list();
		}
	public List<Object[]>  getLevelWiseCount(List<Long> statusIds,String type, Long levelId){
		StringBuilder str=new StringBuilder();
		str.append("select ");
		if(type.equalsIgnoreCase("unique"))
		str.append("count(distinct model.appointmentCandidate.tdpCadreId),");
		else
			str.append("count(model.appointmentCandidate.tdpCadreId),");
		str.append(" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId, TCM.tdpCommitteeRole.tdpRoles.role " + 
		"from TdpCommitteeMember TCM, AppointmentCandidateRelation model " +
		" where  model.appointmentCandidate.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId = :levelId ");
		if(statusIds != null && statusIds.size() > 0)
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		str.append(" group by TCM.tdpCommitteeRole.tdpRoles.tdpRolesId ");
		
		 Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 
		 query.setParameter("levelId",levelId);
		 return query.list();
		}
	
	public List<Object[]> checkIsAppointmentEligible(List<String> membershipNoList,List<Long> apptStatusIds,Long appointmentUserId){
		
		Query query = getSession().createQuery("" +
		" select  acr.appointment.appointmentId,acr.appointmentCandidate.appointmentCandidateId" +
		" from    AppointmentCandidateRelation acr " +
		" where   acr.appointment.appointmentStatusId in (:apptStatusIds) and acr.appointment.isDeleted='N' " +
		"         and acr.appointmentCandidate.membershipId in (:membershipNoList) " +
		"         and acr.appointment.appointmentUserId = :appointmentUserId");
		
		query.setParameterList("membershipNoList", membershipNoList);
		query.setParameterList("apptStatusIds", apptStatusIds);
		query.setParameter("appointmentUserId", appointmentUserId);
		return query.list();
	}
	
	public List<Object[]> getAppointmentSearchDetailsForStatusSSS(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType)
	{
		StringBuffer str = new StringBuffer();
		str.append("select model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name," +
				"model.appointmentCandidate.mobileNo, " +
				"model.appointmentCandidate.candidateDesignation.designation," +
				"model.appointment.reason,model.appointment.createdUser.userId,model.appointment.createdUser.firstName," +
				" model.appointment.createdUser.lastName,0,model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentStatus.status,model.appointment.appointmentId,0," +
				" model.appointment.appointmentUniqueId,0,model.appointmentCandidate.imageURL ," +
				" model.appointment.appointmentStatus.statusColor "+
				" from AppointmentCandidateRelation model" +
				//" left join AppointmentTimeSlot model1 " +
				" where model.appointment.isDeleted='N' " );
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
		
		if(fromDate != null && toDate !=null)
		{
			str.append(" and date(model.appointment.insertedTime) between :fromDate and :toDate  ");
		}
		
		if(inputVo.getStatusIds() !=null && inputVo.getStatusIds().size()>0){
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId in (:statusIds) ");
		}
		
		
		str.append(" group by model.appointment.appointmentId,model.appointmentCandidate.appointmentCandidateId order by model.appointment.insertedTime");
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
		
		if(inputVo.getStatusIds() !=null && inputVo.getStatusIds().size()>0){
			query.setParameterList("statusIds", inputVo.getStatusIds());
		}
		
		return query.list();
		
	}
	
	public List<Object[]> eachStatusApptCountAndUniqueMemCount(Long apptUserId,List<Long> statusIds){
		  
	    Query query = getSession().createQuery("" +
	     " select   model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status," +
	     "          count(distinct model.appointment.appointmentId),count(distinct model.appointmentCandidate.appointmentCandidateId)" +
	     " from     AppointmentCandidateRelation model  " +
	     " where    model.appointment.isDeleted='N' and model.appointment.appointmentUserId = :apptUserId " +
	     "          and model.appointment.appointmentStatusId in (:statusIds)" +
	     " group by model.appointment.appointmentStatusId ");
	    query.setParameter("apptUserId", apptUserId);
	    query.setParameterList("statusIds", statusIds);
	    return query.list();
	 }
	
	public Object[] combinedStatusApptAndUniqueMemCount(Long apptUserId,List<Long> statusIds){
		  
	    Query query = getSession().createQuery("" +
	     " select   count(distinct model.appointment.appointmentId),count(distinct model.appointmentCandidate.appointmentCandidateId)" +
	     " from     AppointmentCandidateRelation model  " +
	     " where    model.appointment.isDeleted='N' and model.appointment.appointmentUserId = :apptUserId " +
	     "          and model.appointment.appointmentStatusId in (:statusIds) ");
	    query.setParameter("apptUserId", apptUserId);
	    query.setParameterList("statusIds", statusIds);
	    return (Object[])query.uniqueResult();
	 }
	
	
	public List<Object[]> getAppointmentSearchDetailsForStatus(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT ac.appointment_candidate_id as apptCandId,ac.name as name,ac.mobile_no as mobileNo,acd.designation as designation," +
				" a.reason as reason ,u.user_id as userId,u.firstname as firstName," +
				" u.lastname as lastName,ats.from_date as fromDate,asts.appointment_status_id as aptStatusId,asts.status as aptStatus,a.appointment_id as aptId" +
				" ,ats.to_date as toDate," +
				"a.appointment_unique_id as uniqueId,ats.date as date,ac.image_url as url," +
				" asts.status_color as colour,ac.tdp_cadre_id as tdpCadreId" +
				
					" FROM " +
					
					"  appointment_candidate_relation acr " +
					" join appointment_candidate ac on acr.appointment_candidate_id=ac.appointment_candidate_id " +
					" join appointment a on acr.appointment_id=a.appointment_id " +
					" join appointment_candidate_designation acd on ac.designation_id = acd.appointment_candidate_designation_id  " +
					" join user u on a.created_by = u.user_id " +
					" join appointment_status asts  on a.appointment_status_id = asts.appointment_status_id" +
					" join appointment_user au on au.appointment_user_id = a.appointment_user_id" +
					"  left join appointment_time_slot ats on a.appointment_id = ats.appointment_id " +

					" WHERE a.is_deleted = 'N' ");
		
		
		if(inputVo.getCreatedBy() != null && inputVo.getCreatedBy()  > 0)
			str.append(" and a.created_by = :createdBy");
		if(inputVo.getName()!= null && !inputVo.getName().isEmpty())
		{
			if(searchType.equalsIgnoreCase("name"))
			str.append(" and ac.name like '%"+inputVo.getName()+"%'");
			else
				str.append(" and ac.mobile_no like '%"+inputVo.getName()+"%'");	
		}
		if(inputVo.getUserId() != null && inputVo.getUserId()  > 0)
			str.append(" and au.appointment_user_id =:appointmenUserId");		
		
		if(fromDate != null && toDate !=null)
		{
			str.append(" and date(a.updated_time) between :fromDate and :toDate  ");
		}
		
		if(inputVo.getStatusIds() !=null && inputVo.getStatusIds().size()>0){
			str.append(" and asts.appointment_status_id in (:statusIds) ");
		}
		
		
		str.append(" group by a.appointment_id,ac.appointment_candidate_id order by a.updated_time desc");
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("apptCandId",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING)
				.addScalar("mobileNo",Hibernate.STRING)
				.addScalar("designation",Hibernate.STRING)
				.addScalar("reason",Hibernate.STRING)
				.addScalar("userId",Hibernate.LONG)
				.addScalar("firstName",Hibernate.STRING)
				.addScalar("lastName",Hibernate.STRING)
				.addScalar("fromDate",Hibernate.STRING)
				.addScalar("aptStatusId",Hibernate.LONG)
				.addScalar("aptStatus",Hibernate.STRING)
				.addScalar("aptId",Hibernate.LONG)
				.addScalar("toDate",Hibernate.STRING)
				.addScalar("uniqueId",Hibernate.STRING)
				.addScalar("date",Hibernate.STRING)
				.addScalar("url",Hibernate.STRING)
				.addScalar("colour",Hibernate.STRING)   
				.addScalar("tdpCadreId",Hibernate.LONG)
				;
		
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVo.getUserId() != null && inputVo.getUserId()  > 0)
			query.setParameter("appointmenUserId", inputVo.getUserId());
		if(inputVo.getCreatedBy() != null && inputVo.getCreatedBy()  > 0)
			query.setParameter("createdBy", inputVo.getCreatedBy());
		
		if(inputVo.getStatusIds() !=null && inputVo.getStatusIds().size()>0){
			query.setParameterList("statusIds", inputVo.getStatusIds());
		}
		
		return query.list();
		
	}
	public List<Object[]> getAllScheduledApptsByDate(Long apptUserId,Date date,Long apptStatusId){
		
		StringBuilder sb =new StringBuilder();
		
		sb.append(" select   ACR.appointmentCandidate.appointmentCandidateId, ACR.appointmentCandidate.name," +
				"            ACR.appointmentCandidate.mobileNo,ACR.appointmentCandidate.candidateDesignation.designation," +
				"            ACR.appointment.reason,ACR.appointment.createdUser.userId,ACR.appointment.createdUser.firstName,ACR.appointment.createdUser.lastName," +
				"            ATS.fromDate," +
				"            ACR.appointment.appointmentStatusId,ACR.appointment.appointmentStatus.status,ACR.appointment.appointmentId," +
				"            ATS.toDate," +
				"            ACR.appointment.appointmentUniqueId,ATS.date,ACR.appointmentCandidate.imageURL,ACR.appointment.appointmentStatus.statusColor " +
				"   from     AppointmentCandidateRelation ACR, AppointmentTimeSlot ATS " +
				"   where    ACR.appointment.appointmentId = ATS.appointment.appointmentId " +
				"            and ACR.appointment.isDeleted='N' and ATS.isDeleted='N' ");
		
				if(apptUserId != null && apptUserId >0l){
					sb.append(" and ACR.appointment.appointmentUserId = :apptUserId");
				}
				if( date!=null ){
					sb.append(" and ATS.date = :date");
				}
				if(apptStatusId!=null && apptStatusId >0l){
					sb.append(" and ACR.appointment.appointmentStatusId = :apptStatusId");
				}
				sb.append(" order by ACR.appointment.updatedTime desc");
				
		Query query = getSession().createQuery(sb.toString());
		
		if(apptUserId != null && apptUserId >0l){
			query.setParameter("apptUserId",apptUserId);
		}
		if( date!=null ){
			query.setDate("date",date);
		}
		if(apptStatusId!=null && apptStatusId >0l){
			query.setParameter("apptStatusId",apptStatusId);
		}
		return query.list();
	}
	
	
	
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate,Long selUserId,
			Long candidateTypeId,Long dateTypeValue){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select  distinct acr.appointment_id as appid, a.reason as reason, ap.priority as priority, ass.status as status," +
				"            a.inserted_time as insertedTime,a.appointment_unique_id as uniqueId" +
				"   from    appointment_candidate_relation acr " +
				"           join appointment                  a    on  acr.appointment_id=a.appointment_id " +
				"           join appointment_priority         ap   on  a.appointment_priority_id=ap.appointment_priority_id " +
				"           join appointment_status           ass  on  a.appointment_status_id=ass.appointment_status_id " +
				"           join appointment_preferable_date  apd  on  acr.appointment_id=apd.appointment_id " +
				"           join appointment_candidate        ac   on  acr.appointment_candidate_id=ac.appointment_candidate_id " +
				"           join user_address                 ua   on  ac.address_id=ua.user_address_id " +
				
				" where a.is_deleted='N' ");
		
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
					sb.append(" and date(a.inserted_time) >= :fromDate");
				}
		        if(toDate!=null){
		        	sb.append(" and date(a.inserted_time) <= :toDate");
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
	
	//query.setParameterList("labelStatus", IConstants.APPOINTMENT_STATUS_LABELED_LIST);
		return query.list();
	}
public List<Object[]> checkIsAppointmentEligibleCadre(List<Long> cadreList,List<Long> apptStatusIds,Long appointmentUserId){
		
		Query query = getSession().createQuery("" +
		" select  acr.appointment.appointmentId, acr.appointment.appointmentUniqueId,acr.appointmentCandidate.appointmentCandidateId,acr.appointmentCandidate.tdpCadre.tdpCadreId" +
		",acr.appointment.appointmentStatusId,acr.appointment.appointmentStatus.status" +
		" from    AppointmentCandidateRelation acr " +
		" where   acr.appointment.appointmentStatusId in (:apptStatusIds) and acr.appointment.isDeleted='N' " +
		"         and acr.appointmentCandidate.tdpCadre.tdpCadreId in (:cadreNoList) " +
		"         and acr.appointment.appointmentUserId = :appointmentUserId");
		
		query.setParameterList("cadreNoList", cadreList);
		query.setParameterList("apptStatusIds", apptStatusIds);
		query.setParameter("appointmentUserId", appointmentUserId);
		return query.list();
	}
	
}
