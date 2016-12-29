package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IEventSurveyUserDAO;
import com.itgrids.partyanalyst.model.EventSurveyUser;

public class EventSurveyUserDAO extends GenericDaoHibernate<EventSurveyUser, Long> implements IEventSurveyUserDAO{

	public EventSurveyUserDAO() {
		super(EventSurveyUser.class);
		
	}
	
	public List<Object[]> getUserDetailsByUnamePwd(String uname,String pwd)
	{
		
		Query query = getSession().createQuery("select model.eventSurveyUserId,model.firstName,model.lastName from EventSurveyUser model where model.userName = :uname " +
				"and model.passWord = :pwd and model.isEnabled = 'Y' ");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		return query.list();
	}

	public List<EventSurveyUser> checkValidUserOrNot(String uname,String pwd){
		Session session = getSession();
		session.setFlushMode(FlushMode.AUTO);
		Query query = session.createQuery("select model from EventSurveyUser model where model.userName = :uname " +
				"and model.passWord = :pwd and model.isEnabled = 'Y' ");
		query.setParameter("uname", uname);
		query.setParameter("pwd", pwd);
		return query.list();
	}
	
	public Long checkUserBlockedOrNot(Long userId){
		Session session = getSession();
		session.setFlushMode(FlushMode.AUTO);
		Query query = session.createQuery("select count(*) from EventSurveyUser model where model.eventSurveyUserId = :userId " +
				" and model.isEnabled = 'N' ");
		query.setParameter("userId", userId);
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getPeshiAppForGrievance(Date fromDate,Date toDate,String memberShipId){
		StringBuilder sb =  new StringBuilder();
		sb.append("select distinct Complaint_id as complaintId,Subject as subject," +
				" description as description,Raised_Date as raisedDate,updated_at as updated," +
				" Completed_Status as completedStatus,type_of_issue as typeOfIssue," +
				" scan_copy as scanCopy,membership_id as memberShipId," +
				" mobile_no as mobileNo" +
				" from complaint_master " +
				" where type_of_issue not in ('Insurance','Trust Education Support') " +
				" and Subject != '' and Subject is not null and delete_status is null and state_id_cmp in(1,2)" +
				" and Completed_Status is not null" +
				" and membership_id =:memberShipId ");
		if(fromDate != null && toDate != null){
			sb.append(" and (date(Raised_Date) between :fromDate and :toDate ) ");
		}
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("complaintId", Hibernate.LONG)
		.addScalar("subject", Hibernate.STRING)
		.addScalar("description", Hibernate.STRING)
		.addScalar("raisedDate", Hibernate.DATE)
		.addScalar("updated", Hibernate.DATE)
		.addScalar("completedStatus", Hibernate.STRING)
		.addScalar("typeOfIssue", Hibernate.STRING)
		.addScalar("scanCopy", Hibernate.STRING)
		.addScalar("memberShipId", Hibernate.STRING)
		.addScalar("mobileNo", Hibernate.STRING);
		
		/*getSession().createSQLQuery(" select Complaint_id as complaintId,Subject as subject, description as description,"
				+" Raised_Date as raisedDate,updated_at as updated, Completed_Status as completedStatus,"
				+" type_of_issue as typeOfIssue, scan_copy as scanCopy "
				+" from complaint_master  where "
				+" type_of_issue not in ('Insurance','Trust Education Support')  and "
				+" Subject != '' and Subject is not null and delete_status is null and "
				+" state_id_cmp in(1,2) and Completed_Status is not null and "
				+" membership_id ='17264704'  and "
				+" (date(Raised_Date) between '2016-12-21' and '2016-12-22' )").addScalar("complaintId", Hibernate.LONG)
				.addScalar("subject", Hibernate.STRING)
				.addScalar("description", Hibernate.STRING)
				.addScalar("raisedDate", Hibernate.DATE)
				.addScalar("updated", Hibernate.DATE)
				.addScalar("completedStatus", Hibernate.STRING)
				.addScalar("typeOfIssue", Hibernate.STRING)
				.addScalar("scanCopy", Hibernate.STRING).list();*/
		query.setParameter("memberShipId", memberShipId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getFilePathUrlForComplaintIds(List<Long> complaintIds){
		Query query = getSession().createSQLQuery("select csc.complaint_id as complaintId,sc.scanned_copy_path as filePath" +
				" from complaint_scanned_copy csc,scanned_copy sc " +
				" where csc.scanned_copy_id = sc.scanned_copy_id and" +
				" csc.complaint_id in (:complaintIds) ").addScalar("complaintId", Hibernate.LONG).addScalar("filePath", Hibernate.STRING);
		query.setParameterList("complaintIds", complaintIds);
		return query.list();
	}
}
