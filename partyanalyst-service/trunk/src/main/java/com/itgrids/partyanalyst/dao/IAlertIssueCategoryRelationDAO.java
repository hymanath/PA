package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertIssueCategoryRelation;

public interface IAlertIssueCategoryRelationDAO extends GenericDao<AlertIssueCategoryRelation, Long> {

	public int deleteIssueCategoryDetails(List<Long> alertIssueCategoryRelationIds);
	public List<Long> getAlertIssueCategoryList(Long alertId,Long alertTypeId);
	public List<Object[]> getIssueCategoryDetailsOfAlerts(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeIds,
			List<Long> editionTypeList,List<Long> alertStatusIds,List<Long> impactScopeIdList,String type);
	public List<Object[]> getBriefIssueCategoryDetailsOfAlert(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertTypeIds,
			List<Long> editionTypeList,List<Long> alertStatusIds,List<Long> impactScopeIdList,Long issueCategoryId);
	public List<Object[]> getStateWiseAlertIssueCategoryDetails(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,List<Long> impactLevelIds,Date fromDate,Date toDate,List<Long> alertTypeList, List<Long> editionList,List<Long> statusIds,Long districtId);
	public List<Object[]> getTotalAlertOfLocationAndIssueCategory(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, 
			Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> alertTypeList, List<Long> editionList,String filterType,Long locationValue
			,Long disctrictId,List<Long> alertStatusIds,List<Long> locationValues);
	
	public List<Object[]> getIssueCategoryWiseLocationAlertDetails(Long userAccessLevelId, List<Long> userAccessLevelValues,Date fromDate, Date toDate, Long stateId,List<Long> impactLevelIds,List<Long> districtIdList, List<Long> alertTypeList, List<Long> editionList,
			Long constituencyId,List<Long> alertStatusIds,Long localElectionBodyId,String locationLevel,String type,List<Long> constituencyList,List<Long> issueCategoryIds);
	public List<Long> getAlertIssueCategoryIdsList(Long alertId);
	public List<String> getAlertIssueCategory(Long alertId);
}
