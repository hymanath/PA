package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IClusterAreaCategoryDAO;
import com.itgrids.model.ClusterAreaCategory;

@Repository
public class ClusterAreaCategoryDAO extends GenericDaoHibernate<ClusterAreaCategory, Long> implements IClusterAreaCategoryDAO  {
	@Autowired
	SessionFactory sessionFactory; 

	public ClusterAreaCategoryDAO() {
		super(ClusterAreaCategory.class);
	}
}
