package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtOrderDAO;
import com.itgrids.partyanalyst.dao.IGovtOrderDocumentsDAO;
import com.itgrids.partyanalyst.model.GovtOrder;
import com.itgrids.partyanalyst.model.GovtOrderDocuments;

public class GovtOrderDocumentsDAO extends GenericDaoHibernate<GovtOrderDocuments, Long> implements IGovtOrderDocumentsDAO{

	public GovtOrderDocumentsDAO() {
		super(GovtOrderDocuments.class);
		// TODO Auto-generated constructor stub
	}

}
