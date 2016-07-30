package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentTrackingDAO;
import com.itgrids.partyanalyst.model.AppointmentTracking;
import com.itgrids.partyanalyst.utils.IConstants;

public class AppointmentTrackingDAO extends GenericDaoHibernate<AppointmentTracking, Long> implements IAppointmentTrackingDAO {

	public AppointmentTrackingDAO() {
		super(AppointmentTracking.class);
	}
	public List<Object[]> getAppointmentTrackingDetails(Long appointmentId)
	{
		Query query = getSession().createQuery("select model.appointmentStatus.appointmentStatusId,"
				+ "model.appointmentStatus.status,model.user.userId,model.user.firstName,model.user.lastName,model.remarks,model.actionTime,"
				+ "appointmentComment.appointmentCommentId,appointmentComment.comment,fromAppointmentStatus.appointmentStatusId,fromAppointmentStatus.status," +
				" model.appointmentAction.appointmentActionId,model.appointmentAction.action "
				+ " from AppointmentTracking model left join model.appointmentComment appointmentComment " +
				" left join model.fromAppointmentStatus fromAppointmentStatus"
				+ " where model.appointment.appointmentId = :appointmentId"
				+ " order by model.actionTime desc");
		query.setParameter("appointmentId", appointmentId);
		return query.list();
	}
	
	public Long getAllRescheduledApptCounts(Long apptUserId){
		  
	    Query query = getSession().createQuery(" " +
	    " select count(distinct tracking.appointment.appointmentId) " +
	    " from   AppointmentTracking tracking " +
	    " where  tracking.appointment.isDeleted='N' and tracking.appointment.appointmentUserId = (:apptUserId) " +
	    "        and tracking.appointmentStatusId = :appointmentStatusId ");
	    
	    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
	    query.setParameter("apptUserId",apptUserId);
	    return (Long)query.uniqueResult();
	  }
	  
	  public Long getAllRescheduledCandiCounts(Long apptUserId){
	    
	    Query query = getSession().createQuery(" " +
	    " select count(distinct model1.appointmentCandidate.appointmentCandidateId) " +
	    " from   AppointmentTracking model,AppointmentCandidateRelation model1 " +
	    " where  model.appointment.appointmentId = model1.appointment.appointmentId " +
	    "        and model.appointment.isDeleted='N' and model.appointment.appointmentUserId = (:apptUserId) " +
	    "        and model.appointmentStatusId = :appointmentStatusId ");
	    
	    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
	    query.setParameter("apptUserId",apptUserId);
	    return (Long)query.uniqueResult();
	  }
	  
	  public List<Object[]> getAllRescheduledApptAndCandiDetails(Long apptUserId){

		    StringBuilder sb = new StringBuilder();
		    
		    sb.append(" select   distinct A.appointment_id as apptId,A.appointment_unique_id as uniqueId,date(A.inserted_time) as createdDate,AST.status as presentStatus," +//3
		        "            AC.appointment_candidate_id as candiId,AC.name as CandiName,AC.image_url as ImageUrl,AC.mobile_no as mobileNo," +//7
		        "            AC.tdp_cadre_id as tdpcadreId,constituency.name as constname,acd.appointment_candidate_designation_id as candiDesigId,acd.designation as designation, " +//11
		        "            act.appointment_candidate_type_id as acid ,act.candidate_type as actype "+//13
		        
		        " from       appointment_candidate_relation ACR join appointment_candidate AC on ACR.appointment_candidate_id = AC.appointment_candidate_id" +
		        "            left join user_address ua on AC.address_id = ua.user_address_id " +
		        "            left join constituency constituency on ua.constituency_id = constituency.constituency_id " +
		        "            join appointment A on A.appointment_id = ACR.appointment_id " +
		        "            join appointment_status AST on AST.appointment_status_id = A.appointment_status_id " +
		        "            left join appointment_candidate_designation acd on AC.designation_id = acd.appointment_candidate_designation_id " +
		        "            left join appointment_candidate_type act on AC.appointment_candidate_type_id = act.appointment_candidate_type_id " +
		        "            join appointment_tracking T on A.appointment_id = T.appointment_id " +
		        
		        " where      A.is_deleted='N' and A.appointment_user_id = :apptUserId and T.appointment_status_id = :appointmentStatusId " +
		        " order by   A.appointment_id;");
		    
		    Query query = getSession().createSQLQuery(sb.toString())
		        .addScalar("apptId",Hibernate.LONG).addScalar("uniqueId",Hibernate.STRING).addScalar("createdDate",Hibernate.DATE)
		        .addScalar("presentStatus",Hibernate.STRING).addScalar("candiId",Hibernate.LONG).addScalar("CandiName",Hibernate.STRING).addScalar("ImageUrl",Hibernate.STRING)
		        .addScalar("mobileNo",Hibernate.STRING).addScalar("tdpcadreId",Hibernate.LONG).addScalar("constname",Hibernate.STRING)
		        .addScalar("candiDesigId",Hibernate.LONG).addScalar("designation",Hibernate.STRING).addScalar("acid",Hibernate.LONG).addScalar("actype",Hibernate.STRING);
		    
		    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
		    query.setParameter("apptUserId",apptUserId);
		    
		    return query.list();
		    
		  }
		  
		  public List<Object[]> getAllRescheduledCommentsAndrescheduledDates(Long apptUserId){
		    
		    StringBuilder sb = new StringBuilder();
		    sb.append(" select   distinct A.appointment_id as apptId,date(T.action_time) as rescheduledDate,ACM.comment as comment,U.firstname as firstName,T.appointment_action_id as actionId " +
		        "   from     appointment A join appointment_tracking T on A.appointment_id = T.appointment_id " +
		        "            join user U on T.user_id = U.user_id " +
		        "            left join appointment_comment ACM on T.appointment_comment_id = ACM.appointment_comment_id " +
		        "   where    A.is_deleted='N' and A.appointment_user_id = :apptUserId and T.appointment_status_id = :appointmentStatusId " +
		        "   order by A.appointment_id,date(T.action_time) ");
		    Query query = getSession().createSQLQuery(sb.toString())
		        .addScalar("apptId",Hibernate.LONG).addScalar("rescheduledDate",Hibernate.DATE).addScalar("comment",Hibernate.STRING)
		        .addScalar("firstName",Hibernate.STRING).addScalar("actionId",Hibernate.LONG);
		    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
		    query.setParameter("apptUserId",apptUserId);
		    return query.list();
		    
		  }

		  public List<Object[]> getCandiWiseRescheduledAppts(Long apptUserId){
			  
			  StringBuilder sb = new StringBuilder();
			  
			  sb.append(" select   distinct AC.appointment_candidate_id as candiId,AC.name as CandiName,AC.image_url as ImageUrl,AC.mobile_no as mobileNo," +//3
			  		"              acd.appointment_candidate_designation_id as candiDesigId,acd.designation as designation, " +//5
			  		"              act.appointment_candidate_type_id as acid ,act.candidate_type as actype," +//7
			  		"              AC.tdp_cadre_id as tdpcadreId,constituency.name as constname," +//9
			  		"              A.appointment_id as apptId,A.appointment_unique_id as uniqueId,date(A.inserted_time) as createdDate,AST.status as presentStatus" +//13
			  		
			  		"     from     appointment_candidate_relation ACR join appointment_candidate AC on ACR.appointment_candidate_id = AC.appointment_candidate_id " +
			  		"              left join user_address ua on AC.address_id = ua.user_address_id " +
			  		"              left join constituency constituency on ua.constituency_id = constituency.constituency_id  " +
			  		"              join appointment A on A.appointment_id = ACR.appointment_id  " +
			  		"              join appointment_status AST on AST.appointment_status_id = A.appointment_status_id " +
			  		"              left join appointment_candidate_designation acd on AC.designation_id = acd.appointment_candidate_designation_id " +
			  		"              left join appointment_candidate_type act on AC.appointment_candidate_type_id = act.appointment_candidate_type_id  " +
			  		"              join appointment_tracking T on A.appointment_id = T.appointment_id  " +
			  		"" +
			  		"    where     A.is_deleted='N' and A.appointment_user_id =:apptUserId and T.appointment_status_id = :appointmentStatusId  " +
			  		"    order by  AC.appointment_candidate_id;");
			  Query query = getSession().createSQLQuery(sb.toString())
					  .addScalar("candiId",Hibernate.LONG).addScalar("CandiName",Hibernate.STRING).addScalar("ImageUrl",Hibernate.STRING).addScalar("mobileNo",Hibernate.STRING)
			          .addScalar("candiDesigId",Hibernate.LONG).addScalar("designation",Hibernate.STRING).addScalar("acid",Hibernate.LONG).addScalar("actype",Hibernate.STRING)
			          .addScalar("tdpcadreId",Hibernate.LONG).addScalar("constname",Hibernate.STRING).addScalar("apptId",Hibernate.LONG).addScalar("uniqueId",Hibernate.STRING)
			           .addScalar("createdDate",Hibernate.DATE).addScalar("presentStatus",Hibernate.STRING);
			  
			  query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
			  query.setParameter("apptUserId",apptUserId);
			    
			  return query.list();  
		  }
		  
		  public List<Object[]> getMeberWiseRescheduledAppts(Long apptUserId){
			  
		    StringBuilder sb = new StringBuilder();
		    sb.append(" select   distinct AC.appointment_candidate_id as candiId," +
		    		"            A.appointment_id as apptId,T.appointment_action_id as actionId,date(T.action_time) as rescheduledDate,ACM.comment as comment" +
		    		"             " +
		    		"             " +
		            "   from     appointment_candidate_relation ACR join appointment_candidate AC on ACR.appointment_candidate_id = AC.appointment_candidate_id    " +
		            "            join appointment A on A.appointment_id = ACR.appointment_id " +
		            "            join appointment_tracking T on A.appointment_id = T.appointment_id " +
		            "            left join appointment_comment ACM on T.appointment_comment_id = ACM.appointment_comment_id " +
		            "   where    A.is_deleted='N' and A.appointment_user_id = :apptUserId and T.appointment_status_id = :appointmentStatusId " +
		            "   order by A.appointment_id,date(T.action_time)");
		    
		    Query query = getSession().createSQLQuery(sb.toString())
		    	.addScalar("candiId",Hibernate.LONG)
		    	.addScalar("apptId",Hibernate.LONG).addScalar("actionId",Hibernate.LONG).addScalar("rescheduledDate",Hibernate.DATE).addScalar("comment",Hibernate.STRING);
		    
		    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
		    query.setParameter("apptUserId",apptUserId);
		   
		    return query.list();
		  }
		  public List<Object[]> overviewSummaryOfRescheduledCandidates(Long apptUserId){
			  
			  StringBuilder sb = new StringBuilder();
			  
			  sb.append(" select   distinct  AC.appointment_candidate_id as candiId,AC.name as CandiName,AC.image_url as ImageUrl,AC.mobile_no as mobileNo," +//3
			  		    "          acd.appointment_candidate_designation_id as candiDesigId,acd.designation as designation, " +//5
			  		    "          act.appointment_candidate_type_id as acid ,act.candidate_type as actype," +//7
			  		    "          AC.tdp_cadre_id as tdpcadreId,constituency.name as constname," +//9
			  		    "          count(distinct A.appointment_id) as apptsCount,count(T.appointment_action_id) as rescheduledCount" +//11
			  		    
			  		    " from     appointment_candidate_relation ACR join appointment_candidate AC on ACR.appointment_candidate_id = AC.appointment_candidate_id " +
			  		    "          left join user_address ua on AC.address_id = ua.user_address_id " +
			  		    "          left join constituency constituency on ua.constituency_id = constituency.constituency_id" +
			  		    "          join appointment A on A.appointment_id = ACR.appointment_id  " +
			  		    "          join appointment_status AST on AST.appointment_status_id = A.appointment_status_id" +
			  		    "          left join appointment_candidate_designation acd on AC.designation_id = acd.appointment_candidate_designation_id " +
			  		    "          left join appointment_candidate_type act on AC.appointment_candidate_type_id = act.appointment_candidate_type_id " +
			  		    "          join appointment_tracking T on A.appointment_id = T.appointment_id" +
			  		    
			  		    " where     A.is_deleted='N'  and T.appointment_status_id = :appointmentStatusId and  T.appointment_action_id =1  and A.appointment_user_id = :apptUserId " +
			  		    " group by  AC.appointment_candidate_id " +
			  		    " order by  AC.appointment_candidate_id ");
			  
			  Query query = getSession().createSQLQuery(sb.toString())
					  .addScalar("candiId",Hibernate.LONG).addScalar("CandiName",Hibernate.STRING).addScalar("ImageUrl",Hibernate.STRING).addScalar("mobileNo",Hibernate.STRING)
			          .addScalar("candiDesigId",Hibernate.LONG).addScalar("designation",Hibernate.STRING).addScalar("acid",Hibernate.LONG).addScalar("actype",Hibernate.STRING)
			          .addScalar("tdpcadreId",Hibernate.LONG).addScalar("constname",Hibernate.STRING).addScalar("apptsCount",Hibernate.LONG).addScalar("rescheduledCount",Hibernate.LONG);
			  
			  query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
			  query.setParameter("apptUserId",apptUserId); 
			  return query.list();
		  }
		  public List<Object[]> getMeberWiseRescheduledAppts(Long apptUserId,List<Long> appointmentCandidateIds){
			  
			    StringBuilder sb = new StringBuilder();
			    sb.append(" select   distinct AC.appointment_candidate_id as candiId," +//0
			    		"            A.appointment_id as apptId,T.appointment_action_id as actionId,date(T.action_time) as rescheduledDate,ACM.comment as comment," +//4
			    		"            A.appointment_unique_id as uniqueId,AST.status as presentStatus " +//6
			    		"             " +
			            "   from     appointment_candidate_relation ACR join appointment_candidate AC on ACR.appointment_candidate_id = AC.appointment_candidate_id    " +
			            "            join appointment A on A.appointment_id = ACR.appointment_id " +
			            "            join appointment_tracking T on A.appointment_id = T.appointment_id " +
			            "            join appointment_status AST on AST.appointment_status_id = A.appointment_status_id " +
			            "            left join appointment_comment ACM on T.appointment_comment_id = ACM.appointment_comment_id " +
			            "   where    A.is_deleted='N' and A.appointment_user_id = :apptUserId and T.appointment_status_id = :appointmentStatusId ");
			    
			    if(appointmentCandidateIds != null && appointmentCandidateIds.size() > 0){
			    	sb.append(" and AC.appointment_candidate_id in (:appointmentCandidateIds)");
			    }
			    sb.append(" order by A.appointment_id,date(T.action_time) ");
			           
			    
			    Query query = getSession().createSQLQuery(sb.toString())
			    	.addScalar("candiId",Hibernate.LONG)
			    	.addScalar("apptId",Hibernate.LONG).addScalar("actionId",Hibernate.LONG).addScalar("rescheduledDate",Hibernate.DATE).addScalar("comment",Hibernate.STRING)
			    	.addScalar("uniqueId",Hibernate.STRING).addScalar("presentStatus",Hibernate.STRING);
			    
			    query.setParameter("appointmentStatusId",IConstants.APPOINTMENT_STATUS_RESCHEDULED);
			    query.setParameter("apptUserId",apptUserId);
			    if(appointmentCandidateIds != null && appointmentCandidateIds.size() > 0){
			    	 query.setParameterList("appointmentCandidateIds",appointmentCandidateIds);
			    }
			    return query.list();
			  }
}
