package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionDepartmentDAO;
import com.itgrids.model.PetitionDepartment;

@Repository
public class PetitionDepartmentDAO extends GenericDaoHibernate<PetitionDepartment, Long> implements IPetitionDepartmentDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionDepartmentDAO() {
		super(PetitionDepartment.class);
	}
	
	public List<Object[]> getAllPetitionList(){
		
		StringBuilder sb = new StringBuilder();
			sb.append("select model.petitionDepartmentId,model.departmentName from PetitionDepartment model where model.isDeleted ='N' order by model.orderNo asc");
			Query qry = getSession().createQuery(sb.toString());
			return qry.list();
		 
	}
}
