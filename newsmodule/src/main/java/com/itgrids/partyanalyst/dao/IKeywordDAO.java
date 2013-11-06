package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Keyword;

public interface IKeywordDAO extends GenericDao<Keyword, Long> {
	
	public List<Object[]> getTotalKeyWords();
	
	public List<Object[]> getKeyWordsList(List<String> keywordList);
	
	public List<Object[]> getKeyWordIdByName(String keywordName);
	
	public List<String> getExistingKeywordsByKeywordsList(List<String> keywordList);

}
