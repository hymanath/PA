package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IFileDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;

public class FileDAOHibernateTest extends BaseDaoTestCase{
	
	private IFileDAO fileDAO;

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}


	public void test(){
		fileDAO.getAll();
	}

}
