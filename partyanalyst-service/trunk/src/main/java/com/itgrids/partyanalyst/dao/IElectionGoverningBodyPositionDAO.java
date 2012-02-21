package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ElectionGoverningBodyPosition;

public interface IElectionGoverningBodyPositionDAO extends GenericDao<ElectionGoverningBodyPosition, Long>{

	public List<ElectionGoverningBodyPosition> findByPosition(String position);
	
	public List<Object[]> getAllPositions();
	
	public List<ElectionGoverningBodyPosition> getPositionsByValue(String position);

}
