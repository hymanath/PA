package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentScope;

public class GovtDepartmentScopeDAO extends GenericDaoHibernate<GovtDepartmentScope, Long> implements IGovtDepartmentScopeDAO{

	public GovtDepartmentScopeDAO() {
		super(GovtDepartmentScope.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getgovtDepatScopeDetails(Long scopeId){
   		Query query = getSession().createQuery(" " +
   				" select model.govtDepartmentScopeId,model.levelName " +
   				" from GovtDepartmentScope model " +
   				" where " +
   				" model.govtDepartmentScopeId >=:scopeId  ");
   		query.setParameter("scopeId",scopeId);
   		return query.list();
     }
 }
