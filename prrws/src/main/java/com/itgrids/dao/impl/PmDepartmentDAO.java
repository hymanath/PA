package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDAO;
import com.itgrids.model.PmDepartment;
@Repository
public class PmDepartmentDAO extends GenericDaoHibernate<PmDepartment, Long> implements IPmDepartmentDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDAO() {
		super(PmDepartment.class);
		
	}
		public List<Object[]> getAllPmDepartmentsList(){
				StringBuilder sb = new StringBuilder();
				sb.append("select model.pmDepartmentId,model.department from PmDepartment model where model.isDeleted ='N' order by model.department asc ");
				Query qry = getSession().createQuery(sb.toString());
				return qry.list();
		}
		
		public List<Object[]> getGivenPmDepartmentsList(){
			StringBuilder sb = new StringBuilder();
				sb.append("select distinct model.petitionDepartment.petitionDepartmentId,model.petitionDepartment.departmentName from PetitionWorkDetails model " +
						" where model.isDeleted ='N' order by model.petitionDepartment.departmentName asc ");
				Query qry = getSession().createQuery(sb.toString());
				return qry.list();
			 
		}
	
}
