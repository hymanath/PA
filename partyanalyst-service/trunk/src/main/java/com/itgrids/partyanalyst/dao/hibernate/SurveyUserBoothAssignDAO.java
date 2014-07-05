package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;

public class SurveyUserBoothAssignDAO extends GenericDaoHibernate<SurveyUserBoothAssign, Long>  implements ISurveyUserBoothAssignDAO{

	public SurveyUserBoothAssignDAO()
	{
		super(SurveyUserBoothAssign.class);
	}
	
   public List<?> getBoothsForUser(long userId)
   {
	   Query  query=  getSession().createQuery("select  sb.booth.boothId,sb.booth.partNo,sb.constituency.constituencyId ,sb.remainingBooth " +
	   		" from SurveyUserBoothAssign sb where sb.surveyUser.surveyUserId =:userId and sb.isDelete='N' group by sb.booth.boothId");
	   
	   query.setParameter("userId",userId);
	   
	   return query.list();
   }
   
   
   
   //get voterdata to display for a user
   public List<Object[]> getVoterDataForUser(long userId)
   {
	   Query  query =  getSession().createQuery("select sb.voter.voterId,sb.mobileNumber,sb.isCadre,sb.isInfluencingPeople,sb.caste.casteStateId,sb.hamlet.hamletId,sb.localArea,sb.hamletName from SurveyDetailsInfo sb where sb.surveyUser.surveyUserId =:userId group by sb.surveyDetailsInfoId ");
	   
	  query.setParameter("userId",userId);
	   
	   return query.list();
   }
   
	
	public List<Object[]> getAllTheAssignedBoothsByConstituencyIdAndUserId(Long constituencyId,Long userId)
	{
		Query query = getSession().createQuery("select SUBA.surveyUserBoothAssignId , SUBA.booth.boothId from  SurveyUserBoothAssign SUBA where " +
				"SUBA.surveyUser.surveyUserId = :userId and SUBA.booth.constituency.constituencyId = :constituencyId and SUBA.isDelete = 'N'");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		
		return query.list();
		
	}

	
	public List<Long> getAssignedBoothsForUser(Long surveyUserId)
	{
		Query query = getSession().createQuery("select model.booth.boothId from SurveyUserBoothAssign model where model.surveyUser.surveyUserId = :surveyUserId");
		query.setParameter("surveyUserId", surveyUserId)	;
		return query.list();
	}
	
	public List<Long> getBoothIdsBySurveyUser(Long surveyUserId)
	{
		
		Query query = getSession().createQuery("select distinct model.booth.boothId from SurveyUserBoothAssign model where model.surveyUser.surveyUserId = :surveyUserId");
		query.setParameter("surveyUserId", surveyUserId);
		return query.list();
	}

}
