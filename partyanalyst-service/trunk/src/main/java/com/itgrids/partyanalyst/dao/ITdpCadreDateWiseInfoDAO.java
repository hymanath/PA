package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfo;

public interface ITdpCadreDateWiseInfoDAO extends GenericDao<TdpCadreDateWiseInfo,Long>{
	
	public int deleteAllRecords(Date fromDate);
	public int setPrimaryKeyAutoIncrementToOne();
	public List<Object[]> get2016TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalRenewalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalNewCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalCadreCountBasedOnUserType(List<Long> locationValue,Date fromDate,Date toDate,Long userType,Long activityMemberId);
	public List<Object[]> get2016TotalRenewalCadreCountBasedOnUserType(List<Long> locationValue,Date fromDate,Date toDate,Long userType,Long activityMemberId);
	public Long getTotalCadreCountLocationWise(Long accessLvlId, List<Long>accessLvlValue, Long stateId, Date frmDt, Date toDt);
	public Long getTotalRenewlCadreLocationWise(Long accessLvlId, List<Long> accessLvlValue,Long stateId, Date frmDt, Date toDt);
	public Long getTotalConstituencyForCdrRegStarted(Long StateId);
	public List<Object[]> get2016TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId,String type,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalRenewalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getDateWiseLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO);
	public int insertTdpCadreLocInfoDateWiseUpToConstituencyLevel();
	public List<Object[]> get2016TotalCadreCountLocationWiseCountSpecial(Long userAccessLevelId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> getConstitiuencyWise2016CadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Date fromDate,Date toDate);
	public List<Object[]> getConstitiuencyWise2016RenewalCadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue,Date fromDate,Date toDate);
	public Long getTotalCadreCountLocationWiseTS(Long stateId, Date frmDt, Date toDt);
	public Long getTotalRenewlCadreLocationWiseTS(Long stateId, Date frmDt, Date toDt);
	public List<Object[]> getActualCountOfCadreSurveyUser(Long districtId,Long stateId,Date startDate,Date endDate,Long constituencyId);
	public List<Object[]> get2016CadreCntDistWise(List<Long> constituencyIds,Date fromDate,Date toDate,String districtName);
	public List<Object[]> get2016RenewalCadreCntDistWise(List<Long> constituencyIds,Date fromDate,Date toDate,String districtName);
	public List<Object[]> getLocationWiseDetailedOverViewDetails(Date fromDate,Date toDate,String locationType,Long locationVal);
}
