package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.model.BoardLevel;

public class BoardLevelDAO extends GenericDaoHibernate<BoardLevel, Long> implements IBoardLevelDAO{

	public BoardLevelDAO() {
		super(BoardLevel.class);
		
	}
	
	public List<Object[]> getBoardLevels(){
		Query query = getSession().createQuery(" select model.boardLevelId, model.level from BoardLevel model ");
		return query.list();
	}

}
