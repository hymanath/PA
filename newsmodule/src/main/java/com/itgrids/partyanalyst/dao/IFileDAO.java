package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.model.File;

public interface IFileDAO extends GenericDao<File, Long> {
	
	public List<Object[]> getFileByFileId(Long fileId);
	
	public List<Object[]> getCategoryDetailsOfAFile(Long fileId);
	
	public List<File> getAllFilesByFileIds(List<Long> fileIds);
	
	public Integer updateProblemFileDetailsByFileId(Long fileId, String fileTitle, String fileDescription);
	
	public List<File> getFilesByCategoryId(Long categoryId,Integer startIndex ,Integer endIndex,String newsType,Date fromDate,Date toDate);
	
	public List<File> getTotalFilesList(Long userId,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex);
	
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,Integer startIndex,Integer maxIndex);
	
	public List<File> getAllLatestFilesByFileIds(List<Long> fileIds);
	 
	public List<Object[]> getNewsBySearchCriteria(String query,AnalysisVO vo);
	 
	public List<Object[]> getSelectedNewsBySearchCriteria(String query,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex);
	
	public Long getSelectedNewsCountBySearchCriteria(String query,Date fromDate,Date toDate);
	 
	 public Long getTotalFilesListCount(Date fromDate,Date toDate);
	 
	 public Long getFilesByCategoryIdCount(Long categoryId,String newsType,Date fromDate,Date toDate);
	 
	 public List<File> getTotalFilesListByLocation(Long userId,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex,Long locationVal,Long scopeId);
	 
	 
	 public Integer deleteFile(Long fileId);
	 
	 public List<File> getFile(Long fileId);
}
