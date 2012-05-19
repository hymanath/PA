package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFilePathDAO;
import com.itgrids.partyanalyst.model.FilePath;

public class FilePathDAO extends GenericDaoHibernate<FilePath,Long> implements IFilePathDAO {

	public FilePathDAO(){
		super(FilePath.class);
	}
	

}
