package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.model.ActivityDocument;

public class ActivityDocumentDAO extends GenericDaoHibernate<ActivityDocument, Long> implements IActivityDocumentDAO{

	public ActivityDocumentDAO() {
		super(ActivityDocument.class);
		
	}

}
