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
		
		    StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  model.position.positionId,model.position.positionName,model.departmentBoardPositionId from DepartmentBoardPosition model where model.isDeleted = 'N' ");
		   
		    if(deapartmentId != null && deapartmentId.longValue()> 0){
		      queryStr.append(" and model.departments.departmentId =:deapartmentId ");	
		    }
            if(boardId != null && boardId.longValue()> 0){
		      queryStr.append(" and model.board.boardId =:boardId ");	
		    }
		    Query query = getSession().createQuery(queryStr.toString());
			 if(deapartmentId != null && deapartmentId.longValue()> 0){
					query.setParameter("deapartmentId", deapartmentId);
			 }
			 if(boardId != null && boardId.longValue()> 0){
					query.setParameter("boardId", boardId); 
			 }
		return query.list();
	}
}
