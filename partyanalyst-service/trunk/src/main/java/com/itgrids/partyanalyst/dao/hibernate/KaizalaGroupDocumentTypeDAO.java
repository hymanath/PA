package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaGroupDocumentTypeDAO;
import com.itgrids.partyanalyst.model.KaizalaGroupDocumentType;

public class KaizalaGroupDocumentTypeDAO extends GenericDaoHibernate<KaizalaGroupDocumentType, Long> implements IKaizalaGroupDocumentTypeDAO{

	public KaizalaGroupDocumentTypeDAO() {
		super(KaizalaGroupDocumentType.class);
		
	}
	
	public Long getGroupDocumentTypeId(String documentType){
		
		Query query = getSession().createQuery(" select model.kaizalaGroupDocumentTypeId from KaizalaGroupDocumentType model " +
				" where model.type =:documentType   ");
		
		query.setParameter("documentType", documentType);
		return (Long)query.uniqueResult();
		
	}
}
