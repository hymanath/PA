package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDocumentDAO;
import com.itgrids.model.Document;

@Repository
public class DocumentDAO extends GenericDaoHibernate<Document, Long> implements IDocumentDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	public DocumentDAO() {
		super(Document.class);
	}
	
	public List<Long> getdocumentByFilePath(String path){
		if(path != null && !path.isEmpty()){
			StringBuilder str = new StringBuilder();
			str.append(" select model.documentId from Document model where model.path='"+path+"' and model.isDeleted='N'");
			Query query = getSession().createQuery(str.toString());
			return query.list();
		}
		return null;
	}
}
