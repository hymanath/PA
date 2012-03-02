package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPositionScopeDAO;
import com.itgrids.partyanalyst.model.PositionScope;

public class PositionScopeDAO  extends GenericDaoHibernate<PositionScope, Long> implements IPositionScopeDAO{

	public PositionScopeDAO() {
		super(PositionScope.class);
	}
	public List<PositionScope> getPositionScopes(Long electionScopeId,Long electionGoverningBodyPositionId,Long electionType,Long ministerTypeId)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model from PositionScope model where model.electionScope.electionScopeId = :electionScopeId  and model.electionGoverningBodyPosition.governingBodyPositionId = :electionGoverningBodyPositionId ");
		
		if(electionType != null && (electionType == 1l || electionType == 2l ))
			query.append(" and model.ministerType.ministerTypeId = :ministerTypeId ");	
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("electionScopeId", electionScopeId);
		queryObject.setLong("electionGoverningBodyPositionId", electionGoverningBodyPositionId);
		
		if(electionType != null && (electionType == 1l || electionType == 2l ))
			queryObject.setLong("ministerTypeId", ministerTypeId);
			
		return queryObject.list();				
	}
	public List<Object[]> getElectionTypeDetails(Long electionGoverningBodyPositionId)
	{
		return getHibernateTemplate().find("select distinct model.electionScope.electionType.electionTypeId ,model.electionScope.electionType.electionType from PositionScope model where " +
				"  model.electionGoverningBodyPosition.governingBodyPositionId = ?  ",electionGoverningBodyPositionId);
	}
	public List<Object[]> getPositionTypeDetails(Long electionGoverningBodyPositionId,Long electionType)
	{
		Object[] data = {electionGoverningBodyPositionId};
		return getHibernateTemplate().find("select distinct model.ministerType.ministerTypeId,model.ministerType.ministerType from PositionScope model where " +
				"  model.electionGoverningBodyPosition.governingBodyPositionId = ? and model.electionScope.electionType.electionTypeId = ? ",data);
	}
	public List<Object[]> getStateDetails(Long electionGoverningBodyPositionId,Long electionType,Long ministerTypeId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select distinct model.electionScope.state.stateId ,model.electionScope.state.stateName from PositionScope model where " +
				"  model.electionGoverningBodyPosition.governingBodyPositionId = :electionGoverningBodyPositionId ");
		
		if(electionType != null && (electionType == 1l || electionType == 2l ))
			query.append(" and model.ministerType.ministerTypeId = :ministerTypeId ");	
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("electionGoverningBodyPositionId", electionGoverningBodyPositionId);
		
		if(electionType != null && (electionType == 1l || electionType == 2l ))
			queryObject.setLong("ministerTypeId", ministerTypeId);
			
		return queryObject.list();	
	}
	public List<Object[]> getMinisteryTypeDetails(Long electionGoverningBodyPositionId,Long electionType)
	{
		Object[] data = {electionGoverningBodyPositionId,electionType};
		return getHibernateTemplate().find("select distinct model.ministerType.ministerTypeId ,model.ministerType.ministerType from PositionScope model where " +
				"  model.electionGoverningBodyPosition.governingBodyPositionId = ? and model.electionScope.electionType.electionTypeId =? ",data);
	}
}
