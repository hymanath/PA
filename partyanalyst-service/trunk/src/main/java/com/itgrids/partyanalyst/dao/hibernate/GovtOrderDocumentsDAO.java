package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtOrderDocumentsDAO;
import com.itgrids.partyanalyst.model.GovtOrderDocuments;

public class GovtOrderDocumentsDAO extends GenericDaoHibernate<GovtOrderDocuments, Long> implements IGovtOrderDocumentsDAO{

	public GovtOrderDocumentsDAO() {
		super(GovtOrderDocuments.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getGovtOrderDocumentsPath(Long govtOrderId){
		Query query = getSession().createQuery("select distinct model.govtOrder.govtOrderId,model.path " +
				   							   " from GovtOrderDocuments model " +
				   							   " where  model.govtOrder.govtOrderId =:govtOrderId and " +
				   							   " model.isDeleted = 'N' ");
		query.setParameter("govtOrderId", govtOrderId);
		return query.list();
	}

}
