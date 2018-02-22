package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmMinistryDepartmentDAO;
import com.itgrids.model.PmMinistryDepartment;

@Repository
public class PmMinistryDepartmentDAO extends GenericDaoHibernate<PmMinistryDepartment, Long> implements IPmMinistryDepartmentDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmMinistryDepartmentDAO() {
		super(PmMinistryDepartment.class);
		// TODO Auto-generated constructor stub
	}
}
