package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreTargetCount;

public interface ITdpCadreTargetCountDAO extends GenericDao<TdpCadreTargetCount, Long> {
	public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long enrollmentYearId,Long activityMemberId);
	public List<Object[]> getTotalCadreTargetCountLocationType(String locationType,Long stateId,Long entollmentTearId);
	public Long getTargetCount(Long stateId);
	public List<Object[]> getTdpCadreTargetCountLocationWise(Long enrollmentYearId);
	public List<Object[]> getTargetCountForLocationsWise(GISVisualizationParameterVO inputVO);
	public List<Object[]> getTtalCadreTargetCountScopeWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId,Long activityMemberId,String reportType);
	public List<Object[]> getConstitiuencyWiseTargetBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Long enrollmentYearId);	
	public List<Object[]> getTtalCadreTargetCountScopeWiseCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId);
	public List<Object[]> getTtalCadreTargetCountScopeWiseCountSpecial(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long enrollmentYearId);
	public List<Object[]> getDistrictsTargetCountForTodayAndOverAll(Long stateId);
	public List<Object[]> getConstitencysTargetCountForTodayAndOverAll(Long stateId);
	public List<Object[]> getTtalCadreTargetCntDistWise(Long enrollmentYearId,List<Long> constituencyIds,String districtName);
	public Long getTargetCountForTodayAndOverAll(List<Long> constiIds);
	public Long getOtherDistTargetCountForTodayAndOverAll(Long stateId,List<Long> constiIds);
}
