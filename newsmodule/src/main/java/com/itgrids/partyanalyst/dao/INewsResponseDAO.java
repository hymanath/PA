package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsResponse;

public interface INewsResponseDAO  extends GenericDao<NewsResponse,Long>{
	
	public List<Long> getCandidateNewsResponseFileIdsByFileID(Long fileId);
	
	 public List<Object[]> getLatestNewsResponses();
	 public List<Long> getCandidateNewsResponseFileIds(Long fileId);
	
}
