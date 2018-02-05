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
	public List<Object[]> getAllBoardLevels(Long boardLevelId){
		StringBuilder sb =new StringBuilder();
		sb.append(" select model.boardLevelId, model.level from BoardLevel model where  model.boardLevelId not in(1,6,8,2) " );
		if(boardLevelId != null && boardLevelId.longValue()>0l){
		  sb.append(" and model.boardLevelId >=:boardLevelId ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(boardLevelId != null && boardLevelId.longValue()>0l){
			query.setParameter("boardLevelId", boardLevelId);
		}
		return query.list();
	}

}
