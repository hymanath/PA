package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.NewsResponse;

public interface INewsResponseDAO  extends GenericDao<NewsResponse,Long>{
	
	public List<Long> getCandidateNewsResponseFileIdsByFileID(Long fileId);
	
	 public List<Object[]> getLatestNewsResponses();
	 public List<Long> getCandidateNewsResponseFileIds(Long fileId);
	 
	 public List<File> getLatestFileDetails(int startIndex,int maxIndex);
	 
	 public Long getCountForNesResponce();
	 
	 public List<Long> getNewsResponceIds(int startIndex,int maxIndex);
	 
	 public List<Object[]> getResponceCountForFiles(List<Long> fileIds);
	 
	 public List<File> getLatestFileDetailsForBtDates(int startIndex,int maxIndex,Date fromDate,Date toDate);
	 
	 public List<Long> getNewsResponceIdsForBtDates(int startIndex,int maxIndex,Date fromDate,Date toDate);
	
	 public List<Long> getFileIds(List<Long> candidatePartyFileIds);
	 
	 public void deleteNewsResponses(List<Long> candidatePartyFileIds);
}
