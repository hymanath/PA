package com.itgrids.partyanalyst.dao;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemFile;

public interface IProblemFileDAO extends GenericDao<ProblemFile, Long> {

	public List<Object[]> getProblemImagesBasedHistoryId(Long probHistoryId);
	
	public List<Object[]> getAllNonApprovedImagesBetweenDatesWithCompleteData();
	
	public List<Object[]> getAllNonApprovedFilesAndProblemDetails();
	
	public List<Object[]> getAllApprovedImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<Object[]> getAllNoNApprovalImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<Object[]> getAllPostedImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<Object[]> getApprovalImagesForParticularDate(Date particularDate,String status);
	
	public List<Object[]> getAllNonApprovalImagesForParticularDate(Date particularDate,String status);
	
	public List<Object[]> getAllImagesForParticularDate(Date particularDate,String status);
}
