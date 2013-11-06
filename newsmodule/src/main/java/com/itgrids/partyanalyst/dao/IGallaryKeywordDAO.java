package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GallaryKeyword;

public interface IGallaryKeywordDAO extends GenericDao<GallaryKeyword, Long>{
	
	public List<Object[]> getGallaryKeywords(List<String> keywordsList);
	
	public List<String> getGallaryMappedKeywordsList(List<String> keywordsList);

}
