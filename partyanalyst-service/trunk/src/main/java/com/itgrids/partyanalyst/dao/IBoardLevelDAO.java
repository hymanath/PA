package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoardLevel;

public interface IBoardLevelDAO extends GenericDao<BoardLevel, Long>{

	public List<Object[]> getBoardLevels();
	public List<Object[]> getAllBoardLevels(Long boardLevelId);
}
