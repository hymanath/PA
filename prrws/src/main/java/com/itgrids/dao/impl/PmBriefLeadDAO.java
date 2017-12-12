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
			public List<Object[]> gePmBriefLeadDetailsList(){
					
					Query qry = getSession().createQuery(" select model.pmBriefLeadId,model.briefLead from PmBriefLead model where model.isDeleted ='N' order by model.orderNo asc ");
					return qry.list();
				}
				
	
}
