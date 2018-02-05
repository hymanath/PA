package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

public interface INominatedPostGovtOrderDAO extends GenericDao<NominatedPostGovtOrder, Long>{
	public List<Object[]> gettingGoPassedDates(Long nominatedPostId);
	public List<Long> getExpiredNominatedPostIdsLsit(Date currentDate);
	public int updateApplicationExpiredByPostIds(List<Long> nominatedPostIdsLsist, Date currentDate,Long userId);
	public List<Long> getNominatedPostGovtOrderByPostId(Long postId);
	public List<Object[]> getLevelWiseGoIssuedPostions(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,List<Long> statusIds,Long startIndex,Long endIndex);
	public List<Object[]> getLevelWiseGoIssuedDate(List<Long> locationValues,Date startDate, Date endDate,Long locationTypeId,String year,Long boardLevelId,List<Long> statusIds,Set<Long> nominatedPostIds);
	public List<Object[]> getNominatedPostExpireDetails(List<Long> locationValues, Date startDate, Date endDate,Long locationTypeId, String year, Long boardLevelId,Long deptId);
	public List<Object[]> getNominatedPostStateWiseExpireDetails(Date startDate, Date endDate);
}
