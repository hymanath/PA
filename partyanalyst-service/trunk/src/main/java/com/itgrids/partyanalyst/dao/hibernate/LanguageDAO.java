package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILanguageDAO;
import com.itgrids.partyanalyst.model.Language;

public class LanguageDAO extends GenericDaoHibernate<Language , Long> implements ILanguageDAO {

	public LanguageDAO() {
		super(Language.class);
		 
	}

	@SuppressWarnings("unchecked")
	public List<Language> findByLanguage(String language) {
		
		return getHibernateTemplate().find("from Language model where model.language = ? ", language);
	}

}
