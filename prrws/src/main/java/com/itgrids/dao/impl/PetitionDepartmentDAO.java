package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
}
