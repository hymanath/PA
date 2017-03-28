package com.itgrids.partyanalyst.dao.hibernate;



import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IVerificationCommentsDAO;
import com.itgrids.partyanalyst.model.VerificationComments;

public class VerificationCommentsDAO extends GenericDaoHibernate<VerificationComments, Long> implements IVerificationCommentsDAO {

	public VerificationCommentsDAO(){
		super(VerificationComments.class);
	}
	public List<Object[]> getAlertCommentByAlertId(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 		" model.alertVerificationUserType.alertVerificationUserTypeId," +//0
		 		" model.comments," +//1
		 		" date(model.updatedTime)," +//2
		 		" time(model.updatedTime)," +//3
		 		" model.updatedUser.firstName," +//4
		 		" model.updatedUser.lastName " +//5
		 		" from VerificationComments model " +
		 		" where " +
		 		" model.verificationConversation.alert.alertId=:alertId " +
		 		" and model.isDeleted='N' and model.verificationConversation.alert.isDeleted='N' order by model.updatedTime  ");
		   Query query = getSession().createQuery(queryStr.toString());
		   query.setParameter("alertId", alertId);
		  return query.list();
	}
	public List<Object[]> getAletConversationDtls(Long alertId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select "+
				" vers.verification_conversation_id as conversationId,"+
				" vc.alert_verification_user_type_id as alerVerififationUserTypeId,"+
				" vc.comments as comments,"+
				" date(vc.updated_time) as updateTime,"+
				" time(vc.updated_time) as time,"+
				" u.firstname as firstName,"+
				" u.lastname as lastName,"+
				" d.document_path as documentPath"+ 
				" from verification_comments vc"+ 
				" join verification_conversation vers on vers.verification_conversation_id = vc.verification_conversation_id"+ 
				" join user u on vc.updated_by = u.user_id "+
				" join alert a on a.alert_id = vers.alert_id"+ 
				" left join verification_documents d on vc.verification_conversation_id = d.verification_conversation_id"+
				" where vers.alert_id =:alertId and a.is_deleted = 'N' and vc.is_deleted = 'N' and vers.is_deleted = 'N' "+
				" order by vc.updated_time");
		   Session session = getSession();
	      SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	      sqlQuery.addScalar("conversationId",Hibernate.LONG); 
	      sqlQuery.addScalar("alerVerififationUserTypeId",Hibernate.LONG); 
	      sqlQuery.addScalar("comments",Hibernate.STRING); 
	      sqlQuery.addScalar("updateTime",Hibernate.DATE); 
	      sqlQuery.addScalar("time",Hibernate.STRING);
	      sqlQuery.addScalar("firstName",Hibernate.STRING);
	      sqlQuery.addScalar("lastName",Hibernate.STRING);
	      sqlQuery.addScalar("documentPath",Hibernate.STRING);
	      sqlQuery.setParameter("alertId", alertId);
		  return sqlQuery.list();
	}
	
	 
	
	
}
