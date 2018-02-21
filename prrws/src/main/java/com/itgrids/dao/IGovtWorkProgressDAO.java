package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgress;

public interface IGovtWorkProgressDAO extends GenericDao<GovtWorkProgress, Long>{
	public List<Object[]> getLatestStatusOfWork(List<Long> workIds);
	public GovtWorkProgress getWorkProgressId(Long workId,Long govtWorkStatusId);
	public List<Object[]> getAllstatusInfoOfGovtWork(Long workId);
	public List<Object[]> getCompletedMianWorkPercentage(Long userId,Long workTypeId);
	public List<Object[]> getAllFininsedKmsOfWorks(List<Long> workIds);
	public List<Object[]> getCompletedWorksCount(Date fromDate,Date toDate);
}
