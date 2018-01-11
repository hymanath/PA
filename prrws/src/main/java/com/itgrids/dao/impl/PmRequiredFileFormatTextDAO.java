package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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

}
