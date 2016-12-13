package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
				" alertType.alertType,alertSource.source,alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" alertStatus.alertStatusId,alertStatus.alertStatus");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name,");
		str.append(" alertCategory.alertCategoryId,alertCategory.category ");
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
		str.append(" left join model.alertType  alertType ");
		str.append(" left join model.alertStatus  alertStatus ");
		str.append(" where model.isDeleted ='N' ");
		
		if(inputVO.getAlertImpactScopeId() !=null && inputVO.getAlertImpactScopeId()>0l){
			str.append(" and model.impactScopeId=:impactScopeId");
			if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
				str.append(" and state.stateId in (1) ");
			else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
				str.append(" and state.stateId in (36) ");
			else
				str.append(" and state.stateId in (1,36) ");
			
		}else{
			if(inputVO.getLevelId() != null && inputVO.getLevelId().longValue() > 0L){
			
					if(inputVO.getLevelId().longValue() == 2L){
						if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() ==1L)
							str.append(" and state.stateId in (1) ");
						else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() ==36L || inputVO.getLevelValue().longValue() ==2L ))
							str.append(" and state.stateId in (36) ");
						else
							str.append(" and state.stateId in (1,36) ");
					}
					else if(inputVO.getLevelId().longValue() == 3L){
						if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() >0L)
							str.append(" and model.userAddress.district.districtId in ("+inputVO.getLevelValue()+") ");
					}
					else if(inputVO.getLevelId().longValue() == 4L){
						if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() >0L)
							str.append(" and model.userAddress.constituency.constituencyId in ("+inputVO.getLevelValue()+") ");
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
				
			}
		}
		
		
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue()>0L)
			str.append(" and model.alertTypeId =:alertTypeId ");
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and ( date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate ) ");
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			str.append(" and alertStatus.alertStatusId = :statusId");
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue()>0L)
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
		
		Query query = getSession().createQuery(str.toString());
		if(sourceIds != null && sourceIds.size() > 0)
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
		/*if(inputVO.getLevelId() != null && inputVO.getLevelId().longValue() > 0L)
			query.setParameter("levelId", inputVO.getLevelId());*/
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue()>0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue()>0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		if(inputVO.getAlertImpactScopeId() !=null && inputVO.getAlertImpactScopeId()>0l){
			query.setParameter("impactScopeId", inputVO.getAlertImpactScopeId());			
		}
		return query.list();
	}
	
	public List<Object[]> getAlertsData(Long alertId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId," +
				   " model.description, " +
				   " date(model.createdTime)," +//2
				   " alertType.alertType, " +
				   " alertSource.source, " +
				   " alertSeverity.severity, " +
				   " model.regionScopes.regionScopesId, " +
				   " model.regionScopes.scope," +//7
				   " alertStatus.alertStatusId, " +
				   " alertStatus.alertStatus");//9
		str.append(" ,tehsil.tehsilId, " +
				   " tehsil.tehsilName , " +
				   " panc.panchayatId, " +
				   " panc.panchayatName, " +
				   " localElectionBody.localElectionBodyId, " +
				   " localElectionBody.name, " +
				   " district.districtId, " +
				   " district.districtName, " +
				   " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +
				   " constituency.name");//20
		str.append(" ,state.stateId, " +
				  " state.stateName");//22
		str.append(" ,ward.constituencyId, " +
				   " ward.name");//24
		str.append(" ,model.title, " +  
				  " alertImpactScope.impactScope," + //25-26
				  " model.alertCategory.alertCategoryId, " +
				  " model.alertCategory.category," + //27-28
				  " model.imageUrl, " +
				  " model.alertCategoryTypeId ");//29-30
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.alertType alertType ");
		str.append(" left join model.alertSource alertSource ");
		str.append(" left join model.alertSeverity alertSeverity ");
		str.append(" left join model.alertStatus alertStatus" +
					" left join model.alertImpactScope alertImpactScope ");
		str.append(" where model.isDeleted ='N' and model.alertId=:alertId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +//2
				" alertType.alertType,model.alertSource.source,alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +//3-7
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
			str.append(" left join model.alertSeverity alertSeverity");
			str.append(" left join model.alertType alertType");
			str.append(" left join model.alertStatus alertStatus ");
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
			str.append(" left join model.alertSeverity alertSeverity");
			str.append(" left join model.alertType alertType");
			str.append(" left join model.alertStatus alertStatus ");
			str.append(" where model.isDeleted ='N' ");	
		}
		
		if(inputVO.getId() != null && inputVO.getId().longValue()>0L){
			str.append(" and model.alertTypeId =:alertTypeId ");
		}
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and date(model.createdTime) >=:fromDate and date(model.createdTime) <=:toDate");
		if(inputVO.getStatusId() != null && inputVO.getStatusId() > 0)
			str.append(" and model.alertStatus.alertStatusId = :statusId");
		
		if(inputVO.getCategoryId() !=null && inputVO.getCategoryId()>0l){
			str.append(" and model.alertCategoryId = :alertCategoryId");
		}
		
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
		if(inputVO.getId() != null && inputVO.getId().longValue()>0L){
			query.setParameter("alertTypeId", inputVO.getId());
		}
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L)
			query.setParameter("statusId", inputVO.getStatusId());
		if(inputVO.getCategoryId() !=null && inputVO.getCategoryId()>0l){
			query.setParameter("alertCategoryId",inputVO.getCategoryId());
		}
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select alertStatus.alertStatusId, alertStatus.alertStatus, count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");
		queryStr.append(" left join model.alertStatus alertStatus ");
		
		queryStr.append(" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			queryStr.append(" and model.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by model.alertStatus.alertStatusId order by model.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByStatusThenCategory(Date fromDate, Date toDate, Long stateId,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertStatus.alertStatusId, " +
						" alertStatus.alertStatus, " +
						" alertCategory.alertCategoryId, " +
						" alertCategory.category," +
						" count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " );
		queryStr.append(" left join model.alertCategory alertCategory ");
		queryStr.append(" left join model.alertType alertType ");
		queryStr.append(" left join model.alertSource alertSource ");
		queryStr.append(" left join model.alertSeverity alertSeverity ");
		queryStr.append(" left join model.alertStatus alertStatus ");
		queryStr.append(" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and ( date(model.createdTime) between :fromDate and :toDate)  ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			queryStr.append(" and model.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by alertStatus.alertStatusId, alertCategory.alertCategoryId " +
						" order by alertStatus.alertStatusId, alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		
		
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByImpactLevel(Date fromDate, Date toDate, Long stateId,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.alertImpactScope.alertImpactScopeId, model.alertImpactScope.impactScope, count(distinct model.alertId) " +
				" from Alert model " +
				" where model.isDeleted ='N'  ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and model.userAddress.state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and model.userAddress.state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and model.userAddress.state.stateId in (1,36) ");
			}
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			queryStr.append(" and model.alertTypeId = :alertTypeId ");
		}
		
		queryStr.append(" group by model.alertImpactScope.alertImpactScopeId order by model.alertImpactScope.orderNo ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId>0l){
			query.setParameter("alertTypeId", alertTypeId);
		}
		
		
		return query.list();   
	}
	public List<Object[]> getTotalAlertGroupByImpactLevelThenStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model.alertImpactScope.alertImpactScopeId, " +
						" model.alertImpactScope.impactScope, " +
						" alertStatus.alertStatusId, " +
						" alertStatus.alertStatus," +
						" count(distinct model.alertId) " +
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " );
		queryStr.append(" left join model.alertStatus alertStatus ");
		queryStr.append(" where model.isDeleted ='N'  ");
		
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate)  ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and model.userAddress.state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and model.userAddress.state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and model.userAddress.state.stateId in (1,36) ");
			}
		}
		if(alertTypeId !=null && alertTypeId>0l){
			queryStr.append(" and model.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by model.alertImpactScope.alertImpactScopeId, model.alertStatus.alertStatusId " +
						" order by model.alertImpactScope.orderNo, model.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(alertTypeId !=null && alertTypeId>0l){
			query.setParameter("alertTypeId", alertTypeId);
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
	public List<Object[]> getLocationIdList(Date fromDate, Date toDate, Long stateId, String Location,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		if(Location.equalsIgnoreCase("state")){
			queryStr.append(" state.stateId, state.stateName ");  
		}else if(Location.equalsIgnoreCase("district")){
			queryStr.append(" district.districtId, district.districtName ");  
		}else if(Location.equalsIgnoreCase("constituency")){
			queryStr.append(" constituency.constituencyId, constituency.name ");  
		}else if(Location.equalsIgnoreCase("village")){
			queryStr.append(" panchayat.panchayatId, panchayat.panchayatName ");  
		}else if(Location.equalsIgnoreCase("ward")){
			queryStr.append(" ward.wardId, ward.wardName ");  
		}
		queryStr.append(" ");   
		queryStr.append(" from Alert model "+
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
				queryStr.append(" date(model.createdTime) between :fromDate and :toDate and model.isDeleted = 'N' ");
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
				
				if(alertTypeId !=null && alertTypeId>0l){
					queryStr.append(" and model.alertTypeId = :alertTypeId ");
				}
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId>0l){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list();    
	}
	public List<Object[]> getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(Date fromDate, Date toDate, Long stateId, List<Long> locaionIds, String Location,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(Location.equalsIgnoreCase("state")){
			queryStr.append(" state.stateId, " ); //0 
		}else if(Location.equalsIgnoreCase("district")){
			queryStr.append(" district.districtId, ");  //0
		}else if(Location.equalsIgnoreCase("constituency" )){
			queryStr.append(" constituency.constituencyId, ");  //0
		}else if(Location.equalsIgnoreCase("village")){
			queryStr.append(" panchayat.panchayatId, ");  //0  
		}else if(Location.equalsIgnoreCase("ward")){
			queryStr.append(" ward.wardId, ward.wardName ");  
		}
		
		queryStr.append(" alertStatus.alertStatusId, " +//1
						" alertStatus.alertStatus, " +//2
						" alertCategory.alertCategoryId, " +//3
						" alertCategory.category, " +//4
						" count(distinct model.alertId) " +//5
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");

		queryStr.append(" left join model.alertCategory alertCategory ");
		queryStr.append(" left join model.alertStatus alertStatus ");
		queryStr.append(" where ");
		
		if(fromDate != null && toDate != null){  
			queryStr.append(" date(model.createdTime) between :fromDate and :toDate and model.isDeleted = 'N' ");
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
		if(alertTypeId !=null && alertTypeId > 0l){
			queryStr.append(" and model.alertTypeId = :alertTypeId ");
		}
		if(Location.equalsIgnoreCase("state")){
			queryStr.append(" group by state.stateId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
					" order by state.stateId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		}else if(Location.equalsIgnoreCase("district")){
			queryStr.append(" group by district.districtId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
					" order by district.districtId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		}else if(Location.equalsIgnoreCase("constituency" )){
			queryStr.append(" group by constituency.constituencyId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
					" order by constituency.constituencyId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		}else if(Location.equalsIgnoreCase("village")){
			queryStr.append(" group by panchayat.panchayatId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
					" order by panchayat.panchayatId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		}else if(Location.equalsIgnoreCase("ward")){
			queryStr.append(" group by ward.wardId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId " +
					" order by ward.wardId, model.alertStatus.alertStatusId, model.alertCategory.alertCategoryId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);  
		}
		if(alertTypeId !=null && alertTypeId>0l){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list();  
	}
	public List<Object[]> getTotalAlertGroupByLocation(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		queryStr.append(" district.districtId, " +//0
					" district.districtName, ");//1
		
		if(step.equalsIgnoreCase("two")){
			queryStr.append(" model.alertCategory.alertCategoryId," +//2
							" model.alertCategory.category,");//3
		}
		queryStr.append(" count(distinct model.alertId) " +  //4  
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
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) and model.alertType.alertTypeId != 2 ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");     
		}
		
		if(scopeIdList != null && scopeIdList.size() > 0){
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		
		if(step.equalsIgnoreCase("one")){
			queryStr.append(" group by district.districtId order by district.districtId ");
		}else{
			queryStr.append(" group by district.districtId, model.alertCategory.alertCategoryId order by district.districtId, model.alertCategory.alertCategoryId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);
		}
		return query.list();       
	}  
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		queryStr.append(" district.districtId, " +//0
					" district.districtName, ");//1
		
		if(step.equalsIgnoreCase("two")){
			queryStr.append(" model.alertStatus.alertStatusId," +//2
							" model.alertStatus.alertStatus,");//3
		}
		queryStr.append(" count(distinct model.alertId) " +  //4  
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
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) and model.alertType.alertTypeId != 2 ");  
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");     
		}
		
		if(scopeIdList != null && scopeIdList.size() > 0){
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		if(step.equalsIgnoreCase("one")){
			queryStr.append(" group by district.districtId order by district.districtId ");
		}else{
			queryStr.append(" group by district.districtId, model.alertStatus.alertStatusId order by district.districtId, model.alertStatus.alertStatusId ");
		}  
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}    
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);  
		}
		return query.list();   
	}
	
	public List<Object[]> getOverAllAlertDetailsForCoreDashBoard(Date startDate,Date endDate,Long locationLevelId,List<Long> levelValues,
			List<Long> impactScopeIds){
		
		StringBuilder str = new StringBuilder();
		str.append(" select model.alertId," +
				"model.alertCategory.alertCategoryId,model.alertCategory.category,model.alertType.alertTypeId,model.alertType.alertType," +
				" model.alertStatus.alertStatusId,model.alertStatus.alertStatus " +
				" FROM  Alert model" +
				" WHERE model.isDeleted ='N' " );
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(model.createdTime) between :startDate and :endDate  ");
		}
		
		if(locationLevelId !=null && locationLevelId>0l && levelValues !=null && levelValues.size()>0){
			if(locationLevelId ==2l)
				str.append(" and model.userAddress.state.stateId in (:levelValues) ");
			else if(locationLevelId ==3l)
				str.append(" and model.userAddress.district.districtId in (:levelValues) ");
			else if(locationLevelId ==5l)
				str.append(" and model.userAddress.constituency.constituencyId in (:levelValues) ");
			/*else if(locationLevelId ==5l)
				str.append(" and model.userAddress.tehsil.tehsilId in (:levelValues) ");
			else if(locationLevelId ==6l)
				str.append(" and model.userAddress.panchayat.panchayatId in (:levelValues) ");
			else if(locationLevelId ==7l)
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:levelValues) ");
			else if(locationLevelId ==8l)
				str.append(" and model.userAddress.ward.constituencyId in (:levelValues) ");	*/		
		}
		
		if(impactScopeIds !=null && impactScopeIds.size()>0l){
			str.append(" and model.impactScopeId in (:impactScopeIds) ");	
		}
		
		Query query = getSession().createQuery(str.toString());
		
		
		if(locationLevelId !=null && locationLevelId>0l && levelValues !=null && levelValues.size()>0){
			query.setParameterList("levelValues", levelValues);
		}
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();  
	}
	public List<Object[]> getTotalAlertGroupByDist(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, Long userAccessLevelId, List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		queryStr.append(" district.districtId, " +//0
						" district.districtName, ");//1
		
		queryStr.append(" count(distinct model.alertId) " +  //3  
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " + 
						" where model.isDeleted ='N' and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")  ");             
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) ");
			}
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");     
		}
		
		if(scopeIdList != null && scopeIdList.size() > 0){   
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		
		queryStr.append(" group by district.districtId order by district.districtId ");
		
		Query query = getSession().createQuery(queryStr.toString()); 
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}    
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);  
		}  
		return query.list();  
	}
	public List<Object[]> getAlertDtls(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long userAccessLevelId, List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");     
		queryStr.append(" model.alertId, " +//0
						" model.createdTime, " +//1
						" model.updatedTime, " +//2
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" model.title, " +//9
						" constituency.name, " +//10
						" district.districtName");//11       
		queryStr.append(" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");  
		queryStr.append(" left join model.alertCategory alertCategory ");
		queryStr.append(" left join model.alertStatus alertStatus ");
		queryStr.append(" left join model.alertImpactScope alertImpactScope ");  
		queryStr.append(" left join model.alertType alertType ");
		queryStr.append(" where model.isDeleted ='N' and alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}     
		
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" and state.stateId = 1 ");  
			}else if(stateId.longValue() == 36L){
				queryStr.append(" and state.stateId = 36 ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" and state.stateId in (1,36) "); 
			}
		}
		
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			queryStr.append(" and alertType.alertTypeId = (:alertTypeId) ");
		}
		
		if(alertStatusId != null && alertStatusId.longValue() > 0L){
			queryStr.append(" and alertStatus.alertStatusId = (:alertStatusId) ");
		}
		
		if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
			queryStr.append(" and alertCategory.alertCategoryId = (:alertCategoryId) ");
		}
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");       
		}
		Query query = getSession().createQuery(queryStr.toString());   
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(alertStatusId != null && alertStatusId.longValue() > 0L){
			query.setParameter("alertStatusId", alertStatusId);
		}
		if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
			query.setParameter("alertCategoryId", alertCategoryId);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		} 
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertTypeBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertType.alertTypeId,model.alertType.alertType,count(distinct model.alertId) from Alert model where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertType.alertTypeId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertStatusBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus,count(distinct model.alertId) from Alert model where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertStatus.alertStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertCategory.alertCategoryId");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertStatus.alertStatusId," +
		  				  " model.alertStatus.alertStatus," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertCategory.alertCategoryId,model.alertStatus.alertStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);  
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertImpactScope.alertImpactScopeId," +
		  				  " model.alertImpactScope.impactScope," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alertImpactScope.alertImpactScopeId not in ("+IConstants.ALERT_IMPACT_SCOPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertCategory.alertCategoryId,model.alertImpactScope.alertImpactScopeId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertImpactScope.alertImpactScopeId," +
		  				  " model.alertImpactScope.impactScope," +
		  				  " model.alertStatus.alertStatusId," +
		  				  " model.alertStatus.alertStatus," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alertImpactScope.alertImpactScopeId not in ("+IConstants.ALERT_IMPACT_SCOPE_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    queryStr.append(" group by model.alertCategory.alertCategoryId,model.alertImpactScope.alertImpactScopeId,model.alertStatus.alertStatusId ");
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public List<Object[]> getPublicRepresentativeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  				  " where  " +
		  				  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  				  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		 }
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		return query.list();
	}
	public List<Object[]> getPartyCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,TdpCommitteeMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		return query.list();
	}
	public List<Object[]> getProgramCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model,TdpMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' and model1.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		return query.list();
	}
	public List<Object[]> getAllAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model" +
		  				  " where  " +
		  				  " model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByPubRepThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step){
	    StringBuilder queryStr = new StringBuilder();    
	    queryStr.append(" select distinct " +
	    				" model1.publicRepresentativeType.publicRepresentativeTypeId, " +
	    				" model1.publicRepresentativeType.type, ");
	    if(step.equalsIgnoreCase("two")){
	    	queryStr.append(" model.alert.alertStatus.alertStatusId, " +
    						" model.alert.alertStatus.alertStatus, ");
	    }
	    				
	    queryStr.append(" count(distinct model.alert.alertId) " +     
	                	" from " +
	                	" AlertAssigned model, " +
	                	" PublicRepresentative model1, " +
	                	" TdpCadreCandidate model2 " +
	                	" where  " +
	                	" model2.candidate.candidateId=model1.candidate.candidateId " +
	                	" and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
	                	" and model.alert.isDeleted='N' " +
	    				" and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
	    				" and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	      queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	    if(step.equalsIgnoreCase("two")){
	    	queryStr.append(" group by model1.publicRepresentativeType.publicRepresentativeTypeId, model.alert.alertStatus.alertStatusId " +
    						" order by model1.publicRepresentativeType.publicRepresentativeTypeId, model.alert.alertStatus.alertStatusId ");
	    }else{
	    	queryStr.append(" group by model1.publicRepresentativeType.publicRepresentativeTypeId " +
    						" order by model1.publicRepresentativeType.publicRepresentativeTypeId ");
	    }
	    
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
	    	query.setDate("startDate", fromDate);
	    query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    return query.list();
	}
	
	public List<Object[]> getTotalAlertGroupByCandThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step, Long publicRepresentativeTypeId){
	    StringBuilder queryStr = new StringBuilder();      
	    queryStr.append(" select distinct " +
	    				" model.tdpCadre.tdpCadreId, " +
	    				" model.tdpCadre.firstname, ");
	    if(step.equalsIgnoreCase("two")){
	    	queryStr.append(" model.alert.alertStatus.alertStatusId, " +
    						" model.alert.alertStatus.alertStatus, ");
	    }
	    				
	    queryStr.append(" count(distinct model.alert.alertId) " +       
	                	" from " +
	                	" AlertAssigned model, " +
	                	" PublicRepresentative model1, " +
	                	" TdpCadreCandidate model2 " +
	                	" where  " +
	                	" model2.candidate.candidateId=model1.candidate.candidateId " +
	                	" and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
	                	" and model.alert.isDeleted='N' " +
	    				" and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
	    				" and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
	    if(publicRepresentativeTypeId != null){
	    	queryStr.append(" and model1.publicRepresentativeType.publicRepresentativeTypeId = :publicRepresentativeTypeId "); 
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	      queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	    if(step.equalsIgnoreCase("two")){
	    	queryStr.append(" group by model.tdpCadre.tdpCadreId, model.alert.alertStatus.alertStatusId " +
    						" order by model.tdpCadre.tdpCadreId, model.alert.alertStatus.alertStatusId ");
	    }else{
	    	queryStr.append(" group by model.tdpCadre.tdpCadreId " +
    						" order by model.tdpCadre.tdpCadreId ");  
	    }
	    
	    Query query = getSession().createQuery(queryStr.toString());
	    if(publicRepresentativeTypeId != null){
	    	query.setParameter("publicRepresentativeTypeId", publicRepresentativeTypeId);     
	    }
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
	    	query.setDate("startDate", fromDate);
	    	query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    return query.list();
	}   
	public int updateCandidateStatusOfAlert(Long alertId,Long userId){
		
		Query query = getSession().createQuery(" update Alert model set model.alertStatusId =1,model.updatedTime =:updatedTime, " +
				" model.updatedBy =:updatedBy " +
				" where model.alertId=:alertId ");
		
		query.setParameter("alertId", alertId);
		query.setParameter("updatedTime", new DateUtilService().getCurrentDateAndTime());
		query.setParameter("updatedBy", userId);
		
		return query.executeUpdate();
	}
	public List<Object[]> getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpBasicCommiteeIds,String step){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select  model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
		  				  " model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,");
		  if(step.equalsIgnoreCase("two")){
			  queryStr.append(" model.alert.alertStatus.alertStatusId," +
	  				          " model.alert.alertStatus.alertStatus,");
		  }
		  				  
		  queryStr.append(" count(distinct model.alert.alertId) " +
		  				  " from AlertAssigned model,TdpCommitteeMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
		 if(tdpBasicCommiteeIds != null && tdpBasicCommiteeIds.size() > 0){
			 queryStr.append(" and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(:tdpBasicCommiteeIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    if(step.equalsIgnoreCase("two")){
	    	queryStr.append(" group  by model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.alert.alertStatusId ");
		}else{
			queryStr.append(" group  by model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");
		}
	    
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		if(tdpBasicCommiteeIds != null && tdpBasicCommiteeIds.size() > 0){
			query.setParameterList("tdpBasicCommiteeIds", tdpBasicCommiteeIds);
		}
		return query.list();  
	}
	public List<Object[]> getTdpCommitteeRolesByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpCommitteeLevelIds,Long tdpBasicCommitteeId,String step){
	      
	    StringBuilder queryStr = new StringBuilder();
	      queryStr.append(" select  model1.tdpCommitteeRole.tdpRoles.tdpRolesId," +
	                	  " model1.tdpCommitteeRole.tdpRoles.role,");
	      if(step.equalsIgnoreCase("two")){
	    	  queryStr.append(" model.alert.alertStatus.alertStatusId," +
		                	  " model.alert.alertStatus.alertStatus,");  
	      }
	                
	      queryStr.append(" count(distinct model.alert.alertId) " +  
	                " from AlertAssigned model,TdpCommitteeMember model1 " +
	                " where  " +
	                " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
	                " and model.alert.isDeleted='N' and model.isDeleted='N' " +
	                " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
	                " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
	      if(stateId != null && stateId.longValue() > 0l){
	        queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
	      }
	      if(fromDate !=null && toDate !=null){
	        queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
	     }
	     if(impactLevelIds != null && impactLevelIds.size() > 0){
	       queryStr.append(" and model.alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	     }
	     if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size() > 0){
	       queryStr.append(" and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(:tdpCommitteeLevelIds)");
	     }
	     if(tdpBasicCommitteeId != null && tdpBasicCommitteeId.longValue() > 0){
	        queryStr.append(" and model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=:tdpBasicCommitteeId");   
	     }
	      if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.alert.userAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and model.alert.userAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model.alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and model.alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	      if(step.equalsIgnoreCase("two")){
	    	  queryStr.append(" group  by model1.tdpCommitteeRole.tdpRoles.tdpRolesId,model.alert.alertStatusId ");
	      }else{
	    	  queryStr.append(" group  by model1.tdpCommitteeRole.tdpRoles.tdpRolesId ");
	      }
	      
	      Query query = getSession().createQuery(queryStr.toString());
	      if(stateId != null && stateId.longValue() > 0l){
	       query.setParameter("stateId", stateId);
	      }
	      if(fromDate !=null && toDate !=null){
	      query.setDate("startDate", fromDate);
	      query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	      query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	      query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size() > 0){
	      query.setParameterList("tdpCommitteeLevelIds", tdpCommitteeLevelIds);
	    }
	    if(tdpBasicCommitteeId != null && tdpBasicCommitteeId.longValue() > 0){
	       query.setParameter("tdpBasicCommitteeId", tdpBasicCommitteeId);  
	    }
	    return query.list();  
	  }
	public List<Object[]> getAlertDtlsForPubRep(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, Long publicRepresentativeTypeId, Long cadreId, Long statusId){
	    StringBuilder queryStr = new StringBuilder();      
	    queryStr.append(" select distinct ");     
		queryStr.append(" alert.alertId, " +//0
						" alert.createdTime, " +//1
						" alert.updatedTime, " +//2  
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" alert.title, " +//9
						" constituency.name, " +//10
						" district.districtName ");//11         
	   			
	    queryStr.append(" from " +
	                	" AlertAssigned model " +
	                	" left join model.alert alert " +
	                	" left join alert.alertStatus alertStatus " +
	                	" left join alert.alertCategory alertCategory " +
	                	" left join alert.alertImpactScope alertImpactScope " +
	                	" left join alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat " +
						" left join userAddress.ward ward, "+
	                	" PublicRepresentative model1, " +
	                	" TdpCadreCandidate model2 " +
	                	" where  " +
	                	" model2.candidate.candidateId=model1.candidate.candidateId " +
	                	" and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
	                	" and alert.isDeleted='N' and model.isDeleted = 'N' " +
	                	" and model.tdpCadre.tdpCadreId = :cadreId " +
	    				" and alert.alertType.alertTypeId in("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
	    				" and alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
	    if(statusId.longValue() != 0L){
	    	queryStr.append(" and alertStatus.alertStatusId = :statusId ");  
	    }
	    if(publicRepresentativeTypeId != null){
	    	queryStr.append(" and model1.publicRepresentativeType.publicRepresentativeTypeId = :publicRepresentativeTypeId "); 
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){  
	      queryStr.append(" and alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and alert.userAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and alert.userAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and alert.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and alert.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and alert.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	   
	    Query query = getSession().createQuery(queryStr.toString());
	    
	    if(publicRepresentativeTypeId != null){
	    	query.setParameter("publicRepresentativeTypeId", publicRepresentativeTypeId);     
	    }
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
	    	query.setDate("startDate", fromDate);
	    	query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    if(statusId.longValue() != 0L && statusId != null){
	    	query.setParameter("statusId", statusId);
	    }
	    if(cadreId.longValue() != 0L && cadreId != null){
	    	query.setParameter("cadreId", cadreId);
	    }
	    return query.list();  
	}
	
	public List<Object[]> getStateImpactLevelAlertCnt(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,String groupType){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append("select ");
		  if(groupType != null && groupType.equalsIgnoreCase("State")){
			  queryStr.append(" model.userAddress.state.stateId,model.userAddress.state.stateName,");
		  }else if(groupType != null && groupType.equalsIgnoreCase("Category")){
			  queryStr.append(" model.alertCategory.alertCategoryId,model.alertCategory.category,");
		  }else if(groupType != null && groupType.equalsIgnoreCase("Status")){
			  queryStr.append(" model.alertStatus.alertStatusId,model.alertStatus.alertStatus,"); 
		  }
		  queryStr.append(" count(distinct model.alertId)" +
		  				  " from Alert model" +
		  				  " where  " +
		  				  " model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			 queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	      queryStr.append(" group by ");
	     if(groupType != null && groupType.equalsIgnoreCase("state")){
			  queryStr.append(" model.userAddress.state.stateId ");
		  }else if(groupType != null && groupType.equalsIgnoreCase("Category")){
			  queryStr.append(" model.alertCategory.alertCategoryId ");
		  }else if(groupType != null && groupType.equalsIgnoreCase("Status")){
			  queryStr.append(" model.alertStatus.alertStatusId "); 
		  }
	    Query query = getSession().createQuery(queryStr.toString());
	    if(stateId != null && stateId.longValue() > 0l){
	     query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		return query.list();
	}
	public List<Object[]> getAlertDetailsByCadreWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long tdpCadreId,Long statusId,String resultType){
		
		StringBuilder queryStr = new StringBuilder();      
	    queryStr.append(" select distinct ");     
		queryStr.append(" alert.alertId, " +//0
						" alert.createdTime, " +//1
						" alert.updatedTime, " +//2  
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" alert.title, " +//9
						" constituency.name, " +//10
						" district.districtName ");//11         
	   			
	    queryStr.append(" from " +
	                	" AlertAssigned model " +
	                	" left join model.alert alert " +
	                	" left join alert.alertStatus alertStatus " +
	                	" left join alert.alertCategory alertCategory " +
	                	" left join alert.alertImpactScope alertImpactScope " +
	                	" left join alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency" +
						" left join userAddress.tehsil tehsil" +
						" left join userAddress.parliamentConstituency parliamentConstituency  " );
				       if(resultType != null && resultType.equalsIgnoreCase("Program Committee")){
			            queryStr.append(" ,TdpMember model1");	 
			           }
			        	queryStr.append(" where  " +
	                	" alert.isDeleted='N' and model.isDeleted = 'N' " +
	    				" and alert.alertType.alertTypeId in("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
	    				" and alert.alertStatus.alertStatusId not in("+IConstants.ALERT_PENDING_STATUS_IDS+") ");
                	 if(resultType != null && resultType.equalsIgnoreCase("Program Committee")){
	                     queryStr.append(" and model1.isDeleted='N' and model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId ");	 
	                   }
                	
	    if(tdpCadreId != null && tdpCadreId.longValue() > 0){
	    	queryStr.append(" and model.tdpCadre.tdpCadreId =:tdpCadreId");
	    }
	    if(statusId.longValue() != 0L){
	    	queryStr.append(" and alertStatus.alertStatusId = :statusId ");  
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){  
	      queryStr.append(" and alert.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	   
	    Query query = getSession().createQuery(queryStr.toString());
	    
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(fromDate !=null && toDate !=null){
	    	query.setDate("startDate", fromDate);
	    	query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    if(statusId.longValue() != 0L && statusId != null){
	    	query.setParameter("statusId", statusId);
	    }
	    if(tdpCadreId.longValue() != 0L && tdpCadreId != null){
	    	query.setParameter("tdpCadreId", tdpCadreId);
	    }
	    return query.list();  
	}
public List<Object[]> getDistrictAndStateImpactLevelWiseAlertDtls(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long districtId){
		
		StringBuilder queryStr = new StringBuilder();      
	    queryStr.append(" select distinct ");     
		queryStr.append(" model.alertId, " +//0
						" model.createdTime, " +//1
						" model.updatedTime, " +//2  
						" alertStatus.alertStatusId, " +//3  
						" alertStatus.alertStatus, " +//4
						" alertCategory.alertCategoryId, " +//5
						" alertCategory.category, " +//6
						" alertImpactScope.alertImpactScopeId, " +//7
						" alertImpactScope.impactScope, " +//8
						" model.title, " +//9
						" constituency.name, " +//10
						" district.districtName ");//11         
	   			
	    queryStr.append(" from " +
	                	" Alert model " +
	                	" left join model.alertStatus alertStatus " +
	                	" left join model.alertCategory alertCategory " +
	                	" left join model.alertImpactScope alertImpactScope " +
	                	" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district " +
						" left join userAddress.constituency constituency " +
						" left join userAddress.tehsil tehsil " +
						" left join userAddress.parliamentConstituency parliamentConstituency " +
						" where  " +
	                	" model.isDeleted='N' " +
	    				" and model.alertType.alertTypeId in("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")  ");
                	
                	
	    if(districtId != null && districtId.longValue() > 0){
	    	queryStr.append(" and district.districtId=:districtId");
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){  
	      queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	          queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues) ");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	          queryStr.append(" and tehsil.tehsilId in (:userAccessLevelValues)");  
	    }
	   
	    Query query = getSession().createQuery(queryStr.toString());
	    
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(districtId != null && districtId.longValue() > 0){
	    	query.setParameter("districtId", districtId);
	    }
	    if(fromDate !=null && toDate !=null){
	    	query.setDate("startDate", fromDate);
	    	query.setDate("endDate", toDate);
	    }
	    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	    	query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	query.setParameterList("impactLevelIds", impactLevelIds); 
	    }
	    return query.list();  
	}
}

