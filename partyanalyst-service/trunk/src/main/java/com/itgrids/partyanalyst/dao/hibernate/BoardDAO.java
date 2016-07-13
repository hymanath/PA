package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoardDAO;
import com.itgrids.partyanalyst.model.Board;

public class BoardDAO extends GenericDaoHibernate<Board, Long> implements IBoardDAO{

	public BoardDAO() {
		super(Board.class);
		
	}

}
