package com.itgrids.partyanalyst.dao.hibernate;

/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileDepartmentDAO;

public class FileDepartmentDAOHibernateTest  extends BaseDaoTestCase{
	private IFileDepartmentDAO fileDepartmentDAO;
	
	
	
	public IFileDepartmentDAO getFileDepartmentDAO() {
		return fileDepartmentDAO;
	}



	public void setFileDepartmentDAO(IFileDepartmentDAO fileDepartmentDAO) {
		this.fileDepartmentDAO = fileDepartmentDAO;
	}

	public void test(){
		List<Object[]> list = fileDepartmentDAO.getDepartmentsOfFile(138654l);
		System.out.println(list.size());
	}
}
