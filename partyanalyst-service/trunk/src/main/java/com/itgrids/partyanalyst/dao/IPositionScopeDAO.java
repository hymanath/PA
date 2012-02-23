package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PositionScope;

public interface IPositionScopeDAO  extends GenericDao<PositionScope, Long>  {
	public List<PositionScope> getPositionScopes(Long electionScopeId,Long electionGoverningBodyPositionId,String type);
	
	public List<Object[]> getElectionTypeDetails(Long electionGoverningBodyPositionId,String posType);
	
	public List<Object> getPositionTypeDetails(Long electionGoverningBodyPositionId);
	
	public List<Object[]> getStateDetails(Long electionGoverningBodyPositionId,String posType);
}
