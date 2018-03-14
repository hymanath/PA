package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionRequestJsonDAO;
import com.itgrids.model.PmPetitionRequestJson;

@Repository
public class PmPetitionRequestJsonDAO extends GenericDaoHibernate<PmPetitionRequestJson, Long> implements IPmPetitionRequestJsonDAO {
	
	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionRequestJsonDAO() {
		super(PmPetitionRequestJson.class);
	}
}
