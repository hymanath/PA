package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.SourceLanguage;

public class SourceLanguageDAO extends
		GenericDaoHibernate<SourceLanguage, Long> implements ISourceLanguageDAO {
	public SourceLanguageDAO() {
		super(SourceLanguage.class);
	}
	public List<Object[]> getSourceLanguageDetails(){
		   return getHibernateTemplate().find("select model.languageId,model.language from SourceLanguage  model order by model.language ");
	   }
}
