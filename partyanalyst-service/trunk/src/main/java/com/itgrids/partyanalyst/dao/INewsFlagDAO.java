package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.NewsFlag;

public interface INewsFlagDAO extends GenericDao<NewsFlag, Long>{
	
	public List<Object[]> getCountForFlagByFileGallaryId(List<Long> contentIds);
	public void removeFlagForNews(Long contentId);
	public List<Long> getCountForFlagByContentId(Long contentId);
	
	public List<FileGallary> getFlagedMews(List<Long> contentIds);

	public Long getFlaggedNewsCount(FileVO inputs,List<Long> contentIds);
}
