package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmOfficerDepartMentOfficerDAO;
import com.itgrids.model.PmOfficerDepartMentOfficer;

@Repository
public class PmOfficerDepartMentOfficerDAO extends GenericDaoHibernate<PmOfficerDepartMentOfficer, Long>
		implements IPmOfficerDepartMentOfficerDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	PmOfficerDepartMentOfficerDAO(){
		super(PmOfficerDepartMentOfficer.class);
	}

}
