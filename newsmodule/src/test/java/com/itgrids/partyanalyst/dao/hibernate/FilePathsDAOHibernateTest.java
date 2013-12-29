package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;

public class FilePathsDAOHibernateTest  extends BaseDaoTestCase{
	private IFilePathsDAO FilePathsDAO;

	public void setFilePathsDAO(IFilePathsDAO FilePathsDAO) {
		this.FilePathsDAO = FilePathsDAO;
	}

	public void testGetMxFilePathsDAO(){
		Long orderNO = FilePathsDAO.getMaxfilePathsOrderNo(1L);
		System.out.println(orderNO);
	}
}
