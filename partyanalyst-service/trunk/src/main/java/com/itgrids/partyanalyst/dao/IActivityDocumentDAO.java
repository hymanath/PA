package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityDocument;

public interface IActivityDocumentDAO extends GenericDao<ActivityDocument, Long>{
	public List<Object[]> getImagesCoveredAndTotalImagesCountForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,String  type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getImagesCoveredAndTotalImagesForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,String  type,Long stateId,Long levelId
			,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getLocationWiseCount(Long activityScopeId,Long locationScopeId, Date startDate, Date endDate);
}
