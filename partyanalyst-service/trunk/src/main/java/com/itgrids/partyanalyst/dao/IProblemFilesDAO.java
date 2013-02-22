package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ProblemFiles;

public interface IProblemFilesDAO extends GenericDao<ProblemFiles,Long>{
	public List<Object[]> getCurrentDateFiles(Date currentDate,Date endDate,String isApproved);

	public List<Object> getNoOfFilesUploadedForAUser(Long problemId);
	
	public List<Object[]> getProblemRelatedFilesForAUser(Long problemId,Long userId);
	
	//public Long getCountOfNewlyPostedImagesByFreeUser();
	
	public Long getCountOfNewlyPostedImagesByFreeUser(Date currentDate);
	
	public Integer deleteProblemFile(Long problemFileId);

}
