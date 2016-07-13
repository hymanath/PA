package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.model.DepartmentBoard;

public class DepartmentBoardDAO extends GenericDaoHibernate<DepartmentBoard, Long> implements IDepartmentBoardDAO{

	public DepartmentBoardDAO() {
		super(DepartmentBoard.class);
		
	}

	public List<Object[]> getDepartmentBoardByDeptId(Long deapartmentId){
		Query query = getSession().createQuery(" select  model.board.boardId,model.board.boardName,model.departmentBoardId from DepartmentBoard where " +
				" model.departments.departmentId =:deapartmentId and model.isDeleted = 'N' ");
		
		query.setParameter("deapartmentId", deapartmentId);
		return query.list();
	}
}
