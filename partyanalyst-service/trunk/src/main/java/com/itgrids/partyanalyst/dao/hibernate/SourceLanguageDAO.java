package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.SourceLanguage;

public class SourceLanguageDAO extends
		GenericDaoHibernate<SourceLanguage, Long> implements ISourceLanguageDAO {
	public SourceLanguageDAO() {
		super(SourceLanguage.class);
	}
	
}
