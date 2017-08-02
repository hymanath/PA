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
	
	public List<Object[]> getGovtGrantTypeDetails(Long programId,Long govtSchemesId){
		
		StringBuilder sb = new StringBuilder();
		 sb.append(" select distinct model.grantType.grantTypeId,model.grantType.type from FundSanction model  where isDeleted= 'N' ");
		 if (govtSchemesId.longValue() > 0L)
			 sb.append(" and model.govtSchemeId =:govtSchemesId ");
		 if (programId.longValue() > 0L)
			 sb.append(" and model.subProgramId =:programId ");
		 Query query = getSession().createQuery(sb.toString());
		 if (govtSchemesId.longValue() > 0L)
			 query.setParameter("govtSchemesId",govtSchemesId);
		 if (programId.longValue() > 0L)
			 query.setParameter("programId",programId);
		 
		return query.list();	  
	}
	
	public List<Object[]> getGovtSchemsTypeDetails(Long programId,Long grantTypeId){
		
		StringBuilder sb = new StringBuilder();
		 sb.append(" select distinct model.govtScheme.govtSchemeId,model.govtScheme.schemeName from FundSanction model  where isDeleted= 'N' ");
		 if (grantTypeId != null && grantTypeId.longValue() > 0L)
			 sb.append(" and model.grantType.grantTypeId =:grantTypeId ");
		 if (programId != null && programId.longValue() > 0L)
			 sb.append(" and model.subProgramId =:programId ");
		 Query query = getSession().createQuery(sb.toString());
		 if (grantTypeId != null && grantTypeId.longValue() > 0L)
			 query.setParameter("grantTypeId",grantTypeId);
		 if (programId.longValue() > 0L)
			 query.setParameter("programId",programId);
		 
		return query.list();	  
	}

}
