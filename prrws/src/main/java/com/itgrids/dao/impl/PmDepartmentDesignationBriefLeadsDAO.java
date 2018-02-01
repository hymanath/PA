package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationBriefLeadsDAO;
import com.itgrids.model.PmDepartmentDesignationBriefLeads;

@Repository
public class PmDepartmentDesignationBriefLeadsDAO extends GenericDaoHibernate<PmDepartmentDesignationBriefLeads, Long>
		implements IPmDepartmentDesignationBriefLeadsDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDesignationBriefLeadsDAO() {
		super(PmDepartmentDesignationBriefLeads.class);
		
	}

	

}
