package com.itgrids.partyanalyst.dao.hibernate;

/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileNewsTypeDAO;

public class FileNewsTypeDAOHibernateTest  extends BaseDaoTestCase{
	private IFileNewsTypeDAO fileNewsTypeDAO;
	
	
	public IFileNewsTypeDAO getFileNewsTypeDAO() {
		return fileNewsTypeDAO;
	}


	public void setFileNewsTypeDAO(IFileNewsTypeDAO fileNewsTypeDAO) {
		this.fileNewsTypeDAO = fileNewsTypeDAO;
	}

	public void test(){
		List<Object[]> list = fileNewsTypeDAO.getNewsTypes(138654l);
		System.out.println(list.size());
	}
	
}
