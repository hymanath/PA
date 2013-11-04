package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.FileKeyword;
import com.itgrids.partyanalyst.dao.IFileKeywordDAO;


public class FileKeywordDAO extends GenericDaoHibernate<FileKeyword, Long> implements IFileKeywordDAO{

	public FileKeywordDAO() {
		super(FileKeyword.class);
		
	}
	

}

