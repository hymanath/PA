package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDepartmentDAO;
import com.itgrids.model.Department;

@Repository
public class DepartmentDAO extends GenericDaoHibernate<Department, Long> implements IDepartmentDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DepartmentDAO() {
		super(Department.class);

	}
	
	public List<Object[]> getAllDepartments(){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select model.departmentId,model.departmentName from Department model "+
	               " order by model.departmentId DESC");
	    Query query = getSession().createQuery(sb.toString());
	    return query.list(); 
	    
	  }

}
