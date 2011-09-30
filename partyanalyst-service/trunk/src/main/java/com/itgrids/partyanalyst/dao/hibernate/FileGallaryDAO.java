package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.FileGallary;

public class FileGallaryDAO extends GenericDaoHibernate<FileGallary, Long> implements IFileGallaryDAO{
	
	public FileGallaryDAO(){
		super(FileGallary.class);
	}
}
