package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGrantTypeDAO;
import com.itgrids.model.GrantType;

@Repository
public class GrantTypeDAO extends GenericDaoHibernate<GrantType, Long> implements IGrantTypeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public GrantTypeDAO() {
		super(GrantType.class);
	}
	public List<Object[]> getGrandTypeDtls(){
		Query query = getSession().createQuery(" select model.grantTypeId, model.type from GrantType model ");
		return query.list();
	}
	
	public List<Object[]> getGovtGrantTypeDetails(Long govtSchemesId){
		
	    
	    if(govtSchemesId == null || govtSchemesId.longValue() == 0L){
			  StringBuilder sb = new StringBuilder();
			    sb.append(" select distinct model.grantType.grantTypeId,model.grantType.type from GrantType model ");
			    Query query = getSession().createQuery(sb.toString());
			    return query.list(); 
		}
		else if (govtSchemesId.longValue() > 0L){
			StringBuilder sb = new StringBuilder();
		    sb.append(" select distinct model.grantType.grantTypeId,model.grantType.type from FundSanction model "+
		               " where isDeleted= 'N' and model.govtSchemeId =:govtSchemesId ");
		    Query query = getSession().createQuery(sb.toString());
		    query.setParameter("govtSchemesId",govtSchemesId);
		    return query.list(); 
		}
		return null;	  
	}
}
