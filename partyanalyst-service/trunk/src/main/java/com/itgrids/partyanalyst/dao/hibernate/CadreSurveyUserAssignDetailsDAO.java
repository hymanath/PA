package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public class CadreSurveyUserAssignDetailsDAO extends GenericDaoHibernate<CadreSurveyUserAssignDetails, Long> implements ICadreSurveyUserAssignDetailsDAO{

	public CadreSurveyUserAssignDetailsDAO() {
		super(CadreSurveyUserAssignDetails.class);
	}

	
	public List<CadreSurveyUserAssignDetails> getCadreAssinedDetails(Long cadreSurveyUserId)
	{
		Query query = getSession().createQuery("select model from CadreSurveyUserAssignDetails model where model.cadreSurveyUserId = :cadreSurveyUserId and model.isDeleted = 'N'");
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return query.list();
	}
	
	public List<Long> getCadreSurveyAssignUsersList()
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId from CadreSurveyUserAssignDetails model where " +
				" model.isDeleted = 'N' ");
		
		return query.list();
		
	}

	
	public List<Object[]> getCadreSurveyAssignUsersListByConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserAssignDetails, model.cadreSurveyUser.userName from CadreSurveyUserAssignDetails model where " +
				" model.isDeleted = 'N' and model.constituencyId = :constituencyId ");
	
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getCadreSurveyAssignConstituencyList()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId, model.constituency.name from CadreSurveyUserAssignDetails model where " +
				" model.isDeleted = 'N' order by  model.constituency.name ");
		
		return query.list();
		
	}
	
	public List<Long> checkIsAlreadyAssigned(Long cadreSurveyUserId, Long levelId, Long levelValue, Long constituencyId)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserAssignDetails from CadreSurveyUserAssignDetails model where " +
				" model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId and model.levelId = :levelId and model.levelValue = :levelValue and model.constituencyId = :constituencyId " +
				" and model.isDeleted = 'N'");
	
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	
	
	public List<Long> isTabAssignedAlready(String tabNo)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserAssignDetails from CadreSurveyUserAssignDetails model where " +
				" model.tabNo like '%"+tabNo+"%'  and model.isDeleted = 'N'");
	
		return query.list();
	}
	
	public List<Object[]> getCandidatesInfo(String queryStr)
	{
		Query query = getSession().createQuery(queryStr);
		return query.list();
	}
	
	public List<Object[]> getTabNos(List<Long> cadreSurveyUserIds)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId,model.tabNo from CadreSurveyUserAssignDetails model where model.cadreSurveyUserId in (:cadreSurveyUserIds) and model.isDeleted = 'N'");
		query.setParameterList("cadreSurveyUserIds", cadreSurveyUserIds);
		return query.list();
	}
	public List<Object[]> getCadreSurveyUsers(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId,model.cadreSurveyUser.userName from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.isDeleted ='N' and model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getUsersByConstituencyAndUserId(Long constituencyId,Long userId)
	{
		Query query = getSession().createQuery("select model1.cadreSurveyUserAssigneeId,model1.name,model1.mobileNo,date(model1.fromDate),date(model1.toDate) from CadreSurveyUserAssignDetails model,CadreSurveyUserAssignee model1 where model.cadreSurveyUser.isDeleted ='N' and model.constituencyId = :constituencyId and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId  and  model.cadreSurveyUser.cadreSurveyUserId = :userId" +
				" order by model1.cadreSurveyUserAssigneeId desc");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getUserConstituencyDetails(List<Long> userIds)
	{
		Query query = getSession().createQuery("select  model.cadreSurveyUserId , model.constituency.name from CadreSurveyUserAssignDetails model where model.cadreSurveyUserId in (:userIds)");
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	public List<Long> getCadreSurveyUserIdsByLocation(String location,Long locationId,String queryString)
	{
		/*select csuas.cadre_survey_user_id
		from cadre_survey_user_assign_details csuas
		where constituency_id=232  and is_deleted='N';#1672,1673,1674,1675,1676*/
		if(queryString == null)
		{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select distinct model.cadreSurveyUserId  from CadreSurveyUserAssignDetails model ");
			if(location.equalsIgnoreCase("constituency"))
				queryStr.append(" where model.constituencyId=:locationId and model.isDeleted='N' ");
			else if(location.equalsIgnoreCase("district"))
				queryStr.append(" where model.constituency.district.districtId=:locationId and model.isDeleted='N' ");
			else if(location.equalsIgnoreCase("state"))
				queryStr.append(" where model.constituency.state.stateId=:locationId and model.isDeleted='N' ");
			Query query = getSession().createQuery(queryStr.toString());
		    
			query.setParameter("locationId", locationId);
		    return query.list();	
		}
		else
		{
			Query query = getSession().createQuery(queryString +" and model2.delimitationConstituency.constituency.constituencyId = :locationId");
			query.setParameter("locationId", locationId);
			
		    return query.list();
		}
	
	}
	public List<Object[]> getUsersDetails(List<Long> cadreSurveyUserIds)
	{
		Query query=getSession().createQuery("select model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.cadreSurveyUser.mobileNo,model.tabNo,model.constituency.name" +
				" from CadreSurveyUserAssignDetails model " +
				" where cadreSurveyUser.cadreSurveyUserId in(:cadreSurveyUserIds) and " +
				" model.isDeleted='N' " +
				" order by model.cadreSurveyUser.userName");
		query.setParameterList("cadreSurveyUserIds", cadreSurveyUserIds);
		 return query.list();	
	}
}
