package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Language;

public interface ILanguageDAO extends GenericDao<Language, Long> {
	public List<Language> findByLanguage(String language);
}
