package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	public List<Object[]> getCandidatesInfo(String queryStr,List<Long> locationIdsList)
	{
		Query query = getSession().createQuery(queryStr);
		
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		
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
	
	public List<Object[]> getTDPCadreAmountDetails(List<Long> districtIds,String type,Date fromDate,Date toDate )
	{
		StringBuilder str = new StringBuilder();
		if(fromDate != null && toDate != null){
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append("select sum(model1.amount), model.constituency.district.districtId");
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append("select sum(model1.amount), model.constituency.constituencyId");	
			str.append(" from CadreSurveyUserAssignDetails model, CadreRegAmountDetails model1 where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model.constituency.district.districtId in(:districtIds) and date(model1.cadreRegAmountFile.date) >=:fromDate and date(model1.cadreRegAmountFile.date) <=:toDate ");
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append("group by model.constituency.district.districtId");
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append("group by model.constituency.constituencyId");
			Query query=getSession().createQuery(str.toString()); 
			query.setParameterList("districtIds", districtIds);
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
			return query.list();
		}else{
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
				str.append("select sum(model1.amount), model.constituency.district.districtId");
				else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
					str.append("select sum(model1.amount), model.constituency.constituencyId");	
				str.append(" from CadreSurveyUserAssignDetails model, CadreRegAmountDetails model1 where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
						" and model.constituency.district.districtId in(:districtIds) ");
				if(type.equalsIgnoreCase(IConstants.DISTRICT))
				str.append("group by model.constituency.district.districtId");
				else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
					str.append("group by model.constituency.constituencyId");
				Query query=getSession().createQuery(str.toString()); 
				query.setParameterList("districtIds", districtIds);
				return query.list();
		}
		
		
	}
	public List<Object[]> getTDPCadreAmountDetails(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId )
	{
		StringBuilder str = new StringBuilder();
		if(fromDate != null && toDate != null){
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append("select sum(model1.amount), model.constituency.district.districtId");
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append("select sum(model1.amount), model.constituency.constituencyId");	
			str.append(" from CadreSurveyUserAssignDetails model, CadreRegAmountDetails model1 where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
					" and model.constituency.district.districtId in(:districtIds) and date(model1.cadreRegAmountFile.date) >=:fromDate and date(model1.cadreRegAmountFile.date) <=:toDate " +
					" and model.constituency.district.state.stateId = :stateId ");
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append("group by model.constituency.district.districtId");
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append("group by model.constituency.constituencyId");
			Query query=getSession().createQuery(str.toString()); 
			query.setParameterList("districtIds", districtIds);
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
			query.setParameter("stateId", stateId);
			return query.list();
		}else{
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
				str.append("select sum(model1.amount), model.constituency.district.districtId");
				else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
					str.append("select sum(model1.amount), model.constituency.constituencyId");	
				str.append(" from CadreSurveyUserAssignDetails model, CadreRegAmountDetails model1 where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
						" and model.constituency.district.districtId in(:districtIds) and and model.constituency.district.state.stateId = :stateId ");
				if(type.equalsIgnoreCase(IConstants.DISTRICT))
				str.append("group by model.constituency.district.districtId");
				else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
					str.append("group by model.constituency.constituencyId");
				Query query=getSession().createQuery(str.toString()); 
				query.setParameterList("districtIds", districtIds);
				query.setParameter("stateId", stateId);
				return query.list();
		}
		
		
	}
	
	public List<Object[]> getUserTrackingDetails(GISVisualizationParameterVO inputVO){

		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		try {
			queryStr.append(" select distinct ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  district.districtId,district.districtName as name ,count(model.cadreSurveyUserAssignDetails) ");
			}else{
				queryStr.append(" constituency.constituencyId,constituency.name as name ,count(model.cadreSurveyUserAssignDetails) ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" CadreSurveyUserAssignDetails model," +
					" Constituency constituency ");
			//if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" left join constituency.district district ");// where model.levelValue = constituency.constituencyId ");
			//}
			//else {
				queryStr.append(" where model.levelValue = constituency.constituencyId ");
			//}
			queryStr.append(" and model.levelId = 4 ");
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and constituency.district.districtId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and constituency.constituencyId = :childLocationTypeId ");
					}
				}else{
						//queryStr.append("  and constituency.constituencyId = :parentLocationTypeId ");
				}
			}
			
			if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" district.districtId ");
			}else{
				queryStr.append(" constituency.constituencyId ");
			}
			
			query = getSession().createQuery(queryStr.toString());
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT) && inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if(!inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
