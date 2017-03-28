package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;

public class CadreSurveyUserDAO extends GenericDaoHibernate<CadreSurveyUser, Long> implements ICadreSurveyUserDAO{

	public CadreSurveyUserDAO() {
		super(CadreSurveyUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserByUserNameAndPassword(String userName , String password)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId from CadreSurveyUser model where model.userName = :userName and model.password = :password and model.isDeleted = 'N' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
	
	public List<Object[]> getCadreSurveyUsersList(List<Long> assignedCadreSurveyUsers)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId, model.userName from CadreSurveyUser model where " +
				" model.isDeleted = 'N' and model.cadreSurveyUserId not in (:assignedCadreSurveyUsers) order by model.userName ");
		
		query.setParameterList("assignedCadreSurveyUsers", assignedCadreSurveyUsers);
		
		return query.list();
		
	}
	
	public List<Object[]> getUserMobileNos(Set<Long> ids){
		//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
		Query query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId,model.constituency.name,model.constituency.district.districtId," +
				" model.constituency.district.districtName from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.cadreSurveyUserId  in (:ids) ");
		
		query.setParameterList("ids", ids);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllUserMobileNos(Long locationType,List<Long> locationIds)
	{
		Query query = null;
		
		if(locationType.longValue() == 1L)
		{
			if(locationIds.contains(1l) && locationIds.contains(2l) )
			 {
				 query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
							"model.constituency.name,model.constituency.district.districtId," +
							" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model ");
			}
			else if(locationIds.contains(2l))
			{
				query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
						"model.constituency.name,model.constituency.district.districtId," +
						" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model where " +
						" model.constituency.district.districtId < 11 and model.constituency.state.stateId = 1 ");
			}
			else if(locationIds.contains(1l))
			{
				 query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
							"model.constituency.name,model.constituency.district.districtId," +
							" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model where " +
							" model.constituency.district.districtId > 10 and model.constituency.state.stateId = 1 ");
			}			
		}
		else if(locationType.longValue() == 2L)
		{
			 query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
						"model.constituency.name,model.constituency.district.districtId," +
						" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model where " +
						" model.constituency.district.districtId in (:locationIds) and model.constituency.state.stateId = 1 ");
			 
			 query.setParameterList("locationIds", locationIds);
			
		}
		
		else if(locationType.longValue() == 3L || locationType.longValue() == 4L)
		{
			 query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
						"model.constituency.name,model.constituency.district.districtId," +
						" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model where " +
						" model.constituency.constituencyId in (:locationIds) and model.constituency.state.stateId = 1 ");
			 
			 query.setParameterList("locationIds", locationIds);
			
		}
		else if(locationType.longValue() == 0L)
		{
			 query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId," +
						"model.constituency.name,model.constituency.district.districtId," +
						" model.constituency.district.districtName,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.tabNo from CadreSurveyUserAssignDetails model ");
		}
		return query.list();
		
	}
	
	
	public Long  getTotalCadreSurveyDetails()
	{
		Query query = getSession().createQuery("select count(model.cadreSurveyUserId) from CadreSurveyUser model where " +
				"  model.isDeleted ='N'  ");		
		return (Long) query.uniqueResult();
	}
	
	public List<CadreSurveyUser> getCadreSurveyUserByUsername(String username)
	{
		Query query = getSession().createQuery("select model from CadreSurveyUser model where model.userName = :username and model.isDeleted='N' and  model.isExcluded='N' " +
				" and model.isEnabled ='Y' ");
		query.setParameter("username", username);
		return  query.list();
	}
	
	
	public List<CadreSurveyUser> getByUserNameAndPassword(String userName , String password)
	{
		Query query = getSession().createQuery("select model from CadreSurveyUser model where model.userName = :userName and model.password = :password and model.isDeleted = 'N' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
	
	
	public List<Object[]> getCadreSurveyUserDetails(List<Long> ids){
		
		Query query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.cadreSurveyUser.mobileNo,model.constituency.constituencyId,model.constituency.name,model.constituency.district.districtId," +
				" model.constituency.district.districtName from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.cadreSurveyUserId  in (:ids) ");
		
		query.setParameterList("ids", ids);
		
		return query.list();
		
	}
	
	public List<Long> getCadreSurveyUserDetailsByType(){
		
		Query query = getSession().createQuery("select model.cadreSurveyUserId from CadreSurveyUser model where model.isDeleted = 'N' and model.userType = 'GHMC'");

		return query.list();
		
	}
	
	public List<Object[]> getCadreSurveyUserList(List<Long> assignedCadreSurveyUsers)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId, model.userName from CadreSurveyUser model where " +
				" model.isDeleted = 'N' and model.cadreSurveyUserId in (:assignedCadreSurveyUsers) order by model.userName ");
		
		query.setParameterList("assignedCadreSurveyUsers", assignedCadreSurveyUsers);
		
		return query.list();
		
	}
	public Object[] getCadreSurveyUserBasicDetails(Long cadreSurveyUserId){
		Query query = getSession().createQuery(" select model.cadreSurveyUserId,model.userName,model.mobileNo,model.name from CadreSurveyUser model " +
											 "   where model.isDeleted='N' and model.cadreSurveyUserId=:cadreSurveyUserId ");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return (Object[])query.uniqueResult();
	}
	
}
