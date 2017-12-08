package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmLeadDAO;
import com.itgrids.model.PmLead;

@Repository
public class PmLeadDAO extends GenericDaoHibernate<PmLead, Long> implements IPmLeadDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmLeadDAO() {
		super(PmLead.class);
		// TODO Auto-generated constructor stub
	}
}
