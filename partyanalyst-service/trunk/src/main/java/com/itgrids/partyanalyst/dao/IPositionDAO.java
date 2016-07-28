package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Position;

public interface IPositionDAO extends GenericDao<Position, Long>{
	public List<Object[]> getAllPositions();
}
