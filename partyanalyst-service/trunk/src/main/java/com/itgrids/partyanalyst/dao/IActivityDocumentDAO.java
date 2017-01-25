package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityDocument;

public interface IActivityDocumentDAO extends GenericDao<ActivityDocument, Long>{
	public List<Object[]> getImagesCoveredAndTotalImagesForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,String  type,Long stateId);
}
