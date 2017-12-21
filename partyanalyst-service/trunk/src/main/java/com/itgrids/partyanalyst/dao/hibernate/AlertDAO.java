package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertInputsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AlertDAO extends GenericDaoHibernate<Alert, Long> implements IAlertDAO {
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
		str.append(",govtDepartment.departmentName,alertStatus.color,alertSeverity.alertSeverityId ");//31-32-33
		str.append(" ,hamlet.hamletId, " +
				   " hamlet.hamletName");//35
		
		str.append(" from Alert model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.userAddress.hamlet hamlet ");
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
	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId,Date fromDate2,Date toDate2,Long involvedCadreId,Long impactId,List<Long> consIds)
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
		if(consIds != null && consIds.size() >0l){
			str.append(" and constituency.constituencyId in (:consIds)");
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
		if(consIds != null && consIds.size() >0l){
			query.setParameterList("consIds", consIds);
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
	
	public List<Alert> getAlertDetailsOfNewstypeNew(Long alertCategoryType){
		Query query = getSession().createQuery(" select model from Alert model " +
				"  where " +
				" model.alertCategoryTypeId = :alertCategoryType" +
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
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue,Long disctrictId,List<Long> alertStatusIds,List<Long> locationValues){
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
		}else if(locationValues != null && locationValues.size() > 0){
			queryStr.append(" and constituency.constituencyId in (:locationValues) ");
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
		if(locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);	
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
						" alertSeverity.severityColor, " +//24
						" alertStatus.color ");//25
		queryStr.append(" from Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.alertSeverity alertSeverity " +   
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
	public List<Object[]> getPartyCommitteeTypeAlertDtls(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
		 if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 queryStr.append(" and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:enrollementYearIds)");
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
		if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 query.setParameterList("enrollementYearIds", enrollementYearIds);
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

	public List<Object[]> getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpBasicCommiteeIds,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
		 
		 if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
	    	 queryStr.append(" and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:enrollementYearIds)");
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
		 if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 query.setParameterList("enrollementYearIds", enrollementYearIds);
	     }
		return query.list();  
	}
	public List<Object[]> getTdpCommitteeRolesByAlertCnt(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> tdpCommitteeLevelIds,Long tdpBasicCommitteeId,String step,List<Long> alertTypeList,List<Long> editionTypeList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
	      
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
	     if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
	    	 queryStr.append(" and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:enrollementYearIds)");
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
		if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			query.setParameterList("enrollementYearIds", enrollementYearIds); 
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
	       queryStr.append(" count(distinct model.alertId)" );
	       if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
	    	   queryStr.append(" ,model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name ");
	       }
	       queryStr.append(" from Alert model" );
	 	   queryStr.append(",AlertDepartmentStatus model1 " );
	 	   queryStr.append(" where   model.isDeleted='N' ");  
	 	   queryStr.append(" and model1.alertType.alertTypeId = model.alertType.alertTypeId and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
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
		  if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
			  queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
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
		}else{
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
		}
		if(editionList != null && editionList.size() > 0){
			queryStr.append(" and model.editionType.editionTypeId in (:editionList) ");
		}
		if(districtId != null && districtId.longValue() > 0){
			queryStr.append(" and model.userAddress.district.districtId=:districtId ");
		}
	    queryStr.append(" group by model.alertStatus.alertStatusId ");
	    if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
	    	queryStr.append(",model.userAddress.localElectionBody.localElectionBodyId");
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
	public List<Object[]> getMemForPartyCommitDesg(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long tdpBasicCommitteeId, Long designationId, String step,List<Long> alertTypeList, List<Long> editionList,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
		 if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 queryStr.append(" and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:enrollementYearIds)");
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
		if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 query.setParameterList("enrollementYearIds", enrollementYearIds);
		}
	    return query.list();    
	}//imp
	public List<Object[]> getAlertDtlsAssignedByPartyCommite(Long userAccessLevelId, List<Long> userAccessLevelValues, Long stateId, List<Long> impactLevelIds, Date fromDate, Date toDate, List<Long> tdpCommitteeLevelIds, Long cadreId, Long tdpBasicCommitteeId, Long designationId,List<Long> alertStatusIds,List<Long> alertTypeList,List<Long> editionList,Long districtId,List<Long> enrollementYearIds){
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
		 if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 queryStr.append(" and model1.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:enrollementYearIds)");
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
		if (enrollementYearIds != null && enrollementYearIds.size() > 0) {
			 query.setParameterList("enrollementYearIds", enrollementYearIds);  
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
		Long constituencyId,List<Long> alertStatusIds,String publicationType,Long publicationId,Long localElectionBodyId,String locationLevel,String type,List<Long> constituencyList){
		
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
					" alertSeverity.severityColor, " +//24
					" alertStatus.color "); //25
		 queryStr.append(" from " +
	                	" Alert model " +
	                	" left join model.alertCategory alertCategory " +
	   	   			 	" left join model.alertSource alertSource " +
	   	   			 	" left join model.editionType editionType " +
	   	   			 	" left join model.edition edition " +
	   	   			 	" left join model.alertSeverity alertSeverity " +   
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
	    
	    if(constituencyList != null && constituencyList.size() > 0){
	    	queryStr.append(" and constituency.constituencyId in (:constituencyList)");
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
	    if(type != null && type.trim().equalsIgnoreCase("impactScopeWise")){
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactLevelIds) ");	
		}else if(type != null && type.trim().equalsIgnoreCase("locationWise")){
			queryStr.append(" and model.impactLevelId in (:impactLevelIds) ");
		}
	    if(localElectionBodyId != null && localElectionBodyId.longValue() > 0){
	    	if(type != null && type.trim().equalsIgnoreCase("impactScopeWise")){
	    		if(impactLevelIds.get(0).longValue() == 8l){//ghmc
	   	    	  queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
	       	   }
	    	}else if(type != null && type.trim().equalsIgnoreCase("locationWise")){
	    		if(impactLevelIds.get(0).longValue() == 7l){//ghmc
		   	    	  queryStr.append(" and electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
		       	  }
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
	    if(type != null && type.trim().equalsIgnoreCase("impactScopeWise") || type.trim().equalsIgnoreCase("locationWise")){
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
	    if(constituencyList != null && constituencyList.size() > 0){
	    	query.setParameterList("constituencyList", constituencyList);
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
		
		Query query = getSession().createQuery(" select model.alertId,date(model.createdTime),model.alertType.alertType from Alert model " +
				"  where " +
				" model.alertCategoryTypeId = :alertCategoryTypeId " +
				" order by model.alertId desc ");
		
		query.setParameter("alertCategoryTypeId", alertCategoryTypeId);
		
		return query.list();
		
	}
   
   public int updateAlertStatusOfNewsForDelete(Long alertId){
	   Query query = getSession().createQuery(" update Alert model set model.isDeleted = 'M',model.updatedTime=:updatedTime where model.alertId=:alertId ");
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
	   			 " model.alertId =:alertId and model.isDeleted = 'N' ");
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
   public List<Object[]> getAlertDtlsByAlertTypeId(Date fromDate, Date toDate, Long stateId, Long alertTypeId,Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> impactScopeIds,List<Long> alertStatusIds,List<Long> editionList,String type){
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
					" alertSeverity.severityColor, " +//24
					" alertStatus.color "); //25
	 queryStr.append(" from Alert model " +
						" left join model.alertSource alertSource " +
		        		" left join model.editionType editionType " +
		        		" left join model.edition edition " +
		        		" left join model.alertSeverity alertSeverity " +   
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
		if(type != null && type.trim().equalsIgnoreCase("impactScopeWise")){
			/*if(impactScopeIds.get(0).longValue() == 8l){
				queryStr.append(" and model.userAddress.localElectionBody.electionType.electionTypeId in ("+IConstants.ELECTION_TYPE_IDS+") ");//CORPORATION & Greater Municipal Corp
			}*/
			queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactScopeIds) ");	
		}else if(type != null && type.trim().equalsIgnoreCase("locationWise")){
			queryStr.append(" and model.impactLevelId in (:impactScopeIds) ");
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
		if(type != null && type.trim().equalsIgnoreCase("impactScopeWise") || type.trim().equalsIgnoreCase("locationWise")){
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
   
   public List<Object[]> getTotalAlertGroupByStatusForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId,List<Long> constIds){
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
		
		queryStr.append(" where model.alert.isDeleted ='N' and model.isDeleted = 'N'");
		
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
			queryStr.append(" and model.tdpCadreId = :tdpCadreId");
		}
		if(constIds != null && constIds.size() > 0l){
			queryStr.append(" and constituency.constituencyId in (:constIds)");
		}
							
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
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
			query.setParameter("tdpCadreId", tdpCadreId);
		}
		if(constIds != null && constIds.size() > 0l){
			query.setParameterList("constIds", constIds);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
   
   public List<Object[]> getTotalAlertGroupByStatusThenCategoryForCentralMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,Long tdpCadreId,List<Long> consIds){
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
		queryStr.append(" where model.alert.isDeleted ='N' and model.isDeleted = 'N'");
		
		if(tdpCadreId != null && tdpCadreId.longValue() >0l){
			queryStr.append(" and model.tdpCadreId = :tdpCadreId");
		}
		if(consIds != null && consIds.size() > 0l){
			queryStr.append(" and constituency.constituencyId in (:consIds)");
		}
		
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
		if(tdpCadreId != null && tdpCadreId.longValue() >0l){
			query.setParameter("tdpCadreId", tdpCadreId);
		}
		if(consIds != null && consIds.size() > 0l){
			query.setParameterList("consIds", consIds);
		}
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
   
   public List<Object[]> getLocationLevelWiseAlertsDataForCentralMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,List<Long> consIds)
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
		
		if(consIds != null && consIds.size() > 0l){
			str.append(" and constituency.constituencyId in (:consIds)");
		}
		
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
		if(consIds != null && consIds.size() > 0l)
			query.setParameterList("consIds", consIds);
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
	
	public List<Object[]> getTotalAlertGroupByStatusForGovt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,
			List<Long> callCenterList){
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
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) )");
			
			if( callCenterList !=null && !callCenterList.isEmpty() ){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" or A.alert_caller_id is null ");
			}
			queryStr.append(" )");
			
		}
			
		
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
	public List<Object[]> getTotalAlertByStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,Long deptId,List<Long> callCenterList){
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
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) )");
			
			if( callCenterList !=null && !callCenterList.isEmpty() ){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" or A.alert_caller_id is null ");
			}
			queryStr.append(" ) ");
			
		}
			
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		
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
						" ALTS.alert_color as color," +//25
						" GON.officer_name as officerName, " + //26
	                    " GDWL.location_name as officerLocation "); //27
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
		
		queryStr.append(" left outer join alert_assigned_officer_new AAO on AAO.alert_id = A.alert_id ");
	    queryStr.append(" left outer join govt_department_designation_officer_new GDDO on AAO.govt_department_designation_officer_id = GDDO.govt_department_designation_officer_id ");
	    queryStr.append(" left outer join govt_officer_new GON on AAO.govt_officer_id = GON.govt_officer_id ");
	    queryStr.append(" left outer join govt_department_work_location GDWL on GDDO.level_value = GDWL.govt_department_work_location_id ");
	    
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");//
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");//
		queryStr.append(" where ");
		queryStr.append("  A.alert_id in (:alertSet) and AAO.is_deleted='N' ");
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
				.addScalar("color", Hibernate.STRING)//25
				.addScalar("officerName", Hibernate.STRING)//26
                .addScalar("officerLocation", Hibernate.STRING);//27
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
	
	public List<Object[]> getPublicationWiseAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String publicationType, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue,List<Long> alertStatusIds,Long disctrictId,List<Long> constituencyList){
		
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
		if(constituencyList != null && constituencyList.size() > 0){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:constituencyList) "); 
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
		if(constituencyList != null && constituencyList.size() > 0){
			query.setParameterList("constituencyList", constituencyList);
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
				   if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
					   queryStr.append(" model.impactScopeId,");     
				   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
					   queryStr.append(" model.impactLevelId,");   
				   }
			   }else if(impactLevel.equalsIgnoreCase("District")){
				  queryStr.append(" userAddress.district.districtId,userAddress.district.districtName,");
				  if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
					   queryStr.append(" model.impactScopeId,");     
				   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
					   queryStr.append(" model.impactLevelId,");   
				   }
			   }else if(impactLevel.equalsIgnoreCase("Constituency")){
				  queryStr.append(" userAddress.constituency.constituencyId,userAddress.constituency.name,"); 
				  if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
					    queryStr.append(" model.impactScopeId,");     
				   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
					   queryStr.append(" model.impactLevelId,");   
				   }
			   }else if(impactLevel.equalsIgnoreCase("Parliament")){
				   queryStr.append(" userAddress.parliamentConstituency.constituencyId,userAddress.parliamentConstituency.name,"); 
				   if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
					   queryStr.append(" model.impactScopeId,");     
				   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
					   queryStr.append(" model.impactLevelId,");   
				   }
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
						  " left join userAddress.constituency constituency " +
						  " left join userAddress.parliamentConstituency parliamentConstituency " +
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
		 if(alertInputsVO.getImpactLevelIds() != null && alertInputsVO.getImpactLevelIds().size() > 0 ){
			 if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
				 queryStr.append(" and model.impactScopeId in (:impactLevelIds)");
			 }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
				 queryStr.append(" and model.impactLevelId in (:impactLevelIds)");
			 }
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
		if(alertInputsVO.getConstituencyIds() != null && alertInputsVO.getConstituencyIds().size() > 0){
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:constituencyIds) ");
		}
		
		  if(impactLevel.equalsIgnoreCase("State")){
			   if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
				   queryStr.append(" group by model.impactScopeId ");     
			   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
				   queryStr.append("  group by model.impactLevelId ");   
			   }
		   }else if(impactLevel.equalsIgnoreCase("District")){
			  queryStr.append("  group by userAddress.district.districtId,userAddress.district.districtName,");
			  if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
				   queryStr.append(" model.impactScopeId order by userAddress.district.districtId,model.impactScopeId");     
			   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
				   queryStr.append(" model.impactLevelId order by userAddress.district.districtId,model.impactLevelId");   
			   }
		   }else if(impactLevel.equalsIgnoreCase("Constituency")){
			   queryStr.append("  group by userAddress.constituency.constituencyId,userAddress.constituency.name,"); 
			   if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
				   queryStr.append(" model.impactScopeId order by userAddress.constituency.constituencyId,model.impactScopeId");     
			   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
				   queryStr.append(" model.impactLevelId order by userAddress.constituency.constituencyId,model.impactLevelId");   
			   }
		   }else if(impactLevel.equalsIgnoreCase("Parliament")){
			   queryStr.append(" group by userAddress.parliamentConstituency.constituencyId, "); 
			   if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("impactScopeWise")){
				   queryStr.append(" model.impactScopeId order by userAddress.parliamentConstituency.constituencyId,model.impactScopeId "); 
			   }else if(alertInputsVO.getType() != null && alertInputsVO.getType().trim().equalsIgnoreCase("locationWise")){
				   queryStr.append(" model.impactLevelId order by userAddress.parliamentConstituency.constituencyId,model.impactLevelId "); 
			   }
		   }else if(impactLevel.equalsIgnoreCase("CORPGMC")){
			   queryStr.append(" group by userAddress.localElectionBody.localElectionBodyId ");
		   }else if(type.equalsIgnoreCase("State")){
			   queryStr.append("  group by userAddress.state.stateId");
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
		if(alertInputsVO.getConstituencyIds() != null && alertInputsVO.getConstituencyIds().size() > 0){
			query.setParameterList("constituencyIds", alertInputsVO.getConstituencyIds());
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
	    	 if(alertInputsVO.getType() != null && alertInputsVO.getType().equalsIgnoreCase("selectedUserType") 
	    			 && alertInputsVO.getTdpCadreIds() != null && alertInputsVO.getTdpCadreIds().size() >0){
	    		 queryStr.append(" and ASSG.tdpCadre.tdpCadreId in (:tdpCadreIds) ");
	    	 }
	     }else if(resultType.equalsIgnoreCase("Involve")){
	    	 queryStr.append(",AlertCandidate AC where AC.alert.alertId = model.alertId "); 
	    	 if(alertInputsVO.getType() != null && alertInputsVO.getType().equalsIgnoreCase("selectedUserType") 
	    			 && alertInputsVO.getTdpCadreIds() != null && alertInputsVO.getTdpCadreIds().size() >0){
	    		 queryStr.append(" and AC.tdpCadre.tdpCadreId in (:tdpCadreIds) ");
	    	 }
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
		if(alertInputsVO.getType() != null && alertInputsVO.getType().equalsIgnoreCase("selectedUserType") 
   			 && alertInputsVO.getTdpCadreIds() != null && alertInputsVO.getTdpCadreIds().size() >0 && (resultType.equalsIgnoreCase("Assigned") 
   			|| resultType.equalsIgnoreCase("Involve"))){
			 query.setParameterList("tdpCadreIds", alertInputsVO.getTdpCadreIds());
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
    public List<Object[]> getTotalGovtPendingStatusAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,String type,List<Long> calCntrIdList,Long regionScopeId,List<Long> scopeValues,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(type.equalsIgnoreCase("Department")){
			queryStr.append(" GD.govt_department_id as govt_department_id, ");
			queryStr.append(" GD.department_name as department_name,GD.color as color, ");
		}else if(type.equalsIgnoreCase("Status")){
			queryStr.append(" A.alert_status_id as alert_status_id, ");
			queryStr.append(" ALTS.alert_status as alert_status,ALTS.alert_color as color, ");
		}else if(type.equalsIgnoreCase("alertSource")){
			queryStr.append(" AC.alert_category_id as alertCategoryId,AC.category as category,' ' as color," +
					" ALTS.alert_status_id as alertStatusId,ALTS.alert_status as alertStatus,ALTS.alert_color as alertColor,");
		}
		queryStr.append(" count(distinct A.alert_id) as count ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
		queryStr.append(" left outer join  social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id ");
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
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) or(SMT.social_media_type_id in(:socialMediaTypeIds)) or(A.alert_call_center_type_id in(:calCntrIdList)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) ) ");
		}
		/*if(regionScopeId != null && regionScopeId.longValue() ==2l && scopeValues!= null && scopeValues.size() >0){//State
		  queryStr.append(" and UA.state_id in(:scopeValues)");	
		}else if(regionScopeId != null && regionScopeId.longValue() ==3l && scopeValues!= null && scopeValues.size() >0){//district
		  queryStr.append(" and UA.district_id in(:scopeValues)");
		}*/
			
		if(type.equalsIgnoreCase("Department")){
			queryStr.append(" group by GD.govt_department_id");
		}else if(type.equalsIgnoreCase("Status")){
			queryStr.append(" group by ALTS.alert_status_id order by ALTS.alert_status_id ");
		}else if(type.equalsIgnoreCase("alertSource")){
			queryStr.append(" group by AC.alert_category_id,ALTS.alert_status_id order by AC.order asc,ALTS.status_order asc");
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
		}else if(type.equalsIgnoreCase("alertSource")){
			query.addScalar("alertCategoryId", Hibernate.LONG);
			query.addScalar("category", Hibernate.STRING);
			query.addScalar("color",Hibernate.STRING);
			query.addScalar("alertStatusId",Hibernate.LONG);
			query.addScalar("alertStatus",Hibernate.STRING);
			query.addScalar("alertColor",Hibernate.STRING);
		}
		query.addScalar("count", Hibernate.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		/* if(regionScopeId != null && regionScopeId.longValue() ==2l || regionScopeId.longValue() ==3l){
			  if(scopeValues!= null && scopeValues.size() >0){
				  query.setParameterList("scopeValues", scopeValues);
			  }
		 }*/
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
			query.setParameterList("calCntrIdList", calCntrIdList);
			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
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
    			+" , model.alertStatusId, alertCaller.callerName,alertCaller.mobileNo  "
    			+ " from Alert model "
    			+ " left join model.alertCaller alertCaller "
    			+ " where model.alertId=:alertId and model.isDeleted='N' ");
    	query.setParameter("alertId", alertId);
    	return (Object[])query.uniqueResult();
    }
    public List<Object[]> getNoOFAlertCreatedList(Date startDate, Date endDate,Long userId){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select " +
    			" ALT.createdBy " +
    			" , count(distinct ALT.alertId) " +
    			" from " +
    			" Alert ALT, UserLoginDetails ULD " +
    			" where " +
    			" ALT.createdBy = ULD.userId " );
    	queryStr.append(" and ALT.isDeleted = 'N' ");
    	if(startDate != null && endDate != null){
    		queryStr.append(" and date(ALT.createdTime) between :startDate and :endDate ");
    	}
    	if(userId != null && userId.longValue() > 0L){
    		queryStr.append(" and ULD.userId =:userId ");
    	}
    	queryStr.append(" group by ALT.createdBy ");
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(startDate != null && endDate != null){
    		query.setDate("startDate",startDate);
    		query.setDate("endDate",endDate);
    	}
    	if(userId != null && userId.longValue() > 0L){
    		query.setParameter("userId", userId);
    	}
    	return query.list();
    			
    }
    public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(type.equalsIgnoreCase("Department")){
			queryStr.append(" GD.govt_department_id as govt_department_id, ");
			queryStr.append(" GD.department_name as department_name,GD.color as color, ");
		}else if(type.equalsIgnoreCase("Status")){
			queryStr.append(" A.alert_status_id as alert_status_id, ");
			queryStr.append(" ALTS.alert_status as alert_status,ALTS.alert_color as color," +
					" GD.govt_department_id as govt_department_id,GD.department_name as department_name, ");
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
			queryStr.append(" group by ALTS.alert_status_id,GD.govt_department_id order by ALTS.alert_status_id ");
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
			query.addScalar("govt_department_id", Hibernate.LONG);
		    query.addScalar("department_name", Hibernate.STRING);
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
    
    public List<Object[]> getAlertDetials(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long departmentId,Long feedbackStattusId){
    	/*StringBuilder sb = new StringBuilder();
    		sb.append("select distinct model.alertId," +
    				" model.title," +
    				" model.impactLevelId," +
    				" date(model.createdTime)," +
    				" model.alertSourceId," +
    				" model.regionScopes.scope, model.alertStatusId" +
    				" from Alert model " +
    				" where model.isDeleted='N' and model.alertCallerId is not null " );
    				//" model.isDeleted = 'N'");
    		if(mobileNo != null && !mobileNo.isEmpty())
    		{
    			sb.append(" and model.alertCaller.mobileNo = :mobileNo ");
    		}
    		if(alertStatusId != null && alertStatusId.longValue() > 0l)
    		{
    			sb.append(" and model.alertStatus.alertStatusId = :alertStatusId ");
    		}
    		if(startDate != null && endDate != null)
    		{
    			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
    		}
    		*/
    	
    	StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" SELECT  a.alert_id as alert_id ,a.title as title , a.impact_level_id as levelId , date(a.created_time) as time ," +
				"  a.alert_source_id as sourceId,rs.scope as scope ,a.alert_status_id as statusId, gd.department_name as deptName,es.entry_source as source , " +
				" it.issue_type as issueType , ist.issue_type as issueSubType , fs.status as feedbackStattus, d.district_name as districtName," +
				" c.name as cname, t.tehsil_name as tehsilName, p.panchayat_name as pname, h.hamlet_name as hname , leb.name as lname, " +
				" w.name as ward,ac.caller_name as callerName,ac.mobile_no as mobileNo");
		queryStr.append(" from ");
		queryStr.append(" alert a ");
		queryStr.append(" Left Join alert_feedback_status fs on a.alert_feedback_status_id = fs.alert_feedback_status_id ");
		queryStr.append(" Left Join alert_entry_source es on a.alert_entry_source_id = es.alert_entry_source_id ");
		queryStr.append(" Left Join alert_issue_type it on a.alert_issue_type_id = it.alert_issue_type_id ");
		queryStr.append(" Left Join alert_issue_sub_type ist on a.alert_issue_sub_type_id = ist.alert_issue_sub_type_id ");
		queryStr.append(" Left Join region_scopes rs on a.impact_level_id = rs.region_scopes_id ");
		queryStr.append(" LEFT JOIN alert_status as1 on a.alert_status_id = as1.alert_status_id ");
		queryStr.append(" LEFT JOIN alert_caller ac on a.alert_caller_id = ac.alert_caller_id  ");
		queryStr.append(" left join alert_assigned_officer a1 on a.alert_id = a1.alert_id  ");
		queryStr.append(" left join govt_department_designation_officer a2 on a1.govt_department_designation_officer_id= a2.govt_department_designation_officer_id  ");
		queryStr.append(" left join govt_department_designation a3 on a2.govt_department_designation_id = a3.govt_department_designation_id  ");
		queryStr.append(" left join govt_department gd on a3.govt_department_id = gd.govt_department_id  ");
		queryStr.append(" left join user_address ua on a.address_id = ua.user_address_id  ");
		queryStr.append(" left join district d on ua.district_id = d.district_id  ");
		queryStr.append(" left join constituency c on ua.constituency_id = c.constituency_id  ");
		queryStr.append(" left join tehsil t on ua.tehsil_id = t.tehsil_id  ");
		queryStr.append(" left join panchayat p on ua.panchayat_id = p.panchayat_id  ");
		queryStr.append(" left join hamlet h on ua.hamlet_id = h.hamlet_id  ");
		queryStr.append(" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id  ");
		queryStr.append(" left join constituency w on ua.ward = w.constituency_id  ");
		
		
		queryStr.append(" where a.is_deleted='N' and a.alert_caller_id is not null ");
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			queryStr.append(" and a.alert_status_id =:alertStatusId ");
		if(mobileNo != null && !mobileNo.isEmpty()) 
			queryStr.append(" and ac.mobile_no =:mobile_no ");
		if(departmentId != null && departmentId.longValue()>0L)
			queryStr.append(" and a3.govt_department_id =:departmentId ");
		if(startDate != null && startDate != null)
			queryStr.append(" and ( date(a.created_time) between :startDate and :endDate ) ");
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			queryStr.append(" and a.alert_feedback_status_id =:feedbackStattusId ");
		
		queryStr.append(" order BY a.alert_status_id ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)
				.addScalar("title", Hibernate.STRING)
				.addScalar("levelId", Hibernate.LONG)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sourceId", Hibernate.LONG)
				.addScalar("scope", Hibernate.STRING)
				.addScalar("statusId", Hibernate.LONG)
				.addScalar("deptName", Hibernate.STRING)
				.addScalar("source", Hibernate.STRING)
				.addScalar("issueType", Hibernate.STRING)
				.addScalar("issueSubType", Hibernate.STRING)
				.addScalar("feedbackStattus", Hibernate.STRING)
				.addScalar("districtName", Hibernate.STRING)//12
				.addScalar("cname", Hibernate.STRING)
				.addScalar("tehsilName", Hibernate.STRING)
				.addScalar("pname", Hibernate.STRING)
				.addScalar("hname", Hibernate.STRING)
				.addScalar("lname", Hibernate.STRING)
				.addScalar("ward", Hibernate.STRING)
				.addScalar("callerName", Hibernate.STRING)
				.addScalar("mobileNo", Hibernate.STRING);
		
		
		if(departmentId != null && departmentId.longValue()>0L)
			query.setParameter("departmentId", departmentId);
		if(mobileNo != null && !mobileNo.isEmpty()) 
		query.setParameter("mobileNo", mobileNo);
		
		if(startDate != null && startDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			query.setParameter("alertStatusId", alertStatusId);
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			query.setParameter("feedbackStattusId", feedbackStattusId);
		
		return query.list();  
    }
    
    public List<Object[]> getAlertCallerDetails(Long alertId){
    	StringBuilder sb = new StringBuilder();
    	 	sb.append("select distinct model.alert.alertSource.alertSourceId," +
    	 			" model.alertCaller.callerName," +
    	 			" model.alertCaller.address," +
    	 			" model.alertCaller.mobileNo, model.alert.title,model.alert.description , date(model.alert.createdTime)" +
    	 			",model.alertCaller.alertCallerId " +
    	 			" from AlertCallerRelation model   " );
    	 			//"model.isDeleted = 'N' ");
    	 	if(alertId != null && alertId.longValue() >0l)
    	 	{
    	 		sb.append( " where  model.alert.alertId = :alertId");
    	 	}
    	 	Query query = getSession().createQuery(sb.toString());
    	 	if(alertId != null && alertId.longValue() >0l)
    	 		query.setParameter("alertId", alertId);
    	 	return query.list();
    	 	
    }
   
    public List<Object[]> getTotalAlertByStatusNew(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
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
				        " ' ' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name," +//19
				        " S.state_name  as stateName, " +//20
				        " T.tehsil_name as tehsilName, " +//21
				        " P.panchayat_name as panchayatName, " +//22
				        " LEB.name as localElectionBodyNeme, "+ //23
						" ALTSVR.severity_color as severityColor, " +//24
						" ALTS.alert_color as color "); //25  
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
		queryStr.append(" left outer join  social_media_type SMT on SMT.social_media_type_id=A.social_media_type_id");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		//queryStr.append(" join govt_department_scope GDS on GDS.govt_department_scope_id = A.impact_scope_id ");
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
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && calCntrIds !=null && !calCntrIds.isEmpty() && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or(SMT.social_media_type_id in(:socialMediaTypeIds)) or (A.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) )");
		}
			
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and A.alert_status_id = :statusId  ");
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			queryStr.append(" and A.impact_level_id in (:impactLevelIdList) ");
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			queryStr.append(" and ALTSVR.alert_severity_id in (:priorityIdList) ");
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			queryStr.append(" and AC.alert_category_id in (:alertSourceIdList) ");
		}
		if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and  (EDS.news_paper_id  in (:printMediaIdList) or (TNC.tv_news_channel_id in (:electronicMediaIdList) ) ) ");
		}else if(printMediaIdList != null && printMediaIdList.size()>0){
			queryStr.append(" and  EDS.news_paper_id  in (:printMediaIdList) ");
		}else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}
		/*if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}*/
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
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && calCntrIds!= null && !calCntrIds.isEmpty()  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);   
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
			query.setParameterList("calCntrIds", calCntrIds);
			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
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
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			query.setParameterList("impactLevelIdList", impactLevelIdList);
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			query.setParameterList("priorityIdList", priorityIdList);
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			query.setParameterList("alertSourceIdList", alertSourceIdList);
		}
		if(printMediaIdList != null && printMediaIdList.size()>0){
			query.setParameterList("printMediaIdList", printMediaIdList);
		}
		if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			query.setParameterList("electronicMediaIdList", electronicMediaIdList);
		}
		return query.list(); 
	}
    public List<Object[]> getStatusCount(Long locationId,String locationType,String searchType,Date startDate,Date endDate){
    	StringBuilder sb = new StringBuilder();
    		sb.append("select distinct ");
    	if(locationId != null && locationId.longValue() > 0l){
	    	if(searchType != null && searchType.trim().equalsIgnoreCase("rural")){
	    		if(locationType != null && locationType.trim().equalsIgnoreCase("mandals")){
	    			sb.append("t.tehsilId,t.tehsilName,");
	    		}else if(locationType != null && locationType.trim().equalsIgnoreCase("panchayat")){
	    			sb.append("p.panchayatId, p.panchayatName,");
	    		}else if(locationType != null && locationType.trim().equalsIgnoreCase("hamlets")){
					sb.append("h.hamletId, h.hamletName,");
				}
	    	}else if(searchType != null && searchType.trim().equalsIgnoreCase("urban")){
	    		if(locationType != null && locationType.trim().equalsIgnoreCase("muncipality")){
	    			sb.append("leb.localElectionBodyId,leb.name,");
	    		}else if(locationType != null && locationType.trim().equalsIgnoreCase("wards")){
	    			sb.append("w.constituencyId,w.name,");
	    		}
	    	}
    	}else{
			sb.append("d.districtId,d.districtName,");
		}
    	
    		sb.append(" model.alertStatus.alertStatusId,count(model.alertId)" ) ;
    		sb.append("from Alert model " +
    				 " left join model.userAddress.state s "+
		    		 " left join model.userAddress.district d "+
		             " left join model.userAddress.constituency c " +
		             " left join model.userAddress.tehsil t " +
		             " left join model.userAddress.localElectionBody leb " +
		             " left join model.userAddress.panchayat p " +
		             " left join model.userAddress.ward w " +
		             " left join model.userAddress.hamlet h ");
    		sb.append(" where model.isDeleted = 'N' and model.alertCallerId is not null ");
    		
    		if(locationId != null && locationId.longValue() > 0l){
    			if(searchType != null && searchType.trim().equalsIgnoreCase("rural")){
    				if(locationType != null && locationType.trim().equalsIgnoreCase("mandals")){
    					sb.append(" and d.districtId = :locationId");
    				}else if(locationType != null && locationType.trim().equalsIgnoreCase("panchayat")){
    					sb.append(" and t.tehsilId = :locationId");
    				}else if(locationType != null && locationType.trim().equalsIgnoreCase("hamlets")){
    					sb.append(" and p.panchayatId = :locationId");
    				}
    			}else if(searchType != null && searchType.trim().equalsIgnoreCase("urban")){
    				if(locationType != null && locationType.trim().equalsIgnoreCase("muncipality")){
    					sb.append(" and d.districtId = :locationId");
    				}else if(locationType != null && locationType.trim().equalsIgnoreCase("wards")){
    					sb.append(" and leb.localElectionBodyId = :locationId");
    				}
    			}
    		}
    		
    		if(startDate != null && endDate != null){
    			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
    		}
    		
    		if(locationId != null && locationId.longValue() > 0l){
    			if(searchType != null && searchType.trim().equalsIgnoreCase("rural")){
    				if(locationType != null && locationType.trim().equalsIgnoreCase("mandals")){
    					sb.append(" group by t.tehsilId,model.alertStatus.alertStatusId order by t.tehsilName");
    				}else if(locationType != null && locationType.trim().equalsIgnoreCase("panchayat")){
    					sb.append(" group by p.panchayatId,model.alertStatus.alertStatusId order by p.panchayatName");
    				}
    			}else if(searchType != null && searchType.trim().equalsIgnoreCase("urban")){
    				if(locationType != null && locationType.trim().equalsIgnoreCase("muncipality")){
    					sb.append(" group by leb.localElectionBodyId,model.alertStatus.alertStatusId order by leb.name");
    				}else if(locationType != null && locationType.trim().equalsIgnoreCase("wards")){
    					sb.append(" group by w.constituencyId,model.alertStatus.alertStatusId order by w.name");
    				}
    			}
    		}else{
    			sb.append(" group by d.districtId,model.alertStatus.alertStatusId order by d.districtName");
    		}
    		
    		Query query = getSession().createQuery(sb.toString());
    		if(locationId != null && locationId.longValue() > 0l)
    			query.setParameter("locationId", locationId);
    		
    		if(startDate != null && endDate != null){
    			query.setDate("startDate", startDate);
    			query.setDate("endDate", endDate);
    		}
    		
    		return query.list();
    	
    }
    public List<Object[]> getCallerUserAlertDtls(Date fromDate, Date toDate, Long userId){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct ");
    	queryStr.append(" A.alert_id as alertId, 			" +//0
    				 	" A.created_time as createdTime, 	" +//1
    				 	" ALTC.category as category, 		" +//2
	    				" A.title as title, 				" +//3
	    				" A.description as description, 	" +//4
	    				" ALTCALL.caller_name as callerName," +//5
	    				" ALTCALL.mobile_no as mobileNo, 	" +//6
	    				" ALTCALL.email as email, 			" +//7
	    				" GD.department_name as departmentName, " +//8
	    				" AIS.impact_scope as impactScope, 	" +//9
	    				" S.state_name as stateName, 		" +//10
	    				" D.district_name as districtName, 	" +//11
	    				" C.name as conName, 				" +//12
	    				" T.tehsil_name as tehsilName, 		" +//13
	    				" P.panchayat_name as panchayatName," +//14
	    				" LEB.name as lebName , 			" +//15
	    				" GDDN.designation_name as designationName, " +//16
	    				" GOV.mobile_no as officerMobileNo");//17
    	queryStr.append(" from alert A ");
    	queryStr.append(" left outer join alert_assigned_officer_new AAON on A.alert_id = AAON.alert_id ");
    	queryStr.append(" left outer join alert_caller ALTCALL on ALTCALL.alert_caller_id = A.alert_caller_id ");
    	queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");
    	queryStr.append(" left outer join state S on UA.state_id=S.state_id ");
    	queryStr.append(" left outer join district D on D.district_id = UA.district_id ");
    	queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");
    	queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");
    	queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");
    	queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");
    	queryStr.append(" left outer join govt_department GD on GD.govt_department_id = A.govt_department_id ");
    	queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");
    	queryStr.append(" left outer join govt_department_designation_officer_new GDDON on  GDDON.govt_department_designation_officer_id = AAON.govt_department_designation_officer_id ");
    	queryStr.append(" left outer join govt_department_designation_new GDDN on GDDN.govt_department_designation_id = GDDON.govt_department_designation_id");
    	queryStr.append(" left outer join govt_officer_new GOV on GOV.govt_officer_id = AAON.govt_officer_id ");
    	queryStr.append(" join alert_category ALTC on ALTC.alert_category_id = A.alert_category_id ");
    	queryStr.append(" where A.is_deleted = 'N' ");
    	queryStr.append(" and A.created_by = :userId ");
    	if(fromDate != null && toDate != null){
    		queryStr.append(" and Date(A.created_time) between :fromDate and :toDate ;");
    	}
    	
    	SQLQuery query = getSession().createSQLQuery(queryStr.toString());
    	
    	query.addScalar("alertId", Hibernate.LONG);
    	query.addScalar("createdTime", Hibernate.STRING);
    	query.addScalar("category", Hibernate.STRING);
    	query.addScalar("title", Hibernate.STRING);
    	query.addScalar("description", Hibernate.STRING);
    	query.addScalar("callerName", Hibernate.STRING);
    	query.addScalar("mobileNo", Hibernate.STRING);
    	query.addScalar("email", Hibernate.STRING);
    	query.addScalar("departmentName", Hibernate.STRING);
    	query.addScalar("impactScope", Hibernate.STRING);
    	query.addScalar("stateName", Hibernate.STRING);
    	query.addScalar("districtName", Hibernate.STRING);
    	query.addScalar("conName", Hibernate.STRING);
    	query.addScalar("tehsilName", Hibernate.STRING);
    	query.addScalar("panchayatName", Hibernate.STRING);
    	query.addScalar("lebName", Hibernate.STRING);
    	query.addScalar("designationName", Hibernate.STRING);
    	query.addScalar("officerMobileNo", Hibernate.STRING);
    	
    	query.setParameter("userId",userId);
    	if(fromDate != null && toDate != null){
    		query.setDate("fromDate",fromDate);
    		query.setDate("toDate",toDate);
    	}
    	return query.list();
    }

    public List<Object[]> getCallerDetailsForAlerts(List<Long> alertIdsList){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct model.alertId, alertCaller.callerName,alertCaller.mobileNo " +
    			" from Alert model " +
    			" left join model.alertCaller alertCaller  where model.isDeleted='N' and model.alertId in (:alertIdsList) ");
    	Query query = getSession().createQuery(queryStr.toString());
    	query.setParameterList("alertIdsList", alertIdsList);
    	return query.list();
    }
    public List<Object[]> getStatusWiseAlertsCountByDates(Date fromDate ,Date toDate){
    	StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct count(A.alertId) , A.alertStatus.alertStatusId from Alert A " +
				" where A.isDeleted = 'N' and A.alertCallerId is not null ");
		
		if(fromDate!=null && toDate!=null){
			queryStr.append(" and  date(A.createdTime) >=:fromDate and date(A.createdTime) <=:toDate  ");
		}
		
		queryStr.append(" group by A.alertStatus.alertStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(fromDate!=null && toDate!=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
		
    }
    public List<Object[]> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,Date fromDate,Date toDate,Long statusId,Long deptId){
    	StringBuilder sb = new StringBuilder();
    	 	sb.append(" select distinct model.alertId,model.createdTime,model.title,model.description,model.alertIssueType.issueType," +
    	 			" model.alertIssueSubType.issueType,model.alertStatus.alertStatus,'', district.districtName, " +
    	 			" constituency.name, tehsil.tehsilName,panchayat.panchayatName,hamlet.hamletName,leb.name,ward.name,'',model.alertId");
    	 				//9					10					11					12					13		14
    	 	sb.append("	from AlertCallerRelation acr,AlertCaller ac,Alert model " );
    		sb.append(" left join model.userAddress userAddress1 ");
    	 	sb.append(" left join userAddress1.panchayat panchayat ");
    	 	sb.append(" left join userAddress1.ward ward ");
    	 	sb.append(" left join userAddress1.tehsil tehsil ");
    	 	sb.append(" left join userAddress1.constituency constituency ");
    	 	sb.append(" left join userAddress1.ward ward ");
    	 	sb.append(" left join userAddress1.district  district");
    	 	sb.append(" left join userAddress1.state state ");
    		sb.append(" left join userAddress1.hamlet hamlet "); 
    		sb.append(" left join userAddress1.localElectionBody leb ");
    	 		sb.append(" where model.alertId = acr.alertId and" +
    	 				" acr.alertCallerId = ac.alertCallerId and");
    	 	if(mobileNo != null && !mobileNo.isEmpty()){
        	 		sb.append(" ac.mobileNo =:mobileNo and ");
        	 	}
    	 	if(locatoinType != null && locatoinType.equalsIgnoreCase("district")){
    	 		sb.append(" model.userAddress.district.districtId =:locationId and ");
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("tehsil")){
    	 		sb.append(" model.userAddress.tehsil.tehsilId =:locationId and ");
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("panchayat")){
    	 		sb.append(" model.userAddress.panchayat.panchayatId =:locationId and ");
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("hamlet")){
    	 		sb.append(" model.userAddress.hamlet.hamletId =:locationId and ");
    	 	}
    	 	
    	 	if(fromDate != null && toDate != null){
    			sb.append(" (date(model.createdTime) between :fromDate and :toDate) and ");
    		}
    	 	if(statusId != null && statusId.longValue() > 0l){
    	 		sb.append(" model.alertStatus.alertStatusId = :statusId and ");
    	 	}
    	 	if(deptId != null && deptId.longValue() > 0l)
    	 		sb.append(" model.govtDepartmentId = :deptId and");
    	 	sb.append(" model.isDeleted ='N' and acr.isDeleted = 'N' and model.alertCategoryId = 4");
    	 	Query query = getSession().createQuery(sb.toString());
    	 	if(locatoinType != null && locatoinType.equalsIgnoreCase("district")){
    	 		query.setParameter("locationId",locationId);
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("tehsil")){
    	 		query.setParameter("locationId",locationId);
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("panchayat")){
    	 		query.setParameter("locationId",locationId);
    	 	}else if(locatoinType != null && locatoinType.equalsIgnoreCase("hamlet")){
    	 		query.setParameter("locationId",locationId);
    	 	}
    	 	if(mobileNo != null && !mobileNo.isEmpty()){
    	 		query.setParameter("mobileNo",mobileNo);
    	 	}
    	 	if(fromDate != null && toDate != null){
    			query.setDate("fromDate", fromDate);
    			query.setDate("toDate", toDate);
    		}
    	 	if(statusId != null && statusId.longValue() > 0l){
    	 		query.setParameter("statusId", statusId);
    	 	}
    	 	if(deptId != null && deptId.longValue() > 0l)
    	 		query.setParameter("deptId", deptId);
    	 	return query.list();
    	 		 	
    }
    public List<Object[]> getAlertDetials1(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long departmentId,Long feedbackStattusId,Long categoryId){
    	/*StringBuilder sb = new StringBuilder();
    		sb.append("select distinct model.alertId," +
    				" model.title," +
    				" model.impactLevelId," +
    				" date(model.createdTime)," +
    				" model.alertSourceId," +
    				" model.regionScopes.scope, model.alertStatusId" +
    				" from Alert model " +
    				" where model.isDeleted='N' and model.alertCallerId is not null " );
    				//" model.isDeleted = 'N'");
    		if(mobileNo != null && !mobileNo.isEmpty())
    		{
    			sb.append(" and model.alertCaller.mobileNo = :mobileNo ");
    		}
    		if(alertStatusId != null && alertStatusId.longValue() > 0l)
    		{
    			sb.append(" and model.alertStatus.alertStatusId = :alertStatusId ");
    		}
    		if(startDate != null && endDate != null)
    		{
    			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
    		}
    		*/
    	
    	StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" SELECT distinct  a.alert_id as alert_id ,a.title as title , a.impact_level_id as levelId , date(a.created_time) as time ," +
				"  a.alert_source_id as sourceId,rs.scope as scope ,a.alert_status_id as statusId, gd.department_name as deptName,es.entry_source as source , " +
				" it.issue_type as issueType , ist.issue_type as issueSubType , fs.status as feedbackStattus, d.district_name as districtName," +
				" c.name as cname, t.tehsil_name as tehsilName, p.panchayat_name as pname, h.hamlet_name as hname , leb.name as lname, " +
				" w.name as ward,'' as callerName,'' as mobileNo,user.username as username,as1.alert_status as alertStatus");
		queryStr.append(" from ");
		queryStr.append(" user user,alert a ");
		queryStr.append(" Left Join alert_feedback_status fs on a.alert_feedback_status_id = fs.alert_feedback_status_id ");
		queryStr.append(" Left Join alert_entry_source es on a.alert_entry_source_id = es.alert_entry_source_id ");
		queryStr.append(" Left Join alert_issue_type it on a.alert_issue_type_id = it.alert_issue_type_id ");
		queryStr.append(" Left Join alert_issue_sub_type ist on a.alert_issue_sub_type_id = ist.alert_issue_sub_type_id ");
		queryStr.append(" Left Join region_scopes rs on a.impact_level_id = rs.region_scopes_id ");
		queryStr.append(" LEFT JOIN alert_status as1 on a.alert_status_id = as1.alert_status_id ");
		queryStr.append(" LEFT JOIN alert_caller_relation acr on acr.alert_id = a.alert_id  ");
		queryStr.append(" LEFT JOIN alert_caller ac on ac.alert_caller_id = acr.alert_caller_id  ");
		queryStr.append(" left join alert_assigned_officer_new a1 on a.alert_id = a1.alert_id  ");
		queryStr.append(" left join govt_department_designation_officer_new a2 on a1.govt_department_designation_officer_id= a2.govt_department_designation_officer_id  ");
		queryStr.append(" left join govt_department_designation_new a3 on a2.govt_department_designation_id = a3.govt_department_designation_id  ");
		queryStr.append(" left join govt_department gd on a3.govt_department_id = gd.govt_department_id  ");
		queryStr.append(" left join user_address ua on a.address_id = ua.user_address_id  ");
		queryStr.append(" left join district d on ua.district_id = d.district_id  ");
		queryStr.append(" left join constituency c on ua.constituency_id = c.constituency_id  ");
		queryStr.append(" left join tehsil t on ua.tehsil_id = t.tehsil_id  ");
		queryStr.append(" left join panchayat p on ua.panchayat_id = p.panchayat_id  ");
		queryStr.append(" left join hamlet h on ua.hamlet_id = h.hamlet_id  ");
		queryStr.append(" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id  ");
		queryStr.append(" left join constituency w on ua.ward = w.constituency_id  ");
		
		
		queryStr.append(" where a.is_deleted='N' and acr.is_deleted = 'N'" +
						" and a.created_by = user.user_id");
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			queryStr.append(" and a.alert_status_id =:alertStatusId ");
		if(mobileNo != null && !mobileNo.isEmpty()) 
			queryStr.append(" and ac.mobile_no =:mobile_no ");
		if(departmentId != null && departmentId.longValue()>0L)
			queryStr.append(" and a3.govt_department_id =:departmentId ");
		if(startDate != null && startDate != null)
			queryStr.append(" and ( date(a.created_time) between :startDate and :endDate ) ");
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			queryStr.append(" and a.alert_feedback_status_id =:feedbackStattusId ");
		
		if(categoryId !=null && categoryId.longValue()>0l){
			queryStr.append(" and a.alert_category_id =:categoryId ");
		}
		
		
		queryStr.append(" order BY a.alert_status_id ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)
				.addScalar("title", Hibernate.STRING)
				.addScalar("levelId", Hibernate.LONG)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sourceId", Hibernate.LONG)
				.addScalar("scope", Hibernate.STRING)
				.addScalar("statusId", Hibernate.LONG)
				.addScalar("deptName", Hibernate.STRING)
				.addScalar("source", Hibernate.STRING)
				.addScalar("issueType", Hibernate.STRING)
				.addScalar("issueSubType", Hibernate.STRING)
				.addScalar("feedbackStattus", Hibernate.STRING)
				.addScalar("districtName", Hibernate.STRING)//12
				.addScalar("cname", Hibernate.STRING)
				.addScalar("tehsilName", Hibernate.STRING)
				.addScalar("pname", Hibernate.STRING)
				.addScalar("hname", Hibernate.STRING)
				.addScalar("lname", Hibernate.STRING)
				.addScalar("ward", Hibernate.STRING)
				.addScalar("callerName", Hibernate.STRING)
				.addScalar("mobileNo", Hibernate.STRING)
				.addScalar("username", Hibernate.STRING)
				.addScalar("alertStatus", Hibernate.STRING);
		
		
		if(departmentId != null && departmentId.longValue()>0L)
			query.setParameter("departmentId", departmentId);
		if(mobileNo != null && !mobileNo.isEmpty()) 
		query.setParameter("mobileNo", mobileNo);
		
		if(startDate != null && startDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			query.setParameter("alertStatusId", alertStatusId);
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			query.setParameter("feedbackStattusId", feedbackStattusId);
		
		if(categoryId !=null && categoryId.longValue()>0l){
			query.setParameter("categoryId",categoryId);
		}
		
		
		return query.list();  
    }
    @SuppressWarnings("unchecked")
	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long statusId,Date fromDate,Date toDate, List<Long> printIdList, List<Long> electronicIdList,
			List<Long> calCntrIdList,Long stateId,List<Long> socialMediaTypeIds){
    	StringBuilder sb = new StringBuilder();
    	sb.append(" select distinct model.alertId  " +
    			" from Alert model " +
    			" left join model.edition EDS " +
    			" left join model.tvNewsChannel TNC" +
    			" left join model.socialMediaType SMT" +
    			" left join model.alertCallCenterType ACCT " +
    			" where model.isDeleted='N'  ");
    	if(statusId != null && statusId.longValue() > 0){
    		sb.append(" and model.alertStatusId = :statusId " );
    	}
    	if(stateId != null && stateId.longValue() > 0){
    		sb.append(" and model.userAddress.state.stateId=:stateId " );
    	}
    	
    	if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0 && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty()){
             sb.append(" and ( EDS.newsPaperId in (:printIdList)  or (TNC.tvNewsChannelId in (:electronicIdList)) or (SMT.socialMediaTypeId in (:socialMediaTypeIds)) or (ACCT.alertCallCenterTypeId in(:calCntrIdList)))");
        }
    	if(deptIds != null && deptIds.size() > 0){
    		sb.append(" and model.govtDepartmentId in(:deptIds) " );
    	}
    	if(fromDate != null && toDate != null){
    		sb.append(" and date(model.createdTime) between :fromDate and :toDate " );
    	}
    	Query query = getSession().createQuery(sb.toString());
    	if(statusId != null && statusId.longValue() > 0){
    		query.setParameter("statusId", statusId);
    	}
    	
    	if(printIdList != null && printIdList.size() > 0 && electronicIdList != null && electronicIdList.size() > 0  && calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds!= null && !socialMediaTypeIds.isEmpty() ){
   	      query.setParameterList("printIdList", printIdList);
   	      query.setParameterList("electronicIdList", electronicIdList);
   	      query.setParameterList("calCntrIdList", calCntrIdList);
   	      query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
   	    }
    	if(deptIds != null && deptIds.size() > 0){
    		query.setParameterList("deptIds", deptIds);
    	}
    	if(fromDate != null && toDate != null){
    		query.setDate("fromDate", fromDate);
    		query.setDate("toDate", toDate);
    	}
    	if(stateId != null && stateId.longValue() > 0){
    		query.setParameter("stateId", stateId);
    	}
    	return query.list(); 
    	
    }
    //swadhin Grievence
    public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,String step,Long locationId,Long statusId){
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
		}else if(step.equalsIgnoreCase("day")){  
			queryStr.append(" date(model.createdTime), ");
		}
		queryStr.append(" count(distinct model.alertId) " +  //4  
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId");
		}
		if(filterType != null && filterType.equalsIgnoreCase("District") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and district.districtId = :locationId");
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and constituency.constituencyId = :locationId ");
		}
		if(step.equalsIgnoreCase("one")){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId order by district.districtId ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId order by constituency.constituencyId ");
			}
		}else if(step.equalsIgnoreCase("two")){
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId,model.alertStatus.alertStatusId order by district.districtId  ");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId,model.alertStatus.alertStatusId order by constituency.constituencyId ");
			}
		}else{
			if(filterType != null && filterType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId,date(model.createdTime) order by district.districtId, date(model.createdTime) desc");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by constituency.constituencyId,date(model.createdTime) order by constituency.constituencyId ,date(model.createdTime) desc ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();   
	}
	
    public List<Object[]> getDayWiseAlertsCounts(Long departmentId,Date startDate,Date endDate){

    	StringBuilder sb = new StringBuilder();
    		sb.append("select distinct ");
    		sb.append(" model.alertStatus.alertStatusId,model.alertStatus.alertStatus,date(model.createdTime),count(model.alertId)" ) ;
    		sb.append("from Alert model ");
    		sb.append(" where model.isDeleted = 'N' ");
    		
    		if(departmentId != null && departmentId > 0l)
    			sb.append(" and model.govtDepartmentId = :departmentId ");
    		
    		if(startDate != null && endDate != null)
    			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
    		
    		sb.append(" group by model.alertStatus.alertStatusId,date(model.createdTime) order by model.alertStatus.alertStatusId");
    		
    		Query query = getSession().createQuery(sb.toString());
    		
    		if(departmentId != null && departmentId > 0l)
    			query.setParameter("departmentId", departmentId);
    		
    		if(startDate != null && endDate != null){
    			query.setDate("startDate", startDate);
    			query.setDate("endDate", endDate);
    		}
    		
    		return query.list();
    }
    
    public Long getTotalAlertsByStatusIdsAndDates(Date prevDay,Date today,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds){
    	
    	StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct model.alertId) from Alert model where  model.isDeleted = 'N' " );
		
		if(departmentIds != null && departmentIds.size() > 0)
			sb.append(" and model.govtDepartment.govtDepartmentId in  (:departmentIds) ");
		
		if(sourceIds != null && sourceIds.size() > 0)
			sb.append(" and model.alertCategory.alertCategoryId in  (:sourceIds) ");
		
		if(alertStatusIds != null && alertStatusIds.size() > 0)
			sb.append(" and model.alertStatus.alertStatusId in  (:alertStatusIds) ");
		
		if(prevDay != null && today != null)
			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
			
		
		Query query = getSession().createQuery(sb.toString());
		
		if(departmentIds != null && departmentIds.size() > 0)
			query.setParameterList("departmentIds", departmentIds);
		
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		
		if(prevDay != null && today != null){
			query.setDate("startDate", prevDay);
			query.setDate("endDate", today);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0)
			query.setParameterList("alertStatusIds", alertStatusIds);
		
		return (Long)query.uniqueResult();
    }
    
    public List<Object[]> getDifferenceTime(Date fromDate ,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds ){
    	
    	StringBuilder sb = new StringBuilder();
		sb.append(" 	SELECT " +
					" 	A.alert_id, " +
					" 	T.govt_alert_action_type_id, " +
					" 	A.created_time, " +
					" 	T.updated_time, " +
					"	TIMESTAMPDIFF(HOUR,A.created_time,T.updated_time) "+
					"	FROM alert_assigned_officer_tracking_new T,alert A "+
					"	WHERE "+
						"	T.alert_id = A.alert_id AND "+
					"	A.alert_status_id IN  (:alertStatusIds) AND "+
					"	A.govt_department_id IN (:departmentIds) AND"+
					"	A.alert_category_id IN (:sourceIds) AND"+
					"	T.alert_status_id IN (:alertStatusIds) AND"+
					"	T.govt_alert_action_type_id = 6 AND"+
					"	DATE(A.created_time) BETWEEN :fromDate AND :toDate "+
					"	GROUP BY A.alert_id "+
					"	ORDER BY T.alert_id ");
		
		Query query = getSession().createSQLQuery(sb.toString());
				
		if(departmentIds != null && departmentIds.size()>0)
			query.setParameterList("departmentIds", departmentIds);
		if(sourceIds != null && sourceIds.size()>0) 
			query.setParameterList("sourceIds", sourceIds);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0)
			query.setParameterList("alertStatusIds", alertStatusIds);
		
		return query.list();
    }
    
    public List<Object[]> getDepartmentDetailsOfAlert(Long alertId){
   	 Query query = getSession().createQuery(" SELECT distinct " +
	     			" model.govtDepartment.govtDepartmentId," +
	     			" model.govtDepartment.departmentName " +
	     			" FROM Alert model " +
	     			" WHERE " +
	     			" model.alertId =:alertId " +
	     			" and model.isDeleted='N'");
	     	query.setParameter("alertId", alertId);
	     	return query.list();
    }
    public List<Long> getTotalAlertForGrievance(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,Long locationId,Long statusId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertId ");
	
		queryStr.append(" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" left join userAddress.tehsil tehsil " +
						" left join model.userAddress.panchayat panc " +
						" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId ");
		}
		if(filterType != null && filterType.equalsIgnoreCase("District") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and district.districtId = :locationId");
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and constituency.constituencyId = :locationId ");
		}else if(filterType != null && filterType.equalsIgnoreCase("tehsil") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and tehsil.tehsilId = :locationId ");
		}else if(filterType != null && filterType.equalsIgnoreCase("panchayat") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and panc.panchayatId = :locationId ");
		}
		if(locationId != null && locationId.longValue() == 0L){
			queryStr.append(" and district.districtId is not null");
		}
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();   
	}
    public List<Object[]> getAlertDtlsForGrievance(List<Long> alertSet){   
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
						" ALTS.alert_color as color, " +//25
						" AIT.issue_type as issueType, " +//26
						" AIST.issue_type as issueSubType "); //27
		queryStr.append(" from alert A ");  
		queryStr.append(" left outer join tv_news_channel TNC on A.tv_news_channel_id = TNC.tv_news_channel_id ");//
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");//
		queryStr.append(" left outer join alert_source ALTSRC on ALTSRC.alert_source_id = A.alert_source_id ");//
		queryStr.append(" left outer join alert_impact_scope AIS on AIS.alert_impact_scope_id = A.impact_scope_id ");//
		queryStr.append(" left outer join alert_severity ALTSVR on ALTSVR.alert_severity_id = A.alert_severity_id ");//
		queryStr.append(" left outer join user_address UA on A.address_id=UA.user_address_id ");//
		queryStr.append(" left outer join state S on UA.state_id=S.state_id ");//
		queryStr.append(" left outer join district D on D.district_id = UA.district_id ");
		queryStr.append(" left outer join constituency C on C.constituency_id = UA.constituency_id ");
		queryStr.append(" left outer join tehsil T on T.tehsil_id = UA.tehsil_id ");
		queryStr.append(" left outer join panchayat P on P.panchayat_id = UA.panchayat_id ");
		queryStr.append(" left outer join local_election_body LEB on LEB.local_election_body_id = UA.local_election_body ");
		queryStr.append(" left outer join alert_issue_type AIT on AIT.alert_issue_type_id = A.alert_issue_type_id ");
		queryStr.append(" left outer join alert_issue_sub_type AIST on AIST.alert_issue_sub_type_id = A.alert_issue_sub_type_id ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		
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
				.addScalar("color", Hibernate.STRING)//25
				.addScalar("issueType", Hibernate.STRING)//26
				.addScalar("issueSubType", Hibernate.STRING);//27
		if(alertSet != null && alertSet.size() > 0){
			query.setParameterList("alertSet", alertSet);   
		}
		return query.list(); 
	}
    public List<Object[]> getTotalAlertGroupByBellowLocationThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String step, Long locationId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
			queryStr.append(" tehsil.tehsilId, " +//0
				       		" tehsil.tehsilName, ");//1
		}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
			queryStr.append(" panc.panchayatId, " +//0
							" panc.panchayatName, ");//1
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
						" left join userAddress.constituency constituency " +
						" left join userAddress.tehsil tehsil  ");
		if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
			queryStr.append(" left join model.userAddress.panchayat panc ");
		}
		queryStr.append(" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
		if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil") && locationId != null &&  locationId.longValue() > 0L){
			queryStr.append(" and district.districtId = :locationId ");
		}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat") && locationId != null &&  locationId.longValue() > 0L){
			queryStr.append(" and tehsil.tehsilId = :locationId  ");
		}
		
		if(step.equalsIgnoreCase("two")){
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" group by tehsil.tehsilId,model.alertStatus.alertStatusId order by tehsil.tehsilId  ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" group by panc.panchayatId,model.alertStatus.alertStatusId order by panc.panchayatId ");
			}
		}else{
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" group by tehsil.tehsilId order by tehsil.tehsilId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" group by panc.panchayatId order by panc.panchayatId ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}
		
		return query.list();   
	}
    public List<Long> getGrievanceReportDtlsForBellowLocation(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType){
    	StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.alertId ");
	
		queryStr.append(" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" left join userAddress.tehsil tehsil " +
						" left join model.userAddress.panchayat panc " +
						" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");    
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
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId ");
		}
		//Long areaType,Long groupType
		if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase(areaType.trim()) && groupType.trim().equalsIgnoreCase("tehsil")){
			if(locationId != null && locationId.longValue() > 0L){
				queryStr.append(" and tehsil.tehsilId = :locationId ");
			}
		}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase(areaType.trim()) && groupType.trim().equalsIgnoreCase("panchayat")){
			if(locationId != null && locationId.longValue() > 0L){
				queryStr.append(" and panc.panchayatId = :locationId ");
			}
		}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase("district") && areaType.trim().equalsIgnoreCase("tehsil")){
			if(locationId != null && locationId.longValue() > 0L){
				queryStr.append(" and district.districtId = :locationId ");
				queryStr.append(" and tehsil.tehsilId is null ");
			}
		}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase("tehsil") && areaType.trim().equalsIgnoreCase("panchayat")){
			if(locationId != null && locationId.longValue() > 0L){
				queryStr.append(" and tehsil.tehsilId = :locationId ");
				queryStr.append(" and panc.panchayatId is null ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();   
    	
    }
    
    public List<Object[]> getTotalAlertGroupByDateThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String filterType,String step,Long locationId,Long statusId){
		StringBuilder queryStr = new StringBuilder();  
		queryStr.append(" select ");
		
		queryStr.append(" date(model.createdTime), ");//0
		if(step.equalsIgnoreCase("two")){ 
			queryStr.append(" model.alertStatus.alertStatusId," +//1 
							" model.alertStatus.alertStatus,");//2
		}
		queryStr.append(" count(distinct model.alertId) " +  //3 
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId ");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId");
		}
		if(filterType != null && filterType.equalsIgnoreCase("District") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and district.districtId = :locationId");
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency") && locationId != null && locationId.longValue() > 0L){
			queryStr.append(" and constituency.constituencyId = :locationId ");
		}
		
		if(filterType != null && filterType.equalsIgnoreCase("District") && locationId != null && locationId.longValue() == 0L){
			queryStr.append(" and district.districtId is not null");
		}else if(filterType != null && filterType.equalsIgnoreCase("Constituency") && locationId != null && locationId.longValue() == 0L){
			queryStr.append(" and constituency.constituencyId is not null ");
		}
		
		if(step.equalsIgnoreCase("one")){
			queryStr.append(" group by date(model.createdTime) ");
		}else if(step.equalsIgnoreCase("two")){
			queryStr.append(" group by date(model.createdTime),model.alertStatus.alertStatusId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}
		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();   
	}
    public List<Object[]> getDeptList(){
    	Query query = getSession().createQuery("select distinct model.govtDepartment.govtDepartmentId, model.govtDepartment.departmentName from Alert model ");
    	return query.list();
    }
    public List<Object[]> getTotalAlertGroupByCategoryThenStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String step,Long locationId,Long statusId){
		StringBuilder queryStr = new StringBuilder();  
		queryStr.append(" select ");
		
			queryStr.append(" model.alertCategory.alertCategoryId," +//0 
							" model.alertCategory.category,");//1
		if(step.equalsIgnoreCase("two")){ 
			queryStr.append(" model.alertStatus.alertStatusId," +//2  
							" model.alertStatus.alertStatus,");//3
		}
		queryStr.append(" count(distinct model.alertId) " +  //4 
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" , AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
		if(sourceId != null && sourceId.longValue() != 0L){
			queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
		}else{
			queryStr.append(" and model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
		}
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
		
		if(statusId != null && statusId.longValue() > 0L){
			queryStr.append(" and model.alertStatus.alertStatusId = :statusId");
		}


		/*if(locationId != null && locationId.longValue() == 0L){
			queryStr.append(" and district.districtId is not null");
		}else{
			queryStr.append(" and district.districtId =:locationId");
		}*/   
		
		if(step.equalsIgnoreCase("one")){
			queryStr.append(" group by model.alertCategory.alertCategoryId ");
		}else if(step.equalsIgnoreCase("two")){
			queryStr.append(" group by model.alertCategory.alertCategoryId,model.alertStatus.alertStatusId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(sourceId != null && sourceId.longValue() > 0L){
			query.setParameter("sourceId",sourceId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId",locationId);
		}			

		if(statusId != null && statusId.longValue() > 0L){
			query.setParameter("statusId",statusId);
		}
		return query.list();   
	}
    public List<Object[]> getTotalAlertByAlertStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
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
				        " ' ' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name," +//19
				        " S.state_name  as stateName, " +//20
				        " T.tehsil_name as tehsilName, " +//21
				        " P.panchayat_name as panchayatName, " +//22
				        " LEB.name as localElectionBodyNeme, "+ //23
						" ALTSVR.severity_color as severityColor, " +//24
						" ALTS.alert_color as color "); //25  
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
		queryStr.append(" left outer join govt_alert_sub_task ASTS on ASTS.alert_id = A.alert_id ");
		queryStr.append(" left outer join social_media_type sm on A.social_media_type_id = sm.social_media_type_id  ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
				
		//queryStr.append(" join govt_department_scope GDS on GDS.govt_department_scope_id = A.impact_scope_id ");
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
		
		/*if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) )");
		
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" and A.alert_caller_id is null ");
			}
			queryStr.append(" )");
		}*/
			
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or(sm.social_media_type_id in(:socialMediaTypeIds)) or(A.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) ) ");
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			queryStr.append(" and A.alert_status_id in (:statusIdList)  ");
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			queryStr.append(" and A.impact_level_id in (:impactLevelIdList) ");
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			queryStr.append(" and ALTSVR.alert_severity_id in (:priorityIdList) ");
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			queryStr.append(" and AC.alert_category_id in (:alertSourceIdList) ");
		}
		if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and  (EDS.news_paper_id  in (:printMediaIdList) or (TNC.tv_news_channel_id in (:electronicMediaIdList) ) ) ");
		}else if(printMediaIdList != null && printMediaIdList.size()>0){
			queryStr.append(" and  EDS.news_paper_id  in (:printMediaIdList) ");
		}else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}else if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
	 		  queryStr.append(" and sm.social_media_type_id in (:filterSocialMediaIds) ");
	 	}else if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
	 		  queryStr.append(" and A.alert_call_center_type_id in(:filterCallCenterIdList) ");   
	 	 }
		if(locationIdList != null && locationIdList.size()>0){
		if(scopeId != null && scopeId.longValue() == 1L ){
			queryStr.append(" and UA.state_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 2L ){
			queryStr.append(" and UA.district_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 3L ){
			queryStr.append(" and UA.constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 5L ){
			queryStr.append(" and  UA.tehsil_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 6L ){
			queryStr.append(" and UA.panchayat_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 4L ){
			queryStr.append(" and UA.parliament_constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 8L ){
			queryStr.append(" and UA.local_election_body in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 7L ){
			queryStr.append(" and UA.hamlet_id in (:locationIdList) ");
		}
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			queryStr.append(" and ASTS.alert_sub_task_status_id in (:subTaskStatusIdList) ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)//0
				.addScalar("created_time", Hibernate.STRING)//1
				.addScalar("updated_time", Hibernate.STRING)//2
				.addScalar("alert_status_id", Hibernate.LONG)//3 
				//.addScalar("alert_sub_task_status_id", Hibernate.LONG)//3
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
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);   
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("calCntrIds", calCntrIds);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
		    query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
			
		}
		
		if(deptId != null && deptId.longValue() > 0 ){
			query.setParameter("deptId", deptId);
		}else{
			if(deptIdList != null && deptIdList.size() > 0){
				query.setParameterList("deptIdList", deptIdList);
			}
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			query.setParameterList("statusIdList", statusIdList);
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			query.setParameterList("impactLevelIdList", impactLevelIdList);
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			query.setParameterList("priorityIdList", priorityIdList);
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			query.setParameterList("alertSourceIdList", alertSourceIdList);
		}
		if(printMediaIdList != null && printMediaIdList.size()>0){
			query.setParameterList("printMediaIdList", printMediaIdList);
		}
		if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			query.setParameterList("electronicMediaIdList", electronicMediaIdList);
		}
		if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0)
		{
			query.setParameterList("filterSocialMediaIds", filterSocialMediaIds);
		}
		if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
			query.setParameterList("filterCallCenterIdList", filterCallCenterIdList);
		}
		if(locationIdList != null && locationIdList.size()>0){
			query.setParameterList("locationIdList", locationIdList);
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			
			query.setParameterList("subTaskStatusIdList", subTaskStatusIdList);
		}
		return query.list(); 
	}
    
    public List<Long> getPendingAlertCntByAlertCategory(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,Long alertCategoryId,List<Long> calCntrIdList,Long regionScopeId,List<Long> scopeValues,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		queryStr.append(" distinct A.alert_id as alertId ");
		queryStr.append(" from ");
		queryStr.append(" alert A ");
		queryStr.append(" left outer join tv_news_channel TNC on ( A.tv_news_channel_id = TNC.tv_news_channel_id and TNC.is_deleted ='N') ");
		queryStr.append(" left outer join editions EDS on EDS.edition_id =A.edition_id ");
		queryStr.append(" left outer join social_media_type SMT on SMT.social_media_type_id =A.social_media_type_id ");
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
		/*if(regionScopeId != null && regionScopeId.longValue() ==2l && scopeValues!= null && scopeValues.size() >0){//State
			  queryStr.append("  UA.state_id in(:scopeValues) and ");	
		}else if(regionScopeId != null && regionScopeId.longValue() ==3l && scopeValues!= null && scopeValues.size() >0){//district
			  queryStr.append("  UA.district_id in(:scopeValues) and ");
		}*/
			
		if(alertCategoryId != null && alertCategoryId.longValue() > 0l){
			queryStr.append("  AC.alert_category_id=:alertCategoryId and ");
		}
		queryStr.append("  A.alert_type_id in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) ) or(SMT.social_media_type_id in(:socialMediaTypeIds)) or(A.alert_call_center_type_id in(:calCntrIdList)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) ) ");
		}
		SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		  query.addScalar("alertId", Hibernate.LONG);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIdList !=null && !calCntrIdList.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
			query.setParameterList("calCntrIdList", calCntrIdList);
			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
 			query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
 			query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
 			query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
		}
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(alertCategoryId != null && alertCategoryId.longValue() > 0){
			query.setParameter("alertCategoryId", alertCategoryId);   
		}
		/* if(regionScopeId != null && regionScopeId.longValue() ==2l || regionScopeId.longValue() ==3l){
			  if(scopeValues!= null && scopeValues.size() >0){
				  query.setParameterList("scopeValues", scopeValues);
			  }
		 }*/
		return query.list(); 
	}
    public List<Object[]> getTotalAlertBySubTaskStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");  
		queryStr.append(" A.alert_id as alert_id, " +//0
				        " A.created_time as created_time, " +//1		 
		                " A.updated_time as updated_time, " +//2
                        " ASTS.alert_sub_task_status_id as alert_status_id, " +//3
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
				        " ' ' as edition_type, " +//15
				        " EDS.edition_id as edition_id, " +//16
				        " EDS.edition_alias as edition_alias, " +//17
				        " A.tv_news_channel_id as tv_news_channel_id, " +//18
				        " TNC.channel_name as channel_name," +//19
				        " S.state_name  as stateName, " +//20
				        " T.tehsil_name as tehsilName, " +//21
				        " P.panchayat_name as panchayatName, " +//22
				        " LEB.name as localElectionBodyNeme, "+ //23
						" ALTSVR.severity_color as severityColor, " +//24
						" ALTS.alert_color as color "); //25  
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
		queryStr.append(" left outer join govt_alert_sub_task ASTS on ASTS.alert_id = A.alert_id ");
		queryStr.append(" left outer join social_media_type sm on A.social_media_type_id = sm.social_media_type_id  ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		//queryStr.append(" join govt_department_scope GDS on GDS.govt_department_scope_id = A.impact_scope_id ");
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
		
		/*if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) )");
		
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" and A.alert_caller_id is null ");
			}
			queryStr.append(" )");
		}*/
			
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or(sm.social_media_type_id in(:socialMediaTypeIds)) or(A.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) ) ");
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			queryStr.append(" and A.alert_status_id in (:statusIdList)  ");
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			queryStr.append(" and A.impact_level_id in (:impactLevelIdList) ");
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			queryStr.append(" and ALTSVR.alert_severity_id in (:priorityIdList) ");
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			queryStr.append(" and AC.alert_category_id in (:alertSourceIdList) ");
		}
		if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and  (EDS.news_paper_id  in (:printMediaIdList) or (TNC.tv_news_channel_id in (:electronicMediaIdList) ) ) ");
		}else if(printMediaIdList != null && printMediaIdList.size()>0){
			queryStr.append(" and  EDS.news_paper_id  in (:printMediaIdList) ");
		}else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}else if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
	 		  queryStr.append(" and sm.social_media_type_id in (:filterSocialMediaIds) ");
	 	 }else if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
	 		  queryStr.append(" and A.alert_call_center_type_id in(:filterCallCenterIdList) ");  
	 	 }
		/*if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}*/
		if(locationIdList != null && locationIdList.size()>0){
		if(scopeId != null && scopeId.longValue() == 1L ){
			queryStr.append(" and UA.state_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 2L ){
			queryStr.append(" and UA.district_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 3L ){
			queryStr.append(" and UA.constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 5L ){
			queryStr.append(" and  UA.tehsil_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 6L ){
			queryStr.append(" and UA.panchayat_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 4L ){
			queryStr.append(" and UA.parliament_constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 8L ){
			queryStr.append(" and UA.local_election_body in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 7L ){
			queryStr.append(" and UA.hamlet_id in (:locationIdList) ");
		}
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			queryStr.append(" and ASTS.alert_sub_task_status_id in (:subTaskStatusIdList) ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG)//0
				.addScalar("created_time", Hibernate.STRING)//1
				.addScalar("updated_time", Hibernate.STRING)//2
				.addScalar("alert_status_id", Hibernate.LONG)//3 
				//.addScalar("alert_sub_task_status_id", Hibernate.LONG)//3
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
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null  && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);   
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("calCntrIds", calCntrIds);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
			
		}
		
		if(deptId != null && deptId.longValue() > 0 ){
			query.setParameter("deptId", deptId);
		}else{
			if(deptIdList != null && deptIdList.size() > 0){
				query.setParameterList("deptIdList", deptIdList);
			}
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			query.setParameterList("statusIdList", statusIdList);
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			query.setParameterList("impactLevelIdList", impactLevelIdList);
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			query.setParameterList("priorityIdList", priorityIdList);
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			query.setParameterList("alertSourceIdList", alertSourceIdList);
		}
		if(printMediaIdList != null && printMediaIdList.size()>0){
			query.setParameterList("printMediaIdList", printMediaIdList);
		}
		if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			query.setParameterList("electronicMediaIdList", electronicMediaIdList);
		}
		if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
			query.setParameterList("filterSocialMediaIds", filterSocialMediaIds);
		}
		if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
			query.setParameterList("filterCallCenterIdList", filterCallCenterIdList);
		}
		if(locationIdList != null && locationIdList.size()>0){
			query.setParameterList("locationIdList", locationIdList);
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			query.setParameterList("subTaskStatusIdList", subTaskStatusIdList);
		}
		return query.list(); 
	}
    public List<Object[]> getSocialAlertDetials(String mobileNo,Long alertStatusId,Date startDate,Date endDate,Long departmentId,Long feedbackStattusId,
    		Long categoryId,Long userId,Long smTypeId){
    	
    	userId =null;
    	
    	StringBuilder queryStr  = new StringBuilder();
		queryStr.append(" SELECT  a.alert_id as alertId ,a.title as title , a.impact_level_id as levelId , date(a.created_time) as time ," +
				"  a.alert_source_id as sourceId,rs.scope as scope ,a.alert_status_id as statusId, gd.department_name as deptName, fs.status as feedbackStattus, d.district_name as districtName," +
				" c.name as cname, t.tehsil_name as tehsilName, p.panchayat_name as pname, h.hamlet_name as hname , leb.name as lname, " +
				" w.name as ward,'' as callerName,'' as mobileNo,user.username as username,sm.social_media_type_id as smTypeId,sm.type as smType," +
				" a.is_verified as is_verified,s.state_name as state ");
		queryStr.append(" from ");
		queryStr.append(" alert_caller_relation acr,alert_caller ac,user user,alert a ");
		queryStr.append(" Left Join alert_feedback_status fs on a.alert_feedback_status_id = fs.alert_feedback_status_id ");
		queryStr.append(" Left Join region_scopes rs on a.impact_level_id = rs.region_scopes_id ");
		queryStr.append(" LEFT JOIN alert_status as1 on a.alert_status_id = as1.alert_status_id ");
		//queryStr.append(" LEFT JOIN alert_caller ac on a.alert_caller_id = ac.alert_caller_id  ");
		queryStr.append(" left join govt_department gd on a.govt_department_id = gd.govt_department_id  ");
		queryStr.append(" left join user_address ua on a.address_id = ua.user_address_id  ");
		
		queryStr.append(" left join state s on s.state_id = ua.state_id  ");
		queryStr.append(" left join district d on ua.district_id = d.district_id  ");
		queryStr.append(" left join constituency c on ua.constituency_id = c.constituency_id  ");
		queryStr.append(" left join tehsil t on ua.tehsil_id = t.tehsil_id  ");
		queryStr.append(" left join panchayat p on ua.panchayat_id = p.panchayat_id  ");
		queryStr.append(" left join hamlet h on ua.hamlet_id = h.hamlet_id  ");
		queryStr.append(" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id  ");
		queryStr.append(" left join constituency w on ua.ward = w.constituency_id  ");
		queryStr.append(" left join social_media_type sm on a.social_media_type_id = sm.social_media_type_id  ");
		
		
		queryStr.append(" where a.alert_id = acr.alert_id and acr.alert_caller_id=ac.alert_caller_id and acr.is_deleted ='N' and a.is_deleted='N'  " +
						" and a.created_by = user.user_id");
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			queryStr.append(" and a.alert_status_id =:alertStatusId ");
		if(mobileNo != null && !mobileNo.isEmpty()) 
			queryStr.append(" and ac.mobile_no =:mobile_no ");
		if(departmentId != null && departmentId.longValue()>0L)
			queryStr.append(" and a.govt_department_id =:departmentId ");
		if(startDate != null && startDate != null)
			queryStr.append(" and ( date(a.created_time) between :startDate and :endDate ) ");
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			queryStr.append(" and a.alert_feedback_status_id =:feedbackStattusId ");
		
		if(categoryId !=null && categoryId.longValue()>0l){
			queryStr.append(" and a.alert_category_id =:categoryId ");
		}
		if(userId !=null && userId.longValue()>0l){
			queryStr.append(" and a.created_by =:userId ");
		}
		
		if(smTypeId !=null && smTypeId.longValue()>0){
			queryStr.append(" and a.social_media_type_id =:smTypeId ");
		}
		
		queryStr.append(" order BY a.alert_status_id ");
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alertId", Hibernate.LONG)
				.addScalar("title", Hibernate.STRING)
				.addScalar("levelId", Hibernate.LONG)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sourceId", Hibernate.LONG)
				.addScalar("scope", Hibernate.STRING)
				.addScalar("statusId", Hibernate.LONG)
				.addScalar("deptName", Hibernate.STRING)
				.addScalar("feedbackStattus", Hibernate.STRING)
				.addScalar("districtName", Hibernate.STRING)//12
				.addScalar("cname", Hibernate.STRING)
				.addScalar("tehsilName", Hibernate.STRING)
				.addScalar("pname", Hibernate.STRING)
				.addScalar("hname", Hibernate.STRING)
				.addScalar("lname", Hibernate.STRING)
				.addScalar("ward", Hibernate.STRING)
				.addScalar("callerName", Hibernate.STRING)
				.addScalar("mobileNo", Hibernate.STRING)
				.addScalar("username", Hibernate.STRING)
				.addScalar("smTypeId", Hibernate.LONG)
				.addScalar("smType", Hibernate.STRING)
				.addScalar("is_verified", Hibernate.STRING)
				.addScalar("state", Hibernate.STRING);
		
		
		if(departmentId != null && departmentId.longValue()>0L)
			query.setParameter("departmentId", departmentId);
		if(mobileNo != null && !mobileNo.isEmpty()) 
		query.setParameter("mobileNo", mobileNo);
		
		if(startDate != null && startDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(alertStatusId != null && alertStatusId.longValue() > 0l)
			query.setParameter("alertStatusId", alertStatusId);
		if(feedbackStattusId != null && feedbackStattusId.longValue()>0L)
			query.setParameter("feedbackStattusId", feedbackStattusId);
		
		if(categoryId !=null && categoryId.longValue()>0l){
			query.setParameter("categoryId",categoryId);
		}
		if(userId !=null && userId.longValue()>0l){
			query.setParameter("userId",userId);
		}
		if(smTypeId !=null && smTypeId.longValue()>0){
			query.setParameter("smTypeId",smTypeId);
		}
		
		return query.list();  
    }
    public List<Object[]> getAllSocialMediaType(){
    	Query query = getSession().createQuery("select model.socialMediaTypeId,model.type from SocialMediaType model ");
    	return query.list();
    }
    public List<Long> getTotalAlertByPendingStatusNew(Date fromDate, Date toDate, Long stateId, List<Long> printIdList, List<Long> electronicIdList,List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");  
		queryStr.append(" A.alert_id as alert_id  from alert A ");
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
		queryStr.append(" left outer join govt_alert_sub_task ASTS on ASTS.alert_id = A.alert_id ");
		queryStr.append(" left outer join social_media_type sm on A.social_media_type_id = sm.social_media_type_id  ");
		queryStr.append(" join alert_status ALTS on A.alert_status_id=ALTS.alert_status_id ");
		queryStr.append(" join govt_department GD on GD.govt_department_id = A.govt_department_id ");
		queryStr.append(" join alert_category AC on AC.alert_category_id = A.alert_category_id ");
		//queryStr.append(" join govt_department_scope GDS on GDS.govt_department_scope_id = A.impact_scope_id ");
		queryStr.append(" where ");
		queryStr.append(" A.is_deleted='N' and A.alert_status_id = 1 and ");
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
		
		/*if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty()){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList) )");
		
			if( calCntrIds !=null && !calCntrIds.isEmpty() && calCntrIds.get(0).longValue()!=0l ){
				queryStr.append(" or A.alert_caller_id is not null ");
			}else{
				queryStr.append(" and A.alert_caller_id is null ");
			}
			queryStr.append(" )");
		}*/
			
		/*else if(printIdList != null && !printIdList.isEmpty())
			queryStr.append(" and EDS.news_paper_id in (:printIdList)");
		else if(electronicIdList != null && !electronicIdList.isEmpty())
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicIdList)");*/
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			queryStr.append(" and ( EDS.news_paper_id in (:printIdList)  or (TNC.tv_news_channel_id in (:electronicIdList)) or(sm.social_media_type_id in(:socialMediaTypeIds)) or(A.alert_call_center_type_id in(:calCntrIds)) or (A.monday_grievance_type_id in (:mondayGrievanceTypeIds)) or (A.janmabhoomi_type_id in (:janmabhoomiTypeIds))  or (A.special_grievance_type_id in (:specialGrievanceTypeIds)) or (A.general_grievance_type_id in (:generalGrievanceTypeIds)) ) ");
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			queryStr.append(" and A.alert_status_id in (:statusIdList)  ");
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			queryStr.append(" and A.impact_level_id in (:impactLevelIdList) ");
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			queryStr.append(" and ALTSVR.alert_severity_id in (:priorityIdList) ");
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			queryStr.append(" and AC.alert_category_id in (:alertSourceIdList) ");
		}
		if(printMediaIdList != null && printMediaIdList.size()>0 && electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and  (EDS.news_paper_id  in (:printMediaIdList) or (TNC.tv_news_channel_id in (:electronicMediaIdList) ) ) ");
		}else if(printMediaIdList != null && printMediaIdList.size()>0){
			queryStr.append(" and  EDS.news_paper_id  in (:printMediaIdList) ");
		}else if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}else if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
 		  queryStr.append(" and sm.social_media_type_id in (:filterSocialMediaIds) ");
 	   }else if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
 		  queryStr.append(" and A.alert_call_center_type_id in(:filterCallCenterIdList) ");
 		   
 	   }
		/*if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			queryStr.append(" and TNC.tv_news_channel_id in (:electronicMediaIdList) ");
		}*/
		if(locationIdList != null && locationIdList.size()>0){
		if(scopeId != null && scopeId.longValue() == 1L ){
			queryStr.append(" and UA.state_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 2L ){
			queryStr.append(" and UA.district_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 3L ){
			queryStr.append(" and UA.constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 5L ){
			queryStr.append(" and  UA.tehsil_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 6L ){
			queryStr.append(" and UA.panchayat_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 4L ){
			queryStr.append(" and UA.parliament_constituency_id in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 8L ){
			queryStr.append(" and UA.local_election_body in (:locationIdList) ");
		}else if(scopeId != null && scopeId.longValue() == 7L ){
			queryStr.append(" and UA.hamlet_id in (:locationIdList) ");
		}
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			queryStr.append(" and ASTS.alert_sub_task_status_id in (:subTaskStatusIdList) ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("alert_id", Hibernate.LONG);//0
				
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(printIdList != null && !printIdList.isEmpty() && electronicIdList != null && !electronicIdList.isEmpty() &&  calCntrIds !=null && !calCntrIds.isEmpty() && socialMediaTypeIds != null && !socialMediaTypeIds.isEmpty() && mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0 && janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0 && specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0 && generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
			query.setParameterList("printIdList", printIdList);   
			query.setParameterList("electronicIdList", electronicIdList);
			query.setParameterList("calCntrIds", calCntrIds);
			query.setParameterList("socialMediaTypeIds", socialMediaTypeIds);
			query.setParameterList("mondayGrievanceTypeIds", mondayGrievanceTypeIds);
	 		query.setParameterList("janmabhoomiTypeIds", janmabhoomiTypeIds);
	 		query.setParameterList("specialGrievanceTypeIds", specialGrievanceTypeIds);
	 		query.setParameterList("generalGrievanceTypeIds", generalGrievanceTypeIds);
			
		}
		
		if(deptId != null && deptId.longValue() > 0 ){
			query.setParameter("deptId", deptId);
		}else{
			if(deptIdList != null && deptIdList.size() > 0){
				query.setParameterList("deptIdList", deptIdList);
			}
		}
		if(statusIdList != null && statusIdList.size() > 0L){
			query.setParameterList("statusIdList", statusIdList);
		}
		if(impactLevelIdList != null && impactLevelIdList.size()>0){
			query.setParameterList("impactLevelIdList", impactLevelIdList);
		}
		if(priorityIdList != null && priorityIdList.size()>0){
			query.setParameterList("priorityIdList", priorityIdList);
		}
		if(alertSourceIdList != null && alertSourceIdList.size()>0){
			query.setParameterList("alertSourceIdList", alertSourceIdList);
		}
		if(printMediaIdList != null && printMediaIdList.size()>0){
			query.setParameterList("printMediaIdList", printMediaIdList);
		}
		if(electronicMediaIdList != null && electronicMediaIdList.size()>0){
			query.setParameterList("electronicMediaIdList", electronicMediaIdList);
		}
		if(filterSocialMediaIds != null && filterSocialMediaIds.size()>0){
			query.setParameterList("filterSocialMediaIds", filterSocialMediaIds);
		}
		if(filterCallCenterIdList != null && filterCallCenterIdList.size()>0){
			  query.setParameterList("filterCallCenterIdList", filterCallCenterIdList);  
	 	   }
		if(locationIdList != null && locationIdList.size()>0){
			query.setParameterList("locationIdList", locationIdList);
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			
			query.setParameterList("subTaskStatusIdList", subTaskStatusIdList);
		}
		return query.list(); 
	}
    public List<Object[]> getAlertCallCenterType(){
    	Query query = getSession().createQuery("select model.alertCallCenterTypeId,model.type from AlertCallCenterType model ");
    	return query.list();
    }
    public List<Object[]> getTotalAlertGroupByStatusForCentralAreaMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,List<Long> constIds){
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
		
		queryStr.append(" where model.isDeleted = 'N'");
		
		if(constIds != null && constIds.size() > 0l){
			queryStr.append(" and constituency.constituencyId in (:constIds)");
		}
							
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
		
		if(constIds != null && constIds.size() > 0l){
			query.setParameterList("constIds", constIds);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
    public List<Object[]> getTotalAlertGroupByStatusThenCategoryForCentralAreaMembers(Date fromDate, Date toDate, Long stateId,Long alertTypeId,List<Long> consIds){
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
		queryStr.append(" where model.isDeleted = 'N'");
		
		if(consIds != null && consIds.size() > 0l){
			queryStr.append(" and constituency.constituencyId in (:consIds)");
		}
		
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
		
		if(consIds != null && consIds.size() > 0l){
			query.setParameterList("consIds", consIds);
		}
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(alertTypeId !=null && alertTypeId > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		return query.list(); 
	}
    public List<Object[]> getLocationLevelWiseAlertsDataForCentralAreaMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,List<Long> consIds)
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
		str.append(" left join model.alertType  alertType ");
		str.append(" left join model.alertStatus  alertStatus ");
		str.append(" where model.isDeleted = 'N'");
		
		if(consIds != null && consIds.size() > 0l){
			str.append(" and constituency.constituencyId in (:consIds)");
		}
		
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
		
		if(consIds != null && consIds.size() > 0l)
			query.setParameterList("consIds", consIds);
		return query.list();
	}
public List<Object[]> getTotalAlertsDateWise(Date fromDate,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds){
    	
    	StringBuilder sb = new StringBuilder();
		sb.append(" select " +
				  " date(model.createdTime), " +
				  " count(distinct model.alertId) " +
				  " from Alert model " +
				  " where  model.isDeleted = 'N' " );
		
		if(departmentIds != null && departmentIds.size() > 0)
			sb.append(" and model.govtDepartment.govtDepartmentId in  (:departmentIds) ");
		
		if(sourceIds != null && sourceIds.size() > 0)
			sb.append(" and model.alertCategory.alertCategoryId in  (:sourceIds) ");
		
		if(alertStatusIds != null && alertStatusIds.size() > 0)
			sb.append(" and model.alertStatus.alertStatusId in  (:alertStatusIds) ");
		
		if(fromDate != null && toDate != null)
			sb.append(" and (date(model.createdTime) between :startDate and :endDate) ");
			
		sb.append(" group by date(model.createdTime) ");
		Query query = getSession().createQuery(sb.toString());
		
		if(departmentIds != null && departmentIds.size() > 0)
			query.setParameterList("departmentIds", departmentIds);
		
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		
		if(fromDate != null && toDate != null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		if(alertStatusIds != null && alertStatusIds.size() > 0)
			query.setParameterList("alertStatusIds", alertStatusIds);
		
		return query.list();
    }
public List<Object[]> getDateWiseAlert(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,Long locationId){
	
	    StringBuilder queryStr = new StringBuilder();  
	
	    queryStr.append(" select ");
	    queryStr.append(" date(model.createdTime), ");//0
		queryStr.append(" model.alertFeedbackStatus.alertFeedbackStatusId," +//1 
						" model.alertFeedbackStatus.status,");//2
	    queryStr.append(" count(distinct model.alertId) " +  //3 
						" from Alert model " +
						" left join model.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency " +
						" ,AlertDepartmentStatus model1 " +
						" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
						" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
						" and model.isDeleted ='N' " +
						" and model.govtDepartment.govtDepartmentId = :departmentId ");
	    
	    if(alertCategoryId != null && alertCategoryId.longValue() != 0L){
		  queryStr.append(" and model.alertCategory.alertCategoryId = :alertCategoryId");
		}else{
			queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
		}
		
		queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
		
		if(locationId != null && locationId.longValue() > 0){
			queryStr.append(" and district.districtId =:locationId");
		}
		
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
		queryStr.append(" group by date(model.createdTime),model.alertFeedbackStatus.alertFeedbackStatusId ");
		
	    Query query = getSession().createQuery(queryStr.toString());
	
		if(departmentId != null && departmentId.longValue() > 0L){
			query.setParameter("departmentId",departmentId);
		}
		if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
			query.setParameter("alertCategoryId",alertCategoryId);
		}
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		}
		if(locationId != null && locationId.longValue() > 0){
			query.setParameter("locationId",locationId);
		}
		
	return query.list();   
 }
	public List<Object[]> getAlertBasedOnRequiredParameter(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,List<Long> statusIds,String type,Long locationId){
		
	       StringBuilder queryStr = new StringBuilder();  
	
		    queryStr.append(" select ");
		    queryStr.append(" date(model.createdTime), ");//0
		    queryStr.append(" count(distinct model.alertId) " +  //3 
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" ,AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
		    
		    if(alertCategoryId != null && alertCategoryId.longValue() != 0L){
			    queryStr.append(" and model.alertCategory.alertCategoryId = :alertCategoryId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
		    
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			
			if(locationId != null && locationId.longValue() > 0){
				queryStr.append(" and district.districtId =:locationId");
			}
			
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
		    if(statusIds != null && statusIds.size() > 0){
		    	queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");
		    }
			if(type != null && type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" and model.alertFeedbackStatusId is null ");
			}else if(type != null && type.equalsIgnoreCase("reopen")){
				queryStr.append(" and model.alertFeedbackStatusId in (2,3)");
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
			queryStr.append(" group by date(model.createdTime)");
			
		    Query query = getSession().createQuery(queryStr.toString());
		
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
				query.setParameter("alertCategoryId",alertCategoryId);
			}
			if(statusIds != null && statusIds.size() > 0){
				query.setParameterList("statusIds",statusIds);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0){
				query.setParameter("locationId",locationId);
			}
	return query.list();   								
	}
	public List<Long> getFeedbackAlertIds(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,List<Long> statusIds,String type,Long locationId,Long feedBackStatusId,String level){
		
	       StringBuilder queryStr = new StringBuilder();  
	
		    queryStr.append(" select ");
		    queryStr.append(" distinct model.alertId " +  
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" ,AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
		    
		    if(alertCategoryId != null && alertCategoryId.longValue() != 0L){
		    	queryStr.append(" and model.alertCategory.alertCategoryId = :alertCategoryId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
			
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			
			if(locationId != null && locationId.longValue() > 0){
				queryStr.append(" and district.districtId =:locationId");
			}
			if(feedBackStatusId != null && feedBackStatusId.longValue() > 0){
				queryStr.append(" and model.alertFeedbackStatusId =:feedBackStatusId");
			}
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
		    if(statusIds != null && statusIds.size() > 0){
		    	queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");
		    }
			if(type != null && type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" and model.alertFeedbackStatusId is null ");
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
			if(level != null && level.trim().equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null ");
			}else if(level != null && level.trim().equalsIgnoreCase("district")){
				queryStr.append(" and district.districtId is not null ");
			}
		    Query query = getSession().createQuery(queryStr.toString());
		
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
				query.setParameter("alertCategoryId",alertCategoryId);
			}
			if(statusIds != null && statusIds.size() > 0){
				query.setParameterList("statusIds",statusIds);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0){
				query.setParameter("locationId",locationId);
			}
			if(feedBackStatusId != null && feedBackStatusId.longValue() > 0){
				query.setParameter("feedBackStatusId",feedBackStatusId);
			}
	return query.list();   								
	}
	 public List<Object[]> getLocationWisefeedbackAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type, Long locationId,List<Long> statusIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("district")){
				queryStr.append(" district.districtId, " +//0
						        " district.districtName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" tehsil.tehsilId, " +//0
					       		" tehsil.tehsilName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" panc.panchayatId, " +//0
								" panc.panchayatName, ");//1
			}
			if(type.equalsIgnoreCase("feebbackStatus")){
				queryStr.append(" model.alertFeedbackStatus.alertFeedbackStatusId," +//2  
								" model.alertFeedbackStatus.status,");//3
			}else if(type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" 4,'Pending FeedBack',");//2,3
			}else if(type.equalsIgnoreCase("reopen")){
				queryStr.append(" 5,'Reopen',");//3
			}
			
			queryStr.append(" count(distinct model.alertId) " +  //4  
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" left join userAddress.tehsil tehsil  ");
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" left join model.userAddress.panchayat panc ");
			}
			queryStr.append(" , AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			
			  if(statusIds != null && statusIds.size() > 0){
			    	queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");
			    }
				if(type != null && type.equalsIgnoreCase("pendingFeedback")){
					queryStr.append(" and model.alertFeedbackStatusId is null ");
				}else if(type != null && type.equalsIgnoreCase("reopen")){
					queryStr.append(" and model.alertFeedbackStatusId in (2,3)");
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
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and district.districtId = :locationId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and tehsil.tehsilId = :locationId  ");
			}
			
			
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId ");
				if(type.equalsIgnoreCase("feebbackStatus")){
					queryStr.append(" ,model.alertFeedbackStatus.alertFeedbackStatusId order by district.districtId,model.alertFeedbackStatus.alertFeedbackStatusId");
				}
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" group by tehsil.tehsilId ");
				if(type.equalsIgnoreCase("feebbackStatus")){
					queryStr.append(" ,model.alertFeedbackStatus.alertFeedbackStatusId order by tehsil.tehsilId,model.alertFeedbackStatus.alertFeedbackStatusId");
				}
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" group by panc.panchayatId ");
				if(type.equalsIgnoreCase("feebbackStatus")){
					queryStr.append(" ,model.alertFeedbackStatus.alertFeedbackStatusId order by panc.panchayatId,model.alertFeedbackStatus.alertFeedbackStatusId");
				}
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0L){
				query.setParameter("locationId",locationId);
			}
			if(statusIds != null && statusIds.size() > 0){
				query.setParameterList("statusIds",statusIds);
			}
			
			return query.list();   
		}
	 public List<Long> getLocationWiseFeebbackAlertIds(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type, Long locationId,List<Long> statusIds,Long feedBackStatusId,String areaType){
			
		    StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			queryStr.append(" distinct model.alertId " +   
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" left join userAddress.tehsil tehsil" +
							" left join model.userAddress.panchayat panc  ");
			
		
			queryStr.append(" , AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			
			if(feedBackStatusId != null && feedBackStatusId.longValue() > 0){
				queryStr.append(" and model.alertFeedbackStatusId =:feedBackStatusId");
			}
		    if(statusIds != null && statusIds.size() > 0){
		    	queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");
		    }
			if(type != null && type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" and model.alertFeedbackStatusId is null ");
			}else if(type != null && type.equalsIgnoreCase("reopen")){
				queryStr.append(" and model.alertFeedbackStatusId in (2,3)");
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
			//Long areaType,Long groupType
			if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase(areaType.trim()) && groupType.trim().equalsIgnoreCase("tehsil")){
				if(locationId != null && locationId.longValue() > 0L){
					queryStr.append(" and tehsil.tehsilId = :locationId ");
				}
			}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase(areaType.trim()) && groupType.trim().equalsIgnoreCase("panchayat")){
				if(locationId != null && locationId.longValue() > 0L){
					queryStr.append(" and panc.panchayatId = :locationId ");
				}
			}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase("district") && areaType.trim().equalsIgnoreCase("tehsil")){
				if(locationId != null && locationId.longValue() > 0L){
					queryStr.append(" and district.districtId = :locationId ");
					queryStr.append(" and tehsil.tehsilId is null ");
				}
			}else if(areaType != null && !areaType.trim().isEmpty() && areaType.trim().length() > 0 && groupType != null && !groupType.trim().isEmpty() && groupType.trim().length() > 0 && groupType.trim().equalsIgnoreCase("tehsil") && areaType.trim().equalsIgnoreCase("panchayat")){
				if(locationId != null && locationId.longValue() > 0L){
					queryStr.append(" and tehsil.tehsilId = :locationId ");
					queryStr.append(" and panc.panchayatId is null ");
				}
			}
		
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0L){
				query.setParameter("locationId",locationId);
			}
			if(statusIds != null && statusIds.size() > 0){
				query.setParameterList("statusIds",statusIds);
			}
			if(feedBackStatusId != null && feedBackStatusId.longValue() > 0){
				query.setParameter("feedBackStatusId",feedBackStatusId);
			}
			return query.list();   
		}
	 public List<Object[]> getDifferenceDay(Date fromDate ,Date toDate,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertStatusIds ){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" 	SELECT " +
						" 	A.alert_id, " +
						" 	A.created_time, " +
						" 	A.updated_time, " +
						"	TIMESTAMPDIFF(DAY,A.created_time,A.updated_time) "+  
						"	FROM " +
						"	alert A left join user_address UA on A.address_id = UA.user_address_id " +
						" 	left join  state S on UA.state_id = S.state_id " +
						"	left join district D on UA.district_id = D.district_id  "+
						"	WHERE "+
						"	A.alert_status_id IN  (:alertStatusIds) AND "+
						"	A.govt_department_id IN (:departmentIds) AND"+
						"	A.alert_category_id IN (:sourceIds) AND " +
						" 	S.state_id = 1 AND "+
						"	DATE(A.created_time) BETWEEN :fromDate AND :toDate AND " +
						" 	A.is_deleted = 'N'" +
						"	GROUP BY A.alert_id " );
			
			Query query = getSession().createSQLQuery(sb.toString());
					
			if(departmentIds != null && departmentIds.size()>0)
				query.setParameterList("departmentIds", departmentIds);
			if(sourceIds != null && sourceIds.size()>0) 
				query.setParameterList("sourceIds", sourceIds);
			
			if(fromDate != null && toDate != null){
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			if(alertStatusIds != null && alertStatusIds.size() > 0)
				query.setParameterList("alertStatusIds", alertStatusIds);
			
			return query.list();
		}
	 public List<Object[]> getTotalAlertGroupByCategoryThenFeedbackStatus(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String step,Long locationId,Long statusId,String feedbackType,String reopen){
			StringBuilder queryStr = new StringBuilder();  
			queryStr.append(" select ");
			
				queryStr.append(" model.alertCategory.alertCategoryId," +//0 
								" model.alertCategory.category,");//1
			if(step.equalsIgnoreCase("two")){ 
				queryStr.append(" model.alertFeedbackStatus.alertFeedbackStatusId , " +
								" model.alertFeedbackStatus.status ," );//2  
			}
			queryStr.append(" count(distinct model.alertId) " +  //4 
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +     
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" , AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
			if(feedbackType != null && feedbackType.trim().equalsIgnoreCase("pending")){
				queryStr.append(" and model.alertStatus.alertStatusId in (12) ");//closed 
				queryStr.append(" and model.alertFeedbackStatusId is null ");
				queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
				//queryStr.append(" and model.alertCategory.alertCategoryId  in (4,5) ");
			}
			if(reopen != null && reopen.trim().equalsIgnoreCase("true")){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3) ");//partial satisfied and not satisfied
				queryStr.append(" and model.alertStatus.alertStatusId in (11) ");//reopen
				queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			}
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{  
				queryStr.append(" and model.alertCategory.alertCategoryId  in (4,5) ");
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
			
			if(statusId != null && statusId.longValue() > 0L){
				queryStr.append(" and model.alertStatus.alertStatusId = :statusId");
			}


			/*if(locationId != null && locationId.longValue() == 0L){
				queryStr.append(" and district.districtId is not null");
			}else{
				queryStr.append(" and district.districtId =:locationId");
			}*/
			
			if(step.equalsIgnoreCase("one")){
				queryStr.append(" group by model.alertCategory.alertCategoryId ");
			}else if(step.equalsIgnoreCase("two")){
				queryStr.append(" group by model.alertCategory.alertCategoryId,model.alertFeedbackStatus.alertFeedbackStatusId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0L){
				query.setParameter("locationId",locationId);
			}			

			if(statusId != null && statusId.longValue() > 0L){
				query.setParameter("statusId",statusId);
			}
			return query.list();   
		}
	 	public List<Object[]> getTotalAlertGroupByStatusForGrievancePage(Date fromDate, Date toDate, Long stateId,Long sourceId,Long deptId,String level){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
							" model.alertStatus.alertStatusId, " +
							" model.alertStatus.alertStatus, " +
							" count(distinct model.alertId) " +
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state" +
							" left join userAddress.district district, AlertDepartmentStatus model1  ");
			queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
			queryStr.append(" and model.isDeleted ='N' and  model.govtDepartment.govtDepartmentId = :deptId ");
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
			if(level != null && level.trim().equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null ");
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
			}
			queryStr.append(" group by model.alertStatus.alertStatusId order by model.alertStatus.alertStatusId ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if(fromDate != null && toDate != null){
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			if(sourceId != null && sourceId.longValue() != 0L){
				query.setParameter("sourceId", sourceId);
			}
			query.setParameter("deptId", deptId);
			return query.list(); 
		}
	 	public List<Object[]> getStateLevalfeedbackAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String type,List<Long> statusIds,String level){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			if(type.equalsIgnoreCase("feedbackStatus")){
				queryStr.append(" model.alertFeedbackStatus.alertFeedbackStatusId," +
								" model.alertFeedbackStatus.status,");
			}else if(type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" 4,'Pending FeedBack',");
			}else if(type.equalsIgnoreCase("reopen")){
				queryStr.append(" 5,'Reopen',");
			}
			queryStr.append(" count(distinct model.alertId) " +
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district ");
			queryStr.append(" , AlertDepartmentStatus model1 " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId " +
						" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			}else{
				if(type.equalsIgnoreCase("pendingFeedback") || type.equalsIgnoreCase("reopen")){
					queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+")) " +
							" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
				}else{
					//queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
					queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+")) " +
							" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
				}	
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			
			if(statusIds != null && statusIds.size() > 0){
				queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds)");
			}
			if(type != null && type.equalsIgnoreCase("pendingFeedback")){
				queryStr.append(" and model.alertFeedbackStatus is null ");
			}else if(type != null && type.equalsIgnoreCase("reopen")){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
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
			if(level != null && level.trim().equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null ");
			}
			if(type.equalsIgnoreCase("feedbackStatus")){
				queryStr.append(" group by model.alertFeedbackStatus.alertFeedbackStatusId order by model.alertFeedbackStatus.alertFeedbackStatusId ");
			}
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(statusIds != null && statusIds.size() > 0){
				query.setParameterList("statusIds",statusIds);
			}
			
			return query.list();   
		}
	 	public List<Object[]> getStateLevalReopenAlertCnt(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String level,String reopenType){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			queryStr.append(" 11,'Reopen',");
			queryStr.append(" count(distinct model.alertId) " +
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district ");
			queryStr.append(" , AlertDepartmentStatus model1, AlertAssignedOfficerTrackingNew AAOTN " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId " +
							" and AAOTN.alert.alertId = model.alertId " +
							" and AAOTN.alertStatus.alertStatusId = 11 " +
							" and AAOTN.govtAlertActionType.govtAlertActionTypeId = 6 ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId ");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+")) ");
			}
			queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			if(reopenType != null && reopenType.trim().equalsIgnoreCase("callCenter")){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
			}else{
				queryStr.append(" and model.alertFeedbackStatus is null");
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
			if(level != null && level.trim().equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			
			return query.list();   
		}
	 	public List<Object[]> getTotalReopenAlerts(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId,String reopenType){
			StringBuilder queryStr = new StringBuilder();  
			queryStr.append(" select ");
			
			queryStr.append(" model.alertCategory.alertCategoryId," +//0 
							" model.alertCategory.category,");//1
			queryStr.append(" count(distinct model.alertId) " +  //4 
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +     
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" , AlertDepartmentStatus model1, AlertAssignedOfficerTrackingNew AAOTN " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId " +
							" and AAOTN.alert.alertId = model.alertId " +
							" and AAOTN.alertStatus.alertStatusId = 11 " +
							" and AAOTN.govtAlertActionType.govtAlertActionTypeId = 6 ");
			queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			if(reopenType != null && reopenType.trim().equalsIgnoreCase("callCenter")){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
			}else{
				queryStr.append(" and model.alertFeedbackStatus is null");
			}
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{  
				queryStr.append(" and model.alertCategory.alertCategoryId  in (4,5) ");
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
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
			
			queryStr.append(" group by model.alertCategory.alertCategoryId ");
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			
			return query.list();   
		}
	 	public List<Object[]> getLocationWiseReopenCount(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String reopenType, Long locationId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("district")){
				queryStr.append(" district.districtId, " +//0
						        " district.districtName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" tehsil.tehsilId, " +//0
					       		" tehsil.tehsilName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" panc.panchayatId, " +//0
								" panc.panchayatName, ");//1
			}
			
			queryStr.append(" count(distinct model.alertId) " +  //4  
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" left join userAddress.tehsil tehsil  ");
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" left join model.userAddress.panchayat panc ");
			}
			queryStr.append(" , AlertDepartmentStatus model1, AlertAssignedOfficerTrackingNew AAOTN  " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId " +
							" and AAOTN.alert.alertId = model.alertId " +
							" and AAOTN.alertStatus.alertStatusId = 11 " +
							" and AAOTN.govtAlertActionType.govtAlertActionTypeId = 6 ");
			queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			
			  
			if(reopenType != null && reopenType.equalsIgnoreCase("callcenter")){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
			}else{
				queryStr.append(" and model.alertFeedbackStatus is null ");
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
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and district.districtId = :locationId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and tehsil.tehsilId = :locationId  ");
			}
			
			
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil")){
				queryStr.append(" group by tehsil.tehsilId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" group by panc.panchayatId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0L){
				query.setParameter("locationId",locationId);
			}
			return query.list();   
		}
	 	public List<Object[]> getDateWiseReopenAlertDtls(Date fromDate, Date toDate, Long stateId, Long departmentId,Long alertCategoryId,String reopenType,Long locationId){
			
		       StringBuilder queryStr = new StringBuilder();  
		
			    queryStr.append(" select ");
			    queryStr.append(" date(model.createdTime), ");//0
			    queryStr.append(" count(distinct model.alertId) " +  //1
								" from Alert model " +
								" left join model.userAddress userAddress " +
								" left join userAddress.state state  " +
								" left join userAddress.district district  " +
								" left join userAddress.constituency constituency " +
								" ,AlertDepartmentStatus model1, AlertAssignedOfficerTrackingNew AAOTN" +
								" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
								" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
								" and model.isDeleted ='N' " +
								" and model.govtDepartment.govtDepartmentId = :departmentId " +
								" and AAOTN.alert.alertId = model.alertId " +
								" and AAOTN.alertStatus.alertStatusId = 11 " +
								" and AAOTN.govtAlertActionType.govtAlertActionTypeId = 6  ");
			    queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			    if(alertCategoryId != null && alertCategoryId.longValue() != 0L){
				    queryStr.append(" and model.alertCategory.alertCategoryId = :alertCategoryId");
				}else{
					queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
				}
			    
				queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
				
				if(locationId != null && locationId.longValue() > 0){
					queryStr.append(" and district.districtId =:locationId");
				}
				
				if(fromDate != null && toDate != null){
					queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
				}
			    
				if(reopenType != null && reopenType.equalsIgnoreCase("callcenter")){
					queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
				}else{
					queryStr.append(" and model.alertFeedbackStatus is null ");
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
				queryStr.append(" group by date(model.createdTime)");
				
			    Query query = getSession().createQuery(queryStr.toString());
			
				if(departmentId != null && departmentId.longValue() > 0L){
					query.setParameter("departmentId",departmentId);
				}
				if(alertCategoryId != null && alertCategoryId.longValue() > 0L){
					query.setParameter("alertCategoryId",alertCategoryId);
				}
				
				if(fromDate != null && toDate != null){  
					query.setDate("fromDate", fromDate);
					query.setDate("toDate", toDate);    
				}
				if(locationId != null && locationId.longValue() > 0){
					query.setParameter("locationId",locationId);
				}
		return query.list();   								
		}
	 	public List<Long> getReopenCountDtls(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,Long reopenType, Long locationId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select ");
			queryStr.append(" distinct model.alertId " +  //4  
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district  " +
							" left join userAddress.constituency constituency " +
							" left join userAddress.tehsil tehsil  ");
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat")){
				queryStr.append(" left join model.userAddress.panchayat panc ");
			}
			queryStr.append(" , AlertDepartmentStatus model1, AlertAssignedOfficerTrackingNew AAOTN  " +
							" where  model1.alertType.alertTypeId = model.alertType.alertTypeId" +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId " + 
							" and model.isDeleted ='N' " +
							" and model.govtDepartment.govtDepartmentId = :departmentId " +
							" and AAOTN.alert.alertId = model.alertId " +
							" and AAOTN.alertStatus.alertStatusId = 11 " +
							" and AAOTN.govtAlertActionType.govtAlertActionTypeId = 6 ");
			queryStr.append(" and (model.socialMediaTypeId is not null or model.alertCallCenterTypeId is not null) ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.FEEDBACK_ALERT_CATEGORY_ID+"))");	
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
			
			  
			if(reopenType != null && reopenType.longValue() == 1L){
				queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (2,3)");
			}else if(reopenType != null && reopenType.longValue() == 2L){
				queryStr.append(" and model.alertFeedbackStatus is null ");
			}else{
				queryStr.append(" and (model.alertFeedbackStatus.alertFeedbackStatusId in (2,3) or model.alertFeedbackStatus is null)");
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
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("district") ){
				queryStr.append(" and district.districtId is not null ");
				if(locationId != null &&  locationId.longValue() > 0L){
					queryStr.append(" and district.districtId = :locationId ");
				}
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("tehsil") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and tehsil.tehsilId = :locationId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("panchayat") && locationId != null &&  locationId.longValue() > 0L){
				queryStr.append(" and panc.panchayatId = :locationId  ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
			if(locationId != null && locationId.longValue() > 0L){
				query.setParameter("locationId",locationId);
			}
			return query.list();   
		}
	 	public List<Long> getTotalAlertGroupByStatusForStateLvlGrievancePage(Date fromDate, Date toDate, Long stateId,Long sourceId,Long deptId,Long statusId,String level){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
							" distinct model.alertId " +
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state" +
							" left join userAddress.district district, AlertDepartmentStatus model1  ");
			queryStr.append(" where model1.alertType.alertTypeId = model.alertType.alertTypeId " +
							" and model1.alertStatus.alertStatusId = model.alertStatus.alertStatusId ");
			queryStr.append(" and model.isDeleted ='N' and  model.govtDepartment.govtDepartmentId = :deptId ");
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
			if(level != null && level.trim().equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null ");
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and (model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+"))");
			}
			if(statusId != null && statusId.longValue() > 0L){
				queryStr.append(" and model.alertStatus.alertStatusId = :statusId");
			}
			Query query = getSession().createQuery(queryStr.toString());
			if(fromDate != null && toDate != null){
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			if(sourceId != null && sourceId.longValue() != 0L){
				query.setParameter("sourceId", sourceId);
			}
			if(statusId != null && statusId.longValue() > 0L){
				query.setParameter("statusId", statusId);
			}
			query.setParameter("deptId", deptId);
			return query.list(); 
		}
	 	public List<Object[]> getPendingAlertDtls(Set<Long> alertSet){   
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
							" ALTS.alert_color as color," +//25
							" '' as officerName, " + //26
		                    " '' as officerLocation "); //27
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
			queryStr.append("  A.alert_id in (:alertSet) and A.is_deleted='N' ");
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
					.addScalar("color", Hibernate.STRING)//25
					.addScalar("officerName", Hibernate.STRING)//26
	                .addScalar("officerLocation", Hibernate.STRING);//27
			if(alertSet != null && alertSet.size() > 0){
				query.setParameterList("alertSet", alertSet);   
			}
			return query.list(); 
		}
	 	public Alert getModal(Long alertId){
	 		Query query = getSession().createQuery( " select modal from Alert modal where modal.alertId=:alertId and modal.isDeleted='N'   ");
	 		if(alertId != null && alertId.longValue() > 0L){
				query.setParameter("alertId",alertId);
			}
	 		return (Alert)query.uniqueResult();
	 		
	 	}
	 	public List<Object[]> getMondayGrievanceTypeList(){
	    	Query query = getSession().createQuery("select model.mondayGrievanceTypeId,model.type from MondayGrievanceType model ");
	    	return query.list();
	    }
	 	public List<Object[]> getJanmabhoomiTypeList(){
	    	Query query = getSession().createQuery("select model.janmabhoomiTypeId,model.type from JanmabhoomiType model ");
	    	return query.list();
	    }
	 	public List<Object[]> getSpecialGrievanceTypeList(){
	    	Query query = getSession().createQuery("select model.specialGrievanceTypeId,model.type from SpecialGrievanceType model ");
	    	return query.list();
	    }
	 	public List<Object[]> getGeneralGrievanceTypeList(){
	    	Query query = getSession().createQuery("select model.generalGrievanceTypeId,model.type from GeneralGrievanceType model ");
	    	return query.list();
	    }
	 	
	 	public Long getPresentStatusOfAlert(Long alertId){
	 		Query query = getSession().createQuery("select model.alertStatusId from Alert model " +
	 				" WHERE model.alertId = :alertId ");
	 		query.setParameter("alertId", alertId);
	    	return (Long) query.uniqueResult();
	 	}
	 	public List<Object[]> getStatusWiseLocationAlert(Date fromDate, Date toDate, Long stateId, Long departmentId,Long sourceId, String groupType,String type,Set<Long> alertStatusIds){
			StringBuilder queryStr = new StringBuilder();
			 queryStr.append(" select ");
			 
			 if(type != null && type.equalsIgnoreCase("Day")){
					queryStr.append(" date(model.createdTime), ");//2
			 }
			 if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("state")){
					queryStr.append(" state.stateId, " +//0
						       		" state.stateName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("district")){
				    queryStr.append(" district.districtId, " +//0
						            " district.districtName, ");//1
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("category")){
				  queryStr.append(" model.alertCategory.alertCategoryId, " +//0
					              " model.alertCategory.category, ");//1
			}
			 
			queryStr.append(" model.alertStatus.alertStatusId," +//2
					        " model.alertStatus.alertStatus,");//3
			
			queryStr.append(" count(distinct model.alertId) " +  //4  
							" from Alert model " +
							" left join model.userAddress userAddress " +
							" left join userAddress.state state  " +
							" left join userAddress.district district");
			
			 queryStr.append("	where model.isDeleted ='N'");
			if(departmentId != null && departmentId.longValue() > 0){
				queryStr.append(" and model.govtDepartment.govtDepartmentId = :departmentId ");
			}
			
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("state")){
				queryStr.append(" and district.districtId is null");
			}
			
			if(sourceId != null && sourceId.longValue() != 0L){
				queryStr.append(" and model.alertCategory.alertCategoryId = :sourceId");
			}else{
				queryStr.append(" and model.alertCategory.alertCategoryId  in ("+IConstants.GOVT_ALERT_CATEGORY_ID+") ");
			}
			queryStr.append(" and model.alertType.alertTypeId in ("+IConstants.GOVT_ALERT_TYPE_ID+") ");
			queryStr.append(" and model.alertStatus.alertStatusId in (:alertStatusIds) ");
			
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
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("State")){
				queryStr.append(" group by state.stateId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("District")){
				queryStr.append(" group by district.districtId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("category")){
			    queryStr.append(" group by model.alertCategory.alertCategoryId");//1
	        }
			queryStr.append(",model.alertStatus.alertStatusId");
			
			if(type != null && type.equalsIgnoreCase("Day")){
				queryStr.append(" ,date(model.createdTime) ");//2
			}
			
			if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("State")){
				queryStr.append(" order by  state.stateId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("District")){
				queryStr.append(" order by district.districtId ");
			}else if(groupType != null && !groupType.isEmpty() && groupType.equalsIgnoreCase("category")){
			    queryStr.append(" order by model.alertCategory.alertCategoryId");
	        }
			queryStr.append(",model.alertStatus.alertStatusId");
			if(type != null && type.equalsIgnoreCase("Day")){
				queryStr.append(" ,date(model.createdTime) ");
			}
			Query query = getSession().createQuery(queryStr.toString());
			
			query.setParameterList("alertStatusIds", alertStatusIds);
			if(departmentId != null && departmentId.longValue() > 0L){
				query.setParameter("departmentId",departmentId);
			}
			if(sourceId != null && sourceId.longValue() > 0L){
				query.setParameter("sourceId",sourceId);
			}
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			}
		return query.list();   
		}
	 	
	 	public List<Object[]> getAlertsDataForAms(Long alertId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model.alertId," +
					   " model.description, " +
					   " model.createdTime," +//2
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
			str.append(",govtDepartment.departmentName,alertStatus.color,alertSeverity.alertSeverityId ");//31-32-33
			str.append(" ,hamlet.hamletId, " +
					   " hamlet.hamletName");//35
			
			str.append(" from Alert model left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
			str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			str.append(" left join model.userAddress.district district ");
			str.append(" left join model.userAddress.state state ");
			str.append(" left join model.userAddress.ward ward ");
			str.append(" left join model.userAddress.hamlet hamlet ");
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
	 	
	 	public List<Object[]> getAlertDetailsOfCategoryByStatusWise(Date fromDate , Date toDate,Long deptId,String year){
	 		
	 		StringBuilder queryStr = new StringBuilder();
	 		
	 		queryStr.append(" SELECT model.alertStatus.alertStatusId,model.alertStatus.alertStatus," +
	 					"model.alertStatus.color,count(distinct model.alertId) " +
	 				" FROM Alert model " +
	 				" WHERE model.isDeleted ='N'" +
	 					" and model.govtDepartmentId  =:deptId " +
	 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			queryStr.append(" and (year(model.createdTime) =:year) ");
	 		}
	 		else if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
	 		
	 		queryStr.append(" and model.alertStatus.alertStatusId !=:pendingStatusId ");
	 		
	 		queryStr.append(" GROUP BY model.alertStatus.alertStatusId order by model.alertStatus.statusOrder  ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		query.setParameter("deptId", deptId);
	 		query.setParameter("pendingStatusId", 1l);
	 		
	 		return query.list();
	 	}
	 	public List<Long> getAlertsOfCategoryByStatusWise(Date fromDate , Date toDate,Long deptId,List<Long> statusIds,int stIndex,int endIndex,String year){
	 		
	 		StringBuilder queryStr = new StringBuilder();
	 		
	 		queryStr.append(" SELECT distinct model.alertId  " +
	 				" FROM Alert model " +
	 				" WHERE model.isDeleted ='N'" +
	 					" and model.govtDepartmentId  =:deptId " +
	 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			queryStr.append(" and (year(model.createdTime) =:year) ");
	 		}
	 		else if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
	 		
	 		if(statusIds !=null && statusIds.size()>0){
	 			queryStr.append(" and model.alertStatus.alertStatusId in (:statusIds) ");
	 		}
	 		
	 		queryStr.append(" GROUP BY model.alertStatus.alertStatusId order by model.alertStatus.statusOrder  ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		
	 		if(statusIds !=null && statusIds.size()>0){
	 			query.setParameterList("statusIds", statusIds);
	 		}
	 		query.setParameter("deptId", deptId);
	 		
	 		if(endIndex !=0){
	 			query.setFirstResult(stIndex);
	 			query.setMaxResults(endIndex);
	 		}
	 		
	 		return query.list();
	 	}
	 	
	 	public List<Object[]> getAlertFeedbackStatusDetails(Date fromDate,Date toDate,Long deptId,String year){
	 		StringBuilder queryStr = new StringBuilder();
	 		
	 		queryStr.append(" SELECT model.alertFeedbackStatus.alertFeedbackStatusId,model.alertFeedbackStatus.status," +
	 					" count(distinct model.alertId) " +
	 				" FROM Alert model " +
	 				" WHERE model.isDeleted ='N'" +
	 					" and model.govtDepartmentId  =:deptId " +
	 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+")" +
	 					" and model.alertFeedbackStatus.isDeleted ='N' " );
	 		if(year !=null && !year.trim().isEmpty()){
	 			queryStr.append(" and (year(model.createdTime) =:year) ");
	 		}else if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
	 		
	 		queryStr.append(" GROUP BY model.alertFeedbackStatus.alertFeedbackStatusId order by model.alertFeedbackStatus.orderNo   ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		
	 		query.setParameter("deptId", deptId);
	 		
	 		return query.list();
	 	}
	 	public List<Long> getAlertsOfFeedbackStatus(Date fromDate,Date toDate,Long deptId,List<Long> statusIds,int stIndex,int endIndex,String year){
	 		StringBuilder queryStr = new StringBuilder();
	 		
	 		queryStr.append(" SELECT distinct model.alertId " +
	 				" FROM Alert model " +
	 				" WHERE model.isDeleted ='N'" +
	 					" and model.govtDepartmentId  =:deptId " +
	 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+")" +
	 					" and model.alertFeedbackStatus.isDeleted ='N' " );
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			queryStr.append(" and (year(model.createdTime) = :year) ");
	 		}
	 		else if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}
	 		
	 		if(statusIds !=null && statusIds.size()>0){
	 			queryStr.append(" and model.alertFeedbackStatus.alertFeedbackStatusId in (:statusIds) ");
	 		}
	 		
	 		queryStr.append(" GROUP BY model.alertFeedbackStatus.alertFeedbackStatusId order by model.alertFeedbackStatus.orderNo   ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		if(statusIds !=null && statusIds.size()>0){
	 			query.setParameterList("statusIds", statusIds);
	 		}
	 		query.setParameter("deptId", deptId);
	 		
	 		if(endIndex !=0){
	 			query.setFirstResult(stIndex);
	 			query.setMaxResults(endIndex);
	 		}
	 		
	 		return query.list();
	 	}
	 	
	 	public List<Object[]> getLocationWiseAlertStatusCountsNew(Date fromDate , Date toDate,Long deptId,String year,Long locationTypeId,List<Long> locationValues,
	 			Long searchLevelId,List<Long> searchLevelValues){
	 		 
	 		StringBuilder queryStr = new StringBuilder();
	 		StringBuilder sb = new StringBuilder();
	 		StringBuilder sbM = new StringBuilder();
	 		StringBuilder sbE = new StringBuilder();
	 		StringBuilder sbG = new StringBuilder();
	 		StringBuilder sbO = new StringBuilder();
	 		
	 		sb.append(" SELECT ");
	 		sbM.append(" FROM Alert model ");	 			 	
	 		sbE.append(" WHERE model.isDeleted ='N' " +
	 				" and model.govtDepartmentId  =:deptId " +
 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
	 		sbE.append(" and model.alertStatus.alertStatusId !=:pendingStatusId ");
	 		
	 		sbG.append(" GROUP BY ");
	 		sbO.append(" ORDER BY ");
	 		
	 		
	 		if(locationTypeId !=null && locationTypeId>0l){
	 			if(locationTypeId == 2l){
	 				sb.append(" model.userAddress.state.stateId,model.userAddress.state.stateName ");
	 				if(locationValues !=null && locationValues.size()>0){
		 				sbE.append(" and model.userAddress.state.stateId in (:locationValues)" );
		 			}
	 				sbE.append("  and model.userAddress.state.stateId is not null ");
	 				sbG.append(" model.userAddress.state.stateId ");
	 				sbO.append("  model.userAddress.state.stateId "); 
	 			}
	 			if(locationTypeId == 3l){
	 				sb.append(" model.userAddress.district.districtId,model.userAddress.district.districtName ");
	 				if(locationValues !=null && locationValues.size()>0){
	 					sbE.append(" and model.userAddress.district.districtId in (:locationValues)" );
		 			}
	 				sbE.append("  and model.userAddress.district.districtId is not null ");
	 				sbG.append(" model.userAddress.district.districtId ");
	 				sbO.append("  model.userAddress.district.districtId "); 
	 			}
	 			if(locationTypeId == 4l){
	 				sb.append(" model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
	 				if(locationValues !=null && locationValues.size()>0){
	 					sbE.append(" and model.userAddress.constituency.constituencyId in (:locationValues)");
		 			}	 				
	 				sbE.append(" and model.userAddress.constituency.constituencyId is not null ");
	 				sbG.append(" model.userAddress.constituency.constituencyId ");
	 				sbO.append("  model.userAddress.constituency.constituencyId "); 
	 			}
	 			if(locationTypeId == 5l){
	 				sb.append(" model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
	 				if(locationValues !=null && locationValues.size()>0){
	 					sbE.append(" and model.userAddress.tehsil.tehsilId in (:locationValues)");
		 			}
	 				sbE.append(" and model.userAddress.tehsil.tehsilId is not null ");	 		
	 				sbG.append("  model.userAddress.tehsil.tehsilId ");
	 				sbO.append(" model.userAddress.tehsil.tehsilId "); 
	 			}
	 			if(locationTypeId == 6l){
	 				sb.append(" model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName ");
	 				if(locationValues !=null && locationValues.size()>0){
	 					sbE.append(" and model.userAddress.panchayat.panchayatId in (:locationValues)");
		 			}
	 				sbE.append(" and model.userAddress.panchayat.panchayatId is not null ");	 		
	 				sbG.append("  model.userAddress.panchayat.panchayatId ");
	 				sbO.append(" model.userAddress.panchayat.panchayatId "); 
	 			}
	 				 			
	 		}
	 		
	 		sb.append(" ,model.alertStatus.alertStatusId,model.alertStatus.alertStatus," +
	 					"model.alertStatus.color,count(distinct model.alertId) ");
	 		
	 		 if(searchLevelId !=null && searchLevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
		         if(searchLevelId ==2l){
		           //sbE.append("  and  model.userAddress.state.stateId in (:searchLevelValues)  ");
		        	 sbE.append("  and  model.userAddress.district.districtId  between 11 and 23 ");
		         }else if(searchLevelId ==3l){
		           sbE.append(" and model.userAddress.district.districtId in (:searchLevelValues) ");		           
		         }else if(searchLevelId ==4l){
		           sbE.append(" and model.userAddress.constituency.constituencyId in (:searchLevelValues) ");		           
		         }else if(searchLevelId ==5l){
		           sbE.append(" and model.userAddress.tehsil.tehsilId  in (:searchLevelValues) ");
		         }else if(searchLevelId ==6l){
		           sbE.append( " and model.userAddress.panchayat.panchayatId in  (:searchLevelValues)  " );
		         }
		    }
	 		
	 		if(year !=null && !year.trim().isEmpty()){
	 			sbE.append(" and (year(model.createdTime) =:year) ");
	 		}else if(fromDate != null && toDate != null){
	 			sbE.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}	 		
	 		
	 		
	 		sbG.append(" ,model.alertStatus.alertStatusId " +
	 				"");
	 		
	 		sbO.append(" ,model.alertStatus.alertStatusId  "); 
	 		
	 		 sb.append(sbM.toString()).append(sbE.toString()).append(sbG.toString()).append(sbO.toString());  //Appending Final String to "sb"
	 		
	 		Query query = getSession().createQuery(sb.toString());
	 		

	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		
	 		  if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
	 		        query.setParameterList("locationValues", locationValues);
	 		   }
	 		  if(searchLevelId !=null && searchLevelId.longValue() != 2l){
		      if(searchLevelId !=null && searchLevelId.longValue()>0l && searchLevelValues !=null && searchLevelValues.size()>0){
		    	  query.setParameterList("searchLevelValues", searchLevelValues);
		      }
	 		  }
	 		
	 		query.setParameter("deptId", deptId);
	 		query.setParameter("pendingStatusId", 1l);
	 		
	 		return query.list();
	 	}
	 	public List<Object[]> getTotalAlertDetailsCount(Date fromDate,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
	 		StringBuilder queryStr = new StringBuilder();
	 			queryStr.append(" SELECT model.alertType.alertTypeId,model.alertType.alertType,count(distinct model.alertId) " +
	 				" FROM Alert model " +
	 				" WHERE model.isDeleted ='N'" +
	 				" and model.alertType.alertTypeId  in (:alertTypeIds) " );
	 		
	 		if(fromDate != null && toDate != null){
				queryStr.append(" and date(model.createdTime) between :fromDate and :toDate ");  
			}
	 		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 	        if(locationTypeId == 4l){
	 	        	queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	queryStr.append(" and model.userAddress.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	queryStr.append(" and model.userAddress.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	queryStr.append(" and model.userAddress.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 2l){
	 	        	queryStr.append(" and model.userAddress.state.stateId in(:locationValues) ");
	 	        }
	 	    }
	 		if(year != null && !year.trim().isEmpty()){
	 	    	queryStr.append(" and year(model.createdTime) =:year ");   
	 	    }
	 		queryStr.append(" GROUP BY model.alertTypeId " +
	 					" order by model.alertStatus.statusOrder  ");
	 		Query query = getSession().createQuery(queryStr.toString());
	 		query.setParameterList("alertTypeIds", alertTypeIds);
			 if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
			 }
			 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
		 	        if(locationTypeId == 4l || locationTypeId == 8l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }else if(locationTypeId == 3l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }else if(locationTypeId == 5l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }else if(locationTypeId == 6l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }else if(locationTypeId == 7l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }else if(locationTypeId == 2l){
		 	        	query.setParameterList("locationValues", locationValues);
		 	        }
		 	    }
			 if(year !=null && !year.trim().isEmpty()){
				 query.setParameter("year", Integer.parseInt(year));
		 	 }
	 		return  query.list();
	 	}
      public List<Object[]> getAlertStatusWiseDetailsForConstituencyInfo(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
	 		StringBuilder queryStr = new StringBuilder();
	 			queryStr.append(" SELECT model.alertStatus.alertStatusId,model.alertStatus.alertStatus," +
	 					" model.alertStatus.color,model.regionScopes.regionScopesId,model.regionScopes.scope," +
	 					" model.alertType.alertTypeId,model.alertType.alertType, " +
	 					" count(distinct model.alertId) " +
	 				    " FROM Alert model " +
	 				    " WHERE model.isDeleted ='N' " +
	 					" and model.alertType.alertTypeId  in (:alertTypeIds) " );
	 		
	 	    if(fromDate != null && toDate != null){
				queryStr.append(" and date(model.createdTime) between :fromDate and :toDate ");  
			}
	 	       
	 	     if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	 	        if(locationTypeId == 4l){
	 	        	queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 3l){
	 	        	queryStr.append(" and model.userAddress.district.districtId in(:locationValues) ");
	 	        }else if(locationTypeId == 5l){
	 	        	queryStr.append(" and model.userAddress.tehsil.tehsilId in(:locationValues) ");
	 	        }else if(locationTypeId == 6l){
	 	        	queryStr.append(" and model.userAddress.panchayat.panchayatId in(:locationValues) ");
	 	        }else if(locationTypeId == 7l){
	 	        	queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }
	 	    }
	 	    if(year != null && !year.trim().isEmpty()){
	 	    	queryStr.append(" and year(model.createdTime) =:year ");   
	 	    }
	 	    queryStr.append(" GROUP BY model.alertStatus.alertStatusId," +
	 	    			" model.alertType.alertTypeId ");
	 	    	
	 	    queryStr.append(" order by model.alertStatus.statusOrder  ");
	 		
	 		Query query = getSession().createQuery(queryStr.toString());
	 		query.setParameterList("alertTypeIds", alertTypeIds);
			 if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
			 }
			 if(year !=null && !year.trim().isEmpty()){
		 			query.setParameter("year", Integer.parseInt(year));
		 	 }
			 if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
				 if(locationTypeId == 4l || locationTypeId == 8l){
				 query.setParameterList("locationValues",locationValues);
	 	        }else if(locationTypeId == 3l){
	 	        	query.setParameterList("locationValues",locationValues);
	 	        }else if(locationTypeId == 5l){
	 	        	query.setParameterList("locationValues",locationValues);
	 	        }else if(locationTypeId == 6l){
	 	        	query.setParameterList("locationValues",locationValues);
	 	        }else if(locationTypeId == 7l){
	 	        	query.setParameterList("locationValues",locationValues);
	 	        }
			 }
	 		return query.list();
	 	}
      public List<Object[]> getAlertImpactLevelWiseDetailsForConstituencyInfo(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type){
	 		StringBuilder queryStr = new StringBuilder();
	 		
	 		if(type != null && type.equalsIgnoreCase("impactScope")){
	 			queryStr.append(" SELECT model.alertImpactScope.alertImpactScopeId,model.alertImpactScope.impactScope ");
	 		}else if(type != null && type.equalsIgnoreCase("alertCategory")){
	 			queryStr.append(" SELECT model.alertCategory.alertCategoryId,model.alertCategory.category ");
	 		}
	 			queryStr.append(" , model.alertType.alertTypeId,model.alertType.alertType, " +
	 				    " count(distinct model.alertId),model.alertStatus.alertStatusId " +
	 				    " FROM Alert model " +
	 				    " WHERE model.isDeleted ='N' " +
	 					" and model.alertTypeId  in (:alertTypeIds) " );
	 		
	 	    if(fromDate != null && toDate != null){
				queryStr.append(" and date(model.createdTime) between :fromDate and :toDate ");  
			}
	 	   if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 	    	if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 4l){
		        	queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 3l){
		        	queryStr.append(" and model.userAddress.district.districtId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 5l){
		        	queryStr.append(" and model.userAddress.tehsil.tehsilId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 6l){
		        	queryStr.append(" and model.userAddress.panchayat.panchayatId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 7l){
	 	        	queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 2l){
	 	        	queryStr.append(" and model.userAddress.state.stateId in(:locationValues) ");
	 	        }
	 	    }
	 	    if(year != null && !year.trim().isEmpty()){
	 	    	queryStr.append(" and year(model.createdTime) =:year ");   
	 	    }
	 		
	 	   if(type != null && type.equalsIgnoreCase("impactScope")){
	 			queryStr.append(" GROUP BY model.alertImpactScope.alertImpactScopeId ");
	 		}else if(type != null && type.equalsIgnoreCase("alertCategory")){
	 			queryStr.append(" GROUP BY model.alertCategory.alertCategoryId ");
	 		}
	 	    queryStr.append(",model.alertType.alertTypeId,model.alertStatus.alertStatusId  " );
	 	    
	 	   if(type != null && type.equalsIgnoreCase("impactScope")){
	 		  queryStr.append(" order by model.alertImpactScope.orderNo  ");
	 	   }else if(type != null && type.equalsIgnoreCase("alertCategory")){
	 		  queryStr.append(" order by model.alertCategory.alertCategoryId ");
	 	   }
	 	    	
	 		Query query = getSession().createQuery(queryStr.toString());
	 			query.setParameterList("alertTypeIds", alertTypeIds);
	 			 if(fromDate != null && toDate != null){
	 				query.setParameter("fromDate", fromDate);
		 			query.setParameter("toDate", toDate);
	 			 }
	 			if(year !=null && !year.trim().isEmpty()){
	 				query.setParameter("year", Integer.parseInt(year));
	 			}
	 		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	 
	 			if(locationTypeId == 4l || locationTypeId == 8l){
	 				query.setParameterList("locationValues", locationValues);
		        }else if(locationTypeId == 3l){
		        	query.setParameterList("locationValues", locationValues);
		        }else if(locationTypeId == 5l){
		        	query.setParameterList("locationValues", locationValues);
		        }else if(locationTypeId == 6l){
		        	query.setParameterList("locationValues", locationValues);
		        }else if(locationTypeId == 7l){
		        	query.setParameterList("locationValues", locationValues);
		        }else if(locationTypeId == 2l){
		        	query.setParameterList("locationValues", locationValues);
	 	        }
	 		}
	 		return query.list();
      }
	 	public List<Long> getLocationWiseAlertStatusDetailsInfo(Date fromDate , Date toDate,Long deptId,String year,Long locationTypeId,List<Long> locationValues,
	 		List<Long> statusIds,int startIndex,int endIndex){
	 		 
	 			
	 		StringBuilder sb = new StringBuilder();
	 		StringBuilder sbM = new StringBuilder();
	 		StringBuilder sbE = new StringBuilder();

	 		
	 		sb.append(" SELECT distinct model.alertId ");
	 		
	 		sbM.append(" FROM Alert model ");	 			 	
	 		sbE.append(" WHERE model.isDeleted ='N' " +
	 				" and model.govtDepartmentId  =:deptId " +
 					" and model.alertTypeId  in ("+IConstants.GOVT_ALERT_TYPE_ID+") " );
	 		
	 		if(statusIds !=null && statusIds.size()>0){
	 			sbE.append(" and model.alertStatus.alertStatusId in (:statusIds) ");
	 		}
	 		
	 		if(locationTypeId !=null && locationTypeId>0l && locationValues !=null && locationValues.size()>0){
	 			if(locationTypeId == 2l){
		 				sbE.append(" and model.userAddress.state.stateId in (:locationValues)" );
	 			}
	 			if(locationTypeId == 3l){
	 					sbE.append(" and model.userAddress.district.districtId in (:locationValues)" );
	 			}
	 			if(locationTypeId == 4l){
	 					sbE.append(" and model.userAddress.constituency.constituencyId in (:locationValues)");		 				 					 			
	 			}
	 			if(locationTypeId == 5l){
	 					sbE.append(" and model.userAddress.tehsil.tehsilId in (:locationValues)");
	 			}
	 			if(locationTypeId == 6l){
	 					sbE.append(" and model.userAddress.tehsil.tehsilId in (:locationValues)");
	 			}	 				 		
	 		}

	 		if(year !=null && !year.trim().isEmpty()){
	 			sbE.append(" and (year(model.createdTime) =:year) ");
	 		}else if(fromDate != null && toDate != null){
	 			sbE.append(" and (date(model.createdTime) between :fromDate and :toDate) ");  
			}	 		
	 		
	 		sb.append(sbM.toString()).append(sbE.toString());   //Appending Final String to "sb"
	 		
	 		Query query = getSession().createQuery(sb.toString());
	 		

	 		if(year !=null && !year.trim().isEmpty()){
	 			query.setParameter("year", Integer.parseInt(year));
	 		}else if(fromDate != null && toDate != null){
	 			query.setParameter("fromDate", fromDate);
	 			query.setParameter("toDate", toDate);
	 		}
	 		
	 		if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
 		        query.setParameterList("locationValues", locationValues);
	 		}
	 		if(statusIds !=null && statusIds.size()>0){
	 			query.setParameterList("statusIds", statusIds);
	 		}
	 		
	 		query.setParameter("deptId", deptId);
	 		
	 		if(endIndex !=0){
	 			query.setFirstResult(startIndex);
	 			query.setMaxResults(endIndex);
	 		}
	 		
	 		return query.list();
	 		
	 	}
	 	public List<Object[]> getAlertCategaryAndImpactLevelWiseDetailsOverView(Date fromDate , Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year,String type,
	 			List<Long> alertCategeryIdsList,List<Long> statusIdsList,List<Long> impactIdsList,String otherCategory){
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
							" alertSeverity.severityColor, " +//24
							" alertStatus.color ");//25
			queryStr.append(" from Alert model " +
							" left join model.alertSource alertSource " +
			        		" left join model.editionType editionType " +
			        		" left join model.edition edition " +
			        		" left join model.alertSeverity alertSeverity " +   
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
			queryStr.append(" where model.isDeleted ='N' ");
					//" and alertType.alertTypeId in ("+IConstants.ALERT_PARTY_AND_OTHERS_TYPE_IDS+") ");
			if(fromDate != null && toDate != null){ 
				queryStr.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
			}     
			
			if(alertTypeIds != null && alertTypeIds.size() > 0){
				queryStr.append(" and alertType.alertTypeId in (:alertTypeIds)");
			}
			
			if(statusIdsList != null && statusIdsList.size() > 0L){
				queryStr.append(" and alertStatus.alertStatusId in  (:statusIdsList) ");
			}
			
			if(alertCategeryIdsList != null && alertCategeryIdsList.size() > 0L){
				queryStr.append(" and alertCategory.alertCategoryId in (:alertCategeryIdsList) ");
			}
			if(type != null && type.equalsIgnoreCase("categoryOthers")){
				queryStr.append(" and alertStatus.alertStatusId  not in (1,3,4,6,7) ");
			}else if(type != null && type.equalsIgnoreCase("impactOthers")){
				queryStr.append(" and alertStatus.alertStatusId  not in (3,4)");
			}
			if(impactIdsList != null && impactIdsList.size() > 0){
				queryStr.append(" and alertImpactScope.alertImpactScopeId in (:impactIdsList) ");
			}
			if(otherCategory != null && otherCategory.equalsIgnoreCase("categoryOthers")){
				queryStr.append(" and alertCategory.alertCategoryId not in (1,2,3) ");
			}else if(otherCategory != null && otherCategory.equalsIgnoreCase("impactOthers")){
				queryStr.append(" and alertImpactScope.alertImpactScopeId not in (1,2,3,5,7,8,10) ");
			}
			if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	 	    	if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 4l){
		        	queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 3l){
		        	queryStr.append(" and model.userAddress.district.districtId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 5l){
		        	queryStr.append(" and model.userAddress.tehsil.tehsilId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 6l){
		        	queryStr.append(" and model.userAddress.panchayat.panchayatId in(:locationValues) ");
		        }else if(locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId == 7l){
	 	        	queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:locationValues) ");
	 	        }else if(locationTypeId == 8l){
	 	        	queryStr.append(" and model.userAddress.ward.constituencyId in(:locationValues) ");
	 	        }else if(locationTypeId == 2l){
	 	        	queryStr.append(" and model.userAddress.state.stateId in(:locationValues) ");
	 	        }
	 	    }
			Query query = getSession().createQuery(queryStr.toString());   
			
			if(fromDate != null && toDate != null){  
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);    
			} 
			if(alertTypeIds != null && alertTypeIds.size()> 0){
				query.setParameterList("alertTypeIds", alertTypeIds);
			}
			if(statusIdsList != null && statusIdsList.size() > 0L){
				query.setParameterList("statusIdsList", statusIdsList);
			}
			if(alertCategeryIdsList != null && alertCategeryIdsList.size() > 0L){
				query.setParameterList("alertCategeryIdsList", alertCategeryIdsList);
			}
			if(year !=null && !year.trim().isEmpty()){
 				query.setParameter("year", Integer.parseInt(year));
 			}
 		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	 
 			if(locationTypeId == 4l || locationTypeId == 8l){
 				query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 3l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 5l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 6l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 7l){
	        	query.setParameterList("locationValues", locationValues);
	        }else if(locationTypeId == 2l){
	        	query.setParameterList("locationValues", locationValues);
 	        }
 		}
 		if(impactIdsList != null && impactIdsList.size() > 0){
 			query.setParameterList("impactIdsList", impactIdsList);
 		}
			return query.list();
		}
	 	
}