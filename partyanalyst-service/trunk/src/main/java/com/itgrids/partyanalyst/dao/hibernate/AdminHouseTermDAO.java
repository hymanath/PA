package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseTermDAO;
import com.itgrids.partyanalyst.model.AdminHouseTerm;

public class AdminHouseTermDAO extends GenericDaoHibernate<AdminHouseTerm, Long> implements IAdminHouseTermDAO{

	public AdminHouseTermDAO() {
		super(AdminHouseTerm.class);
	}
	
	public List<Object[]> getAllElectionYears(){
		Query query = getSession().createQuery("select model.adminHouseTermId, " +
				" year(model.fromDate),year(model.toDate) " +
				" from AdminHouseTerm model " +
				" where model.adminHouse.adminHouseId = 3" +
				" and model.isActive = 'Y' and model.isDeleted = 'N' and model.adminHouse.electionScope.electionScopeId = 2");
		return query.list();
	}

}
