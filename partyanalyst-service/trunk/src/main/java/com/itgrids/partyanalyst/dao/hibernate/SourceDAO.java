package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;

public class SourceDAO extends GenericDaoHibernate<Source, Long> implements
		ISourceDAO {
	public SourceDAO() {
		super(Source.class);
	}

}
