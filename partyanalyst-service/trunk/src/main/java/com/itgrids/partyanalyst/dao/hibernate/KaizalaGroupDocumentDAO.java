package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaEventsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupDocumentDAO;
import com.itgrids.partyanalyst.model.KaizalaEvents;
import com.itgrids.partyanalyst.model.KaizalaGroupDocument;

public class KaizalaGroupDocumentDAO extends GenericDaoHibernate<KaizalaGroupDocument, Long> implements IKaizalaGroupDocumentDAO{

	public KaizalaGroupDocumentDAO() {
		super(KaizalaGroupDocument.class);
		
	}	
}
