package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDocumentTypeDAO;
import com.itgrids.model.PmDocumentType;

@Repository
public class PmDocumentTypeDAO extends GenericDaoHibernate<PmDocumentType, Long> implements IPmDocumentTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDocumentTypeDAO() {
		super(PmDocumentType.class);
		
	}

	public List<Object[]> getPmDocumentTypeList(){
		StringBuilder sb = new StringBuilder("select model.pmDocumentTypeId,model.pmDocumentType from PmDocumentType model where model.isDeleted ='N' ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}

}
