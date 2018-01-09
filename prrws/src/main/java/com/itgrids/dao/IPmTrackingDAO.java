package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmTracking;

public interface IPmTrackingDAO extends GenericDao<PmTracking, Long> {
	public List<Object[]> getPetitionTrackingHistoryDetails(Long petitionId,List<Long> subWorksList);
	public List<Object[]> getLatestTrackingDetails(Long petitionId,List<Long> subWorksList);
}
