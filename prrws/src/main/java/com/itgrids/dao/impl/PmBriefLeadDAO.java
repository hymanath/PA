package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.model.PmBriefLead;
@Repository
public class PmBriefLeadDAO extends GenericDaoHibernate<PmBriefLead, Long> implements IPmBriefLeadDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmBriefLeadDAO() {
		super(PmBriefLead.class);
	}

}
