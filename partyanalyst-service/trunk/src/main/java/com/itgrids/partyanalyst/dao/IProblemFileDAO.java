package com.itgrids.partyanalyst.dao;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemFile;

public interface IProblemFileDAO extends GenericDao<ProblemFile, Long> {

	public List<Object[]> getProblemImagesBasedHistoryId(Long probHistoryId);
	
	public List<Object[]> getAllNonApprovedImagesBetweenDatesWithCompleteData();
	
	public List<ProblemFile> getAllNonApprovedFilesAndProblemDetails();
	
	public List<ProblemFile> getAllApprovedImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<ProblemFile> getAllNoNApprovalImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<ProblemFile> getAllPostedImagesBetweenDates(Date startDate,Date endDate,String status);
	
	public List<ProblemFile> getApprovalImagesForParticularDate(Date particularDate,String status);
	
	public List<ProblemFile> getAllNonApprovalImagesForParticularDate(Date particularDate,String status);
	
	public List<ProblemFile> getAllImagesForParticularDate(Date particularDate,String status);
	
	public List<Object> getNoOfFilesUploadedForAUser(Long problemHistoryId);
	
	public Long getCountOfNewlyPostedImagesByFreeUser();
}
