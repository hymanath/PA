package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertDAO extends GenericDaoHibernate<Alert, Long> implements
		IAlertDAO {
	public AlertDAO() {
		super(Alert.class);
	}
	
	
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> sourceIds,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.alertId),model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus" +
				" from Alert model where model.isDeleted ='N'and model.alertSource.alertSourceId in(:sourceIds)");
		if(startDate != null)
		{
			str.append(" and date(model.createdTime) >=:startDate and date(model.createdTime) <=:endDate " );
		}
		str.append(" group by model.regionScopes.regionScopesId,model.alertStatus.alertStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(startDate!=null){
			query.setDate("startDate", startDate);	
		}
		if(endDate!=null){
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,alertSource.source,alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name, alertCategory.alertCategoryId,alertCategory.category ");
		str.append(" from Alert model " +
				" left join model.alertSeverity alertSeverity" +
				" left join model.alertSource  alertSource " +
				" left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.alertCategory alertCategory ");
		str.append(" where model.isDeleted ='N'");
		if(inputVO.getLevelId() != null && inputVO.getLevelId().longValue() > 0L){
			str.append(" and model.impactLevelId=:levelId");
		
			if(inputVO.getSearchTypeStr() != null && inputVO.getSearchTypeStr().equalsIgnoreCase("totalBlock")){
					if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
						str.append(" and state.stateId in (1) ");
					else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
						str.append(" and state.stateId in (36) ");
					else
						str.append(" and state.stateId in (1,36) ");
			}
			else{
				if(inputVO.getLevelId().longValue() == 2L){
					if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
						str.append(" and state.stateId in (1) ");
					else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
						str.append(" and state.stateId in (36) ");
					else
						str.append(" and state.stateId in (1,36) ");
				}
				else if(inputVO.getLevelId().longValue() == 3L){
					if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
						str.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
					else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
						str.append(" and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
					else
						str.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}
				else if(inputVO.getLevelId().longValue() == 4L){
					if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
						str.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
					else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
						str.append(" and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
					else
						str.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}
			}
		}
		else{
			if(inputVO.getSearchTypeStr() != null && (inputVO.getSearchTypeStr().equalsIgnoreCase("totalBlock") || inputVO.getSearchTypeStr().equalsIgnoreCase("statusBlock") )){
				if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
					str.append(" and state.stateId in (1) ");
				else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
					str.append(" and state.stateId in (36) ");
				else
					str.append(" and state.stateId in (1,36) ");
			}
			else{
				if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
					str.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
				else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
					str.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				else
					str.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}
			
		}
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue()>0L)
			str.append(" and model.alertTypeId =:alertTypeId ");
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and ( date(model.updatedTime) >=:fromDate and date(model.updatedTime) <=:toDate ) ");
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			str.append(" and model.alertStatus.alertStatusId = :statusId");
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue()>0L)
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			query.setParameter("statusId", inputVO.getStatusId());
		if(inputVO.getLevelId() != null && inputVO.getLevelId().longValue() > 0L)
		query.setParameter("levelId", inputVO.getLevelId());
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue()>0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue()>0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		return query.list();
	}
	
	public List<Object[]> getAlertsData(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" model.alertType.alertType,model.alertSource.source,model.alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name");
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" where model.isDeleted ='N' and model.alertId=:alertId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +//2
				" model.alertType.alertType,model.alertSource.source,model.alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +//3-7
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus");//8-9
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");//10-17
		str.append(" ,constituency.constituencyId,constituency.name");//18 -19 
		str.append(" ,state.stateId,state.stateName");//20
		str.append(" ,ward.constituencyId,ward.name, alertCategory.alertCategoryId,alertCategory.category ");//21 - 23
		if(assignedCadreId != null && assignedCadreId > 0)
		{
			str.append(" from AlertAssigned model1,Alert model left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
			str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			str.append(" left join model.userAddress.district district ");
			str.append(" left join model.userAddress.state state ");
			str.append(" left join model.userAddress.ward ward ");
			str.append(" left join model.alertCategory alertCategory ");
			str.append(" where model.isDeleted ='N' and model1.alertId = model.alertId and model1.tdpCadreId =:assignedCadreId");
		}
		else
			
		{
			str.append(" from Alert model left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
			str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			str.append(" left join model.userAddress.district district ");
			str.append(" left join model.userAddress.state state ");
			str.append(" left join model.userAddress.ward ward ");
			str.append(" left join model.alertCategory alertCategory ");
			str.append(" where model.isDeleted ='N' ");	
		}
	/*	if(levelId != null && levelId > 0)
		str.append(" and model.impactLevelId=:levelId");*/
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate");
		/*if(statusId != null && statusId > 0)
			str.append(" and model.alertStatus.alertStatusId = :statusId");*/
		
		
		//Location Filter
		if(inputVO.getStateId() != null && inputVO.getStateId() > 0)
		{
			str.append(" and model.userAddress.state.stateId =:stateId");
		}
		if(inputVO.getDistrictId() != null && inputVO.getDistrictId() > 0)
		{
			str.append(" and model.userAddress.district.districtId =:districtId");
		}
		if(inputVO.getConstituencyId() != null && inputVO.getConstituencyId() > 0)
		{
			str.append(" and model.userAddress.constituency.constituencyId =:constituencyId");
			
		}
		if(inputVO.getLocationType() != null && !inputVO.getLocationType().isEmpty())
		{
			if(inputVO.getLocationType().equalsIgnoreCase("mandal"))
			{
				if(inputVO.getTehsilId() != null && inputVO.getTehsilId() > 0)
				{
					str.append(" and model.userAddress.tehsil.tehsilId =:tehsilId");
				}
				if(inputVO.getVillageId() != null && inputVO.getVillageId() > 0)
				{
					
					str.append(" and model.userAddress.panchayat.panchayatId  =:panchayatId");
				}
			}
			else
			{
					if(inputVO.getTehsilId() != null && inputVO.getTehsilId() > 0)
					{
						str.append(" and model.userAddress.localElectionBody.localElectionBodyId =:tehsilId");
					}
					
					if(inputVO.getVillageId() != null && inputVO.getVillageId() > 0)
					{
						str.append(" and model.userAddress.ward.constituencyId =:panchayatId");
					}
			}		
		}
		
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(assignedCadreId != null && assignedCadreId > 0)
			query.setParameter("assignedCadreId", assignedCadreId);
		if(inputVO.getStateId() != null && inputVO.getStateId() > 0)
		{
			query.setParameter("stateId", inputVO.getStateId());
		}
		if(inputVO.getDistrictId() != null && inputVO.getDistrictId() > 0)
		{
			query.setParameter("districtId", inputVO.getDistrictId());
		}
		if(inputVO.getConstituencyId() != null && inputVO.getConstituencyId() > 0)
		{
			query.setParameter("constituencyId", inputVO.getConstituencyId());
		}
		if(inputVO.getTehsilId() != null && inputVO.getTehsilId() > 0)
		{
			query.setParameter("tehsilId", inputVO.getTehsilId());
		}
		if(inputVO.getVillageId() != null && inputVO.getVillageId() > 0)
		{
			query.setParameter("panchayatId", inputVO.getVillageId());
		}
		/*if(statusId != null && statusId > 0)
			query.setParameter("statusId", statusId);
		if(levelId != null && levelId > 0)
		query.setParameter("levelId", levelId);*/
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.alertStatus.alertStatusId, model.alertStatus.alertStatus, count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " +
				" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.updatedTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
				//queryStr.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				//queryStr.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		queryStr.append(" group by model.alertStatus.alertStatusId order by model.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByStatusThenCategory(Date fromDate, Date toDate, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model.alertStatus.alertStatusId, " +
						" model.alertStatus.alertStatus, " +
						" model.alertCategory.alertCategoryId, " +
						" model.alertCategory.category," +
						" count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " +
				" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and ( date(model.updatedTime) between :fromDate and :toDate)  ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
				//queryStr.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				//queryStr.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		queryStr.append(" group by model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
						" order by model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByImpactLevel(Date fromDate, Date toDate, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.regionScopes.regionScopesId, model.regionScopes.scope, count(distinct model.alertId) " +
				" from Alert model " +
				" left join model.userAddress userAddress " +
				" left join userAddress.state state  " +
				" left join userAddress.district district  " +
				" left join userAddress.constituency constituency  " +
				" left join userAddress.tehsil tehsil  " +
				" left join userAddress.localElectionBody localElectionBody  " +
				" left join userAddress.panchayat panchayat  " +
				" left join userAddress.ward ward  " +
				" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.updatedTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
				//queryStr.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				//queryStr.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}  
		queryStr.append(" group by model.regionScopes.regionScopesId order by model.regionScopes.regionScopesId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		
		return query.list();   
	}
	public List<Object[]> getTotalAlertGroupByImpactLevelThenStatus(Date fromDate, Date toDate, Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model.regionScopes.regionScopesId, " +
						" model.regionScopes.scope, " +
						" model.alertStatus.alertStatusId, " +
						" model.alertStatus.alertStatus," +
						" count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " +
						" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.updatedTime) between :fromDate and :toDate)  ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
				//queryStr.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				//queryStr.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		queryStr.append(" group by model.regionScopes.regionScopesId, model.alertStatus.alertStatusId " +
						" order by model.regionScopes.regionScopesId, model.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();       
	}
	
	public List<Alert> getAlertDetailsOfNewstype(Long alertCategoryType){
		
		Query query = getSession().createQuery(" select model from Alert model " +
				"  where " +
				" model.alertCategoryTypeId = :alertCategoryType " +
				" order by model.updatedTime desc ");
		
		query.setParameter("alertCategoryType", alertCategoryType);
		
		return query.list();
		
	}
	
	public int updateAlertStatusOfNews(Long alertCategoryType,Long alertStatusId){
		Query query = getSession().createQuery(" update Alert model set model.alertStatusId =:alertStatusId,model.updatedTime=:updatedTime where " +
				"  model.alertCategoryTypeId =:alertCategoryType ");
		
		query.setParameter("alertCategoryType", alertCategoryType);
		query.setParameter("alertStatusId", alertStatusId);
		query.setParameter("updatedTime", new DateUtilService().getCurrentDateAndTime());
		
		return query.executeUpdate();
	}
	public List<Object[]> getLocationIdList(Date fromDate, Date toDate, Long stateId, String Location){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.impactLevelId, model.impactLevelValue " +
						" from Alert model where ");
		
		if(fromDate != null && toDate != null)  
			queryStr.append(" date(model.updatedTime) between :fromDate and :toDate ");
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+","+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}
		}
		if(Location.equalsIgnoreCase("state")){
			queryStr.append(" and model.impactLevelId = 2 ");  
		}else if(Location.equalsIgnoreCase("district")){
			queryStr.append(" and model.impactLevelId = 3 ");
		}else if(Location.equalsIgnoreCase("constituency")){
			queryStr.append(" and model.impactLevelId = 4 ");
		}else if(Location.equalsIgnoreCase("village")){
			queryStr.append(" and model.impactLevelId in (6,8) ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();  
	}
	public List<Object[]> getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(Date fromDate, Date toDate, Long stateId, List<Long> locaionIds, String Location){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model.impactLevelValue, " +//0
						" model.alertStatus.alertStatusId, " +//1
						" model.alertStatus.alertStatus, " +//2
						" model.alertCategory.alertCategoryId, " +//3
						" model.alertCategory.category, " +//4
						" count(distinct model.alertId) " +//5
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " +
						" where ");
		if(fromDate != null && toDate != null){
			queryStr.append(" date(model.updatedTime) between :fromDate and :toDate and model.isDeleted = 'N' ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
				//queryStr.append(" and district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId.longValue() == 36L){
				//queryStr.append(" and district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				queryStr.append(" and state.stateId = 36 ");  
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		
		if(Location.equalsIgnoreCase("state")){
			queryStr.append(" and model.impactLevelId = 2 ");  
		}else if(Location.equalsIgnoreCase("district")){
			queryStr.append(" and model.impactLevelId = 3 ");
		}else if(Location.equalsIgnoreCase("constituency")){
			queryStr.append(" and model.impactLevelId = 4 ");
		}else if(Location.equalsIgnoreCase("village")){
			queryStr.append(" and model.impactLevelId in (6,8) ");  
		}
		
		if(locaionIds != null){
			queryStr.append(" and model.impactLevelValue in (:locaionIds) ");  
		}
		queryStr.append(" group by model.impactLevelValue, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
						" order by model.impactLevelValue, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);  
		}
		if(locaionIds != null){  
			query.setParameterList("locaionIds", locaionIds);  
		}
		return query.list();  
	}
}

