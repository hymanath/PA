package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionDAO;
import com.itgrids.model.Petition;

@Repository
public class PetitionDAO extends GenericDaoHibernate<Petition, Long> implements IPetitionDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PetitionDAO() {
		super(Petition.class);
	}

	

}
