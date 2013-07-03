package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.electoralconnect.dao.IFileTypeDAO;
import com.itgrids.electoralconnect.model.FileType;

public class FileTypeDAO extends GenericDaoHibernate<FileType, Long> implements IFileTypeDAO{

	public FileTypeDAO() {
		super(FileType.class);
		
	}

}
