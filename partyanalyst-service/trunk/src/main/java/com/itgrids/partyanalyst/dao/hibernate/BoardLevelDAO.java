package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.model.BoardLevel;

public class BoardLevelDAO extends GenericDaoHibernate<BoardLevel, Long> implements IBoardLevelDAO{

	public BoardLevelDAO() {
		super(BoardLevel.class);
		
	}

}
