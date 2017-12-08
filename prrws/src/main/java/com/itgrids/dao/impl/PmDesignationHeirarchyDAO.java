package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDesignationHeirarchyDAO;
import com.itgrids.model.PmDesignationHeirarchy;

@Repository
public class PmDesignationHeirarchyDAO extends GenericDaoHibernate<PmDesignationHeirarchy, Long> implements IPmDesignationHeirarchyDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PmDesignationHeirarchyDAO(){
		super(PmDesignationHeirarchy.class);
	}
}
