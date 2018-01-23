package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRequiredFileFormatTextDAO;
import com.itgrids.model.PmRequiredFileFormatText;

@Repository
public class PmRequiredFileFormatTextDAO extends GenericDaoHibernate<PmRequiredFileFormatText, Long> implements IPmRequiredFileFormatTextDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public PmRequiredFileFormatTextDAO() {
		super(PmRequiredFileFormatText.class);
	}
	
	public String getCoverLetterMessage(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.formatText from PmRequiredFileFormatText model where model.isDeleted = 'N' ");
		
		Query query = getSession().createQuery(sb.toString());
		return (String)query.uniqueResult();
		
	}

}
