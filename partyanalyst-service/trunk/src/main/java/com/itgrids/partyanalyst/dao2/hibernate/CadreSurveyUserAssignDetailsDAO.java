package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

	public List<Long> getConstituencyIdByUserId(Long cadreSurveyUserId){
		StringBuilder str = new StringBuilder(); 
		
		str.append(" select distinct model.constituencyId from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.cadreSurveyUserId = :cadreSurveyUserId "
				+ " and model.isDeleted = 'N' and model.cadreSurveyUser.isEnabled='Y' and model.cadreSurveyUser.isDeleted='N' " );
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return query.list();
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
	
	public List<Object[]> getNewUserTrackingDetails(GISVisualizationParameterVO inputVO){

		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		try {
			queryStr.append(" select distinct ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  constituency.district.districtId,constituency.district.districtName as name ,count(model.cadreSurveyUserAssignDetails) ");
			}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  constituency.district.districtId,constituency.district.districtName as name ,count(model.cadreSurveyUserAssignDetails) ");
			}*/
			else{
				queryStr.append(" constituency.constituencyId,constituency.name as name ,count(model.cadreSurveyUserAssignDetails) ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" CadreSurveyUserAssignDetails model," +
					" Constituency constituency ");
			//if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				//queryStr.append(" left join constituency.district district ");// where model.levelValue = constituency.constituencyId ");
			//}
			//else {
				queryStr.append(" where model.levelValue = constituency.constituencyId ");
			//}
			queryStr.append(" and model.levelId = 4 ");
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					if(inputVO.getParentLocationTypeId().longValue()==36L && inputVO.getParentLocationTypeId().longValue()==2L)
						queryStr.append(" and constituency.district.districtId  between 1 and 10 ");
					else if(inputVO.getParentLocationTypeId().longValue()==1L)
						queryStr.append(" and constituency.district.districtId  between 11 and 23 ");
					else
						queryStr.append(" and constituency.district.state.stateId =1 ");
				}
				else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					//queryStr.append(" and constituency.district.districtId = :parentLocationTypeId ");
					queryStr.append(" and (constituency.district.districtId  between 11 and 23) ");
					if(inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and constituency.constituencyId = :childLocationTypeId ");
					}
				}else{
						//queryStr.append("  and constituency.constituencyId = :parentLocationTypeId ");
						queryStr.append(" and (constituency.district.districtId  between 11 and 23) ");
				}
			}
			queryStr.append(" and model.cadreSurveyUserAssignDetails.cadreSurveyUser.isDeleted='N' " +
					"  and model.cadreSurveyUserAssignDetails.cadreSurveyUser.isEnabled='Y'  and model.cadreSurveyUserAssignDetails.isDeleted='N' ");
			/*if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");*/
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" constituency.district.districtId ");
			}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.district.districtId ");
			}*/
			else{
				queryStr.append(" constituency.constituencyId ");
			}
			
			query = getSession().createQuery(queryStr.toString());
			/*if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());*/
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> getUserTrackingDetails(GISVisualizationParameterVO inputVO){

		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		try {
			queryStr.append(" select distinct ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  constituency.district.districtId,constituency.district.districtName as name ,count(model.cadreSurveyUserAssignDetails) ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  constituency.district.districtId,constituency.district.districtName as name ,count(model.cadreSurveyUserAssignDetails) ");
			}
			else{
				queryStr.append(" constituency.constituencyId,constituency.name as name ,count(model.cadreSurveyUserAssignDetails) ");
			}
			
			queryStr.append(" from ");
			queryStr.append(" CadreSurveyUserAssignDetails model," +
					" Constituency constituency ");
			//if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				//queryStr.append(" left join constituency.district district ");// where model.levelValue = constituency.constituencyId ");
			//}
			//else {
				queryStr.append(" where model.levelValue = constituency.constituencyId ");
			//}
			queryStr.append(" and model.levelId = 4 ");
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					if(inputVO.getParentLocationTypeId().longValue()==36L && inputVO.getParentLocationTypeId().longValue()==2L)
						queryStr.append(" and constituency.district.districtId  between 1 and 10 ");
					else if(inputVO.getParentLocationTypeId().longValue()==1L)
						queryStr.append(" and constituency.district.districtId  between 11 and 23 ");
					else
						queryStr.append(" and constituency.district.state.stateId =1 ");
				}
				else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and constituency.district.districtId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and constituency.constituencyId = :childLocationTypeId ");
					}
				}else{
						queryStr.append("  and constituency.constituencyId = :parentLocationTypeId ");
				}
			}
			queryStr.append(" and model.cadreSurveyUserAssignDetails.cadreSurveyUser.isEnabled='Y'  and model.cadreSurveyUserAssignDetails.isDeleted='N' ");
			/*if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");*/
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" constituency.district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.district.districtId ");
			}
			else{
				queryStr.append(" constituency.constituencyId ");
			}
			
			query = getSession().createQuery(queryStr.toString());
			if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Object[]> getMapPowerLocationWise(Long locationScopeId, String locationType){  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		if(locationScopeId.longValue() == 4l){
			queryStr.append(" CSUAD.constituency.constituencyId,");  
		}else if(locationScopeId.longValue() == 3l){
			queryStr.append(" CSUAD.constituency.district.districtId,");
		}
		
		queryStr.append(" count(distinct CSUAD.cadreSurveyUser.cadreSurveyUserId) from CadreSurveyUserAssignDetails CSUAD " +
						" where CSUAD.isDeleted = 'N' " +
						" and CSUAD.cadreSurveyUser.isEnabled = 'Y' "); 
		queryStr.append(" and CSUAD.constituency.district.districtId between 1 and 23 ");
		/*if(locationType.equalsIgnoreCase("AP")){
			queryStr.append(" and CSUAD.constituency.district.districtId between 11 and 23 ");
		}else if(locationType.equalsIgnoreCase("TS")){
			queryStr.append(" and CSUAD.constituency.district.districtId between 1 and 10 ");
		}*/
		
		if(locationScopeId.longValue() == 4l){   
			queryStr.append(" group by CSUAD.constituency.constituencyId ");
		}else if(locationScopeId.longValue() == 3l){
			queryStr.append(" group by CSUAD.constituency.district.districtId");
		}  
		
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	/*public Long getTabUserTrackingDetails(GISVisualizationParameterVO inputVO){

		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		try {
			queryStr.append(" select distinct ");
			
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" district.districtId,district.districtName,count(model.cadreSurveyUserAssignDetails) ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					queryStr.append("  constituency.constituencyId,constituency.name,count(model.cadreSurveyUserAssignDetails) ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("  booth.tehsil.tehsilId,booth.tehsil.tehsilName,count(model.cadreSurveyUserAssignDetails) ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
					queryStr.append("  booth.panchayat.panchayatId,booth.panchayat.panchayatName,count(model.cadreSurveyUserAssignDetails) ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append("  booth.localBody.localElectionBodyId,booth.localBody.name,count(model.cadreSurveyUserAssignDetails) ");
				}
			
			queryStr.append(" from ");
			queryStr.append(" CadreSurveyUserAssignDetails model " );
					
			
		 			
				queryStr.append(" ,model.constituency constituency ");
			
				queryStr.append(" where  ");
			
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.levelId = 4 and constituency.district.districtId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					queryStr.append("   model.levelId = 4 and model.levelValue = constituency.constituencyId  and constituency.constituencyId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("   model.levelId = 5 and model.levelValue = booth.tehsil.tehsilId  and booth.tehsil.tehsilId = :parentLocationTypeId and booth.tehsil.tehsilId = model.constituency.tehsil.tehsilId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append("   model.levelId = 7 and model.levelValue = booth.localBody.localElectionBodyId  and booth.localBody.localElectionBodyId = :parentLocationTypeId and booth.localBody.localElectionBodyId = model.constituency.localElectionBody.localElectionBodyId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
					queryStr.append("   model.levelId = 6 and model.levelValue = booth.panchayat.panchayatId  and booth.panchayat.panchayatId = :parentLocationTypeId ");
				}
			}
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				queryStr.append(" constituency.constituencyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" booth.tehsil.tehsilId  ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" booth.localBody.localElectionBodyId  ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append("  booth.panchayat.panchayatId ");
			}
			
			query = getSession().createQuery(queryStr.toString());
			if(inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			
			return  (Long)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	public List<Object[]> getCadreSurveyUserDtlsLocationWise(String locationType,List<Long> locationValues,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct " +
		  						" model.cadreSurveyUser.cadreSurveyUserId," +
		  						" model.cadreSurveyUser.userName," +
		  						" model.constituency.constituencyId," +
		  						" model.constituency.name ");
			  if(locationType != null && locationType.equalsIgnoreCase("District") && locationValues != null && locationValues.size() > 0){
				 queryStr.append(" ,model.constituency.district.districtId " +
				  		         " ,model.constituency.district.districtName ");
			  }
		  	  queryStr.append("  from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.isEnabled='Y' " +
		  				      "  and model.constituency.deformDate is null and model.constituency.electionScope.electionScopeId=2 ");
		  if(locationType != null && locationType.equalsIgnoreCase("District") && locationValues != null && locationValues.size() > 0){
			  queryStr.append(" and model.constituency.district.districtId in (:locationValues)");
		  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency") && locationValues != null && locationValues.size() > 0 ){
			  queryStr.append(" and model.constituency.constituencyId in(:locationValues)");
		  }
		  if(fromDate != null && toDate != null){
		    	 queryStr.append(" and date(model.insertedTime) between :fromDate and :toDate");
		   }
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate != null && toDate != null){
		    	 query.setParameter("fromDate", fromDate);
		    	 query.setParameter("toDate", toDate);
		  }
		  if(locationValues != null && locationValues.size() > 0){
			  query.setParameterList("locationValues", locationValues);
		  }
		  return query.list();
	}
	public List<Object[]> getLocationWiseSmartDevicesCount(Long stateId,Long districtId,Long constituencyId,Date startDate,Date endDate){  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		if(districtId != null && districtId.longValue()>0l){
			queryStr.append(" distinct CSUAD.constituency.constituencyId,CSUAD.constituency.name ");
		}else{
			queryStr.append(" distinct CSUAD.constituency.district.districtId,CSUAD.constituency.district.districtName ");
		}
		
		queryStr.append(" ,count(distinct model.imeiNo),sum(model.totalRecords),sum(model.kafkaSync),sum(model.kafkaPending),sum(model.pending),sum(model.sync)," +
				" model.cadreSurveyUserId from CadreSurveyUserAssignDetails CSUAD,CadreTabRecordsStatus model,TabUserInfo model1 " +
						" where CSUAD.isDeleted = 'N' and model.cadreSurveyUserId = CSUAD.cadreSurveyUserId " +
						 " and model.tabUserInfoId = model1.tabUserInfoId and model1.isEnabled = 'Y' " +
						 " and model.cadreSurveyUser.isDeleted = 'N' and model.cadreSurveyUser.isEnabled = 'Y'  " +
						" and CSUAD.cadreSurveyUser.isEnabled = 'Y' "); 
		
		if(constituencyId != null && constituencyId.longValue()>0l){
			queryStr.append(" and CSUAD.constituency.constituencyId = :constituencyId ");
		}
		
		if(districtId != null && districtId.longValue()>0l){
			queryStr.append(" and CSUAD.constituency.district.districtId = :districtId ");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append(" and CSUAD.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append(" and CSUAD.constituency.district.districtId between 1 and 10 ");
			}
		}
		
		if(startDate != null && endDate != null){
			queryStr.append(" and date(model.surveyDate) between :startDate and :endDate");
		 }
		
		if(districtId != null && districtId.longValue()>0l){   
			queryStr.append(" group by CSUAD.constituency.constituencyId, model.cadreSurveyUserId ");
		}else {
			queryStr.append(" group by CSUAD.constituency.district.districtId , model.cadreSurveyUserId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(districtId != null && districtId.longValue()>0l){
			query.setParameter("districtId", districtId);
		}
		
		if(constituencyId != null && constituencyId.longValue()>0l){
			query.setParameter("constituencyId", constituencyId);	
		}
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getActualCountOfCadreSurveyUser(Set<Long> locationIds,Long districtId,Date startDate,Date endDate){
		
		StringBuilder str = new StringBuilder();
		
		if(locationIds !=null && locationIds.size()>0){
			if(districtId != null && districtId.longValue()>0l){
				str.append(" select  model.tdpCadre.userAddress.constituency.constituencyId " );
			}else{
				str.append(" select  model.tdpCadre.userAddress.district.districtId " );
			}
		}
		str.append(" ,count(distinct model.tdpCadre.tdpCadreId) " +
				" from TdpCadreEnrollmentYear model  " +
				" where " +
				" model.tdpCadre.isDeleted ='N' " +
				" and model.isDeleted = 'N' " +
				" and model.tdpCadre.enrollmentYear = :enrollmentYear " +
				" and model.enrollmentYearId = :enrollmentYearId   ");					
		
		if(startDate != null && endDate != null){
			str.append(" and model.tdpCadre.surveyTime between :startDate and :endDate");
		 }
		
		if(locationIds !=null && locationIds.size()>0){
			if(districtId != null && districtId.longValue()>0l){
				str.append( " and model.tdpCadre.userAddress.constituency.constituencyId in (:locationIds) group by model.tdpCadre.userAddress.constituency.constituencyId " );
			}else{
				str.append( " and  model.tdpCadre.userAddress.district.districtId in (:locationIds) group by model.tdpCadre.userAddress.district.districtId " );
			}
		}
		
		
		Query query = getSession().createQuery(str.toString());
		
		
		//CADRE_ENROLLMENT_NUMBER
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		query.setParameter("enrollmentYearId", 4l);
		if(locationIds !=null && locationIds.size()>0){
			query.setParameterList("locationIds", locationIds);
		}
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
	
public List<Object[]> getLocationWiseCadreSurveyUserIds(Long districtId,Long stateId,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		if(districtId != null && districtId.longValue()>0l){
			queryStr.append(" distinct CSUAD.constituency.constituencyId,CSUAD.constituency.name ");
		}else{
			queryStr.append(" distinct CSUAD.constituency.district.districtId,CSUAD.constituency.district.districtName ");
		}
		
		queryStr.append(" ,distinct model.cadreSurveyUserId " +
				" from CadreSurveyUserAssignDetails CSUAD " +
						" where CSUAD.isDeleted = 'N' " +
						" and CSUAD.cadreSurveyUser.isEnabled = 'Y' "); 
		
		/*if(constituencyId != null && constituencyId.longValue()>0l){
			queryStr.append(" and CSUAD.constituency.constituencyId = :constituencyId ");
		}*/
		
		if(districtId != null && districtId.longValue()>0l){
			queryStr.append(" and CSUAD.constituency.district.districtId = :districtId ");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append(" and CSUAD.constituency.district.districtId between 11 and 23 ");
			}else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append(" and CSUAD.constituency.district.districtId between 1 and 10 ");
			}
		}
		
		if(startDate != null && endDate != null){
			queryStr.append(" and date(CSUAD.insertedTime) between :startDate and :endDate");
		 }
		
		if(districtId != null && districtId.longValue()>0l){   
			queryStr.append(" group by CSUAD.constituency.constituencyId ");
		}else {
			queryStr.append(" group by CSUAD.constituency.district.districtId");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(districtId != null && districtId.longValue()>0l){
			query.setParameter("districtId", districtId);
		}
		
		/*if(constituencyId != null && constituencyId.longValue()>0l){
			query.setParameter("constituencyId", constituencyId);	
		}*/
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
		
	}
	
	public List<Long> getAssignedConstiteuncyListByUserId(Long cadreSurveyUserId){
		Query query = getSession().createQuery("select distinct model.constituencyId from CadreSurveyUserAssignDetails model" +
				"  where model.cadreSurveyUserId =:cadreSurveyUserId  and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted ='N' and " +
				" model.cadreSurveyUser.isEnabled='Y' and model.cadreSurveyUser.isExcluded ='N' ");		
		query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		return query.list();
	}
	
}
