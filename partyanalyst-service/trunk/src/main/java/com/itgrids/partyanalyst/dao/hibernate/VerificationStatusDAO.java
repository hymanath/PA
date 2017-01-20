package com.itgrids.partyanalyst.dao.hibernate;



import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.VerificationStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class VerificationStatusDAO extends GenericDaoHibernate<VerificationStatus, Long> implements IVerificationStatusDAO {

	public VerificationStatusDAO() {
		super(VerificationStatus.class);
	}
	public List<Object[]> getStatusWiseAlertCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model1.actionTypeStatus.actionType.actionTypeId, " +
						" model1.actionTypeStatus.actionType.typeName, " +
						" model1.actionTypeStatus.actionTypeStatusId, " +
						" model1.actionTypeStatus.status, " +
						" model1.alert.alertCategory.alertCategoryId, " +
						" model1.alert.alertCategory.category, " +
						" count(distinct model1.alert.alertId) " +
						" from VerificationStatus model1 " +
						" where " +
						" model1.isDeleted = 'N' " +
						" and model1.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+") ");
		if(stateId != null && stateId.longValue() > 0){
			queryStr.append(" and model1.alert.userAddress.state.stateId = :stateId ");
		}
		if(fromDate != null && toDate != null){ 
			queryStr.append(" and (date(model1.alert.createdTime) between :fromDate and :toDate) ");
		}
		if(alertTypeId != null && alertTypeId.longValue() > 0 ){
			queryStr.append(" and  model1.alert.alertType.alertTypeId = :alertTypeId ");
		}
		queryStr.append(" group by " +
						" model1.actionTypeStatus.actionType.actionTypeId, " +
						" model1.actionTypeStatus.actionTypeStatusId, " +
						" model1.alert.alertCategory.alertCategoryId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null && toDate != null){  
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);    
		} 
		if(alertTypeId != null && alertTypeId.longValue() > 0L){
			query.setParameter("alertTypeId", alertTypeId);
		}
		if(stateId != null && stateId.longValue() > 0){
			query.setParameter("stateId", stateId);
		}
		return query.list();
	}
	public List<Object[]> getAllAlerts(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		str.append(" select " +
				   " model.alertId ," +//0
				   " model.description, " +//1
				   " date(model.createdTime)," +//2
				   " alertType.alertType.alertTypeId, " +//3
				   " alertSource.source, " +//4
				   " alertSeverity.severity, " +//5
				   " model.regionScopes.regionScopesId, " +//6
				   " model.regionScopes.scope," +//7
				   " vs.actionTypeStatus.actionTypeStatusId, " +//8
				   " vs.actionTypeStatus.status ");//9
		str.append(" ,tehsil.tehsilId, " +//10
				   " tehsil.tehsilName , " +//11
				   " panc.panchayatId, " +//12
				   " panc.panchayatName, " +//13
				   " localElectionBody.localElectionBodyId, " +//14
				   " localElectionBody.name, " +//15
				   " district.districtId, " +//16
				   " district.districtName, " +//17
				   " electionType.electionType ");//18
		str.append(" ,constituency.constituencyId, " +//19
				   " constituency.name");//20
		str.append(" ,state.stateId, " +//21
				   " state.stateName ");//22
		str.append(" ,ward.constituencyId, " +//23
				   " ward.name,");//24
		str.append(" alertCategory.alertCategoryId, " +//25
				   " alertCategory.category ");//26
		str.append(" from VerificationStatus vs " +
				" 	 left join vs.alert model " +
				" 	 left join model.alertSeverity alertSeverity" +
				" 	 left join model.alertSource  alertSource " +
				"	 left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
		str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.localElectionBody.electionType electionType ");
		str.append(" left join model.userAddress.district district ");
		str.append(" left join model.userAddress.state state ");
		str.append(" left join model.userAddress.ward ward ");
		str.append(" left join model.alertCategory alertCategory ");
		str.append(" left join model.alertType  alertType ");
		
		str.append(" where model.isDeleted ='N' and vs.isDeleted='N' ");
		
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0l){
			str.append(" and model.impactScopeId=:impactScopeId ");
		}
		if(inputVO.getLevelValue() != null && inputVO.getLevelValue().longValue() == 1L)
			str.append(" and state.stateId in (1) ");
		else if(inputVO.getLevelValue() != null && (inputVO.getLevelValue().longValue() == 36L ))
			str.append(" and state.stateId in (36) ");
		else
			str.append(" and state.stateId in (1,36) ");
	
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			str.append(" and model.alertTypeId = :alertTypeId ");
		
		if(sourceIds != null && sourceIds.size() > 0)
			str.append(" and model.alertSource.alertSourceId in (:sourceIds)");
		
		if(fromDate != null && toDate != null){ 
			str.append(" and (date(model.createdTime) between :fromDate and :toDate) ");
		}
		
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			str.append(" and vs.actionTypeStatus.actionTypeStatusId = :actionTypeStatusId ");
		
		str.append(" and vs.actionTypeStatus.actionType.actionTypeId in ("+IConstants.ALERT_ACTION_TYPE_ID+")  ");
		
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			str.append(" and alertCategory.alertCategoryId = :alertCategoryId");
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAlertImpactScopeId() != null && inputVO.getAlertImpactScopeId() > 0L){
			query.setParameter("impactScopeId", inputVO.getAlertImpactScopeId());			
		}
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		if(sourceIds != null && sourceIds.size() > 0)
			query.setParameterList("sourceIds", sourceIds);
		if(fromDate != null && toDate != null){ 
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getActionTypeStatusId() != null && inputVO.getActionTypeStatusId().longValue() > 0L)
			query.setParameter("actionTypeStatusId", inputVO.getActionTypeStatusId());
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue() > 0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		return query.list();
	}
}
