package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.model.PmRepresenteeRefDetails;
@Repository
public class PmRepresenteeRefDetailsDAO extends GenericDaoHibernate<PmRepresenteeRefDetails, Long> implements IPmRepresenteeRefDetailsDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeRefDetailsDAO() {
		super(PmRepresenteeRefDetails.class);
	}

	

}
