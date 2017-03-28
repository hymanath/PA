package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserDAO;
import com.itgrids.partyanalyst.model.SurveyUser;
import com.itgrids.partyanalyst.utils.IConstants;

public class SurveyUserDAO extends GenericDaoHibernate<SurveyUser, Long> implements ISurveyUserDAO{

	public SurveyUserDAO() 
	{
		super(SurveyUser.class);
	}
	
	public Long getUserDetails(String userName,String password)
	{
		Query query = getSession().createQuery("select model.surveyUserId from SurveyUser model where model.userName = :userName and model.password = :password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (Long) query.uniqueResult();
	}
	
	public Object getUserDetailsAndUserType(String userName,String password)
	{
		Query query = getSession().createQuery("select model.surveyUserId,model.surveyUserType.surveyUsertypeId from SurveyUser model where model.userName = :userName and model.password = :password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.uniqueResult();
	}
	
	public Object getUserAssignedBooths(long userId)
	{
		Query query = getSession().createQuery("select model.surveyUserId,model.surveyUserType.surveyUsertypeId from SurveyUser model where model.userName = :userName and model.password = :password");
		
		return query.uniqueResult();
	}
	
	
	
	public List<Object[]> getSurveyUsersByUserType(Long userTypeId)
	{
		Query query = getSession().createQuery("select model.surveyUserId,model.userName from SurveyUser model where model.surveyUserType.surveyUsertypeId = :userTypeId and model.activeStatus = 'Y'");
		query.setParameter("userTypeId", userTypeId);
		return query.list();
	}
	
	/*public List<Long> checkUserForVoter(long userId,long voterId)
	{
		
		Query query = getSession().createQuery("select model.surveyUserId,model.userName from SurveyUser model where model.surveyUserType.surveyUsertypeId = :userTypeId and model.activeStatus = 'Y'");
		query.setParameter("userTypeId", userTypeId);
		return query.list();
		return 
	}*/
	
	public List<Object[]> getSurveyUsersByUserTypeForLeaderAssign(Long userTypeId)
	{
		Query query = getSession().createQuery("select model.surveyUserId,model.userName from SurveyUser model " +
				"  where    model.surveyUserType.surveyUsertypeId = :userTypeId and model.activeStatus = 'Y'  and model.surveyUserId not in ( select model1.surveyUser.surveyUserId from SurveyUserRelation model1 where model1.activeStatus = 'Y')");
		query.setParameter("userTypeId", userTypeId);
		return query.list();
	}
	
	public List<Object[]> getUsersDetailsBySurveyUserIds(List<Long> userIds)
	{
		Query query = getSession().createQuery("select SU.surveyUserId,SU.userName,SU.mobileNo from SurveyUser SU where SU.surveyUserId in(:userIds) and SU.activeStatus = 'Y'");
		
		query.setParameterList("userIds", userIds);
		
		return query.list();
		
	}
	
	

}
