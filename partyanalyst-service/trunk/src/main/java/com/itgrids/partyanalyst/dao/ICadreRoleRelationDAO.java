package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRoleRelation;

public interface ICadreRoleRelationDAO extends GenericDao<CadreRoleRelation,Long>{
	
	public List<Object[]> getRolesByCadreId(Long cadreId);
	
	public Integer deleteRolesByCadreId(Long cadreId);
}
