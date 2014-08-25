package com.itgrids.partyanalyst.dao.hibernate;

/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentDAO;
import com.itgrids.partyanalyst.model.Department;

public class DepartmentDAO  extends GenericDaoHibernate<Department, Long>  implements IDepartmentDAO{
    public DepartmentDAO(){
    	super(Department.class);
    }
    
    public List<Object[]> getAllDepartments(){
    	Query query = getSession().createQuery("select " +
    			" model.departmentId," +
    			" model.departmentName from Department model");
    	
    	return query.list();
    }
    
   
}
