package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
	public List<Object[]> gePmBriefLeadDetailsList(Long deptDesignationId){
		Query qry = getSession().createQuery(" select distinct model.pmBriefLead.pmBriefLeadId,model.pmBriefLead.briefLead from PmDepartmentDesignationBriefLeads model " +
				" where model.pmBriefLead.isDeleted='N' and model.isDeleted ='N' and model.pmDepartmentDesignationId=:deptDesignationId " +
				" order by model.pmBriefLead.orderNo asc ");
		qry.setParameter("deptDesignationId", deptDesignationId);
		return qry.list();
	}
}       

