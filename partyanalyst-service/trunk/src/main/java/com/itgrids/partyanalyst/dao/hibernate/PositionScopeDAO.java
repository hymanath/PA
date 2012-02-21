package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPositionScopeDAO;
import com.itgrids.partyanalyst.model.PositionScope;

public class PositionScopeDAO  extends GenericDaoHibernate<PositionScope, Long> implements IPositionScopeDAO{

	public PositionScopeDAO() {
		super(PositionScope.class);
	}
	public List<PositionScope> getPositionScopes(Long electionScopeId,Long electionGoverningBodyPositionId,String type)
	{
		Object[] data = {electionScopeId,electionGoverningBodyPositionId,type};
		return getHibernateTemplate().find("select model from PositionScope model where model.electionScope.electionScopeId = ?  and model.electionGoverningBodyPosition.governingBodyPositionId = ? and " +
				" model.status =? ",data);
	}
}
