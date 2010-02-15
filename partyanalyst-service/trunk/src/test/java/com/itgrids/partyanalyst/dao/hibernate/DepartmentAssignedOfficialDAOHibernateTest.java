package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDepartmentAssignedOfficialDAO;
import com.itgrids.partyanalyst.model.DepartmentAssignedOfficial;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;

public class DepartmentAssignedOfficialDAOHibernateTest extends BaseDaoTestCase{

	private IDepartmentAssignedOfficialDAO departmentAssignedOfficialDAO;

	public IDepartmentAssignedOfficialDAO getDepartmentAssignedOfficialDAO() {
		return departmentAssignedOfficialDAO;
	}

	public void setDepartmentAssignedOfficialDAO(
			IDepartmentAssignedOfficialDAO departmentAssignedOfficialDAO) {
		this.departmentAssignedOfficialDAO = departmentAssignedOfficialDAO;
	}
	
	public void testGetAll(){
		List<DepartmentAssignedOfficial> list = departmentAssignedOfficialDAO.getAll();
		assertEquals(1, list.size());
	}
	
	/*public void testSave(){
		ProblemSourceScopeConcernedDepartment obj1 = new ProblemSourceScopeConcernedDepartment(new Long(1)); 
	}*/
	
}
