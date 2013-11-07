package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GallaryKeyword;

public interface IGallaryKeywordDAO extends GenericDao<GallaryKeyword, Long>{
	
	public List<Object[]> getGallaryKeywords(List<String> keywordsList);
	
	public List<String> getGallaryMappedKeywordsList(List<String> keywordsList);

	public List<Object[]> getUnAssignedKeyWords(Long userId);
	
	public Integer DeleteKeyWords(List<Long> keyWords,Long userId);
	
	public List<Object[]> getGallaryMapedKeyWords(Long userId);
	
	public List<Object[]> getGallaryMapedKeyWords(Long userId,List<Long> keyWords);
	
	public Long getGallaryKeywordId(Long keyWord,Long gallaryId);

}
