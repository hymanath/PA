package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;

public interface IFileDAO extends GenericDao<File, Long> {
	
	public List<Object[]> getFileByFileId(Long fileId);
	
	public List<Object[]> getCategoryDetailsOfAFile(Long fileId);
	
	public List<File> getAllFilesByFileIds(List<Long> fileIds);
	
	public Integer updateProblemFileDetailsByFileId(Long fileId, String fileTitle, String fileDescription);
	
	public List<File> getFilesByCategoryId(Long categoryId,Integer startIndex ,Integer endIndex,String newsType,Date fromDate,Date toDate);
	
	public List<File> getTotalFilesList(Long userId,Date fromDate,Date toDate);
	
}
