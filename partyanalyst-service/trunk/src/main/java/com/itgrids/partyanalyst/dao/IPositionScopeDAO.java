package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PositionScope;

public interface IPositionScopeDAO  extends GenericDao<PositionScope, Long>  {
	public List<PositionScope> getPositionScopes(Long electionScopeId,Long electionGoverningBodyPositionId,Long electionType,Long ministerTypeId);
	
	public List<Object[]> getElectionTypeDetails(Long electionGoverningBodyPositionId);
	
	public List<Object[]> getPositionTypeDetails(Long electionGoverningBodyPositionId,Long electionType);
	
	public List<Object[]> getStateDetails(Long electionGoverningBodyPositionId,Long electionType,Long ministerTypeId);
	
	public List<Object[]> getMinisteryTypeDetails(Long electionGoverningBodyPositionId,Long electionType);
}
