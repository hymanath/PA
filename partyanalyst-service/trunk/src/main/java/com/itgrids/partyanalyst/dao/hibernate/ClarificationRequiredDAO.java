package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IClarificationRequiredDAO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.ClarificationRequired;

public class ClarificationRequiredDAO extends GenericDaoHibernate<ClarificationRequired, Long> implements IClarificationRequiredDAO{

	public ClarificationRequiredDAO() {
		super(ClarificationRequired.class);
		
	}
	
	public List<Object[]> getDetails(Long alertId){
		
		Query query = getSession().createQuery(" select model.isRequired,model.alertClarificationStatusId,model.alertClarificationStatus.status,model.clarificationRequiredId " +
				" from ClarificationRequired model " +
				" where model.alertId = :alertId and model.isDeleted='N' and model.alert.isDeleted='N' ");
		
		query.setParameter("alertId", alertId);
		
		return query.list();
	}
	
	public Integer updateStatusForOld(Long userId,Long alertId,Date date,String userType){
		Query query = getSession().createQuery(" update ClarificationRequired model set model.isDeleted='Y', model.updatedBy=:updatedBy,model.updatedTime=:updatedTime, " +
				" model.userType=:userType where model.alertId=:alertId and model.isDeleted='N'");
		query.setParameter("updatedBy", userId);
		query.setParameter("updatedTime", date);
		query.setParameter("alertId", alertId);
		query.setParameter("userType", userType);
		return query.executeUpdate();
	}
	
	public List<Object[]> getStatusAndCategoryWiseAlertsCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId){
		StringBuilder sb = new StringBuilder();
		//0-alertClarificationStatusId,1-status,2-alertCategoryId,3-category,4-count
		sb.append(" select model.alertClarificationStatus.alertClarificationStatusId,model.alertClarificationStatus.status ," +
				" model.alert.alertCategory.alertCategoryId,model.alert.alertCategory.category,count(distinct model.alertId)  " +
				" from ClarificationRequired model " +
				" where model.isDeleted='N' and model.alert.isDeleted='N' ");
		
		if(stateId != null && stateId > 0l)
			sb.append(" and model.alert.userAddress.state.stateId=:stateId ");
		
		if(fromDate != null && toDate != null)
			sb.append(" and date(model.alert.updatedTime) between :fromDate and :toDate ");
		
		if(alertTypeId != null && alertTypeId > 0l)
			sb.append(" and model.alert.alertTypeId=:alertTypeId ");
		
		sb.append(" group by model.alertClarificationStatus.alertClarificationStatusId,model.alert.alertCategory.alertCategoryId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(stateId != null && stateId > 0l)
			query.setParameter("stateId", stateId);
		
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(alertTypeId != null && alertTypeId > 0l)
			query.setParameter("alertTypeId", alertTypeId);
		
		return query.list();
		
	}
	
	public List<Object[]> getLocationLevelAlertClarificationData(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		str.append("select model.alertId,model.description,date(model.createdTime)," +
				" alertType.alertType,alertSource.source,alertSeverity.severity,model.regionScopes.regionScopesId,model.regionScopes.scope," +
				" cr.alertClarificationStatusId,cr.alertClarificationStatusId ");
		str.append(" ,tehsil.tehsilId,tehsil.tehsilName , panc.panchayatId, panc.panchayatName,localElectionBody.localElectionBodyId,localElectionBody.name, district.districtId,district.districtName, electionType.electionType ");
		str.append(" ,constituency.constituencyId,constituency.name");
		str.append(" ,state.stateId,state.stateName");
		str.append(" ,ward.constituencyId,ward.name,");
		str.append(" alertCategory.alertCategoryId,alertCategory.category ");
		str.append(" from ClarificationRequired cr " +
				" 	 left join cr.alert model " +
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
		
		str.append(" where model.isDeleted ='N' and cr.isDeleted='N' ");
		
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
			}else{
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
			str.append(" and cr.alertClarificationStatusId = :statusId");
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
		
		if(inputVO.getCategoryId() != null && inputVO.getCategoryId().longValue()>0L)
			query.setParameter("alertCategoryId", inputVO.getCategoryId());
		if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue()>0L)
			query.setParameter("alertTypeId", inputVO.getAlertTypeId());
		if(inputVO.getAlertImpactScopeId() !=null && inputVO.getAlertImpactScopeId()>0l){
			query.setParameter("impactScopeId", inputVO.getAlertImpactScopeId());			
		}
		return query.list();
		
	}
}
