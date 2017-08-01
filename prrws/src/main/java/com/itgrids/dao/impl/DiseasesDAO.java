package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDiseasesDAO;
import com.itgrids.model.Diseases;
@Repository
public class DiseasesDAO extends GenericDaoHibernate<Diseases, Long> implements IDiseasesDAO {
	public DiseasesDAO(){
		super(Diseases.class);
	}
}
