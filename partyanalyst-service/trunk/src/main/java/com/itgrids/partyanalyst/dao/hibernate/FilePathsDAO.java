package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.model.FilePaths;

public class FilePathsDAO extends GenericDaoHibernate<FilePaths,Long> implements IFilePathsDAO {

	public FilePathsDAO(){
		super(FilePaths.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMaxOrderNo()
	{
		return getHibernateTemplate().find("select max(model.orderNo) from FilePaths model");
	}

}
