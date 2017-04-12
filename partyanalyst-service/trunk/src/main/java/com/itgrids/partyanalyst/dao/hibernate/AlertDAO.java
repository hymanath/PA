package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.object.SqlQuery;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertInputsVO;
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
		str.append("select " +
				" model.alertId, " +//0
				" model.description, " +//1
				" date(model.createdTime)," +//2
				" alertType.alertType, " +//3
				" alertSource.source, " +//4
				" alertSeverity.severity, " +//5
				" model.regionScopes.regionScopesId, " +//6
				" model.regionScopes.scope," +//7
				" model.alertStatus.alertStatusId, " +//8
				" model.alertStatus.alertStatus");//9
		str.append(" ,tehsil.tehsilId, " +//10
				  " tehsil.tehsilName , " +//11
				  " panc.panchayatId, " +//12
				  " panc.panchayatName," +//13
				  " localElectionBody.localElectionBodyId," +//14
				  " localElectionBody.name, " +//15
				  " district.districtId, " +//16
				  " district.districtName, " +//17
				  " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +//19
				   " constituency.name");//20
		str.append(" ,state.stateId," +//21
				   " state.stateName");//22
		str.append(" ,ward.constituencyId," +//23
				   " ward.name,");//24
		str.append(" alertCategory.alertCategoryId, " +//25
				   " alertCategory.category, " +//26
				   " editionType.editionTypeId, " +//27
				   " editionType.editionType, " +//28
				   " edition.editionId, " +//29
				   " edition.editionAlias, " +//30
				   " tvNewsChannel.tvNewsChannelId, " +//31
				   " tvNewsChannel.channelName, " +//32
				   " model.title ");//33 
		str.append(" from Alert model " +
				" 	 left join model.editionType editionType " +
        		"  	 left join model.edition edition " +
        		" 	 left join model.tvNewsChannel tvNewsChannel "+
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
		str.append(" left join model.alertType  alertType,AlertDepartmentStatus model1 ");
		str.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
				  "  and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
		str.append(" and model.isDeleted ='N' ");
		
		
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
			str.append(" and model.alertStatus.alertStatusId = :statusId");
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
		str.append(",govtDepartment.departmentName,alertStatus.color ");//31-32
		
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
					" left join model.alertImpactScope alertImpactScope " +
					" left join model.govtDepartment govtDepartment ");
		str.append(" where model.isDeleted ='N' and model.alertId=:alertId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	//hiii 
	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId,Date fromDate2,Date toDate2,Long involvedCadreId,Long impactId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.alertId, " +
				   " model.description, " +
				   " date(model.createdTime)," +//2
				   " alertType.alertType, " +
				   " alertSource.source, " +//4
				   " alertSeverity.severity, " +
				   " model.regionScopes.regionScopesId, " +
				   " model.regionScopes.scope, "); //7
		  str.append(" model.alertStatus.alertStatusId, " +//8
					 " model.alertStatus.alertStatus, ");//9
		  str.append(" tehsil.tehsilId, " + 
				   " tehsil.tehsilName , " +
				   " panc.panchayatId, " +
				   " panc.panchayatName," +
				   " localElectionBody.localElectionBodyId," +
				   " localElectionBody.name, " +
				   " district.districtId, " +
				   " district.districtName, " +
				   " electionType.electionType, ");//10-17
		str.append(" constituency.constituencyId, " +
				   " constituency.name, ");//18 -19 
		str.append(" state.stateId, " +
				   " state.stateName, ");//20
		str.append(" ward.constituencyId, " +
				   " ward.name, " +
				   " alertCategory.alertCategoryId, " +//25
				   " alertCategory.category, " +//26
				   " editionType.editionTypeId, " +//27
				   " editionType.editionType, " +//28
				   " edition.editionId, " +//29
				   " edition.editionAlias, " +//30
				   " tvNewsChannel.tvNewsChannelId, " +//31
				   " tvNewsChannel.channelName, " +//32
				   " model.title ");//33
		
		if(inputVO.getTask().equalsIgnoreCase("verification")){
			str.append(" ,verificationStatus.actionTypeStatus.actionTypeStatusId, " +//34
					   " verificationStatus.actionTypeStatus.status ");//35
		}
		if((assignedCadreId != null && assignedCadreId > 0L) || (involvedCadreId != null && involvedCadreId > 0L))
		{
			if(inputVO.getTask().equalsIgnoreCase("verification")){//yet to implement
				str.append(" from VerificationStatus verificationStatus, AlertAssigned alertAssigned " +
						   " left join verificationStatus.alert  model ");
			}else{
				if(!(assignedCadreId != null && assignedCadreId > 0L)){
					str.append(" from AlertCandidate model1 " +
							   " left join model1.alert model ");
				}else if(!(involvedCadreId != null && involvedCadreId > 0L)){
					str.append(" from AlertAssigned model1 " +
							   " left join model1.alert model ");
				}else{
					str.append(" from AlertAssigned model1 " +
							   " left join model1.alert model ");
				}
				
			}
			str.append(" left join model.editionType editionType " +
	        		"  	 left join model.edition edition " +
	        		" 	 left join model.tvNewsChannel tvNewsChannel " +
	        		"    left join model.alertSource alertSource "+
					"    left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
			str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			str.append(" left join model.userAddress.district district ");
			str.append(" left join model.userAddress.state state ");
			str.append(" left join model.userAddress.ward ward ");

			str.append(" left join model.alertCategory alertCategory ");
			str.append(" left join model.alertSeverity alertSeverity");
			str.append(" left join model.alertType alertType,AlertDepartmentStatus model2 ");
			if(assignedCadreId != null && assignedCadreId > 0L && involvedCadreId != null && involvedCadreId > 0L){
				str.append(" ,AlertCandidate model3 ");
			}
			str.append(" where model2.alertType.alertTypeId = model.alertType.alertTypeId " +
					   " and model2.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
			if(inputVO.getTask().equalsIgnoreCase("verification")){//yet to implement
				str.append(" and model.isDeleted ='N' and alertAssigned.isDeleted='N' and verificationStatus.alert.alertId = alertAssigned.alert.alertId and alertAssigned.tdpCadre.tdpCadreId =:assignedCadreId ");
			}else{
				if(!(assignedCadreId != null && assignedCadreId > 0L)){
					str.append(" and model.isDeleted ='N' and model1.tdpCadre.tdpCadreId =:involvedCadreId ");
					if(impactId != null && impactId.longValue() > 0L){
						str.append(" and model1.alertImpactId =:impactId ");
					}
				}else if(!(involvedCadreId != null && involvedCadreId > 0L)){
					str.append(" and model.isDeleted ='N' and model1.isDeleted='N'  and model1.tdpCadre.tdpCadreId =:assignedCadreId ");
				}else{
					str.append(" and model.isDeleted ='N' and model1.isDeleted='N'  and " +
							   " model1.alert.alertId = model3.alert.alertId and model1.tdpCadre.tdpCadreId =:assignedCadreId and " +
							   " model3.tdpCadre.tdpCadreId =:involvedCadreId ");
					if(impactId != null && impactId.longValue() > 0L){
						str.append(" and model3.alertImpactId = :impactId ");
					}
				}  
				
			}
			
		}
		else			
		{
			if(inputVO.getTask().equalsIgnoreCase("verification")){
				str.append(" from VerificationStatus verificationStatus " +
						   " left join verificationStatus.alert model");
			}else{
				str.append(" from Alert model ");
			}
			str.append(" left join model.editionType editionType " +
	        		   " left join model.edition edition " +
	        		   " left join model.tvNewsChannel tvNewsChannel "+
	        		   " left join model.alertSource alertSource "+
					   " left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
			str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			str.append(" left join model.userAddress.district district ");
			str.append(" left join model.userAddress.state state ");
			str.append(" left join model.userAddress.ward ward ");
			str.append(" left join model.alertCategory alertCategory ");
			str.append(" left join model.alertSeverity alertSeverity");
			str.append(" left join model.alertType alertType,AlertDepartmentStatus model2 ");
			str.append(" where model2.alertType.alertTypeId = model.alertType.alertTypeId " +
					  "  and model2.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
			str.append(" and model.isDeleted ='N' ");
			
				
		}
		
		if(inputVO.getId() != null && inputVO.getId().longValue()>0L){
			str.append(" and model.alertTypeId =:alertTypeId ");
		}
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null && toDate != null){ 
			str.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		
		if(inputVO.getTask().equalsIgnoreCase("verification")){
			if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
				str.append(" and verificationStatus.actionTypeStatus.actionTypeStatusId = :actionTypeStatusId ");
			if(fromDate2 != null && toDate2 != null){ 
				str.append(" and (date(verificationStatus.updatedTime) between :fromDate2 and :toDate2) ");
			}
			str.append(" and verificationStatus.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+")  ");
			str.append("and verificationStatus.isDeleted='N' ");
		}
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L){
			str.append(" and model.alertStatus.alertStatusId = :alertStatusId ");
		}
		
		
		if(inputVO.getCategoryId() !=null && inputVO.getCategoryId()>0l){
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
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
		
		if(inputVO.getId() != null && inputVO.getId().longValue()>0L){
			query.setParameter("alertTypeId", inputVO.getId());
		}
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null && toDate != null){ 
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getTask().equalsIgnoreCase("verification")){
			if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
				query.setParameter("actionTypeStatusId",inputVO.getActionTypeStatusId());
			if(fromDate2 != null && toDate2 != null){ 
				query.setDate("fromDate2", fromDate2);
				query.setDate("toDate2", toDate2);
			}
		}
		if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() > 0L){
			query.setParameter("alertStatusId",inputVO.getStatusId());
		}
		if(inputVO.getCategoryId() !=null && inputVO.getCategoryId()>0l){
			query.setParameter("alertCategoryId",inputVO.getCategoryId());
		}
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
		if(assignedCadreId != null && assignedCadreId.longValue() > 0L){
			query.setParameter("assignedCadreId", assignedCadreId);
		}
		if(involvedCadreId != null && involvedCadreId.longValue() > 0L){
			query.setParameter("involvedCadreId", involvedCadreId);  
		}
		if(involvedCadreId != null && involvedCadreId.longValue() > 0L && impactId != null && impactId.longValue() > 0L){
			query.setParameter("impactId", impactId);  
		}
		if(inputVO.getTehsilId() != null && inputVO.getTehsilId() > 0)
		{
			query.setParameter("tehsilId", inputVO.getTehsilId());
		}
		if(inputVO.getVillageId() != null && inputVO.getVillageId() > 0)
		{
			query.setParameter("panchayatId", inputVO.getVillageId());
		}
		return query.list();
	}
//Santosh
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId){
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
						" left join userAddress.ward ward,AlertDepartmentStatus model1   ");
		queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
		
		queryStr.append(" and model.isDeleted ='N'  ");
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
						" model.alertStatus.alertStatusId, " +
						" model.alertStatus.alertStatus, " +
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
		queryStr.append(" left join model.alertSeverity alertSeverity,AlertDepartmentStatus model1  ");
		queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId  ");
		queryStr.append(" and  model.isDeleted ='N'  ");
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
		queryStr.append(" group by model.alertStatus.alertStatusId, alertCategory.alertCategoryId " +
						" order by model.alertStatus.alertStatusId, alertCategory.alertCategoryId ");
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
				" from Alert model,AlertDepartmentStatus model1 " +
				" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
				" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId  " +
				" and model.isDeleted ='N'  ");
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
						" left join userAddress.ward ward,AlertDepartmentStatus model1  " );
		queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
		queryStr.append(" and model.isDeleted ='N'  ");
		
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
	
	public List<Alert> getAlertDetailsOfNewstype(Long alertCategoryType,Long alertTypeId){
		
		Query query = getSession().createQuery(" select model from Alert model " +
				"  where " +
				" model.alertCategoryTypeId = :alertCategoryType" +
				" and model.alertTypeId = :alertTypeId " +
				" order by model.updatedTime desc ");
		
		query.setParameter("alertCategoryType", alertCategoryType);
		query.setParameter("alertTypeId", alertTypeId);
		
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
	
		queryStr.append(" model.alertStatus.alertStatusId, " +//1
						" model.alertStatus.alertStatus, " +//2
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

		queryStr.append(" left join model.alertCategory alertCategory,AlertDepartmentStatus model1  ");
		queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
		
		if(fromDate != null && toDate != null){  
			queryStr.append(" and date(model.createdTime) between :fromDate and :toDate and model.isDeleted = 'N' ");
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
	public List<Object[]> getTotalAlertGroupByLocation(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList){
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();       
	}  
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue,Long disctrictId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(filterType != null && filterType.equalsIgnoreCase("District")){
			queryStr.append(" district.districtId, " +//0
					       " district.districtName, ");//1
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
			queryStr.append(" constituency.constituencyId, " +//0
							" constituency.name, ");//1
		}
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
						" left join userAddress.ward ward,AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N'  ");
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
			/*if(scopeIdList.get(0).longValue() == 8l){
				queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds) ");
		}
		if(locationValue != null && locationValue.longValue() > 0){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" and district.districtId =:locationValue ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" and constituency.constituencyId =:locationValue");
			}
	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			queryStr.append(" and district.districtId =:disctrictId ");
		}
		if(step.equalsIgnoreCase("one")){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId order by district.districtId ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId order by constituency.constituencyId ");
			}
		}else{
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId,model.alertStatus.alertStatusId order by district.districtId  ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId,model.alertStatus.alertStatusId order by constituency.constituencyId ");
			}
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(locationValue != null && locationValue.longValue() > 0){
		 query.setParameter("locationValue", locationValue);	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			 query.setParameter("disctrictId", disctrictId);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
		  query.setParameterList("alertStatusIds", alertStatusIds);
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
	public List<Object[]> getTotalAlertGroupByDist(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList){
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();  
	}
	//anil
	public List<Object[]> getAlertDtls(Date fromDate, Date toDate, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long alertCategoryId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,List<Long> impactScopeIds){
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
						" district.districtName," +//11
						" alertSource.alertSourceId, " +//12
						" alertSource.source," +//13
						" editionType.editionTypeId, " +//14
						" editionType.editionType, " +//15
						" edition.editionId, " +//16
						" edition.editionAlias, " +//17
						" tvNewsChannel.tvNewsChannelId, " +//18
						" tvNewsChannel.channelName," +//19
						" state.stateName, " +//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" 4 ," +
						" alertStatus.color ");//24
		queryStr.append(" from Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.tvNewsChannel tvNewsChannel "+
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
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency");
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
			queryStr.append(" and alertType.alertTypeId = :alertTypeId ");
		}
		
		if(alertStatusIds != null && alertStatusIds.size() > 0L){
			queryStr.append(" and alertStatus.alertStatusId in  (:alertStatusIds) ");
		}
		
		if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
			queryStr.append(" and alertCategory.alertCategoryId = (:alertCategoryId) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and editionType.editionTypeId in (:editionList) ");
		}
		if(impactScopeIds != null && impactScopeIds.size() > 0){
		/*	if(impactScopeIds.get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactScopeIds) ");
		}
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");       
		}if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) "); 
		}
		Query query = getSession().createQuery(queryStr.toString());   
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0L){
			query.setParameterList("alertStatusIds", alertStatusIds);
		}
		if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
			query.setParameter("alertCategoryId", alertCategoryId);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);
		}
		if(impactScopeIds != null && impactScopeIds.size() > 0){
			query.setParameterList("impactScopeIds", impactScopeIds);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertTypeBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " model.alertType.alertTypeId, " +
		  				  " model.alertType.alertType, ");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from " +
		  				  " Alert model where model.isDeleted='N' ");
		  if(alertType != null && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");  
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  }
		  
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		  }
		  if(scopeIds != null && scopeIds.size() > 0){
			/*  if(scopeIds.get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
				}*/
			  queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:scopeIds)");
		  }
		  if(alertStatusIds != null && alertStatusIds.size() > 0){
			  queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
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
	    
    	if(editionTypes != null && editionTypes.get(0).longValue() > 0){
    		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
    	}
	    queryStr.append(" group by model.alertType.alertTypeId ");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		if(scopeIds != null && scopeIds.size() > 0){
			  query.setParameterList("scopeIds", scopeIds);  
		 }
		 if(alertStatusIds != null && alertStatusIds.size() > 0){
			 query.setParameterList("alertStatusIds", alertStatusIds);
		  }
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertStatusBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " model.alertStatus.alertStatusId, " +
		  				  " model.alertStatus.alertStatus,");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from Alert model,AlertDepartmentStatus model1 " +
		  				  " where  " +
		  				  " model1.alertType.alertTypeId = model.alertType.alertTypeId " +
						  " and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
						  " and model.isDeleted='N' ");
		  if(alertType != null && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  }
		  
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(scopeIds != null && scopeIds.size() > 0){
			 /*   if(scopeIds.get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
				}*/
			  queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:scopeIds) ");  
		 }
		 if(alertStatusIds != null && alertStatusIds.size() > 0){
			  queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
    		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
    	}
	    queryStr.append(" group by model.alertStatus.alertStatusId ");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		 if(scopeIds != null && scopeIds.size() > 0){
			 query.setParameterList("scopeIds", scopeIds);
		 }
		 if(alertStatusIds != null && alertStatusIds.size() > 0){
			 query.setParameterList("alertStatusIds", alertStatusIds);
		 }
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category,");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' ");
		  if(alertType != null && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  }
		  
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		  if(alertStatusIds != null && alertStatusIds.size() > 0){
			  queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
    		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
    	}
	    if(scopeIds != null && scopeIds.size() > 0){
	    	/* if(scopeIds.get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
	    	queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:scopeIds) ");
	    }
	    queryStr.append(" group by model.alertCategory.alertCategoryId");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		 if(scopeIds != null && scopeIds.size() > 0){
		   query.setParameterList("scopeIds", scopeIds); 
		 }
		 if(alertStatusIds != null && alertStatusIds.size() > 0){
			 query.setParameterList("alertStatusIds", alertStatusIds);
		 }
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertStatus.alertStatusId," +
		  				  " model.alertStatus.alertStatus," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model,AlertDepartmentStatus model1  " +
		  				  " where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
		  				  " and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
		  				  " and model.isDeleted='N' ");
		  if(alertType != null && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		  }
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
    		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
    	}
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
    		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
    	}
	     if(scopeIds != null && scopeIds.size() > 0){
	    	/* if(scopeIds.get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
	    	queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:scopeIds) ");
	    }
	     if(alertStatusIds != null && alertStatusIds.size() > 0){
			  queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
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
		if(alertType != null && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		 if(scopeIds != null && scopeIds.size() > 0){
			   query.setParameterList("scopeIds", scopeIds); 
		 }
		 if(alertStatusIds != null && alertStatusIds.size() > 0){
			 query.setParameterList("alertStatusIds", alertStatusIds);
		 }
		return query.list();  
	}
	public List<Object[]> getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionList,List<Long> alertStatusIds,List<Long> scopeIdList,String type){
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds) ");
	    }
	    if(scopeIdList != null && scopeIdList.size() > 0){
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
	  /*  if(type.equalsIgnoreCase("GHMC")){
	    	queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
	    }*/
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);
		}
		return query.list();
	}
	public List<Object[]> getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,List<Long> scopeIdList,String type){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertImpactScope.alertImpactScopeId," +
		  				  " model.alertImpactScope.impactScope," +
		  				  " model.alertStatus.alertStatusId," +
		  				  " model.alertStatus.alertStatus," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model,AlertDepartmentStatus model1  " +
		  				  " where model1.alertType.alertTypeId = model.alertType.alertTypeId" +
		  				  " and  model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
		  				  " and model.isDeleted='N' " +
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
	    } 
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds) ");
	    }
	    if(scopeIdList != null && scopeIdList.size() > 0){
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
	  /*  if(type.equalsIgnoreCase("GHMC")){
	    	queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp	
	    }*/
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(scopeIdList != null && scopeIdList.size() > 0){
			query.setParameterList("scopeIdList", scopeIdList);
		}
		return query.list();
	}
	public List<Object[]> getPublicRepresentativeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  				  " where  " +
		  				  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  				  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		 }
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			/* if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
        if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
		}
		if(districtId != null && districtId.longValue() > 0){
		  queryStr.append(" and model.alert.userAddress.district.districtId=:districtId ");	
		}
	   if(alertStatusIds != null && alertStatusIds.size() > 0){
		   queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) "); 
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		   query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
		  query.setParameterList("alertStatusIds", alertStatusIds);
		}
		return query.list();
	}
	public List<Object[]> getPartyCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,TdpCommitteeMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			/* if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
        if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(districtId != null && districtId.longValue() > 0){
 		   queryStr.append(" and model.alert.userAddress.district.districtId=:districtId");	
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			   queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) "); 
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		   query.setParameter("districtId", districtId);	
	    }
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			  query.setParameterList("alertStatusIds", alertStatusIds);
		}
		return query.list();
	}
	public List<Object[]> getProgramCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model,TdpMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' and model1.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			/* if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(districtId != null && districtId.longValue() > 0){
  		   queryStr.append(" and model.alert.userAddress.district.districtId=:districtId");	
  		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
			   queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) "); 
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
			   query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			  query.setParameterList("alertStatusIds", alertStatusIds);
		}
		return query.list();
	}
	public List<Object[]> getAllAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model" +
		  				  " where  " +
		  				  " model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
		/*	 if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
	    if(districtId != null && districtId.longValue() > 0){
	  		   queryStr.append(" and model.alert.userAddress.district.districtId=:districtId");	
	  	}
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
			   queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) "); 
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			  query.setParameterList("alertStatusIds", alertStatusIds);
		}
		return query.list();
	}
	public List<Object[]> getTotalAlertGroupByPubRepThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
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
	                	" and model.alert.isDeleted='N' and model.isDeleted='N' " +
	    				" and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
	    				" and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){
	    /*	if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
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
	    if(districtId != null && districtId.longValue() > 0){
	    	queryStr.append(" and model.alert.userAddress.district.districtId=:districtId");
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds);
 		}
	    return query.list();
	}
	
	public List<Object[]> getTotalAlertGroupByCandThenStatus(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, String step, Long publicRepresentativeTypeId,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
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
	                	" and model.alert.isDeleted='N' and model.isDeleted='N' " +
	    				" and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")" +
	    				" and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
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
	    /*  if(impactLevelIds.get(0).longValue() == 8l){
				queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
		   }*/
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
	    if(districtId != null && districtId.longValue() > 0){
	    	 queryStr.append(" and model.alert.userAddress.district.districtId =:districtId ");	
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
  			queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
  			query.setParameterList("alertStatusIds", alertStatusIds);
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

	public List<Object[]> getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpBasicCommiteeIds,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
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
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		  if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			/* if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
 		}
 		if(districtId != null && districtId.longValue() > 0){
 			queryStr.append(" and model.alert.userAddress.district.districtId =:districtId");
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		   query.setParameter("districtId", districtId);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds);
 		}
		return query.list();  
	}
	public List<Object[]> getTdpCommitteeRolesByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpCommitteeLevelIds,Long tdpBasicCommitteeId,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds){
	      
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
	                " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
	      if(stateId != null && stateId.longValue() > 0l){
	        queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
	      }
	      if(fromDate !=null && toDate !=null){
	        queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
	     }
	     if(impactLevelIds != null && impactLevelIds.size() > 0){
	    	/* if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
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
	    if(districtId != null && districtId.longValue() > 0){
	    	queryStr.append(" and model.alert.userAddress.district.districtId =:districtId");
	    }
        if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds); 
 		}
	    return query.list();  
	  }
	
	public List<Object[]> getAlertDtlsForPubRep(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate, Long publicRepresentativeTypeId, Long cadreId, List<Long> alertStatusIds,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId){
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
						" district.districtName, "+//11
					 	 " alertSource.alertSourceId, " +//12
						 " alertSource.source," +//13
						 " editionType.editionTypeId, " +//14
						 " editionType.editionType, " +//14
						 " edition.editionId, " +//16
						 " edition.editionAlias, " +//17
						 " tvNewsChannel.tvNewsChannelId, " +//18
						 " tvNewsChannel.channelName," +//19
						 " state.stateName, "+//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" 1 , " +
						" alertStatus.color "); //24
	    queryStr.append(" from " +
	                	" AlertAssigned model " +
	                	" left join model.alert alert " +
	                	 " left join alert.alertCategory alertCategory " +
	    	   			 " left join alert.alertSource alertSource " +
	    	   			 " left join alert.editionType editionType " +
	    	   			 " left join alert.edition edition " +
	    	   			 " left join alert.tvNewsChannel tvNewsChannel "+
	                	" left join alert.alertStatus alertStatus " +
	                	" left join alert.alertCategory alertCategory " +
	                	" left join alert.alertImpactScope alertImpactScope " +
	                	" left join alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.parliamentConstituency parliamentConstituency  " +
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
	    				" and alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds) ");  
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
	     /*  if(impactLevelIds.get(0).longValue() == 8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
		   }*/
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
	    if(districtId != null && districtId.longValue() > 0){
	    	queryStr.append(" and district.districtId=:districtId");
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionTypeList != null && editionTypeList.size() > 0){
 			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
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
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(cadreId.longValue() != 0L && cadreId != null){
	    	query.setParameter("cadreId", cadreId);
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		  query.setParameter("districtId", districtId);   
		}
	    return query.list();  
	}
	
	public List<Object[]> getStateImpactLevelAlertCnt(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> statusIds,Long districtId){
		   
		   StringBuilder queryStr = new StringBuilder();
		   
		   queryStr.append("select ");
		   queryStr.append(" model.alertStatus.alertStatusId,model.alertStatus.alertStatus,"); 
	       queryStr.append(" count(distinct model.alertId)" +
	  				     " from Alert model" );
	 	   queryStr.append(",AlertDepartmentStatus model1 " +
		  		" where model1.alertType.alertTypeId = model.alertType.alertTypeId  " +
		  		" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId and model.isDeleted='N' ");  
		  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		 if(statusIds != null && statusIds.size() > 0){
		  queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");	 
		 }
		 if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(impactLevelIds != null && impactLevelIds.size() > 0){
			/* if(impactLevelIds != null && impactLevelIds.get(0).longValue()==8l){
				queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")"); 
			 }*/
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(districtId != null && districtId.longValue() > 0){
			queryStr.append(" and model.userAddress.district.districtId=:districtId ");
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
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			query.setParameterList("impactLevelIds", impactLevelIds); 
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		 if(statusIds != null && statusIds.size() > 0){
			 query.setParameterList("statusIds", statusIds); 
		 }
		 if(districtId != null && districtId.longValue() > 0){
			 query.setParameter("districtId", districtId); 
		 }
		return query.list();
	}
	public List<Object[]> getMemForPartyCommitDesg(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long tdpBasicCommitteeId, Long designationId, String step,List<Long> alertTypeList, List<Long> editionList,Long districtId,List<Long> alertStatusIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  model.tdpCadre.tdpCadreId," +  
	                	" model.tdpCadre.firstname,");
		if(step.equalsIgnoreCase("two")){  
			queryStr.append(" model.alert.alertStatus.alertStatusId," +
		                	" model.alert.alertStatus.alertStatus,");  
		}
	                
		queryStr.append(" count(distinct model.alert.alertId) " +  
	                	" from AlertAssigned model,TdpCommitteeMember model1 " +
	                	" where " +
	                	" model1.tdpCommitteeRole.tdpRoles.tdpRolesId = :designationId " +
	                	" and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId " +
	                	" and model.alert.isDeleted = 'N' and model.isDeleted='N' " +
	                	" and model.alert.alertType.alertTypeId  in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +  
	                	" and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");  
		if(stateId != null && stateId.longValue() > 0l){
			queryStr.append(" and model.alert.userAddress.state.stateId=:stateId ");  
		}
		if(fromDate !=null && toDate !=null){
			queryStr.append(" and date(model.alert.createdTime) between :startDate and :endDate  ");
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
		/*	if(impactLevelIds != null && impactLevelIds.get(0).longValue()==8l){
					queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")"); 
			}*/
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
		
		if(districtId != null && districtId.longValue() > 0){
			queryStr.append(" and model.alert.userAddress.district.districtId=:districtId");
		}
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and  model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionList != null && editionList.size() > 0){
 			queryStr.append(" and  model.alert.editionType.editionTypeId in (:editionList) ");
 		}
 		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			queryStr.append(" and  model.alert.alertStatus.alertStatusId in (:alertStatusIds) ");
 		}
 		
		if(step.equalsIgnoreCase("two")){
			queryStr.append(" group  by model.tdpCadre.tdpCadreId, model.alert.alertStatus.alertStatusId ");
		}else{
			queryStr.append(" group  by model.tdpCadre.tdpCadreId ");
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
	    if(designationId != null && designationId.longValue() > 0){
	    	query.setParameter("designationId", designationId);  
	     }
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		  query.setParameter("districtId", districtId);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
 			query.setParameterList("alertStatusIds", alertStatusIds);
 		}
	    return query.list();    
	}//imp
	public List<Object[]> getAlertDtlsAssignedByPartyCommite(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long cadreId, Long tdpBasicCommitteeId, Long designationId,List<Long> alertStatusIds,List<Long> alertTypeList,List<Long> editionList,Long districtId){
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
						" district.districtName, "+//11 
						 " alertSource.alertSourceId, " +//12
						 " alertSource.source," +//13
						 " editionType.editionTypeId, " +//14
						 " editionType.editionType, " +//15
						 " edition.editionId, " +//16
						 " edition.editionAlias, " +//17
						 " tvNewsChannel.tvNewsChannelId, " +//18
						 " tvNewsChannel.channelName," +//19
						 " state.stateName ," +//20
						 " tehsil.tehsilName, " +//21
						 " panchayat.panchayatName , " +//22
						 " localElectionBody.name, " +//23
						 " 1 , " +//24
						 " alertStatus.color "); //25
		queryStr.append(" from AlertAssigned model " +   
						" left join model.alert alert " +
						 " left join alert.alertCategory alertCategory " +
			   			 " left join alert.alertSource alertSource " +
			   			 " left join alert.editionType editionType " +
			   			 " left join alert.edition edition " +
			   			 " left join alert.tvNewsChannel tvNewsChannel "+
		            	" left join alert.alertStatus alertStatus " +
		            	" left join alert.alertCategory alertCategory " +
		            	" left join alert.alertImpactScope alertImpactScope " +
		            	" left join alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.parliamentConstituency parliamentConstituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat " +
						" left join userAddress.ward ward, "+
						" TdpCommitteeMember model1 " +
	                	" where " +
	                	" model1.tdpCommitteeRole.tdpRoles.tdpRolesId = :designationId " +
	                	" and model.tdpCadre.tdpCadreId = :cadreId " +
	                	" and model.tdpCadre.isDeleted = 'N' " +
	                	" and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId " +
	                	" and alert.isDeleted = 'N' and model.isDeleted='N' " +
	                	" and alert.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +  
	                	" and alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
		if(alertStatusIds != null && alertStatusIds.size() > 0l){  
			queryStr.append(" and alertStatus.alertStatusId in(:alertStatusIds) ");  
		}
		if(stateId != null && stateId.longValue() > 0l){
			queryStr.append(" and state.stateId=:stateId ");  
		}
		if(fromDate !=null && toDate !=null){
			queryStr.append(" and date(alert.createdTime) between :startDate and :endDate  ");
		}
		if(impactLevelIds != null && impactLevelIds.size() > 0){
			/*if(impactLevelIds != null && impactLevelIds.get(0).longValue()==8l){
				queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")"); 
		    }*/
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		}
		if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size() > 0){
			queryStr.append(" and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(:tdpCommitteeLevelIds)");
		}
		if(tdpBasicCommitteeId != null && tdpBasicCommitteeId.longValue() > 0){
			queryStr.append(" and model1.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=:tdpBasicCommitteeId");   
		}
	   if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and  model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
	 	if(editionList != null && editionList.size() > 0){
 			queryStr.append(" and  model.alert.editionType.editionTypeId in (:editionList) ");
 		}
	 	if(districtId != null && districtId.longValue() > 0){
	 		queryStr.append(" and district.districtId =:districtId");   
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
	    if(tdpCommitteeLevelIds != null && tdpCommitteeLevelIds.size() > 0){
	    	query.setParameterList("tdpCommitteeLevelIds", tdpCommitteeLevelIds);
	    }
	    if(tdpBasicCommitteeId != null && tdpBasicCommitteeId.longValue() > 0){
	    	query.setParameter("tdpBasicCommitteeId", tdpBasicCommitteeId);  
	    }
	    if(designationId != null && designationId.longValue() > 0){
	    	query.setParameter("designationId", designationId);  
	    }
	    if(cadreId != null && cadreId.longValue() > 0){
	    	query.setParameter("cadreId", cadreId);  
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0l){  
			query.setParameterList("alertStatusIds", alertStatusIds);
		}
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(districtId != null && districtId.longValue() > 0){
		  query.setParameter("districtId", districtId);	
		}
	    return query.list();
	}
	
	public List<Object[]> getAlertDetailsByCadreWise(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long tdpCadreId,List<Long> alertStatusIds,String resultType,List<Long> alertTypeList,List<Long> editionList,Long districtId){
		
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
						" district.districtName, "+//11         
					 	" alertSource.alertSourceId, " +//12
						 " alertSource.source," +//13
						 " editionType.editionTypeId, " +//14
						 " editionType.editionType, " +//15
						 " edition.editionId, " +//16
						 " edition.editionAlias, " +//17
						 " tvNewsChannel.tvNewsChannelId, " +//18
						 " tvNewsChannel.channelName," +//19
						 " state.stateName, "+//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" 1 , " +
						" alertStatus.color "); //24
	    queryStr.append(" from " +
	                	" AlertAssigned model " +
	                	" left join model.alert alert " +
	                	 " left join alert.alertCategory alertCategory " +
	    	   			 " left join alert.alertSource alertSource " +
	    	   			 " left join alert.editionType editionType " +
	    	   			 " left join alert.edition edition " +
	    	   			 " left join alert.tvNewsChannel tvNewsChannel  "+
	                	" left join alert.alertStatus alertStatus " +
	                	" left join alert.alertCategory alertCategory " +
	                	" left join alert.alertImpactScope alertImpactScope " +
	                	" left join alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency" +
						" left join userAddress.tehsil tehsil" +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat " +
						" left join userAddress.parliamentConstituency parliamentConstituency  " );
				       if(resultType != null && resultType.equalsIgnoreCase("Program Committee")){
			            queryStr.append(" ,TdpMember model1");	 
			           }
			        	queryStr.append(" where  " +
	                	" alert.isDeleted='N' and model.isDeleted = 'N' " +
	    				" and alert.alertType.alertTypeId in("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") " +
	    				" and alert.alertStatus.alertStatusId not in("+IConstants.ALERT_STATUS_ID+") ");
                	 if(resultType != null && resultType.equalsIgnoreCase("Program Committee")){
	                     queryStr.append(" and model1.isDeleted='N' and model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId ");	 
	                   }
                	
	    if(tdpCadreId != null && tdpCadreId.longValue() > 0){
	    	queryStr.append(" and model.tdpCadre.tdpCadreId =:tdpCadreId");
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds) ");  
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and alert.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){
	       queryStr.append(" and date(alert.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){  
	    /*   if(impactLevelIds != null && impactLevelIds.get(0).longValue()==8l){
				queryStr.append(" and model.alert.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")"); 
		   }*/
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
	    if(districtId != null && districtId.longValue() > 0){
	    	queryStr.append(" and district.districtId =:districtId");
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
 			queryStr.append(" and  model.alert.alertType.alertTypeId in (:alertTypeList) ");
 		}
 		if(editionList != null && editionList.size() > 0){
 			queryStr.append(" and  model.alert.editionType.editionTypeId in (:editionList) ");
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
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
	    }
	    if(tdpCadreId.longValue() != 0L && tdpCadreId != null){
	    	query.setParameter("tdpCadreId", tdpCadreId);
	    }
       if(alertTypeList != null && alertTypeList.size() > 0){
 			query.setParameterList("alertTypeList", alertTypeList);  
 		}
 		if(editionList != null && editionList.size() > 0){
 			query.setParameterList("editionList", editionList);  
 		}
 		if(districtId != null && districtId.longValue() > 0){
 			query.setParameter("districtId", districtId); 
 		}
	    return query.list();  
	}
public List<Object[]> getDistrictAndStateImpactLevelWiseAlertDtls(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,List<Long> districtIdList,Long catId, List<Long> alertTypeList, List<Long> editionList,
		Long constituencyId,List<Long> alertStatusIds,String publicationType,Long publicationId,Long localElectionBodyId,String locationLevel){
		
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
						" district.districtName, "+//11  
						" alertSource.alertSourceId, " +//12
						 " alertSource.source," +//13
						 " editionType.editionTypeId, " +//14
						 " editionType.editionType, " +//15
						 " edition.editionId, " +//16
						 " edition.editionAlias, " +//17
						 " tvNewsChannel.tvNewsChannelId, " +//18
						 " tvNewsChannel.channelName," +//19
						 " state.stateName, " +//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" 1 , " +//24
						" alertStatus.color "); //25 
	    queryStr.append(" from " +
	                	" Alert model " +
	                	" left join model.alertCategory alertCategory " +
	   	   			 	" left join model.alertSource alertSource " +
	   	   			 	" left join model.editionType editionType " +
	   	   			 	" left join model.edition edition " +
	   	   			 	" left join model.tvNewsChannel tvNewsChannel "+
	                	" left join model.alertStatus alertStatus " +
	                	" left join model.alertCategory alertCategory " +
	                	" left join model.alertImpactScope alertImpactScope " +
	                	" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district " +
						" left join userAddress.constituency constituency " +
						" left join userAddress.parliamentConstituency parliamentConstituency" +
						" left join userAddress.tehsil tehsil " +
						" left join userAddress.localElectionBody localElectionBody " +
						" left join userAddress.panchayat panchayat " +
						" left join localElectionBody.electionType electionType  " +
						" where  " +
	                	" model.isDeleted='N' " +
	    				" and model.alertType.alertTypeId in("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")  ");
                	
                	
	    if(catId != null && catId.longValue() > 0){
	    	queryStr.append(" and alertCategory.alertCategoryId=:catId");
	    }
	    if(districtIdList != null && districtIdList.size() > 0){
	    	queryStr.append(" and district.districtId in (:districtIds)");
	    }
	    if(constituencyId != null && constituencyId.longValue() > 0){
	    	queryStr.append(" and constituency.constituencyId=:constituencyId");
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds)");
	    }
	    if(stateId != null && stateId.longValue() > 0l){
	    	queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
	    }
	    if(fromDate !=null && toDate !=null){  
	       queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
	    }
	    if(impactLevelIds != null && impactLevelIds.size() > 0){ 
	    	/*if(impactLevelIds.get(0).longValue() == 8l){//ghmc
	    		queryStr.append(" and userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+")");//election type
	    	}*/
	      queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
	    }
	    if(localElectionBodyId != null && localElectionBodyId.longValue() > 0){
	      if(impactLevelIds.get(0).longValue() == 8l){//ghmc
	    	  queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
    	   }
	       queryStr.append(" and userAddress.localElectionBody.localElectionBodyId = :localElectionBodyId ");	
	    }
	    if(locationLevel.equalsIgnoreCase("OtherLocations")){
	      if(impactLevelIds.get(0).longValue() == 8l){//ghmc
	    	  queryStr.append(" and ((electionType.electionTypeId not in ("+IConstants.ELECTION_TYPE_IDS+")) or (userAddress.localElectionBody is null )) ");//Not CORPORATION & Greater Municipal Corp
	      	}
	    }
	    if(publicationType.equalsIgnoreCase("TvChannel")){
	    	if(publicationId.longValue() > 0l){
	    		queryStr.append(" and tvNewsChannel.tvNewsChannelId =:publicationId");	
	    	}else{
	    	  queryStr.append(" and tvNewsChannel.tvNewsChannelId is not null ");	
	    	}
	    }else if(publicationType.equalsIgnoreCase("NewsPaper")){
	    	if(publicationId.longValue() > 0l){
	    		queryStr.append(" and edition.newsPaperId =:publicationId");	
	    	}else{
	    		queryStr.append(" and edition.newsPaperId is not null ");	
	    	}
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
	    if(alertTypeList != null && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionTypeId in (:editionList) ");
	    }
	   
	    Query query = getSession().createQuery(queryStr.toString());
	    
	    if(stateId != null && stateId.longValue() > 0l){            
	    	query.setParameter("stateId", stateId);
	    }
	    if(districtIdList != null && districtIdList.size() > 0){
	    	query.setParameterList("districtIds", districtIdList);
	    }
	    if(constituencyId != null && constituencyId.longValue() > 0){
	    	query.setParameter("constituencyId", constituencyId);
	    }
	    if(alertStatusIds != null && alertStatusIds.size() > 0){
	    	query.setParameterList("alertStatusIds", alertStatusIds);
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
	    if(catId != null && catId.longValue() > 0){
	    	query.setParameter("catId", catId);
	    }
	    if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
	    if(publicationId.longValue() > 0l){
	       query.setParameter("publicationId", publicationId);	
	    }
	    if(localElectionBodyId != null && localElectionBodyId.longValue() > 0){
	    	query.setParameter("localElectionBodyId", localElectionBodyId);	
		    }
		 
	    return query.list();  
	}
   public Date getAlertLastUpdatedTime(){
	   Query query = getSession().createQuery("select max(model.createdTime) from Alert model where model.isDeleted='N' ");
	   return (Date)query.uniqueResult();
   }
   
   public Long getAlertStatusOfArticle(Long articleId){
	   Query query = getSession().createQuery(" select model.alertCategoryTypeId from Alert model " +
	   		" where model.isDeleted='N' " +
	   		" and model.alertCategoryId = 2l " +
	   		" and model.alertCategoryTypeId = :articleId  ");
	   
	   query.setParameter("articleId", articleId);
	   return (Long) query.uniqueResult();
   }
   
   public List<Object[]> getAlertCreatedDate(Long alertCategoryTypeId){
		
		Query query = getSession().createQuery(" select model.alertId,date(model.createdTime) from Alert model " +
				"  where " +
				" model.alertCategoryTypeId = :alertCategoryTypeId " +
				" order by model.alertId desc ");
		
		query.setParameter("alertCategoryTypeId", alertCategoryTypeId);
		
		return query.list();
		
	}
   
   public int updateAlertStatusOfNewsForDelete(Long alertId){
	   Query query = getSession().createQuery(" update Alert model set model.isDeleted = 'O',model.updatedTime=:updatedTime where model.alertId=:alertId ");
	   query.setParameter("alertId", alertId);
	   query.setParameter("updatedTime", new DateUtilService().getCurrentDateAndTime());
	    return query.executeUpdate();
   }
   public Object[] getSourceDtlsByAlertId(Long alertId){
	   StringBuilder sb = new StringBuilder();
	   sb.append(" select " +
			   	 " alertCategory.alertCategoryId, " +//0
	   			 " alertSource.alertSourceId, " +//1
	   			 " alertSource.source," +//2
	   			 " editionType.editionTypeId, " +//3
	   			 " editionType.editionType, " +//4
	   			 " edition.editionId, " +//5
	   			 " edition.editionAlias, " +//6
	   			 " tvNewsChannel.tvNewsChannelId, " +//7
	   			 " tvNewsChannel.channelName " +//8
	   			 " from Alert model " +
	   			 " left join model.alertCategory alertCategory " +
	   			 " left join model.alertSource alertSource " +
	   			 " left join model.editionType editionType " +
	   			 " left join model.edition edition " +
	   			 " left join model.tvNewsChannel tvNewsChannel  where " +
	   			 " model.alertId = :alertId and model.isDeleted = 'N' ");
	   Query query = getSession().createQuery(sb.toString());
	   query.setParameter("alertId", alertId);
	   return (Object[]) query.uniqueResult();  
   }

   public List<Object[]> getAlertCntByAlertTypeBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " model.alertType.alertTypeId, " +
		  				  " model.alertType.alertType, ");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from " +
		  				  " Alert model where model.isDeleted='N' ");
		  if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");  
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		  }
		  
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
	    
   	if(editionTypes != null && editionTypes.get(0).longValue() > 0){
   		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
   	}
	    queryStr.append(" group by model.alertType.alertTypeId ");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		return query.list();
	}
   
   public List<Object[]> getAlertCntByAlertStatusBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " model.alertStatus.alertStatusId, " +
		  				  " model.alertStatus.alertStatus,");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from Alert model where model.isDeleted='N' ");
		  if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		  }
		  
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
   		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
   	}
	    queryStr.append(" group by model.alertStatus.alertStatusId ");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		return query.list();
	}
   
   public List<Object[]> getAlertCntByAlertCategoryBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate, String nextLvlGroup,List<Long> alertType,List<Long> editionTypes){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category,");
		  if(nextLvlGroup.equalsIgnoreCase("true")){
			  queryStr.append(" model.editionType.editionTypeId, " +
	  				  		  " model.editionType.editionType, ");
		  }
		  queryStr.append(" count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' ");
		  if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		  }
		  
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
   		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
   	}
	    queryStr.append(" group by model.alertCategory.alertCategoryId");
	    if(nextLvlGroup.equalsIgnoreCase("true")){
	    	queryStr.append(" ,model.editionType.editionTypeId ");
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
		if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		return query.list();
	}
   
   public List<Object[]> getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertStatus.alertStatusId," +
		  				  " model.alertStatus.alertStatus," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' ");
		  if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			  queryStr.append(" and model.alertType.alertTypeId in (:alertType) ");
		  }else{
			  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		  }
		  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
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
	    if(editionTypes != null && editionTypes.get(0).longValue() > 0){
   		queryStr.append(" and model.editionType.editionTypeId in (:editionTypes) ");
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
		if(alertType != null && !alertType.isEmpty() && alertType.get(0).longValue() > 0L){
			query.setParameterList("alertType", alertType);
		}
		if(editionTypes != null && editionTypes.get(0).longValue() > 0){
			query.setParameterList("editionTypes", editionTypes);
		}
		return query.list();  
	}
   
   public List<Object[]> getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.alertCategory.alertCategoryId," +
		  				  " model.alertCategory.category," +
		  				  " model.alertImpactScope.alertImpactScopeId," +
		  				  " model.alertImpactScope.impactScope," +
		  				  " count(distinct model.alertId) " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' " +
		  				  " and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") " +
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
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
		return query.list();
	}
   public List<Object[]> getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevelForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList){
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
		  				  " and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") " +
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
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
		return query.list();
	}
   
   public List<Object[]> getDistrictAndStateImpactLevelWiseAlertDtlsForOrganization(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,Long districtId,Long catId, List<Long> alertTypeList, List<Long> editionList){
		
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
						" district.districtName, "+//11  
						" alertSource.alertSourceId, " +//1
						 " alertSource.source," +//2
						 " editionType.editionTypeId, " +//3
						 " editionType.editionType, " +//4
						 " edition.editionId, " +//5
						 " edition.editionAlias, " +//6
						 " tvNewsChannel.tvNewsChannelId, " +//7
						 " tvNewsChannel.channelName " );//8
	   			
	    queryStr.append(" from " +
	                	" Alert model " +
	                	" left join model.alertCategory alertCategory " +
	   	   			 	" left join model.alertSource alertSource " +
	   	   			 	" left join model.editionType editionType " +
	   	   			 	" left join model.edition edition " +
	   	   			 	" left join model.tvNewsChannel tvNewsChannel "+
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
	    				" and model.alertType.alertTypeId in("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")  ");
               	
               	
	    if(catId != null && catId.longValue() > 0){
	    	queryStr.append(" and alertCategory.alertCategoryId=:catId");
	    }
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
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
	    	queryStr.append(" and model.alertTypeId in (:alertTypeList) ");  
	    }
	    if(editionList != null && editionList.size() > 0){
	    	queryStr.append(" and model.editionTypeId in (:editionList) ");
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
	    if(catId != null && catId.longValue() > 0){
	    	query.setParameter("catId", catId);
	    }
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
	    return query.list();  
	}
   
   public List<Object[]> getAlertDtlsForOrganization(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList){
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
						" district.districtName," +//11
						" alertSource.alertSourceId, " +//12
						" alertSource.source," +//13
						" editionType.editionTypeId, " +//14
						" editionType.editionType, " +//15
						" edition.editionId, " +//16
						" edition.editionAlias, " +//17
						" tvNewsChannel.tvNewsChannelId, " +//18
						" tvNewsChannel.channelName ");//19
		queryStr.append(" from Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.tvNewsChannel tvNewsChannel "+
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
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency");
		queryStr.append(" where model.isDeleted ='N' and alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
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
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and editionType.editionTypeId in (:editionList) ");
		}
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");       
		}if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) "); 
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
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);
		}
		return query.list();
	}
   public List<Object[]> getVerificationDetails(Long alertId){
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select distinct ");
	   queryStr.append(" AVUT.alert_verification_user_type_id as alertVerificationUserTypeId , ");
	   queryStr.append(" AVUT.user_type as userType , ");
	   queryStr.append(" VC.verification_conversation_id as verificationConversationId, ");
	   queryStr.append(" VD.verification_documents_id as verificationDocumentsId, ");
	   queryStr.append(" VD.document_path as documentPath, ");
	   queryStr.append(" VERC.verification_comments_id as verificationCommentsId, ");
	   queryStr.append(" VERC.comments as comments, ");
	   queryStr.append(" VC.alert_id as alertId");
	   queryStr.append(" from verification_conversation VC ");
	   queryStr.append(" left outer join verification_documents VD on VC.verification_conversation_id = VD.verification_conversation_id and VD.is_deleted = 'N' ");
	   queryStr.append(" left outer join verification_comments VERC on VC.verification_conversation_id = VERC.verification_conversation_id and VERC.is_deleted = 'N', ");
	   queryStr.append(" alert A, alert_verification_user_type AVUT ");
	   queryStr.append(" where  ");
	   queryStr.append(" VC.alert_id = A.alert_id ");
	   queryStr.append(" and VC.is_deleted = 'N' ");
	   queryStr.append(" and VC.alert_verification_user_type_id = AVUT.alert_verification_user_type_id ");
	   queryStr.append(" and A.alert_id = :alertId ");
	   queryStr.append(" order by AVUT.alert_verification_user_type_id,VC.verification_conversation_id,VD.verification_documents_id; ");
	   Query query = getSession().createSQLQuery(queryStr.toString())
			   .addScalar("alertVerificationUserTypeId",Hibernate.LONG)
			   .addScalar("userType", Hibernate.STRING)
			   .addScalar("verificationConversationId", Hibernate.LONG)
			   .addScalar("verificationDocumentsId", Hibernate.LONG)
			   .addScalar("documentPath",Hibernate.STRING)
			   .addScalar("verificationCommentsId", Hibernate.LONG)
			   .addScalar("comments", Hibernate.STRING)
			   .addScalar("alertId", Hibernate.LONG);
	   return query.list();
   }
   
   public List<Object[]> getStateImpactLevelAlertCntForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,String groupType,List<Long> alertTypeList, List<Long> editionList){
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
		  				  " and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
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
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();
	}
   
   public List<Object[]> getTotalAlertGroupByLocationForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList){
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		else{
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();       
	}
   
   public List<Object[]> getTotalAlertGroupByLocationThenStatusForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList){
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		else{
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();   
	}
   
   public List<Object[]> getPublicRepresentativeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,PublicRepresentative model1,TdpCadreCandidate model2 " +
		  				  " where  " +
		  				  " model2.candidate.candidateId=model1.candidate.candidateId " +
		  				  " and model2.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
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
       if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		return query.list();
	}
   
   public List<Object[]> getPartyCommitteeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId " +
		  				  " from AlertAssigned model,TdpCommitteeMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
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
       if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		return query.list();
	}
   
   public List<Object[]> getProgramCommitteeTypeAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model,TdpMember model1 " +
		  				  " where  " +
		  				  " model1.tdpCadre.tdpCadreId=model.tdpCadre.tdpCadreId " +
		  				  " and model.alert.isDeleted='N' and model.isDeleted='N' and model1.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") " +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
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
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		return query.list();
	}
   
   public List<Object[]> getAllAlertDtlsForOrganization(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.alert.alertStatus.alertStatusId,model.alert.alertId,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname " +
		  				  " from AlertAssigned model" +
		  				  " where  " +
		  				  " model.alert.isDeleted='N' and model.isDeleted='N' " +
		  				  " and model.alert.alertType.alertTypeId  in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")" +
		  				  " and model.alert.alertStatus.alertStatusId not in ("+IConstants.ALERT_STATUS_ID+") ");
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
	    
	    if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alert.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			queryStr.append(" and model.alert.editionType.editionTypeId in (:editionList) ");
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionTypeList != null && editionTypeList.size() > 0){
			query.setParameterList("editionList", editionTypeList);  
		}
		return query.list();
	}
   
   public List<Object[]> getTotalAlertGroupByDistForOrganization(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		
		queryStr.append(" district.districtId, " +//0
						" district.districtName," +  //1
						" model1.newsOrganizationId," +  //2
						" model1.organization,");//3
		
		queryStr.append(" count(distinct model.alertId) " +  //4  
						" from AlertCandidate model1,Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " + 
						" where model.isDeleted ='N' and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+")" +
						" and model.alertId = model1.alert.alertId" +
						" and model1.isDepartment = 'Y'");             
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		queryStr.append(" group by model1.newsOrganizationId,district.districtId order by district.districtId,model1.newsOrganizationId ");
		
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
		if(alertTypeList != null && !alertTypeList.isEmpty() && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();  
	}
   public List<Object[]> getAlertDtlsByAlertTypeId(Date fromDate, Date toDate, Long stateId, Long alertTypeId,Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> impactScopeIds,List<Long> alertStatusIds,List<Long> editionList){
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
						" district.districtName," +//11
						" alertSource.alertSourceId, " +//12
						" alertSource.source," +//13
						" editionType.editionTypeId, " +//14
						" editionType.editionType, " +//15
						" edition.editionId, " +//16
						" edition.editionAlias, " +//17
						" tvNewsChannel.tvNewsChannelId, " +//18
						" tvNewsChannel.channelName," +//19
						" state.stateName, "+//20
						" tehsil.tehsilName, " +//21
						" panchayat.panchayatName , " +//22
						" localElectionBody.name, " +//23
						" 1 , " +//24
						" alertStatus.color "); //25
		queryStr.append(" from Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.tvNewsChannel tvNewsChannel "+
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
		queryStr.append(" left join userAddress.parliamentConstituency parliamentConstituency");
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
		if(impactScopeIds != null && impactScopeIds.size() > 0){
			/*if(impactScopeIds.get(0).longValue() == 8l){
				queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactScopeIds) ");	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			queryStr.append(" and alertStatus.alertStatusId in (:alertStatusIds) ");	
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues)");       
		}if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) "); 
		}
		Query query = getSession().createQuery(queryStr.toString());   
		
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(impactScopeIds != null && impactScopeIds.size() > 0){
			query.setParameterList("impactScopeIds", impactScopeIds);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds);
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		return query.list();
	}
   
   public List<Object[]> getTotalAlertGroupByStatusForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select alertStatus.alertStatusId, alertStatus.alertStatus, count(distinct model.alert.alertId) " +
						" from AlertAssigned model " +
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  ");
		queryStr.append(" left join model.alert.alertStatus alertStatus ");
		
		queryStr.append(" where model.alert.isDeleted ='N' and model.tdpCadreId = :tdpCadreId" +
							" and model.isDeleted = 'N'");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.alert.createdTime) between :fromDate and :toDate) ");
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
			queryStr.append(" and model.alert.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by model.alert.alertStatus.alertStatusId order by model.alert.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
   
   public List<Object[]> getTotalAlertGroupByStatusThenCategoryForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" alertStatus.alertStatusId, " +
						" alertStatus.alertStatus, " +
						" alertCategory.alertCategoryId, " +
						" alertCategory.category," +
						" count(distinct model.alert.alertId) " +
						" from AlertAssigned model " +
						" left join model.alert.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " +
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " );
		queryStr.append(" left join model.alert.alertCategory alertCategory ");
		queryStr.append(" left join model.alert.alertType alertType ");
		queryStr.append(" left join model.alert.alertSource alertSource ");
		queryStr.append(" left join model.alert.alertSeverity alertSeverity ");
		queryStr.append(" left join model.alert.alertStatus alertStatus ");
		queryStr.append(" where model.alert.isDeleted ='N' and model.tdpCadreId = :tdpCadreId" +
							" and model.isDeleted = 'N' ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and ( date(model.alert.createdTime) between :fromDate and :toDate)  ");
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
			queryStr.append(" and model.alert.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by alertStatus.alertStatusId, alertCategory.alertCategoryId " +
						" order by alertStatus.alertStatusId, alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
   
   public List<Object[]> getLocationLevelWiseAlertsDataForCentralMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select " +
				" model.alert.alertId, " +//0
				" model.alert.description, " +//1
				" date(model.alert.createdTime)," +//2
				" alertType.alertType, " +//3
				" alertSource.source, " +//4
				" alertSeverity.severity, " +//5
				" model.alert.regionScopes.regionScopesId, " +//6
				" model.alert.regionScopes.scope," +//7
				" alertStatus.alertStatusId, " +//8
				" alertStatus.alertStatus");//9
		str.append(" ,tehsil.tehsilId, " +//10
				  " tehsil.tehsilName , " +//11
				  " panc.panchayatId, " +//12
				  " panc.panchayatName," +//13
				  " localElectionBody.localElectionBodyId," +//14
				  " localElectionBody.name, " +//15
				  " district.districtId, " +//16
				  " district.districtName, " +//17
				  " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +//19
				   " constituency.name");//20
		str.append(" ,state.stateId," +//21
				   " state.stateName");//22
		str.append(" ,ward.constituencyId," +//23
				   " ward.name,");//24
		str.append(" alertCategory.alertCategoryId, " +//25
				   " alertCategory.category, " +//26
				   " editionType.editionTypeId, " +//27
				   " editionType.editionType, " +//28
				   " edition.editionId, " +//29
				   " edition.editionAlias, " +//30
				   " tvNewsChannel.tvNewsChannelId, " +//31
				   " tvNewsChannel.channelName, " +//32
				   " model.alert.title ");//33 
		
		str.append(" from AlertAssigned model " +
				" 	 left join model.alert.editionType editionType " +
       		"  	 left join model.alert.edition edition " +
       		" 	 left join model.alert.tvNewsChannel tvNewsChannel "+
				" left join model.alert.alertSeverity alertSeverity" +
				" left join model.alert.alertSource  alertSource " +
				" left join model.alert.userAddress.panchayat panc ");
		str.append(" left join model.alert.userAddress.tehsil tehsil ");
		str.append(" left join model.alert.userAddress.constituency constituency ");
		str.append(" left join model.alert.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.alert.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.alert.userAddress.district district ");
		str.append(" left join model.alert.userAddress.state state ");
		str.append(" left join model.alert.userAddress.ward ward ");
		str.append(" left join model.alert.alertCategory alertCategory ");
		str.append(" left join model.alert.alertType  alertType ");
		str.append(" left join model.alert.alertStatus  alertStatus ");
		str.append(" where model.alert.isDeleted ='N' and model.isDeleted = 'N'");
		
		if(inputVO.getAssignId() != null && inputVO.getAssignId() > 0l)
			str.append(" and model.tdpCadreId = :tdpCadreId");
		
		if(inputVO.getAlertImpactScopeId() !=null && inputVO.getAlertImpactScopeId()>0l){
			str.append(" and model.alert.impactScopeId=:impactScopeId");
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
							str.append(" and model.alert.userAddress.district.districtId in ("+inputVO.getLevelValue()+") ");
					}
					else if(inputVO.getLevelId().longValue() == 4L){
						if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() >0L)
							str.append(" and model.alert.userAddress.constituency.constituencyId in ("+inputVO.getLevelValue()+") ");
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
			str.append(" and model.alert.alertTypeId =:alertTypeId ");
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alert.alertSource.alertSourceId in(:sourceIds)");
		if(fromDate != null)
			str.append(" and ( date(model.alert.createdTime) >=:fromDate and date(model.alert.createdTime) <=:toDate ) ");
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
		if(inputVO.getAssignId() != null && inputVO.getAssignId() > 0l)
			query.setParameter("tdpCadreId", inputVO.getAssignId());
		return query.list();
	}
   	//swadhin lenka
	public List<Object[]> getAlertDetailsForUpdate(Long alertId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select alert.alertTypeId, " +//0
				        " alert.title, " +//1
				        " alert.description, " +//2
				        " alert.alertSeverityId, " +//3
				        " alert.impactLevelId, " +//4
				        " alert.alertSourceId, " +//5
				        " alert.impactScopeId, " +//6
				        " state.stateId, " +//7
				        " district.districtId, " +//8
				        " constituency.constituencyId, " +//9
				        " tehsil.tehsilId, " +//10
				        " panchayat.panchayatId, " +//11
				        " localElectionBody.localElectionBodyId, " +//12
				        " ward.constituencyId " +//13
				        " from Alert alert " +
				        " left join alert.userAddress userAddress " +
				        " left join userAddress.state state" +
				        " left join userAddress.district district " +
				        " left join userAddress.constituency constituency " +
				        " left join userAddress.tehsil tehsil " +
				        " left join userAddress.panchayat panchayat " +
				        " left join userAddress.localElectionBody localElectionBody " +
				        " left join userAddress.ward ward " +
				        " where alert.alertId = :alertId and alert.isDeleted = 'N'");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("alertId", alertId);
		return query.list();
	}
	public int deleteAlert(Long alertId){
		Query query = getSession().createQuery(" update Alert model set model.isDeleted = 'Y' where model.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return query.executeUpdate();
	}
	
	public List<Object[]> getTotalAlertGroupByStatusForGovt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		queryStr.append(" A.alert_status_id as alert_status_id, ");
		queryStr.append(" ALTS.alert_status as alert_status, ");
		queryStr.append(" count(distinct A.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and A.alert_status_id = 1 and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		if(deptIdList != null && deptIdList.size() > 0)
			queryStr.append(" A.govt_department_id in (:deptIdList) and ");
		if(fromDate != null && toDate != null)
			queryStr.append(" (date(A.created_time) between :fromDate and :toDate) and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) )");
		
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
			
		//queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		queryStr.append(" group by ALTS.alert_status_id order by ALTS.alert_status_id; ");
		
		
		
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		
		return query.list(); 
	}
	public List<Object[]> getTotalAlertGroupByStatusThenDepartmentForGovt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		queryStr.append(" A.alert_status_id as alert_status_id, ");
		queryStr.append(" ALTS.alert_status as alert_status, ");
		queryStr.append(" A.govt_department_id as govt_department_id, ");
		queryStr.append(" GD.department_name as department_name, ");
		queryStr.append(" count(distinct A.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		if(deptIdList != null && deptIdList.size() > 0)
			queryStr.append(" A.govt_department_id in (:deptIdList) and ");
		if(fromDate != null && toDate != null)
			queryStr.append(" (date(A.created_time) between :fromDate and :toDate) and A.alert_status_id = 1 and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) )");
		/*else if(printIdList != null && !printIdList.isEmpty()) 
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		
		//queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		queryStr.append(" group by ALTS.alert_status_id,A.govt_department_id order by ALTS.alert_status_id,A.govt_department_id; ");
		
		
		
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_status_id", Hibernate.LONG)
				.addScalar("alert_status", Hibernate.STRING)
				.addScalar("govt_department_id", Hibernate.LONG)
				.addScalar("department_name", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		
		return query.list(); 
	}
	
	public List<Object[]> getDepartmentsByAlertId(Long alertId){
		Query query = getSession().createQuery("select distinct model.govtDepartment.govtDepartmentId," +
											" model.govtDepartment.departmentName" +
											" from Alert model" +
											" where model.alertId = :alertId" +
											" and model.isDeleted = 'N'");
		query.setParameter("alertId", alertId);
		return query.list();
	}//comp
	public List<Object[]> getTotalAlertByStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,Long deptId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");  
		queryStr.append(" A.alert_id as alert_id, " +//0
				        " A.created_time as created_time, " +//1
				        " A.updated_time as updated_time, " +//2
				        " A.alert_status_id as alert_status_id, " +//3
				        " ALTS.alert_status as alert_status, " +//4
				        " A.alert_category_id as alert_category_id, " +//5
				        " AC.category as category, " +//6
				        " AIS.alert_impact_scope_id as alert_impact_scope_id, " +//7
				        " AIS.impact_scope as impact_scope, " +//8
				        " A.title as title, " +//9
				        " C.name as name, " +//10
				        " D.district_name as district_name, " +//11
				        " A.alert_source_id as alert_source_id, " +//12
				        " ALTSRC.source as source, " +//13
				        " 0 as edition_type_id, " +//14
				        " '' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name," +//19
				        " S.state_name  as stateName, " +//20
				        " T.tehsil_name as tehsilName, " +//21
				        " P.panchayat_name as panchayatName, " +//22
				        " LEB.name as localElectionBodyNeme, "+ //23
						" ALTSVR.severity_color as severityColor, " +//24
						" ALTS.alert_color as color"); //25  
		queryStr.append(" from alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join alert_source ALTSRC on ALTSRC.alert_source_id = A.alert_source_id ");
		queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");
		queryStr.append(" left outer join alert_severity ALTSVR on ALTSVR.alert_severity_id = A.alert_severity_id ");//
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");
		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");
		queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");//
		queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");//
		queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");//
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and ");
		if(deptId != null && deptId.longValue() > 0 ){
			queryStr.append(" A.govt_department_id =:deptId and ");
		}else{
			if(deptIdList != null && deptIdList.size() > 0)
				queryStr.append(" A.govt_department_id in (:deptIdList) and ");
		}
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		
		if(fromDate != null && toDate != null)
			queryStr.append(" (date(A.created_time) between :fromDate and :toDate) and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		//queryStr.append(" AND ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) ) ");
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) )");
		else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and A.alert_status_id = :statusId ; ");
		}
		
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)//0
				.addScalar("created_time", Hibernate.STRING)//1
				.addScalar("updated_time", Hibernate.STRING)//2
				.addScalar("alert_status_id", Hibernate.LONG)//3
				.addScalar("alert_status", Hibernate.STRING)//4
				.addScalar("alert_category_id", Hibernate.LONG)//5
				.addScalar("category", Hibernate.STRING)//6
				.addScalar("alert_impact_scope_id", Hibernate.LONG)//7
				.addScalar("impact_scope", Hibernate.STRING)//8
				.addScalar("title", Hibernate.STRING)//9
				.addScalar("name", Hibernate.STRING)//10
				.addScalar("district_name", Hibernate.STRING)//11
				.addScalar("alert_source_id", Hibernate.LONG)//12
				.addScalar("source", Hibernate.STRING)//13
				.addScalar("edition_type_id", Hibernate.LONG)//14
				.addScalar("edition_type", Hibernate.STRING)//15
				.addScalar("edition_id", Hibernate.LONG)//16
				.addScalar("edition_alias", Hibernate.STRING)//17
				.addScalar("tv_news_channel_id", Hibernate.LONG)//18
				.addScalar("channel_name", Hibernate.STRING)//19
				.addScalar("stateName", Hibernate.STRING)//20
				.addScalar("tehsilName", Hibernate.STRING)//21
				.addScalar("panchayatName", Hibernate.STRING)//22
				.addScalar("localElectionBodyNeme", Hibernate.STRING)//23
				.addScalar("severityColor", Hibernate.STRING)//24
				.addScalar("color", Hibernate.STRING);//25
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		
		
		if(deptId != null && deptId.longValue() > 0 ){
			query.setParameter("deptId", deptId);
		}else{
			if(deptIdList != null && deptIdList.size() > 0){
				query.setParameterList("deptIdList", deptIdList);
			}
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId", statusId);
		}
		
		return query.list(); 
	}
	public List<Object[]> getNewsPapaerList(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct alert.edition.newsPaper.newsPaperId, alert.edition.newsPaper.newsPaper from Alert alert order by alert.edition.newsPaper.newsPaper ");
		Query query = getSession().createQuery(queryStr.toString());  
		return query.list();
	}
	public Long getAlertTypeByAlertTypeId(Long alertId){
		Query query = getSession().createQuery("select model.alertType.alertTypeId from Alert model where model.isDeleted='N' and model.alertId=:alertId");
		query.setParameter("alertId", alertId);
		return (Long)query.uniqueResult();
	}
	public int setDepartmentOfAlert(Long organizationId,String isMultiple,Long alertId){ //Like saving Not For Updation
		Query query = getSession().createQuery(" update Alert model set model.govtDepartmentId =:organizationId ,model.isMultiple =:isMultiple  where model.alertId = :alertId ");
		query.setParameter("alertId", alertId);
		query.setParameter("organizationId", organizationId);
		query.setParameter("isMultiple", isMultiple);
		return query.executeUpdate();
	}
	public Object[] getAlertStatus(Long alertId){
		Query query = getSession().createQuery(" select model.alertStatus.alertStatusId,model.alertStatus.alertStatus from Alert model where model.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return (Object[]) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictWiseTotalAlertsForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.govtDepartment.govtDepartmentId," +
						" model.govtDepartment.departmentName," +
						" model.userAddress.district.districtId," +
						" model.userAddress.district.districtName," +
						" count(distinct model.alertId)" +
						" from Alert model " +
						" left join model.edition edition " );
		
		//sb.append(" left join model.tvNewsChannel tvNewsChannel " );
		
		sb.append(" where model.isDeleted = 'N' " +
						" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		
		if(deptIds != null && deptIds.size() > 0 ){
			sb.append(" and model.govtDepartment.govtDepartmentId in(:deptIds) ");
		}
		if(paperIds != null && paperIds.size() > 0){
			sb.append(" and ((edition.newsPaper.newsPaperId in(:paperIds)) ");
		}
		if(channelIds != null && channelIds.size() > 0){ 
			sb.append(" or (model.tvNewsChannel.tvNewsChannelId in(:channelIds))) "); 
		}
		if(fromDate != null && toDate != null){ 
			sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				sb.append(" and model.userAddress.state.stateId =:stateId ");
			}else if(stateId.longValue() == 36L){
				sb.append(" and model.userAddress.state.stateId =:stateId ");
			}/*else if(stateId.longValue() == 0L){
				sb.append(" and model.userAddress.state.stateId in (1,36) ");
			}*/
		}
		sb.append(" group by model.govtDepartmentId, model.userAddress.district.districtId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(deptIds != null && deptIds.size() > 0 ){
			query.setParameterList("deptIds",deptIds);
		}
		if(channelIds != null && channelIds.size() > 0){ 
			query.setParameterList("channelIds",channelIds);
		}
		if(paperIds != null && paperIds.size() > 0 ){
			query.setParameterList("paperIds",paperIds);
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				query.setParameter("stateId",stateId);
			}else if(stateId.longValue() == 36L){
				query.setParameter("stateId",stateId);
			}/*else if(stateId.longValue() == 0L){
				query.setParameter("stateId", stateId);
			}*/
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);   
		}
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getStatusWiseTotalCountsForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select  ASTS.alert_status_id, ASTS.alert_status, count(distinct A.alert_id),A.alert_category_id,A.govt_department_id " +
				" from alert_department_status ADS, alert_status ASTS,user_address UA,state S,alert A " +
				" left outer join editions E on A.edition_id=E.edition_id  ");
	
		sb.append(" where A.alert_status_id=ADS.alert_status_id and" +
				"  ADS.alert_status_id=ASTS.alert_status_id and " +
				" A.address_id =UA.user_address_id and " +
				" UA.state_id = S.state_id " +
				"  and A.is_deleted='N' and " +
				" A.alert_type_id in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") and ");
		
		sb.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") " );
		
		if(deptIds != null && deptIds.size() > 0 ){
			sb.append(" and A.govt_department_id in(:deptIds) ");
		}
		if(paperIds != null && paperIds.size() > 0 ){
			sb.append(" and ((E.news_paper_id in(:paperIds)) ");
		}
		if(channelIds != null && channelIds.size() > 0){
			sb.append(" or (A.tv_news_channel_id in(:channelIds)))");
		}
		if(fromDate != null && toDate != null){ 
			sb.append("  and date(A.created_time) between :fromDate and :toDate ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				sb.append(" and S.state_id =:stateId ");
			}else if(stateId.longValue() == 36L){
				sb.append(" and S.state_id =:stateId ");
			}/*else if(stateId.longValue() == 0L){
				sb.append(" and model.userAddress.state.stateId in (1,36) ");
			}*/
		}
		sb.append("  group by A.govt_department_id,A.alert_category_id,ADS.alert_status_id ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		if(deptIds != null && deptIds.size() > 0 ){
			query.setParameterList("deptIds",deptIds);
		}
		if(channelIds != null && channelIds.size() > 0){ 
			query.setParameterList("channelIds",channelIds);
		}
		if(paperIds != null && paperIds.size() > 0 ){
			query.setParameterList("paperIds",paperIds);
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				query.setParameter("stateId",stateId);
			}else if(stateId.longValue() == 36L){
				query.setParameter("stateId",stateId);
			}/*else if(stateId.longValue() == 0L){
				query.setParameter("stateId", stateId);
			}*/
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);   
		}
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistWiseTotalAlertsStatusForAlert(Date fromDate,Date toDate,Long stateId,List<Long> deptIds,List<Long> paperIds,List<Long> channelIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.userAddress.district.districtId," +
						" model.userAddress.district.districtName," +
						" model.alertStatus.alertStatusId," +
						" model.alertStatus.alertStatus," +
						" count(distinct model.alertId) " +
						" from AlertDepartmentStatus model1,Alert model " +
						" left join model.edition edition " );
		
		//sb.append(" left join model.tvNewsChannel tvNewsChannel " );
		sb.append(" where " +
						" model.alertStatusId = model1.alertStatusId " +
						" and model.alertTypeId = model1.alertTypeId " +
						" and model.isDeleted = 'N' " +
						" and model.alertType.alertTypeId in ("+IConstants.GOVT_CORE_DASHBOARD_ALERT_TYPE_ID+") ");
		
		if(deptIds != null && deptIds.size() > 0 ){
			sb.append(" and model.govtDepartment.govtDepartmentId in(:deptIds) ");
		}
		if(paperIds != null && paperIds.size() > 0 ){
			sb.append(" and ((edition.newsPaper.newsPaperId in(:paperIds)) ");
		}
		if(channelIds != null && channelIds.size() > 0){
			sb.append(" or (model.tvNewsChannel.tvNewsChannelId in(:channelIds))) ");
		}
		if(fromDate != null && toDate != null){ 
			sb.append(" and date(model.createdTime) between :fromDate and :toDate ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				sb.append(" and model.userAddress.state.stateId =:stateId ");
			}else if(stateId.longValue() == 36L){
				sb.append(" and model.userAddress.state.stateId =:stateId ");
			}/*else if(stateId.longValue() == 0L){
				sb.append(" and model.userAddress.state.stateId in (1,36) ");
			}*/
		}
		sb.append(" group by model.userAddress.district.districtId,model.alertStatus.alertStatusId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(deptIds != null && deptIds.size() > 0 ){
			query.setParameterList("deptIds",deptIds);
		}
		if(channelIds != null && channelIds.size() > 0){ 
			query.setParameterList("channelIds",channelIds);
		}
		if(paperIds != null && paperIds.size() > 0 ){
			query.setParameterList("paperIds",paperIds);
		}
	if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				query.setParameter("stateId",stateId);
			}else if(stateId.longValue() == 36L){
				query.setParameter("stateId",stateId);
			}/*else if(stateId.longValue() == 0L){
				query.setParameter("stateId", stateId);
			}*/
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);   
		}
		
		return query.list();
	}
	public List<Object[]> getTotalAlertByStatusForDeptWiseClick(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		queryStr.append(" A.alert_id as alert_id, " +//0
				        " A.created_time as created_time, " +//1
				        " A.updated_time as updated_time, " +//2
				        " A.alert_status_id as alert_status_id, " +//3
				        " ALTS.alert_status as alert_status, " +//4
				        " A.alert_category_id as alert_category_id, " +//5
				        " AC.category as category, " +//6
				        " AIS.alert_impact_scope_id as alert_impact_scope_id, " +//7
				        " AIS.impact_scope as impact_scope, " +//8
				        " A.title as title, " +//9
				        " C.name as name, " +//10
				        " D.district_name as district_name, " +//11
				        " A.alert_source_id as alert_source_id, " +//12
				        " ALTSRC.source as source, " +//13
				        " 0 as edition_type_id, " +//14
				        " '' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name "); //19
		queryStr.append(" from alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join alert_source ALTSRC on ALTSRC.alert_source_id = A.alert_source_id ");
		queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");
		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		
		if(deptIdList != null && deptIdList.size() > 0){
			queryStr.append(" A.govt_department_id in (:deptIdList) and ");
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" (date(A.created_time) between :fromDate and :toDate) and ");
		}
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		if(type.equalsIgnoreCase("PMedia")){
			if(printIdList != null && printIdList.size() > 0){
				queryStr.append(" AND EDS.news_paper_id in (:printIdList) ");
			}
		}else if(type.equalsIgnoreCase("electronic")){
			if(electronicIdList !=null && electronicIdList.size() > 0){
				queryStr.append(" AND TNC.tv_news_channel_id in (:electronicIdList) ");
			}
		}else if(type.equalsIgnoreCase("Totals")){
			queryStr.append(" AND ( EDS.news_paper_id in (:printIdList) or TNC.tv_news_channel_id in (:electronicIdList) )  ");
		}
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and A.alert_status_id = :statusId  ");
		}
		
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)//0
				.addScalar("created_time", Hibernate.STRING)//1
				.addScalar("updated_time", Hibernate.STRING)//2
				.addScalar("alert_status_id", Hibernate.LONG)//3
				.addScalar("alert_status", Hibernate.STRING)//4
				.addScalar("alert_category_id", Hibernate.LONG)//5
				.addScalar("category", Hibernate.STRING)//6
				.addScalar("alert_impact_scope_id", Hibernate.LONG)//7
				.addScalar("impact_scope", Hibernate.STRING)//8
				.addScalar("title", Hibernate.STRING)//9
				.addScalar("name", Hibernate.STRING)//10
				.addScalar("district_name", Hibernate.STRING)//11
				.addScalar("alert_source_id", Hibernate.LONG)//12
				.addScalar("source", Hibernate.STRING)//13
				.addScalar("edition_type_id", Hibernate.LONG)//14
				.addScalar("edition_type", Hibernate.STRING)//15
				.addScalar("edition_id", Hibernate.LONG)//16
				.addScalar("edition_alias", Hibernate.STRING)//17
				.addScalar("tv_news_channel_id", Hibernate.LONG)//18
				.addScalar("channel_name", Hibernate.STRING);//19
				
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId", statusId);
		}
		
		return query.list(); 
	}
	//ccccc
	public List<Object[]> getAlertDtls(Set<Long> alertSet){   
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		queryStr.append(" A.alert_id as alert_id, " +//0
				        " A.created_time as created_time, " +//1
				        " A.updated_time as updated_time, " +//2
				        " A.alert_status_id as alert_status_id, " +//3
				        " ALTS.alert_status as alert_status, " +//4
				        " A.alert_category_id as alert_category_id, " +//5
				        " AC.category as category, " +//6
				        " AIS.alert_impact_scope_id as alert_impact_scope_id, " +//7
				        " AIS.impact_scope as impact_scope, " +//8
				        " A.title as title, " +//9
				        " C.name as name, " +//10
				        " D.district_name as district_name, " +//11
				        " A.alert_source_id as alert_source_id, " +//12
				        " ALTSRC.source as source, " +//13
				        " 0 as edition_type_id, " +//14
				        " '' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name," + //19
				        " S.state_name, "+ //20
					 	" T.tehsil_name as tehsilName, " +//21
				        " P.panchayat_name as panchayatName, " +//22
				        " LEB.name as localElectionBodyNeme, " +//23
				        " ALTSVR.severity_color as severityColor, "+ //24
						" ALTS.alert_color as color"); //25
		queryStr.append(" from alert A ");  
		queryStr.append(" left outer join tv_news_channel TNC on A.tv_news_channel_id = TNC.tv_news_channel_id ");//
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");//
		queryStr.append(" left outer join alert_source ALTSRC on ALTSRC.alert_source_id = A.alert_source_id ");//
		queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");//
		queryStr.append(" left outer join alert_severity ALTSVR on ALTSVR.alert_severity_id = A.alert_severity_id ");//
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");//
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");//
		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");//
		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");//
		queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");//
		queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");//
		queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");//
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");//
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");//
		queryStr.append(" where ");
		queryStr.append(" A.alert_id in (:alertSet) ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)//0
				.addScalar("created_time", Hibernate.STRING)//1
				.addScalar("updated_time", Hibernate.STRING)//2
				.addScalar("alert_status_id", Hibernate.LONG)//3
				.addScalar("alert_status", Hibernate.STRING)//4
				.addScalar("alert_category_id", Hibernate.LONG)//5
				.addScalar("category", Hibernate.STRING)//6
				.addScalar("alert_impact_scope_id", Hibernate.LONG)//7
				.addScalar("impact_scope", Hibernate.STRING)//8
				.addScalar("title", Hibernate.STRING)//9
				.addScalar("name", Hibernate.STRING)//10
				.addScalar("district_name", Hibernate.STRING)//11
				.addScalar("alert_source_id", Hibernate.LONG)//12
				.addScalar("source", Hibernate.STRING)//13
				.addScalar("edition_type_id", Hibernate.LONG)//14
				.addScalar("edition_type", Hibernate.STRING)//15
				.addScalar("edition_id", Hibernate.LONG)//16
				.addScalar("edition_alias", Hibernate.STRING)//17
				.addScalar("tv_news_channel_id", Hibernate.LONG)//18
				.addScalar("channel_name", Hibernate.STRING) // 19
				.addScalar("state_name", Hibernate.STRING)//20
				.addScalar("tehsilName", Hibernate.STRING)//21
				.addScalar("panchayatName", Hibernate.STRING)//22
				.addScalar("localElectionBodyNeme", Hibernate.STRING)//23
				.addScalar("severityColor", Hibernate.STRING)//24
				.addScalar("color", Hibernate.STRING);//25
		if(alertSet != null && alertSet.size() > 0){
			query.setParameterList("alertSet", alertSet);   
		}
		return query.list(); 
	}
	public List<Object[]> getDistrictIdAndNameByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName " +
		  				  " from Alert model " +
		  				  " where model.isDeleted='N' ");
		  if(stateId != null && stateId.longValue() > 0l){
			  if(stateId.longValue() == 1l){
				  queryStr.append(" and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");  
			  }else if(stateId.longValue() == 36l){
				  queryStr.append(" and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");  
			  }
		  }
		  if(fromDate !=null && toDate !=null){
			  queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		  if(alertTypeList != null && alertTypeList.size() > 0){
		    	queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");  
		    }
		    if(editionList != null && editionList.size() > 0){
		    	queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		    }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			      queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			      queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		          queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }
		 
		 queryStr.append(" order by model.userAddress.district.districtId");
		 
	    Query query = getSession().createQuery(queryStr.toString());
	    if(fromDate !=null && toDate !=null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);
	    }
	    if(editionList != null && editionList.size() > 0){
	    	query.setParameterList("editionList", editionList);
	    }
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return query.list();
	}
	public Object[] getStateByStateId(Long stateId){
		Query query = getSession().createQuery("select model.stateId,model.stateName from State model where model.stateId=:stateId");
		query.setParameter("stateId", stateId);
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getPublicationWiseAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String publicationType, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue,List<Long> alertStatusIds,Long disctrictId){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select ");
		if(filterType != null && filterType.equalsIgnoreCase("District")){
			queryStr.append(" model.userAddress.district.districtId, " +//0
					       "  model.userAddress.district.districtName, ");//1
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
			queryStr.append(" model.userAddress.constituency.constituencyId, " +//0
							" model.userAddress.constituency.name, ");//1
		}
		if(publicationType.equalsIgnoreCase("tvChannel")){
			queryStr.append(" tvNewsChannel.tvNewsChannelId,");//2
		}else if(publicationType.equalsIgnoreCase("newsPaper")){
			queryStr.append(" edition.newsPaper.newsPaperId,");//2	
		}
		queryStr.append(" count(distinct model.alertId) " +  //3 
						" from Alert model ");
		
		if(publicationType.equalsIgnoreCase("TvChannel")){
			queryStr.append(" ,TvNewsChannel tvNewsChannel where tvNewsChannel.tvNewsChannelId = model.tvNewsChannelId ");//3
		}else if(publicationType.equalsIgnoreCase("NewsPaper")){
		   queryStr.append(" ,Editions  edition where edition.editionId = model.edition.editionId ");//3
		}
		queryStr.append(" and model.isDeleted ='N' and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
		}
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues)");     
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
		/*	 if(scopeIdList.get(0).longValue() == 8l){
				 queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(locationValue != null && locationValue > 0l){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" and model.userAddress.district.districtId =:locationValue");	
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" and model.userAddress.constituency.constituencyId=:locationValue"); 
			}
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			queryStr.append(" and model.userAddress.district.districtId =:disctrictId");
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			queryStr.append(" and model.alertStatus.alertStatusId in(:alertStatusIds)");	
		}
		if(filterType != null && filterType.equalsIgnoreCase("District")){
			queryStr.append(" group by model.userAddress.district.districtId ");//1
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId  ");//1
		}
		if(publicationType.equalsIgnoreCase("tvChannel")){
			queryStr.append(" ,tvNewsChannel.tvNewsChannelId ");//3
		}else if(publicationType.equalsIgnoreCase("newsPaper")){
		   queryStr.append(" ,edition.newsPaper.newsPaperId ");//3
		}
		if(filterType != null && filterType.equalsIgnoreCase("District")){
			queryStr.append(" order by model.userAddress.district.districtId ");//1
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
			queryStr.append(" order by model.userAddress.constituency.constituencyId  ");//1
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(stateId != null && stateId.longValue() > 0){
		  query.setParameter("stateId", stateId);
		}
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(locationValue != null && locationValue > 0l){
			query.setParameter("locationValue", locationValue);	
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds);	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			query.setParameter("disctrictId", disctrictId);	
		}
		return query.list();   
	}
	
	public Long getGovtDepartmentIdForAlert(Long alertId){
		Query query = getSession().createQuery("select model.govtDepartment.govtDepartmentId" +
												" from Alert model" +
												" where model.isDeleted = 'N'" +
												" and model.alertId = :alertId");
		query.setParameter("alertId", alertId);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getChannelListForUser(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tvNewsChannel.tvNewsChannelId, model.tvNewsChannel.channelName " +
				  " from " +
				  " Alert model " +
				  " where " +
				  " model.isDeleted = 'N' " +
				  " and model.tvNewsChannel.isDeleted = 'N' ");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
   public List<Object[]> getRegionScopesByIds(List<Long> regionScopesIds){
	    Query query = getSession().createQuery(" select model.regionScopesId,model.scope from RegionScopes model " +
	   										   " where model.regionScopesId in (:regionScopesIds)");
	    query.setParameterList("regionScopesIds", regionScopesIds);
	    return query.list();
   }
   public List<Object[]> getImpactLevelByAlertCount(AlertInputsVO alertInputsVO,String impactLevel,String type){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append("select ");
		   if(impactLevel.equalsIgnoreCase("State")){
			   queryStr.append(" model.impactScopeId,");   
		   }else if(impactLevel.equalsIgnoreCase("District")){
			  queryStr.append(" userAddress.district.districtId,userAddress.district.districtName,model.impactScopeId,");
		   }else if(impactLevel.equalsIgnoreCase("Constituency")){
			  queryStr.append(" userAddress.constituency.constituencyId,userAddress.constituency.name,model.impactScopeId,"); 
		   }else if(impactLevel.equalsIgnoreCase("CORPGMC")){
			   queryStr.append(" userAddress.localElectionBody.localElectionBodyId,userAddress.localElectionBody.name,");
		   }else if(type.equalsIgnoreCase("State")){
			   queryStr.append(" userAddress.state.stateId,userAddress.state.stateName,");
		   }
		  queryStr.append(" count(distinct model.alertId)" +
		  				  " from Alert model" +
		  				  " left join model.userAddress userAddress " +
						  " left join userAddress.state state  " +
						  " left join userAddress.district district  " +
						  " left join userAddress.constituency constituency  " +
						  " left join userAddress.tehsil tehsil  " +
						  " left join userAddress.localElectionBody localElectionBody " +
						  " left join localElectionBody.electionType electionType, "+ 
		  				  " AlertDepartmentStatus model1 " +
		  				  " where model1.alertType.alertTypeId = model.alertType.alertTypeId " );
		  queryStr.append(" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
		  				 "  and model.isDeleted='N' ");  
		  queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		 if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
			 queryStr.append(" and userAddress.state.stateId=:stateId ");  
		 }
		 if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
		 }
		 if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			 queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			/* if(alertInputsVO.getImpactLevelIds().get(0).longValue() == 8l){
				 queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
			 queryStr.append(" and model.impactScopeId in (:impactLevelIds)");
		 }
		 if(impactLevel.equalsIgnoreCase("CORPGMC")){
			queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp 
		 }
		 if(type.equalsIgnoreCase("State")){
			 queryStr.append(" and ((electionType.electionTypeId not in ("+IConstants.ELECTION_TYPE_IDS+")) or (userAddress.localElectionBody is null )) ");//Not CORPORATION & Greater Municipal Corp 
		 }
	    if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			queryStr.append(" and userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.MANDAL_LEVEl_ID){
			queryStr.append(" and userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId =:alertTypeId ");
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(alertInputsVO.getDistrictId() != null && alertInputsVO.getDistrictId()> 0l){
			queryStr.append(" and model.userAddress.district.districtId = :districtId ");
		}
		if(alertInputsVO.getConstituencyId() != null && alertInputsVO.getConstituencyId()> 0l){
			queryStr.append(" and model.userAddress.constituency.constituencyId = :constituencyId ");
		}
	
		if(impactLevel.equalsIgnoreCase("State")){
			   queryStr.append(" group by model.impactScopeId");
		}else if(impactLevel.equalsIgnoreCase("District")){
			   queryStr.append(" group by userAddress.district.districtId,model.impactScopeId, " +
			  				  " model.impactScopeId order by " +
			  				  " userAddress.district.districtId,model.impactScopeId ");
		}else if(impactLevel.equalsIgnoreCase("Constituency")){
			  queryStr.append(" group by userAddress.constituency.constituencyId,model.impactScopeId," +
			  				 "  model.impactScopeId order by " +
			  				 "  userAddress.district.districtId,model.impactScopeId"); 
		}else if(impactLevel.equalsIgnoreCase("CORPGMC")){
			  queryStr.append(" group by userAddress.localElectionBody.localElectionBodyId ");
		}else if(type.equalsIgnoreCase("State")){
			   queryStr.append(" group by userAddress.state.stateId ");
		}
	    Query query = getSession().createQuery(queryStr.toString());
	    if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
	     query.setParameter("stateId", alertInputsVO.getStateId());
	    }
	    if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			query.setDate("startDate", alertInputsVO.getFromDate());
			query.setDate("endDate", alertInputsVO.getToDate());
		}
		if(alertInputsVO.getUserAccessLevelValues() != null && alertInputsVO.getUserAccessLevelValues().size() > 0){
			query.setParameterList("userAccessLevelValues", alertInputsVO.getUserAccessLevelValues());
		}
		if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			query.setParameterList("impactLevelIds", alertInputsVO.getImpactLevelIds()); 
		}
		if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
			query.setParameter("alertTypeId", alertInputsVO.getAlertTypeId());  
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			query.setParameterList("editionList", alertInputsVO.getEditionList());  
		}
		if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 query.setParameterList("alertStatusIds", alertInputsVO.getAlertStatusIds()); 
		}
		if(alertInputsVO.getDistrictId() != null && alertInputsVO.getDistrictId()> 0l){
			query.setParameter("districtId", alertInputsVO.getDistrictId());	
		}
		if(alertInputsVO.getConstituencyId() != null && alertInputsVO.getConstituencyId()> 0l){
			query.setParameter("constituencyId", alertInputsVO.getConstituencyId());	
		}
		return query.list();
	}
   public List<Object[]> getLocationWiseAssignedAndInvolveAlertCnt(AlertInputsVO alertInputsVO,String resultType,String type){
		 
          StringBuilder queryStr = new StringBuilder();
		   queryStr.append("select ");
		 if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" model.userAddress.district.districtId,model.userAddress.district.districtName,");   
		 }else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			  queryStr.append(" model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,");
		 }
		 if(type != null && type.equalsIgnoreCase("Status")){
			 queryStr.append(" model.alertStatus.alertStatusId,"); 
		 }
	     queryStr.append(" count(distinct model.alertId)" +
	  				    " from Alert model,AlertDepartmentStatus model1");
	     if(resultType.equalsIgnoreCase("Assigned")){
	    	 queryStr.append(",AlertAssigned ASSG where ASSG.alert.alertId = model.alertId and ASSG.isDeleted='N' ");
	     }else if(resultType.equalsIgnoreCase("Involve")){
	    	 queryStr.append(",AlertCandidate AC where AC.alert.alertId = model.alertId "); 
	     }else if(resultType.equalsIgnoreCase("Total")){
	    	 queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId ");	 
	     }
	  	 if(resultType.equalsIgnoreCase("Involve") || resultType.equalsIgnoreCase("Assigned")){
	  		  queryStr.append(" and model1.alertType.alertTypeId = model.alertType.alertTypeId "); 
	  	 }
	     queryStr.append(" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
	     		        "  and model.isDeleted='N' ");  
	     queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		 if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
			 queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		 if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
		 }
		 if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			 queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			/* if(alertInputsVO.getImpactLevelIds().get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
			 queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	   /* if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.MANDAL_LEVEl_ID){
			queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}*/
	    if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId =:alertTypeId ");
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
	   if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" group by model.userAddress.district.districtId");   
	   }else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		  queryStr.append("  group by model.userAddress.constituency.constituencyId");
	   }
	    if(type != null && type.equalsIgnoreCase("Status")){
			 queryStr.append(",model.alertStatus.alertStatusId "); 
	    }
	    Query query = getSession().createQuery(queryStr.toString());
	    if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
	     query.setParameter("stateId", alertInputsVO.getStateId());
	    }
	    if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			query.setDate("startDate", alertInputsVO.getFromDate());
			query.setDate("endDate", alertInputsVO.getToDate());
		}
		/*if(alertInputsVO.getUserAccessLevelValues() != null && alertInputsVO.getUserAccessLevelValues().size() > 0){
			query.setParameterList("userAccessLevelValues", alertInputsVO.getUserAccessLevelValues());
		}*/
		if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			query.setParameterList("impactLevelIds", alertInputsVO.getImpactLevelIds()); 
		}
		if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
			query.setParameter("alertTypeId", alertInputsVO.getAlertTypeId());  
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			query.setParameterList("editionList", alertInputsVO.getEditionList());  
		}
		if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 query.setParameterList("alertStatusIds", alertInputsVO.getAlertStatusIds()); 
		}
	return query.list();
}
   public List<Object[]> getLocationAndImapctLevelWiseAssignedAndInvolveAlertCnt(AlertInputsVO alertInputsVO,String resultType){
		 
           StringBuilder queryStr = new StringBuilder();
           
		  queryStr.append("select ");
		 if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" model.userAddress.district.districtId,model.userAddress.district.districtName,");   
		 }else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(alertInputsVO.getActivityMemerId() != null && (alertInputsVO.getActivityMemerId().longValue() == 4l || alertInputsVO.getActivityMemerId().longValue() == 5l)){
				  queryStr.append(" model.userAddress.district.districtId,model.userAddress.district.districtName,"); 
			 }else{
				 queryStr.append(" model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,");	 
			 }
		 }
	     queryStr.append(" model.alertImpactScope.alertImpactScopeId,model.alertStatus.alertStatusId,"); 
	     queryStr.append(" count(distinct model.alertId)" +
	  				    " from Alert model,AlertDepartmentStatus model1");
	     if(resultType.equalsIgnoreCase("Assigned")){
	    	 queryStr.append(",AlertAssigned ASSG where ASSG.alert.alertId = model.alertId and ASSG.isDeleted='N' ");
	     }else if(resultType.equalsIgnoreCase("Involve")){
	    	 queryStr.append(",AlertCandidate AC where AC.alert.alertId = model.alertId "); 
	     }else if(resultType.equalsIgnoreCase("Total")){
	    	 queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId ");	 
	     }
	  	 if(resultType.equalsIgnoreCase("Involve") || resultType.equalsIgnoreCase("Assigned")){
	  		  queryStr.append(" and model1.alertType.alertTypeId = model.alertType.alertTypeId "); 
	  	 }
	     queryStr.append(" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " +
	  				     " and model.isDeleted='N'");  
	     queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		 if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
			 queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		 if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");
		 }
		 if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			 queryStr.append(" and date(model.createdTime) between :startDate and :endDate  ");
		 }
		 if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			/* if(alertInputsVO.getImpactLevelIds().get(0).longValue() == 8l){
					queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			 }*/
			 queryStr.append(" and model.alertImpactScope.alertImpactScopeId in (:impactLevelIds)");
		 }
	    if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    	queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.MANDAL_LEVEl_ID){
			queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}
	    if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
	    	queryStr.append(" and model.alertType.alertTypeId =:alertTypeId ");
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
	   if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" group by model.userAddress.district.districtId");   
	   }else if(alertInputsVO.getUserAccessLevelId() != null && alertInputsVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		   if(alertInputsVO.getActivityMemerId() != null && (alertInputsVO.getActivityMemerId().longValue() == 4l || alertInputsVO.getActivityMemerId().longValue() == 5l)){
			   queryStr.append(" group by model.userAddress.district.districtId");   
		   }else{
			   queryStr.append("  group by model.userAddress.constituency.constituencyId");   
		   }
	   }
	    queryStr.append(",model.alertImpactScope.alertImpactScopeId,model.alertStatus.alertStatusId "); 
	    Query query = getSession().createQuery(queryStr.toString());
	    if(alertInputsVO.getStateId() != null && alertInputsVO.getStateId().longValue() > 0l){
	     query.setParameter("stateId", alertInputsVO.getStateId());
	    }
	    if(alertInputsVO.getFromDate() !=null && alertInputsVO.getToDate() !=null){
			query.setDate("startDate", alertInputsVO.getFromDate());
			query.setDate("endDate", alertInputsVO.getToDate());
		}
		if(alertInputsVO.getUserAccessLevelValues() != null && alertInputsVO.getUserAccessLevelValues().size() > 0){
			query.setParameterList("userAccessLevelValues", alertInputsVO.getUserAccessLevelValues());
		}
		if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0){
			query.setParameterList("impactLevelIds", alertInputsVO.getImpactLevelIds()); 
		}
		if(alertInputsVO.getAlertTypeId() != null && alertInputsVO.getAlertTypeId().longValue() > 0){
			query.setParameter("alertTypeId", alertInputsVO.getAlertTypeId());  
		}
		if(alertInputsVO.getEditionList() != null && alertInputsVO.getEditionList().size() > 0){
			query.setParameterList("editionList", alertInputsVO.getEditionList());  
		}
		if(alertInputsVO.getAlertStatusIds() != null && alertInputsVO.getAlertStatusIds().size() >0){
			 query.setParameterList("alertStatusIds", alertInputsVO.getAlertStatusIds()); 
		}
	return query.list();
}
   public List<Object[]> getStateOrGHMCImpcatLevelAlertCntPublicationWise(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String publicationType, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,List<Long> alertStatusIds,Long disctrictId){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select ");
		if(publicationType.equalsIgnoreCase("tvChannel")){
			queryStr.append(" tvNewsChannel.tvNewsChannelId,tvNewsChannel.channelName");//2
		}else if(publicationType.equalsIgnoreCase("newsPaper")){
			queryStr.append(" edition.newsPaper.newsPaperId,edition.newsPaper.newsPaper");//2	
		}
		queryStr.append(",count(distinct model.alertId) from Alert model " );  //3 
		
		if(publicationType.equalsIgnoreCase("TvChannel")){
			queryStr.append(" ,TvNewsChannel tvNewsChannel where tvNewsChannel.tvNewsChannelId = model.tvNewsChannelId ");//3
		}else if(publicationType.equalsIgnoreCase("NewsPaper")){
		   queryStr.append(" ,Editions  edition where edition.editionId = model.edition.editionId ");//3
		}
		queryStr.append(" and model.isDeleted ='N' and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+")");
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
		}
		 if(stateId != null && stateId.longValue() > 0l){
			  queryStr.append(" and model.userAddress.state.stateId=:stateId ");  
		 }
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.state.stateId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:userAccessLevelValues)");     
		}
		if(scopeIdList != null && scopeIdList.size() > 0){
			/*if(scopeIdList.get(0).longValue() == 8l){
				queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
			queryStr.append(" and model.impactScopeId in (:scopeIdList) ");
		}
		if(alertTypeList != null && alertTypeList.size() > 0){
			queryStr.append(" and model.alertType.alertTypeId in (:alertTypeList) ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds)");	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			queryStr.append(" and model.userAddress.district.districtId =:disctrictId");	
		}
		if(publicationType.equalsIgnoreCase("tvChannel")){
			queryStr.append(" group by tvNewsChannel.tvNewsChannelId ");//3
		}else if(publicationType.equalsIgnoreCase("newsPaper")){
		   queryStr.append(" group by edition.newsPaper.newsPaperId ");//3
		}
	
		Query query = getSession().createQuery(queryStr.toString());
		
		if(stateId != null && stateId.longValue() > 0){
		  query.setParameter("stateId", stateId);
		}
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
		if(alertTypeList != null && alertTypeList.size() > 0){
			query.setParameterList("alertTypeList", alertTypeList);  
		}
		if(editionList != null && editionList.size() > 0){
			query.setParameterList("editionList", editionList);  
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0){
			query.setParameterList("alertStatusIds", alertStatusIds);	
		}
		if(disctrictId != null && disctrictId.longValue() > 0){
			query.setParameter("disctrictId", disctrictId);
		}
		return query.list();   
	}
    public List<Object[]> getTotalGovtPendingStatusAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(type.equalsIgnoreCase("Department")){
			queryStr.append(" GD.govt_department_id as govt_department_id, ");
			queryStr.append(" GD.department_name as department_name,GD.color as color, ");
		}else if(type.equalsIgnoreCase("Status")){
			queryStr.append(" A.alert_status_id as alert_status_id, ");
			queryStr.append(" ALTS.alert_status as alert_status,ALTS.alert_color as color, ");
		}
		queryStr.append(" count(distinct A.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and A.alert_status_id = 1 and ");
		queryStr.append(" A.alert_category_id in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") and ");
		if(deptIdList != null && deptIdList.size() > 0)
			queryStr.append(" A.govt_department_id in (:deptIdList) and ");
		if(fromDate != null && toDate != null)
			queryStr.append(" (date(A.created_time) between :fromDate and :toDate) and ");
		if(stateId != null && stateId.longValue() >= 0L){
			if(stateId.longValue() == 1L){
				queryStr.append(" S.state_id = 1 and ");
			}else if(stateId.longValue() == 36L){
				queryStr.append(" S.state_id = 36 and ");
			}else if(stateId.longValue() == 0L){
				queryStr.append(" S.state_id in (1,36) and ");
			}
		}
		queryStr.append(" A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) )");
		
		if(type.equalsIgnoreCase("Department")){
			queryStr.append(" group by GD.govt_department_id");
		}else if(type.equalsIgnoreCase("Status")){
			queryStr.append(" group by ALTS.alert_status_id order by ALTS.alert_status_id ");
		}
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		if(type.equalsIgnoreCase("Department")){
		    query.addScalar("govt_department_id", Hibernate.LONG);
		    query.addScalar("department_name", Hibernate.STRING);
		    query.addScalar("color", Hibernate.STRING);
		}else if(type.equalsIgnoreCase("Status")){
			query.addScalar("alert_status_id", Hibernate.LONG);
			query.addScalar("alert_status", Hibernate.STRING);
			query.addScalar("color",Hibernate.STRING);
		}
		query.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && printIdList.size() > 0){
			query.setParameterList("printIdList", printIdList);   
		}
		if(electronicIdList != null && electronicIdList.size() > 0){
			query.setParameterList("electronicIdList", electronicIdList);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		
		return query.list(); 
	}

    public Integer updateAlertPriority(Long alertId,Long priorityId,Long userId,Date date){
 	   Query query = getSession().createQuery(" update Alert model set model.alertSeverityId=:priorityId,model.updatedBy=:userId,model.updatedTime=:date "
 	   		+ "where model.alertId=:alertId ");
 	   query.setParameter("alertId", alertId);
 	   query.setParameter("priorityId", priorityId);
 	   query.setParameter("userId", userId);
 	   query.setDate("date", date);
 	   return query.executeUpdate();
    }
    
    public Object[] getAlertDetailsForSMS(Long alertId){
    	Query query = getSession().createQuery(" select model.title,model.govtDepartment.govtDepartmentId,model.govtDepartment.departmentName "
    			+ " from Alert model "
    			+ " where model.alertId=:alertId and model.isDeleted='N' ");
    	query.setParameter("alertId", alertId);
    	return (Object[])query.uniqueResult();
    }

    public List<Object[]> getAlertDetials(String mobileNo,Long alertStatusId,Date startDate,Date endDate){
    	StringBuilder sb = new StringBuilder();
    		sb.append("select distinct model.alertId," +
    				" model.title," +
    				" model.impactLevelId," +
    				" date(model.createdTime)," +
    				" model.alertSourceId," +
    				" model.regionScopes.scope," +
    				" model.alertStatus.alertStatusId " +
    				" from Alert model " +
    				" where " );
    				//" model.isDeleted = 'N'");
    		if(mobileNo != null)
    		{
    			sb.append(" model.alertCaller.mobileNo = :mobileNo ");
    		}
    		if(alertStatusId != null && alertStatusId.longValue() > 0l)
    		{
    			sb.append(" and model.alertStatus.alertStatusId = :alertStatusId ");
    		}
    		if(startDate != null && endDate != null)
    		{
    			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
    		}
    		
    		Query query = getSession().createQuery(sb.toString());
    		if(mobileNo != null)
    			query.setParameter("mobileNo", mobileNo);
    		if(alertStatusId != null && alertStatusId.longValue() > 0l)
    			query.setParameter("alertStatusId", alertStatusId);
    		if(startDate != null && endDate != null)
    		{
    			query.setDate("startDate", startDate);
    			query.setDate("endDate", endDate);
    			
    		}
    		
    		return query.list();
    }
    
    public List<Object[]> getAlertCallerDetails(Long alertId){
    	StringBuilder sb = new StringBuilder();
    	 	sb.append("select distinct model.alertSource.alertSourceId," +
    	 			" model.alertCaller.callerName," +
    	 			" model.alertCaller.address," +
    	 			" model.alertCaller.mobileNo " +
    	 			" from Alert model " +
    	 			" where " );
    	 			//"model.isDeleted = 'N' ");
    	 	if(alertId != null && alertId.longValue() >0l)
    	 	{
    	 		sb.append("  model.alertId = :alertId");
    	 	}
    	 	Query query = getSession().createQuery(sb.toString());
    	 	if(alertId != null && alertId.longValue() >0l)
    	 		query.setParameter("alertId", alertId);
    	 	return query.list();
    	 	
    }
}