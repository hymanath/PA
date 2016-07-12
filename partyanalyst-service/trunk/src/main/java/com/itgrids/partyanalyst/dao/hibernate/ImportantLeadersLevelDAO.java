package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IImportantLeadersLevelDAO;
import com.itgrids.partyanalyst.model.ImportantLeadersLevel;

public class ImportantLeadersLevelDAO extends GenericDaoHibernate<ImportantLeadersLevel, Long> implements IImportantLeadersLevelDAO{

	public ImportantLeadersLevelDAO() {
		super(ImportantLeadersLevel.class);
		// TODO Auto-generated constructor stub
	}

}
