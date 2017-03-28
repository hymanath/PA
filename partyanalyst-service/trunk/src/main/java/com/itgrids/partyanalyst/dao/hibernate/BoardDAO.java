package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoardDAO;
import com.itgrids.partyanalyst.model.Board;

public class BoardDAO extends GenericDaoHibernate<Board, Long> implements IBoardDAO{

	public BoardDAO() {
		super(Board.class);
		
	}

	public List<Object[]> getBoardsByIdsList(List<Long> boadsIds){
		Query query = getSession().createQuery(" select distinct model.boardId, model.boardName from Board model where  model.boardId in (:boadsIds) " +
				" order by model.boardName ");
		query.setParameterList("boadsIds", boadsIds);
		return query.list();
	}
}
