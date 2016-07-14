package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentBoardPositionDAO;
import com.itgrids.partyanalyst.model.DepartmentBoardPosition;

public class DepartmentBoardPositionDAO extends GenericDaoHibernate<DepartmentBoardPosition, Long> implements IDepartmentBoardPositionDAO{

	public DepartmentBoardPositionDAO() {
		super(DepartmentBoardPosition.class);
		
	}
	public List<Object[]> getDepartmentBoardPositionsByDeptIdABrdId(Long deapartmentId,Long boardId){
		Query query = getSession().createQuery(" select  model.position.positionId,model.position.positionName,model.departmentBoardPositionId from DepartmentBoardPosition model where " +
				" model.departments.departmentId =:deapartmentId and model.board.boardId =:boardId and model.isDeleted = 'N' ");
		
		query.setParameter("deapartmentId", deapartmentId);
		query.setParameter("boardId", boardId);
		return query.list();
	}
}
