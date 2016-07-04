package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IImportantLeadersDAO;
import com.itgrids.partyanalyst.model.ImportantLeaders;

public class ImportantLeadersDAO extends GenericDaoHibernate<ImportantLeaders, Long> implements IImportantLeadersDAO{

	public ImportantLeadersDAO() {
		super(ImportantLeaders.class);
		// TODO Auto-generated constructor stub
	}

}
