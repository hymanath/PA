package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SourceLanguage;

public interface ISourceLanguageDAO extends GenericDao<SourceLanguage, Long> {
	public List<Object[]> getSourceLanguageDetails();
	
}
