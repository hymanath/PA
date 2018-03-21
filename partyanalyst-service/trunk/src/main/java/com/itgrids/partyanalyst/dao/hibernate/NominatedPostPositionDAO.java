package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostPositionDAO;
import com.itgrids.partyanalyst.model.NominatedPostPosition;

public class NominatedPostPositionDAO extends GenericDaoHibernate<NominatedPostPosition, Long> implements INominatedPostPositionDAO{

	public NominatedPostPositionDAO() {
		super(NominatedPostPosition.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getDepartmentWisePositionList(Long boardId){
		StringBuilder  sb =new StringBuilder();
		sb.append(" select NPP.position.positionId, NPP.position.positionName  from NominatedPostPosition NPP " +
				" where NPP.board.boardId =:boardId and NPP.isDeleted ='N' ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("boardId", boardId);
		return query.list();
	}

}
