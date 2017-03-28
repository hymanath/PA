package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserLocationTrackingDAO;
import com.itgrids.partyanalyst.model.UserLocationTracking;

public class UserLocationTrackingDAO extends GenericDaoHibernate<UserLocationTracking, Long>  implements IUserLocationTrackingDAO {

	public UserLocationTrackingDAO() {
		super(UserLocationTracking.class);		
	}

	public List<UserLocationTracking> getUserLocationByUserIdAndUniqueId(Long userId , String uniqueId)
	{
		Query query = getSession().createQuery(
				"select model from UserLocationTracking model where model.userId =:userId and "
						+"model.uniqueId =:uniqueId");
		
		query.setParameter("userId", userId);
		query.setParameter("uniqueId", uniqueId);
		
		return query.list();
	}
	
	 public List<UserLocationTracking> getUserTrackingDetails(Long userId,Date surveyDate)
	 {
		 StringBuilder str = new StringBuilder();
		 
		 str.append("select model from UserLocationTracking model where " +
		 		"model.userId = :userId and date(model.surveyTime) = :surveyDate order by model.userLocationTrackingId");
		/* str.append(" select model " +
		 		" from Respondent model Left Join model.surveyAnswerInfo.localElectionBody leb Left Join model.surveyAnswerInfo.tehsil t  where model.surveyAnswerInfo.survey.surveyId =:surveyId and model.surveyAnswerInfo.userId =:userId " +
		 		" and date(model.surveyAnswerInfo.surveyTime) =:surveyDate " +
		 		" order by  model.surveyAnswerInfo.surveyTime ");
		 */
		 Query query = getSession().createQuery(str.toString());
		 
		// query.setParameter("surveyId", surveyId);
		 query.setParameter("userId", userId);
		 query.setParameter("surveyDate", surveyDate);
		 return query.list();
	  }
	 
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getLatestUserTrackingDetals(Date date)
	 {
		 return getHibernateTemplate().find("select distinct model.user.userId , model.user.firstName ," +
		 		" model.longitude,model.latitude,max(model.surveyTime) from UserLocationTracking model where " +
		 		" date(model.surveyTime) = ? group by model.user.userId order by model.surveyTime asc ",date);
	 }
	 
	 public List<Object[]> getUserTrackingDetails(Long userId){
		 Query query = getSession().createQuery(" select model.latitude,model.longitude,model.surveyTime " +
		 		" from UserLocationTracking model " +
		 		" where model.userId=:userId ");
		 query.setParameter("userId", userId);
		 return query.list();
	 }
}	
