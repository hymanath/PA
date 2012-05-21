package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.model.FilePaths;

public class FilePathsDAO extends GenericDaoHibernate<FilePaths,Long> implements IFilePathsDAO {

	public FilePathsDAO(){
		super(FilePaths.class);
	}
	

}
