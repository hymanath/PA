package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgressTrack;

public interface IGovtWorkProgressTrackDAO extends GenericDao<GovtWorkProgressTrack, Long>{
	public List<Object[]> getStatusWiseDayReport(Long workTypeId,Long workId,Date startDate,Date endDate,String reportType);
}
